	/**
	 * Returns the current location of the Drag-and-Drop activity.
	 */

	public Point getInsertionLocation() {
		return insertionLocation;
	}

	/**
	 * Returns the currently selected document.
	 * If no one is selected, then the first one will be select.
	 * @return GPDocument
	 */

	public GPDocument getCurrentDocument() {
		GPInternalFrame internalFrame =
			(GPInternalFrame) desktop.getSelectedFrame();
		if (internalFrame == null) {
			JInternalFrame[] frames = desktop.getAllFrames();
			if (frames.length > 0) {
				try {
					frames[0].setSelected(true);
					internalFrame = (GPInternalFrame) frames[0];
				} catch (PropertyVetoException e) {
					return null;
				}
			}
		}
		if (internalFrame == null)
			return null;
		return internalFrame.getDocument();
	}

	/**
	 * Returns the undoAction.
	 * @return UndoAction
	 */

	public AbstractActionDefault getEditUndoAction() {
		return (AbstractActionDefault) currentActionMap.get(
			Utilities.getClassNameWithoutPackage(EditUndo.class));
	}

	/**
	 * Sets the current location for Drag-and-Drop activity. Should be
	 * set to null after a drop. Used from within DropTargetListener.
	 */

	public void setInsertionLocation(Point p) {
		insertionLocation = p;
	}

	/**
	 * Returns the redoAction.
	 *
	 * @return RedoAction
	 */

	public AbstractActionDefault getEditRedoAction() {
		return (AbstractActionDefault) currentActionMap.get(
			Utilities.getClassNameWithoutPackage(EditRedo.class));
	}

	/** Adds a new Internal Frame to the Graphpad
	 */

	public void addGPInternalFrame(GPInternalFrame f) {
		desktop.add(f);
		try {
			f.setSelected(true);
		} catch (Exception ex) {
		}
		doc2InternalFrame.put(f.getDocument(), f);
	}

	/** removes the specified Internal Frame from the Graphpad
	 */

	public void removeGPInternalFrame(GPInternalFrame f) {
		if (f == null)
			return;
		f.setVisible(false);
		desktop.remove(f);
		doc2InternalFrame.remove(f.getDocument());
		JInternalFrame[] frames = desktop.getAllFrames();
		if (frames.length > 0) {
			try {
				frames[0].setSelected(true);
			} catch (PropertyVetoException e) {
			}
		}
	}

	/** Adds a new Document based on the GraphModelProvider.
	 *
	 */

	public void exit() {

		if (!isApplet()) {
			System.exit(0);
		} else {
			getApplet().exit(this);
		}
	}

	/**
	 * Messaged to update the selection based on a MouseEvent over a
	 * particular cell. If the event is a toggle selection event, the
	 * cell is either selected, or deselected. Otherwise the cell is
	 * selected.
	 */

	public void selectCellForEvent(Object cell, MouseEvent event) {
		// Toggle selection
		if (isToggleSelectionEvent(event))
			toggleSelectionCellForEvent(cell, event);

		// Select cell
		else if (isAddToSelectionEvent(event))
			graph.addSelectionCell(cell);
		else
			graph.setSelectionCell(cell);
	}

	/** Adds a new Document based on the GraphModelProvider.
	 *
	 */

	public void setApplet(JGraphpad applet) {
		this.applet = applet;
	}

	/** Adds a new Document based on the GraphModelProvider.
	 *
	 */

	public JGraphpad getApplet() {
		return applet;
	}

	/**
	 * Messaged to update the selection based on a toggle selection
	 * event, which means the cell's selection state is inverted.
	 */

	protected void toggleSelectionCellForEvent(Object cell, MouseEvent event) {
		if (graph.isCellSelected(cell))
			graph.removeSelectionCell(cell);
		else
			graph.addSelectionCell(cell);
	}

	/** Adds a new Document based on the GraphModelProvider.
	 *
	 */

	public boolean isApplet() {
		return (applet != null);
	}

	/** Adds a new Document based on the GraphModelProvider.
	 *
	 */

	public void addDocument(GraphModelProvider graphModelProvider) {
		addDocument(null, graphModelProvider, null, null, null);
	}

	/**
	 * Returning true signifies that cells are added to the selection.
	 */

	public boolean isAddToSelectionEvent(MouseEvent e) {
		return e.isShiftDown();
	}

	/**
	 * Returning true signifies a mouse event on the cell should toggle
	 * the selection of only the cell under mouse.
	 */

	public boolean isToggleSelectionEvent(MouseEvent event) {
		return (event.isControlDown());
	}

	/**
	  * Returns the first cell.
	  */

	public Object getCell() {
		return cells[0];
	}

	/**
	 * Returns true if the first cell has been added to the selection,
	 * a return value of false means the first cell has been
	 * removed from the selection.
	 */

	public boolean isAddedCell() {
		return areNew[0];
	}

	/**
	 * Returns true if the cell identified by cell was added to the
	 * selection. A return value of false means the cell was in the
	 * selection but is no longer in the selection. This will raise if
	 * cell is not one of the cells identified by this event.
	 */

	public boolean isAddedCell(Object cell) {
		for (int counter = cells.length - 1; counter >= 0; counter--)
			if (cells[counter].equals(cell))
				return areNew[counter];
		throw new IllegalArgumentException("cell is not a cell identified by the GraphSelectionEvent");
	}

	/**
	 * Returns true if the cell identified by <code>index</code> was added to
	 * the selection. A return value of false means the cell was in the
	 * selection but is no longer in the selection. This will raise if
	 * index < 0 || >= <code>getPaths</code>.length.
	 *
	 * @since 1.3
	 */

	public boolean isAddedCell(int index) {
		if (cells == null || index < 0 || index >= cells.length) {
			throw new IllegalArgumentException("index is beyond range of added cells identified by GraphSelectionEvent");
		}
		return areNew[index];
	}

	/**
	 * Returns a copy of the receiver, but with the source being newSource.
	 */

	public Object cloneWithSource(Object newSource) {
		// Fix for IE bug - crashing
		return new GraphSelectionEvent(newSource, cells, areNew);
	}

	/**
	 * Returns the object that constitues the change.
	 */

	public GraphModelChange getChange() {
		return change;
	}

	/**
	 * Returns the barFactory.
	 * @return GPBarFactory
	 */

	public GPBarFactory getBarFactory() {
		return barFactory;
	}

	/**
	 * Returning true signifies the marquee handler has precedence over
	 * other handlers, and is receiving subsequent mouse events.
	 */

	public boolean isForceMarqueeEvent(MouseEvent event) {
		if (marquee != null)
			return marquee.isForceMarqueeEvent(event);
		return false;
	}

	/**
	 * Sets the barFactory.
	 * @param barFactory The barFactory to set
	 */

	public void setBarFactory(GPBarFactory barFactory) {
		this.barFactory = barFactory;
	}

	/**
	 * Returning true signifies a move should only be
	 * applied to one direction.
	 */

	public boolean isConstrainedMoveEvent(MouseEvent event) {
		if (event != null)
			return event.isShiftDown();
		return false;
	}

	/**
	 * Returns the L&F object that renders this component.
	 * @return the GraphUI object that renders this component
	 */

	public GraphUI getUI() {
		return (GraphUI) ui;
	}

	/**
	 * Returns the marqueeHandler.
	 * @return JGpdMarqueeHandler
	 */

	public JGpdMarqueeHandler getMarqueeHandler() {
		return marqueeHandler;
	}

	/**
	 * Sets the L&F object that renders this component.
	 * @param ui the GraphUI L&F object
	 * @see javax.swing.UIDefaults#getUI(JComponent)
	 *
	 */

	public void setUI(GraphUI ui) {
		if ((GraphUI) this.ui != ui) {
			settingUI = true;
			try {
				super.setUI(ui);
			} finally {
				settingUI = false;
			}
		}
	}

	/**
	  * Returns true if the graph is being edited.  The item that is being
	  * edited can be returned by getEditingPath().
	  */

	public boolean isEditing(JGraph graph) {
		return (editingComponent != null);
	}

	/**
	 * Notification from the <code>UIManager</code> that the L&F has changed.
	 * Replaces the current UI object with the latest version from the
	 * <code>UIManager</code>. Subclassers can override this to support
	 * different GraphUIs.
	 * @see JComponent#updateUI
	 *
	 */

	public void updateUI() {
		setUI(new org.jgraph.plaf.basic.BasicGraphUI());
		invalidate();
	}

	/**
	  * Stops the current editing session.  This has no effect if the
	  * graph isn't being edited.  Returns true if the editor allows the
	  * editing session to stop.
	  */

	public boolean stopEditing(JGraph graph) {
		if (editingComponent != null && cellEditor.stopCellEditing()) {
			completeEditing(false, false, true);
			return true;
		}
		return false;
	}

	/**
	 * Returns true if the edge shape intersects the given rectangle.
	 */

	public boolean intersects(Graphics g, CellView value, Rectangle r) {
		if (value instanceof EdgeView && g != null && value != null) {
            setView(value);
            Graphics2D g2 = (Graphics2D) g;
            boolean hit = g2.hit(r, view.getShape(), true);
			if (hit)
				return true;
			Rectangle rect = view.getLabelBounds();
			if (rect != null)
				return rect.intersects(r);
		}
		return false;
	}

	/**
	 * Returns the bounds of the edge shape.
	 */

	public Rectangle getBounds(CellView value) {
		if (value instanceof EdgeView && value != null) {
            setView(value);
			Rectangle r = getPaintBounds(view);
			Rectangle label = getLabelBounds(view);
			if (label != null)
				r = r.union(label);
			int b = (int) Math.ceil(lineWidth);
			r.x = r.x - b;
			r.y = r.y - b;
			r.width = r.width + 2 * b;
			r.height = r.height + 2 * b;
			return r;
		}
		return null;
	}

	/**
	 * Returns the name of the L&F class that renders this component.
	 * @return the string "GraphUI"
	 * @see JComponent#getUIClassID
	 *
	 */

	public String getUIClassID() {
		return uiClassID;
	}

	/**
	 * Returns the label bounds of the specified view in the given graph.
	 */

	public Rectangle getLabelBounds(EdgeView view) {
        setView(view);
		Point p = getLabelPosition(this.view);
		Dimension d = getLabelSize(this.view);
		if (p != null && d != null) {
			p.translate(-d.width / 2, -d.height / 2);
			return new Rectangle(p.x, p.y, d.width + 1, d.height + 1);
		}
		return null;
	}

	/**
	  * Cancels all current editing sessions.
	  */

	public void cancelEditing(JGraph graph) {
		if (editingComponent != null)
			completeEditing(false, true, false);
		// Escape key is handled by the KeyHandler.keyPressed inner class method
	}

	/**
	 * Returns the label size of the specified view in the given graph.
	 */

	public Dimension getLabelSize(EdgeView view) {
        setView(view);
		Object label = view.getGraph().convertValueToString(view);
		if (label != null && label.toString().length() > 0) {
			fontGraphics.setFont(
				GraphConstants.getFont(view.getAllAttributes()));
			metrics = fontGraphics.getFontMetrics();
			int sw = metrics.stringWidth(label.toString());
			int sh = metrics.getHeight();
			return new Dimension(sw, sh);
		}
		return null;
	}

	/**
	 * Returns a map of (cell, clone)-pairs for all <code>cells</code>
	 * and their children. Special care is taken to replace the anchor
	 * references between ports. (Iterative implementation.)
	 */

	public Map cloneCells(Object[] cells) {
		return graphModel.cloneCells(cells);
	}

	/**
	 * Returns the bounds of the edge shape without label
	 */

	public Rectangle getPaintBounds(EdgeView view) {
        setView(view);
		return view.getShape().getBounds();
	}

	/**
	  * Selects the cell and tries to edit it.  Editing will
	  * fail if the CellEditor won't allow it for the selected item.
	  */

	public void startEditingAtCell(JGraph graph, Object cell) {
		graph.scrollCellToVisible(cell);
		if (cell != null)
			startEditing(cell, null);
	}

	/**
	 * Returns the topmost cell at the specified location.
	 * @param x an integer giving the number of pixels horizontally from
	 * the left edge of the display area, minus any left margin
	 * @param y an integer giving the number of pixels vertically from
	 * the top of the display area, minus any top margin
	 * @return the topmost cell at the specified location
	 */

	public Object getFirstCellForLocation(int x, int y) {
		return getNextCellForLocation(null, x, y);
	}

	/**
	 * Returns the element that is being edited.
	 */

	public Object getEditingCell(JGraph graph) {
		return editingCell;
	}

	/**
	 * Returns the cell at the specified location that is "behind" the
	 * <code>current</code> cell. Returns the topmost cell if there are
	 * no more cells behind <code>current</code>.
	 */

	public Object getNextCellForLocation(Object current, int x, int y) {
		x /= scale;
		y /= scale; // FIX: Consistency with other methods?
		CellView cur = graphLayoutCache.getMapping(current, false);
		CellView cell = getNextViewAt(cur, x, y);
		if (cell != null)
			return cell.getCell();
		return null;
	}

	/**
	 * Overridden for performance reasons.
	 * See the <a href="#override">Implementation Note</a>
	 * for more information.
	 */

	public void validate() {
	}

	/**
	 * Returns the bounding rectangle of the specified cell.
	 */

	public Rectangle getCellBounds(Object cell) {
		CellView view = graphLayoutCache.getMapping(cell, false);
		if (view != null)
			return view.getBounds();
		return null;
	}

	/**
	 * Overridden for performance reasons.
	 * See the <a href="#override">Implementation Note</a>
	 * for more information.
	 */

	public void revalidate() {
	}

	/**
	 * Overridden for performance reasons.
	 * See the <a href="#override">Implementation Note</a>
	 * for more information.
	 */

	public void repaint(long tm, int x, int y, int width, int height) {
	}

	/**
	 * Overridden for performance reasons.
	 * See the <a href="#override">Implementation Note</a>
	 * for more information.
	 */

	public void repaint(Rectangle r) {
	}

	/**
	 * Returns the bounding rectangle of the specified cells.
	 */

	public Rectangle getCellBounds(Object[] cells) {
		if (cells != null && cells.length > 0) {
			Rectangle ret = getCellBounds(cells[0]);
			if (ret != null) {
				ret = new Rectangle(ret);
				for (int i = 1; i < cells.length; i++) {
					Rectangle r = getCellBounds(cells[i]);
					if (r != null)
					    SwingUtilities.computeUnion(
						r.x,
						r.y,
						r.width,
						r.height,
						ret);
				}
				return ret;
			}
		}
		return null;
	}

	/**
	 * Invoked after the <code>graph</code> instance variable has been
	 * set, but before any defaults/listeners have been installed.
	 */

	protected void prepareForUIInstall() {
		// Data member initializations
		stopEditingInCompleteEditing = true;
		preferredSize = new Dimension();
		setGraphLayoutCache(graph.getGraphLayoutCache());
		setModel(graph.getModel());
	}

	/**
	 * Returns the next view at the specified location wrt. <code>current</code>.
	 * This is used to iterate overlapping cells, and cells that are grouped.
	 * The current selection affects this method.
	 */

	public CellView getNextViewAt(CellView current, int x, int y) {
		Object[] sel =
			graphLayoutCache.order(getSelectionModel().getSelectables());
		CellView[] cells = graphLayoutCache.getMapping(sel);
		CellView cell = getNextViewAt(cells, current, x, y);
		return cell;
	}

	/**
	 * Invoked from installUI after all the defaults/listeners have been
	 * installed.
	 */

	protected void completeUIInstall() {
		// Custom install code
		setSelectionModel(graph.getSelectionModel());
		updateSize();
	}

	/**
	 * Invoked as part from the boilerplate install block. This
	 * sets the look and feel specific variables in JGraph.
	 */

	protected void installDefaults() {
		if (graph.getBackground() == null
			|| graph.getBackground() instanceof UIResource) {
			graph.setBackground(UIManager.getColor("Tree.background"));
		}
		if (graph.getFont() == null || graph.getFont() instanceof UIResource)
			graph.setFont(UIManager.getFont("Tree.font"));
		// Set JGraph's laf-specific colors
		graph.setMarqueeColor(UIManager.getColor("Table.gridColor"));
		graph.setHandleColor(
			UIManager.getColor("MenuItem.selectionBackground"));
		graph.setLockedHandleColor(UIManager.getColor("MenuItem.background"));
		graph.setGridColor(UIManager.getColor("Tree.selectionBackground"));
		graph.setOpaque(true);
	}

	/**
	 * Convenience method to return the port at the specified location.
	 */

	public Object getPortForLocation(int x, int y) {
		PortView view = getPortViewAt(x, y);
		if (view != null)
			return view.getCell();
		return null;
	}

	/**
	  * Includes the specified startPoint in the marquee selection. Calls
	  * overlay.
	  */

	public void mouseDragged(MouseEvent e) {
		if (!e.isConsumed() && startPoint != null) {
			if (!(e.getSource() instanceof JGraph))
				throw new IllegalArgumentException(
					"MarqueeHandler cannot handle event from unknown source: "
						+ e);
			JGraph graph = (JGraph) e.getSource();
			Graphics g = graph.getGraphics();
			Color bg = graph.getBackground();
			Color fg = graph.getMarqueeColor();
			g.setColor(fg);
			g.setXORMode(bg);
			overlay(g);
			currentPoint = e.getPoint();
			marqueeBounds = new Rectangle(startPoint);
			marqueeBounds.add(currentPoint);
			g.setColor(bg);
			g.setXORMode(fg);
			overlay(g);
			e.consume();
		}
	}

	/** 
		* Called after the component was repainted (ie. after autoscroll).
	  * This is used to indicate that the graphics is no more dirty.
	  */

	public void paint(Graphics g) {
		overlay(g);
	}

	/**
	  * Start the marquee at the specified startPoint. This invokes
	  * expandMarqueeToPoint to initialize marquee selection.
	  */

	public void mousePressed(MouseEvent e) {
		if (!e.isConsumed()) {
			if (!(e.getSource() instanceof JGraph))
				throw new IllegalArgumentException(
					"MarqueeHandler cannot handle event from unknown source: "
						+ e);
			JGraph graph = (JGraph) e.getSource();
			startPoint = e.getPoint();
			marqueeBounds = new Rectangle(startPoint);
			previousCursor = graph.getCursor();
			graph.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			e.consume();
		}
	}

	/**
	 * Returns the currentPoint.
	 * @return Point
	 */

	public Point getCurrentPoint() {
		return currentPoint;
	}

	/**
	 * Returns the marqueeBounds.
	 * @return Rectangle
	 */

	public Rectangle getMarqueeBounds() {
		return marqueeBounds;
	}

	/**
	 * Returns the portview at the specified location.
	 */

	public PortView getPortViewAt(int x, int y) {
		Rectangle r =
			new Rectangle(
				x - tolerance,
				y - tolerance,
				2 * tolerance,
				2 * tolerance);
		PortView[] ports = graphLayoutCache.getPorts();
		for (int i = ports.length - 1; i >= 0; i--)
			if (ports[i] != null && ports[i].intersects(getGraphics(), r))
				return ports[i];
		return null;
	}

	/**
	 * Returns the previousCursor.
	 * @return Cursor
	 */

	public Cursor getPreviousCursor() {
		return previousCursor;
	}

	/**
	 * Returns the startPoint.
	 * @return Point
	 */

	public Point getStartPoint() {
		return startPoint;
	}

	/**
	 * Sets the currentPoint.
	 * @param currentPoint The currentPoint to set
	 */

	public void setCurrentPoint(Point currentPoint) {
		this.currentPoint = currentPoint;
	}

	/**
	 * Sets the marqueeBounds.
	 * @param marqueeBounds The marqueeBounds to set
	 */

	public void setMarqueeBounds(Rectangle marqueeBounds) {
		this.marqueeBounds = marqueeBounds;
	}

	/**
	 * Invoked as part from the boilerplate install block.
	 */

	protected void installKeyboardActions() {
		InputMap km =
			getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		SwingUtilities.replaceUIInputMap(
			graph,
			JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,
			km);
		km = getInputMap(JComponent.WHEN_FOCUSED);
		SwingUtilities.replaceUIInputMap(graph, JComponent.WHEN_FOCUSED, km);
		SwingUtilities.replaceUIActionMap(graph, createActionMap());
	}

	/**
	 * Sets the previousCursor.
	 * @param previousCursor The previousCursor to set
	 */

	public void setPreviousCursor(Cursor previousCursor) {
		this.previousCursor = previousCursor;
	}

	/**
	 * Sets the startPoint.
	 * @param startPoint The startPoint to set
	 */

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	/**
	 * Overrides the parent method to udpate the cached points,
	 * source and target port. If the source or target is removed,
	 * a point is inserted into the array of points.
	 */

	public void refresh(boolean createDependentViews) {
		super.refresh(createDependentViews);
		// Sync Source- and Targetport
		if (points != null) {
			Object modelSource = getModel().getSource(cell);
			Object modelTarget = getModel().getTarget(cell);
			setSource(mapper.getMapping(modelSource, createDependentViews));
			setTarget(mapper.getMapping(modelTarget, createDependentViews));
			// Re-Route
			Edge.Routing routing = GraphConstants.getRouting(allAttributes);
			if (routing != null)
				routing.route(this, points);
		}
	}

	/**
	 * Converts the specified value to string. If the value is an instance of
	 * CellView or the current GraphLayoutCache returns a mapping for value, then
	 * then value attribute of that CellView is used. (The value is retrieved using
	 * getAllAttributes.) If the value is an instance
	 * of DefaultMutableTreeNode (e.g. DefaultGraphCell), then the userobject
	 * is returned as a String.
	 */

	public String convertValueToString(Object value) {
		CellView view =
			(value instanceof CellView)
				? (CellView) value
				: getGraphLayoutCache().getMapping(value, false);
		if (view != null) {
			Object newValue = GraphConstants.getValue(view.getAllAttributes());
			if (newValue != null)
				value = newValue;
			else
				value = view.getCell();
		}
		if (value instanceof DefaultMutableTreeNode
			&& ((DefaultMutableTreeNode) value).getUserObject() != null)
			return ((DefaultMutableTreeNode) value).getUserObject().toString();
		else if (value != null)
			return value.toString();
		return null;
	}

	/**
	 * Update attributes and recurse children.
	 */

	public void update() {
		super.update();
		points = GraphConstants.getPoints(allAttributes);
		labelPosition = GraphConstants.getLabelPosition(allAttributes);
		Edge.Routing routing = GraphConstants.getRouting(allAttributes);
		if (routing != null)
			routing.route(this, points);
		// Synchronize Points and PortViews
		if (getModel().getSource(cell) != null)
			setSource(getSource());
		if (getModel().getTarget(cell) != null)
			setTarget(getTarget());
		// Clear cached shapes
		beginShape = null;
		endShape = null;
		lineShape = null;
		sharedPath = null;
		cachedBounds = null;
		cachedLabelBounds = null;
	}

	/**
	 * Returns true if this view intersects the given rectangle.
	 */

	public boolean intersects(Graphics g, Rectangle rect) {
		return getEdgeRenderer().intersects(g, this, rect);
	}

	/**
	 * Intalls the subcomponents of the graph, which is the renderer pane.
	 */

	protected void installComponents() {
		if ((rendererPane = createCellRendererPane()) != null)
			graph.add(rendererPane);
	}

	/**
	 * Returns the location for this portview.
	 */

	public Rectangle getBounds() {
		if (cachedBounds != null) {
			return cachedBounds;
		} else {
			return cachedBounds = getEdgeRenderer().getBounds(this);
		}
	}

	/**
	 * Returns the local renderer. Do not access the renderer
	 * field directly. Use this method instead!
	 */

	public EdgeRenderer getEdgeRenderer() {
		return (EdgeRenderer) getRenderer();
	}

	/**
	 * Returns the given point applied to the grid.
	 * @param p a point in screen coordinates.
	 * @return the same point applied to the grid.
	 */

	public Point snap(Point p) {

		if (gridEnabled && p != null) {

			double sgs = (double) gridSize * getScale();

			p.x = (int) Math.round(Math.round(p.x / sgs) * sgs);
			p.y = (int) Math.round(Math.round(p.y / sgs) * sgs);

		}

		return p;

	}

	/**
	 * Returns a renderer for the class.
	 */

	public CellViewRenderer getRenderer() {
		return renderer;
	}

	/**
	 * Returns a cell handle for the view.
	 */

	public CellHandle getHandle(GraphContext context) {
		return new EdgeHandle(this, context);
	}

	/**
	 * Creates an instance of TransferHandler. Used for subclassers
	 * to provide different TransferHandler.
	 */

	protected TransferHandler createTransferHandler() {
		return new GraphTransferHandler();
	}

	/**
	 * Returns the CellView that represents the source of the edge.
	 */

	public CellView getSource() {
		return source;
	}

	/**
	 * Returns the given point applied to the grid.
	 * @param p a point in screen coordinates.
	 * @return the same point applied to the grid.
	 */

	public Dimension snap(Dimension d) {

		if (gridEnabled && d != null) {

			double sgs = (double) gridSize * getScale();

			d.width = 1 + (int) Math.round(Math.round(d.width / sgs) * sgs);
			d.height = 1 + (int) Math.round(Math.round(d.height / sgs) * sgs);

		}

		return d;

	}

	/**
	 * Sets the <code>sourceView</code> of the edge.
	 */

	public void setSource(CellView sourceView) {
		source = sourceView;
		if (source != null)
			points.set(0, source);
		else
			points.set(0, getPoint(0));
		invalidate();
	}

	/**
	 * Returns the CellView that represents the target of the edge.
	 */

	public CellView getTarget() {
		return target;
	}

	/**
	 * Sets the <code>targetView</code> of the edge.
	 */

	public void setTarget(CellView targetView) {
		target = targetView;
		int n = points.size() - 1;
		if (target != null)
			points.set(n, target);
		else
			points.set(n, getPoint(n));
		invalidate();
	}

	/**
	 * Creates a listener that is responsible to update the UI based on
	 * how the graph's bounds properties change.
	 */

	protected PropertyChangeListener createPropertyChangeListener() {
		return new PropertyChangeHandler();
	}

	/**
	 * Returns a point that describes the position of the label.
	 */

	public Point getLabelPosition() {
		return labelPosition;
	}

	/**
	 * Upscale the given point in place, ie.
	 * using the given instance.
	 * @param p the point to be upscaled
	 * @return the upscaled point instance
	 */

	public Point toScreen(Point p) {
		if (p == null)
			return null;

		p.x = (int) Math.round(p.x * scale);
		p.y = (int) Math.round(p.y * scale);
		return p;
	}

	/**
	 * Sets the description of the label position.
	 */

	public void setLabelPosition(Point pos) {
		labelPosition.setLocation(pos);
		invalidate();
	}

	/**
	 * Returns the number of point for this edge.
	 */

	public int getPointCount() {
		return points.size();
	}

	/**
	 * Returns the cached points for this edge.
	 */

	public Point getPoint(int index) {
		Object obj = points.get(index);
		if (obj instanceof PortView)
			// Port Location Seen From This Edge
			return ((PortView) obj).getLocation(this);
		else if (obj instanceof CellView)
			return ((CellView) obj).getBounds().getLocation();
		else if (obj instanceof Point)
			// Regular Point
			return (Point) obj;
		return null;
	}

	/**
	 * Creates the listener responsible for calling the correct handlers
	 * based on mouse events, and to select invidual cells.
	 */

	protected MouseListener createMouseListener() {
		return new MouseHandler();
	}

	/**
	 * Sets the point at <code>index</code> to <code>p</code>.
	 */

	public void setPoint(int index, Point p) {
		points.set(index, p);
		invalidate();
	}

	/**
	 * Downscale the given point in place, ie.
	 * using the given instance.
	 * @param p the point to be downscaled
	 * @return the downscaled point instance
	 */

	public Point fromScreen(Point p) {
		if (p == null)
			return null;

		p.x = (int) Math.round(p.x / scale);
		p.y = (int) Math.round(p.y / scale);
		return p;
	}

	/**
	 * Removes the point at position <code>index</code>.
	 */

	public void removePoint(int index) {
		points.remove(index);
		invalidate();
	}

	/**
	 * Returning true signifies a mouse event adds a new point to an edge.
	 */

	public boolean isAddPointEvent(MouseEvent event) {
		return SwingUtilities.isRightMouseButton(event);
	}

	/**
	 * Upscale the given rectangle in place, ie.
	 * using the given instance.
	 * @param rect the rectangle to be upscaled
	 * @return the upscaled rectangle instance
	 */

	public Rectangle toScreen(Rectangle rect) {
		if (rect == null)
			return null;

		rect.x *= scale;
		rect.y *= scale;
		rect.width *= scale;
		rect.height *= scale;
		return rect;
	}

	/**
	 * Creates the listener reponsible for getting key events from
	 * the graph.
	 */

	protected KeyListener createKeyListener() {
		return new KeyHandler();
	}

	/**
	 * Downscale the given rectangle in place, ie.
	 * using the given instance.
	 * @param rect the rectangle to be downscaled
	 * @return the down-scaled rectangle instance
	 */

	public Rectangle fromScreen(Rectangle rect) {
		if (rect == null)
			return null;

		rect.x /= scale;
		rect.y /= scale;
		rect.width /= scale;
		rect.height /= scale;
		return rect;
	}

	/**
	 * Creates the listener that updates the display based on selection change
	 * methods.
	 */

	protected GraphSelectionListener createGraphSelectionListener() {
		return new GraphSelectionHandler();
	}

	/**
	 * Constructs a view for the specified cell and associates it
	 * with the specified object using the specified CellMapper.
	 * This calls refresh on the created CellView to create all
	 * dependent views.<p>
	 * Note: The mapping needs to be available before the views
	 * of child cells and ports are created.
	 *
	 * @param cell reference to the object in the model
	 */

	public CellView createView(Object cell, CellMapper map) {
		CellView view = null;
		if (graphModel.isPort(cell))
			view = createPortView(cell, map);
		else if (graphModel.isEdge(cell))
			view = createEdgeView(cell, map);
		else
			view = createVertexView(cell, map);
		map.putMapping(cell, view);
		view.refresh(true); // Create Dependent Views
		view.update();
		return view;
	}

	/**
	 * Creates a listener to handle events from the current editor.
	 */

	protected CellEditorListener createCellEditorListener() {
		return new CellEditorHandler();
	}

	/**
	 * Computes and updates the size for <code>view</code>.
	 */

	public void updateAutoSize(CellView view) {
		if (view != null && !isEditing()
			&& GraphConstants.isAutoSize(view.getAllAttributes())) {
			Rectangle bounds = view.getBounds();
			if (bounds != null) {
				Dimension d = getUI().getPreferredSize(this, view);
				bounds.setSize(d);
			}
		}
	}

		/**
		 * Invoked when the mouse pointer has been moved on a component
		 * (with no buttons down).
		 */

		public void mouseMoved(MouseEvent event) {
			for (int i = 0; i < r.length; i++)
				if (r[i].contains(event.getPoint())) {
					graph.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
					event.consume();
					return;
				}
			if (loc.contains(event.getPoint())
				&& graph.isMoveable()
				&& GraphConstants.isMoveable(edge.getAllAttributes())) {
				graph.setCursor(new Cursor(Cursor.HAND_CURSOR));
				event.consume();
			}
		}

	/**
	 * Creates and returns a new ComponentHandler.
	 */

	protected ComponentListener createComponentListener() {
		return new ComponentHandler();
	}

	/**
	 * Constructs an EdgeView view for the specified object.
	 */

	protected EdgeView createEdgeView(Object e, CellMapper cm) {
		if (e instanceof Edge)
			return createEdgeView((Edge) e, cm);
		else
			return new EdgeView(e, this, cm);
	}

	/**
	 * Returns the renderer pane that renderer components are placed in.
	 */

	protected CellRendererPane createCellRendererPane() {
		return new CellRendererPane();
	}

	/**
	 * Constructs a PortView view for the specified object.
	 */

	protected PortView createPortView(Object p, CellMapper cm) {
		if (p instanceof Port)
			return createPortView((Port) p, cm);
		else
			return new PortView(p, this, cm);
	}

	/**
	 * Returns the graph associated with the view.
	 */

	public JGraph getGraph() {
		return graph;
	}

	/**
	 * Returns the model associated with the view.
	 */

	public GraphModel getModel() {
		return graph.getModel();
	}

	/**
	 * Returns a listener that can update the graph when the view changes.
	 */

	protected Observer createGraphViewObserver() {
		return new GraphViewObserver();
	}

	/**
	 * Returns the cell mapper associated with the view.
	 */

	public CellMapper getMapper() {
		return mapper;
	}

	/**
	 * Returns the model object that this view represents.
	 */

	public Object getCell() {
		return cell;
	}

	/**
	 * Constructs an EdgeView view for the specified object.
	 *
	 * @deprecated	replaced by {@link #createEdgeView(Object,CellMapper)}
	 *		since JGraph no longer exposes dependecies on
	 * 		GraphCell subclasses (Port, Edge)
	 */

	protected EdgeView createEdgeView(Edge e, CellMapper cm) {
		return new EdgeView(e, this, cm);
	}

	/**
	 * Update attributes and recurse children.
	 */

	public void update() {
		updateAllAttributes();
		// Notify Parent
		childUpdated();
	}

	/**
	 * This method implements the merge between the
	 * cell's and the view's attributes. The view's
	 * attributes override the cell's attributes
	 * with one exception.
	 */

	protected void updateAllAttributes() {
		allAttributes = getModel().getAttributes(cell);
		if (allAttributes != null) {
			allAttributes = GraphConstants.cloneMap(allAttributes);
		} else
			allAttributes = GraphConstants.createMap();
		allAttributes.putAll(attributes);
	}

	/**
	 * Returns the parent view for this view.
	 */

	public CellView getParentView() {
		return parent;
	}

	/**
	 * Returns a listener that can update the graph when the model changes.
	 */

	protected GraphModelListener createGraphModelListener() {
		return new GraphModelHandler();
	}

	/**
	 * Removes this view from the list of childs of the parent.
	 */

	public void removeFromParent() {
		if (parent instanceof AbstractCellView) {
			java.util.List list = ((AbstractCellView) parent).childViews;
			list.remove(this);
		}
	}

	/**
	 * Returns true if the view is a leaf.
	 */

	public boolean isLeaf() {
		return childViews.isEmpty();
	}

	/**
	 * Constructs a PortView view for the specified object.
	*
	* @deprecated	replaced by {@link #createPortView(Object,CellMapper)}
	*		since JGraph no longer exposes dependecies on
	* 		GraphCell subclasses (Port, Edge)
	 */

	protected PortView createPortView(Port p, CellMapper cm) {
		return new PortView(p, this, cm);
	}

	/**
	 * Return the attributes of the view.
	 */

	public Map getAttributes() {
		return attributes;
	}

	/**
	 * Returns the attributes of the view combined with the
	 * attributes of the corresponding cell. The view's attributes
	 * override the cell's attributes with the same key.
	 */

	public Map getAllAttributes() {
		return allAttributes;
	}

	/**
	 * Applies <code>change</code> to the attributes of the view
	 * and calls update.
	 */

	public Map setAttributes(Map change) {
		Map undo = GraphConstants.applyMap(change, attributes);
		update();
		return undo;
	}

	/**
	 * Constructs a VertexView view for the specified object.
	 */

	protected VertexView createVertexView(Object v, CellMapper cm) {
		return new VertexView(v, this, cm);
	}

	/**
	 * Returns true if the view intersects the given rectangle.
	 */

	public boolean intersects(Graphics g, Rectangle rect) {
		if (isLeaf()) {
			Rectangle bounds = getBounds();
			if (bounds != null)
				return bounds.intersects(rect);
		} else { // Check If Children Intersect
			Iterator it = childViews.iterator();
			while (it.hasNext())
				if (((CellView) it.next()).intersects(g, rect))
					return true;
		}
		return false;
	}

	/**
	 * Returns a cell editor for the view.
	 */

	public GraphCellEditor getEditor() {
		return cellEditor;
	}

	/**
	 * Returns an iterator of the edges connected
	 * to the port.
	 */

	public Iterator edges() {
		return edges.iterator();
	}

	/**
	 * Adds <code>edge</code> to the list of ports.
	 */

	public boolean addEdge(Object edge) {
		return edges.add(edge);
	}

	/**
	 * Removes <code>edge</code> from the list of ports.
	 */

	public boolean removeEdge(Object edge) {
		return edges.remove(edge);
	}

	/**
	 * Returns the anchor of this port.
	 */

	public Set getEdges() {
		return new HashSet(edges);
	}

	/**
	 * Sets the anchor of this port.
	 */

	public void setEdges(Set edges) {
		this.edges = new HashSet(edges);
	}

	/**
	 * Returns the anchor of this port.
	 */

	public Port getAnchor() {
		return anchor;
	}

	/**
	 * Sets the anchor of this port.
	 */

	public void setAnchor(Port port) {
		anchor = port;
	}

	/**
	 * Returns the number of clicks for editing to start.
	 */

	public int getEditClickCount() {
		return editClickCount;
	}

	/**
	 * Create a clone of the cell. The cloning of the
	 * user object is deferred to the cloneUserObject()
	 * method.
	 *
	 * @return Object  a clone of this object.
	 */

	public Object clone() {
		DefaultPort c = (DefaultPort) super.clone();
		c.edges = new HashSet();
		return c;
	}

	/**
	 * Sets the number of clicks for editing to start.
	 */

	public void setEditClickCount(int count) {
		editClickCount = count;
	}

	/**
	 * Returns true if the graph accepts drops/pastes from external sources.
	 */

	public boolean isDropEnabled() {
		return dropEnabled;
	}

	/**
	 * Returns the number of roots in the model.  Returns 0 if the
	 * model is empty.
	 *
	 * @return  the number of roots in the model
	 */

	public int getRootCount() {
		return roots.size();
	}

	/**
	 * Returns the root at index <I>index</I> in the model.
	 * This should not return null if <i>index</i> is a valid
	 * index for the model (that is <i>index</i> >= 0 &&
	 * <i>index</i> < getRootCount()).
	 *
	 * @return  the root of at index <I>index</I>
	 */

	public Object getRootAt(int index) {
		return roots.get(index);
	}

	/**
	 * Returns the index of <code>root</code> in the model.
	 * If root is <code>null</code>, returns -1.
	 * @param parent a root in the model, obtained from this data source
	 * @return the index of the root in the model, or -1
	 *    if the parent is <code>null</code>
	 */

	public int getIndexOfRoot(Object root) {
		return roots.indexOf(root);
	}

	/**
	 * Sets if the graph accepts drops/pastes from external sources.
	 */

	public void setDropEnabled(boolean flag) {
		dropEnabled = flag;
	}

	/**
	 * Returns <code>true</code> if <code>node</code> or one of its
	 * ancestors is in the model.
	 *
	 * @return <code>true</code> if  <code>node</code> is in the model
	 */

	public boolean contains(Object node) {
		Object parentNode = null;
		while ((parentNode = getParent(node))
			!= null)
			node = parentNode;
		return roots.contains(node);
	}

	/**
	 * Uninstalls the renderer pane.
	 */

	protected void uninstallComponents() {
		if (rendererPane != null)
			graph.remove(rendererPane);
	}

	/**
	 * Returns a <code>Map</code> that represents the attributes for
	 * the specified cell. This attributes have precedence over each
	 * view's attributes, regardless of isAttributeStore.
	 *
	 * @return attributes of <code>node</code> as a <code>Map</code>
	 */

	public Map getAttributes(Object node) {
		if (node instanceof GraphCell)
			return ((GraphCell) node).getAttributes();
		return null;
	}

	/**
	 * Returns true if the graph uses Drag-and-Drop to move cells.
	 */

	public boolean isDragEnabled() {
		return dragEnabled;
	}

	/**
	 * Returns the source of <code>edge</code>. <I>edge</I> must be an object
	 * previously obtained from this data source.
	 *
	 * @return <code>Object</code> that represents the source of <i>edge</i>
	 */

	public Object getSource(Object edge) {
		if (edge instanceof Edge)
			return ((Edge) edge).getSource();
		return null;
	}

	/**
	 * Returns the target of <code>edge</code>. <I>edge</I> must be an object
	 * previously obtained from this data source.
	 *
	 * @return <code>Object</code> that represents the target of <i>edge</i>
	 */

	public Object getTarget(Object edge) {
		if (edge instanceof Edge)
			return ((Edge) edge).getTarget();
		return null;
	}

	/**
	 * Sets if the graph uses Drag-and-Drop to move cells.
	 */

	public void setDragEnabled(boolean flag) {
		dragEnabled = flag;
	}

	/**
	 * Returns <code>true</code> if <code>port</code> is a valid source
	 * for <code>edge</code>. <I>edge</I> and <I>port</I> must be
	 * objects previously obtained from this data source.
	 *
	 * @return <code>true</code> if <code>port</code> is a valid source
	 *                           for <code>edge</code>.
	 */

	public boolean acceptsSource(Object edge, Object port) {
		return true;
	}

	/**
	 * Returns <code>true</code> if <code>port</code> is a valid target
	 * for <code>edge</code>. <I>edge</I> and <I>port</I> must be
	 * objects previously obtained from this data source.
	 *
	 * @return <code>true</code> if <code>port</code> is a valid target
	 *                           for <code>edge</code>.
	 */

	public boolean acceptsTarget(Object edge, Object port) {
		return true;
	}

	/**
	 * Returns an iterator of the edges connected to <code>port</code>.
	 * <I>port</I> must be a object previously obtained from
	 * this data source. This method never returns null.
	 *
	 * @param   port  a port in the graph, obtained from this data source
	 * @return  <code>Iterator</code> that represents the connected edges
	 */

	public Iterator edges(Object port) {
		if (port instanceof Port)
			return ((Port) port).edges();
		return emptyIterator;
	}

	/**
	 * Sets if the graph allows movement of cells.
	 */

	public void setMoveable(boolean flag) {
		moveable = flag;
	}

	/**
	 * Paint the background of this graph. Calls paintGrid.
	 */

	protected void paintBackground(Graphics g) {
		if (graph.isGridVisible())
			paintGrid(graph.getGridSize(), g, g.getClipBounds());
	}

	/**
	 * Returns true if the graph allows adding/removing/modifying points.
	 */

	public boolean isBendable() {
		return bendable;
	}

	/**
	 * Returns the parent of <I>child</I> in the model.
	 * <I>child</I> must be a node previously obtained from
	 * this data source. This returns null if <i>child</i> is
	 * a root in the model.
	 *
	 * @param   child  a node in the graph, obtained from this data source
	 * @return  the parent of <I>child</I>
	 */

	public Object getParent(Object child) {
		if (child != null && child instanceof TreeNode)
			return ((TreeNode) child).getParent();
		return null;
	}

	/**
	 * Returns the index of child in parent.
	 * If either the parent or child is <code>null</code>, returns -1.
	 * @param parent a note in the tree, obtained from this data source
	 * @param child the node we are interested in
	 * @return the index of the child in the parent, or -1
	 *    if either the parent or the child is <code>null</code>
	 */

	public int getIndexOfChild(Object parent, Object child) {
		if (parent == null || child == null)
			return -1;
		return ((TreeNode) parent).getIndex((TreeNode) child);
	}

	/**
	 * Sets if the graph allows adding/removing/modifying points.
	 */

	public void setBendable(boolean flag) {
		bendable = flag;
	}

	/**
	 * Paint the foreground of this graph. Calls paintPorts.
	 */

	protected void paintForeground(Graphics g) {
		if (graph.isPortsVisible())
			paintPorts(g, graphLayoutCache.getPorts());
	}

	/**
	 * Returns the number of children of <I>parent</I>.  Returns 0 if the node
	 * is a leaf or if it has no children.  <I>parent</I> must be a node
	 * previously obtained from this data source.
	 *
	 * @param   parent  a node in the tree, obtained from this data source
	 * @return  the number of children of the node <I>parent</I>
	 */

	public int getChildCount(Object parent) {
		if (parent instanceof TreeNode)
			return ((TreeNode) parent).getChildCount();
		return 0;
	}

	/**
	 * Returns whether the specified node is a leaf node.
	 * The way the test is performed depends on the.
	 *
	 * @param node the node to check
	 * @return true if the node is a leaf node
	 */

	public boolean isLeaf(Object node) {
		if (asksAllowsChildren && node instanceof TreeNode)
			return !((TreeNode) node).getAllowsChildren();
		return ((TreeNode) node).isLeaf();
	}

	/**
	 * Returns true if the graph allows new connections to be established.
	 */

	public boolean isConnectable() {
		return connectable;
	}

	/**
	 * Setse if the graph allows new connections to be established.
	 */

	public void setConnectable(boolean flag) {
		connectable = flag;
	}

	/**
	 * Removes <code>cells</code> from the model.
	 * Notifies the model- and undo listeners of the change.
	 */

	public void remove(Object[] roots) {
		GraphModelEdit edit = createRemoveEdit(roots);
		if (edit != null) {
			edit.execute();
			postEdit(edit);
		}
	}

	/**
	 * Update the handle using createHandle.
	 */

	protected void updateHandle() {
		if (graphLayoutCache != null) {
			Object[] cells = graphLayoutCache.order(graph.getSelectionCells());
			if (cells != null && cells.length > 0)
				handle = createHandle(createContext(graph, cells));
			else
				handle = null;
		}
	}

	/**
	 * Returns true if the graph allows existing connections to be removed.
	 */

	public boolean isDisconnectable() {
		return disconnectable;
	}

	/**
	 * Sends <code>cells</code> to back.
	 */

	public void toBack(Object[] cells) {
		GraphModelLayerEdit edit =
			createLayerEdit(cells, GraphModelLayerEdit.BACK);
		if (edit != null) {
			edit.execute();
			postEdit(edit);
		}
	}

	/**
	 * Brings <code>cells</code> to front.
	 */

	public void toFront(Object[] cells) {
		GraphModelLayerEdit edit =
			createLayerEdit(cells, GraphModelLayerEdit.FRONT);
		if (edit != null) {
			edit.execute();
			postEdit(edit);
		}
	}

	/**
	 * Sets if the graph allows existing connections to be removed.
	 */

	public void setDisconnectable(boolean flag) {
		disconnectable = flag;
	}

	/**
	 * Returns true if cells are cloned on CTRL-Drag operations.
	 */

	public boolean isCloneable() {
		return cloneable;
	}

	/**
	 * Constructs the "root handle" for <code>context</code>.
	 *
	 * @param context reference to the context of the current selection.
	 */

	public CellHandle createHandle(GraphContext context) {
		if (context != null && !context.isEmpty() && graph.isEnabled())
			return new RootHandle(context);
		return null;
	}

	/**
	 * Returns an edit that represents a remove.
	 */

	protected GraphModelEdit createRemoveEdit(Object[] cells) {
		// Remove from GraphStructure
		ConnectionSet cs = ConnectionSet.create(this, cells, true);
		// Remove from Group Structure
		ParentMap pm = ParentMap.create(this, cells, true, false);
		// Construct Edit
		//GraphModelEdit edit = new GraphModelEdit(cells, cs, pm);
		GraphModelEdit edit = createEdit(null, cells, null, cs, pm);
		if (edit != null)
			edit.end();
		return edit;
	}

	/**
	 * Sets if cells are cloned on CTRL-Drag operations.
	 */

	public void setCloneable(boolean flag) {
		cloneable = flag;
	}

	/**
	 * Messages the Graph with <code>graphDidChange</code>.
	 */

	public void updateSize() {
		validCachedPreferredSize = false;
		graph.graphDidChange();
		updateHandle();
	}

	/**
	 * Returns true if the graph allows cells to be resized.
	 */

	public boolean isSizeable() {
		return sizeable;
	}

	/**
	 * Sets if the graph allows cells to be resized.
	 */

	public void setSizeable(boolean flag) {
		sizeable = flag;
	}

	/**
	 * Applies <code>connectionSet</code> to the model. Returns
	 * a connection set that may be used to undo this change.
	 */

	protected ConnectionSet handleConnectionSet(ConnectionSet cs) {
		if (cs != null) {
			ConnectionSet csundo = new ConnectionSet();
			Iterator it = cs.connections();
			while (it.hasNext()) {
				ConnectionSet.Connection c =
					(ConnectionSet.Connection) it.next();
				Object edge = c.getEdge();
				if (c.isSource())
					csundo.connect(edge, getSource(edge), true);
				else
					csundo.connect(edge, getTarget(edge), false);
				handleConnection(c);
			}
			return csundo;
		}
		return null;
	}

	/** Sets the preferred minimum size.
	  */

	public void setPreferredMinSize(Dimension newSize) {
		preferredMinSize = newSize;
	}

	/**
	 * Inserts the specified connection into the model.
	 */

	protected void handleConnection(ConnectionSet.Connection c) {
		Object edge = c.getEdge();
		Object old = (c.isSource()) ? getSource(edge) : getTarget(edge);
		Object port = c.getPort();
		if (port != old) {
			connect(edge, old, c.isSource(), true);
			if (contains(port) && contains(edge))
				connect(edge, port, c.isSource(), false);
		}
	}

	/**
	 * Returns true if selected edges should be disconnected from
	 * unselected vertices when they are moved.
	 */

	public boolean isDisconnectOnMove() {
		return disconnectOnMove && disconnectable;
	}

	/** Returns the minimum preferred size.
	  */

	public Dimension getPreferredMinSize() {
		if (preferredMinSize == null)
			return null;
		return new Dimension(preferredMinSize);
	}

	/**
	 * Adds a listener for the GraphModelEvent posted after the graph changes.
	 *
	 * @see     #removeGraphModelListener
	 * @param   l       the listener to add
	 */

	public void addGraphModelListener(GraphModelListener l) {
		listenerList.add(GraphModelListener.class, l);
	}

	/**
	 * Sets if selected edges should be disconnected from
	 * unselected vertices when they are moved.
	 */

	public void setSelectNewCells(boolean flag) {
		selectNewCells = flag;
	}

	/**
	 * Removes a listener previously added with <B>addGraphModelListener()</B>.
	 *
	 * @see     #addGraphModelListener
	 * @param   l       the listener to remove
	 */

	public void removeGraphModelListener(GraphModelListener l) {
		listenerList.remove(GraphModelListener.class, l);
	}

	/**
	 * Returns true if selected edges should be disconnected from
	 * unselected vertices when they are moved.
	 */

	public boolean isSelectNewCells() {
		return selectNewCells;
	}

	/** Returns the preferred size to properly display the graph.
	  */

	public Dimension getPreferredSize(JComponent c) {
		Dimension pSize = this.getPreferredMinSize();

		if (!validCachedPreferredSize)
			updateCachedPreferredSize();
		if (graph != null) {
			if (pSize != null)
				return new Dimension(
					Math.max(pSize.width, preferredSize.width),
					Math.max(pSize.height, preferredSize.height));
			return new Dimension(preferredSize.width, preferredSize.height);
		} else if (pSize != null)
			return pSize;
		else
			return new Dimension(0, 0);
	}

	/**
	 * Sets if selected edges should be disconnected from
	 * unselected vertices when they are moved.
	 */

	public void setDisconnectOnMove(boolean flag) {
		disconnectOnMove = flag;
	}

	/**
	  * Returns the minimum size for this component.  Which will be
	  * the min preferred size or 0, 0.
	  */

	public Dimension getMinimumSize(JComponent c) {
		if (this.getPreferredMinSize() != null)
			return this.getPreferredMinSize();
		return new Dimension(0, 0);
	}

		/**
		 * Adds the groups that become empty to the cells that
		 * will be removed. (Auto remove empty cells.) Removed
		 * cells will be re-inserted on undo, and the parent-
		 * child relations will be restored.
		 */

		protected void handleEmptyGroups(Object[] groups) {
			if (groups != null && groups.length > 0) {
				if (remove == null)
					remove = new Object[] {
				};
				Object[] tmp = new Object[remove.length + groups.length];
				System.arraycopy(remove, 0, tmp, 0, remove.length);
				System.arraycopy(groups, 0, tmp, remove.length, groups.length);
				remove = tmp;
			}
		}

	/**
	 * Returns true if the grid is active.
	 * @see #snap
	 *
	 */

	public boolean isGridEnabled() {
		return gridEnabled;
	}

	/**
	  * Returns the maximum size for this component, which will be the
	  * preferred size if the instance is currently in a JGraph, or 0, 0.
	  */

	public Dimension getMaximumSize(JComponent c) {
		if (graph != null)
			return getPreferredSize(graph);
		if (this.getPreferredMinSize() != null)
			return this.getPreferredMinSize();
		return new Dimension(0, 0);
	}

		/**
		 * Returns the source of this change. This can either be a
		 * view or a model, if this change is a GraphModelChange.
		 */

		public Object getSource() {
			return DefaultGraphModel.this;
		}

	/**
	 * If set to true, the grid will be active.
	 * @see #snap
	 *
	 */

	public void setGridEnabled(boolean flag) {
		gridEnabled = flag;
	}

		/**
		 * Returns a map that contains (object, map) pairs
		 * of the attributes that have been stored in the model.
		 */

		public Map getPreviousAttributes() {
			return previousAttributes;
		}

	/**
	 * Messages to stop the editing session. If the UI the receiver
	 * is providing the look and feel for returns true from
	 * <code>getInvokesStopCellEditing</code>, stopCellEditing will
	 * invoked on the current editor. Then completeEditing will
	 * be messaged with false, true, false to cancel any lingering
	 * editing.
	 */

	protected void completeEditing() {
		/* If should invoke stopCellEditing, try that */
		if (graph.getInvokesStopCellEditing()
			&& stopEditingInCompleteEditing
			&& editingComponent != null) {
			cellEditor.stopCellEditing();
		}
		/* Invoke cancelCellEditing, this will do nothing if stopCellEditing
		   was succesful. */
		completeEditing(false, true, false);
	}

	/**
	 * Returns the maximum distance between the mousepointer and a cell to
	 * be selected.
	 */

	public int getTolerance() {
		return tolerance;
	}

		/**
		 * Returns the connectionSet.
		 * @return ConnectionSet
		 */

		public ConnectionSet getConnectionSet() {
			return connectionSet;
		}

	/**
	 * Sets the maximum distance between the mousepointer and a cell to
	 * be selected.
	 */

	public void setTolerance(int size) {
		tolerance = size;
	}

		/**
		 * Returns the parentMap.
		 * @return ParentMap
		 */

		public ParentMap getParentMap() {
			return parentMap;
		}

	/**
	 * Returns the size of the handles.
	 */

	public int getHandleSize() {
		return handleSize;
	}

	/**
	 * Sets the size of the handles.
	 */

	public void setHandleSize(int size) {
		handleSize = size;
	}

		/**
		 * Redoes a change.
		 *
		 * @exception CannotRedoException if the change cannot be redone
		 */

		public void redo() throws CannotRedoException {
			super.redo();
			execute();
		}

		/**
		 * Undoes a change.
		 *
		 * @exception CannotUndoException if the change cannot be undone
		 */

		public void undo() throws CannotUndoException {
			super.undo();
			execute();
		}

	/**
	 * Returns the miminum amount of pixels for a move operation.
	 */

	public int getMinimumMove() {
		return minimumMove;
	}

		/**
		 * Creates, if necessary, and starts a Timer to check if need to
		 * resize the bounds.
		 */

		protected void startTimer() {
			if (timer == null) {
				timer = new Timer(200, this);
				timer.setRepeats(true);
			}
			timer.start();
		}

	/**
	 * Sets the miminum amount of pixels for a move operation.
	 */

	public void setMinimumMove(int pixels) {
		minimumMove = pixels;
	}

		/**
		 * Returns the JScrollPane housing the JGraph, or null if one isn't
		 * found.
		 */

		protected JScrollPane getScrollPane() {
			Component c = graph.getParent();

			while (c != null && !(c instanceof JScrollPane))
				c = c.getParent();
			if (c instanceof JScrollPane)
				return (JScrollPane) c;
			return null;
		}

	/**
	 * Returns the current grid color.
	 */

	public Color getGridColor() {
		return gridColor;
	}

		/**
		 * Public as a result of Timer. If the scrollBar is null, or
		 * not adjusting, this stops the timer and updates the sizing.
		 */

		public void actionPerformed(ActionEvent ae) {
			if (scrollBar == null || !scrollBar.getValueIsAdjusting()) {
				if (timer != null)
					timer.stop();
				updateSize();
				timer = null;
				scrollBar = null;
			}
		}

	/**
	 * Sets the current grid color.
	 */

	public void setGridColor(Color newColor) {
		gridColor = newColor;
	}

	/**
	 * Returns the current handle color.
	 */

	public Color getHandleColor() {
		return handleColor;
	}

		/**
		 * Allows a <code>GraphLayoutCache</code> to add and execute and
		 * UndoableEdit in this change. This does also work if the
		 * parent edit has already been executed, in which case the
		 * to be added edit will be executed immediately, after
		 * addition.
		 * This is used to handle changes to the view that are 
		 * triggered by certain changes of the model. Such implicit
		 * edits may be associated with the view so that they may be
		 * undone and redone correctly, and are stored in the model's
		 * global history together with the parent event as one unit.
		 */

		public void addImplicitEdit(UndoableEdit edit) {
			// ignore	
		}

	/**
	 * Sets the current handle color.
	 */

	public void setHandleColor(Color newColor) {
		handleColor = newColor;
	}

		/**
		 * Returns the views that have not changed explicitly, but
		 * implicitly because one of their dependent cells has changed.
		 */

		public void putViews(GraphLayoutCache view, CellView[] cellViews) {
			// ignore
		}

	/**
	 * Returns the current second handle color.
	 */

	public Color getLockedHandleColor() {
		return lockedHandleColor;
	}

		/**
		 * Messaged when the selection changes in the graph we're displaying
		 * for. Stops editing, updates handles and displays the changed cells.
		 */

		public void valueChanged(GraphSelectionEvent event) {
			//cancelEditing(graph);
			updateHandle();
			Object[] cells = event.getCells();
			if (cells != null && cells.length <= MAXCLIPCELLS) {
				Rectangle r = graph.toScreen(graph.getCellBounds(cells));
				if (r != null) {
					int hsize = graph.getHandleSize();
					r.grow(hsize, hsize); //padding to paint handles
					updateHandle();
					graph.repaint(r);
				}
			} else
				graph.repaint();
		}

		/**
		 * Returns the list that exclusively contains <code>view</code>.
		 */

		protected List getParentList(Object cell) {
			List list = null;
			if (cell instanceof DefaultMutableTreeNode) {
				Object parent = ((DefaultMutableTreeNode) cell).getParent();
				if (parent instanceof DefaultGraphCell)
					list = ((DefaultGraphCell) parent).getChildren();
				else
					list = roots;
			}
			return list;
		}

	/**
	 * Sets the current second handle color.
	 */

	public void setLockedHandleColor(Color newColor) {
		lockedHandleColor = newColor;
	}

		/** Messaged when editing has stopped in the graph. */

		public void editingStopped(ChangeEvent e) {
			completeEditing(false, false, true);
		}

	/**
	 * Returns the current marquee color.
	 */

	public Color getMarqueeColor() {
		return marqueeColor;
	}

		/** Messaged when editing has been canceled in the graph. */

		public void editingCanceled(ChangeEvent e) {
			completeEditing(false, false, false);
		}

	/**
	 * Sets the current marquee color.
	 */

	public void setMarqueeColor(Color newColor) {
		marqueeColor = newColor;
	}

	/**
	  * Sets the color to use for the border.
	  */

	public void setBorderSelectionColor(Color newColor) {
		borderSelectionColor = newColor;
	}

	/**
	  * Returns the color the border is drawn.
	  */

	public Color getBorderSelectionColor() {
		return borderSelectionColor;
	}

	/**
	 * Sets the font to edit with. null indicates the renderers font should
	 * be used. This will NOT override any font you have set in the editor
	 * the receiver was instantied with. If null for an editor was passed in
	 * a default editor will be created that will pick up this font.
	 *
	 * @param font  the editing Font
	 * @see #getFont
	 */

	public void setFont(Font font) {
		this.font = font;
	}

	/**
	 * Returns the current highlight color.
	 */

	public Color getHighlightColor() {
		return highlightColor;
	}

	/**
	 * Gets the font used for editing.
	 *
	 * @return the editing Font
	 * @see #setFont
	 */

	public Font getFont() {
		return font;
	}

	/**
	 * Returns the value currently being edited.
	 */

	public Object getCellEditorValue() {
		return realEditor.getCellEditorValue();
	}

	/**
	 * If the realEditor returns true to this message, prepareForEditing
	 * is messaged and true is returned.
	 */

	public boolean isCellEditable(EventObject event) {
		boolean retValue = false;

		if (!realEditor.isCellEditable(event))
			return false;
		if (canEditImmediately(event))
			retValue = true;
		if (retValue)
			prepareForEditing();
		return retValue;
	}

	/**
	 * Messages the realEditor for the return value.
	 */

	public boolean shouldSelectCell(EventObject event) {
		return realEditor.shouldSelectCell(event);
	}

	/**
	 * Sets the current selection highlight color.
	 */

	public void setHighlightColor(Color newColor) {
		highlightColor = newColor;
	}

	/**
	 * If the realEditor will allow editing to stop, the realEditor is
	 * removed and true is returned, otherwise false is returned.
	 */

	public boolean stopCellEditing() {
		if (realEditor.stopCellEditing()) {
			if (editingComponent != null)
				editingContainer.remove(editingComponent);
			editingComponent = null;
			return true;
		}
		return false;
	}

	/**
	 * Messages cancelCellEditing to the realEditor and removes it from this
	 * instance.
	 */

	public void cancelCellEditing() {
		realEditor.cancelCellEditing();
		if (editingComponent != null)
			editingContainer.remove(editingComponent);
		editingComponent = null;
	}

	/**
	 * Adds the CellEditorListener.
	 */

	public void addCellEditorListener(CellEditorListener l) {
		realEditor.addCellEditorListener(l);
	}

	/**
	  * Removes the previously added CellEditorListener l.
	  */

	public void removeCellEditorListener(CellEditorListener l) {
		realEditor.removeCellEditorListener(l);
	}

	/**
	 * Resets lastPath.
	 */

	public void valueChanged(GraphSelectionEvent e) {
		if (graph != null) {
			if (graph.getSelectionCount() == 1)
				lastCell = graph.getSelectionCell();
			else
				lastCell = null;
		}
	}

	/**
	 * Messaged when the timer fires, this will start the editing
	 * session.
	 */

	public void actionPerformed(ActionEvent e) {
		if (graph != null)
			graph.startEditingAtCell(lastCell);
	}

	/**
	 * Returns the current scale.
	 * @return the current scale as a double
	 */

	public double getScale() {
		return scale;
	}

	/**
	 * Sets the tree currently editing for. This is needed to add
	 * a selection listener.
	 */

	protected void setGraph(JGraph newGraph) {
		if (graph != newGraph) {
			if (graph != null)
				graph.removeGraphSelectionListener(this);
			graph = newGraph;
			if (graph != null)
				graph.addGraphSelectionListener(this);
		}
	}

	/**
	 * Returns true if <code>event</code> is a MouseEvent and the click
	 * count is 1.
	 */

	protected boolean shouldStartEditingTimer(EventObject event) {
		if ((event instanceof MouseEvent)
			&& SwingUtilities.isLeftMouseButton((MouseEvent) event)) {
			MouseEvent me = (MouseEvent) event;

			return (
				me.getClickCount() == 1 && inHitRegion(me.getX(), me.getY()));
		}
		return false;
	}

	/**
	 * Should return true if the passed in location is a valid mouse location
	 * to start editing from. This is implemented to return false if
	 * <code>x</code> is <= the width of the icon and icon gap displayed
	 * by the renderer. In other words this returns true if the user
	 * clicks over the text part displayed by the renderer, and false
	 * otherwise.
	 */

	protected boolean inHitRegion(int x, int y) {
		if (lastCell != null && graph != null) {
			Rectangle bounds = graph.getCellBounds(lastCell);

			if (bounds != null
				&& x <= (bounds.x + offsetX)
				&& y <= (bounds.y + offsetY)
				&& offsetX < (bounds.width - 5)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Sets the current scale.
	 * <p>
	 * Fires a property change for the SCALE_PROPERTY.
	 * @param newValue the new scale
	 */

	public void setScale(double newValue) {
		if (newValue > 0) {
			double oldValue = this.scale;
			scale = newValue;
			firePropertyChange(SCALE_PROPERTY, oldValue, newValue);
		}
	}

	/**
	 * Invoked just before editing is to start. Will add the
	 * <code>editingComponent</code> to the
	 * <code>editingContainer</code>.
	 */

	protected void prepareForEditing() {
		editingContainer.add(editingComponent);
	}

	/**
	 * Creates the container to manage placement of editingComponent.
	 */

	protected Container createContainer() {
		return new EditorContainer();
	}

	/**
	 * Returns the size of the grid in pixels.
	 * @return the size of the grid as an int
	 */

	public int getGridSize() {
		return gridSize;
	}

		/**
		 * Overrides <code>JComponent.getBorder</code> to
		 * returns the current border.
		 */

		public Border getBorder() {
			return border;
		}

	/**
	 * Returns the current grid view mode.
	 */

	public int getGridMode() {
		return gridMode;
	}

		/**
		 * Overrides <code>Container.paint</code> to paint the node's
		 * icon and use the selection color for the background.
		 */

		public void paint(Graphics g) {
			Dimension size = getSize();

			// Then the icon.
			if (editingIcon != null) {
				int yLoc = 0;
				int xLoc = 0;
				editingIcon.paintIcon(this, g, xLoc, yLoc);
			}

			// Border selection color
			Color background = getBorderSelectionColor();
			if (background != null) {
				g.setColor(background);
				g.drawRect(0, 0, size.width - 1, size.height - 1);
			}
			super.paint(g);
		}

		/**
		 * Lays out this Container.  If editing, the editor will be placed at
		 * offset in the x direction and 0 for y.
		 */

		public void doLayout() {
			if (editingComponent != null) {
				Dimension cSize = getSize();
				int h = (int) editingComponent.getPreferredSize().getHeight();
				editingComponent.setBounds(
					offsetX,
					offsetY,
					cSize.width - offsetX,
					h);
			}
		}

		/**
		 * Returns the preferred size for the Container.  This will be
		 * the preferred size of the editor offset by offset.
		 */

		public Dimension getPreferredSize() {
			if (editingComponent != null) {
				Dimension pSize = editingComponent.getPreferredSize();

				pSize.width += offsetX + 2;
				pSize.height += offsetY + 2;

				// Make sure width is at least 50.
				// and height at least 20
				int iwidth = 50;
				if (editingIcon != null) {
					iwidth = Math.max(editingIcon.getIconWidth(), iwidth);
				}
				pSize.height = Math.max(pSize.height, 24); // Offset 4
				pSize.width = Math.max(pSize.width, iwidth);
				return pSize;
			}
			return new Dimension(0, 0);
		}

		/**
		 * Invoked when the mouse pointer has been moved on a component
		 * (with no buttons down).
		 */

		public void mouseMoved(MouseEvent e) {
			if (previousCursor == null)
				previousCursor = graph.getCursor();
			if (graph != null && graph.isEnabled()) {
				if (marquee != null)
					marquee.mouseMoved(e);
				if (handle != null)
					handle.mouseMoved(e);
				if (!e.isConsumed() && previousCursor != null) {
					graph.setCursor(previousCursor);
					previousCursor = null;
				}
			}
		}

	/**
	 * Overrides the parent method to udpate the cached points.
	 */

	public void update() {
		super.update();
		bounds = GraphConstants.getBounds(allAttributes);
		groupBounds = null;
	}

	/**
	 * Sets the size of the grid.
	 * <p>
	 * Fires a property change for the GRID_SIZE_PROPERTY.
	 * @param newSize the new size of the grid in pixels
	 */

	public void setGridSize(int newSize) {
		int oldValue = this.gridSize;

		this.gridSize = newSize;
		firePropertyChange(GRID_SIZE_PROPERTY, oldValue, newSize);
	}

	/**
	 * Returns the cached bounds for the vertex.
	 */

	public Rectangle getBounds() {
		if (!isLeaf()) {
			if (groupBounds == null)
				updateGroupBounds();
			return groupBounds;
		}
		return bounds;
	}

	/**
	 * Sets the current grid view mode.
	 *
	 * @param mode The current grid view mode. Valid values are
	 *    <CODE>DOT_GRID_MODE</CODE>,
	 *    <CODE>CROSS_GRID_MODE</CODE>, and
	 *    <CODE>LINE_GRID_MODE</CODE>.
	 */

	public void setGridMode(int mode) {
		if (mode == DOT_GRID_MODE
			|| mode == CROSS_GRID_MODE
			|| mode == LINE_GRID_MODE) {
			gridMode = mode;
			repaint();
		}
	}

	/**
	 * Returns a cell handle for the view, if the graph and the view
	 * are sizeable.
	 */

	public CellHandle getHandle(GraphContext context) {
		if (GraphConstants.isSizeable(getAllAttributes())
			&& context.getGraph().isSizeable())
			return new SizeHandle(this, context);
		return null;
	}

	/**
	 * Returns the center of this vertex.
	 */

	public Point getCenterPoint() {
		Rectangle r = getBounds();
		return new Point((int) r.getCenterX(), (int) r.getCenterY());
	}

	/**
	 * Returns the intersection of the bounding rectangle and the
	 * straight line between the source and the specified point p.
	 * The specified point is expected not to intersect the bounds.
	 * Note: You must override this method if you use a different
	 * renderer. This is because this method relies on the
	 * VertexRenderer interface, which can not be safely assumed
	 * for subclassers.
	 */

	public Point getPerimeterPoint(Point source, Point p) {
		return renderer.getPerimeterPoint(this, source, p);
	}

	/**
	 * Returns true if the grid will be visible.
	 * @return true if the grid is visible
	 */

	public boolean isGridVisible() {
		return gridVisible;
	}

		/**
		 * Invoked when the mouse pointer has been moved on a component
		 * (with no buttons down).
		 */

		public void mouseMoved(MouseEvent event) {
			if (vertex != null) {
				for (int i = 0; i < r.length; i++) {
					if (r[i].contains(event.getPoint())) {
						graph.setCursor(new Cursor(cursors[i]));
						event.consume();
						return;
					}
				}
			}
		}

		/** Process mouse pressed event. */

		public void mousePressed(MouseEvent event) {
			if (!graph.isSizeable())
				return;
			for (int i = 0; i < r.length; i++) {
				if (r[i].contains(event.getPoint())) {
					Set set = new HashSet();
					set.add(vertex.getCell());
					contextViews = context.createTemporaryContextViews(set);
					Object[] all =
						AbstractCellView.getDescendantViews(
							new CellView[] { vertex });
					if (all.length
						>= org.jgraph.plaf.basic.BasicGraphUI.MAXHANDLES)
						cachedBounds = new Rectangle(initialBounds);
					event.consume();
					index = i;
					return;
				}
			}
		}

	/**
	 * If set to true, the grid will be visible. <p>
	 * Fires a property change for the GRID_VISIBLE_PROPERTY.
	 */

	public void setGridVisible(boolean flag) {
		boolean oldValue = gridVisible;

		gridVisible = flag;
		firePropertyChange(GRID_VISIBLE_PROPERTY, oldValue, flag);
	}

	/**
	 * Adds the connections in <code>views</code> to the connection set.
	 */

	public void addConnections(CellView[] views) {
		for (int i = 0; i < views.length; i++) {
			if (views[i] instanceof EdgeView) {
				EdgeView edgeView = (EdgeView) views[i];
				Object edge = edgeView.getCell();
				CellView sourceView = edgeView.getSource();
				CellView targetView = edgeView.getTarget();
				Object source = null;
				if (sourceView != null)
					source = sourceView.getCell();
				Object target = null;
				if (targetView != null)
					target = targetView.getCell();
				connect(edge, source, target);
			}
		}
	}

	/**
	 * Returns true if the ports will be visible.
	 * @return true if the ports are visible
	 */

	public boolean isPortsVisible() {
		return portsVisible;
	}

	/**
	 * Connect <code>edge</code> to <code>source</code>
	 * and <code>target</code> in the connection set. The
	 * previous connections between <code>edge</code> and
	 * its source and target are replaced in the set.
	 */

	public void connect(Object edge, Object source, Object target) {
		connect(edge, source, true);
		connect(edge, target, false);
	}

	/**
	 * Connect <code>edge</code> to <code>port</code>.
	 * <code>source</code> indicates if <code>port</code> is the
	 * source of <code>edge</code>. The previous connections
	 * between <code>edge</code> and its source or target
	 * in the set is replaced.
	 */

	public void connect(Object edge, Object port, boolean source) {
		Connection c = new Connection(edge, port, source);
		connections.remove(c);
		connections.add(c);
		edges.add(edge);
	}

	/**
	 * Disconnect <code>edge</code> from <code>source</code>
	 * and <code>target</code> in the connection set. The
	 * previous connections between <code>edge</code> and
	 * its source and target are replaced in the set.
	 */

	public void disconnect(Object edge) {
		disconnect(edge, true);
		disconnect(edge, false);
	}

	/**
	 * Disconnect <code>edge</code> from <code>port</code>.
	 * <code>source</code> indicates if <code>port</code> is the
	 * source of <code>edge</code>.  The previous connections
	 * between <code>edge</code> and its source or target
	 * in the set is replaced.
	 */

	public void disconnect(Object edge, boolean source) {
		connections.add(new Connection(edge, null, source));
		edges.add(edge);
	}

	/**
	 * Returns <code>true</code> if the connection set is empty.
	 */

	public boolean isEmpty() {
		return connections.isEmpty();
	}

	/**
	 * Returns the number of (edge, port)-pairs.
	 */

	public int size() {
		return connections.size();
	}

	/**
	 * Returns an <code>Iterator</code> for the connections in this set.
	 */

	public Iterator connections() {
		return connections.iterator();
	}

	/**
	 * Returns a <code>Set</code> for the edges in this connection set.
	 */

	public Set getChangedEdges() {
		return edges;
	}

	/**
	 * If set to true, the ports will be visible. <p>
	 * Fires a property change for the PORTS_VISIBLE_PROPERTY.
	 */

	public void setPortsVisible(boolean flag) {
		boolean oldValue = portsVisible;

		portsVisible = flag;
		firePropertyChange(PORTS_VISIBLE_PROPERTY, oldValue, flag);
	}

	/**
	 * Creates a new connection set based on this connection set,
	 * where the edges, and ports are mapped using <code>map</code>.
	 * If a port is not found, the old port is used. If both, the
	 * edge and the port are not in <code>map</code>, the entry
	 * is ignored.<p>
	 * <strong>Note:</strong> Consequently, unselected edges are
	 * only reconnected at the first "paste" after a "cut", because
	 * in this case the ConnectionSet is not cloned.
	 */

	public ConnectionSet clone(Map map) {
		ConnectionSet cs = new ConnectionSet();
		Iterator it = connections();
		while (it.hasNext()) {
			// Shortcut Vars
			Connection c = (Connection) it.next();
			Object edge = map.get(c.getEdge());
			Object port = c.getPort();
			if (port != null)
				port = map.get(port);
			// New Port
			if (edge != null && port != null)
				cs.connect(edge, port, c.isSource());
			// Old Port
			else if (edge != null)
				cs.connect(edge, c.getPort(), c.isSource());
		}
		return cs;
	}

		/**
		 * Returns the edge of the connection.
		 */

		public Object getEdge() {
			return edge;
		}

		/**
		 * Returns the port of the connection.
		 */

		public Object getPort() {
			return port;
		}

		/**
		 *  Returns <code>true</code> if <code>port</code> is the source
		 *  of <code>edge</code>.
		 */

		public boolean isSource() {
			return isSource;
		}

		/**
		 * Two connections are equal if they represent the source
		 * or target of the same edge. That is, if <p>
		 * c1.edge == c2.edge && c1.isSource == c2.isSource.
		 */

		public boolean equals(Object obj) {
			if (obj instanceof Connection) {
				Connection other = (Connection) obj;
				return (
					other.getEdge().equals(edge)
						&& other.isSource() == isSource);
			}
			return false;
		}

		/**
		 * Ensure equality of hashCode wrt. equals().
		 */

		public int hashCode() {
			return edge.hashCode();
		}

	/**
	 * Returns true if the graph will be anti aliased.
	 * @return true if the graph is anti aliased
	 */

	public boolean isAntiAliased() {
		return antiAliased;
	}

	/**
	 * Sets antialiasing on or off based on the boolean value.
	 * <p>
	 * Fires a property change for the ANTIALIASED_PROPERTY.
	 * @param newValue whether to turn antialiasing on or off
	 */

	public void setAntiAliased(boolean newValue) {
		boolean oldValue = this.antiAliased;

		this.antiAliased = newValue;
		firePropertyChange(ANTIALIASED_PROPERTY, oldValue, newValue);
	}

	/**
	 * Sets the selection mode, which must be one of SINGLE_TREE_SELECTION,
	 */

	public void setSelectionMode(int mode) {
		int oldMode = selectionMode;

		selectionMode = mode;
		if (selectionMode != GraphSelectionModel.MULTIPLE_GRAPH_SELECTION
			&& selectionMode != GraphSelectionModel.SINGLE_GRAPH_SELECTION)
			selectionMode = GraphSelectionModel.MULTIPLE_GRAPH_SELECTION;
		if (oldMode != selectionMode && changeSupport != null)
			changeSupport.firePropertyChange(
				SELECTION_MODE_PROPERTY,
				new Integer(oldMode),
				new Integer(selectionMode));
	}

	/**
	 * Returns the selection mode, one of <code>SINGLE_TREE_SELECTION</code>,
	 * <code>DISCONTIGUOUS_TREE_SELECTION</code> or
	 * <code>CONTIGUOUS_TREE_SELECTION</code>.
	 */

	public int getSelectionMode() {
		return selectionMode;
	}

	/**
	  * Sets if the selection model allows the selection
	  * of children.
	  */

	public void setChildrenSelectable(boolean flag) {
		childrenSelectable = flag;
	}

	/**
	  * Returns true if the selection model allows the selection
	  * of children.
	  */

	public boolean isChildrenSelectable() {
		return childrenSelectable;
	}

	/**
	 * Returns true if the graph is editable, ie. if it allows
	 * cells to be edited.
	 * @return true if the graph is editable
	 */

	public boolean isEditable() {
		return editable;
	}

	/**
	  * Hook for subclassers for fine-grained control over stepping-into cells.
	  * This implementation returns <code>childrenSelectable</code>.
	  */

	protected boolean isChildrenSelectable(Object cell) {
		return childrenSelectable;
	}

	/**
	  * Sets the selection to path. If this represents a change, then
	  * the TreeSelectionListeners are notified. If <code>path</code> is
	  * null, this has the same effect as invoking <code>clearSelection</code>.
	  *
	  * @param path new path to select
	  */

	public void setSelectionCell(Object cell) {
		if (cell == null)
			setSelectionCells(null);
		else
			setSelectionCells(new Object[] { cell });
	}

		/**
		 * Invoked when the mouse pointer has been moved on a component
		 * (with no buttons down).
		 */

		public void mouseMoved(MouseEvent event) {
			if (!event.isConsumed() && handles != null)
				for (int i = handles.length - 1;
					i >= 0 && !event.isConsumed();
					i--)
					if (handles[i] != null)
						handles[i].mouseMoved(event);
		}

	/**
	  * Adds path to the current selection. If path is not currently
	  * in the selection the TreeSelectionListeners are notified. This has
	  * no effect if <code>path</code> is null.
	  *
	  * @param path the new path to add to the current selection
	  */

	public void addSelectionCell(Object cell) {
		if (cell != null)
			addSelectionCells(new Object[] { cell });
	}

	/**
	  * Adds cells to the current selection. If any of the paths in
	  * paths are not currently in the selection the TreeSelectionListeners
	  * are notified. This has
	  * no effect if <code>paths</code> is null.
	  * <p>The lead path is set to the last element in <code>paths</code>.
	  * <p>If the selection mode is <code>CONTIGUOUS_TREE_SELECTION</code>,
	  * and adding the new paths would make the selection discontiguous.
	  * Then two things can result: if the TreePaths in <code>paths</code>
	  * are contiguous, then the selection becomes these TreePaths,
	  * otherwise the TreePaths aren't contiguous and the selection becomes
	  * the first TreePath in <code>paths</code>.
	  *
	  * @param path the new path to add to the current selection
	  */

	public void addSelectionCells(Object[] cells) {
		if (cells != null) {
			if (selectionMode == GraphSelectionModel.SINGLE_GRAPH_SELECTION)
				setSelectionCells(cells);
			else {
				Vector change = new Vector();
				for (int i = 0; i < cells.length; i++) {
					if (cells[i] != null) {
						boolean newness = select(selection, cells[i]);
						if (newness) {
							change.addElement(
								new CellPlaceHolder(cells[i], true));
							Object parent =
								graph.getModel().getParent(cells[i]);
							if (parent != null)
								change.addElement(
									new CellPlaceHolder(parent, false));
						}
					}
				}
				if (change.size() > 0)
					notifyCellChange(change);
			}
		}
	}

	/**
	 * Determines whether the graph is editable. Fires a property
	 * change event if the new setting is different from the existing
	 * setting.
	 * <p>
	 * Note: Editable determines whether the graph allows editing. This
	 * is not to be confused with enabled, which allows the graph to
	 * handle mouse events (including editing).
	 * @param flag a boolean value, true if the graph is editable
	 */

	public void setEditable(boolean flag) {
		boolean oldValue = this.editable;

		this.editable = flag;
		firePropertyChange(EDITABLE_PROPERTY, oldValue, flag);
	}

	/**
	  * Removes path from the selection. If path is in the selection
	  * The TreeSelectionListeners are notified. This has no effect if
	  * <code>path</code> is null.
	  *
	  * @param path the path to remove from the selection
	  */

	public void removeSelectionCell(Object cell) {
		if (cell != null)
			removeSelectionCells(new Object[] { cell });
	}

	/**
	  * Removes paths from the selection.  If any of the paths in paths
	  * are in the selection the TreeSelectionListeners are notified.
	  * This has no effect if <code>paths</code> is null.
	  *
	  * @param path the path to remove from the selection
	  */

	public void removeSelectionCells(Object[] cells) {
		if (cells != null) {
			Vector change = new Vector();
			for (int i = 0; i < cells.length; i++) {
				if (cells[i] != null) {
					boolean removed = deselect(cells[i]);
					if (removed) {
						change.addElement(new CellPlaceHolder(cells[i], false));
						Object parent = graph.getModel().getParent(cells[i]);
						if (parent != null)
							change.addElement(
								new CellPlaceHolder(parent, false));
					}
				}
			}
			if (change.size() > 0)
				notifyCellChange(change);
		}
	}

	/**
	  * Returns the first cell in the selection. This is useful if there
	  * if only one item currently selected.
	  */

	public Object getSelectionCell() {
		if (selection != null && selection.size() > 0)
			return selection.toArray()[0];
		return null;
	}

	/**
	 * Returns the number of paths that are selected.
	 */

	public int getSelectionCount() {
		return (selection == null) ? 0 : selection.size();
	}

	/**
	 * Returns the <code>GraphModel</code> that is providing the data.
	 * @return the model that is providing the data
	 */

	public GraphModel getModel() {
		return graphModel;
	}

	/**
	  * Returns true if the cell, <code>cell</code>,
	  * is in the current selection.
	  */

	public boolean isCellSelected(Object cell) {
		int count = getSelectedChildCount(cell);
		return (count == SELECTED);
	}

	/**
	  * Returns true if the cell, <code>cell</code>,
	  * has selected children.
	  */

	public boolean isChildrenSelected(Object cell) {
		int count = getSelectedChildCount(cell);
		return (count > 0);
	}

	/**
	  * Returns true if the selection is currently empty.
	  */

	public boolean isSelectionEmpty() {
		return (selection.isEmpty());
	}

		/**
		 * Hook for subclassers to return a different view for a mouse click
		 * at <code>pt</code>. For example, this can be used to return a leaf
		 * cell instead of a group.
		 */

		protected CellView findViewForPoint(Point pt) {
			int snap = graph.getTolerance();
			Rectangle r =
				new Rectangle(pt.x - snap, pt.y - snap, 2 * snap, 2 * snap);
			for (int i = 0; i < views.length; i++)
				if (views[i].intersects(graph.getGraphics(), r))
					return views[i];
			return null;
		}

	/**
	 * Sets the <code>GraphModel</code> that will provide the data.
	 * Note: Updates the current GraphLayoutCache's model using setModel if the
	 * GraphLayoutCache points to a different model. <p>
	 * Fires a property change for the GRAPH_MODEL_PROPERTY.
	 * @param newModel the <code>GraphModel</code> that is to provide the data
	 */

	public void setModel(GraphModel newModel) {
		GraphModel oldModel = graphModel;

		graphModel = newModel;
		firePropertyChange(GRAPH_MODEL_PROPERTY, oldModel, graphModel);
		// FIX: Use Listener
		if (graphLayoutCache != null
			&& graphLayoutCache.getModel() != graphModel)
			graphLayoutCache.setModel(graphModel);
		invalidate();
	}

	/**
	  * Empties the current selection.  If this represents a change in the
	  * current selection, the selection listeners are notified.
	  */

	public void clearSelection() {
		if (selection != null) {
			Vector change = new Vector();
			Iterator it = cellStates.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				change.addElement(new CellPlaceHolder(entry.getKey(), false));
			}
			selection.clear();
			cellStates.clear();
			if (change.size() > 0)
				notifyCellChange(change);
		}
	}

	/**
	  * Returns the number of selected childs for <code>cell</code>.
	  */

	protected int getSelectedChildCount(Object cell) {
		if (cell != null) {
			Integer state = (Integer) cellStates.get(cell);
			if (state == null) {
				state = UNSELECTED;
				cellStates.put(cell, state);
			}
			return state.intValue();
		}
		return 0;
	}

	/**
	  * Sets the number of selected childs for <code>cell</code>
	  * to <code>count</code>.
	  */

	protected void setSelectedChildCount(Object cell, int count) {
		Integer i = new Integer(count);
		cellStates.put(cell, i);
	}

	/**
	 * Returns the <code>GraphLayoutCache</code> that is providing the view-data.
	 * @return the view that is providing the view-data
	 */

	public GraphLayoutCache getGraphLayoutCache() {
		return graphLayoutCache;
	}

	/**
	  * Adds x to the list of listeners that are notified each time the
	  * set of selected TreePaths changes.
	  *
	  * @param x the new listener to be added
	  */

	public void addGraphSelectionListener(GraphSelectionListener x) {
		listenerList.add(GraphSelectionListener.class, x);
	}

	/**
	  * Removes x from the list of listeners that are notified each time
	  * the set of selected TreePaths changes.
	  *
	  * @param x the listener to remove
	  */

	public void removeGraphSelectionListener(GraphSelectionListener x) {
		listenerList.remove(GraphSelectionListener.class, x);
	}

	/**
	 * Sets the <code>GraphLayoutCache</code> that will provide the view-data. <p>
	 * Note: Updates the GraphLayoutCache's model using setModel if the
	 * GraphLayoutCache points to an other model than this graph. <p>
	 * Fires a property change for the GRAPH_LAYOUT_CACHE_PROPERTY.
	 * @param newView the <code>GraphLayoutCache</code> that is to provide the view-data
	 */

	public void setGraphLayoutCache(GraphLayoutCache newLayoutCache) {
		GraphLayoutCache oldLayoutCache = graphLayoutCache;

		graphLayoutCache = newLayoutCache;
		firePropertyChange(
			GRAPH_LAYOUT_CACHE_PROPERTY,
			oldLayoutCache,
			graphLayoutCache);
		// FIX: Use Listener
		if (graphLayoutCache != null
			&& graphLayoutCache.getModel() != getModel())
			graphLayoutCache.setModel(getModel());
		invalidate();
	}

	/**
	 * Notifies all listeners that are registered for
	 * tree selection events on this object.
	 * @see #addGraphSelectionListener
	 * @see EventListenerList
	 */

	protected void fireValueChanged(GraphSelectionEvent e) {
		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();
		// TreeSelectionEvent e = null;
		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == GraphSelectionListener.class) {
				// Lazily create the event:
				// if (e == null)
				// e = new ListSelectionEvent(this, firstIndex, lastIndex);
				 ((GraphSelectionListener) listeners[i + 1]).valueChanged(e);
			}
		}
	}

	/**
	  * Notifies listeners of a change in path. changePaths should contain
	  * instances of PathPlaceHolder.
	  */

	protected void notifyCellChange(Vector changedCells) {
		int cCellCount = changedCells.size();
		boolean[] newness = new boolean[cCellCount];
		Object[] cells = new Object[cCellCount];
		CellPlaceHolder placeholder;

		for (int counter = 0; counter < cCellCount; counter++) {
			placeholder = (CellPlaceHolder) changedCells.elementAt(counter);
			newness[counter] = placeholder.isNew;
			cells[counter] = placeholder.cell;
		}

		GraphSelectionEvent event =
			new GraphSelectionEvent(this, cells, newness);

		fireValueChanged(event);
	}

	/**
	 * Returns a clone of this object with the same selection.
	 * This method does not duplicate
	 * selection listeners and property listeners.
	 *
	 * @exception CloneNotSupportedException never thrown by instances of
	 *                                       this class
	 */

	public Object clone() throws CloneNotSupportedException {
		DefaultGraphSelectionModel clone =
			(DefaultGraphSelectionModel) super.clone();
		clone.changeSupport = null;
		if (selection != null)
			clone.selection = new ArrayList(selection);
		clone.listenerList = new EventListenerList();
		return clone;
	}

	/**
	 * Returns the <code>MarqueeHandler</code> that will handle
	 * marquee selection.
	 */

	public BasicMarqueeHandler getMarqueeHandler() {
		return marquee;
	}

		/**
		 * Returns the isNew.
		 * @return boolean
		 */

		public boolean isNew() {
			return isNew;
		}

		/**
		 * Sets the cell.
		 * @param cell The cell to set
		 */

		public void setCell(Object cell) {
			this.cell = cell;
		}

	/**
	 * Sets the <code>MarqueeHandler</code> that will handle
	 * marquee selection.
	 */

	public void setMarqueeHandler(BasicMarqueeHandler newMarquee) {
		BasicMarqueeHandler oldMarquee = marquee;

		marquee = newMarquee;
		firePropertyChange(MARQUEE_HANDLER_PROPERTY, oldMarquee, newMarquee);
		invalidate();
	}

		/**
		 * Sets the isNew.
		 * @param isNew The isNew to set
		 */

		public void setNew(boolean isNew) {
			this.isNew = isNew;
		}

	/**
	 * Returns the number of all objects (cells and children) in this object.
	 */

	public int getDescendantCount() {
		return cellCount;
	}

	/**
	 * Returns <code>true</code> if <code>node</code> or one of
	 * its ancestors is contained in this object.
	 */

	public boolean contains(Object node) {
		return cellSet.contains(node);
	}

	/**
	 * Returns the <code>CellView</code> that is mapped to <code>cell</code>
	 * in the graph context. New views are created based on whether cell
	 * is containes in the context. The <code>create</code>-flag is ignored.
	 */

	public CellView getMapping(Object cell, boolean create) {
		if (cell != null) {
			CellView view = (CellView) views.get(cell);
			if (view != null)
				return view;
			else if (contains(cell))
				return createMapping(cell);
			else
				return graphLayoutCache.getMapping(cell, false);
		}
		return null;
	}

	/**
	 * Determines what happens when editing is interrupted by selecting
	 * another cell in the graph, a change in the graph's data, or by some
	 * other means. Setting this property to <code>true</code> causes the
	 * changes to be automatically saved when editing is interrupted.
	 * <p>
	 * Fires a property change for the INVOKES_STOP_CELL_EDITING_PROPERTY.
	 * @param newValue true means that <code>stopCellEditing</code> is invoked
	 * when editing is interruped, and data is saved; false means that
	 * <code>cancelCellEditing</code> is invoked, and changes are lost
	 */

	public void setInvokesStopCellEditing(boolean newValue) {
		boolean oldValue = invokesStopCellEditing;

		invokesStopCellEditing = newValue;
		firePropertyChange(
			INVOKES_STOP_CELL_EDITING_PROPERTY,
			oldValue,
			newValue);
	}

	/**
	 * Associates <code>cell</code> with <code>view</code>
	 * in the graph context.
	 */

	public void putMapping(Object cell, CellView view) {
		views.put(cell, view);
	}

	/**
	 * Sets the factory that creates the cell views.
	 */

	public void setFactory(CellViewFactory factory) {
		this.factory = factory;
	}

	/**
	 * Returns the indicator that tells what happens when editing is
	 * interrupted.
	 * @return the indicator that tells what happens when editing is
	 * interrupted
	 * @see #setInvokesStopCellEditing
	 *
	 */

	public boolean getInvokesStopCellEditing() {
		return invokesStopCellEditing;
	}

	/**
	 * Returns the factory that was passed to the constructor.
	 */

	public CellViewFactory getFactory() {
		return factory;
	}

	/**
	 * Sets the current model.
	 */

	public void setModel(GraphModel model) {
		roots.clear();
		mapping.clear();
		hiddenSet.clear();
		visibleSet.clear();
		graphModel = model;
		Object[] cells = DefaultGraphModel.getRoots(model);
		if (!isPartial())
			insertRoots(getMapping(cells, true));
		// AutoSize for Existing Cells
		if (cells != null)
			for (int i = 0; i < cells.length; i++)
				factory.updateAutoSize(getMapping(cells[i], false));
		// Update PortView Cache
		updatePorts();
	}

	/**
	 * Returns <code>isEditable</code>. This is invoked from the UI before
	 * editing begins to ensure that the given cell can be edited. This
	 * is provided as an entry point for subclassers to add filtered
	 * editing without having to resort to creating a new editor.
	 * @return true if the specified cell is editable
	 * @see #isEditable
	 *
	 */

	public boolean isCellEditable(Object cell) {
		if (cell != null) {
			CellView view = graphLayoutCache.getMapping(cell, false);
			if (view != null) {
				return isEditable()
				&& GraphConstants.isEditable(view.getAllAttributes());
			}
		}
		return false;
	}

	/**
	 * Updates the cached array of ports.
	 */

	protected void updatePorts() {
		Object[] roots = DefaultGraphModel.getRoots(graphModel);
		Set set = DefaultGraphModel.getDescendants(graphModel, roots);
		if (set != null) {
			Object[] all = set.toArray(); //order(set.toArray());
			ArrayList result = new ArrayList();
			for (int i = 0; i < all.length; i++) {
				if (graphModel.isPort(all[i])) {
					CellView portView = getMapping(all[i], false);
					if (portView != null) {
						result.add(portView);
						portView.refresh(false);
					}
				}
			}
			ports = new PortView[result.size()];
			result.toArray(ports);
		}
	}

	/**
	 * Overrides <code>JComponent</code>'s <code>getToolTipText</code>
	 * method in order to allow the graph to create a tooltip
	 * for the topmost cell under the mousepointer. This differs from JTree
	 * where the renderers tooltip is used.
	 * <p>
	 * NOTE: For <code>JGraph</code> to properly display tooltips of its
	 * renderers, <code>JGraph</code> must be a registered component with the
	 * <code>ToolTipManager</code>.  This can be done by invoking
	 * <code>ToolTipManager.sharedInstance().registerComponent(graph)</code>.
	 * This is not done automatically!
	 * @param event the <code>MouseEvent</code> that initiated the
	 * <code>ToolTip</code> display
	 * @return a string containing the  tooltip or <code>null</code>
	 * if <code>event</code> is null
	 */

	public String getToolTipText(MouseEvent event) {
		if (event != null) {
			Object cell = getFirstCellForLocation(event.getX(), event.getY());
			String s = convertValueToString(cell);
			return (s != null && s.length() > 0) ? s : null;
		}
		return null;
	}

	/**
	 * Sets the graph's selection model. When a <code>null</code> value is
	 * specified an emtpy
	 * <code>selectionModel</code> is used, which does not allow selections.
	 * @param selectionModel the <code>GraphSelectionModel</code> to use,
	 * or <code>null</code> to disable selections
	 * @see GraphSelectionModel
	 *
	 */

	public void setSelectionModel(GraphSelectionModel selectionModel) {
		if (selectionModel == null)
			selectionModel = EmptySelectionModel.sharedInstance();

		GraphSelectionModel oldValue = this.selectionModel;

		// Remove Redirector From Old Selection Model
		if (this.selectionModel != null && selectionRedirector != null)
			this.selectionModel.removeGraphSelectionListener(
				selectionRedirector);

		this.selectionModel = selectionModel;

		// Add Redirector To New Selection Model
		if (selectionRedirector != null)
			this.selectionModel.addGraphSelectionListener(selectionRedirector);

		firePropertyChange(
			SELECTION_MODEL_PROPERTY,
			oldValue,
			this.selectionModel);
	}

	/**
	 * Returns the model for selections. This should always return a
	 * non-<code>null</code> value. If you don't want to allow anything
	 * to be selected
	 * set the selection model to <code>null</code>, which forces an empty
	 * selection model to be used.
	 * @return the current selection model
	 * @see #setSelectionModel
	 *
	 */

	public GraphSelectionModel getSelectionModel() {
		return selectionModel;
	}

	/**
	 * Clears the selection.
	 */

	public void clearSelection() {
		getSelectionModel().clearSelection();
	}

	/**
	 * Adds the specified model root cells to the view.
	 */

	public void insertRoots(CellView[] views) {
		if (views != null) {
			refresh(views, true);
			for (int i = 0; i < views.length; i++) {
				if (views[i] != null
					&& getMapping(views[i].getCell(), false) != null) {
					CellView parentView = views[i].getParentView();
					Object parent =
						(parentView != null) ? parentView.getCell() : null;
					if (!(views[i] instanceof PortView)
						&& !roots.contains(views[i])
						&& parent == null) {
						roots.add(views[i]);
						// Remove children for non-partial views?
					}
				}
			}
		}
	}

	/**
	 * Returns true if the selection is currently empty.
	 * @return true if the selection is currently empty
	 */

	public boolean isSelectionEmpty() {
		return getSelectionModel().isSelectionEmpty();
	}

	/**
	 * Returns the view for the specified cell. If create is true
	 * and no view is found then a view is created using
	 * createView(Object).
	 */

	public CellView getMapping(Object cell, boolean create) {
		if (cell == null)
			return null;
		CellView view = (CellView) mapping.get(cell);
		if (view == null && create) {
			view = (CellView) hiddenSet.get(cell);
			if (view != null && isVisible(cell)) {
				putMapping(cell, view);
				hiddenSet.remove(cell);
			} else
				view = factory.createView(cell, this);
		}
		return view;
	}

	/**
	 * Associates the specified model cell with the specified view.
	 * Updates the portlist if necessary.
	 */

	public void putMapping(Object cell, CellView view) {
		// Remove isVisible-condition?
		if (cell != null && view != null && isVisible(cell))
			mapping.put(cell, view);
	}

	/**
	 * Adds a listener for <code>GraphSelection</code> events.
	 * @param tsl the <code>GraphSelectionListener</code> that will be notified
	 * when a cell is selected or deselected (a "negative
	 * selection")
	 */

	public void addGraphSelectionListener(GraphSelectionListener tsl) {
		listenerList.add(GraphSelectionListener.class, tsl);
		if (listenerList.getListenerCount(GraphSelectionListener.class) != 0
			&& selectionRedirector == null) {
			selectionRedirector = new GraphSelectionRedirector();
			selectionModel.addGraphSelectionListener(selectionRedirector);
		}
	}

	/**
	 * Removes the associaten for the specified model cell and
	 * returns the view that was previously associated with the cell.
	 * Updates the portlist if necessary.
	 */

	public CellView removeMapping(Object cell) {
		if (cell != null) {
			CellView view = (CellView) mapping.remove(cell);
			return view;
		}
		return null;
	}

	/**
	 * Removes a <code>GraphSelection</code> listener.
	 * @param tsl the <code>GraphSelectionListener</code> to remove
	 */

	public void removeGraphSelectionListener(GraphSelectionListener tsl) {
		listenerList.remove(GraphSelectionListener.class, tsl);
		if (listenerList.getListenerCount(GraphSelectionListener.class) == 0
			&& selectionRedirector != null) {
			selectionModel.removeGraphSelectionListener(selectionRedirector);
			selectionRedirector = null;
		}
	}

	/**
	 * Notifies all listeners that have registered interest for
	 * notification on this event type.  The event instance
	 * is lazily created using the parameters passed into
	 * the fire method.
	 * @param e the <code>GraphSelectionEvent</code> generated by the
	 * <code>GraphSelectionModel</code>
	 * when a cell is selected or deselected
	 * @see javax.swing.event.EventListenerList
	 *
	 */

	protected void fireValueChanged(GraphSelectionEvent e) {
		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();
		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == GraphSelectionListener.class) {
				((GraphSelectionListener) listeners[i + 1]).valueChanged(e);
			}
		}
	}

	/**
	 * Selects the specified cell.
	 * @param cell the <code>Object</code> specifying the cell to select
	 */

	public void setSelectionCell(Object cell) {
		getSelectionModel().setSelectionCell(cell);
	}

	/**
	 * Selects the specified cells.
	 * @param cells an array of objects that specifies
	 * the cells to select
	 */

	public void setSelectionCells(Object[] cells) {
		getSelectionModel().setSelectionCells(cells);
	}

	/**
	 * Adds the cell identified by the specified <code>Object</code>
	 * to the current selection.
	 * @param cell the cell to be added to the selection
	 */

	public void addSelectionCell(Object cell) {
		getSelectionModel().addSelectionCell(cell);
	}

	/**
	 * Adds each cell in the array of cells to the current selection.
	 * @param cells an array of objects that specifies the cells to add
	 */

	public void addSelectionCells(Object[] cells) {
		getSelectionModel().addSelectionCells(cells);
	}

	/**
	 * Removes the cell identified by the specified Object from the current
	 * selection.
	 * @param cell the cell to be removed from the selection
	 */

	public void removeSelectionCell(Object cell) {
		getSelectionModel().removeSelectionCell(cell);
	}

	/**
	 * Removes <code>cells</code> from the model. If <code>removeChildren</code>
	 * is <code>true</code>, the children are also removed.
	 * Notifies the model- and undo listeners of the change.
	 */

	public void remove(Object[] roots) {
		graphModel.remove(roots);
	}

	/**
	 * Returns the first selected cell.
	 * @return the <code>Object</code> for the first selected cell,
	 * or <code>null</code> if nothing is currently selected
	 */

	public Object getSelectionCell() {
		return getSelectionModel().getSelectionCell();
	}

	/**
	 * Sends <code>cells</code> to back. Note: This expects an array of cells!
	 */

	public void toBack(Object[] cells) {
		if (cells != null && cells.length > 0) {
			if (isOrdered()) {
				CellView[] views = getMapping(cells, false);
				GraphViewLayerEdit edit =
					new GraphViewLayerEdit(
						this,
						views,
						GraphViewLayerEdit.BACK);
				graphModel.edit(null, null, null, new UndoableEdit[] { edit });
			} else
				graphModel.toBack(cells);
		}
	}

	/**
	 * Returns the number of cells selected.
	 * @return the number of cells selected
	 */

	public int getSelectionCount() {
		return getSelectionModel().getSelectionCount();
	}

	/**
	 * Brings <code>cells</code> to front. Note: This expects an array of cells!
	 */

	public void toFront(Object[] cells) {
		if (cells != null && cells.length > 0) {
			if (isOrdered()) {
				CellView[] views = getMapping(cells, false);
				GraphViewLayerEdit edit =
					new GraphViewLayerEdit(
						this,
						views,
						GraphViewLayerEdit.FRONT);
				graphModel.edit(null, null, null, new UndoableEdit[] { edit });
			} else
				graphModel.toFront(cells);
		}
	}

	/**
	 * Returns true if the cell is currently selected.
	 * @param cell an object identifying a cell
	 * @return true if the cell is selected
	 */

	public boolean isCellSelected(Object cell) {
		return getSelectionModel().isCellSelected(cell);
	}

	/**
	 * Scrolls to the specified cell. Only works when this
	 * <code>JGraph</code> is contained in a <code>JScrollPane</code>.
	 * @param cell the object identifying the cell to bring into view
	 */

	public void scrollCellToVisible(Object cell) {
		Rectangle bounds = getCellBounds(cell);
		if (bounds != null) {
			bounds = new Rectangle(bounds);
			scrollRectToVisible(toScreen(bounds));
		}
	}

		/**
		 * Returns the source of this change. This can either be a
		 * view or a model, if this change is a GraphModelChange.
		 */

		public Object getSource() {
			return GraphLayoutCache.this;
		}

	/**
	 * Makes sure the specified point is visible.
	 * @param p the point that should be visible
	 */

	public void scrollPointToVisible(Point p) {
		if (p != null) {
			Rectangle bounds = new Rectangle(p);
			if (bounds != null)
				scrollRectToVisible(bounds);
		}
	}

	/**
	 * Returns true if the graph is being edited. The item that is being
	 * edited can be obtained using <code>getEditingCell</code>.
	 * @return true if the user is currently editing a cell
	 * @see #getSelectionCell
	 *
	 */

	public boolean isEditing() {
		GraphUI graph = getUI();

		if (graph != null)
			return graph.isEditing(this);
		return false;
	}

	/**
	 * Ends the current editing session.
	 * (The <code>DefaultGraphCellEditor</code>
	 * object saves any edits that are currently in progress on a cell.
	 * Other implementations may operate differently.)
	 * Has no effect if the tree isn't being edited.
	 * <blockquote>
	 * <b>Note:</b><br>
	 * To make edit-saves automatic whenever the user changes
	 * their position in the graph, use {@link #setInvokesStopCellEditing}.
	 * </blockquote>
	 * @return true if editing was in progress and is now stopped,
	 * false if editing was not in progress
	 */

	public boolean stopEditing() {
		GraphUI graph = getUI();

		if (graph != null)
			return graph.stopEditing(this);
		return false;
	}

	/**
	 * Cancels the current editing session. Has no effect if the
	 * graph isn't being edited.
	 */

	public void cancelEditing() {
		GraphUI graph = getUI();

		if (graph != null)
			graph.cancelEditing(this);
	}

		/**
		 * Returns the source of this change. This can either be a
		 * view or a model, if this change is a GraphModelChange.
		 */

		public Object getSource() {
			return changeSource;
		}

	/**
	 * Selects the specified cell and initiates editing.
	 * The edit-attempt fails if the <code>CellEditor</code>
	 * does not allow
	 * editing for the specified item.
	 */

	public void startEditingAtCell(Object cell) {
		GraphUI graph = getUI();

		if (graph != null)
			graph.startEditingAtCell(this, cell);
	}

		/**
		 * Returns a map of (cell view, attribute) pairs.
		 */

		public Map getAttributes() {
			return null;
		}

		/**
		 * called to save the state of a component in case it needs to
		 * be restored because a drop is not performed.
		 */

		protected void saveComponentState(JComponent comp) {
		}

	/**
	 * Returns the cell that is currently being edited.
	 * @return the cell being edited
	 */

	public Object getEditingCell() {
		GraphUI graph = getUI();

		if (graph != null)
			return graph.getEditingCell(this);
		return null;
	}

		/**
		 * Redoes a change.
		 *
		 * @exception CannotRedoException if the change cannot be redone
		 */

		public void redo() throws CannotRedoException {
			super.redo();
			updateNext();
			execute();
		}

		/**
		 * called to restore the state of a component
		 * because a drop was not performed.
		 */

		protected void restoreComponentState(JComponent comp) {
			if (handle != null)
				handle.mouseDragged(null);
		}

	/**
	 * Messaged when the graph has changed enough that we need to resize
	 * the bounds, but not enough that we need to remove the cells
	 * (e.g cells were inserted into the graph). You should never have to
	 * invoke this, the UI will invoke this as it needs to. (Note: This
	 * is invoked by GraphUI, eg. after moving.)
	 */

	public void graphDidChange() {
		revalidate();
		repaint();
	}

		/**
		 * Execute this edit such that the next invocation to this
		 * method will invert the last execution.
		 */

		public void execute() {
			for (int i = 0; i < cells.length; i++) {
				List list = getParentList(cells[i]);
				if (list != null) {
					prev[i] = list.indexOf(cells[i]);
					if (prev[i] >= 0) {
						list.remove(prev[i]);
						int n = next[i];
						if (n == FRONT)
							n = list.size();
						else if (n == BACK)
							n = 0;
						list.add(n, cells[i]);
						next[i] = prev[i];
					}
				}
			}
			updateListeners();
		}

		/**
		 * called to set the insertion location to match the current
		 * mouse pointer coordinates.
		 */

		protected void updateInsertionLocation(JComponent comp, Point p) {
			setInsertionLocation(p);
			if (handle != null) {
				// How to fetch the shift state?
				int mod =
					(dropAction == TransferHandler.COPY)
						? InputEvent.CTRL_MASK
						: 0;
				handle.mouseDragged(
					new MouseEvent(comp, 0, 0, mod, p.x, p.y, 1, false));
			}
		}

		/**
		 * Returns the list that exclusively contains <code>view</code>.
		 */

		protected List getParentList(Object view) {
			if (view instanceof CellView) {
				CellView parent = ((CellView) view).getParentView();
				List list = null;
				if (parent == null)
					list = ((GraphLayoutCache) changeSource).roots;
				else if (parent instanceof AbstractCellView)
					list = ((AbstractCellView) parent).childViews;
				return list;
			}
			return null;
		}

	/**
	 * Returns the hiddenSet.
	 * @return Map
	 */

	public Map getHiddenSet() {
		return hiddenSet;
	}

	/**
	 * Returns the hideEdgesOnBecomeInvisible.
	 * @return boolean
	 */

	public boolean isHideEdgesOnBecomeInvisible() {
		return hideEdgesOnBecomeInvisible;
	}

		/** Returns a shared instance of an empty selection model. */

		static public EmptySelectionModel sharedInstance() {
			return sharedInstance;
		}

	/**
	 * Returns the hideEdgesOnHide.
	 * @return boolean
	 */

	public boolean isHideEdgesOnHide() {
		return hideEdgesOnHide;
	}

		/** A <code>null</code> implementation that selects nothing. */

		public void setSelectionCells(Object[] cells) {
		}

	/**
	 * Returns the rememberCellViews.
	 * @return boolean
	 */

	public boolean isRememberCellViews() {
		return rememberCellViews;
	}

	/**
	 * Returns the showAllEdgesForVisibleVertices.
	 * @return boolean
	 */

	public boolean isShowAllEdgesForVisibleVertices() {
		return showAllEdgesForVisibleVertices;
	}

		/** A <code>null</code> implementation that adds nothing. */

		public void addSelectionCells(Object[] cells) {
		}

	/**
	 * Returns the showEdgesOnShow.
	 * @return boolean
	 */

	public boolean isShowEdgesOnShow() {
		return showEdgesOnShow;
	}

		/** A <code>null</code> implementation that removes nothing. */

		public void removeSelectionCells(Object[] cells) {
		}

	/**
	 * Sets the hideEdgesOnBecomeInvisible.
	 * @param hideEdgesOnBecomeInvisible The hideEdgesOnBecomeInvisible to set
	 */

	public void setHideEdgesOnBecomeInvisible(boolean hideEdgesOnBecomeInvisible) {
		this.hideEdgesOnBecomeInvisible = hideEdgesOnBecomeInvisible;
	}

		/**
		 * Invoked by the <code>GraphSelectionModel</code> when the
		 * selection changes.
		 *
		 * @param e the <code>GraphSelectionEvent</code> generated by the
		 *		<code>GraphSelectionModel</code>
		 */

		public void valueChanged(GraphSelectionEvent e) {
			GraphSelectionEvent newE;

			newE = (GraphSelectionEvent) e.cloneWithSource(JGraph.this);
			fireValueChanged(newE);
		}

	/**
	 * Sets the hideEdgesOnHide.
	 * @param hideEdgesOnHide The hideEdgesOnHide to set
	 */

	public void setHideEdgesOnHide(boolean hideEdgesOnHide) {
		this.hideEdgesOnHide = hideEdgesOnHide;
	}

	/**
	* Returns the preferred display size of a <code>JGraph</code>. The height is
	* determined from <code>getPreferredWidth</code>.
	* @return the graph's preferred size
	*/

	public Dimension getPreferredScrollableViewportSize() {
		return getPreferredSize();
	}

	/**
	 * Sets the rememberCellViews.
	 * @param rememberCellViews The rememberCellViews to set
	 */

	public void setRememberCellViews(boolean rememberCellViews) {
		this.rememberCellViews = rememberCellViews;
	}

	/**
	 * Sets the showAllEdgesForVisibleVertices.
	 * @param showAllEdgesForVisibleVertices The showAllEdgesForVisibleVertices to set
	 */

	public void setShowAllEdgesForVisibleVertices(boolean showAllEdgesForVisibleVertices) {
		this.showAllEdgesForVisibleVertices = showAllEdgesForVisibleVertices;
	}

	/**
	 * Sets the showEdgesOnShow.
	 * @param showEdgesOnShow The showEdgesOnShow to set
	 */

	public void setShowEdgesOnShow(boolean showEdgesOnShow) {
		this.showEdgesOnShow = showEdgesOnShow;
	}

	/**
	 * Paint the renderer. Overrides superclass paint
	 * to add specific painting.
	 */

	public void paint(Graphics g) {
		try {
			//if (preview && !isDoubleBuffered)
			//	setOpaque(false);
			super.paint(g);
			paintSelectionBorder(g);
		} catch (IllegalArgumentException e) {
			// JDK Bug: Zero length string passed to TextLayout constructor
		}
	}

	/**
	 * Provided for subclassers to paint a selection border.
	 */

	protected void paintSelectionBorder(Graphics g) {
		((Graphics2D) g).setStroke(GraphConstants.SELECTION_STROKE);
		if (childrenSelected)
			g.setColor(graph.getGridColor());
		else if (hasFocus && selected)
			g.setColor(graph.getLockedHandleColor());
		else if (selected)
			g.setColor(graph.getHighlightColor());
		if (childrenSelected || selected) {
			Dimension d = getSize();
			g.drawRect(0, 0, d.width - 1, d.height - 1);
		}
	}

	/**
	* Returns false to indicate that the width of the viewport does not
	* determine the width of the graph, unless the preferred width of
	* the graph is smaller than the viewports width.  In other words:
	* ensure that the graph is never smaller than its viewport.
	* @return false
	* @see Scrollable#getScrollableTracksViewportWidth
	*
	*/

	public boolean getScrollableTracksViewportWidth() {
		if (getParent() instanceof JViewport) {
			return (
				((JViewport) getParent()).getWidth()
					> getPreferredSize().width);
		}
		return false;
	}

	/**
	* Returns false to indicate that the height of the viewport does not
	* determine the height of the graph, unless the preferred height
	* of the graph is smaller than the viewports height.  In other words:
	* ensure that the graph is never smaller than its viewport.
	* @return false
	* @see Scrollable#getScrollableTracksViewportHeight
	*
	*/

	public boolean getScrollableTracksViewportHeight() {
		if (getParent() instanceof JViewport) {
			return (
				((JViewport) getParent()).getHeight()
					> getPreferredSize().height);
		}
		return false;
	}

	/**
	 * Returns the hideGroups.
	 * @return boolean
	 */

	public boolean isHideGroups() {
		return hideGroups;
	}

	/**
	 * Sets the hideGroups.
	 * @param hideGroups The hideGroups to set
	 */

	public void setHideGroups(boolean hideGroups) {
		this.hideGroups = hideGroups;
	}

	/**
	 * Returns the connections between <code>cells</code> (and possibly
	 * other, unselected cells).
	 */

	public ConnectionSet getConnectionSet() {
		return cs;
	}

	/**
	 * Returns a map of (GraphCell, Map)-pairs that represent the
	 * view attributes for the respecive cells.
	 */

	public Map getAttributeMap() {
		return attributeMap;
	}

	/**
	* Returns a string representation of this <code>JGraph</code>.
	* This method
	* is intended to be used only for debugging purposes, and the
	* content and format of the returned string may vary between
	* implementations. The returned string may be empty but may not
	* be <code>null</code>.
	* @return a string representation of this <code>JGraph</code>.
	*/

	protected String paramString() {
		String editableString = (editable ? "true" : "false");
		String invokesStopCellEditingString =
			(invokesStopCellEditing ? "true" : "false");
		return super.paramString()
			+ ",editable="
			+ editableString
			+ ",invokesStopCellEditing="
			+ invokesStopCellEditingString;
	}

	/**
	 * Returns true if the transferable support a text/plain format.
	 */

	public boolean isPlainSupported() {
		return (cells != null && cells.length == 1);
	}

	/**
	 * Fetch the data in a text/plain format.
	 */

	public String getPlainData() {
		if (cells[0] instanceof DefaultGraphCell) {
			Object obj = ((DefaultGraphCell) cells[0]).getUserObject();
			if (obj != null)
				return obj.toString();
		}
		return cells[0].toString();
	}

	/**
	 * If this UndoManager is inProgress, undo the last significant
	 * UndoableEdit wrt. to source, and all insignificant edits back to
	 * it. Updates indexOfNextAdd accordingly.
	 *
	 * <p>If not inProgress, indexOfNextAdd is ignored and super's routine is
	 * called.</p>
	 *
	 * @see UndoManager#undo
	 */

	public void undo(Object source) {
		if (source == null || !isInProgress())
			super.undo();
		else {
			UndoableEdit edit = editToBeUndone(source);
			//System.out.println("undoTo edit="+edit);
			if (edit == null)
				throw new CannotUndoException();
			undoTo(edit);
		}
	}

	/**
	 * Returns true if the transferable support a text/html format.
	 */

	public boolean isHTMLSupported() {
		return isPlainSupported();
	}

	/**
	 * Fetch the data in a text/html format.
	 */

	public String getHTMLData() {
		StringBuffer buf = new StringBuffer();
		buf.append("<html><body><p>");
		buf.append(getPlainData());
		buf.append("</p></body></html>");
		return buf.toString();
	}

	/**
	 * Returns the the next significant edit wrt. to current
	 * to be undone if undo is called. May return null.
	 */

	protected UndoableEdit nextEditToBeUndone(UndoableEdit current) {
		if (current == null)
			return editToBeUndone();
		else {
			int index = edits.indexOf(current) - 1;
			if (index >= 0)
				return (UndoableEdit) edits.get(index);
		}
		return null;
	}

	/**
	 * If this <code>UndoManager</code> is <code>inProgress</code>,
	 * redoes the last significant <code>UndoableEdit</code> with
	 * respect to source or after, and all insignificant
	 * edits up to it. Updates <code>indexOfNextAdd</code> accordingly.
	 *
	 * <p>If not <code>inProgress</code>, <code>indexOfNextAdd</code>
	 * is ignored and super's routine is called.</p>
	 */

	public void redo(Object source) {
		if (source == null || !isInProgress())
			super.redo();
		else {
			UndoableEdit edit = editToBeRedone(source);
			//System.out.println("redoTo edit="+edit);
			if (edit == null)
				throw new CannotRedoException();
			redoTo(edit);
		}
	}

	/**
	 * Returns the source of the edge.
	 */

	public Object getSource() {
		return source;
	}

	/**
	 * Returns the target of the edge.
	 */

	public Object getTarget() {
		return target;
	}

	/**
	 * Returns the the next significant edit wrt. to current
	 * to be redone if redo is called. May return null.
	 */

	protected UndoableEdit nextEditToBeRedone(UndoableEdit current) {
		if (current == null)
			return editToBeRedone();
		else {
			int index = edits.indexOf(current) + 1;
			if (index < edits.size())
				return (UndoableEdit) edits.get(index);
		}
		return null;
	}

	/**
	 * Sets the source of the edge.
	 */

	public void setSource(Object port) {
		source = port;
	}

	/**
	 * Add a new entry for this child, parent pair to the parent map.
	 * The child and parent are added to the set of changed nodes.
	 * Note: The previous parent is changed on execution of this parent
	 * map and must be added by the GraphModel and reflected by the
	 * GraphChange.getChanged method.
	 * TODO: In general, the GraphModel should be in charge of computing
	 * the set of changed cells.
	 */

	public void addEntry(Object child, Object parent) {
		if (child != null) {
			entries.add(new Entry(child, parent));
			// Update Changed Nodes
			changedNodes.add(child);
			if (parent != null)
				changedNodes.add(parent);
		}
	}

	/**
	 * Returns the target of <code>edge</code>.
	 */

	public void setTarget(Object port) {
		target = port;
	}

	/**
	 * Returns the number of entries.
	 */

	public int size() {
		return entries.size();
	}

	/**
	 * Create a clone of the cell. The cloning of the
	 * user object is deferred to the cloneUserObject()
	 * method.
	 *
	 * @return Object  a clone of this object.
	 */

	public Object clone() {
		DefaultEdge c = (DefaultEdge) super.clone();
		c.source = null;
		c.target = null;
		return c;
	}

	/**
	 * Returns an <code>Iterator</code> for the entries in the map.
	 */

	public Iterator entries() {
		return entries.iterator();
	}

	/**
	 * Returns a <code>Set</code> for the nodes, childs and parents,
	 * in this parent map.
	 */

	public Set getChangedNodes() {
		return changedNodes;
	}

	/**
	 * This method ensures a non-null value. If the super method
	 * returns null then the last valid parent is returned.
	 * Note: If a vertex is removed, all ports will be replaced
	 * in connected edges. The ports are replaced by the center
	 * point of the <i>last</i> valid vertex view.
	 */

	public CellView getParentView() {
		CellView parent = super.getParentView();
		if (parent == null)
			parent = lastParent;
		else
			lastParent = parent;
		return parent;
	}

	/**
	 * Creates a new parent map based on this parent map,
	 * where the child and parents are mapped using <code>map</code>.
	 * If one the cells is not in <code>map</code>, then the original
	 * cell is used instead.<p>
	 */

	public ParentMap clone(Map map) {
		ParentMap pm = new ParentMap();
		Iterator it = entries();
		while (it.hasNext()) {
			Entry e = (Entry) it.next();
			Object child = map.get(e.getChild());
			Object parent = map.get(e.getParent());
			if (child == null)
				child = e.getChild();
			if (parent == null)
				parent = e.getParent();
			if (child != null && parent != null)
				pm.addEntry(child, parent);
		}
		return pm;
	}

	/**
	 * Returns the bounds for the port view.
	 */

	public Rectangle getBounds() {
		Rectangle bounds = new Rectangle(getLocation(null));
		bounds.x = bounds.x - size / 2;
		bounds.y = bounds.y - size / 2;
		bounds.width = bounds.width + size;
		bounds.height = bounds.height + size;
		return bounds;
	}

		/**
		 * Returns the child of the relation.
		 */

		public Object getChild() {
			return child;
		}

		/**
		 * Returns the parent of the relation.
		 */

		public Object getParent() {
			return parent;
		}

	/**
	 * Returns the point that is closest to the port view on
	 * <code>edge</code>. Returns <code>null</code> if
	 * <code>edge</code> has less than 2 points.
	 */

	protected Point getNextPoint(EdgeView edge) {
		int n = edge.getPointCount();
		if (n > 1) {
			if (edge.getSource() == this)
				return getEdgePoint(edge, 1);
			else if (edge.getTarget() == this)
				return getEdgePoint(edge, n - 2);
		}
		return null;
	}

	/**
	 * Override parent method to synchronize value property and userObject.
	 * The following holds for each GraphCell c:<p>
	 * GraphConstants.getValue(c.getAttributes()) == c.getUserObject()<p>
	 * <strong>Note:</strong> A cell's userObject can be set using
	 * GraphModel.edit() with a propertyMap that carries a value entry
	 * for that cell.
	 */

	public void setUserObject(Object obj) {
		if (userObject instanceof ValueChangeHandler) {
			((ValueChangeHandler) userObject).valueChanged(obj);
		} else
			super.setUserObject(obj);
		obj = getUserObject();
		if (obj == null)
			GraphConstants.setValue(attributes, "");
		else
			GraphConstants.setValue(attributes, obj);
	}

	/**
	 * Returns the point of <code>edge</code> at index
	 * <code>index</code>. Avoids calling <code>getLocation</code>
	 * on the opposite port of <code>edge</code> (possible
	 * infinite recursion).
	 */

	protected Point getEdgePoint(EdgeView view, int index) {
		Object obj = view.points.get(index);
		if (obj instanceof Point)
			return (Point) obj;
		else if (obj instanceof PortView) {
			VertexView vertex = (VertexView) ((CellView) obj).getParentView();
			if (vertex != null)
				return vertex.getCenterPoint();
		}
		return null;
	}

	/**
	 * Provides access to the children list to change ordering.
	 * This method returns a <code>Collections.EMPTY_LIST</code>
	 * if the list of childrenpoints to <code>null</code>.
	 */

	public List getChildren() {
		if (children == null)
			return Collections.EMPTY_LIST;
		return children;
	}

	/**
	 * Sets the attributes.
	 * @param attributes The attributes to set
	 */

	public void setAttributes(Map attributes) {
		this.attributes = attributes;
	}

	/**
	 * Create a clone of the cell. The cloning of the
	 * user object is deferred to the cloneUserObject()
	 * method.
	 *
	 * @return Object  a clone of this object.
	 */

	public Object clone() {
		DefaultGraphCell c = (DefaultGraphCell) super.clone();
		c.attributes = new Hashtable(attributes);
		c.userObject = cloneUserObject();
		return c;
	}

	/**
	 * Override tool tip method to display URL
	 *
	 * @param event  event passed
	 * @return       tooltip as URL
	 */

	public String getToolTipText(MouseEvent event) {
		if (linkHandler.isHoveringOverHyperlink() && (linkHandler.getHoveredURL() != null)) {
			// have to manually toggle tooltip enabled status to prevent empty
			// tooltip from appearing when not hovering over url
			ToolTipManager.sharedInstance().setEnabled(true);
			return linkHandler.getHoveredURL();
		}
		else {
			ToolTipManager.sharedInstance().setEnabled(false);
			return null;
		}
	}

	/**
	 * Sets the {3} attribute of the UserProperties object
	 *
	 * @param key The new {3} value
	 * @param buttonRectangle The new {3} value
	 */

	public void setRect(String key, Rectangle r) {
		String val = r.getX() + "," + r.getY() + "," + r.getWidth() + "," + r.getHeight();
		this.setProperty(key, val);
	}

	/**
	 * Override Swing's poor label position choice. The new behaviour
	 * shows the label relative to the current location of the mouse.
	 *
	 * @param event  tool tip location event
	 * @return       tool tip location
	 */

	public Point getToolTipLocation(MouseEvent event) {
		return new Point(event.getX() + 10, event.getY() + 25);
	}

	/**
	 * Gets the {3} attribute of the UserProperties object
	 *
	 * @param key Description of Parameter
	 * @return The {3} value
	 */

	public Rectangle getRect(String key) {
		int x = 0;
		int y = 0;
		int width = 0;
		int height = 0;
		String rect = this.getProperty(key);
		try {
			StringTokenizer st = new StringTokenizer(rect, ",");
			     x = (int)Double.parseDouble(st.nextToken());
			     y = (int)Double.parseDouble(st.nextToken());
			 width = (int)Double.parseDouble(st.nextToken());
			height = (int)Double.parseDouble(st.nextToken());
		}
		catch (Exception e) {
			return null;
		}
		return new Rectangle(x, y, width, height);
	}

	/**
	 * Determines if current mouse location is hovering over a hyperlink.
	 * Remember, <code>CustomLinkHandler</code> is NOT notified of hyperlink
	 * events if editing is enabled by defintion in JEditorPane. In otherwords,
	 * when HTML code is being displayed, then hyperlink tracking is not occuring.
	 *
	 * @return   <code>true</code> if mouse if hovering over hyperlink and pane
	 * is not editable
	 */

	public boolean isHoveringOverHyperlink() {
		return linkHandler.isHoveringOverHyperlink();
	}

	/**
	 * Description of the Method
	 */

	public void save() {
		Utilities.writePropertiesToFile((Frame)null, this, file,  "---User Properties--- output from UserProperties.java");
	}

	/**
	 * Gets the URL being hovered over.
	 *
	 * @return   The URL value if mouse is currently hovering over a URL, or
	 * <code>null</code> if not currently hovering over a URL
	 */

	public String getHoveredURL() {
		return linkHandler.getHoveredURL();
	}

		/**
		 * Determines if current mouse location is hovering over a hyperlink.
		 * Remember, <code>CustomLinkHandler</code> is NOT notified of hyperlink
		 * events if editing is enabled by defintion in JEditorPane. In otherwords,
		 * when HTML code is being displayed, then hyperlink tracking is not occuring.
		 *
		 * @return   true if mouse if hovering over hyperlink and pane is not editable
		 */

		public boolean isHoveringOverHyperlink() {
			// check if pane is editable as caller could have changed editability after
			// hyperlinkUpdate was fired causing indeterminability in hovering status
			if (pane.isEditable()) {
				return false;
			}
			else {
				return isHovering;
			}
		}

	/** Removes the current instance from the  
	 *  position manager and from
	 *  the locale change adapter.
	 *  After that the method calls
	 *  the super implementation. 
	 *  
	 * @see java.lang.Object#finalize()
	 */

	protected void finalize() throws Throwable {
		LocaleChangeAdapter.removeContainer(this);
		PositionManager.removeComponent(this);
		super.finalize();
	}

		/**
		 * Gets the URL being hovered over.
		 *
		 * @return   The URL value if mouse is currently hovering over a URL, or
		 * <code>null</code> if not currently hovering over a URL
		 */

		public String getHoveredURL() {
			return hoveredURLString;
		}

	/** Calls the super implementation
	 *  and makes an update for the
	 *  component by using the locale
	 *  change adapter and the 
	 *  position manager.
	 *  
	 *  @param name the new name
	 *  @see PositionManager#updateComponent(Component)
	 *  @see LocaleChangeAdapter#updateComponent(Component)
	 *  @see java.awt.Component#setName(java.lang.String)
	 * 
	 */

	public void setName(String name) {
		super.setName(name);
		PositionManager.updateComponent(this);
		LocaleChangeAdapter.updateComponent(this);
	}

	/** makes an update for the locale
	 *  dependent values from the whole
	 *  container and calls
	 *  the super implementation 
	 *  
	 * @see java.awt.Component#validate()
	 * @see LocaleChangeAdapter#updateContainer(Container)
	 * @see java.awt.Container#validate()
	 */

	public void validate() {
		LocaleChangeAdapter.updateContainer(this);
		super.validate();
	}

	/** Registers the default 
	 *  window esc action for this
	 *  frame. 
	 * 
	 *  @see GPEscAction
	 *  
	 */

	public void registerDefaultEscAction() {
		registerEscAction(new GPEscAction());
	}

	/** Registers the specified
	 *  action for a esc action 
	 *  of this frame. 
	 * 
	 *  @param action the action 
	 *  
	 */

	public void registerEscAction(Action action) {
		this.getRootPane().registerKeyboardAction(
			action,
			escKeystroke,
			JComponent.WHEN_IN_FOCUSED_WINDOW);
	}

	/** Unregisters the esc action 
	 *  of this frame. 
	 *  
	 */

	public void unregisterEscAction() {
		this.getRootPane().unregisterKeyboardAction(escKeystroke);

	}

	/** Registers the specified button
	 *  for the default esc button.
	 * 
	 * @param button
	 */	

	public void setEscButton(JButton button){
		registerEscAction(new GPEscAction(button));
	}

	/** Registers the specified button
	 *  for the default button.
	 * 
	 * @param button
	 */	

	public void setDefaultButton(JButton button){
		getRootPane().setDefaultButton(button);
	}

	/**If the button is set, then
	 * the method will call the do click
	 * method at the button.
	 * 
	 * If the event source 
	 * is a JInternalFrame or a Window, then
	 * the action will call the dispose method. 
	 * 
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * @see javax.swing.JButton#doClick()
	 */

	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getSource().getClass());

		// is the esc button set?
		if (button != null){
			button.doClick();
			return;
		}
			 

		// is it an internal frame?
		try {
			JInternalFrame jif =
				(JInternalFrame) SwingUtilities.getAncestorOfClass(
					JInternalFrame.class,
					(Component) e.getSource());
			if (jif != null) {
				jif.dispose();
				return;
			}
		} catch (Exception ex) {
			System.err.println(ex);
		}

		// is it an window?
		try {
			Window w =
				(Window) SwingUtilities.getAncestorOfClass(
					Window.class,
					(Component) e.getSource());
			if (w != null) {
				w.dispose();
				return;
			}
		} catch (Exception ex) {
			System.err.println(ex);
		}

	}

	/** Removes the Split Pane from the 
	 *  position manager and calls
	 *  the super implementation. 
	 *  
	 * @see java.lang.Object#finalize()
	 */

	protected void finalize() throws Throwable {
		PositionManager.removeComponent(this);
		super.finalize();
	}

	/** Will be called from the translator 
	 *  if a locale was changed
	 *  
	 */

	public void localeChanged(LocaleChangeEvent e) {
		Vector copy;
		synchronized (containers) {
			copy = (Vector) containers.clone();
		}
		Enumeration oEnum = copy.elements();
		while (oEnum.hasMoreElements()) {
			updateContainer((Container) oEnum.nextElement());
		}
	}

	/** returns the keys for the proper names
	 * 
	 */

	public Enumeration getKeys() {
		return properNames.keys();
	}

	/** returns the value for a proper name key
	 * 
	 */

	public String getString(String key) {
		return (String) properNames.get(key);
	}

	/**
	 *  Sets the bounds attribute of the JFrameP object
	 *
	 *@param  buttonRectangle  The new bounds value
	 */

	public void setBounds(Rectangle r) {
		this.setBounds(r.x, r.y, r.width, r.height);
	}

	/** Stores the property value for some special
	 *  properties.
	 * 
	 *  The method considers the property
	 *  <ul>
	 *  <li>{@link JSplitPane#DIVIDER_LOCATION_PROPERTY}</li>
	 *  </ul> 
	 * 
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */

	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getSource() == null)
			return;

		if (evt.getPropertyName() == JSplitPane.DIVIDER_LOCATION_PROPERTY
			&& ((Integer) evt.getOldValue()).intValue() != -1) {

			if (evt.getSource() instanceof JSplitPane) {
				try {
					JSplitPane jsp = (JSplitPane) evt.getSource();
					PositionManager.setIntPos(
						jsp,
						PositionManager.DIVIDER_LOCATION,
						jsp.getDividerLocation());
				} catch (Exception ex) {
					System.err.println(ex);
				}
			}
		}
	}

	/**
	 *  Sets the location attribute of the JFrameP object
	 *
	 *@param  p  The new location value
	 */

	public void setLocation(Point p) {
		this.setLocation(p.x, p.y);
	}

	/**
	 * Sets the mode of the snapSelectedView drag operation.
	 * @param bSnapToGrid specifies if the snap-to-grid mode should be applied during a drag operation.
	 * If it is enabled,  the view, that is returned by the findViewForPoint(Point pt),
	 * will be snapped to the grid lines. <br>
	 * By default, findViewForPoint() returns the first view from the GraphContext whose bounds intersect with snap proximity of a mouse pointer.
	 * If snap-to-grid mode is disabled, views are moved by a snap increment.
	 */

	public void setSnapSelectedView(boolean snapSelectedView) {
		this.snapSelectedView = snapSelectedView;
	}

	/**
	 *  Sets the size attribute of the JFrameP object
	 *
	 *@param  d  The new size value
	 */

	public void setSize(Dimension d) {
		this.setSize(d.width, d.height);
	}

	/**
	 *  Description of the Method
	 */

	public void show() {
		init();

		if (hasBeenVisibleAtLeastOnce == false) {
			// set default location to be centered
			// this'buttonSelect implementation of setBounds will deal with checking for persisted value
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (screenSize.width - this.getWidth()) / 2;
			int y = (screenSize.height - this.getHeight()) / 2;
			this.setBounds(x, y, this.getWidth(), this.getHeight());
		}

		hasBeenVisibleAtLeastOnce = true;
		super.show();
	}

	/**
	 *  Description of the Method
	 */

	public void hide() {
		init();
		//Before hiding frame, set properties inside UserProperties then save UserProperties to file in hard disk
		guiProperties.setRect(property_bounds, this.getBounds());
		guiProperties.save();
		super.hide();
	}

	/**
	 *  Description of the Method
	 */

	protected void init() {

		// check if not already init'd
		if (guiProperties == null) {
			guiProperties = UserProperties.getInstance(nameSpace);
		}

      	}

	/**
	 * Returns the document.
	 * @return GPDocument
	 */

	public GPDocument getDocument() {
		return document;
	}

	/**
	 * Sets the document.
	 * @param document The document to set
	 */

	public void setDocument(GPDocument document) {
		this.remove(this.document);
		this.document = document;
		this.add(this.document);
		//this.pack();
	}

	/**
	 * Description of the Method
	 */

	public void hide() {
		init();
		guiProperties.setRect(property_bounds, this.getBounds());
		guiProperties.save();
		super.hide();
	}

	/**
	 * Returns the filename.
	 * @return String
	 */

	public URL getFilename() {
		return file;
	}

	/**
	 * Sets the filename.
	 * @param filename The filename to set
	 */

	public void setFilename(URL filename) {
		this.file = filename;
		updateFrameTitle();
	}

	/**
	 * Fetch the editor contained in this panel
	 */

	public GPGraph getGraph() {
		return graph;
	}

	/** Returns a file filter for the <tt>pad_xml</tt> extension.
	 *
	 * @see org.jgraph.pad.GraphModelFileFormat#getFileFilter()
	 */

	public FileFilter getFileFilter() {
		return fileFilter;
	}

	/** Returns the compZipSelect object.
	 *
	 * @see #compZipSelect
	 * @see org.jgraph.pad.GraphModelFileFormat#getWriteAccessory()
	 */

	public JComponent getWriteAccessory() {
	    return null; //compZipSelect;
	}

	/** returns the GPGraph UI
	 */

	protected GPGraphUI getGraphUI() {
		return (GPGraphUI) graph.getUI();
	}

	/** Returns the view from the current graph
	 *
	 */

	public GraphLayoutCache getGraphLayoutCache() {
		return graph.getGraphLayoutCache();
	}

	/**
	 * Puts the value from the checkbox into the properties hashtable
	 *
	 * @see org.jgraph.pad.GraphModelFileFormat#getWriteProperties(JComponent)
	 */

	public Hashtable getWriteProperties(JComponent accessory) {
		Hashtable properties = new Hashtable();
		if (!(accessory instanceof JCheckBox)){
			return properties;
		}
		properties.put(COMPRESS_WITH_ZIP, new Boolean(((JCheckBox)accessory).isSelected() ));
		return properties;
	}

	/** not from Printable interface, but related
	 */

	public void updatePageFormat() {
		PageFormat f = graph.getPageFormat();
		columnRule.setActiveOffset((int) (f.getImageableX()));
		rowRule.setActiveOffset((int) (f.getImageableY()));
		columnRule.setActiveLength((int) (f.getImageableWidth()));
		rowRule.setActiveLength((int) (f.getImageableHeight()));
		if (graph.isPageVisible()) {
			int w = (int) (f.getWidth());
			int h = (int) (f.getHeight());
			graph.setMinimumSize(new Dimension(w + 5, h + 5));
		} else
			graph.setMinimumSize(null);
		invalidate();
		// Execute fitAction...
		componentResized(null);
		graph.repaint();
	}

	/**
	 * Returns the graphUndoManager.
	 * @return GraphUndoManager
	 */

	public GraphUndoManager getGraphUndoManager() {
		return graphUndoManager;
	}

	/**
	 * Sets the graphUndoManager.
	 * @param graphUndoManager The graphUndoManager to set
	 */

	public void setGraphUndoManager(GraphUndoManager graphUndoManager) {
		this.graphUndoManager = graphUndoManager;
	}

	/** Delete element from library.  Caller is responsible for any warning messages
	 * as this method immediately deletes without any prompts.
	 */

	public void delete() {
		if (selected != null && fullLibraryAccess) {
			GraphCellsComponent tmp = selected;
			GPTransferable t = tmp.getTransferable();
			panel.getGraph().getModel().remove(t.getCells());
			panel.remove(tmp);
			setSelected(null);
			panel.revalidate();
			repaint();
		}
	}

	/** Resets the Graph undo manager
	 */

	public void resetGraphUndoManager() {
		graphUndoManager.discardAllEdits();
	}

		/**
		 * Create a Transferable to use as the source for a data transfer.
		 *
		 * @param buttonCircle  The component holding the data to be transfered.  This
		 *  argument is provided to enable sharing of TransferHandlers by
		 *  multiple components.
		 * @return  The representation of the data to be transfered.
		 *
		 */

		protected Transferable createTransferable(JComponent c) {
			if (selected != null) {
				dragging = true;
				return selected.getTransferable();
			}
			return null;
		}

	/**
	 * Returns the graphpad.
	 * @return GPGraphpad
	 */

	public GPGraphpad getGraphpad() {
		return graphpad;
	}

	/**
	 * Sets the graphpad.
	 * @param graphpad The graphpad to set
	 */

	public void setGraphpad(GPGraphpad graphpad) {
		this.graphpad = graphpad;
	}

	/**
	 * Returns the touch.
	 * @return Touch
	 */

	public Touch getTouch() {
		return touch;
	}

	/**
	 * Sets the touch.
	 * @param touch The touch to set
	 */

	public void setTouch(Touch touch) {
		this.touch = touch;
	}

		/**
		 * Messaged when the Document has created an edit, the edit is
		 * added to <code>graphUndoManager</code>, an instance of UndoManager.
		 */

		public void undoableEditHappened(UndoableEditEvent e) {
			if (addEdits)
				document.getGraphUndoManager().addEdit(e.getEdit());
			document.getGraphpad() .getEditUndoAction().update();
			document.getGraphpad() .getEditRedoAction().update();
		}

	/**
	 * Returns the actionMap.
	 * @return ActionMap
	 */

	public ActionMap getActionMap() {
		return actionMap;
	}

	/**
	 * Sets the actionMap.
	 * @param actionMap The actionMap to set
	 */

	public void setActionMap(ActionMap actionMap) {
		this.actionMap = actionMap;
	}

  /**
   * Returns the old Locale
   */

  public Locale getOldLocale(){
    return this.oldLocale;
  }

  /**
   * Returns the new Locale
   */

  public Locale getNewLocale(){
    return this.newLocale;
  }

  /** Adds the Propernameprovider and asks him for
   *  the proper names.
   *
   */

  public void addProperNameProvider(ProperNameProvider provider){
    properNameProvider.add(provider);

    Enumeration keys = provider.getKeys();
    while (keys.hasMoreElements()){
      String key = (String)keys.nextElement();
      String value = provider.getString(key);
      if (key != null && value != null){
        defaultNames.put(key, value);
      }
    }
  }

  /** removes the propernameprovider
   *
   */

  public void removeProperNameProvider(ProperNameProvider provider){
    properNameProvider.remove(provider);
    requeryDefaultNames();
  }

  /** merges the keys of any registered ProperNameProvider and returns them.
   *
   */

  public Enumeration getKeys() {
    return defaultNames.elements();
  }

  /** Returns the object for the key or null
   *
   */

  public Object handleGetObject(String key) {
    return defaultNames.get(key);
  }

	/**
	 * Returns the findPattern.
	 * @return String
	 */

	public String getFindPattern() {
		return findPattern;
	}

	/**
	 * Sets the findPattern.
	 * @param findPattern The findPattern to set
	 */

	public void setFindPattern(String findPattern) {
		this.findPattern = findPattern;
	}

	/**
	 * Returns the lastFound.
	 * @return Object
	 */

	public Object getLastFound() {
		return lastFound;
	}

	/**
	 * Sets the lastFound.
	 * @param lastFound The lastFound to set
	 */

	public void setLastFound(Object lastFound) {
		this.lastFound = lastFound;
	}

	/**
	 * Returns the overviewDialog.
	 * @return JDialog
	 */

	public JDialog getOverviewDialog() {
		return overviewDialog;
	}

	/**
	 * Returns true if <code>object</code> is a vertex, that is, if it
	 * is not an instance of Port or Edge, and all of its children are
	 * ports, or it has no children.
	 */

	public boolean isGroup(Object cell) {
		// Map the Cell to its View
		CellView view = getGraphLayoutCache().getMapping(cell, false);
		if (view != null)
			return !view.isLeaf();
		return false;
	}

	/**
	 * Sets the overviewDialog.
	 * @param overviewDialog The overviewDialog to set
	 */

	public void setOverviewDialog(JDialog overviewDialog) {
		this.overviewDialog = overviewDialog;
	}

	/**
	 * Returns true if <code>object</code> is a vertex, that is, if it
	 * is not an instance of Port or Edge, and all of its children are
	 * ports, or it has no children.
	 */

	public boolean isVertex(Object object) {
		if (!(object instanceof Port) && !(object instanceof Edge))
			return !isGroup(object) && object != null;
		return false;
	}

	/**
	 * Returns the splitPane.
	 * @return JSplitPane
	 */

	public GPSplitPane getSplitPane() {
		return splitPane;
	}

	/**
	 * Sets the splitPane.
	 * @param splitPane The splitPane to set
	 */

	public void setSplitPane(GPSplitPane splitPane) {
		this.splitPane = splitPane;
	}

	/**
	 * Returns the scrollPane.
	 * @return JScrollPane
	 */

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	/**
	 * Sets the scrollPane.
	 * @param scrollPane The scrollPane to set
	 */

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	/**
	 * Overrides <code>JComponent</code>'buttonSelect <code>getToolTipText</code>
	 * method in order to allow the graph controller to create a tooltip
	 * for the topmost cell under the mousepointer. This differs from JTree
	 * where the renderers tooltip is used.
	 * <p>
	 * NOTE: For <code>JGraph</code> to properly display tooltips of its
	 * renderers, <code>JGraph</code> must be a registered component with the
	 * <code>ToolTipManager</code>.  This can be done by invoking
	 * <code>ToolTipManager.sharedInstance().registerComponent(graph)</code>.
	 * This is not done automatically!
	 * @param event the <code>MouseEvent</code> that initiated the
	 * <code>ToolTip</code> display
	 * @return a string containing the  tooltip or <code>null</code>
	 * if <code>event</code> is null
	 */

	public String getToolTipText(MouseEvent event) {
		if (event != null) {
			Object cell = getFirstCellForLocation(event.getX(), event.getY());
			if (cell != null) {
				String tmp = convertValueToString(cell);
				String s = "<html>";
				if (tmp != null && tmp.length() > 0)
					s = s + "<strong>" + tmp + "</strong><br>";
				return s + getToolTipForCell(cell) + "</html>";
			}
		}
		return null;
	}

	/**
	 * Returns the columnRule.
	 * @return Rule
	 */

	public Rule getColumnRule() {
		return columnRule;
	}

	/**
	 * Notification from the <code>UIManager</code> that the L&F has changed.
	 * Replaces the current UI object with the latest version from the
	 * <code>UIManager</code>. Subclassers can override this to support
	 * different GraphUIs.
	 * @see JComponent#updateUI
	 *
	 */

	public void updateUI() {
		setUI(new GPGraphUI());
		invalidate();
	}

	/**
	 * Returns the rowRule.
	 * @return Rule
	 */

	public Rule getRowRule() {
		return rowRule;
	}

	/** Returns true if the given vertices are conntected by a single edge
	 * in this document.
	 */

	public boolean isNeighbour(Object v1, Object v2) {
		Object[] edges = getEdgesBetween(v1, v2);
		return (edges != null && edges.length > 0);
	}

	/**Overriden, in order to be able to deal with window events*/

	protected void processWindowEvent(WindowEvent e) {
		//
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			// only close the window when we are not in embedded mode
			// release resources and exit if we are not running embedded, buttonImage.buttonEdge., as
			// part of another application
			//super.processWindowEvent(buttonEdge);
			this.dispose() ;
		}
	}

	/**
	 * Sets the columnRule.
	 * @param columnRule The columnRule to set
	 */

	public void setColumnRule(Rule columnRule) {
		this.columnRule = columnRule;
	}

