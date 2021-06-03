	/**
	 * Returns the pushes lowerbound of this board position.
	 * 
	 * @return the pushes lowerbound
	 */

	public int getPushesLowerbound() {
		return pushesLowerbound;
	}

	/**
	 * Sets the pushes lowerbound of this board position.
	 * 
	 * @param pushesLowerbound
	 */

	public void setPushesLowerbound(int pushesLowerbound) {
		this.pushesLowerbound = pushesLowerbound;
	}

		/**
		 * Play a sound.
		 */

		public void play() {
			
			// If no sound file is there nothing can be played.
			if(currentSoundFilename.equals(""))
				return;
			
			// Check if the this sound must be read from another file (the user has
			// chosen another sound to be played).
			String settingsFileName = Settings.get(soundSettingsKey);
			if(!currentSoundFilename.equals(settingsFileName)) {
				loadClip(settingsFileName);			
			}
			
			// Stop the currently playing sound to play the new sound if necessary.
			if (clip.isRunning()) {
				clip.stop();
			}
			
			// Rewind to the beginning.
			clip.setFramePosition(0); 
			
			// Start playing.
			clip.start();    
		}

	/**
	 * Returns the influence value between the positions of the passed boxes.
	 * <p>
	 * The LOWER the influence value the more influence has the box on the other box.
	 * 
	 * @param boxNo1  the position of the first  box whose position is considered
	 * @param boxNo2  the position of the second box whose position is considered
	 * @return the influence value 
	 */

	public int getInfluenceValue(int boxNo1, int boxNo2) {
		return influence.getInfluenceDistance( board.boxData.getBoxPosition(boxNo1),
				                               board.boxData.getBoxPosition(boxNo2) );
	}

	/**
	 * Sets the box positions and the player position.
	 *     
	 * @param positions positions of the boxes and the player
	 */

	public void setPositions(int[] positions){
		this.positions = positions;
	}

	/**
	 * Returns the player position of this board position.
	 * 
	 * @return the player position
	 */

	public int getPlayerPosition() {
		return positions[positions.length-1];
	}

	/**
	 * Returns the number of the moved box.
	 * 
	 * @return the number of the moved box
	 */

	public int getPulledBoxNumber() {
		return pulledBoxNo;
	}

	/**
	 * Returns the start position of the pulled box.
	 * 
	 * @return the position of the box before it was pulled.
	 */

	public int getStartBoxPosition() {
		return startPosition;
	}

	/**
	 * Returns the target position of the pulled box.
	 * 
	 * @return the position of the box after it was pulled.
	 */

	public int getTargetBoxPosition() {
		return targetPosition;
	}

    /**
     * Returns the preceding board position of this board position.
     *
     * @return the preceding board position of this board position
     */

    public BoardPositionPackingSequence getPrecedingBoardPosition() {
    	return precedingBoardPosition;
    }

	/**
	 * Compares both board positions. Note: lower values means high priority
	 * in the <code>PriorityQueue</code>.
	 */

	public int compareTo(BoardPositionPackingSequence boardPosition) {
		return boardPosition.getRelevance() - getRelevance();
	}

	/**
	 * Returns whether the push is a forced push, that means a box 
	 * must be pushed from the start position to the target position.
	 */

	public boolean isForcedPush() {
		return isPushForced;
	}

	/**
	 * Sets the status bar text.
	 * 
	 * @param text
	 *            the text to be shown in the status bar
	 */

	final public void setInfoText(String text) {
		infoText.setText(text);
	}

	/**
	 * Returns whether the solver has been stopped due to insufficient memory.
	 * 
	 * @return <code>true</code> if the solver stopped due to insufficient memory, and
	 *        <code>false</code> otherwise
	 */

	public boolean isSolverStoppedDueToOutOfMemory() {
		return isSolverStoppedDueToOutOfMemory;
	}

	/**
	 * Returns the board position with the shortest determined solution path length.
	 *
     * @return board position with the shortest determined solution path length.
     */

    final protected IBoardPositionMoves getBestBoardPosition(){

		// Nimmt die Liste aller Stellungen mit einer bestimmten Lösungspfadlänge auf
		LinkedList<IBoardPositionMoves> boardPositionList;
		
		for(int solutionLength = shortestSolutionPathLength; solutionLength <= longestSolutionPathLength; solutionLength++) {
		
			// Liste der Stellungen mit der aktuellen Pfadlänge holen. 
			boardPositionList = boardPositionQueue.get(solutionLength);
			
			if(boardPositionList.size() > 0){	 
			    shortestSolutionPathLength = solutionLength;
			    
				// Die zuletzt eingefügte Stellung zurückgeben
	    		return boardPositionList.removeLast();
			}
		}
		
		return null;
	}

	/**
	 * Returns the preceding board position of this board position.
	 * 
	 * @return preceding board position
	 */

	final public IBoardPosition getPrecedingBoardPosition() {
		return precedingBoardPosition;
	}

	/**
	 * Returns the direction the box has been pushed.
	 * 
	 * @return Direction of the push
	 */

	final public int getDirection() {
		return positionData >>> 13;
	}

	/**
	 * Returns the number of the pushed box.
	 * 
	 * @return Number of the pushed box
	 */

	public int getBoxNo() {
		return positionData & ((1 << 10) - 1);
	}

	/**
	 * Gives the name of this solver as used to tag new solutions.
	 * @return the name of this solver
	 */

	protected String creatorName() {
		return Texts.getText("solver");
	}

	/**
	 * Return the player position of this board position.
	 * 
	 * @return the player position
	 */

	final public int getPlayerPosition() {
		return playerPosition;
	}

	/**
	 * Sets the box- and the player positions.
	 *     
	 * @param positions Positions of the boxes and the player
	 */

	final public void setPositions(int[] positions) {
		this.positions = positions;
	}

	/**
	 * An absolute board position usually hasn't a preceding board position. This method is
	 * implemented for easier working with linked lists.
	 *
	 * @return always null
	 */

	final public IBoardPosition getPrecedingBoardPosition() {
		return null;
	}

	/**
	 * Sets the searchdirection of the solutionsearch this class is created in.
	 * 
	 * @param searchDirection Direction of the search
	 */

	final public void setSearchDirection(SearchDirection searchDirection) {

		if (searchDirection == SearchDirection.FORWARD) {
			forwardsSearch  = true;
		} else {
			backwardsSearch = true;
		}
	}

	/**
	 * For avoiding some casts this method is implemented here. Actually, this method
	 * is only needed for relative board positions.
	 *
	 * @return always 0
	 */

	final public int getDirection() {
		return 0;
	}

	/** 
	 * Returns the number of pushes of this board position.
	 * 
	 * @return number of pushes
	 */

	public int getPushesCount() {

		int pushesCount = 0;

		for (BoardPosition currentBoardPosition = this; currentBoardPosition
				.getPrecedingBoardPosition() != null; currentBoardPosition = (BoardPosition) currentBoardPosition
				.getPrecedingBoardPosition()) {
			if (currentBoardPosition.getBoxNo() != NO_BOX_PUSHED) {
				pushesCount++;
			}
		}

		return pushesCount;
	}

	/**
	 * Absolute board positions are only created when no box has been pushed. This value is interpreted 
	 * during a search for a solution (no box has been pushed so no tunnel check has to be performed).
	 *
	 * @return always <code>NO_BOX_PUSHED</code>
	 */

	final public int getBoxNo() {
		return NO_BOX_PUSHED;
	}

	/**
	 * Sets the maximum solution length. 
	 * This is a value representing the iteration depth during the search for a solution.
	 * (first all board positions are created that have a maximum solution length of x pushes.
	 * Then all board positions are created with a maximum solution length of x+1, ...
	 *  
	 * @param maximumSolutionLength	the maximum solution length to be set
	 */

	public void setMaximumSolutionLength(short maximumSolutionLength) {
		maximumPushesCurrentIteration = maximumSolutionLength;
	}

	/**
	 * Return the player position of this board position.
	 * 
	 * @return the player position
	 */

	final public int getPlayerPosition() {
		return positions[boxCount];
	}

	/**
	 * Returns the maximum solution length (= iteration depth).
	 * 
	 * @return	the maximum solution length stored in this board position
	 */

	public short getMaximumSolutionLength() {
		return maximumPushesCurrentIteration;
	}

	/**
	 * Returns the number of pushes that were made to reach this board position.
	 * An absolute board position is only created at the beginning, so this is
	 * always 0.
	 * This number is used in the solving methods.
	 * 
	 * @return always 0
	 */

	final public int getPushesCount() {
		return 0;
	}

	/**
	 * Returns a string to be attached to a solution, which says that this
	 * solver did create the solution at the passed point in time.
	 * 
	 * @param date the creation time point, or <code>null</code> for "now"
	 * @return string identifying the solver as solution creator
	 * @see Solution#name
	 */

	protected String solutionByMeAt(Date date) {
		return  Texts.getText("createdBy")
		      + " " + creatorName()
		      + " " + Utilities.dateString(date);
	}

	/**
	 * Sets the number of moves.
	 * 
	 * @param movesCount	Number of moves the player has done
	 */

	final public void setMovesCount(int movesCount) {
		this.movesCount = (short) movesCount;
	}

	/**
	 * Returns the index in the packing sequence that has already been reached.
	 * 
	 * @return index in the packing sequence
	 */

	public int getIndexPackingSequence() {
		return indexPackingSequence;
	}

	/**
	 * Sets the index in the packing sequence that has been reached.
	 * 
	 * @param indexPackingSequence the index in the packing sequence
	 */

	public void setIndexPackingSequence(int indexPackingSequence) {
		this.indexPackingSequence = indexPackingSequence;
	}

	/**
	 * Returns, whether a box is inactive, and therefore a position value 0
	 * has to be assumed.
	 *
	 * @param  boxNo number of the box to be checked for being inactive
	 * @return <code>true</code> if the box is inactive, and
	 * 		  <code>false</code> if the box is active
	 */

	protected boolean isBoxInactive(int boxNo) {
		return isBoxInactive[boxNo];
	}

	/**
	 *  Returns whether the pushed box has been inactive.
	 *
	 * @return <code>true</code> the box is inactive
	 * 			<code>false</code> the box is active
	 */

	final public boolean isBoxInactive() {
		return (positionData & INACTIVE_BOX) > 0;
	}

	/**
	 * Mark this board position to be a deadlock.
	 */

	public void setCorralDeadlock() {
		isDeadlock = true;
	}

	/**
	 * Mark this board position not to be a deadlock.
	 * <p>
	 * Attention: this board position is only for the current
	 * investigated corral assumed not to be a deadlock.
	 * Nevertheless it can be a deadlock, because not all
	 * deadlocks are detected.
	 */

	public void setNotCorralDeadlock() {
		isNotDeadlock = true;
	}

		/**
		 * Retrieves the current value of the debug variable
		 * described by this object, by using reflection.
		 * 
		 * @return current value of the described debug variable
		 */

		public boolean getValue() {
			/*
			 * Since we here are inside of class Settings, we should not get
			 * an IllegalAccessException.
			 * Hence, we catch the exceptions here, and return false,
			 * just in case.
			 */
			try {
				return field.getBoolean(null);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			return false;
		}

	/**
	 * Returns a string to be attached to a solution, which says that this
	 * solver did create the solution "now".
	 * 
	 * @return string identifying the solver as solution creator
	 * @see Solution#name
	 */

	protected String solutionByMeNow() {
		return solutionByMeAt(null);
	}

	/**
	 * Sets the number of the corral this board position belongs to.
	 * 
	 * @param corralNo the number of the corral
	 */

	public void setCorralNo(int corralNo) {
		this.corralNo = corralNo;
	}

	/**
	 * Sets the status bar text.
	 * 
	 * @param text
	 *            the text to be shown in the status bar
	 */

	public void setInfoText(String text) {
		infoText.setText(text);
	}

	/**
	 * Returns the number of the corral this board position belongs to.
	 * 
	 * @return the number of the corral
	 */

	public int getCorralNo() {
		return corralNo;
	}

	/**
	 * Returns whether this board position has been proven to be a corral deadlock.
	 * 
	 * @return <code>true</code> if this board position is a corral deadlock, and
	 * 	     <code>false</code> if this board position has not been proven to be a deadlock
	 */

	public boolean isCorralDeadlock() {
		return isDeadlock;
	}

	/**
	 * Returns whether this board position is classified not to be a corral deadlock.
	 * <p>
	 * This board position can be a corral deadlock, anyhow. For example the corral
	 * detection could be aborted due to a reached time limit. Nevertheless it would
	 * be classified as not to be a corral deadlock.
	 * 
	 * @return <code>true</code> this board position has been classified not to be a corral deadlock
	 * 		  <code>false</code> otherwise
	 */

	public boolean isNotCorralDeadlock() {
		return isNotDeadlock;
	}

	/**
	 * Returns whether this board position has been classified (deadlock or no deadlock).
	 * <p>
	 * This method returns the same value as <code>!isBeeingAnalyzed()</code>
	 * 
	 * @return <code>true</code> this board position has been classified.
	 * 		<code>false</code> this board position has not been classified yet.
	 */

	public boolean isClassified() {
		return isDeadlock || isNotDeadlock;
	}

	/**
	 * Returns whether is currently beeing analyzed to be a corral deadlock or not.
	 * <p>
	 * Every corral (= board position) not classified counts as "beeingAnalyzed". Due to every
	 * board position occured during the corral detection getting a corral number - even if it
	 * isn't a corral at all - finally there can be board positions still having the status
	 * "isBeeingAnalyzed".
	 * 
	 * @return <code>true</code> if the deadlock status of this board position
	 *                           is currently beeing analyzed, and
	 * 		  <code>false</code> otherwise
	 */

	public boolean isBeeingAnalyzed() {
		return !isDeadlock && !isNotDeadlock;
	}

	/**
	 * Returns a deep clone of the current board.
	 * 
	 * @return the clone of this board
	 */

	public CBoard getClone() {
		return new CBoard(boardElements.clone(), width, height, playerPositionX, playerPositionY);
	}

		/**
		 * Sets the value of the debug variable described by this object
		 * by using reflection.
		 * 
		 * @param value  to be assigned to the described debug variable
		 */

		public void setValue(boolean value) {
			/*
			 * Regarding exceptions see comment in "getValue"
			 */
			try {
				field.setBoolean(null, value);
				System.out.println("Setting: DebugVar: "+field.getName()+" set to "+value);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			// well... forget it.
		}

	/**
	 * Returns whether the two box configurations are equal.
	 * 
	 * @param boxConfigurationNumber logical index into our flat data array
	 * @param boxConfiguration the candidate box configuration to compare with
	 * @return <code>true</code> if both box configurations are equal, and<br>
	 *        <code>false</code> otherwise
	 */

	private boolean isEqual(int boxConfigurationNumber, byte[] boxConfiguration) {
		
		int indexOfBoxConfiguration = boxConfigurationNumber * packedBoardByteSize;
		
		// Compare byte by byte of both box configurations.
		for(int byteNo=0; byteNo < boxConfiguration.length; byteNo++) {
			if (boxConfigurations[indexOfBoxConfiguration++] != boxConfiguration[byteNo]) {
				return false;
			}
		}
		
		return true;
	}

	/**
	 * Returns the maximal capacity of this storage.
	 * 
	 * @return the maximal capacity of this storage
	 */

	public int getMaxCapacity() {
		return maxCapacity;
	}

	/**
	 * Adds data to the queue.
	 * 
	 * @param boardPositionIndex the board position to be added
	 * @param boardPositionPredecessor the predecessor to be added
	 */

	public void add(int boardPositionIndex, int boardPositionPredecessor) {
		
		// The moves queue also contains an arbitrary number of span markers.
		// Therefore after every added board position there must be done
		// a check whether the memory block is already full.
		add(boardPositionIndex      );
		add(boardPositionPredecessor);
	}

	/**
	 * Logically removes all board positions from the queue
	 * until a board position different to the passed one occurs in the queue.
	 * 
	 * @param boardPosition the board position to be over jumped
	 */

	public void jumpOverBoardPosition(int boardPosition) {
	
		// "Remove" all values from the queue which are equal to the passed one
		// FFS/hm: does the caller guarantee that the Q cannot become empty by this?
		while(memoryBlockToRead.memory[nextIndexToRead] == boardPosition) {
			removeBoardPosition();
		}
	}

	/**
	 * Retrieves and removes the last board position of this queue.
	 * 
	 * @return the previous board position
	 */

	public int removeLastBoardPosition() {
						
		if(nextIndexToWrite == 0) {
			
			// The previous memory block becomes the new current memory block.
			memoryBlockToWrite = memoryBlockToWrite.previousMemoryBlock;
			nextIndexToWrite = MEMORY_BLOCK_SIZE;
		}
		
		return memoryBlockToWrite.memory[--nextIndexToWrite];
	}

	/**
	 * Jumps backwards in the queue by the passed number of board positions.
	 * 
	 * @param jumpCount number of board positions to be skipped backwards
	 */

	public void jumpXBoardPositionsBackwards(int jumpCount) {
		
		while(jumpCount > nextIndexToWrite) {
							
			// Assign the previous memory block of the queue.
			memoryBlockToWrite = memoryBlockToWrite.previousMemoryBlock;
			
			jumpCount -= nextIndexToWrite;
			nextIndexToWrite = MEMORY_BLOCK_SIZE;					
		}
		nextIndexToWrite -= jumpCount;
	}

	/**
	 * Returns whether the queue is empty.
	 * 
	 * @return <code>true</code> when queue is empty, and<br>
	 *        <code>false</code> otherwise
	 */

	public boolean isEmpty() {
		// This method is only called for the moves queue which has recycling activated.
		return memoryBlockToRead == memoryBlockToWrite
		    && nextIndexToRead   == nextIndexToWrite;
	}

	/**
	 * Returns whether all box configurations could be generated. 
	 * 
	 * @return <code>true</code> if all box configurations have been generated,
	 * 	      <code>false</code> if there is insufficient memory available,
	 *        and not all box configurations have been generated.
	 */

	public boolean isGenerationStoppedDueToFullStorage() {
		return generationStoppedDueToFullStorage;
	}

	/**
	 * Every object of this class gets passed a pushes depth. This depth holds
	 * the number of pushes needed to reach the box configuration passed
	 * to the constructor. This value is unique because there is only one
	 * generation Runnable for every box configuration of the solution
	 * to be optimized. By reading this value the optimizer can identify
	 * each object of this class.
	 * 
	 * @return the pushes depth that has been passed to the constructor of this object
	 */

	public int getBoxConfigurationNumber() {
		return uniqueBoxConfigurationNumber;
	}

	/**
	 * Determine, whether this solution is better than the passed solution,
	 * with respect to moves and then pushes.
	 * 
	 * @param other the other solution we compare against
	 * @return whether this solution is better
	 */

	public boolean isBetterMovesPushesThan( OptimizerSolution other ) {
		if (other == null) {
			return true;			// everybody is better than nobody
		}
		return Utilities.intCompare2Pairs( movesCount , other.movesCount,
		                                   pushesCount, other.pushesCount )
		     < 0 ;
	}

	/**
	 * Determine, whether this solution is better than the passed solution,
	 * with respect to pushes and then moves.
	 * 
	 * @param other the other solution we compare against
	 * @return whether this solution is better
	 */

	public boolean isBetterPushesMovesThan( OptimizerSolution other ) {
		if (other == null) {
			return true;			// everybody is better than nobody
		}
		return Utilities.intCompare2Pairs( pushesCount, other.pushesCount,
		                                   movesCount , other.movesCount  )
		     < 0 ;
	}

	/**
	 * Adds the specified board position into this priority queue.
	 */

	public void add(OptimizerBoardPosition boardPosition) {

		// Ensure that a real board position has been passed.
        if (boardPosition == null)
            throw new NullPointerException();
		
		// Double the size of the queue if it is full.
		if (count >= queue.length) {
			queue = Arrays.copyOf(queue, queue.length * 2);	
		}		
		
		// Add the new value at the correct position. 
		siftUp(count, boardPosition);
		
		// One more board position has been stored => increase the counter.
		count++;
	}

	/**
	 * Returns the number of stored <code>OptimizerBoardPositions</code>.
	 * 
	 * @return the number of stored <code>OptimizerBoardPositions</code>
	 */

	public int size() {
		return count;
	}

	/**
	 * Removes and returns the board position having the lowest metrics (moves, pushes, ...).
	 * 
	 * @return  board position having the lowest metrics 
	 */

	public OptimizerBoardPosition removeFirst() {

		if (count == 0)
			return null;

		// The head of the queue must be returned.
		OptimizerBoardPosition result = queue[0];
		
		// The head of the queue has (logically) been removed => adjust the size.
		--count;
		
		// Remove the last board position.
		OptimizerBoardPosition x = queue[count];
		queue[count] = null;
		
		// If there is at least one board position left in the queue then 
		// shift the board positions so the queue has a new head.
		if (count != 0)
			siftDown(0, x);

		return result;
	}

	/**
	 * Adds the passed <code>String</code> to the log texts of the optimizer
	 * to inform the user about the progress of the optimizer,
	 * or to inform the developer about statistical data.
	 * 
	 * @param text       text to be added to the log
	 * @param stylename  registered name of style to be used
	 */

	private void addLogTextStyle(final String text, final String stylename) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					StyledDocument doc = optimizerLog.getStyledDocument(); 
					doc.insertString(doc.getLength(), text+"\n", doc.getStyle(stylename));			
				} catch (BadLocationException e) {	/* ignore */ }
			}
		});
	}

	 /**
	  *Inserts the passed board position at the passed position.
	  * <p>
	  * This method moves the passed board position down until 
	  * it is less than or equal to its children (or is a leaf).
	  *
	  * @param position the position to start the search for the correct position to insert into
	  * @param boardPosition the board position to be inserted
	  */

	 private void siftDown(int position, OptimizerBoardPosition boardPosition) {
		 
		 // Calculate the half of the size as the maximum position the loop must go to.
		 int half = count >>> 1;        
		 
		 while (position < half) {
			 int childIndex = (position << 1) + 1; // assuming the left child is the "better" board position
			 OptimizerBoardPosition child = queue[childIndex];
			 int rightChildIndex = childIndex + 1;
			 if (rightChildIndex < count && child.compareTo(queue[rightChildIndex]) > 0)
				 child = queue[childIndex = rightChildIndex];
			 if (boardPosition.compareTo(child) <= 0)
				 break;
			 queue[position] = child;
			 position = childIndex;
		 }
		 
		 queue[position] = boardPosition;
	 }

	/**
	 * Returns the number of board positions stored in this hash table.
	 *
	 * @return  the number of board positions stored in this hash table.
	 */

	public int getNumberOfStoredBoardPositions() {
		return count.get();
	}

	/**
	 * Removes all markings of squares.  
	 */

	public void removeAllMarkings() {
		markedSquares = null;
	}

	/**
	 * Debug method: without {@link Settings#isDebugModeActivated} nothing
	 * visible happens.
	 * In debug mode we print the current statistics to the optimizer logging.
	 */

	public void showDeadlockQueryStats() {
		dlqTotStats.sumFrom(dlqCurStats);
		if ( ! dlqTotStats.equals(dlqCurStats)) {
			show1DLQStats("total", dlqTotStats, 0);
		}
		
		show1DLQStats("this time", dlqCurStats, dlqTotStats.dlqAsked);
		dlqCurStats.clear();
	}

	/**
	 * Returns the box configuration index of the passed board position.
	 * 
	 * @param boardPositionIndex  the index of the board position
	 * @return the index the box configuration is stored at in the box configuration storage
	 */

	private int getBoxConfigurationIndex(int boardPositionIndex) {
		// The board position may just been locked by another thread. However,
		// this method is only called for already completely stored board positions.
		// Hence, the index can't hold just the "LOCKED" value but must
		// always also contain a valid box configuration index.
		return table.get(boardPositionIndex+BOX_CONFIGURATION_OFFSET)&(~LOCKED); 
	}

	/**
	 * Returns whether the second passed box configuration is a subset
	 * of the first passed box configuration.
	 * 
	 * @param boxConfiguration1
	 *            box configuration 1
	 * @param boxConfigurationArray
	 *            array which contains the box configuration to be checked for
	 *            being a subset
	 * @param indexInArray
	 *            index of the box configuration in the array
	 * 
	 * @return <code>true</code> if the second passed box configuration is a subset
	 *         of the first passed box configuration, and <code>false</code> otherwise
	 */

	final private boolean hasSubset(byte[] boxConfiguration1, byte[] boxConfigurationArray, int indexInArray) {
		for (int i = 0; i < packedBoardByteSize; i++) {
			byte b2 = boxConfigurationArray[indexInArray + i];
			// "b2" is a subset, if all its 1-bits survive the ANDing with the first byte
			if ((boxConfiguration1[i] & b2) != b2) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns the player position of the passed board position.
	 * 
	 * @param boardPositionIndex  the index of the board position
	 * @return the player position of the passed board position
	 */

	private int getPlayerPosition(int boardPositionIndex) {
		// The player position is only using some of the lower bits of the int it is stored in. Hence, we have to use a bit mask.
		return table.get(boardPositionIndex+PLAYER_POSITION_OFFSET)&PLAYER_POSITION_AND_MASK;
	}	

		/**
		 * Doubles the tree size.
		 */

		final private void doubleTreeSize() {
	
			// Double the tree size.
			int[] newArrayPointers = new int[2*2*treeSizeMax];
			System.arraycopy(arrayPointers, 0, newArrayPointers, 0, 2*treeSizeMax);
			arrayPointers = newArrayPointers;
	
			byte[] newArrayData = new byte[packedBoardByteSize*2*treeSizeMax];
			System.arraycopy(treeData, 0, newArrayData, 0, packedBoardByteSize*treeSizeMax);
			treeData = newArrayData;
	
			treeSizeMax <<= 1;
		}

		/**
		 * Fills the sorted list with the box configurations from the tree.
		 * 
		 * @param indexInTree index of the box configuration in the tree
		 * @param last	
		 * @return "lowest" boxConfiguration at the moment in the tree
		 */

		final private int treeToSortedListRecursively(int indexInTree, int last) {
	
			// If the end of the tree has been reached return the last index.
			if (indexInTree == -1) {
				return last;
			}
	
			// Left side of the tree.
			int d = treeToSortedListRecursively(arrayPointers[2*indexInTree], last);
			sortedList[d++] = indexInTree;
	
			// Right side of the tree.
			return (treeToSortedListRecursively(arrayPointers[2*indexInTree+1], d));
		}

	/**
	 * Returns the number of the currently selected object.
	 * 
	 * @return number of the currently selected obj
	 */

	final public int getNumberOfSelectedObject() {
		return currentlyMarkedObject;
	}

		/**
		 * Transforms the tree to a binary heap.
		 * This method destroys the tree. That means:
		 * only the binary heap can be used after this method has been called.
		 */

		final private void binaryTreeToBinaryHeap() {
		
			byte[] treeDataTemp = new byte[packedBoardByteSize*treeSize];
		
			for (int i=0, indexInVector = 0; i<treeSize; i++) {
				System.arraycopy(treeData, arrayPointers[i]*packedBoardByteSize, treeDataTemp, indexInVector, packedBoardByteSize);
				indexInVector += packedBoardByteSize;
			}
		
			treeData = treeDataTemp;
			arrayPointers = null;
		}

	/**
	 * Using the mouse wheel is interpreted as a move through the selectable objects. 
	 *
	 * @param evt the <code>MouseWheelEvent</code> fired
	 */

	final public void mouseWheelMoved(MouseWheelEvent evt) {

		int scrollDirection = evt.getWheelRotation();

		// Scrollen nach oben
		if (scrollDirection < 0 && currentlyMarkedObject > 0) {
			currentlyMarkedObject--;
		}

		// Scrollen nach unten
		if (scrollDirection > 0 && currentlyMarkedObject < 4) {
			currentlyMarkedObject++;
		}

		application.redraw(false);
	}

	/**
	 * Sets the "is already processed" status of the passed board position.
	 * 
	 * @param boardPositionIndex  index of the board position in this storage
	 * @param processedStatus processed status to set
	 */

	public void setProcessedStatus(int boardPositionIndex, boolean processedStatus) {
		
		// Set/delete the "processed"-flag. This needn't to be done using compareAndSwap
		// because all of the other information stored at that offset have already been 
		// stored and cannot change anymore while this method is executed.
		int currentValue = table.get(boardPositionIndex+PLAYER_POSITION_OFFSET);
		if(processedStatus == true)
			table.set(boardPositionIndex+PLAYER_POSITION_OFFSET, currentValue | PROCESSED_FLAG_BIT_MASK);
		else
			table.set(boardPositionIndex+PLAYER_POSITION_OFFSET, currentValue & (~PROCESSED_FLAG_BIT_MASK));
	}

    /**
	 * This method is called when the user selects another view.
	 */

	public void stateChanged(final ChangeEvent e) {
				
		// Get the tabbed pane all views are stored in.
		JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
		
		/*
		 * View on the levels
		 */
		if(tabbedPane.getSelectedComponent() == languageSettings) {
		
		}		
		
	}

		/**
		 * Sets all variables to null for the garbage collection.
		 */

		protected void finalize() {
			arrayPointers = null;
			sortedList = null;
			treeData = null;
		}

    /**
	 * This method is called when the user selects another view.
	 */

	public void stateChanged(final ChangeEvent e) {
				
		// Get the tabbed pane all views are stored in.
		JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
		
		/*
		 * Language settings
		 */
		if(tabbedPane.getSelectedComponent() == languageSettings) {
		
		}		
		
	}

	/**
	 * Creates all things this panel needs.
	 */

	private void createPanel() {
		
		setLayout(new BorderLayout());

		JPanel guiPanel = new JPanel(new GridLayout(0, 1, 0, 10));
		guiPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createEmptyBorder(10, 10, 10, 10)));

		// Just a quick and dirty test coding ...
		NimRODFontDialog d = new NimRODFontDialog(null);
		Component[] c = d.getContentPane().getComponents();
		guiPanel.add(c[0]);
		
		add(guiPanel, BorderLayout.NORTH);
	}

	/**
	 * Use the current value of the check box to enable or disable
	 * the text field and label components.
	 */

	private void updateElements() {
		final boolean selected = checkboxGUI.isSelected();
		
		textfieldGUI.setEnabled(selected);
		labelGUI.setEnabled(selected);
	}

	/**
	 * Returns the value of the input field as double.
	 * 
	 * @return the value of the input field, or {@code null}
	 */

	public Double getValueAsDouble() {
		return checkboxGUI.isSelected() ? textfieldGUI.getValueAsDouble() : null;
	}

	/**
	 * Returns the value of the input field as integer.
	 * 
	 * @return the value of the input field, or {@code null}
	 */

	public Integer getValueAsInteger() {
		return checkboxGUI.isSelected() ? textfieldGUI.getValueAsInteger() : null;
	}

	/**
	 * Convenience function: enters this object as the default table cell
	 * renderer for type {@link java.util.Date}.
	 * This is the expected usage for this class.
	 * 
	 * @param table
	 */

	public void enterMeForTypeDate(JTable table) {
		table.setDefaultRenderer(java.util.Date.class, this);
	}

	/**
	 * Adds a level to be displayed
	 * @param level level to be displayed
	 */

	public void addLevel(Level level) {
		displayedLevels.add(level);
		if (displayedLevels.size() < levelsPerPage) {
			lastLevelIndex = displayedLevels.size() - 1;
		} else {
			lastLevelIndex = levelsPerPage - 1;
		}
	}

	/**
	 * Returns whether the field is set "active".
	 * 
	 * @return <code>true</code> if the field is active, and
	 *        <code>false</code> otherwise
	 */

	public boolean isFieldActive() {
		return textfieldGUI.isEnabled();
	}

	/**
	 * Adds a collection of levels to be displayed
	 * @param collection collection of levels
	 * @param startNumber index of the start level in the collection
	 * @param endNumber index of the end level in the collection
	 */

	public void addLevelCollection(final LevelCollection collection, int startNumber, int endNumber) {
		for(int levelNo = startNumber; levelNo <= collection.getNumberOfLevels() && levelNo <= endNumber; levelNo++) {
			addLevel(collection.getLevel(levelNo));
		}
	}

	/**
	 * Sets a new value for the display field.<br>
	 * If the value is higher than the maximum or lower than the minimum,
	 * the maximum or minimum value is used, respectively.
	 * In any case the effectively used value is returned.
	 * 
	 * @param value the value to be set
	 * @return the set value
	 */

	public double setValue(double value) {

		double rc = value;
		if (value > maximumValue) {
			rc = maximumValue;
		} else if (value < minimumValue) {
			rc = minimumValue;
		} else {
			rc = value;
		}
		textfieldGUI.setValue(rc);

		return rc;		
	}

	/**
	 * Method for closing the window after the ok-button has been clicked
	 * 
	 * @param actionevent the action event to be analyzed
	 */

	public void actionPerformed(ActionEvent actionevent) {
		if (actionevent.getActionCommand() == "okbutton") {
			dispose();
		}
	}

	/**
	 * Informs all listeners using objects generated by the passed generator.
	 * While calling the listener method for this notification this
	 * ListenerSet object is not synchronized, any more.
	 * Only the initial copying of the listener list is synchronized.
	 * 
	 * @param eventgen generates the events to be passed to the listeners
	 *                  ({@code null} is taken to generate {@code nulls})
	 */

	public void informAllUnsync(Generator<E> eventgen) {
		/*
		 * We must not directly use (weakhashmap.keySet()), since it still
		 * is coupled to the weakhashmap itself, which needs synchronization.
		 * Hence we first must obtain a copy.
		 */
		for (L listener : getListenersCopy()) {
			final E evt = ((eventgen != null) ? eventgen.generate() : null);
			caller.call(listener, evt);
		}
	}

	/**
	 * Sets up the button for the "start" version.
	 */

	public void setToStart() {
		setTo(true);
	}

	/**
	 * Sets up the button for the "stop" version.
	 */

	public void setToStop() {
		setTo(false);
	}

	/**
	 * Sets up the button for the indicated version.
	 * 
	 * @param forStart whether to set up for the "start" version
	 */

	private void setTo( boolean forStart ) {
		String textkey = (forStart ? startkey           : stopkey          );
		String action  = (forStart ? startActionCommand : stopActionCommand);
		
		setText(Texts.getText( textkey ));
		setActionCommand( action );
		
		setupBorder(forStart);
		setupBgColor(forStart);
	}

	/**
	 * Partial setup regarding the border.
	 * 
	 * @param forStart whether to set up for the "start" version
	 */

	private void setupBorder(boolean forStart) {
		setBorder( useBorder ? makeBorder(forStart) : null );
	}

	/**
	 * Partial setup regarding the background color.
	 * 
	 * @param forStart whether to set up for the "start" version
	 */

	private void setupBgColor(boolean forStart) {
		if (useBgColor) {
			int rgb = (forStart ? bgColorStart : bgColorStop);
			
			setBackground( new Color(rgb) );
			setOpaque(false);
		} else {
			// FFS/hm: should we actively put away a background color?
		}
	}

	/**
	 * Create the border for the indicated version.
	 * Does <em>not</em> look at {@link #useBorder}.
	 * 
	 * @param forStart whether to create it for the "start" version
	 * @return         border for the indicated version
	 */

	public Border makeBorder(boolean forStart) {
		int rgb = (forStart ? borderColorStart : borderColorStop);
		return makeRgbBorder(rgb, useRoundedCorners);
	}

	/**
	 * Creates and returns a border for the "start" version.
	 * Does <em>not</em> look at {@link #useBorder}.
	 * 
	 * @return border for a "start" version
	 */

	public Border makeStartBorder() {
		return makeBorder(true);
	}

	/**
	 * Creates and returns a border for the "stop" version.
	 * Does <em>not</em> look at {@link #useBorder}.
	 * 
	 * @return border for a "stop" version
	 */

	public Border makeStopBorder() {
		return makeBorder(false);
	}

	/**
	 * Does not have effect before the next {@link #setToStart()}
	 * or {@link #setToStop()}.
	 * @param useBorder the useBorder to set
	 */

	public void setUseBorder(boolean useBorder) {
		this.useBorder = useBorder;
	}

	/**
	 * Does not have effect before the next {@link #setToStart()}
	 * or {@link #setToStop()}.
	 * @param useBgColor the useBgColor to set
	 */

	public void setUseBgColor(boolean useBgColor) {
		this.useBgColor = useBgColor;
	}

	/**
	 * Does not have effect before the next {@link #setToStart()}.
	 * @param borderColorStart the borderColorStart to set
	 */

	public void setBorderColorStart(int borderColorStart) {
		this.borderColorStart = borderColorStart;
	}

	/**
	 * Does not have effect before the next {@link #setToStop()}.
	 * @param borderColorStop the borderColorStop to set
	 */

	public void setBorderColorStop(int borderColorStop) {
		this.borderColorStop = borderColorStop;
	}

	/**
	 * Does not have effect before the next {@link #setToStart()}.
	 * @param bgColorStart the bgColorStart to set
	 */

	public void setBgColorStart(int bgColorStart) {
		this.bgColorStart = bgColorStart;
	}

	/**
	 * Does not have effect before the next {@link #setToStop()}.
	 * @param bgColorStop the bgColorStop to set
	 */

	public void setBgColorStop(int bgColorStop) {
		this.bgColorStop = bgColorStop;
	}

	/**
	 * Computes and returns whether the rectangle given by the left upper corner
	 * and both dimensions is completely outside the specified clip rectangle.
	 * When the clip rectangle is missing, it is considered to be arbitrarily large.
	 * 
	 * @param x    x of upper left corner
	 * @param y    y of upper left corner
	 * @param xlen width
	 * @param ylen height
	 * @param clip rectangle we compare against
	 * @return whether (x,y,xlen,ylen) is completely outside of "clip"
	 */

	static private boolean isOutsideClip(int x, int y, int xlen, int ylen, Rectangle clip) {

		// Non-existing rectangle stands for the complete plane => nothing is outside of the complete plane.
		// We are "outside" of "clip", if we do not intersect with it.
		return clip != null && ! clip.intersects(x, y, xlen, ylen);
		
	}

	/**
	 * Returns whether this shortcut has an alias.
	 * 
	 * @return <code>true</code> if this shortcut has an alias,
	 *        <code>false</code> otherwise
	 */

	public boolean hasAlias() {
		return (alias.length() > 0);
	}

	/**
	 * Returns the alias of this shortcut.
	 * 
	 * @return the alias of this shortcut
	 */

	public String getAlias() {
		return alias;
	}

	/**
	 * Sets the alias of this shortcut.
	 * 
	 * @param newAlias the new alias of this shortcut
	 */

	public void setAlias(String newAlias) {
		alias = newAlias;
	}

	/**
	 * Returns the path stored in this shortcut.
	 * 
	 * @return the path
	 */

	public String getPath() {
		return path;
	}

	/**
	 * Returns the name of this shortcut.
	 * <p>
	 * If there is an alias this alias is returned. Otherwise the path is returned.
	 * 
	 * @return the name of this shortcut
	 */

	public String getName() {
		if (hasAlias()) {
			return alias;
		}
		return path;
	}

	/**
	 * Returns formatted shortcut's name for display.
	 *
	 * @return the display name of this shortcut
	 */

	public String getDisplayName() {
		if (hasAlias()) {
			return '[' + alias + ']';
		}
		return path;
	}

	/**
	 * Extracts the graphic specified by the passed name from the passed graphic
	 * which contains all graphics of the skin.
	 * <p>
	 * This method is only used for reducing the lines of code when extracting
	 * the graphics from the entire graphic containing all skin graphics.
	 * This method assumes that the position of the graphic to be extracted
	 * is stored in the settings as "x, y" coordinates.
	 * 
	 * @param entireGraphic  skin graphic containing all skin graphics
	 * @param graphicName  the name of the graphic to extract
	 * @return the extracted <code>BufferedImage</code>
	 */

	private BufferedImage extractGraphicTwoCoordinates(BufferedImage entireGraphic, String graphicName) {
	
		try {
			Point graphicCoordinates = getPoint(graphicName);
			if(graphicCoordinates != null) {
				return entireGraphic.getSubimage(graphicCoordinates.x * graphicWidth,
						                         graphicCoordinates.y * graphicHeight,
						                         graphicWidth, graphicHeight);
			}
		} catch (Exception e) {
			if(Settings.isDebugModeActivated) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	/**
	 * Returns the color of this shortcut.
	 * 
	 * @return the color of this shortcut
	 */

	public Color getColor() {
		return color;
	}

	/**
	 * Sets the color of this shortcut.
	 * 
	 * @param color color to be set
	 */

	public void setColor(String color) {
		this.color = parseColor(color);
	}

	/**
	 * Sets the board this GUI shows.
	 * 
	 * @param board board to be shown
	 */

	public void setBoardToDisplay(Board board) {

		// Save the reference.
		this.board = board;

		// Create new array for storing information about which positions have to be drawn when repainting.
		graphicStatus = new byte[board.size];

		// Initialize the debug array used for showing numbers on the board.
		Arrays.fill(numbersToShow, -1);

		// Reset the transformation of the board.
		transformBoard(-1);
	}

	/**
	 * Returns the <code>String</code> representation of the shortcut's color.
	 * 
	 * @return color of this shortcut as <code>String</code>
	 */

	public String getColorString() {
		return colorToString(color);
	}

	/**
	 * Extracts the graphic specified by the passed name from the passed graphic
	 * which contains all graphics of the skin.
	 * <p>
	 * This method is only used for reducing the lines of code when extracting
	 * the graphics from the entire graphic containing all skin graphics.
	 * This method assumes that only the x-coordinate is relevant for locating
	 * a sub graphic in the entire graphic. 
	 * 
	 * @param entireGraphic  skin graphic containing all skin graphics
	 * @param graphicName  the name of the graphic to extract
	 * @return the extracted <code>BufferedImage</code>
	 */

	private BufferedImage extractGraphicOneCoordinate(BufferedImage entireGraphic, String graphicName) {
	
		try {
			int xCoordinate = getInt(graphicName, -1);
			if(xCoordinate == -1) {
				return null;
			}
			return entireGraphic.getSubimage(xCoordinate * graphicWidth, 0, graphicWidth, graphicHeight);
		} catch (Exception e) {
			if(Settings.isDebugModeActivated) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	/**
	 * Erase any comments of the passed property string.
	 * A comment starts at the first '#' (hash sign).
	 * Also, blanks and tabs are trimmed from the (right) end of the result.
	 * 
	 * @param propertyValue value of a property as a String
	 * @return trimmed value
	 */

	private String trimValue(String propertyValue) {
	
		if (propertyValue == null || propertyValue.length() == 0) {
			return "";
		}
	
		int lastpos = propertyValue.indexOf('#');
		if (lastpos == -1) {
			lastpos = propertyValue.length() - 1;
		}
		
		
		for(; lastpos >= 0 ; --lastpos ) {
			char c = propertyValue.charAt(lastpos);
			if (c != ' ' && c != '\t') {
				break;
			}
		}
		
		// Now, "lastpos" indexes the last char to be retained (or -1 if there is none)
		
		// Return the trimmed value.
		return propertyValue.substring(0, lastpos + 1);
	}

	/**
	 * Returns the current transformation as String.
	 * This String is saved into the file of the level as information.
	 * Note: this string is NOT influenced by any language settings.
	 * 
	 * @return the transformation as <code>String</code>
	 * @see #newlevel()
	 */

	static public String getTransformationAsString() {

		// If the level isn't displayed transformed return an empty String.
		if (getRotationValue() == ROTATION_BY_0_DEGREES && isLevelFlippedHorizontally() == false) {
			return "";
		}

		// Build the transformation string and return it.
		String transformationString = "View: Rotated "
			                        + getRotationAsString()
			                        + " degrees clockwise";
		if (isLevelFlippedHorizontally()) {
			transformationString += ", flipped horizontally.";
		} else {
			transformationString += ".";
		}

		return transformationString;
	}

	/**
	 * This default generator implementation just returns the {@link #base}
	 * object reference.
	 * That probably works only for immutable types T.
	 * <p>
	 * Most implementations should override this method by something that
	 * clones the {@link #base} or uses it as basis for a constructor.
	 * E.g. ActionEvents are neither immutable nor do they support cloning.
	 * 
	 * @return the next generated object
	 */

	public T generate() {
		return base;
	}

	/**
	 * Creates a button for the tool bar.
	 * 
	 * @param iconName		the name of the icon for the button
	 * @param actionCommand the action command of the button
	 * @param toolTipText 	the tool tip text for the button
	 * @return the created button
	 * @see #createToolBarButtonByKey(String, String, String)
	 */

	final private JButton createToolBarButton(String iconName, String actionCommand, String toolTipText) {

		// Create and initialize the button.
		JButton button = new JButton(Utilities.getIcon(iconName, null));
		button.setActionCommand(actionCommand);
		button.setToolTipText(toolTipText);
		button.addActionListener(this);

		// The buttons must be clicked with the mouse.
		button.setFocusable(false);

		return button;
	}

	/**
	 * Returns the currently used skin.
	 * 
	 * @return the <code>Skin</code> currently used
	 */

	public Skin getCurrentSkin() {
		return skin;
	}

	/**
	 * Returns the string corresponding to the passed property name.
	 * 
	 * @param name name of property	
	 * @param defaultValue value to be set if the property value can't be set
	 * @return value of the property as string or null, if no property is found
	 */

	private String getString(String name, String ... defaultValue) {
	
		// Get the value of the property.
		String propertyValue = trimValue(properties.getProperty(name));
	
		// If the the property couldn't be found set the default value.
		if (propertyValue == null) {
			if(defaultValue.length > 0) {
				return defaultValue[0];
			}
		}
	
		return propertyValue;
	}

	/**
	 * Creates a button for the tool bar.
	 * 
	 * @param iconName       the name of the icon for the button
	 * @param actionCommand  the action command of the button
	 * @param toolTipTextKey the text key for the tool tip text for the button
	 * @return the created button
	 * @see #createToolBarButton(String, String, String)
	 */

	final private JButton createToolBarButtonByKey(String iconName, String actionCommand, String toolTipTextKey) {
		String toolTipText = Texts.getText(toolTipTextKey);
		return createToolBarButton(iconName, actionCommand, toolTipText);
	}

	/**
	 * Sets a new delay for the animations that are shown.
	 * <p>
	 * Depending on the skin there may be an animation for a selected box and
	 * the selected player.
	 * 
	 * @param delay
	 *            the delay in milliseconds
	 */

	public void setSkinAnimationDelay(int delay) {

		// Save the delay in the settings.
		Settings.set("skinAnimationDelay", "" + delay);

		// If a timer is running set the new delay value.
		if (animationTimer != null && animationTimer.isRunning()) {
			animationTimer.setDelay(delay);
		}
	}

	/**
	 * Sets the view direction of the player to the passed direction.
	 * <p>
	 * The view direction is important for some skins in order to be able to
	 * show the correct graphic.
	 * 
	 * @param viewDirection
	 *            the view direction of the player
	 */

	public void setViewDirection(int viewDirection) {
		this.viewDirection = (byte) viewDirection;
	}

	/**
	 * Sets the maximum factor for scaling the graphics.
	 * <p>
	 * Some skins offer bad quality graphics which shouldn't be scaled too much.
	 * Hence the user can set a maximum scaling factor. This way the graphics
	 * aren't scaled beyond this factor even if there is enough space for the
	 * graphics to be drawn.
	 * 
	 * @param maximumScalingFactor
	 *            the new factor to be set
	 */

	public void setMaximumScalingFactor(int maximumScalingFactor) {

		maximumScaling = maximumScalingFactor;

		recalculateGraphicSizes();
		repaint();

		// Save the zooming factor.
		Settings.set("maximumScaling", "" + maximumScaling);
	}

	/**
	 * Sets the flag that a recalculation has to be done.
	 */

	public void recalculateGraphicSizes() {
		isRecalculationNecessary = true;
	}

	/**
	 * Transforms the board (rotation and mirroring)
	 * 
	 * @param transformationValue  kind of transformation
	 */

	public void transformBoard(int transformationValue) {

		// Tell it to the Transformation class.
		Transformation.transform(transformationValue);

		// Since the transformation may have exchanged height and width, we force a
		// recalculation of the graphics, as if we had loaded a new level.
		isRecalculationNecessary = true;

		// Show new graphics
		repaint();
	}

	/**
	 * Sets the passed level as level to be displayed in this Panel.
	 * 
	 * @param levelToBeDisplayed  the <code>Level</code> to be displayed
	 */

	public void setLevelToDisplay(Level levelToBeDisplayed) {
		
		// Create an own board.
		board = new Board(application);
		
		// Set the passed level on the board.
		try {
			board.setBoardFromString(levelToBeDisplayed.getBoardDataAsString());
		} catch (Exception e) {
			// Show the error message.
			Utilities.showExceptionError(this, e);
		}

		// Array containing the information which graphic has to be drawn at a specific position.
		graphicStatus = new byte[board.height * board.width];
		
		// New level means every thing has to be recalculated to refresh the Panel.
		isRecalculationNecessary = true;

		// Repaint this Panel.
		repaint();
	}

	/**
	 * Sets the passed board to be displayed.
	 * 
	 * @param boardAsString the board data as <code>String</code>
	 */

	public void setBoardToDisplay(String boardAsString) {
		
		// Create an new board.
		board = new Board(application);
		
		// Create an own board from the board of the level.
		try {
			board.setBoardFromString(boardAsString);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Array containing the information which graphic has to be drawn at a specific position.
		graphicStatus = new byte[board.height * board.width];
		
		// New level means every thing has to be recalculated to refresh the Panel.
		isRecalculationNecessary = true;

		// Repaint this Panel.
		repaint();
	}

	/**
	 * Sets the flag specifying whether the infobar is to be shown or not.
	 * 
	 * @param visibleStatus
	 *            visibility status of the infobar to set
	 */

	public void setInfoBarVisible(boolean visibleStatus) {
		isInfoBarVisible = visibleStatus;
	}

	/**
	 * Sets the passed board to be displayed.
	 * 
	 * @param board the board to be displayed
	 */

	public void setBoardToDisplay(Board board) {
		
		this.board = board;
		
		// Array containing the information which graphic has to be drawn at a specific position.
		graphicStatus = new byte[board.height * board.width];
		
		// New level means every thing has to be recalculated to refresh the Panel.
		isRecalculationNecessary = true;

		// Repaint this Panel.
		repaint();
	}

	/**
	 * Returns the <code>Board</code> displayed in this class.
	 * 
	 * @return the <code>Board</code> of this class
	 */

	public Board getBoard() {
		return board;
	}

	/**
	 * Returns the <code>Level</code> displayed in this class
	 * 
	 * @return the <code>Level</code> of this class
	 */

	public Level getDisplayedLevel() {
		return displayedLevel;
	}

	/**
	 * Returns the current width of a square from the current scaled skin.
	 * @return width of a square
	 */

	public int getSquareWidth() {
		if (skin == null) {
			return 0;
		}
		return skin.graphicWidth;
	}

	/**
	 * Returns the current height of a square from the current scaled skin.
	 * @return height of a square
	 */

	public int getSquareHeight() {
		if (skin == null) {
			return 0;
		}
		return skin.graphicHeight;
	}

	/**
	 * Sets the flag indication that the sizes must be recalculated.
	 * If e.g. we switch into editor mode, we have less space for the board,
	 * since we have to draw some objects for the editor.
	 */

	final public void recalculateGraphicSizes() {
		isRecalculationNecessary = true;
	}

	/**
	 * Resets the <code>board</code> and the player position members
	 * from the <code>boardInitial</code> member.
	 */

	private void setInitialBoardPosition() {
		board      = boardInitial.boardElements.clone();
		playerPosX = boardInitial.playerPositionX;
		playerPosY = boardInitial.playerPositionY;
	}

	/**
	 * Repaints the GUI immediately.
	 * This is the sledge-hammer method: everything is painted, again.
	 */

	public void paintImmediately() {
		paintImmediately(0, 0, getWidth(), getHeight());
	}

	/**
	 * Packs the passed unpacked box configuration into a new packed box configuration.
	 * 
	 * @param newPackedBoxConfiguration
	 * @param unpackedBoxConfiguration
	 */

	private void packBoxConfiguration(byte[] newPackedBoxConfiguration, byte[] unpackedBoxConfiguration) {

		int bytePosition = 0, bitPosition = 0;

		// Loop over all internal box positions.
		for (int i = 0; i < boxPositionsCount; i++) {

			bytePosition = i >> 3;
			bitPosition  = i & 7;

			if ((unpackedBoxConfiguration[boxInternalToExternalPosition[i]] & BOX) == BOX)
				newPackedBoxConfiguration[bytePosition] |= 1 << bitPosition;
			else
				newPackedBoxConfiguration[bytePosition] &= (255 - (1 << bitPosition));
		}
	}

	/**
	 * Marks the passed board position as visited.
	 * 
	 * @param boardPositionIndex
	 *            index of the board position in the visitedData array
	 */

	private void setVisited(int boardPositionIndex) {
		int bytePosition = (boardPositionIndex >>> 3), bitPosition = (boardPositionIndex & 7);
		visitedData[bytePosition] |= (1 << bitPosition);
	}

	/**
	 * Sets the cursor image to the specified cursor.
	 * 
	 * @param cursor The value of the cursor to be set
	 */

	final public void setCursor(int cursor) {

		// Set the specified predefined cursor.
		mainBoardDisplay.setCursor(Cursor.getPredefinedCursor(cursor));
	}

	/**
	 * If the coordinates of the point aren't located on the board
	 * they are adjusted so they are on the edge of the board.
	 * 
	 * @param p  <code>Point</code> containing the data which is adjusted
	 */

	protected void adjustToBoard(Point p) {
		
		// Clip both point coordinates at their allowed minimum (inclusive).
		if( p.x < xOffset ) {
			p.x = xOffset;
		}
		if( p.y < yOffset ) {
			p.y = yOffset;
		}
		
		// Compute maximal point coordinates, inclusive...
		int xmax = xOffset + Transformation.getOutputLevelWidth()  * skin.graphicWidth  - 1;
		int ymax = yOffset + Transformation.getOutputLevelHeight() * skin.graphicHeight - 1;
		
		// ... and use them to clip off larger values:
		if( p.x > xmax ) {
			p.x = xmax;
		}
		if( p.y > ymax ) {
			p.y = ymax;	
		}
	}

	/**
	 * Repaints part of the GUI immediately.  Only a rectangle inside the board is
	 * considered to be changed, specified in external board square coordinates.
	 * But, the number of moves and pushes has also be considered to be changed,
	 * which implies some more updates.
	 * 
	 * @param xmin      upper left X of board square rectangle
	 * @param ymin      upper left Y of board square rectangle
	 * @param extWidth  width  of rectangle in board squares
	 * @param extHeight height of rectangle in board squares
	 */

	private void paintExtSqRect(int xmin, int ymin, int extWidth, int extHeight) {
		int pixXmin   = xOffset + xmin * skin.graphicWidth;
		int pixYmin   = yOffset + ymin * skin.graphicHeight;
		int pixWidth  =      extWidth  * skin.graphicWidth;
		int pixHeight =      extHeight * skin.graphicHeight;
		
		if (pixWidth > 0 || pixHeight > 0) {
			repaint(pixXmin, pixYmin, pixWidth, pixHeight);
			paintMovesPushes();
		}
	}

	/**
	 * Returns the influence distance between the start and the target square.
	 * The higher the distance the less influence the target square has
	 * on the start square.
	 * 
	 * @param startSquare  position of the start square
	 * @param targetSquare position of the target square
	 * 
	 * @return influence distance from the start square to the target square
	 */

	public int getInfluenceDistance(int startSquare, int targetSquare) {
		return influenceDistances[startSquare][targetSquare];
	}

	/**
	 * This method sets the currently set language in all menu bar
	 * and tool bar components.
	 */

	final public void setNewLanguage() {

		// Create a new menu bar and and a new tool bar according to the new language.
		application.setJMenuBar(createMenuBar());
		createToolBar();
	}

	/**
	 * Returns whether the board position (box + player positions) is marked as
	 * visited.
	 * 
	 * @param boardPositionIndex
	 *            index of the board position in the visitedData array
	 * @return <code>true</code>board position has already been visited;
	 *         <code>false</code>otherwise
	 */

	private boolean isVisited(int boardPositionIndex) {
		int i = (boardPositionIndex >>> 3), j = (boardPositionIndex & 7);
		return (visitedData[i] & (1 << j)) > 0;
	}

	/**
	 * Inserts a move of the player into the history.
	 * 
	 * @param direction	into which the player moved
	 */

	final public void addPlayerMove(int direction) {
		addMovement(direction, -1);
	}

		/**
		 * Set the collected values back to the initial values,
		 * indicating an empty range.
		 */

		public void clear() {
			minX = Integer.MAX_VALUE;
			maxX = Integer.MIN_VALUE;
			minY = Integer.MAX_VALUE;
			maxY = Integer.MIN_VALUE;
		}

	/**
	 * Returns the next movement from the history,
	 * and advances the "current" pointer to it.
	 * 
	 * @return <code>null</code>, or the <code>HistoryElement</code> containing the data
	 *         for the next movement of the history
	 */

	final public HistoryElement getSuccessorMovement() {
		if (hasSuccessorMovement() == false) {
			return null;
		}
		return movementHistory.get( ++currentMovementNo );
	}

	/**
	 * Returns the previously executed movement from the history,
	 * and moves back the "current" pointer before it.
	 * 
	 * @return <code>null</code>, or the <code>HistoryElement</code> containing the data
	 *         for the previously executed movement 
	 */

	final public HistoryElement getPrecedingMovement() {
		if (hasPrecedingMovement() == false) {
			return null;
		}
		return movementHistory.get( currentMovementNo-- );
	}

	/**
	 * Returns the movement with the given number.
	 * 
	 * @param movementNo number of the movement to be returned
	 * @return  <code>null</code>, or the <code>HistoryElement</code> of the movement
	 *          with the given number
	 */

	final public HistoryElement getMovement(int movementNo) {

		if (movementNo < 0 || movementNo > movementHistory.size() - 1) {
			return null;
		}
		return movementHistory.get(movementNo);
	}

	/**
	 * Returns the index of the last executed movement.
	 * That is also the count of the player moves up to now/here. 
	 * 
	 * @return index of the last executed movement
	 */

	final public int getCurrentMovementNo() {
		return currentMovementNo;	
	}

		/**
		 * Returns whether the collected area is empty.
		 * 
		 * @return whether the area is empty
		 */

		public boolean isEmpty() {
			return (minX > maxX) || (minY > maxY);
		}

	/**
	 * Returns the total count of the stored movements.
	 * 
	 * @return total count of movements
	 */

	final public int getMovementsCount() {
		return movementHistory.size();
	}

	/**
	 * Returns the (total) number of pushes contained in the history.
	 * 
	 * @return number of pushes in the history
	 */

	final public int getPushesCount() {

		int pushesCount = 0;

		for (HistoryElement historyElement : movementHistory) {
			if (historyElement.pushedBoxNo != -1) {
				pushesCount++;
			}
		}
		return pushesCount;
	}

	/**
	 * Returns the LURD string of all movements up to the current movement.
	 * 
	 * @return LURD string till the current movement (inclusive)
	 * @see #getLURDString(int, int)
	 */

	final public String getLURDString() {
		return getLURDString(0, currentMovementNo);
	}

		/**
		 * Adds a point to the area, given by its components.
		 * 
		 * @param x
		 * @param y
		 */

		public void add(int x, int y) {
			minX = Math.min(minX, x);
			maxX = Math.max(maxX, x);
			minY = Math.min(minY, y);
			maxY = Math.max(maxY, y);
		}

	/**
	 * Returns the complete LURD string of all movements, independent from the
	 * current point inside of the history.
	 * 
	 * @return LURD string till the end (inclusive)
	 * @see #getLURDString(int, int)
	 */

	final public String getLURDStringTotal() {
		return getLURDString(0, getMovementsCount() - 1);
	}

	/**
	 * Sets the history to the first movement.
	 */

	final public void setHistoryToStart() {
		currentMovementNo = -1;
	}

	/**
	 * Sets the "current" pointer to the specified index.
	 * This changes to reference point for further "undo" and "redo" operations.
	 * This is the way to quickly jump to an arbitrary point in the history.
	 *
	 * @param movementNo the movement number to be set
	 */

	final public void setMovementNo(int movementNo) {
		currentMovementNo = movementNo;
	}

	/**
	 * Sets the marker which indicates that the next movement will be the start
	 * of a combined movement.
	 */

	final public void setStartOfCombinedMovement() {
		isStartOfCombinedMovement = true;
	}

		/**
		 * Collect another external position.
		 * External positions are what is handled in the GUI, they reflect screen
		 * positions after any transformation.
		 * 
		 * @param externalPos <code>-1</code>. or external position to collect
		 */

		public void addExternal(int externalPos) {
			if (externalPos != -1) {
				int externalWidth = Transformation.getOutputLevelWidth();
				int extX = externalPos % externalWidth;
				int extY = externalPos / externalWidth;
				
				add(extX, extY);
			}
		}

	/**
	 * Sets back the "current" pointer to the previous movement.
	 * This operation is unchecked / unconditional.
	 */

	final public void goToPrecedingMovement() {
		currentMovementNo--;
	}

	/**
	 * Sets forward the "current" pointer to the next movement.
	 * This operation is unchecked / unconditional.
	 */

	final public void goToNextMovement() {
		++currentMovementNo;
	}

		/**
		 * Collect another internal position (from the model).
		 * Before it is collected, it must be translated to an external position
		 * according to the current transformation.
		 * 
		 * @param internalPos <code>-1</code>. or internal position to collect
		 */

		public void addInternal(int internalPos) {
			if (internalPos != -1) {
				int externalPos = Transformation.getExternalPosition(internalPos);
				
				addExternal(externalPos);
			}
		}

	/**
	 * Returns whether there is a preceding movement.
	 * 
	 * @return <code>true</code>, if there is a  preceding movement,<br>
	 *   	  <code>false</code>, if there is no preceding movement
	 */

	final public boolean hasPrecedingMovement() {
		return currentMovementNo >= 0;
	}

	/**
	 * Returns whether there is a successor movement.
	 * 
	 * @return <code>true</code> if there is a  successor movement,<br>
	 * 		  <code>false</code> if there is no successor movement
	 */

	final public boolean hasSuccessorMovement() {
		return (currentMovementNo + 1) < movementHistory.size();
	}

	/**
	 * Repaints part of the GUI.
	 * Just the number of moves and the number of pushes is considered changed.
	 */

	private void paintMovesPushes() {
		// Moves and pushes are displayed as part of the history slider panel.
		if (historySliderPanel != null) {
			// We have to include the textual representation, as well as the graphical
			// slider representation.  That includes most of the area.
			int w = historySliderPanel.getWidth();
			int h = historySliderPanel.getHeight();
			
			// Redraw the slider. This needn't to be done immediately. Repaint is much
			// faster then paintImmediately.
			historySliderPanel.repaint(0, 0, w, h);
		}
	}

	/**
	 * Return the number of steps to the preceding element one can do,
	 * until the first movement is reached, or one that is marked to be
	 * the start of a combined movement.
	 * 
	 * @return length of the last combined movement
	 */

	final public int combinedLengthPreceding() {
		int steps = 0;
		
		for( int mNo = currentMovementNo; mNo >= 0 ; --mNo ) {
			HistoryElement histElem = getMovement(mNo);
			
			if( histElem == null ) {
				break;
			}
			++steps;
			if( histElem.isStartOfCombinedMovement ) {
				break;
			}
		}
		return steps;
	}

	/**
	 * Return the length of the next combined movement, i.e. how many steps
	 * forward can we do until we find an element which is marked to be
	 * the start of a combined movement (excluding the current element).
	 * 
	 * @return length of the next combined movement
	 */

	final public int combinedLengthSuccessor() {
		int steps = 0;
		int mNo = currentMovementNo;
		
		for(;;) {
			++mNo;
			
			HistoryElement histElem = getMovement(mNo);
			
			if( histElem == null ) {
				break;
			}
			++steps;
			if( histElem.isStartOfCombinedMovement ) {
				break;
			}
		}
		
		return steps;
	}

	/**
	 * Starting with the current movement, count the number of combined
	 * segments.  Even when the first (current) movement is not marked
	 * to be the start of a combined movement, we count it as start
	 * of such a segment.
	 * 
	 * @return count of segments starting at current movement
	 */

	final public int countCombinedForward() {
		int starts = 0;
		int mNo = currentMovementNo;
		
		for(;;) {
			HistoryElement histElem = getMovement(mNo);
			
			if( histElem == null ) {
				break;
			}
			if( histElem.isStartOfCombinedMovement ) {
				++starts;
			} else if( starts == 0 ) {
				++starts;
			}
			++mNo;
		}
		
		return starts;
	}

	/**
	 * Starting with the current movement we scan forward and compute the
	 * average length of combined segments.
	 * @return average length of movement segments starting at current movement
	 */

	final public float averageLengthCombinedForward() {
		int len = getMovementsCount() - currentMovementNo;
		if( len <= 0 ) {
			return 0.0f;
		}
		int cntcombined = countCombinedForward();
		return (float)len / cntcombined;
	}

	/**
	 * Returns if a box has been pushed.
	 * 
	 * @return true = A box has been pushed, false = no box has been pushed
	 */

	public boolean isABoxBeenMoved() {
		return pushedBoxNo != -1;
	}

	/**
	 * Returns the mouse listener for this class.
	 * 
	 * @return the mouse listener
	 */

	private MouseListener getMouseListener() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() > 1) {
					int index = locationToIndex(e.getPoint());
					if (index >= 0) {
						
						// This action can be disabled (for instance when the solver is running).
						if(!isTakeSolutionAsHistoryEnabled) {
							return;
						}
						
						Solution clickedSolution = (Solution) listModel.getElementAt(index);
						
						// Set the selected solution as new history in the game.
						application.takeSolutionForHistory(clickedSolution);
					}
				}
			}
		};
	}

	/**
	 * Sets whether the editor menu item is enabled.
	 * @param enabled
	 */

	final public void setEditorMenuItemEnabled(boolean enabled) {
		editorMenuItem.setEnabled(enabled);
	}

	/**
	 * Returns the menu item for saving a level.
	 * 
	 * @return the <code>JMenuItem</code> for saving a level
	 */

	final public JMenuItem getSaveLevelMenuItem() {
		return saveLevelMenuItem;
	}

	/**
	 * Returns the button for showing info in the menu bar.
	 * <p>
	 * The caller can fully access this button.
	 * 
	 * @return the <code>JMenuItem</code> for saving a level
	 */

	final public JButton getInfoButton() {
		return infoButton;
	}

	/**
	 * Sets the objects enabled or disabled depending on the current mode
	 * (play or editor mode). 
	 */

	final public void setModeDependentObjectStatus() {

		// Ensure to change status on the EDT (event dispatcher thread),
		// because this method may be called from a background thread.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				for (Component button : playModeDependentObjects) {
					button.setEnabled(application.isPlayModeActivated());
				}

				for (AbstractButton button : editorModeDependentObjects) {
					button.setEnabled(application.isEditorModeActivated());
				}
			}
		});
	}

	/**
	 * Displays the passed board position for debug purposes.
	 * 
	 * @param storage
	 *            storage the box configuration is stored in
	 * @param boardPositionIndex
	 *            index of the board position in the visited data array
	 * @param graphicOutput
	 *            flag, indicating whether there should be a graphical display
	 *            or not
	 * @param waitForEnter
	 *            flag, indicating whether the program has to wait for "enter"
	 *            after displaying the box configuration
	 */

	private void debugDisplayBoardPosition(BoxConfigurationStorageHashtable storage, int boardPositionIndex, final boolean graphicOutput, final boolean waitForEnter) {
		byte[] temp = new byte[packedBoardByteSize];
		storage.copyBoxConfiguration(temp, boardPositionIndex / playerSquaresCount);
		int playerPosition = boardPositionIndex % playerSquaresCount;
		debugDisplayBoxConfiguration(temp, playerPosition, graphicOutput, waitForEnter);
	}

	/**
	 * Sets a color for the passed <code>Solution</code> in this GUI.
	 * <p>
	 * If "null" is passed as color the solution is set back to the default color.
	 * 
	 * @param solution the <code>Solution</code> to be colored
	 * @param color the <code>Color</code>
	 */

	public void setSolutionColor(Solution solution, Color color) {
		if(color == null) {
			coloredSolutions.remove(solution);
		} else {
			coloredSolutions.put(solution, color);
		}
	}

	/**
	 * Sets the status of the solver mode dependent objects.
	 * 
	 * @param enabledStatus <code>true</code>, if the objects are enabled, and
	 * 						<code>false</code> if the objects are disabled
	 */

	final public void setSolverDependentObjectsEnabled(final boolean enabledStatus) {
		
		// Ensure to change status on the EDT (event dispatcher thread),
		// because this method may be called from a background thread.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				for (Component component : solverModeDependentObjects) {
					component.setEnabled(enabledStatus);
				}
			}
		});
	}

		/**
		 * Returns whether the the current solution type is a valid
		 * and new solution.
		 * @return <code>true</code> if the solution is a valid new solution,
		 *   or<br><code>false</code> otherwise
		 */

		public boolean isValidNewSolution() {
			return this != INVALID_SOLUTION && this != DUPLICATE_SOLUTION;
		}

	/**
	 * Sets the background color for all solutions to the default color.
	 */

	public void setAllSolutionsUncolored() {
		coloredSolutions.clear();
	}

	/**
	 * Makes a single row "selected".
	 * When the passed view row index is negative (e.g. as result of a
	 * failed conversion) the current selection remains unchanged.
	 * 
	 * @param viewrowindex the view index of the row to be selected
	 */

	private void setSelectedViewRow(int viewrowindex) {
		if (       (viewrowindex >= 0)
				&& (tableModelSolutionData != null)
				&& (tableModelSolutionData.getRowCount() > 0)
				&& (tableSolutionData != null)) {
			ListSelectionModel lsm = tableSolutionData.getSelectionModel();
			lsm.setSelectionInterval(viewrowindex, viewrowindex);
		}
	}

	/**
	 * Adds the passed {@code ActionListener} to the listeners being informed
	 * when an action is fired. 
	 * 
	 * @param actionListener  the {@code ActionListener} to be added
	 */

	public void addActionListener(ActionListener actionListener) {
		actionListenerSet.register(actionListener);
	}

	/**
	 * Shows an information dialog with translated title "note" and the message text
	 * for the text key "solutionList.noSolutionSelected".
	 */

	protected void showNoSolutionsSelected() {
		Utilities.showInfoNoteTextKey(getParent(), "solutionList.noSolutionSelected");
	}

	/**
	 * Removes the passed {@code ActionListener} from the listeners of this object.
	 * 
	 * @param actionListener  the {@code ActionListener} to be removed
	 */

	public void removeActionListener(ActionListener actionListener) {
		actionListenerSet.unregister(actionListener);
	}

	/**
	 * Displays the passed board position for debug purposes.
	 * 
	 * @param storage
	 *            storage the box configuration is stored in
	 * @param boxConfigurationIndex
	 *            index of the box configurtaion in the visited data array
	 * @param playerPosition
	 * 		      position of the player in internal format
	 * @param graphicOutput
	 *            flag, indicating whether there should be a graphical display
	 *            or not
	 * @param waitForEnter
	 *            flag, indicating whether the program has to wait for "enter"
	 *            after displaying the box configuration
	 */

	private void debugDisplayBoardPosition(BoxConfigurationStorageHashtable storage, int boxConfigurationIndex, int playerPosition, final boolean graphicOutput, final boolean waitForEnter) {
		byte[] temp = new byte[packedBoardByteSize];
		storage.copyBoxConfiguration(temp, boxConfigurationIndex);
		debugDisplayBoxConfiguration(temp,playerPosition, graphicOutput, waitForEnter);
	}

	/**
	 * Sets the enabled status of the undo buttons.
	 * 
	 * @param enabledStatus <code>true</code>, if the objects are enabled, and
	 * 						<code>false</code> if the objects are disabled
	 */

	final public void setUndoButtonsEnabled(final boolean enabledStatus) {

		// Ensure to change status on the EDT, because this method may be called from a background thread.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				for (Component component : undoButtons) {
					component.setEnabled(enabledStatus);
				}
			}
		});
	}

		/**
		 * Handles the mouse event "mouseDragged".
		 * 
		 * @param evt  the event that has been fired
		 */

		public void mouseDragged(MouseEvent evt) {
		
			if(isMouseDragged == false) {
			
				startOfLastDrag = evt.getWhen();
				
				// Save the dragged status.
				isMouseDragged = true;
			}
			
			mousePressed(evt);
		}		

	/**
	 * This is the kernel of {@link #informListeners(Solution, String)},
	 * after that method has decided, in which thread we have to work.
	 * @param solution
	 * @param action
	 */

	private void informThemHere(final Solution solution, final String action) {
		actionListenerSet.informAllSync(makeActionGenerator(solution, action));
	}

	/**
	 * Informs all listeners about a change of the stored solutions.
	 * <p>
	 * The listeners must know which actions can be caught from this class
	 * (this avoids an extra interface containing methods for the different actions).
	 */

	private void informListeners(final Solution solution, final String action) {
	
		// The GUI should be updated in the EDT.
		if(SwingUtilities.isEventDispatchThread()) {
			informThemHere(solution, action);
		}
		else {
			// Update the GUI before this thread continues. 
			try {
				SwingUtilities.invokeAndWait(new Runnable() {
					@Override
					public void run() {
						informThemHere(solution, action);
					}
				});
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			} catch (InvocationTargetException e) { /* just continue */ }
		}
	}

	/**
	 * Sets the visibility of the "take solution as history" menu item.
	 * 
	 * @param isVisible <code>true</code> sets the "take solution as history" menu item visible
	 * 				   <code>false</code> sets the "take solution as history" menu item invisible
	 */

	public void setTakeSolutionAsHistoryVisible(boolean isVisible) {
		isTakeSolutionAsHistoryEnabled = isVisible;
	}

	/**
	 * Sets the enabled status of the redo buttons.
	 * 
	 * @param enabledStatus <code>true</code> if the objects are enabled, and
	 * 					   <code>false</code> if the objects are disabled
	 */

	final public void setRedoButtonsEnabled(final boolean enabledStatus) {

		// Ensure to change status on the EDT, because this method may be called from a background thread.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				for (Component component : redoButtons) {
					component.setEnabled(enabledStatus);
				}
			}
		});
	}

		/**
		 * Add the passed solution as solution to be highlighted.
		 * 
		 * @param solution <code>Solution</code> to be highlighted
		 */

		public void addSolutionToBeHighlighted(Solution solution) {
			highlightedSolutions.put(solution, 10);
			
			// Inform this thread that new solutions are to be highlighted.
			synchronized(this) {
				notify();
			}
		}

		/**
		 * Removes all solutions to be highlighted.
		 */

		public void removeAllSolutionsToBeHighlighted() {
			highlightedSolutions.clear();
		}

		/**
		 * Returns the intensity the passed solution is to be highlighted.
		 *
		 * @param solution the <code>Solution</code> whose highlight intensity is returned
		 * @return the highlight intensity or null if the passed solution isn't to be highlighted
		 */

		public Integer getHighlightIntensity(Solution solution) {
			return highlightedSolutions.get(solution);
		}

	/**
	 * This methods sets the enabled status of specific GUI elements.
	 * <p>
	 * If the current loaded level is invalid then some of the GUI elements
	 * have to be disabled.
	 * 
	 * @param enabledStatus  whether the GUI elements are to be enabled
	 */

	final public void setInvalidLevelModeDependentObjectsEnabled(boolean enabledStatus) {

		for (Component component : invalidLevelModeDependentObjects) {
			component.setEnabled(enabledStatus);
		}

		// An invalid level can't have a movement history.
		// Hence, disable the undo/redo buttons.
		setUndoButtonsEnabled(enabledStatus);
		setRedoButtonsEnabled(enabledStatus);

		// Enable the editor menu item so the user can open the editor.
		setEditorMenuItemEnabled(true);
	}

	/**
	 * Tell whether we currently have a valid {@code lastChanged} data.
	 * 
	 * @return whether the {@code lastChanged} data is valid
	 */

	public boolean isValidLastChanged() {
		if (lastChanged != null) {
			if (lastChanged.getTime() > 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Sets the debug menu visible or invisible.
	 * 
	 * @param isToBeVisible <code>true</code>, if the debug menu shall be visible, and
	 * 						<code>false</code> if the debug menu shall be invisible
	 */

	final public void setDebugMenuVisible(boolean isToBeVisible) {
		for (Component component : debugModeDependentObjects) {
			component.setVisible(isToBeVisible);
		}
	}

	/**
	 * Returns the solutions view.
	 * <p>
	 * The solutions are shown in an own JPanel at the left of the main GUI.
	 * 
	 * @return the <code>solutionsGUI</code> displaying the solutions
	 */

	public SolutionsGUI getSolutionsView() {
		return solutionsGUI;
	}

	/**
	 * Method used for the SQL commands CREATE, DROP, INSERT and UPDATE
	 * 
	 * @param expression the SQL command to be executed
	 */

	final public void update(String expression) {

		try {
			Statement st = conn.createStatement();

			// Run the query.
			if (st.executeUpdate(expression) == -1) {
				System.out.println("db error: " + expression);
			}

			// Close the Statement object.
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Wait for the deadlock identification (detection) to be ready.
	 * This must be done prior to generation of box configurations,
	 * since the results of deadlock computation are used there
	 * without any further checks.
	 */

	private void waitForDeadlockIdentification() {
		try {
			if(deadlockIdentification.isAlive()) {
				optimizerGUI.setInfoText(Texts.getText("optimizer.waitingForDeadlockDetection"));
				deadlockIdentification.join();
			}
		} catch (InterruptedException e) {}
	}

	/**
	 * Saves the passed level using the passed file name.
	 *
	 * @param level the <code>Level</code> to save
	 * @param fileName the file the level is to be saved to
	 * @throws IOException thrown when the level couldn't be saved
	 */

	final public void saveLevel(Level level, String fileName) throws IOException {

		// Create a PrintWriter for writing the data to hard disk.
		PrintWriter levelFile = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));

		// Write the level data to the file.
		writeLevelToFile(level, levelFile);

		// Check the error status.
		boolean isFileSavingFailed = levelFile.checkError();

		// Close the file.
		levelFile.close();

		// Throw exception in the case of an error.
		if (isFileSavingFailed) {
			throw new IOException(Texts.getText("errorBySaving"));
		}
	}

	/**
	 * Returns the char code of the square at the specified location.
	 *  
	 * @param xPosition X coordinate of the square to return a char for
	 * @param yPosition Y coordinate of the square to return a char for
	 * @return			char code for the square, or a blank if the position is outside
	 *                  the implemented part of the board
	 */

	final public int getSquareCharacter(int xPosition, int yPosition) {

		// Select the line by the Y coordinate
		if (yPosition < 0 || yPosition >= boardData.size()) {
			return ' ';
		}
		final String line = boardData.get(yPosition);
		
		// The level lines need not be filled with spaces at the end.
		// Such a line may be shorter than the level width.
		if (xPosition < 0 || xPosition >= line.length()) {
			return ' ';
		}

		return line.charAt(xPosition);
	}

	/**
	 * Reload the data of this view from the database.
	 */

	protected void refreshView() {
		actionPerformed(new ActionEvent(this, 0, "refreshView"));
	}

		/**
		 * Return the ID of the stored string.
		 * 
		 * @return the ID
		 */

		public int getID() {
			return ID;
		}

	/**
	 * Adds all author names to the author <code>ComboxBox</code>es.
	 */

	protected void updateComboBoxAuthors() {
		
		// Update all needed ComoboBoxes of this view.
		super.updateComboBoxAuthors(comboBoxAuthors, selectionAuthor);
		
		// When adding new items the combo boxes should not fire actions.
		// (If this isn't set the combo boxes refresh the views every time their content changes)
		doNotFireActions = true;
		
		// The selection ComboBox's first item is always the wildcard "*".
		selectionAuthor.insertItemAt(new ComboBoxEntry("*", 0), 0);
		
		// Set the wildcard as selected.
		selectionAuthor.setSelectedIndex(0);
		
		// Actions may be fired again.
		doNotFireActions = false;
	}

	/**
     * Close this dialog and return to the caller of this dialog. 
     */

    protected void finalize() {
    	databaseViews.dispose();
    	databaseViews = null;
    }

    	/**
    	 * Called from the author view when the user has changed data
    	 * for the authors that is relevant for the other views.
    	 */

    	static public void authorsNamesChanged() {
    		for(int i=0; i<changeInAuthorView.length; i++) {
    			changeInAuthorView[i] = true;
    		}
    	}

    	/**
    	 * Called from collection view when the user has changed something
    	 * that is relevant for the other views.
    	 */

    	static public void collectionNamesChanged() {
    		for(int i=0; i<changeInCollectionView.length; i++) {
    			changeInCollectionView[i] = true;
    		}
    	}

    	/**
    	 * Called from level assignment view when the user has changed
    	 * something that is relevant for the other views. 
    	 */

    	static public void changeInAssignmentView() {
    		for(int i=0; i<changeInAssignmentView.length; i++) {
    			changeInAssignmentView[i] = true;
    		}
    	}

    	/**
    	 * Called from level view when the user has changed something that is
    	 * relevant for the other views.
    	 */

    	static public void changeInLevelView() {
    		for(int i=0; i<changeInLevelView.length; i++) {
    			changeInLevelView[i] = true;
    		}
    	}

    	/**
    	 * After a view has been refreshed the data is up-to-date again.
    	 * Hence all update flags for this view can be reset. 
    	 */

    	static public void resetUpdateFlags(int view) {
    		changeInAuthorView[view]     = false;
        	changeInCollectionView[view] = false;
        	changeInAssignmentView[view] = false;
        	changeInLevelView[view]      = false;
    	}

    	/**
    	 * Marks the passed view for being refreshed because data have changed. 
    	 */

    	static public void setUpdateFlags(int view) {
    		changeInAuthorView[view]     = true;
        	changeInCollectionView[view] = true;
        	changeInAssignmentView[view] = true;
        	changeInLevelView[view]      = true;
    	}

	/**
	 * This method is called whenever the additional information of a selected
	 * level has changed.
	 * (For example, when a solution has been deleted)
	 */

	protected void updateAdditionalInformation() {
		int firstSelectedRow = tableLevelData.convertRowIndexToModel(tableLevelData.getSelectionModel().getMinSelectionIndex());
		valueChanged(new ListSelectionEvent(tableLevelData.getSelectionModel(), firstSelectedRow, firstSelectedRow, false));
	}

	/**
	 * Returns the number of board positions in this hash table.
	 *
	 * @return  the number of board positions in this hash table.
	 */

	final public int getNumberOfStoredBoardPositions() {
		return count;
	}

	/**
	 * Clears the storage so that it contains no board positions anymore.
	 */

	final public void clear() {
		// This code is similar to hash table.clear()
		Entry tab[] = table;
		
		for (int index = tab.length; --index >= 0;) {
			tab[index] = null;
		}
		count = 0;
	}

		/**
		 * Returns the stored board positions.
		 * 
		 * @return	the board position that is stored in this entry.
		 */

		public Object getBoardPosition() {
			return boardPosition;
		}

	/**
	 * Returns the selected collection.
	 * 
	 * @return the currently selected collection item
	 */

	protected Object getSelectedCollection() {
		return selectionCollection.getSelectedItem();
	}

	/** This class has 4 bytes per atom */

	protected int bytesPerAtom() {
		return (4);
	}

	/** Any multiple of 4 will do, 72 might be common */

	protected int bytesPerLine() {
		return (72);
	}

		/**
		 * This method should return, if it knows, the number of bytes
		 * that will be decoded. Many formats such as uuencoding provide
		 * this information. By default we return the maximum bytes that
		 * could have been encoded on the line.
		 */

		protected int decodeLinePrefix(PushbackInputStream aStream, OutputStream bStream) throws IOException {
			return (bytesPerLine());
		}

		/**
		 * This method does an actual decode. It takes the decoded bytes and
		 * writes them to the OutputStream. The integer <i>l</i> tells the
		 * method how many bytes are required. This is always <= bytesPerAtom().
		 */

		protected void decodeAtom(PushbackInputStream aStream, OutputStream bStream, int l) throws IOException {
			throw new IOException();
		}

		/**
		 * Alternate decode interface that takes a String containing the encoded
		 * buffer and returns a byte array containing the data.
		 * @exception CEFormatException An error has occurred while decoding
		 */

		public byte decodeBuffer(String inputString)[] throws IOException {
			byte    inputBuffer[] = new byte[inputString.length()];
			ByteArrayInputStream inStream;
			ByteArrayOutputStream outStream;

			inputBuffer = inputString.getBytes();
			inStream = new ByteArrayInputStream(inputBuffer);
			outStream = new ByteArrayOutputStream();
			decodeBuffer(inStream, outStream);
			return (outStream.toByteArray());
		}

		/**
		 * Decode the contents of the inputstream into a buffer.
		 */

		public byte decodeBuffer(InputStream in)[] throws IOException {
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			decodeBuffer(in, outStream);
			return (outStream.toByteArray());
		}

	/**
	 * Sets the basic delay step to be used, in milliseconds.
	 * 
	 * @param msStep delay time in milliseconds
	 */

	public void setStep(int msStep) {
		stepMillis = msStep;
	}

	/**
	 * Returns the current delay time in milliseconds.
	 * 
	 * @return current delay time in milliseconds
	 */

	public int getStep() {
		return stepMillis;
	}

	/**
	 * Sets the intended sequence length of delay steps.
	 * Positive values are used to somewhat reduce the total time of the
	 * sequence by reducing the effective single step delay time.
	 * 
	 * @param len intended sequence length of delay steps
	 */

	public void setLength(int len) {
		intendedLength = len;
	}

	/**
	 * Returns the current intended sequence length of delay steps.
	 * @return the current intended sequence length of delay steps
	 */

	public int getLength() {
		return intendedLength;
	}

	/**
	 * Construct and return a new object for the standard step delay
	 * from the Settings.
	 * 
	 * @return new standard <code>Delays</code> object
	 */

	static public Delays makeDelayNormal() {
		return makeDelayNormal(0);
	}

	/**
	 * Construct and return a new object for the standard step delay
	 * from the Settings, and the indicated intended sequence length.
	 * 
	 * @param seqLength intended length of the sequence
	 * @return new standard <code>Delays</code> object
	 */

	static public Delays makeDelayNormal(int seqLength) {
		return new Delays(Settings.delayValue, seqLength);
	}

	/**
	 * Construct and return a new object for the undo/redo step delay
	 * from the Settings, and the indicated intended sequence length.
	 * 
	 * @return new undo/redo <code>Delays</code> object
	 */

	static public Delays makeDelayUndoRedo() {
		return makeDelayUndoRedo(0);
	}

	/**
	 * Handle save button action.
	 */

	private void saveButtonActionPerformed() {

		// Save properties to a file.
		saveProperties(newLanguageProperties, getFilepathFromLanguageCode(newLanguageCode));

		// Make new backup copy of properties.
		lastSavedNewLanguageProperties = (Properties) newLanguageProperties.clone();
	}

	/**
	 * Construct and return a new object for the undo/redo step delay
	 * from the Settings, and the indicated intended sequence length.
	 * 
	 * @param seqLength intended length of the sequence
	 * @return new undo/redo <code>Delays</code> object
	 */

	static public Delays makeDelayUndoRedo(int seqLength) {
		return new Delays(Settings.delayValueUndoRedo, seqLength);
	}

	/**
	 * Start the Delay object.  If not yet started, this determines and
	 * remembers "now" as the base time stamp for the next (first) delay.
	 */

	public void start() {
		if ( ! started ) {
			lastNow = System.currentTimeMillis();
			started = true;
		}
	}

	/**
	 * Stops the Delay object.
	 * This just invalidates the last remembered wall clock time.
	 */

	public void stop() {
		started = false;
	}

	/**
	 * Based of the mathematical value from {@link #effStep(boolean)}
	 * we compute a meaningful approximation,
	 * and return an <code>int</code> value.
	 * 
	 * @param slowstep whether this delay shall be extra large
	 * @return integral approximation of current delay in milliseconds
	 */

	private int effStepInt( boolean slowstep ) {
		float step = effStep(slowstep);
		int  istep = Math.round(step);
		
		// When we would tell a zero delay (or even less), but the original
		// intention was a positive delay, we shall return the smallest
		// possible positive value: 1.
		if( istep <= 0 ) {
			if( stepMillis > 0 ) {
				istep = 1;
			}
		}
		
		// Never consider negative values
		if( istep < 0 ) {
			istep = 0;
		}
		
		return istep;
	}

			/**
			 * Starts a new backward search.
			 */

			public void run() {

				try {
					// Start the backward search.
					backwardSearch();

				} catch (OutOfMemoryError e) {
					// Stop the optimizer by setting the proper stop reason.
					optimizerStatus = OptimizerStatus.STOPPED_DUE_TO_OUT_OF_MEMORY;
				}
			}

	/**
	 * The main method of this application.
	 * <p>
	 * 
	 * @param argv passed parameters
	 */

	static public void main(String[] argv) {

		// Check for debug parameters.
		for(String parameter : argv) {
			if(parameter.equalsIgnoreCase("-debug")) {
				Settings.isDebugModeActivated = true;	
			}
			if(parameter.equalsIgnoreCase("-debugSettings")) {
				Settings.isSettingsDebugModeActivated = true;
			}
			if(parameter.equalsIgnoreCase("-debugTiming")) {
				Settings.isTimingDebugModeActivated = true;
			}
		}
		
		// Save the information whether this program is started as "web start application".
		Settings.isStartedAsWebStartApplication =
					   (argv.length > 0 && argv[0].equals("-webstart"))
					|| (argv.length > 1 && argv[1].equals("-webstart"));
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new JSoko().startProgram();
			}
		});
	}

	/**
	 * We are going to wait somewhat to create an observable motion.
	 * If the wait amount is zero, we do not even call {@link Thread#sleep(long)}.
	 * 
	 * @param reassertIntr whether a possible <code>InterruptedException</code>
	 *                      is to be reasserted (<em>not</em> rethrown).
	 *                      Else it is ignored.
	 */

	public void sleep(boolean reassertIntr) {
		sleep(reassertIntr, false);
	}

	/**
	 * Decrypts special character regions of the ascii code.
	 * 
	 * @param c  <code>Character</code> to be decrypted
	 * @param start minimum ascii value to be used for decrypting
	 * @param end   maximum ascii value to be used for decrypting
	 * @param offset  offset the character has to be shifted by
	 * @return the decrypted <code>Character</code>
	 */

	private char decryptCharcode(int c, int start, int end, int offset) {
		
		c+=offset;
		if(offset > 0 && c > end){
			c= (start+(c-end-1));
		}
		else if(offset < 0 && c < start){
			c= (end-(start-c-1));
		}
		return (char) c;
	}

	/**
	 * Restores the last saved properties.
	 * 
	 * @param evt
	 */

	private void restoreButtonActionPerformed(ActionEvent evt) {
		newLanguageProperties = (Properties) lastSavedNewLanguageProperties.clone();

		loadLanguageTexts(NEW_LANGUAGE_COLUMN);
		refreshTranslationTextAreas();
	}

	/**
	 * Inform every listener about the change of the state.
	 */

	protected void stateChanged() {
		ChangeEvent e = new ChangeEvent(this);
		for (ChangeListener listener : changeListeners) {
			listener.stateChanged(e);
		}
	}

	/**
	 * Adds the passed listener.
	 * 
	 * @param listener  listener to be added
	 */

	public void addValueListener(ChangeListener listener) {
		changeListeners.add(listener);
	}

	/**
	 * Returns the value of the spinner as double.
	 * 
	 * @return the value
	 */

	public double getValueAsDouble() {
		Object currentValue = getValue();
		if (currentValue instanceof Double) {
			return (Double) currentValue;
		}
		if (currentValue instanceof Integer) {
			return ((Integer) currentValue).doubleValue();
		}

		return 0;
	}

	/**
	 * Returns the value of the spinner as integer.
	 * 
	 * @return the value
	 */

	public int getValueAsInteger() {
		Object currentValue = getValue();
		if (currentValue instanceof Double) {
			return ((Double) currentValue).intValue();
		}
		if (currentValue instanceof Integer) {
			return (Integer) currentValue;
		}

		return 0;
	}

	/**
	 * If the available memory (RAM) is less than this bound,
	 * then we are going to remove the eldest entry whenever we add a new one,
	 * i.e. we do not expand our memory foot print.
	 * <p>
	 * Negative values indicate "no such limit exists".
	 * 
	 * @param minRAMinMiB the minRAMinMiB to set
	 * @see Utilities#getMaxUsableRAMinMiB()
	 */

	public void setMinRAMinMiB(long minRAMinMiB) {
		this.minRAMinMiB = minRAMinMiB;
	}

	/**
	 * Inside this method (we override it) we implement our deletion strategy.
	 * Either we return {@code true}, indicating the caller shall delete
	 * that eldest entry, but in that case we are not allowed to change
	 * the object ourselves.
	 * <p>
	 * Or we decide to take our own steps, return {@code false} to hinder
	 * the caller to take any action, but may have removed one or more
	 * elements ourselves.
	 * 
	 * @param eldest   the currently eldest element, deletion candidate
	 * @return whether the caller shall really remove that eldest entry
	 */

	protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
		if (minRAMinMiB >= 0) {
			// We are limited...
			if (size() >= 2) {
				// We are large enough to loose an entry...
				if (Utilities.getMaxUsableRAMinMiB() < minRAMinMiB) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * This is like {@link LinkedHashMap#get(Object)}, but forces the
	 * correct type of the key.
	 * 
	 * @param key the key for which we search the mapped value
	 * @return the mapped value, or {@code null}.
	 */

	public V getV(K key) {
		return map.get(key);
	}

	/**
	 * Adds a key/value pair to the cache mapping.
	 * We expect the key to be new, but we do not enforce (or check) that,
	 * since the typical cache user will first use {@link #getV(Object)},
	 * anyhow.
	 * 
	 * @param key   key of the new cache entry
	 * @param value value of the new cache entry
	 */

	public void add(K key, V value) {
		map.put(key, value);
	}

	/**
	 * Trim down the memory usage to the currently needed amount.
	 * This is a user hint, and we need not really take any action,
	 * e.g. if the implementation does not know how to do that.
	 * @see ArrayList#trimToSize()
	 */

	public void trimToSize() {
		// we cannot do anything useful, here
	}

	/**
	 * Creation of a box data clone.
	 *
	 *@return  the cloned box data object
	 */

	final public Object clone() {
		return new BoxData(this);
	}

	/**
	 * Define the initial location (position) of a box.
	 *
	 *@param boxNo       number of the box, the location of which is to be set
	 *@param boxPosition position of the box
	 */

	final public void setBoxStartPosition(int boxNo, int boxPosition) {
		boxPositions[boxNo] = boxPosition;
	}

	/**
	 * Change the location (position) of a box.
	 *
	 *@param boxNo		 number of the box, the location of which is to be set
	 *@param boxPosition new position of the box
	 */

	final public void setBoxPosition(int boxNo, int boxPosition) {
		boxPositions[boxNo] = boxPosition;
	}

	/**
	 * Set / change the position of all boxes at once.
	 * The passed array often contains an additional player position
	 * (at the end of the array).  We don't care much, the array is
	 * just one larger than necessary.
	 *
	 *@param  newBoxPositions the new box positions to be set
	 */

	final public void setBoxPositions(int[] newBoxPositions) {

		// We must use a copy of the the array!
		// The caller does not donate this object, he will continue
		// to use and modify that array, while we expect to own this array.
		boxPositions = newBoxPositions.clone();
	}

	/**
	 * Activate a box.
	 *
	 *@param  boxNo number of the box that shall be activated
	 */

	final public void setBoxActive(int boxNo) {
		isBoxInactive[boxNo] = false;
	}

	/**
	 * Deactivate a box.
	 * Implies {@link #removeBoxFromCorral(int)}.
	 *
	 *@param  boxNo number of the box that shall be set inactive
	 */

	final public void setBoxInactive(int boxNo) {
		isBoxInactive[boxNo] = true;

		// An inactive box cannot be part of a corral
		isBoxInCorral[boxNo] = false;
	}

	/**
	 * Tell whether a box is active.
	 *
	 *@param  boxNo number of the box we want to investigate
	 *@return state of the box: <code>true</code> = is active
	 */

	final public boolean isBoxActive(int boxNo) {
		return isBoxInactive[boxNo] == false;
	}

	/**
	 * Tell whether a box is inactive.
	 *
	 *@param  boxNo number of the box we want to investigate
	 *@return state of the box: <code>true</code> = is inactive
	 */

	final public boolean isBoxInactive(int boxNo) {
		return isBoxInactive[boxNo];
	}

	/**
	 * Mark a specified box to be frozen.
	 * <p>
	 * Such a box can never again be moved (pushed), regardless all other
	 * possible changes on the board... except for an "undo" action.
	 * <p>
	 * Only boxes on goals are to be marked as frozen, as otherwise
	 * it would constitute a deadlock condition.
	 *
	 *@param  boxNo number of the box to be marked "frozen"
	 */

	final public void setBoxFrozen(int boxNo) {
		isBoxFrozen[boxNo] = true;
	}

	/**
	 * Sets a new level for playing.
	 * 
	 * @param levelNo
	 *            number of the level to set (first is 1)
	 */

	public void setLevelForPlaying(int levelNo) {
		setLevelForPlaying(currentLevelCollection.getLevel(levelNo));
	}

	/**
	 * Tell whether a box is frozen.
	 * A box can be frozen on a goal, only, since otherwise it would be
	 * a deadlock condition.
	 *
	 *@param  boxNo number of the box we want to investigate
	 *@return       whether the box is frozen
	 */

	final public boolean isBoxFrozen(int boxNo) {
		return isBoxFrozen[boxNo];
	}

	/**
	 * Mark a specified box to not be frozen, anymore
	 * This method is used for "undo" (on a frozen box).
	 *
	 *@param  boxNo number of the box to be unmarked
	 */

	final public void setBoxUnfrozen(int boxNo) {
		isBoxFrozen[boxNo] = false;
	}

	/**
	 * Mark a specified box to be part of a corral.
	 *
	 *@param  boxNo number of the box which is part of a corral
	 */

	final public void setBoxInCorral(int boxNo) {
		isBoxInCorral[boxNo] = true;
	}

	/**
	 * Mark a specified box to not be part of any corral.
	 *
	 *@param  boxNo number of the box which is not part of any corral
	 */

	final public void removeBoxFromCorral(int boxNo) {
		isBoxInCorral[boxNo] = false;
	}

	/**
	 * Tell whether a box is part of a corral.
	 *
	 *@param  boxNo number of the box we want to investigate
	 *@return <code>true</code> = box is part of a corral
	 */

	final public boolean isBoxInCorral(int boxNo) {
		return isBoxInCorral[boxNo];
	}

	/**
	 * Tell the location of a box by its number.
	 *
	 *@param  boxNo number of the box we want to investigate
	 *@return       position (location) of the box
	 */

	final public int getBoxPosition(int boxNo) {
		return boxPositions[boxNo];
	}

	/**
	 * Tells whether all our boxes are on some goal.
	 *
	 *@return <code>true</code> if all boxes are on a goal, and
	 *       <code>false</code> if at least one box is on a non-goal
	 */

	final public boolean isEveryBoxOnAGoal() {

		// Check all boxes for "on goal"
		for (int boxNo = 0; boxNo < boxCount; boxNo++) {
			// ignore deactivated boxes
			if (isBoxInactive(boxNo))
				continue;

			if (board.isBoxOnGoal(boxPositions[boxNo]) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns the number of boxes on a goal.
	 * 
	 * @return number of boxes on a goal
	 */

	public int getBoxesOnGoalsCount() {
		int boxesOnGoalsCount = 0;
		for(int boxNo=0; boxNo<boxCount; boxNo++) {
			// TODO: explain why inactive boxes are not ignored Answer: bad programming. this method
			// is only used when a new level is loaded and all boxes are active. Inactive boxes are
			// only there during deadlock detection. Nevertheless, I think it's better to check for 
			// inactive boxes here, too. I will have a look at this at some time. 
			if(board.isGoal(getBoxPosition(boxNo))) {
				boxesOnGoalsCount++;
			}
		}
		return boxesOnGoalsCount;
	}

	/**
	 * Returns whether all active boxes are located on a backward goal.
	 * 
	 * @return <code>true</code> if all active boxes are on backward goals, and
	 * <code>false</code> if at least one active box not on a backward goal.
	 */

	final public boolean isEveryBoxOnABackwardGoal() {

		int[] backwardGoalPositions = board.getGoalPositionsBackward();

		for (int boxNo = 0; boxNo < boxCount; boxNo++) {
			if (isBoxInactive(boxNo)) {
				continue;
			}
			if (board.isBox(backwardGoalPositions[boxNo]) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Tell whether all corral boxes are located on goals.
	 *
	 *@return <code>true</code> if all corral boxes are on goals, and
	 *		 <code>false</code> if at least one corral box is on a non-goal
	 */

	final public boolean isEveryCorralBoxOnAGoal() {

		// check all boxes
		for (int boxNo = 0; boxNo < boxCount; boxNo++) {
			// ignore deactivated and non-corral boxes
			if (isBoxInactive(boxNo) || isBoxInCorral(boxNo) == false) {
				continue;
			}
			if (board.isBoxOnGoal(boxPositions[boxNo]) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Mark all boxes as not frozen.
	 */

	final public void setAllBoxesNotFrozen() {
		for (int boxNo = 0; boxNo < boxCount; boxNo++) {
			isBoxFrozen[boxNo] = false;
		}
	}

	/**
	 * Tell the overall size of the large index byte array.
	 * Corresponds to the member of same name of array objects.
	 * @return size of the array
	 */

	public long length() {
		return size;
	}

	/**
	 * Reads the element value at the passed large index.
	 * Models the array read access.
	 * 
	 * @param lax large index of the array member
	 * @return value of the array member at the index {@code lax}
	 */

	public byte getAt(long lax) {
		if ((lax < 0) || (lax >= size)) {
			throw new ArrayIndexOutOfBoundsException("lax="+lax+",size="+size);
		}
		if (lax < blocksize) {
			// the simple case: one flat array
			return (arrvec[0][ (int)lax ]);
		}
		long blockno = lax / blocksize;
		long eleminx = lax % blocksize;
		return (arrvec[(int)blockno][(int)eleminx]);
	}

	/**
	 * Writes an element value at the passed large index.
	 * 
	 * @param lax large index of the array member
	 * @param val the value to store
	 * @return the just stored value
	 */

	public byte putAt(long lax, byte val) {
		if (lax < blocksize) {
			// the simple case: one flat array
			return (arrvec[0][ (int)lax ] = val);
		}
		long blockno = lax / blocksize;
		long eleminx = lax % blocksize;
		// FFS/hm: index out of range
		return (arrvec[(int)blockno][(int)eleminx] = val);
	}

	/**
	 * Modifies the byte at the specified large array index
	 * by "oring in" the passed byte value.
	 * Models the operation {@code (arr[lax] |= val)}.
	 * 
	 * @param lax large index of the array member to modify
	 * @param val the value to "or in"
	 * @return the resulting (stored) byte value
	 */

	public byte orAt(long lax, byte val) {
		if (lax < blocksize) {
			// the simple case: one flat array
			return (arrvec[0][ (int)lax ] |= val);
		}
		long blockno = lax / blocksize;
		long eleminx = lax % blocksize;
		// FFS/hm: index out of range
		return (arrvec[(int)blockno][(int)eleminx] |= val);
	}

	/**
	 * Fetch a bit sized fragment of a {@code long} from a contiguous series
	 * of bits from this "array".
	 * The result is presented in unsigned interpretation (except the
	 * {@code bitcnt} is 64).
	 * 
	 * @param bitlax index of the first bit (not byte) to fetch
	 * @param totbits number of bits to fetch (at most 64)
	 * @return the indicated bits as unsigned value
	 */

	public long getNumBitsAt( long bitlax, int totbits ) {
		if (totbits > 64 || totbits < 0) {
			throw new java.lang.UnsupportedOperationException("bits="+totbits);
		}
		// asserted: 0 <= totbits <= 64
		long result = 0;
		
		int  resoff = 0;
		while (totbits > resoff) {
			int  toget  = totbits - resoff;
			byte bitoff = (byte)(bitlax & 0x07);	// [0..7]
			long lax    = bitlax >>> 3;

			int bitlen = 8 - bitoff;		// so many in this byte [1..8]
			if (bitlen > toget) {
				bitlen = toget;
			}
			long v    = getAt(lax) & 0xffL;
			v      >>>= bitoff;
			v        &= (1L << bitlen) - 1;

			result |= (v << resoff);
			resoff += bitlen;
			bitlax += bitlen;
		}
		return result;
	}

	/**
	 * Shows dialog which informs about missing file.
	 * <p>
	 * If error is critical (it is when <code>messageType</code> equals
	 * <code>JOptionPane.ERROR_MESSAGE</code> then application is closed.
	 *
	 * @param filePath	path of the missing file
	 * @param messageType	<code>JOptionPane.WARNING_MESSAGE</code> if missing file is non-critical;
	 *						<code>JOptionPane.ERROR_MESSAGE</code> if missing file is critical
	 */

	public void showMissingFileDialog(String filePath, int messageType) {
		JOptionPane.showMessageDialog(
				parent,
				"File: " + filePath + " is missing!",
				"Error",
				messageType);

		if (messageType == JOptionPane.ERROR_MESSAGE) {
			setBlankProject();
		}
	}

	/**
	 * Tells, whether the SparseArray does not contain any non-{@code null}
	 * element.
	 * <p>
	 * This is also part of the {@link Map} interface.
	 * 
	 * @return whether the SparseArray is empty
	 */

	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Removes all entries from the object.
	 * <p>
	 * This method is also part of the map interface.
	 */

	public void clear() {
		this.root      = null;
		this.elemcount = 0;
	}

	/**
	 * Load properties from a file.
	 *
	 * @param fileName	name of the file
	 * @return	<code>Properties</code> variable containing loaded properties
	 */

	private Properties loadPropertiesByFilename(String fileName) throws IOException {

		// Create new properties.
		Properties tempProp = new Properties();

		// Get input stream to the property file.
		InputStream inPropFile = Utilities.getInputStream(fileName);
		
		try {
			// Load properties.
			tempProp.load(inPropFile);
			
			// Close file.
			inPropFile.close();
		}
		catch(Exception e) {
			throw new IOException(e.getLocalizedMessage());
		}

		// Return the read properties.
		return tempProp;

	}

	/**
	 * Load the properties corresponding to the passed language code.
	 *
	 * @param languageCode code of the language (example: "en")
	 * @return	<code>Properties</code> variable containing loaded properties
	 */

	private Properties loadPropertiesByLanguageCode(String languageCode) throws IOException {

		// Return the read properties.
		return loadPropertiesByFilename(getFilepathFromLanguageCode(languageCode));

	}

	/**
	 * Read the array element at the specified index.
	 * 
	 * @param inx indexes the (sparse) array
	 * @return {@code null}, or the element at index {@code inx}
	 */

	public V rd(long inx) {
		return rd(root, MAX_DEP, inx);
	}

	/**
	 * This is a helper method for {@link #wr(Node, byte, long, Object)}.
	 * We store a new reference where we fetched the last {@code Node}
	 * reference during tree path scanning: at {@code Node pa}
	 * with index {@code painx}.
	 * If {@code pa} is {@code null}, we must have fetched from the
	 * {@link #root} and thus we here store to it.
	 * 
	 * @param pa    the node to which we store a new reference, or {@code null}
	 * @param painx the index of the store
	 * @param nref  the new reference to store
	 */

	private void paput(Inner<V> pa, byte painx, Node<V> nref) {
		if (pa == null) {
			root = nref;
		} else {
			// Store leaf where we found the null in the last step
			pa.iput(painx, nref);
		}
	}

	/**
	 * Reloads all non-static GUI components.
	 */

	private void reloadGUI() {
		changeLanguageComboBoxes();

		loadLanguageTexts(NEW_LANGUAGE_COLUMN);
		loadLanguageTexts(HINT_LANGUAGE_COLUMN);

		// Set new and hint language combo boxes for default values.
		String language = (new Locale(Settings.get("newTranslationLanguage"))).getDisplayLanguage(getUserLocale());

		newLanguageComboBox.setSelectedItem(language);

		language = (new Locale(Settings.get("hintTranslationLanguage"))).getDisplayLanguage(getUserLocale());
		hintLanguageComboBox.setSelectedItem(language);

		// By default select the first row of the table.
		if (table.getRowCount() > 0) {
			table.changeSelection(0, NEW_LANGUAGE_COLUMN, false, false);
		}
	}

	/**
	 * Redraws the screen and waits for "enter" if needed.
	 * 
	 * @param waitForEnter  whether the method is to wait for enter after drawing
	 */

	public void redraw(boolean waitForEnter) {

		// Draw the new GUI.
		applicationGUI.mainBoardDisplay.repaint();

		// For debugging purposes wait for "Enter"
		if (waitForEnter) {
			JDialog dialog = new JOptionPane("").createDialog(null, "Waiting for Enter");
			dialog.setLocation(getX() + getWidth() + 45, getY() + 10);
			dialog.setVisible(true);
			// Beginners explanation: The above dialog is "modal", and hence freezes
			// all other graphics activity.  That way "we wait" by being frozen.
			// When "setVisible(true)" returns, the dialog is done!
		}
	}

	/**
	 * Stores the specified value into the array slot with the specified index,
	 * and returns the former value of that array slot.
	 * <p>
	 * Storing a {@code null} value means to remove any former value
	 * from the indexed array slot.
	 * 
	 * @param inx the index of the array slot to write to
	 * @param val the value to store at index {@code inx}
	 * @return the former content of the array slot at index {@code inx}
	 */

	public V wr(long inx, V val) {
		return wr(root, MAX_DEP, inx, val);
	}

	/**
	 * Removes a box from the passed position.
	 * 
	 * @param position the position a box is to be removed from.
	 */

	final public void removeBox(int position) {
		boxesArray[position] = false;
	}

	/**
	 * Debug: Calculates and displays the lower bound for all levels.
	 */

	private void calculateLowerboundOfAllLevel() {

		final int maxLevelNo = currentLevelCollection.getNumberOfLevels();
		int[] lowerbounds = new int[1 + maxLevelNo];

		for (int levelNo = 1; levelNo <= maxLevelNo; levelNo++) {
			setLevelForPlaying(levelNo);
			lowerbounds[levelNo] = board.lowerbound.calculateLowerbound(SearchDirection.FORWARD);
		}

		for (int levelNo = 1; levelNo <= maxLevelNo; levelNo++) {
			System.out.printf("Level: %3d  Name: %-60s Lowerbound: %3d \n",
					levelNo, currentLevelCollection.getLevel(levelNo).getTitle(), lowerbounds[levelNo]);
		}
	}

			/**
			 * Starts a new forward search.
			 */

			public void run() {

				try {
					// Start a new forward search.
					forwardSearch();

				} catch (OutOfMemoryError e) {
					// Stop the optimizer by setting the proper stop reason.
					optimizerStatus = OptimizerStatus.STOPPED_DUE_TO_OUT_OF_MEMORY;
				}

			}

	/**
	 * Removes the box having the passed number.
	 * 
	 * @param boxNo the number of the box that is to be removed.
	 */

	final public void removeBoxByNumber(int boxNo) {
		boxesArray[boxData.getBoxPosition(boxNo)] = false;
	}

	/**
	 * Returns whether the game is in play mode, just now.
	 * 
	 * @return <code>true</code>, if the game is in play mode
	 */

	public boolean isPlayModeActivated() {
		return gameMode == GameMode.PLAY;
	}

	/**
	 * Removes a box from the passed position.
	 * The arguments are not checked against the board dimensions.
	 * 
	 * @param x	the x-coordinate of the position the box is to be removed from.
	 * @param y the y-coordinate of the position the box is to be removed from.
	 */

	final public void removeBox(int x, int y) {
		boxesArray[x + width * y] = false;
	}

	/**
	 * Returns whether the game is in editor mode, just now.
	 * 
	 * @return <code>true</code>, iff the game is in editor mode
	 */

	public boolean isEditorModeActivated() {
		return gameMode == GameMode.EDITOR;
	}

	/**
	 * Removes a wall from the passed position.
	 * 
	 * @param position the position a wall is to be removed from.
	 */

	final public void removeWall(int position) {
		wallsArray[position] -= ((wallsArray[position] > 0) ? 1 : 0);
	}

	/**
	 * Sets the "invalid level" mode. The current level is invalid. Therefore
	 * the user isn't allowed to play it. Furthermore the solver and the optimizer are disabled.
	 */

	private void setInvalidLevelMode() {
		applicationGUI.setInvalidLevelModeDependentObjectsEnabled(false);
		gameMode = GameMode.INVALID_LEVEL;
	}

	/**
	 * Removes a wall from the passed position.
	 * The arguments are not checked against the board dimensions.
	 * 
	 * @param x	the x-coordinate of the position the wall is to be removed from.
	 * @param y the y-coordinate of the position the wall is to be removed from.
	 */

	final public void removeWall(int x, int y) {
		wallsArray[x + width * y] -= ((wallsArray[x + width * y] > 0) ? 1 : 0);
	}

	/**
	 * Removes a goal from the passed position.
	 * 
	 * @param position the position a goal is to be removed from.
	 */

	final public void removeGoal(int position) {
		goalsArray[position] = false;
	}

	/**
	 * Removes a goal from the passed position.
	 * The arguments are not checked against the board dimensions.
	 * 
	 * @param x	the x-coordinate of the position the goal is to be removed from.
	 * @param y the y-coordinate of the position the goal is to be removed from.
	 */

	final public void removeGoal(int x, int y) {
		goalsArray[x + width * y] = false;
	}

	/**
	 * Determines the first key associated with a non-{@code null} value.
	 * Sorting order is native: signed.
	 * @return the first key in the sparse array
	 * @see SortedMap#firstKey()
	 */

	public long firstInx() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		ArrEntry<V> e = findFiLa(true, true, null);
		return e.inx;
	}

	/**
	 * Determines the last key associated with a non-{@code null} value.
	 * Sorting order is native: signed.
	 * @return the last key in the sparse array
	 * @see SortedMap#lastKey()
	 */

	public long lastInx() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		ArrEntry<V> e = findFiLa(false, true, null);
		return e.inx;
	}

	/**
	 * Removes the player from the board.
	 */

	final public void removePlayer() {
		playerPosition = NO_PLAYER;
	}

	/**
	 * Sets a box at the passed position.
	 * 
	 * @param position the position a box is to be set.
	 */

	final public void setBox(int position) {
		boxesArray[position] = true;
	}

    /**
     * Method that is backed to a submit button of a form.
     */

    public String send(){
        //do real logic
        return ("success");
    }

	/**
	 * Sets a box at the passed position.
	 * The arguments are not checked against the board dimensions.
	 * 
	 * @param x the x-coordinate of the position a box is to be set.
	 * @param y the y-coordinate of the position a box is to be set.
	 */

	final public void setBox(int x, int y) {
		boxesArray[x + width * y] = true;
	}

	/**
	 * Generate an id for a new lock. Uniqueness per cache instance is very 
	 * desirable but not absolutely critical. Must be called from one of the 
	 * synchronized methods of this class.
	 */

	private int nextLockId() {
		if (nextLockId==Integer.MAX_VALUE) nextLockId = Integer.MIN_VALUE;
		return nextLockId++;
	}

	/**
	 * decrement a lock and put it back in the cache
	 */

	private void decrementLock(Object key, Lock lock) throws CacheException {
		//decrement the lock
		lock.unlock( cache.nextTimestamp() );
		if ( cache instanceof ClusterCache)
			((ClusterCache)cache).putQuiet(key, lock);
		else
			cache.put(key, lock);
	}

    /**
     * Gets a value of an element which matches the given key.
     * @param key the key of the element to return.
     * @return The value placed into the cache with an earlier put, or null if not found or expired
     * @throws CacheException
     */

    public Object get(Object key) throws CacheException {
        try {
            if ( log.isDebugEnabled() ) {
                log.debug("key: " + key);
            }
            if (key == null) {
                return null;
            } 
            else {
                Element element = cache.get( (Serializable) key );
                if (element == null) {
                    if ( log.isDebugEnabled() ) {
                        log.debug("Element for " + key + " is null");
                    }
                    return null;
                } 
                else {
                    return element.getValue();
                }
            }
        } 
        catch (net.sf.ehcache.CacheException e) {
            throw new CacheException(e);
        }
    }

	/**
	 * Sets a box with the passed number at the passed position.
	 * 
	 * @param position the position a box is to be set.	 
	 * @param boxNo	the number of the box to be set
	 */

	final public void setBoxWithNo(int boxNo, int position) {
		boxesArray[position] = true;
		boxNumbers[position] = boxNo;
	}

    /**
     * Puts an object into the cache.
     * @param key a {@link Serializable} key
     * @param value a {@link Serializable} value
     * @throws CacheException if the parameters are not {@link Serializable}, the {@link CacheManager}
     * is shutdown or another {@link Exception} occurs.
     */

    public void put(Object key, Object value) throws CacheException {
        try {
            Element element = new Element( (Serializable) key, (Serializable) value );
            cache.put(element);
        } 
        catch (IllegalArgumentException e) {
            throw new CacheException(e);
        } 
        catch (IllegalStateException e) {
            throw new CacheException(e);
        }

    }

    /**
     * Removes the element which matches the key.
     * <p>
     * If no element matches, nothing is removed and no Exception is thrown.
     * @param key the key of the element to remove
     * @throws CacheException
     */

    public void remove(Object key) throws CacheException {
        try {
            cache.remove( (Serializable) key );
        } 
        catch (ClassCastException e) {
            throw new CacheException(e);
        } 
        catch (IllegalStateException e) {
            throw new CacheException(e);
        }
    }

    /**
     * Remove all elements in the cache, but leave the cache
     * in a useable state.
     * @throws CacheException
     */

    public void clear() throws CacheException {
        try {
            cache.removeAll();
        } 
        catch (IllegalStateException e) {
            throw new CacheException(e);
        } 
        catch (IOException e) {
            throw new CacheException(e);
        }
    }

    /**
     * Remove the cache and make it unuseable.
     * @throws CacheException
     */

    public void destroy() throws CacheException {
        try {
            CacheManager.getInstance().removeCache( cache.getName() );
        } 
        catch (IllegalStateException e) {
            throw new CacheException(e);
        } 
        catch (net.sf.ehcache.CacheException e) {
            throw new CacheException(e);
        }
    }

    /**
     * Calls to this method should perform there own synchronization.
     * It is provided for distributed caches. Because EHCache is not distributed
     * this method does nothing.
     */

    public void lock(Object key) throws CacheException {
    }

    /**
     * Calls to this method should perform there own synchronization.
     * It is provided for distributed caches. Because EHCache is not distributed
     * this method does nothing.
     */

    public void unlock(Object key) throws CacheException {
    }

	/**
	 * Enables or disables the buttons for "undo" and "redo" by inspection of
	 * the history, i.e. whether there is something to undo or redo.
	 */

	private void setUndoRedoFromHistory() {
		applicationGUI.setUndoButtonsEnabled(movementHistory.hasPrecedingMovement());
		applicationGUI.setRedoButtonsEnabled(movementHistory.hasSuccessorMovement());
	}

		/**
		 * The timestamp on the cached data
		 */

		public long getFreshTimestamp() {
			return freshTimestamp;
		}

    /**
     * Gets the next timestamp;
     */

    public long nextTimestamp() {
        return Timestamper.next();
    }

    /**
     * Returns the lock timeout for this cache.
     */

    public int getTimeout() {
        // 60 second lock timeout
        return Timestamper.ONE_MS * 60000;
    }

		/**
		 * The actual cached data
		 */

		public Object getValue() {
			return value;
		}

		/**
		 * Lock the item
		 */

		public Lock lock(long timeout, int id) {
			return new Lock(timeout, id, version);
		}

		/**
		 * Not a lock!
		 */

		public boolean isLock() {
			return false;
		}

		/**
		 * Is this item visible to the timestamped
		 * transaction?
		 */

		public boolean isGettable(long txTimestamp) {
			return freshTimestamp < txTimestamp;
		}

		/**
		 * Don't overwite already cached items
		 */

		public boolean isPuttable(long txTimestamp, Object newVersion, Comparator comparator) {
			// we really could refresh the item if it  
			// is not a lock, but it might be slower
			//return freshTimestamp < txTimestamp
			return  ( version!=null && newVersion!=null)&& comparator.compare(version, newVersion) < 0;
		}

	/**
	 * Sets a box with the passed number at the passed position.
	 * The arguments are not checked against the board dimensions.
	 * 
	 * @param x the x-coordinate of the position a box is to be set.
	 * @param y the y-coordinate of the position a box is to be set.
	 * @param boxNo	the number of the box to be set
	 */

	final public void setBoxWithNo(int boxNo, int x, int y) {
		boxesArray[x + width * y] = true;
		boxNumbers[x + width * y] = boxNo;
	}

	/**
	 * Check if this lock mode is more restrictive than the given lock mode.
	 *
	 * @param mode LockMode to check
	 * @return true if this lock mode is more restrictive than given lock mode
	 */

	public boolean greaterThan(LockMode mode) {
		return level > mode.level;
	}

	/**
	 * Check if this lock mode is less restrictive than the given lock mode.
	 *
	 * @param mode LockMode to check
	 * @return true if this lock mode is less restrictive than given lock mode
	 */

	public boolean lessThan(LockMode mode) {
		return level < mode.level;
	}

		/**
		 * Increment the lock, setting the
		 * new lock timeout
		 */

		public Lock lock(long timeout, int id) {
			concurrentLock = true;
			multiplicity++;
			this.timeout = timeout;
			return this;
		}

		/**
		 * Decrement the lock, setting the unlock
		 * timestamp if now unlocked
		 * @param currentTimestamp
		 */

		public void unlock(long currentTimestamp) {
			if ( --multiplicity == 0 ) {
				unlockTimestamp = currentTimestamp;
			}
		}

		/**
		 * Can the timestamped transaction re-cache this
		 * locked item now?
		 */

		public boolean isPuttable(long txTimestamp, Object newVersion, Comparator comparator) {
			if (timeout < txTimestamp) return true;
			if (multiplicity>0) return false;
			return version==null || newVersion==null? 
				unlockTimestamp < txTimestamp :
				comparator.compare(version, newVersion) < 0; //by requiring <, we rely on lock timeout in the case of an unsuccessful update!
		}

		/**
		 * Was this lock held concurrently by multiple
		 * transactions?
		 */

		public boolean wasLockedConcurrently() {
			return concurrentLock;
		}

		/**
		 * Yes, this is a lock
		 */

		public boolean isLock() {
			return true;
		}

		/**
		 * locks are not returned to the client!
		 */

		public boolean isGettable(long txTimestamp) {
			return false;
		}

	/**
	 * Sets a box and a goal at the passed position.
	 * 
	 * @param position the position the objects are to be set.
	 */

	final public void setBoxOnGoal(int position) {
		boxesArray[position] = true;
		goalsArray[position] = true;
	}

	/**
     * Defines the mapper to map source to destination files.
     */

    public Mapper createMapper() throws BuildException {
        if (_mapper != null) {
            throw new BuildException("Cannot define more than one mapper");
        }
        _mapper = new Mapper(this.getProject());
        return _mapper;
    }

	/**
	 * Copies the data of the current level to the clipboard, optionally
	 * using run length encoding (RLE).
	 * 
	 * @param withRLE whether to use RLE
	 * @see #exportLevelToClipboard(boolean, boolean)
	 */

	private void exportLevelToClipboard(boolean withRLE) {
		exportLevelToClipboard(withRLE, false);
	}

	/**
	 * Sets a box and a goal at the passed position.
	 * The arguments are not checked against the board dimensions.
	 * 
	 * @param x the x-coordinate of the position the objects are to be set.
	 * @param y the y-coordinate of the position the objects are to be set.
	 */

	final public void setBoxOnGoal(int x, int y) {
		boxesArray[x + width * y] = true;
		goalsArray[x + width * y] = true;
	}

	/**
	 * Sets a a goal at the passed position.
	 * 
	 * @param position the position the goal is to be set.
	 */

	final public void setGoal(int position) {
		goalsArray[position] = true;
	}

	/**
	 * Sets a goal at the passed position.
	 * The arguments are not checked against the board dimensions.
	 * 
	 * @param x the x-coordinate of the position a goal is to be set.
	 * @param y the y-coordinate of the position a goal is to be set.
	 */

	final public void setGoal(int x, int y) {
		goalsArray[x + width * y] = true;
	}

	/**
	 * Sets a wall at the passed position.
	 * 
	 * @param position the position the wall is to be set.
	 */

	final public void setWall(int position) {
		wallsArray[position]++;
	}

	/**
	 * Sets a wall at the passed position.
	 * The arguments are not checked against the board dimensions.
	 * 
	 * @param x the x-coordinate of the position a wall is to be set.
	 * @param y the y-coordinate of the position a wall is to be set.
	 */

	final public void setWall(int x, int y) {
		wallsArray[x + width * y]++;
	}

	/**
	 * Sets the number of a box at the passed position.
	 * 
	 * @param boxNo	the box number to be set
	 * @param position the position the box number is to be set.
	 */

	final public void setBoxNo(int boxNo, int position) {
		boxNumbers[position] = boxNo;
	}

	/**
	 * Sets the number of a box at the passed position.
	 * The arguments are not checked against the board dimensions.
	 * 
	 * @param boxNo	the box number to be set
	 * @param x the x-coordinate of the position the box number is to be set.
	 * @param y the y-coordinate of the position the box number is to be set.
	 */

	final public void setBoxNo(int boxNo, int x, int y) {
		boxNumbers[x + width * y] = boxNo;
	}

	/**
	 * Sets the square at the the passed position to be an advanced deadlock
	 * square.
	 * 
	 * @param position the position of the square
	 */

	final public void setAdvancedSimpleDeadlock(int position) {
		advancedSimpleDeadlockSquareForwards[position] = true;
	}

	/**
	 * Sets the player to the passed position.
	 * 
	 * @param position Position the player is to be set at.
	 */

	final public void setPlayerPosition(int position) {
		playerPosition = position;
	}

	/**
	 * Sets the player to the passed position.
	 * The arguments are not checked against the board dimensions.
	 * 
	 * @param x the x-coordinate of the position the player is to be set at.
	 * @param y the y-coordinate of the position the player is to be set at.
	 */

	final public void setPlayerPosition(int x, int y) {
		playerPosition = x + width * y;
	}

	/**
	 * Returns whether the square at the the passed position is a corral
	 * forcer square.
	 * 
	 * @param position the position of the square
	 * @return <code>true</code> if there is a corral forcer square at the
	 *                           passed position, or<br>
	 * 		  <code>false</code> if there isn't a corral forcer square at the
	 *                           passed position
	 */

	final public boolean isCorralForcerSquare(int position) {
		return corralForcer[position];
	}

	/**
	 * Returns whether the square at the the passed position is a corral
	 * forcer square.
	 * The arguments are not checked against the board dimensions.
	 * 
	 * @param x the x-coordinate of the position
	 * @param y the y-coordinate of the position
	 * @return <code>true</code> if there is a corral forcer square at the
	 *                           passed position, or<br>
	 * 		  <code>false</code> if there isn't a corral forcer square at the
	 *                           passed position
	 */

	final public boolean isCorralForcerSquare(int x, int y) {
		return corralForcer[x + width * y];
	}

	/**
	 * Returns whether there is a box at the passed position.
	 * 
	 * @param position the position of the square
	 * @return <code>true</code> if there is a box at the passed position,
	 * or<br> <code>false</code> if there isn't a box at the passed position
	 */

	final public boolean isBox(int position) {
		return boxesArray[position];
	}

	/**
	 * Returns whether there is a box at the passed position.
	 * The arguments are not checked against the board dimensions.
	 * 
	 * @param x the x-coordinate of the position
	 * @param y the y-coordinate of the position
	 * @return <code>true</code> if there is a box at the passed position,
	 * or<br> <code>false</code> if there isn't a box at the passed position
	 */

	final public boolean isBox(int x, int y) {
		return boxesArray[x + width * y];
	}

	/**
	 * Returns whether there is a wall at the passed position.
	 * 
	 * @param position the position of the square
	 * @return <code>true</code> if there is a wall at the passed position,
	 * or<br> <code>false</code> if there isn't a wall at the passed position
	 */

	final public boolean isWall(int position) {
		return wallsArray[position] > 0;
	}

	/**
	 * Returns if a box has been selected.
	 * 
	 * @return <code>true</code> a box has been selected <code>false</code> no box
	 *         has been selected
	 */

	public boolean isABoxSelected() {
		return isABoxSelected;
	}

	/**
	 * Returns whether there is a wall at the passed position.
	 * The arguments are not checked against the board dimensions.
	 * 
	 * @param x the x-coordinate of the position
	 * @param y the y-coordinate of the position
	 * @return <code>true</code> if there is a wall at the passed position,
	 * or<br> <code>false</code> if there isn't a wall at the passed position
	 */

	final public boolean isWall(int x, int y) {
		return wallsArray[x + width * y] > 0;
	}

	/**
	 * Returns whether the player reachable squares are to be highlighted.
	 * 
	 * @return <code>true</code> if the player reachable squares are to be highlighted,<br>
	 * 	      <code>false</code> otherwise
	 */

	public boolean isHighLightingOfPlayerReachableSquaresActivated() {
		return isHighLightOfPlayerSquaresActivated;
	}

	/**
	 * The location of ant-installer.jar and sysout.jar and possibly jgoodies-edited-1_2_2.jar
	 * @param antInstallLib The antInstallLib to set.
	 */

	public void setAntInstallLib(File antInstallLib) {
		this.antInstallLib = antInstallLib;
		FileSet set = new FileSet();
		set.setFile(new File(antInstallLib,"tikal-antinstaller.jar"));
		set.setFile(new File(antInstallLib,"sysout.jar"));
		addZipGroupFileset(set);
	}

	/**
	 * The location of ant.jar and ant-launcher.jar
	 * @param antLib The antLib to set.
	 */

	public void setAntLib(File antLib) {
		this.antLib = antLib;
		FileSet set = new FileSet();
		set.setFile(new File(antLib,"tikal-ant.jar"));
		set.setFile(new File(antLib,"ant-launcher.jar"));
		addZipGroupFileset(set);
	}

	/**
	 * Returns whether there is a goal at the passed position.
	 * 
	 * @param position the position of the square
	 * @return <code>true</code> if there is a goal at the passed position,
	 * or<br> <code>false</code> if there isn't a goal at the passed position
	 */

	final public boolean isGoal(int position) {
		return goalsArray[position];
	}

	/**
	 * Returns the position of the selected box.
	 * 
	 * @return the position of the selected box
	 */

	public int getSelectedBoxPosition() {
		return selectedBoxPosition;
	}

	/**
	 * Returns whether there is a goal at the passed position.
	 * The arguments are not checked against the board dimensions.
	 * 
	 * @param x the x-coordinate of the position
	 * @param y the y-coordinate of the position
	 * @return <code>true</code> if there is a goal at the passed position,
	 * or<br> <code>false</code> if there isn't a goal at the passed position
	 */

	final public boolean isGoal(int x, int y) {
		return goalsArray[x + width * y];
	}

	/**
	 * Returns whether the square at the passed position is either a goal
	 * or a wall.
	 * 
	 * @param position  the position of the square
	 * @return <code>true</code> if the square is a goal or a wall, or<br>
	 * 		  <code>false</code> otherwise
	 */	

	public boolean isGoalOrWall(int position) {
		return goalsArray[position] || wallsArray[position] > 0;
	}

	/**
	 * Plays the next movements from the history movements, in an own thread,
	 * in order to avoid blocking the event dispatcher thread (EDT).
	 * 
	 * @param redoAllMovements whether all movements have to be redone
	 */

	private void redoMovementInOwnThread(final boolean redoAllMovements) {
		
		movePlayerThread = new Thread() {
			public void run() {
				redoMovement(redoAllMovements);

				// The "setUndoRedoFromHistory()" has happened already.

				// This thread has finished its work. The thread isn't needed any more.
				movePlayerThread = null;
			}
		};
		movePlayerThread.start();
	}

	/**
	 * Returns whether there is a corral forcer at the passed position.
	 * A corral forcer divides the board into areas the player can reach
	 * and can't reach.
	 * 
	 * @param position the position of the square
	 * @return <code>true</code> if there is a corral forcer at the position,
	 * or<br> <code>false</code> if there isn't a corral forcer at the position 
	 */

	final public boolean isCorralForcer(int position) {
		return corralForcer[position] == true;
	}

    /**
     * Returns a list of all elements in the cache. Only keys of non-expired
     * elements are returned.
     * <p/>
     * The returned keys are unique and can be considered a set.
     * <p/>
     * The List returned is not live. It is a copy.
     * <p/>
     * The time taken is O(n), where n is the number of elements in the cache. On
     * a 1.8Ghz P4, the time taken is approximately 200ms per 1000 entries. This method
     * is not syncrhonized, because it relies on a non-live list returned from {@link #getKeys()}
     * , which is synchronised, and which takes 8ms per 1000 entries. This way
     * cache liveness is preserved, even if this method is very slow to return.
     * <p/>
     * Consider whether your usage requires checking for expired keys. Because
     * this method takes so long, depending on cache settings, the list could be
     * quite out of date by the time you get it.
     *
     * @return a list of {@link Serializable} keys
     * @throws IllegalStateException if the cache is not {@link Status#STATUS_ALIVE}
     */

    public List getKeysWithExpiryCheck() throws IllegalStateException, CacheException {
        List allKeyList = getKeys();
        //remove keys of expired elements
        ArrayList nonExpiredKeys = new ArrayList(allKeyList.size());
        int allKeyListSize = allKeyList.size();
        for (int i = 0; i < allKeyListSize; i++) {
            Serializable key = (Serializable) allKeyList.get(i);
            Element element = getQuiet(key);
            if (element != null) {
                nonExpiredKeys.add(key);
            }
        }
        nonExpiredKeys.trimToSize();
        return nonExpiredKeys;
    }

	/**
	 * Returns whether there is a backward search goal at the passed position.
	 * The backward search goals are the positions of the boxes
	 * at the beginning of a level.
	 * 
	 * @param position the position of the square
	 * @return <code>true</code> if there is a backward search goal
	 *                           at the passed position, or<br>
	 * 		  <code>false</code> if there isn't a backward search goal
	 *                           at the passed position
	 */

	final public boolean isGoalBackwardsSearch(int position) {
		return goalSquareBackwardsSearch[position];
	}

    /**
     * Returns the number of elements in the memory store.
     *
     * @throws IllegalStateException if the cache is not {@link Status#STATUS_ALIVE}
     */

    public long getMemoryStoreSize() throws IllegalStateException {
        checkStatus();
        return memoryStore.getSize();
    }

    /**
     * Returns the number of elements in the disk store.
     *
     * @throws IllegalStateException if the cache is not {@link Status#STATUS_ALIVE}
     */

    public int getDiskStoreSize() throws IllegalStateException {
        checkStatus();
        if (overflowToDisk) {
            return diskStore.getSize();
        } else {
            return 0;
        }
    }

	/**
	 * Returns whether there is a backward search goal at the passed position.
	 * The backward search goals are the positions of the boxes
	 * at the beginning of a level.
	 * The arguments are not checked against the board dimensions.
	 * 
	 * @param x the x-coordinate of the position
	 * @param y the y-coordinate of the position
	 * @return <code>true</code> if there is a backward search goal
	 *                           at the passed position, or<br>
	 * 		  <code>false</code> if there isn't a backward search goal
	 *                           at the passed position
	 */

	final public boolean isGoalBackwardsSearch(int x, int y) {
		return goalSquareBackwardsSearch[x + width * y];
	}

    /**
     * Gets the status attribute of the Cache
     *
     * @return The status value from the Status enum class
     */

    public Status getStatus() {
        return status;
    }

	/**
	 * Undoes the last movement in an own thread,
	 * in order to avoid blocking the event dispatcher thread (EDT).
	 */

	private void undoMovementInOwnThread() {

		movePlayerThread = new Thread() {
			@SuppressWarnings("synthetic-access")
			public void run() {
				undoMovement();

				// Enable / disable the undo / redo button depending on the history status.
				setUndoRedoFromHistory();			}
		};
		movePlayerThread.start();
	}

    /**
     * Receive a Locator object for document events.
     */

    public void setDocumentLocator(Locator locator) {
        this.locator = locator;
    }

    /**
     * Number of times a requested item was found in the Memory Store
     *
     * @throws IllegalStateException if the cache is not {@link Status#STATUS_ALIVE}
     */

    public int getMemoryStoreHitCount() throws IllegalStateException {
        checkStatus();
        return memoryStoreHitCount;
    }

	/**
	 * Returns whether there is an empty square at the passed position.
	 * NB: a goal is <em>not</em> considered to be empty.
	 * 
	 * @param position the position of the square
	 * @return <code>true</code> if there is an empty square
	 *                           at the passed position, or<br>
	 * 		  <code>false</code> if there isn't an empty square
	 *                           at the passed position
	 */

	final public boolean isEmptySquare(int position) {
		return ! (boxesArray[position] || wallsArray[position] > 0 || goalsArray[position]);
	}

    /**
     * Number of times a requested item was found in the Disk Store
     *
     * @throws IllegalStateException if the cache is not {@link Status#STATUS_ALIVE}
     */

    public int getDiskStoreHitCount() throws IllegalStateException {
        checkStatus();
        return diskStoreHitCount;
    }

    /**
     * Finds a creator method.
     */

    private Method findCreateMethod(Class objClass, String name) {
        final String methodName = makeMethodName("create", name);
        final Method[] methods = objClass.getMethods();
        for (int i = 0; i < methods.length; i++) {
            final Method method = methods[i];
            if (!method.getName().equals(methodName)) {
                continue;
            }
            if (Modifier.isStatic(method.getModifiers())) {
                continue;
            }
            if (method.getParameterTypes().length != 0) {
                continue;
            }
            if (method.getReturnType().isPrimitive() || method.getReturnType().isArray()) {
                continue;
            }
            return method;
        }

        return null;
    }

    /**
     * Builds a method name from an element or attribute name.
     */

    private String makeMethodName(final String prefix, final String name) {
        return prefix + Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }

    /**
     * Number of times a requested element was not found in the cache. This
     * may be because it expired, in which case this will also be recorded in {@link #getMissCountExpired},
     * or because it was simply not there.
     *
     * @throws IllegalStateException if the cache is not {@link Status#STATUS_ALIVE}
     */

    public int getMissCountNotFound() throws IllegalStateException {
        checkStatus();
        return missCountNotFound;
    }

    /**
     * Number of times a requested element was found but was expired
     *
     * @throws IllegalStateException if the cache is not {@link Status#STATUS_ALIVE}
     */

    public int getMissCountExpired() throws IllegalStateException {
        checkStatus();
        return missCountExpired;
    }

	/**
	 * Returns whether there is an empty square at the passed position.
	 * NB: a goal is <em>not</em> considered to be empty.
	 * The arguments are not checked against the board dimensions.
	 * 
	 * @param x the x-coordinate of the position
	 * @param y the y-coordinate of the position
	 * @return <code>true</code> if there is an empty square
	 *                           at the passed position, or<br>
	 * 		  <code>false</code> if there isn't an empty square
	 *                           at the passed position
	 */

	final public boolean isEmptySquare(int x, int y) {
		return ! (   boxesArray[x + width * y]
		          || wallsArray[x + width * y] > 0
		          || goalsArray[x + width * y]     );
	}

    /**
     * Gets the cache name
     */

    public String getName() {
        return name;
    }

    /**
     * Formats the current document location.
     */

    private String getLocation() {
        return locator.getSystemId() + ':' + locator.getLineNumber();
    }

    /**
     * Gets timeToIdleSeconds
     */

    public long getTimeToIdleSeconds() {
        return timeToIdleSeconds;
    }

    /**
     * Gets timeToLiveSeconds
     */

    public long getTimeToLiveSeconds() {
        return timeToLiveSeconds;
    }

	/**
	 * Returns whether the square at the passed is accessible,
	 * that means: not a box and not a wall.
	 * 
	 * @param position the position of the square
	 * @return <code>true</code> if the square is accessible, or<br>
	 * 		  <code>false</code> if the square isn't accessible
	 */

	final public boolean isAccessible(int position) {
		return ! (wallsArray[position] > 0 || boxesArray[position]);
	}

    /**
     * Does the overflow go to disk
     */

    public boolean isOverflowToDisk() {
        return overflowToDisk;
    }

    /**
     * Gets the maximum number of elements to hold in memory
     */

    public int getMaxElementsInMemory() {
        return maxElementsInMemory;
    }

	/**
	 * Returns whether the square at the passed is accessible,
	 * i.e. not a box and not a wall.
	 * The arguments are not checked against the board dimensions.
	 * 
	 * @param x the x-coordinate of the position
	 * @param y the y-coordinate of the position
	 * @return <code>true</code> if the square is accessible, or<br>
	 * 		  <code>false</code> if the square isn't accessible
	 */

	final public boolean isAccessible(int x, int y) {
		return ! (wallsArray[x + width * y] > 0 || boxesArray[x + width * y]);
	}

    /**
     * The policy used to evict elements from the {@link net.sf.ehcache.store.MemoryStore}.
     * This can be one of:
     * <ol>
     * <li>LRU - least recently used
     * <li>LFU - least frequently used
     * <li>FIFO - first in first out, the oldest element by creation time
     * </ol>
     * The default value is LRU
     *
     * @since 1.2
     */

    public MemoryStoreEvictionPolicy getMemoryStoreEvictionPolicy() {
        return memoryStoreEvictionPolicy;
    }

    /**
     * Gets the disk cache path
     */

    public String getDiskCachePath() {
        if (diskStore != null) {
            return diskStore.path;
        } else {
            return null;
        }
    }

    /**
     * Gets a Map of caches
     */

    public Set getCacheKeySet() {
        return caches.keySet();
    }

    /**
     * Checks whether this cache element has expired.
     * <p/>
     * The element is expired if:
     * <ol>
     * <li> the idle time is non-zero and has elapsed, unless the cache is eternal; or
     * <li> the time to live is non-zero and has elapsed, unless the cache is eternal; or
     * <li> the value of the element is null.
     * </ol>
     *
     * @return true if it has expired
     * @throws IllegalStateException if the cache is not {@link Status#STATUS_ALIVE}
     * @throws NullPointerException  if the element is null
     */

    public boolean isExpired(Element element) throws IllegalStateException, NullPointerException {
        checkStatus();
        boolean expired;
        synchronized (element) {
            if (element.getValue() == null) {
                expired = true;
            }
            if (!eternal) {
                expired = checkExpirationForNotEternal(element);
            } else {
                expired = false;
            }
            if (LOG.isDebugEnabled()) {
                Serializable key = null;
                if (element != null) {
                    key = element.getKey();
                }
                LOG.debug(getName() + ": Is element with key " + key + " expired?: " + expired);
            }
            return expired;
        }
    }

	/**
	 * Returns whether the square at the passed position is accessible for
	 * a box.  A square is accessible for a box if there is neither a wall
	 * nor a box at this square AND the square is no simple deadlock square.
	 * 
	 * @param position the position of the square
	 * @return <code>true</code> if the square is accessible for a box, or<br>
	 * 		  <code>false</code> if the square isn't accessible for a box
	 */

	final public boolean isAccessibleBox(int position) {
		return ! (   wallsArray[position] > 0
				  || boxesArray[position]
				  || simpleDeadlockSquareForwards[position]
				  || advancedSimpleDeadlockSquareForwards[position] );
	}

    /**
     * Clones a cache. This is only legal if the cache has not been
     * initialized. At that point only primitives have been set and no
     * {@link net.sf.ehcache.store.LruMemoryStore} or {@link net.sf.ehcache.store.DiskStore} has been created.
     *
     * @return an object of type {@link Cache}
     * @throws CloneNotSupportedException
     */

    public Object clone() throws CloneNotSupportedException {
        if (!(memoryStore == null && diskStore == null)) {
            throw new CloneNotSupportedException("Cannot clone an initialized cache.");
        }
        //added by yanai
        Cache cache = (Cache) super.clone();
        cache.cacheEventNotificationService = new CacheEventNotificationService(cache);
        return cache;
       //end of add
    }

        /**
         * Sets the class name
         *
         * @param fullyQualifiedClassPath
         */

        public void setClass(String fullyQualifiedClassPath) {
            this.fullyQualifiedClassPath = fullyQualifiedClassPath;
        }

	/**
	 * Returns whether the square at the passed position is accessible for
	 * a box.  A square is accessible for a box if there is neither a wall
	 * nor a box at this square AND the square is no simple deadlock square.
	 * The arguments are not checked against the board dimensions.
	 * 
	 * @param x the x-coordinate of the position
	 * @param y the y-coordinate of the position
	 * @return <code>true</code> if the square is accessible for a box, or<br>
	 * 		  <code>false</code> if the square isn't accessible for a box
	 */

	final public boolean isAccessibleBox(int x, int y) {
		return ! (   wallsArray[x + width * y] > 0
				  || boxesArray[x + width * y]
				  || simpleDeadlockSquareForwards[x + width * y]
				  || advancedSimpleDeadlockSquareForwards[x + width * y] );
	}

        /**
         * Sets the name of the cache. This must be unique
         */

        public void setName(String name) {
            this.name = name;
        }

    /**
     * Use this to access the service in order to register and unregister listeners
     *
     * @return the CacheEventNotificationService instance for this cache.
     */

    public CacheEventNotificationService getCacheEventNotificationService() {
        return cacheEventNotificationService;
    }

