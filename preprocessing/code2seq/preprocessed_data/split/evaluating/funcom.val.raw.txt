	/**
	 * Returns the number of the box located at the passed position.
	 * For positions without a box the return value is undefined.
	 * 
	 * @param position the position of the square	 
	 * @return	the number of the box
	 */

	final public int getBoxNo(int position) {
		return boxNumbers[position];
	}

	/**
	 * Returns the number of the goal located at the passed position.
	 * For positions without a goal the return value is undefined.
	 * 
	 * @param position the position of the square	 
	 * @return	the number of the goal
	 */

	final public int getGoalNo(int position) {
		return goalsNumbers[position];
	}

	/**
	 * Returns the position of the goal with the passed goal number.
	 * 
	 * @param goalNo the number of the goal
	 * @return the position of the goal
	 */

	final public int getGoalPosition(int goalNo) {
		return goalsPositions[goalNo];
	}

	/**
	 * Returns the distance which the player has to walk,
	 * starting from one square in order to reach a second square.
	 * 
	 * @param fromSquare player start position
	 * @param toSquare	 player walk destination
	 * @return number of steps the player needs to walk
	 */

	final public int getPlayerDistance(int fromSquare, int toSquare) {
		return playerDistances[fromSquare][toSquare];
	}

	/**
	 * Sets a new board position.
	 * 
	 * @param position Board position to be set.
	 */

	final public void setBoardPosition(IBoardPosition position) {
		setBoardPosition(position.getPositions());
	}

	/**
	 * Sets a new board position.
	 * 
	 * @param positions box and player positions to be set.
	 */

	final public void setBoardPosition(int[] positions) {

		// remove all boxes from the board
		removeAllBoxes();

		// store the new box positions in our box data object
		boxData.setBoxPositions(positions);

		// Put the new boxes into the board
		for (int boxNo = 0; boxNo < boxCount; boxNo++) {
			setBoxWithNo(boxNo, positions[boxNo]);
		}

		// set up the new player location
		playerPosition = positions[boxCount];
	}

	/**
	 * Removes all boxes from the board.
	 */

	final public void removeAllBoxes() {
		for (int boxNo = 0; boxNo < boxCount; boxNo++) {
			removeBox(boxData.getBoxPosition(boxNo));
		}
	}

	/**
	 * The goals of the backward search are the box positions at search start.
	 * Here we set the goals for the backward search from the current box
	 * positions.
	 */

	final public void setGoalsBackwardsSearch() {

		int goalNo = 0;

		// Set up the new goals for backwards search, and clear the old ones
		for (int position = firstRelevantSquare; position < lastRelevantSquare; position++) {
			if (isBox(position) && isOuterSquareOrWall(position) == false) {
				goalSquareBackwardsSearch[position] = true;
				goalsPositionsBackwardsSearch[goalNo++] = position;
			} else {
				goalSquareBackwardsSearch[position] = false;
			}
		}
	}

    /**
     * Retrieve a list of persistent objects using a hibernate query
     */

    public List find(String query) throws HibernateException {
        return find(query, NO_ARGS, NO_TYPES);
    }

		/**
		 * Returns the push distance of a specific box to a specific goal.
		 * <p>
		 * The distance is calculated under the assumption that:
		 * <ol>
		 *  <li> the box is the only one on the whole board
		 *  <li> the player can reach every side of the box at the moment
		 * </ol>
		 *  
		 * @param boxNo number of the relevant box
		 * @param goalNo number of the relevant goal
		 * @return push distance
		 */

		public int getBoxDistanceForwardsPlayerPositionIndependentNo(int boxNo, int goalNo) {
			return getBoxDistanceForwardsPlayerPositionIndependent(boxData.getBoxPosition(boxNo), goalsPositions[goalNo]);
		}

	/**
	 * The action has been activated. The argument of the
	 * method represents the 'real' action sitting
	 * in the workbench UI.
	 * @see IWorkbenchWindowActionDelegate#run
	 */

	public void run(IAction action) {
		UpdateManagerDialog d = new UpdateManagerDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
		d.open();
	}

	/**
	 * Selection in the workbench has been changed. We 
	 * can change the state of the 'real' action here
	 * if we want, but this can only happen after 
	 * the delegate has been created.
	 * @see IWorkbenchWindowActionDelegate#selectionChanged
	 */

	public void selectionChanged(IAction action, ISelection selection) {
	}

	/**
	 * We can use this method to dispose of any system
	 * resources we previously allocated.
	 * @see IWorkbenchWindowActionDelegate#dispose
	 */

	public void dispose() {
	}

	/**
	 * We will cache window object in order to
	 * be able to provide parent shell for the message dialog.
	 * @see IWorkbenchWindowActionDelegate#init
	 */

	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

    /**
     * The <code>addToHierarchyToCheckedStore</code> implementation of this 
     * <code>WizardDataTransferPage</code> method returns <code>false</code>. 
     * Subclasses may override this method.
     */

    protected boolean allowNewContainerName() {
        return false;
    }

    /**
     * This method must be called just before this window becomes visible.
     */

    public void aboutToOpen() {
        determineWhiteCheckedDescendents(root);
        checkNewTreeElements(treeContentProvider.getElements(root));

        //select the first element in the list
        Object[] elements = treeContentProvider.getElements(root);
        Object primary = elements.length > 0 ? elements[0] : null;
        if (primary != null) {
            treeViewer.setSelection(new StructuredSelection(primary));
        }
        treeViewer.getControl().setFocus();
    }

    /**
     *	Add the passed listener to self's collection of clients
     *	that listen for changes to element checked states
     *
     *	@param listener ICheckStateListener
     */

    public void addCheckStateListener(ICheckStateListener listener) {
        addListenerObject(listener);
    }

    /**
     * Returns a content provider for <code>IResource</code>s that returns 
     * only children of the given resource type.
     */

    private ITreeContentProvider getResourceProvider(final int resourceType) {
        return new WorkbenchContentProvider() {
            @SuppressWarnings("unchecked")
			public Object[] getChildren(Object o) {
                //input element case
                if (o instanceof ArrayList) {
                    return ((ArrayList) o).toArray();
                } 
               return new Object[0];            	
            }
        };
    }

    /**
     *	Return a boolean indicating whether all children of the passed tree element
     *	are currently white-checked
     *
     *	@return boolean
     *	@param treeElement java.lang.Object
     */

    protected boolean areAllChildrenWhiteChecked(Object treeElement) {
        Object[] children = treeContentProvider.getChildren(treeElement);
        for (int i = 0; i < children.length; ++i) {
            if (!whiteCheckedTreeItems.contains(children[i])) {
				return false;
			}
        }

        return true;
    }

    /**
     *	Return a boolean indicating whether all list elements associated with
     *	the passed tree element are currently checked
     *
     *	@return boolean
     *	@param treeElement java.lang.Object
     */

    protected boolean areAllElementsChecked(Object treeElement) {
        List checkedElements = (List) checkedStateStore.get(treeElement);
        if (checkedElements == null) {
			return false;
		}
        return true;
    }

    /**
     *	Iterate through the passed elements which are being realized for the first
     *	time and check each one in the tree viewer as appropriate
     */

    protected void checkNewTreeElements(Object[] elements) {
        for (int i = 0; i < elements.length; ++i) {
            Object currentElement = elements[i];
            boolean checked = checkedStateStore.containsKey(currentElement);
            treeViewer.setChecked(currentElement, checked);
            treeViewer.setGrayed(currentElement, checked
                    && !whiteCheckedTreeItems.contains(currentElement));
        }
    }

    /**
     * Returns this page's collection of currently-specified resources to be 
     * exported. This is the primary resource selection facility accessor for 
     * subclasses.
     *
     * @return an iterator over the collection of resources currently selected 
     * for export (element type: <code>IResource</code>). This will include
     * white checked folders and individually checked files.
     */

    protected Iterator getSelectedResourcesIterator() {
        return this.resourceGroup.getAllCheckedListItems().iterator();
    }

    /**
     *	An item was checked in one of self's two views.  Determine which
     *	view this occurred in and delegate appropriately
     *
     *	@param event CheckStateChangedEvent
     */

    public void checkStateChanged(final CheckStateChangedEvent event) {

        //Potentially long operation - show a busy cursor
        BusyIndicator.showWhile(treeViewer.getControl().getDisplay(),
            new Runnable() {
                public void run() {
                    if (event.getCheckable().equals(treeViewer)) {
						treeItemChecked(event.getElement(), event
                                .getChecked());
					}

                    notifyCheckStateChangeListeners(event);
                }
            });
    }

    /**
     * Returns the resource extensions currently specified to be exported.
     *
     * @return the resource extensions currently specified to be exported (element 
     *   type: <code>String</code>)
     */

    protected List getTypesToExport() {
        return selectedTypes;
    }

    /**
     * Returns this page's collection of currently-specified resources to be 
     * exported. This returns both folders and files - for just the files use
     * getSelectedResources.
     *
     * @return a collection of resources currently selected 
     * for export (element type: <code>IResource</code>)
     */

    protected List getWhiteCheckedResources() {
        return this.resourceGroup.getAllWhiteCheckedItems();
    }

    /**
     *	Create this group's tree viewer.
     */

    protected void createTreeViewer(Composite parent, boolean useHeightHint) {
        Tree tree = new Tree(parent, SWT.CHECK | SWT.BORDER);
        GridData data = new GridData(GridData.FILL_BOTH);
        if (useHeightHint) {
			data.heightHint = PREFERRED_HEIGHT;
		}
        tree.setLayoutData(data);
        tree.setFont(parent.getFont());

        treeViewer = new CheckboxTreeViewer(tree);
        treeViewer.setContentProvider(treeContentProvider);
        treeViewer.setLabelProvider(treeLabelProvider);
        treeViewer.addTreeListener(this);
        treeViewer.addCheckStateListener(this);
        treeViewer.addSelectionChangedListener(this);
    }

    /**
     * Returns whether the extension of the given resource name is an extension that
     * has been specified for export by the user.
     *
     * @param resourceName the resource name
     * @return <code>true</code> if the resource name is suitable for export based 
     *   upon its extension
     */

    protected boolean hasExportableExtension(String resourceName) {
        if (selectedTypes == null) {
			return true;
		}

        int separatorIndex = resourceName.lastIndexOf("."); //$NON-NLS-1$
        if (separatorIndex == -1) {
			return false;
		}

        String extension = resourceName.substring(separatorIndex + 1);

        Iterator it = selectedTypes.iterator();
        while (it.hasNext()) {
            if (extension.equalsIgnoreCase((String) it.next())) {
				return true;
			}
        }

        return false;
    }

    /**
     * Returns a boolean indicating whether the passed tree item should be
     * white-checked.
     *
     * @return boolean
     * @param treeElement java.lang.Object
     */

    protected boolean determineShouldBeWhiteChecked(Object treeElement) {
        return areAllChildrenWhiteChecked(treeElement)
                && areAllElementsChecked(treeElement);
    }

    /**
     *	Recursively add appropriate tree elements to the collection of
     *	known white-checked tree elements.
     *
     *	@param treeElement java.lang.Object
     */

    protected void determineWhiteCheckedDescendents(Object treeElement) {
        // always go through all children first since their white-checked
        // statuses will be needed to determine the white-checked status for
        // this tree element
        Object[] children = treeContentProvider.getElements(treeElement);
        for (int i = 0; i < children.length; ++i) {
			determineWhiteCheckedDescendents(children[i]);
		}

        // now determine the white-checked status for this tree element
        if (determineShouldBeWhiteChecked(treeElement)) {
			setWhiteChecked(treeElement, true);
		}
    }

    /**
     * Persists resource specification control setting that are to be restored
     * in the next instance of this page. Subclasses wishing to persist additional
     * setting for their controls should extend hook method 
     * <code>internalSaveWidgetValues</code>.
     */

    protected void saveWidgetValues() {
        // allow subclasses to save values
        internalSaveWidgetValues();
    }

    /**
     * Cause the tree viewer to expand all its items
     */

    public void expandAll() {
        treeViewer.expandAll();
    }

    /**
     * Set the initial selections in the resource group.
     */

    protected void setupBasedOnInitialSelections() {

        Iterator it = this.initialResourceSelection.iterator();
        while (it.hasNext()) {
            IResource currentResource = (IResource) it.next();
            if (currentResource.getType() != IResource.FILE) {
				this.resourceGroup.initialCheckTreeItem(currentResource);
			}
        }
    }

		/**
		 * Returns whether the player can reach the passed position.
		 * 
		 * @param position the position to be tested for reachability
		 * 
		 * @return <code>true</code> the position is reachable by the player
		 * 			<code>false</code> the position isn't reachable by the player
		 */

		final public boolean isSquareReachable(int position) {
			return playersReachableSquaresArray[position] == indicatorReachableSquare;
		}

    /**
     * Save any editors that the user wants to save before export.
     * @return boolean if the save was successful.
     */

    protected boolean saveDirtyEditors() {
        return IDEWorkbenchPlugin.getDefault().getWorkbench().saveAllEditors(
                true);
    }

    /**
     *	Returns a flat list of all of the leaf elements which are checked.
     *
     *	@return all of the leaf elements which are checked. This API does not
     * 	return null in order to keep backwards compatibility.
     */

    public List getAllCheckedListItems() {

        final ArrayList returnValue = new ArrayList();

        IElementFilter passThroughFilter = new IElementFilter() {

            @SuppressWarnings("unchecked")
			public void filterElements(Collection elements,
                    IProgressMonitor monitor) {
                returnValue.addAll(elements);
            }

            @SuppressWarnings("unchecked")
			public void filterElements(Object[] elements,
                    IProgressMonitor monitor) {
                for (int i = 0; i < elements.length; i++) {
                    returnValue.add(elements[i]);
                }
            }
        };

        try {
            getAllCheckedListItems(passThroughFilter, null);
        } catch (InterruptedException exception) {
            return new ArrayList();
        }
        return returnValue;

    }

    /**
     * Check if widgets are enabled or disabled by a change in the dialog.
     */

    protected void updateWidgetEnablements() {

        boolean pageComplete = determinePageCompletion();
        setPageComplete(pageComplete);
        if (pageComplete) {
			setMessage(null);
		}
        super.updateWidgetEnablements();
    }

		/**
		 * Returns a clone of the current object.
		 * 
		 * @return a PlayersReachableSquares object identical to this object
		 */

		final public PlayersReachableSquares getClone() {
			return new PlayersReachableSquares(playersReachableSquaresArray, indicatorReachableSquare);
		}

    /**
     * give the interceptor an opportunity to override the default instantiation
     */

    public Object instantiate(ClassPersister persister, Serializable id) throws HibernateException {
        Object result = interceptor.instantiate( persister.getMappedClass(), id );
        if (result==null) result = persister.instantiate(id);
        return result;
    }

    /**
     *	Add the passed value to self's destination widget's history
     *
     *	@param value java.lang.String
     */

    protected void addDestinationItem(String value) {
        destinationNameField.add(value);
    }

    /**
     *	Returns a list of all of the items that are white checked.
     * 	Any folders that are white checked are added and then any files
     *  from white checked folders are added. 
     *
     *	@return the list of all of the items that are white checked
     */

    public List getAllWhiteCheckedItems() {

        List result = new ArrayList();

        //Iterate through the children of the root as the root is not in the store
        Object[] children = treeContentProvider.getChildren(root);
        for (int i = 0; i < children.length; ++i) {
            findAllWhiteCheckedItems(children[i], result);
        }

        return result;
    }

    /**
     *	Answer the number of elements that have been checked by the
     *	user.
     *
     *	@return int
     */

    public int getCheckedElementCount() {
        return checkedStateStore.size();
    }

    /**
     * Create the buttons for the group that determine if the entire or
     * selected directory structure should be created.
     * @param optionsGroup
     * @param font
     */

    protected void createDirectoryStructureOptions(Composite optionsGroup, Font font) {
        // create directory structure radios
        createDirectoryStructureButton = new Button(optionsGroup, SWT.RADIO
                | SWT.LEFT);
        createDirectoryStructureButton.setText(DataTransferMessages.FileExport_createDirectoryStructure);
        createDirectoryStructureButton.setSelection(false);
        createDirectoryStructureButton.setFont(font);

        // create directory structure radios
        createSelectionOnlyButton = new Button(optionsGroup, SWT.RADIO
                | SWT.LEFT);
        createSelectionOnlyButton.setText(DataTransferMessages.FileExport_createSelectedDirectories);
        createSelectionOnlyButton.setSelection(true);
        createSelectionOnlyButton.setFont(font);
    }

		/**
		 * Returns the position reachable of the player that is
		 * the most top left one.
		 * This is a normalization of the player position, used, where the
		 * exact player position is not relevant, but its reachable area is.
		 * 
		 * @return the position top left
		 */

		final public int getPlayerPositionTopLeft() {

			// Calculate squares reachable by the player.
			update();

			// This is just the square with the smallest index.
			for (int position = firstRelevantSquare; position < lastRelevantSquare; position++) {
				if (isSquareReachable(position)) {
					return position;
				}
			}

			// The player can't reach any square.
			return -1;
		}

    /**
     *	Set the checked state of self and all ancestors appropriately. Do not white check anyone - this is
     *  only done down a hierarchy.
     */

    private void grayUpdateHierarchy(Object treeElement) {

        boolean shouldBeAtLeastGray = determineShouldBeAtLeastGrayChecked(treeElement);

        treeViewer.setGrayChecked(treeElement, shouldBeAtLeastGray);

        if (whiteCheckedTreeItems.contains(treeElement)) {
			whiteCheckedTreeItems.remove(treeElement);
		}

        // proceed up the tree element hierarchy
        Object parent = treeContentProvider.getParent(treeElement);
        if (parent != null) {
            grayUpdateHierarchy(parent);
        }
    }

    /**
     * Set the initial checked state of the passed element to true,
     * as well as to all of its children and associated list elements
     * @param element
     */

    public void initialCheckTreeItem(Object element) {
        treeItemChecked(element, true);
        selectAndReveal(element);
    }

    /**
     * Attempts to ensure that the specified directory exists on the local file system.
     * Answers a boolean indicating success.
     *
     * @return boolean
     * @param directory java.io.File
     */

    protected boolean ensureDirectoryExists(File directory) {
        if (!directory.exists()) {
            if (!queryYesNoQuestion(DataTransferMessages.DataTransfer_createTargetDirectory)) {
				return false;
			}

            if (!directory.mkdirs()) {
                displayErrorDialog(DataTransferMessages.DataTransfer_directoryCreationError);
                giveFocusToDestination();
                return false;
            }
        }

        return true;
    }

    /**
     *	If the target for export does not exist then attempt to create it.
     *	Answer a boolean indicating whether the target exists (ie.- if it
     *	either pre-existed or this method was able to create it)
     *
     *	@return boolean
     */

    protected boolean ensureTargetIsValid(File targetDirectory) {
        if (targetDirectory.exists() && !targetDirectory.isDirectory()) {
            displayErrorDialog(DataTransferMessages.FileExport_directoryExists);
            giveFocusToDestination();
            return false;
        }

        return ensureDirectoryExists(targetDirectory);
    }

    /**
     *  Set up and execute the passed Operation.  Answer a boolean indicating success.
     *
     *  @return boolean
     */

    protected boolean executeExportOperation(FileSystemExportOperation op) {
        op.setCreateLeadupStructure(createDirectoryStructureButton
                .getSelection());
        op.setOverwriteFiles(overwriteExistingFilesCheckbox.getSelection());

        try {
            getContainer().run(true, true, op);
        } catch (InterruptedException e) {
            return false;
        } catch (InvocationTargetException e) {
            displayErrorDialog(e.getTargetException());
            return false;
        }

        IStatus status = op.getStatus();
        if (!status.isOK()) {
            ErrorDialog.openError(getContainer().getShell(),
                    DataTransferMessages.DataTransfer_exportProblems,
                    null, // no special message
                    status);
            return false;
        }

        return true;
    }

		/**
		 * Returns a (deep) clone of this object.
		 * 
		 * @return a (deep) clone of this object
		 */

		final public PlayersReachableSquaresOnlyWalls getClone() {
			return new PlayersReachableSquaresOnlyWalls(playersReachableSquaresOnlyWallsArray, reachableSquareIndicatorOnlyWalls);
		}

    /**
     *	Answer the string to display in self as the destination type
     *
     *	@return java.lang.String
     */

    protected String getDestinationLabel() {
        return DataTransferMessages.FileExport_toDirectory;
    }

