        /**
         * Sets the maximum objects to be held in memory
         */

        public void setMaxElementsInMemory(int maxElementsInMemory) {
            this.maxElementsInMemory = maxElementsInMemory;
        }

    /**
     * Notifies all registered listeners, in no guaranteed order, that an element was removed
     * @param element
     * @see CacheEventListener#notifyElementRemoved
     */

    public void notifyElementRemoved(Element element) {
        Iterator iterator = cacheEventListeners.iterator();
        while (iterator.hasNext()) {
            CacheEventListener cacheEventListener = (CacheEventListener) iterator.next();
            cacheEventListener.notifyElementRemoved(cache, element);
        }
    }

        /**
         * Sets the eviction policy. An invalid argument will set it to null
         */

        public void setMemoryStoreEvictionPolicy(String memoryStoreEvictionPolicy) {
            this.memoryStoreEvictionPolicy = MemoryStoreEvictionPolicy.fromString(memoryStoreEvictionPolicy);
        }

    /**
     * Notifies all registered listeners, in no guaranteed order, that an element has expired
     * @param element
     * @see CacheEventListener#notifyElementExpired
     */

    public void notifyElementExpiry(Element element) {
        Iterator iterator = cacheEventListeners.iterator();
        while (iterator.hasNext()) {
            CacheEventListener cacheEventListener =  (CacheEventListener) iterator.next();
            cacheEventListener.notifyElementExpired(cache, element);
        }
    }

    /**
     * Adds a listener to the notification service. No guarantee is made that listeners will be
     * notified in the order they were added.
     * @param cacheEventListener
     * @return true if the listener is being added and was not already added
     */

    public boolean registerListener(CacheEventListener cacheEventListener) {
        return cacheEventListeners.add(cacheEventListener);
    }

    /**
     * Removes a listener from the notification service.
     * @param cacheEventListener
     * @return true if the listener was present
     */

    public boolean unregisterListener(CacheEventListener cacheEventListener) {
        return cacheEventListeners.remove(cacheEventListener);
    }

    /**
     * Gets a list of the listeners registered to this class
     * @return a list of type <code>CacheEventListener</code>
     */

    public Set getCacheEventListeners() {
        return cacheEventListeners;
    }

	/**
	 * Returns whether the square at the passed position is a wall
	 * or a simple deadlock square.
	 * 
	 * @param position the position of the square
	 * @return <code>true</code> if the square is a wall or a simple
	 *                           deadlock square, or<br>
	 * 		  <code>false</code> if the square is neither a wall nor a simple
	 *                           deadlock square
	 */

	final public boolean isWallOrIllegalSquare(int position) {
		return     wallsArray[position] > 0
				|| simpleDeadlockSquareForwards[position]
				|| advancedSimpleDeadlockSquareForwards[position];
	}

        /**
         * Sets whether elements are eternal. If eternal,  timeouts are ignored and the element
         * is never expired.
         */

        public void setEternal(boolean eternal) {
            this.eternal = eternal;
        }

	/**
	 * Returns whether the current level is valid.
	 * <P>
	 * If the level is invalid an info message is displayed.
	 * 
	 * @return <code>true</code> if the level is valid, and<br>
	 *        <code>false</code> if the level is invalid
	 */

	public boolean isLevelValid() {

		StringBuilder validityMessage = new StringBuilder();

		// Let the board check if it is valid.
		boolean levelIsValid = board.isValid(validityMessage);

		// If the level is invalid the editor mustn't be left and the
		// reason for the invalidity is displayed.
		if (levelIsValid == false) {
			displayInfotext(validityMessage.toString());
			applicationGUI.setEditorMenuItemEnabled(false);
		} else {
			displayInfotext("");
			applicationGUI.setEditorMenuItemEnabled(true);
		}

		return levelIsValid;
	}

	/**
	 * Uses JavaGroups to broadcast the supplied notification message across the
	 * cluster.
	 * 
	 * @param message
	 *            The cluster nofication message to broadcast.
	 */

	public void sendNotification(ClusterNotification message) {
		if (log.isDebugEnabled())
			log.debug("sendNotification : " + message);
		bus.sendNotification(message);
	}

        /**
         * Sets the time to idle for an element before it expires. Is only used
         * if the element is not eternal.
         */

        public void setTimeToIdleSeconds(int timeToIdleSeconds) {
            this.timeToIdleSeconds = timeToIdleSeconds;
        }

	/**
	 * We are not using the caching, so we just return something that identifies
	 * us. This method should never be called directly.
	 */

	public Serializable getCache() {
		return "BroadcastingManager: " + bus.getLocalAddress();
	}

	/**
	 * A callback that is fired when a new member joins the cluster. This method
	 * should never be called directly.
	 * 
	 * @param address
	 *            The address of the member who just joined.
	 */

	public void memberJoined(Address address) {
		if (log.isInfoEnabled()) {
			log.info("A new member at address '" + address + "' has joined the cluster");
		}
	}

	/**
	 * A callback that is fired when an existing member leaves the cluster. This
	 * method should never be called directly.
	 * 
	 * @param address
	 *            The address of the member who left.
	 */

	public void memberLeft(Address address) {
		if (log.isInfoEnabled()) {
			log.info("Member at address '" + address + "' left the cluster");
		}
	}

        /**
         * Sets the time to idle for an element before it expires. Is only used
         * if the element is not eternal.
         */

        public void setTimeToLiveSeconds(int timeToLiveSeconds) {
            this.timeToLiveSeconds = timeToLiveSeconds;
        }

	/**
	 * Returns whether the square at the passed position is a wall
	 * or a simple deadlock square.
	 * The arguments are not checked against the board dimensions.
	 * 
	 * @param x the x-coordinate of the position
	 * @param y the y-coordinate of the position
	 * @return <code>true</code> if the square is a wall or a simple
	 *                           deadlock square, or<br>
	 * 		  <code>false</code> if the square is neither a wall nor a simple
	 *                           deadlock square
	 */

	final public boolean isWallOrIllegalSquare(int x, int y) {
		return     wallsArray[x + width * y] > 0
				|| simpleDeadlockSquareForwards[x + width * y]
				|| advancedSimpleDeadlockSquareForwards[x + width * y];
	}

    /**
     * Shuts down the CacheManager.
     * <p/>
     * If the shutdown occurs on the singleton, then the singleton is removed, so that if a singleton access method
     * is called, a new singleton will be created.
     */

    public void shutdown() {
        if (status.equals(Status.STATUS_SHUTDOWN)) {
            if (LOG.isWarnEnabled()) {
                LOG.warn("CacheManager already shutdown");
            }
            return;
        }
        synchronized (CacheManager.class) {
            allCacheManagersDiskStorePaths.remove(diskStorePath);
            Enumeration allCaches = caches.elements();
            while (allCaches.hasMoreElements()) {
                Cache cache = (Cache) allCaches.nextElement();
                if (cache != null) {
                    cache.dispose();
                }
            }
            if ( getBroadcastingManager()!=null) getBroadcastingManager().stop();
            status = Status.STATUS_SHUTDOWN;
            
            //only delete singleton if the singleton is shutting down.
            if (this == singleton) {
                singleton = null;
            }
        }
    }

        /**
         * Sets whether elements can overflow to disk when the in-memory cache
         * has reached the set limit.
         */

        public void setOverflowToDisk(boolean overflowToDisk) {
            this.overflowToDisk = overflowToDisk;
        }

        /**
         * Sets whether, for caches that overflow to disk,
         * the disk cache persist between CacheManager instances
         */

        public void setDiskPersistent(boolean diskPersistent) {
            this.diskPersistent = diskPersistent;
        }

        /**
         * Sets the interval in seconds between runs of the disk expiry thread.
         * <p/>
         * 2 minutes is the default.
         * This is not the same thing as time to live or time to idle. When the thread runs it checks
         * these things. So this value is how often we check for expiry.
         */

        public void setDiskExpiryThreadIntervalSeconds(int diskExpiryThreadIntervalSeconds) {
            this.diskExpiryThreadIntervalSeconds = diskExpiryThreadIntervalSeconds;
        }

	/**
	 * Returns whether the passed position is an outer square or a wall.
	 * An outer square is a square which is outside the reachable area
	 * of the player even if there weren't any boxes on the board.
	 * 
	 * @param position the position of the square
	 * @return <code>true</code> if the square is an outer square or a wall,
	 * or<br> <code>false</code> if the square isn't an outer square or a wall 
	 */

	final public boolean isOuterSquareOrWall(int position) {
		return    ! playersReachableSquaresOnlyWallsAtLevelStart[position]
		       || wallsArray[position] > 0;
	}

	/**
	 * Returns whether the square at the passed position is a simple
	 * deadlock square.
	 * <p>
	 * The search direction doesn't matter, because the simple deadlock squares
	 * of the other direction can never be reached from a specific direction.
	 * Therefore both the forward and the backward simple deadlock squares
	 * are checked.
	 * 
	 * @param position the position of the square
	 * @return <code>true</code> if the square is a simple deadlock square,
	 * or<br> <code>false</code> if the square isn't a simple deadlock square
	 */

	final public boolean isSimpleDeadlockSquare(int position) {
		return     simpleDeadlockSquareForwards[position]
				|| advancedSimpleDeadlockSquareForwards[position]
				|| simpleDeadlockSquareBackwards[position];
	}

	/**
	 * This method is called upon plug-in activation
	 */

	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/**
	 * This method is called when the plug-in is stopped
	 */

	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

    /**
     *	Set the current input focus to self's destination entry field
     */

    protected void giveFocusToProjectsNames() {
        projectsNamesCombo.setFocus();
    }

	/**
	 * Returns whether the square at the passed position is a simple
	 * deadlock square.
	 * <p>
	 * The search direction doesn't matter, because the simple deadlock squares
	 * of the other direction can never be reached from a specific direction.
	 * Therefore both the forward and the backward simple deadlock squares
	 * are checked.
	 * <p>
	 * The arguments are not checked against the board dimensions.
	 * 
	 * @param x the x-coordinate of the position
	 * @param y the y-coordinate of the position
	 * @return <code>true</code> if the square is a simple deadlock square,
	 * or<br> <code>false</code> if the square isn't a simple deadlock square
	 */

	final public boolean isSimpleDeadlockSquare(int x, int y) {
		return     simpleDeadlockSquareForwards[x + width * y]
				|| advancedSimpleDeadlockSquareForwards[x + width * y]
				|| simpleDeadlockSquareBackwards[x + width * y];
	}

	/**
	 * Sets a new collection for playing.
	 * 
	 * @param levelCollection
	 *            the level collection to be set
	 */

	public void setCurrentCollection(LevelCollection levelCollection) {

		currentLevelCollection = levelCollection;

		// Update the combo box showing all levels.
		updateLevelComboBox();
		
//		if (Settings.isDebugModeActivated) {
//			System.out.println( "Set collection with "
//					            + levelCollection.getNumberOfLevels() + " levels" );
//			System.out.println( "  ID = " + levelCollection.getDatabaseID());
//			System.out.println( "  title = " + levelCollection.getTitle());
//		}
	}

	/**
	 * Uses the standard container selection fileDialog to choose the new value
	 * for the container field.
	 */

	protected String handleDirectoryBrowse(String path) {
		DirectoryDialog directoryDialog = new DirectoryDialog(getShell());
		if (path != null && !"".equals(path)) {
			directoryDialog.setFilterPath(path);
		}
		String dirName = directoryDialog.open();

		return dirName;
	}

	/**
	 * Method only for debugging:
	 * Returns whether the square at the passed position is
	 * a simple deadlock forward square.
	 *  
	 * @param position the position of the square
	 * @return <code>true</code> if the square is a wall or a simple
	 *                           deadlock forward square,
	 * or<br> <code>false</code> if the square isn't a wall nor a simple
	 *                           deadlock forward square
	 */

	final public boolean isSimpleDeadlockSquareForwardsDebug(int position) {
		return simpleDeadlockSquareForwards[position];
	}

	/**
	 * Method only for debugging:
	 * Returns whether the square at the passed position is
	 * a simple deadlock forward square.
	 *  
	 * @param x the x-coordinate of the position
	 * @param y the y-coordinate of the position
	 * @return <code>true</code> if the square is a wall or a simple
	 *                           deadlock forward square,
	 * or<br> <code>false</code> if the square isn't a wall nor a simple
	 *                           deadlock forward square
	 */

	final public boolean isSimpleDeadlockSquareForwardsDebug(int x, int y) {
		return simpleDeadlockSquareForwards[x + width * y];
	}

	/**
	 * Method only for debugging:
	 * Returns whether the square at the passed position is
	 * a simple deadlock backward square.
	 *  
	 * @param position the position of the square
	 * @return <code>true</code> if the square is a wall or a simple
	 *                           deadlock backward square,
	 * or<br> <code>false</code> if the square isn't a wall nor a simple
	 *                           deadlock backward square
	 */

	final public boolean isSimpleDeadlockSquareBackwardsDebug(int position) {
		return simpleDeadlockSquareBackwards[position];
	}

	/**
	 * If the current level collection contains the level with the
	 * specified data base ID, we return the title of that current collection.
	 * Otherwise we return null.
	 * <p>
	 * NOTE: There may be other collections containing that levelID.
	 * 
	 * @param levelID ID of the level to search for
	 * @return the title of the collection containing the levelID
	 */

	public String collectionTitleOfLevelID(int levelID) {
		if (findLevelByID(levelID) != null) {
			return currentLevelCollection.getTitle();
		}
		return null;
	}

    /**
     * Save a transient object.
     * An id is generated, assigned to the given object and returned.
     */

    public Serializable save(Object obj) throws HibernateException {

        if (obj==null) throw new NullPointerException("attempted to save null");

        Object object = unproxy(obj); //throws exception if uninitialized

        EntityEntry e = getEntry(object);
        if ( e!=null ) {
            if ( e.status==DELETED ) {
                forceFlush(e);
            }
            else {
                log.trace( "object already associated with session" );
                return e.id;
            }
        }

        Serializable id = saveWithGeneratedIdentifier(object, Cascades.ACTION_SAVE_UPDATE, null); //id might be generated by SQL insert
        reassociateProxy(obj, id); //TODO: move into saveWithGeneratedIdentifier()?
        return id;

    }

	/**
	 * Method only for debugging:
	 * Returns whether the square at the passed position is a simple deadlock backward square.
	 *  
	 * @param x the x-coordinate of the position
	 * @param y the y-coordinate of the position
	 * @return <code>true</code> if the square is a wall or a simple
	 *                           deadlock backward square,
	 * or<br> <code>false</code> if the square isn't a wall nor a simple
	 *                           deadlock backward square
	 */

	final public boolean isSimpleDeadlockSquareBackwardsDebug(int x, int y) {
		return simpleDeadlockSquareBackwards[x + width * y];
	}

	/**
	 * Method only for debugging:
	 * Returns whether the square at the passed position is
	 * an advanced simple deadlock forward square.
	 *  
	 * @param position the position of the square
	 * @return <code>true</code> if the square is a wall or an advanced
	 *                           simple deadlock forward square,
	 * or<br> <code>false</code> if the square isn't a wall nor a advanced
	 *                           simple deadlock forward square
	 */

	final public boolean isAdvancedSimpleDeadlockSquareForwards(int position) {
		return advancedSimpleDeadlockSquareForwards[position];
	}

	/**
	 * Method only for debugging:
	 * Returns whether the square at the passed position is a advanced simple deadlock forward square.
	 *  
	 * @param x the x-coordinate of the position
	 * @param y the y-coordinate of the position
	 * @return <code>true</code> if the square is a wall or an advanced
	 *                           simple deadlock forward square,
	 * or<br> <code>false</code> if the square isn't a wall nor a advanced
	 *                           simple deadlock forward square
	 */

	final public boolean isAdvancedSimpleDeadlockSquareForwards(int x, int y) {
		return advancedSimpleDeadlockSquareForwards[x + width * y];
	}

	/**
	 * Returns whether there is a box and a goal at the passed position.
	 * 
	 * @param position the position of the square
	 * @return <code>true</code> if there is a box and a goal
	 *                           at the passed position,
	 * or<br> <code>false</code> if there isn't a box and a goal
	 *                           at the passed position
	 */

	final public boolean isBoxOnGoal(int position) {
		return boxesArray[position] && goalsArray[position];
	}

	/**
	 * Returns whether there is a box and a goal at the passed position.
	 * 
	 * @param x the x-coordinate of the position
	 * @param y the y-coordinate of the position
	 * @param position the position of the square
	 * @return <code>true</code> if there is a box and a goal
	 *                           at the passed position,
	 * or<br> <code>false</code> if there isn't a box and a goal
	 *                           at the passed position
	 */

	final public boolean isBoxOnGoal(int x, int y) {
		return boxesArray[x + width * y] && goalsArray[x + width * y];
	}

	/**
	 * Returns whether there is a player in the level.
	 * <p>
	 * Usually there is a player in every level. This method is just used for
	 * determining "special" squares for displaying them correctly. 
	 * 
	 * @return <code>true</code> if there is a player in the level,
	 * or<br> <code>false</code> if there isn't a player in the level
	 */

	final public boolean isPlayerInLevel() {
		return playerPosition != NO_PLAYER;
	}

	/**
	 * Method only for debugging:
	 * Returns whether the passed position is marked.
	 * <p>
	 * Marked positions are displayed with a little square.
	 * 
	 * @param position the position of the square
	 * @return <code>true</code> if the passed position is marked,
	 * or<br> <code>false</code> if the passed position is not marked
	 */

	final public boolean isMarked(int position) {
		return marked[position];
	}

	/**
	 * Method only for debugging:
	 * Returns whether the passed position is marked.
	 * <p>
	 * Marked positions are displayed with a little square graphic.
	 * 
	 * @param x the x-coordinate of the position
	 * @param y the y-coordinate of the position
	 * @return <code>true</code> if the passed position is marked,
	 * or<br> <code>false</code> if the passed position is not marked
	 */

	final public boolean isMarked(int x, int y) {
		return marked[x + width * y];
	}

	/**
	 * Removes a marking from the passed position.
	 * 
	 * @param position the position a marking is to be removed from.
	 */

	final public void removeMarking(int position) {
		marked[position] = false;
	}

	/**
	 * Removes a marking from the passed position.
	 * 
	 * @param x	the x-coordinate of the position the marking is to be removed from.
	 * @param y the y-coordinate of the position the marking is to be removed from.
	 */

	final public void removeMarking(int x, int y) {
		marked[x + width * y] = false;
	}

	/**
	 * Removes all marking from the board.
	 */

	final public void removeAllMarking() {
		Arrays.fill(marked, false);			// this is not time critical
	}

	/**
	 * Sets a marking at the passed position.
	 * 
	 * @param position the position the marking is to be set.
	 */

	final public void setMarking(int position) {
		marked[position] = true;
	}

    /**
     * associate a proxy that was instantiated by another session with this session
     */

    private void reassociateProxy(LazyInitializer li, HibernateProxy proxy) throws MappingException {
        if ( li.getSession()!=this ) {
            ClassPersister persister = getClassPersister( li.getPersistentClass() );
            Key key = new Key( li.getIdentifier(), persister );
            if ( !proxiesByKey.containsKey(key) ) proxiesByKey.put(key, proxy); // any earlier proxy takes precedence
            HibernateProxyHelper.getLazyInitializer( proxy ).setSession(this);
        }
    }

	/**
	 * Sets a marking at the passed position.
	 * 
	 * @param x the x-coordinate of the position a marking is to be set.
	 * @param y the y-coordinate of the position a marking is to be set.
	 */

	final public void setMarking(int x, int y) {
		marked[x + width * y] = true;
	}

	/**
	 * Marks the passed position with the passed value.
	 * 
	 * @param position  what position is to be marked
	 * @param markValue with what value the position id to be marked
	 */

	final public void assignMarking(int position, boolean markValue) {
		marked[position] = markValue;
	}

	/**
	 * Changes the making status of the passed position.
	 * 
	 * @param position the position is to be changed
	 */

	final public void flipMarking(int position) {
		marked[position] = ! marked[position];
	}

