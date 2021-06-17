	/**
	 * Sets the rowRule.
	 * @param rowRule The rowRule to set
	 */

	public void setRowRule(Rule rowRule) {
		this.rowRule = rowRule;
	}

	/**
	 * Returns the enableTooltips.
	 * @return boolean
	 */

	public boolean isEnableTooltips() {
		return enableTooltips;
	}

	/**
	 * Sets the enableTooltips.
	 * @param enableTooltips The enableTooltips to set
	 */

	public void setEnableTooltips(boolean enableTooltips) {
		this.enableTooltips = enableTooltips;

		if (this.enableTooltips)
			ToolTipManager.sharedInstance().registerComponent(graph);
		else
			ToolTipManager.sharedInstance().unregisterComponent(graph);
	}

	/** Sets the current text area
	 *
	 */

	public void setTextArea(JTextArea ta) {
		currentWindow = ta;
	}

	/** clears the window
	 */

	private void clearWindow() {
		currentWindow.setText("");
	}

	/**
	 * Returns the internalFrame.
	 * @return GPInternalFrame
	 */

	public GPInternalFrame getInternalFrame() {
		return internalFrame;
	}

	/** writes a boolean value to the target
	 */

	public void print(boolean b) {
		if (showOrig)
			orig.print(b);
		if (b)
			target.append("true"/*#Frozen*/);
		else
			target.append("false"/*#Frozen*/);
		target.setCaretPosition(target.getText().length());
	}

	/** writes a boolean value to the target
	 *
	 */

	public void println(boolean b) {
		if (showOrig)
			orig.println(b);

		if (b)
			target.append("true\n"/*#Frozen*/);
		else
			target.append("false\n"/*#Frozen*/);
		target.setCaretPosition(target.getText().length());
	}

	/**
	 * Sets the internalFrame.
	 * @param internalFrame The internalFrame to set
	 */

	protected void setInternalFrame(GPInternalFrame internalFrame) {
		this.internalFrame = internalFrame;
	}

			/**
			 * Overriting this so that I could modify an eiditor container.
			 * see http://sourceforge.net/forum/forum.php?thread_id=781479&forum_id=140880
			 */

			protected Container createContainer() {
				return new MultiLinedEditor.ModifiedEditorContainer();
			}

	/** writes the value to the target
	 *
	 */

	public void print(char c) {
		if (showOrig)
			orig.print(c);

		char[] tmp = new char[1];
		tmp[0] = c;
		target.append(new String(tmp));
		target.setCaretPosition(target.getText().length());
	}

	/** writes the value to the target
	 *
	 */

	public void println(char c) {
		if (showOrig)
			orig.println(c);

		char[] tmp = new char[2];
		tmp[0] = c;
		tmp[1] = '\n';
		target.append(new String(tmp));
		target.setCaretPosition(target.getText().length());
	}

	/** writes the value to the target
	 *
	 */

	public void print(char[] s) {
		if (showOrig)
			orig.print(s);

		target.append(new String(s));
		target.setCaretPosition(target.getText().length());
	}

	/** writes the value to the target
	 *
	 */

	public void println(char[] s) {
		if (showOrig)
			orig.println(s);

		target.append(new String(s) + "\n"/*#Frozen*/);
		target.setCaretPosition(target.getText().length());
	}

	/** writes the value to the target
	 *
	 */

	public void print(double d) {
		if (showOrig)
			orig.print(d);

		target.append(Double.toString(d));
		target.setCaretPosition(target.getText().length());
	}

	/** writes the value to the target
	 *
	 */

	public void println(double d) {
		if (showOrig)
			orig.println(d);

		target.append(Double.toString(d) + "\n"/*#Frozen*/);
		target.setCaretPosition(target.getText().length());
	}

	/** writes the value to the target
	 *
	 */

	public void print(float f) {
		if (showOrig)
			orig.print(f);

		target.append(Float.toString(f));
		target.setCaretPosition(target.getText().length());
	}

	/**
	 * Returns the writeProperties.
	 * @return Hashtable
	 */

	public Hashtable getWriteProperties() {
		return writeProperties;
	}

	/**
	 * Returns the graphModelProvider.
	 * @return GraphModelProvider
	 */

	public GraphModelProvider getGraphModelProvider() {
		return graphModelProvider;
	}

	/** writes the value to the target
	 *
	 */

	public void println(float f) {
		if (showOrig)
			orig.println(f);

		target.append(Float.toString(f) + "\n"/*#Frozen*/);
		target.setCaretPosition(target.getText().length());
	}

	/** writes the value to the target
	 *
	 */

	public void print(int i) {
		if (showOrig)
			orig.print(i);

		target.append(Integer.toString(i));
		target.setCaretPosition(target.getText().length());
	}

	/**
	 * Sets the writeProperties.
	 * @param writeProperties The writeProperties to set
	 */

	public void setWriteProperties(Hashtable writeProperties) {
		this.writeProperties = writeProperties;
	}

	/** writes the value to the target
	 *
	 */

	public void println(int i) {
		if (showOrig)
			orig.println(i);

		target.append(Integer.toString(i) + "\n"/*#Frozen*/);
		target.setCaretPosition(target.getText().length());
	}

	/**
	 * Returns the networkModel.
	 * @return GraphNetworkModel
	 */

	public GraphNetworkModel getNetworkModel() {
		return networkModel;
	}

	/** writes the value to the target
	 *
	 */

	public void print(long l) {
		if (showOrig)
			orig.print(l);

		target.append(Long.toString(l));
		target.setCaretPosition(target.getText().length());
	}

	/** writes the value to the target
	 *
	 */

	public void println(long l) {
		if (showOrig)
			orig.println(l);

		target.append(Long.toString(l) + "\n"/*#Frozen*/);
		target.setCaretPosition(target.getText().length());
	}

	/**
	 * Sets the networkModel.
	 * @param networkModel The networkModel to set
	 */

	public void setNetworkModel(GraphNetworkModel networkModel) {
		this.networkModel = networkModel;
	}

	/** writes the value to the target
	 *
	 */

	public void print(Object o) {
		if (showOrig)
			orig.print(o);

		target.append(o.toString());
		target.setCaretPosition(target.getText().length());
	}

	/** writes the value to the target
	 *
	 */

	public void println(Object o) {
		if (showOrig)
			orig.println(o);

		target.append(o.toString() + "\n"/*#Frozen*/);
		target.setCaretPosition(target.getText().length());
	}

	/** writes the value to the target
	 *
	 */

	public void print(String s) {
		if (showOrig)
			orig.print(s);

		target.append(s);
		target.setCaretPosition(target.getText().length());
	}

	/** writes the value to the target
	 *
	 */

	public void println(String s) {
		if (showOrig)
			orig.println(s);

		target.append(s + "\n"/*#Frozen*/);
		target.setCaretPosition(target.getText().length());
	}

	/** writes the value to the target
	 *
	 */

	public void println() {
		if (showOrig)
			orig.println();

		target.append(new String("\n"/*#Frozen*/));
		target.setCaretPosition(target.getText().length());
	}

	/**
	 * Returns the barKey.
	 * @return String
	 */

	public String getBarKey() {
		return barKey;
	}

	/**
	 * Returns the barValue.
	 * @return String
	 */

	public String getBarValue() {
		return barValue;
	}

	/**
	 * Returns the pos.
	 * @return int
	 */

	public int getPos() {
		return pos;
	}

	/**
	 * Sets the barKey.
	 * @param barKey The barKey to set
	 */

	public void setBarKey(String barKey) {
		this.barKey = barKey;
	}

	/**
	 * Sets the barValue.
	 * @param barValue The barValue to set
	 */

	public void setBarValue(String barValue) {
		this.barValue = barValue;
	}

	/**
	 * Sets the pos.
	 * @param pos The pos to set
	 */

	public void setPos(int pos) {
		this.pos = pos;
	}

	/** Prints the Entry with all properties.
	 * 
	 */	

	public String toString(){
		StringBuffer b = new StringBuffer();
		b.append("GPBarEntry: barKey=");
		b.append(barKey);
		b.append("; pos=");
		b.append(pos);
		b.append("; barValue=");
		b.append(barValue);
		return b.toString() ;
	}

	/**
	 * Create the menubar for the app.  By default this pulls the
	 * definition of the menu from the associated resource file.
	 */

	public JMenuBar createMenubar() {
		JMenuBar mb = new JMenuBar();

		String[] menuKeys = tokenize(MENUBAR, Translator.getString(MENUBAR));
		for (int i = 0; i < menuKeys.length; i++) {
			String itemKey = Translator.getString(menuKeys[i] + SUFFIX_MENU);
			if (itemKey == null) {
				System.err.println(
					"Can't find MenuKey: '"
						+ menuKeys[i]
						+ "'. I'm ignoring the MenuKey!");
				continue;
			}
			String[] itemKeys = tokenize(menuKeys[i], itemKey);
			JMenu m = createMenu(menuKeys[i], itemKeys);
			if (m != null)
				mb.add(m);
		}
		return mb;
	}

	/** creates the popup menu for the graph
	 */

	public JPopupMenu createGraphPopupMenu() {
		return createPopupMenu(GRAPH_POPUP);
	}

	/** creates the popup menu for the library
	 */

	public JPopupMenu createLibraryPopupMenu() {
		return createPopupMenu(LIBRARY_POPUP);
	}

	/** creates a popup menu for the specified key.
	 */

	protected JPopupMenu createPopupMenu(String key) {
		JPopupMenu pop = new JPopupMenu();
		String[] itemKeys = tokenize(key, Translator.getString(key));
		for (int i = 0; i < itemKeys.length; i++) {
			if (itemKeys[i].equals("-")) {
				pop.addSeparator();
			} else {
				Component[] mi = createMenuItem(itemKeys[i]);
				for (int j = 0; j < mi.length; j++) {
					pop.add(mi[j]);
				}
			}
		}

		LocaleChangeAdapter.updateContainer(pop);

		return pop;
	}

		/**
		 * Returns a new map that contains all (key, value)-pairs
		 * of <code>newState</code> where either key is not used
		 * or value is different for key in <code>oldState</code>.
		 * In other words, this method removes the common entries
		 * from oldState and newState, and returns the "difference"
		 * between the two.
		 * 
		 * This method never returns null.
		 */

		public Map diffMap(Map oldState, Map newState) {
			Map diff = new Hashtable();
			Iterator it = newState.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				Object key = entry.getKey();
				Object newValue = entry.getValue();
				Object oldValue = oldState.get(key);
				if (oldValue == null || !oldValue.equals(newValue))
					diff.put(key, newValue);
			}
			return diff;
		}

	/** creates a menu for the specified key
	 */

	protected JMenu createMenu(String key) {

		return createMenu(key, tokenize(key, Translator.getString(key)));
	}

	/** updates all Abstract Buttons from this action
	 */

	public void update() {
		Enumeration enumerator = abstractButtons.elements();
		while (enumerator.hasMoreElements()) {
			AbstractButton button = (AbstractButton) enumerator.nextElement();
			button.setSelected(isSelected(button.getActionCommand()));
		}
	}

	/** Should return presentation Text for the 
	 *  action command or null 
	 *  for the default
	 */

	public String getPresentationText(String actionCommand) {
		if (actionCommand == null)
			return null;
			
		for (int i = 0; i < lookAndFeels.length ; i++){
			if (lookAndFeels[i].getClassName().equals(actionCommand)){
				return lookAndFeels[i].getName() ;
			}
		}
		
		return actionCommand;
	}

	/** Shows a file open dialog and returns the filename. */

	public String openDialog(String message, String extension, String desc) {
		return dialog(message, true, extension, desc);
	}

	/** Shows a file save dialog and returns the filename. */

	public String saveDialog(String message, String extension, String desc) {
		return dialog(message, false, extension, desc);
	}

		/** Returns true if the file ends with the full extension or
		 *  if the file is a directory
		 *
		 */

		public boolean accept(File file) {
			return file.isDirectory()
				|| file.getName().toLowerCase().endsWith(fullExt);
		}

		/** returns the desc
		 */

		public String getDescription() {
			return desc;
		}

	/** Returns the List Cell Renderer for the
	 *  Items. By default returns null.
	 *
	 */

	protected ListCellRenderer getItemListCellRenderer() {
		return null;
	};

	/** Returns the item presentation text
	 *  (buttonEdge.g. for the MenuItem)
	 *
	 *  The default Implemenation returns
	 *  <tt>item.toString()</tt>
	 *
	 */

	protected String getItemPresentationText(Object item) {
		return item.toString();
	}

	/**
	 * Returns the buttonActivity.
	 * @return JToggleButton
	 */

	public JToggleButton getButtonActivity() {
		return buttonActivity;
	}

	/** updates all Abstract Buttons from this action
	 */

	public void update() {
		super.update();

		Enumeration enumerator = abstractButtons.elements();
		while (enumerator.hasMoreElements()) {
			AbstractButton button = (AbstractButton) enumerator.nextElement();
			button.setSelected(isSelected(button.getActionCommand()));
		}
	}

	/** removes the abstract action from the
	 *  action control
	 */

	public void removeAbstractButton(AbstractButton button){
		abstractButtons.remove(button);
	}

	/**
	 * Returns the buttonDecision.
	 * @return JToggleButton
	 */

	public JToggleButton getButtonDecision() {
		return buttonDecision;
	}

	/**
	 * Returns the buttonStart.
	 * @return JToggleButton
	 */

	public JToggleButton getButtonStart() {
		return buttonStart;
	}

	/**
	 * Returns the buttonEnd.
	 * @return JToggleButton
	 */

	public JToggleButton getButtonEnd() {
		return buttonEnd;
	}

	/** Shows a file chooser with the
	 *  file filters from the file formats
	 *  to select a file.
	 *
	 *  Furthermore the method uses the selected
	 *  file format for the read process.
	 *
	 *  @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
	 *  @see GraphModelProviderRegistry
	 */

	public void actionPerformed(ActionEvent e) {
		String name =
			JOptionPane.showInputDialog(Translator.getString("URLDialog", new Object[]{"foo.gpd"}));

		// canceled?
		if (name == null)
			return;

		// open the graphpad
		try {
			graphpad.addDocument(new URL(name));
		} catch (MalformedURLException ex) {
			JOptionPane.showMessageDialog(
				graphpad,
				ex.getLocalizedMessage(),
				Translator.getString("Error"),
				JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Returns the buttonSplit.
	 * @return JToggleButton
	 */

	public JToggleButton getButtonSplit() {
		return buttonSplit;
	}

	/**
	 * Calls the method setMaximum(true) for each
	 * JInternalFrame.
	 *
	 * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
	 */

	public void actionPerformed(ActionEvent e) {
		JInternalFrame[] ajif = graphpad.getAllFrames();

		for (int i = 0; i < ajif.length; i++) {
			try {
				ajif[i].setMaximum(true);
			} catch (java.beans.PropertyVetoException pvex) {

			}
		}
	}

	/**
	 * Calls the method setIcon(true) for each
	 * JInternalFrame.
	 * 
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
	 */

	public void actionPerformed(ActionEvent e) {
		JInternalFrame[] ajif = graphpad.getAllFrames();

		for (int i = 0; i < ajif.length; i++) {
			try {
				ajif[i].setIcon(true);
			} catch (java.beans.PropertyVetoException pvex) {
				// do nothing
			}
		}
	}

	/**
	 * Returns the buttonJoin.
	 * @return JToggleButton
	 */

	public JToggleButton getButtonJoin() {
		return buttonJoin;
	}

	/**
	 * Returns the buttonEdge.
	 * @return JToggleButton
	 */

	public JToggleButton getButtonEdge() {
		return buttonEdge;
	}

	/**
	 * Override or implement to map from cells to urls.
	 * @param cell Cell that should be converted to a URL
	 * @return String String that can be used as a href
	 */

	public String getURL(GPGraph graph, Object cell) {
		if (cell instanceof DefaultGraphCell) {
			final Object userObject = ((DefaultGraphCell) cell).getUserObject();
			if (userObject instanceof GPUserObject) {
				Object url = ((GPUserObject) userObject).getProperty(GPUserObject.keyURI);
				if (url != null)
					return url.toString();
			}
		}
		return cell.toString();
	}

	/**
	 * Override or implement to map from cells to labels.
	 * @param cell Cell that should be converted to a label
	 * @return String String that can be used as a label
	 */

	public String getLabel(GPGraph graph, Object cell) {
		return graph.convertValueToString(cell.toString());
	}

	/** Returns the name of the action
	 *
	 */

	public String getName() {
		return (String) getValue(NAME);
	}

	/**
	 * Returns the buttonLine.
	 * @return JToggleButton
	 */

	public JToggleButton getButtonLine() {
		return buttonLine;
	}

	/**
	 * Gets the GPInternalFrame from the ActionEvent and sets the
	 * frame toFront and selected.
	 *
	 * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
	 */

	public void actionPerformed(ActionEvent e) {
		GPInternalFrame gpframe = (GPInternalFrame) this.getSelectedItem(e);
		gpframe.toFront();
		try {
			gpframe.setSelected(true);
		} catch (PropertyVetoException pve) {
		}
	}

	/**Returns a JMenu and stores the JMenu at the menus Vector
	 *
	 * @see #menus
	 * @see org.jgraph.pad.actions.AbstractActionList#getMenuBarComponent()
	 */

	protected JMenu getMenuBarComponent() {
		JMenu menu = new JMenu(this);
		menu.setName(getName());
		menus.add(menu);
		return menu;
	}

	/** returns the actionCommand (The presentation file name from the document)
	 */

	public String getPresentationText(String actionCommand) {
		return actionCommand;
	}

	/** updates the window list at the menu entries
	 *
	 */

	public void update(){
		super.update() ;

		JInternalFrame[] iframes = graphpad.getAllFrames();

		for (int j = 0; j < menus.size(); j++) {
			JMenu menu = (JMenu) menus.get(j);
			menu.removeAll();

			for (int i = 0; i < iframes.length; i++) {
				GPInternalFrame internalFrame = (GPInternalFrame) iframes[i];
				menu.add(
					getMenuComponent(
						internalFrame.getDocument().getFrameTitle(),
						internalFrame));
			}
		}
	}

	/** Returns a JMenuItem with a link to this action.
	 */

	protected Component getMenuComponent(String actionCommand) {
		JMenuItem item = new JMenuItem(this);
		GPBarFactory.fillMenuButton(item, getName(), actionCommand);
		String presentationText = getPresentationText(actionCommand);
		if (presentationText != null)
			item.setText(presentationText);

		return item;
	}

	/**
	 * Returns the buttonSelect.
	 * @return JToggleButton
	 */

	public JToggleButton getButtonSelect() {
		return buttonSelect;
	}

	/** Returns a clean JButton which has a link to this action.
	 *
	 */

	protected Component getToolComponent(String actionCommand) {
		AbstractButton b = new JButton(this) {
			public float getAlignmentY() {
				return 0.5f;
			}
		};
		return GPBarFactory.fillToolbarButton(
			b,
			getName(),
			actionCommand);
	}

	/** empty implementation for this typ of action
	 *
	 */

	public void update() {
		if (graphpad.getCurrentDocument() == null)
			setEnabled(false);
		else
			setEnabled(true);
	}

	/** Should return presentation Text for the
	 *  action command or null
	 *  for the default
	 */

	public String getPresentationText(String actionCommand) {
		return null;
	}

	/**
	 * Returns the buttonText.
	 * @return JToggleButton
	 */

	public JToggleButton getButtonText() {
		return buttonText;
	}

	/**
	 * Returns the buttonZoomArea.
	 * @return JToggleButton
	 */

	public JToggleButton getButtonZoomArea() {
		return buttonZoomArea;
	}

	/**
	 * Returns the message.
	 *
	 * @return The message from the status bar
	 */

	public String getMessage() {
		return message.getText() ;
	}

	/**Returns <tt>Default Graph Model</tt>
	 *
	 * @see org.jgraph.pad.GraphModelProvider#getPresentationName()
	 */

	public String getPresentationName() {
		return "Default Graph Model";
	}

	/**
	 * Returns the scale.
	 * @return JLabel
	 */

	public String getScale() {
		return scale.getText() ;
	}

	/**
	 * Sets the message.
	 * @param message The message to set
	 */

	public void setMessage(String message) {
		this.message.setText(message);
	}

	/**
	 * Sets the scale.
	 * @param scale The scale to set
	 */

	public void setScale(String scale) {
		this.scale.setText(scale);
	}

	/**
	 * Creates a clean default graph model.
	 *
	 * @see DefaultGraphModel
	 * @see org.jgraph.pad.GraphModelProvider#createCleanGraphModel()
	 */

	public GraphModel createCleanGraphModel() {
		return new DefaultGraphModel();
	}

	/** Currently returns every time false.
	 *
	 * @see org.jgraph.pad.GraphModelProvider#isMutateAbleTo(Class)
	 */

	public boolean isMutateAbleTo(Class otherGraphModelClass) {
		return false;
	}

	/**Adds a Port to the model specific vertex object
	 *
	 * @see org.jgraph.pad.GraphModelProvider#addPort(Object, Object)
	 */

	public void addPort(Object vertex, Object port) {
		if (port instanceof DefaultPort) {
			if (vertex instanceof DefaultMutableTreeNode) {
				((DefaultMutableTreeNode) vertex).add((DefaultPort) port);
			}
		}
	}

	/**
	 * Creates a new clean graph for this model provider
	 *
	 * @see org.jgraph.pad.GraphModelProvider#createCleanGraph(GraphModel model)
	 */

	public GPGraph createCleanGraph(GraphModel model) {
		GPGraph graph = new GPGraph(model);
		// TODO: Remove this code when VM is fixed on Mac OS X
		if (System.getProperty("os.name").equals("Mac OS X")) {
			String s = Translator.getString("doubleBufferedOnMacOSX");
			if (s != null)
				graph.setDoubleBuffered(Boolean.getBoolean(s));
		}
		return graph;
	}

	/**Returns the selected graph model provider
	 *
	 */

	public GraphModelProvider getSelectedGraphModelProvider(){
		if (lstGraphModelProviders.getModel() .getSize() == 0)
			return null;
		return (GraphModelProvider)lstGraphModelProviders.getSelectedValue() ;
	}

	/**
	 * Returns the answer.
	 * @return int
	 */

	public int getAnswer() {
		return answer;
	}

	/** shows the select dialog. If only one
	 *  graph model provider is available the
	 *  method returns directly.
	 */

	public void show(){

		if (lstGraphModelProviders .getModel() .getSize() <= 1)
			return;
		else
			super.show();
	}

	/**
	 * Create a clone of the user object. This is provided for
	 * subclassers who need special cloning. This implementation
	 * simply returns a reference to the original user object.
	 *
	 * @return Object  a clone of this cells user object.
	 */

	protected Object cloneUserObject() {
		return userObject;
	}

	/**
	 * Returns a cell handle for the view, if the graph and the view
	 * are sizeable.
	 */

	public CellHandle getHandle(GraphContext context) {
		if (GraphConstants.isSizeable(getAllAttributes())
				&& context.getGraph().isSizeable())
			return new JGpdSizeHandle(this, context);
		return null;
	}

	/** removes the specified Internal Frame from the Graphpad
	 */

	public void removeGPInternalFrame(GPInternalFrame f) {
		if (f == null)
			return;
		f.setVisible(false);
		desktop.remove(f);
		doc2InternalFrame.remove(f.getDocument());
		f.cleanUp();
		JInternalFrame[] frames = desktop.getAllFrames();
		if (frames.length > 0) {
			try {
				frames[0].setSelected(true);
			} catch (PropertyVetoException e) {
			}
		}
	}

	/**
	 * Returns the marqueeHandler.
	 * @return JGpdMarqueeHandler
	 */

	public GPMarqueeHandler getMarqueeHandler() {
		return marqueeHandler;
	}

	/**Returns a JMenu and stores the JMenu at the menus Vector
	 *
	 * @see #menus
	 * @see org.jgraph.pad.actions.AbstractActionList#getMenuBarComponent()
	 */

	protected JMenu getMenuBarComponent() {
//		JMenu menu = new JMenu(this);
//		menu.setName(getName());
//		menus.add(menu);
		return null; //menu;
	}

	/**
	 * Creates a new clean graph for this model provider
	 *
	 * @see org.jgraph.pad.GraphModelProvider#createCleanGraph(GraphModel model)
	 */

	public GPGraph createCleanGraph(GraphModel model) {
		GPGraph graph = new GPGraph(model);
		// TODO: Remove this code when VM is fixed on Mac OS X
		if (System.getProperty("os.name").equals("Mac OS X")) {
			String s = Translator.getString("doubleBufferedOnMacOSX");
			if (s != null)
				graph.setDoubleBuffered(Boolean.getBoolean(s));
		}
		graph.setDragEnabled(true);
		return graph;
	}

  /**
   * This puts a property in the map. It is indexed by its
   * name and holds the specified value. Stands for Put PROPerty.
   * @param name String
   * @param value Object
   */

  public void pprop(String name, Object value) {
    LogoList listTemp = new LogoList();
    boolean found = false;

    while (!data.empty()) {
      String pname = (String) data.first();
      data.butFirstF();
      Object pValue = data.first();
      data.butFirstF();

      if (pname.equalsIgnoreCase(name)) {
        listTemp.fputF(value);
        listTemp.fputF(name);
        found = true;
      } else {
        listTemp.fputF(pValue);
        listTemp.fputF(pname);
      }
    }

    if (!found) {
      listTemp.fputF(value);
      listTemp.fputF(name);
    }

    data = listTemp;
  }

  /**
   * Removes a property from the list. Stands for
   * Remove PROPerty.
   * @param name String
   */

  public void rprop(String name) {
    LogoList listTemp = new LogoList();

    while (!data.empty()) {
      String pname = (String) data.first();
      data.butFirstF();
      Object pValue = data.first();
      data.butFirstF();

      // copy to new list only if not what we're removing
      if (!pname.equalsIgnoreCase(name)) {
        listTemp.fputF(pValue);
        listTemp.fputF(pname);
      }
    }

    data = listTemp;
  }

  /**
   * Gets the property indexed by the name specified.
   * Stands for Get PROPerty.
   * @param name String
   * @return Object
   */

  public Object gprop(String name) {
    LogoList listTemp = data.duplicate();

    while (!listTemp.empty()) {
      String pname = (String) listTemp.first();
      listTemp.butFirstF();
      Object value = listTemp.first();
      listTemp.butFirstF();

      if (pname.equalsIgnoreCase(name))
        return value;
    }

    return null;
  }

  /**
   * Returns a list of all property indexes contained within the
   * map.
   * @return LogoList
   */

  public LogoList lprops() {
    LogoList listTemp = new LogoList();
    LogoList outList = new LogoList();

    while (!data.empty()) {
      String name = (String) data.first();
      data.butFirstF();
      Object value = data.first();
      data.butFirstF();

      listTemp.fputF(value);
      listTemp.fputF(name);

      outList.fputF(name);
    }

    data = listTemp;

    return outList;
  }

  /**
   * Returns a string of the data within.
   * @return String
   */

  public String toString() {
    return data.toString();
  }

  /**
   * Creates a copy of this map and returns it.
   * @return LogoMap
   */

  public LogoMap duplicate() {
    return new LogoMap(data);
  }

  /**
   * Puts a link at the front of the list.
   * @param o Object
   */

  public void fputF(Object o) {
    head = new LogoLink(o, head);
  }

  /**
   * Puts a link at the front of a copied list and returns the
   * copy.
   * @param o Object
   * @return LogoList
   */

  public LogoList fput(Object o) {
    LogoList out = this.duplicate();
    out.fputF(o);
    return out;
  }

  /**
   * Puts a link at the end of the list
   * @param o Object
   */

  public void lputF(Object o) {
    if (empty()) {
      head = new LogoLink(o);
    } else {
      LogoLink cur = head;
      while (cur.next != null) {
        cur = cur.next;
      }
      cur.next = new LogoLink(o);
    }
  }

  /**
   * Puts a link at the end of a copied list and returns
   * the copy
   * @param o Object
   * @return LogoList
   */

  public LogoList lput(Object o) {
    LogoList out = this.duplicate();
    out.lputF(o);
    return out;
  }

  /**
   * Returns whether there are elements in this list.
   * @return boolean
   */

  public boolean empty() {
    return head == null;
  }

  /**
   * Returns a reference to the first object in the list
   * @return Object
   */

  public Object first() {
    if (!empty()) {
      return head.data;
    } else {
      if (logoErrors)
        throw new RuntimeException("First does not like [] as input.");
      else throw new RuntimeException("Cannot use first on an empty list.");
    }
  }

  /**
   * Returns a reference to the last object in the list
   * @return Object
   */

  public Object last() {
    if (!empty()) {
      LogoLink cur = head;
      while (cur.next != null)
        cur = cur.next;
      return cur.data;
    } else {
      if (logoErrors)
        throw new RuntimeException("Last does not like [] as input.");
      else throw new RuntimeException("Cannot use last on an empty list");
    }
  }

  /**
   * Returns a list with all but the first element if this list.
   * @return LogoList
   */

  public LogoList butFirst() {
    LogoList out = duplicate();
    out.butFirstF();
    return out;
  }

  /**
   * Returns a list with all but the last element of this list.
   * @return LogoList
   */

  public LogoList butLast() {
    LogoList out = duplicate();
    out.butLastF();
    return out;
  }

  /**
   * Prepares the object for use.
   * @param file String
   * @param out OutputStream
   * @throws IOException
   */

  protected void init(String file, OutputStream out) throws IOException {
    filename = file;
    output = new PrintStream(out);
    tempRead = File.createTempFile("file1",".temp");
    tempWrite = File.createTempFile("file2",".temp");
  }

  /**
   * Removes the first element from the list. Returns a reference to this list.
   * @return LogoList
   */

  public LogoList butFirstF() {
    if (!empty()) {
      head = head.next;
    } else {
      if (logoErrors)
        throw new RuntimeException("butfirst does not like " + this.toString() +
                                   " as input.");
      else throw new RuntimeException("Cannot use butfirst on an empty list");
    }

    return this;
  }

  /**
   * Removes the last element of the list. Returns a reference to this list.
   * @return LogoList
   */

  public LogoList butLastF() {
    if (!empty()) {
      if (head.next == null) {
        // means head is the last one, remove it
        head = null;
      } else {
        LogoLink cur = head;
        while (cur.next.next != null)
          cur = cur.next;
        cur.next = null;
      }
    } else {
      if (logoErrors)
        throw new RuntimeException("butlast does not like [] as input.");
      else throw new RuntimeException("Cannot use butlast on an empty list");
    }

    return this;
  }

  /**
   * Returns the number of elements in this list.
   * @return int
   */

  public int count() {
    if (empty()) {
      return 0;
    } else {
      int count = 1;
      LogoLink cur = head;

      while (cur.next != null) {
        count++;
        cur = cur.next;
      }

      return count;
    }
  }

  /**
   * Removes comments and junk only humans need and puts in LogoList. Step 1
   * @throws IOException - if file cannot be opened for any reason.
   * @param file file to preparse
   */

  protected void preparse(File file) throws IOException {
    File tRead = tempRead;
    try {
      debug("preparsing " + tempRead.getAbsolutePath() + " to " +
            tempWrite.getAbsolutePath());
      prepareFiles(file, tempWrite, true);
      tempOut.println("#inserted " + file.getAbsolutePath());

      String line = tempIn.readLine();
      while (line != null) {

        if (line.indexOf("//") > 0) {
          line = line.substring(0, line.indexOf("//"));
        }

        if (line.length() > 0) {
          tempOut.println(line);
        }

        line = tempIn.readLine();
      }

      debug("Preparsed " + filename);
    } catch (IOException ex) {
      tempRead = tRead; // make sure this runs
      throw ex;
    }
    tempRead = tRead;
  }

  /**
   * Creates a copy of this list and returns it.
   * @return LogoList
   */

  public LogoList duplicate() {
    LogoList out = new LogoList();

    out.copyListToMemory(head);

    return out;
  }

  /**
   * Returns a string representation of this list. Conforms to Logo display.
   * @return String
   */

  public String toString() {
    StringBuffer out = new StringBuffer("[");

    LogoLink cur = head;

    while (cur != null) {
      if (cur.data != null)
        out.append(cur.data.toString());
      else out.append(" ");
      if (cur.next != null) out.append(" ");

      cur = cur.next;
    }

    out.append("]");
    return out.toString();
  }

  /**
   * Fast removal of any occurances of "o"
   * @param o Object
   */

  public void removeAll(Object o) {
    LogoList copy = duplicate();
    this.clean();

    while (!copy.empty()) {
      if (copy.first() != o)
        fput(copy.first());
      copy.butFirstF();
    }

    copy = duplicate();
    clean();
    while (!copy.empty()) {
      fput(copy.first());
      copy.butFirstF();
    }

  }

  /**
   * Imitates a doList command in logo. Pass the ForEachListener with the
   * appropriate instructions to run on each list item.
   * @param fel ForEachListener
   */

  public void doList(ForEachListener fel) {
    LogoLink cur = head;
    while (cur != null) {
      fel.doFor(cur.data);
      cur = cur.next;
    }
  }

  /**
   * Returns whether the list contains the object requested.
   * @param o Object
   * @return boolean
   */

  public boolean contains(Object o) {
    LogoLink cur = head;
    boolean found = false;

    while (cur != null && !found) {
      found = cur.data == o || (cur.data != null && cur.data.equals(o));

      if (o instanceof String && cur.data instanceof String) {
        found |= ((String)o).equalsIgnoreCase((String)cur.data);
      }

      cur = cur.next;
    }

    return found;
  }

  /**
   * Removes all data from this object.
   */

  public void clean() {
    head = null;
  }

  /**
   * Rewrites the type so that the information is correctly formatted for
   * SQL
   * @param value String
   * @param type String
   * @return String
   */

  protected String writeType(String value, String type) {
    if (type.matches("CHAR\\(\\d+\\).*") || type.matches("VARCHAR.*")) {
      return "'" + value.trim().replaceAll("\'","\\'") + "'";
//      return "STRING";
    } else {
//      return type;
      return value.trim();
    }
  }

  /**
   * Finite State Machine, Compiler Directive?
   * Returns if this line is a compiler directive or not.
   * @param line String
   * @return boolean
   */

  protected boolean FSM_compilerDirective(String line) {
    for(int i = 0; i < line.length(); i++) {
      if (line.charAt(i) == '#') {
        return true;
      } else if (line.charAt(i) == ' ' || line.charAt(i) == '\t') {
        // do nothing, its is still possible
      } else {
        // proven wrong
        return false;
      }
    }
    return false;
  }

  /**
   * Sends output to console if debug is on.
   * @param text String
   */

  public void debug(String text) {
    if (debugOn) debugStream.println(text);
  }

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJPanel(), java.awt.BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jJMenuBar
	 * 
	 * @return javax.swing.JMenuBar
	 */

	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getFileMenu());
			jJMenuBar.add(getHelpMenu());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jMenu
	 * 
	 * @return javax.swing.JMenu
	 */

	private JMenu getFileMenu() {
		if (fileMenu == null) {
			fileMenu = new JMenu();
			fileMenu.setText("File");
			fileMenu.add(getExitMenuItem());
		}
		return fileMenu;
	}

	/**
	 * This method initializes jMenu
	 * 
	 * @return javax.swing.JMenu
	 */

	private JMenu getHelpMenu() {
		if (helpMenu == null) {
			helpMenu = new JMenu();
			helpMenu.setText("Help");
			helpMenu.add(getAboutMenuItem());
		}
		return helpMenu;
	}

	/**
	 * This method initializes jMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */

	private JMenuItem getExitMenuItem() {
		if (exitMenuItem == null) {
			exitMenuItem = new JMenuItem();
			exitMenuItem.setText("Exit");
			exitMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return exitMenuItem;
	}

	/**
	 * This method initializes jMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */

	private JMenuItem getAboutMenuItem() {
		if (aboutMenuItem == null) {
			aboutMenuItem = new JMenuItem();
			aboutMenuItem.setText("About");
			aboutMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane
							.showMessageDialog(
									null,
									"MPGCalculator \n\nBy Yann Laviolette, Stratag�me Ing�nierie Conseil\n(c) 2005 - Under GPL Licence",
									"About", JOptionPane.INFORMATION_MESSAGE);

				}
			});
		}
		return aboutMenuItem;
	}

	/**
	 * This method initializes txtDistance
	 * 
	 * @return javax.swing.JTextField
	 */

	private JTextField getTxtDistance() {
		if (txtDistance == null) {
			txtDistance = new JTextField();
			txtDistance.setPreferredSize(new java.awt.Dimension(10, 20));
			txtDistance.setSize(new java.awt.Dimension(85, 20));
			txtDistance.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
			txtDistance.setLocation(new java.awt.Point(100, 30));
		}
		return txtDistance;
	}

	/**
	 * This method initializes txtConsumption
	 * 
	 * @return javax.swing.JTextField
	 */

	private JTextField getTxtConsumption() {
		if (txtConsumption == null) {
			txtConsumption = new JTextField();
			txtConsumption.setLocation(new java.awt.Point(100, 60));
			txtConsumption.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
			txtConsumption.setSize(new java.awt.Dimension(85, 20));
		}
		return txtConsumption;
	}

	/**
	 * This method initializes cboDistanceUnit
	 * 
	 * @return javax.swing.JComboBox
	 */

	private JComboBox getCboDistanceUnit() {
		if (cboDistanceUnit == null) {
			cboDistanceUnit = new JComboBox();
			cboDistanceUnit.setMaximumRowCount(2);
			cboDistanceUnit.setSize(new java.awt.Dimension(85, 20));
			cboDistanceUnit.setLocation(new java.awt.Point(200, 30));
		}
		return cboDistanceUnit;
	}

	/**
	 * This method initializes cboConsumptionUnit
	 * 
	 * @return javax.swing.JComboBox
	 */

	private JComboBox getCboConsumptionUnit() {
		if (cboConsumptionUnit == null) {
			cboConsumptionUnit = new JComboBox();
			cboConsumptionUnit.setLocation(new java.awt.Point(200, 60));
			cboConsumptionUnit.setSize(new java.awt.Dimension(85, 20));
		}
		return cboConsumptionUnit;
	}

	/**
	 * This method initializes bttnCalculate
	 * 
	 * @return javax.swing.JButton
	 */

	private JButton getBttnCalculate() {
		if (bttnCalculate == null) {
			bttnCalculate = new JButton();
			bttnCalculate.setBounds(new java.awt.Rectangle(114, 113, 88, 23));
			bttnCalculate
					.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
			bttnCalculate.setToolTipText("Calculates the consumption");
			bttnCalculate.setText("Calculate");

			bttnCalculate
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							controller.calculate(txtDistance.getText(),
									(String) cboDistanceUnit.getSelectedItem(),
									txtConsumption.getText(),
									(String) cboConsumptionUnit
											.getSelectedItem());
						}
					});
		}
		return bttnCalculate;
	}

	/**
	 * Calculates the consumption in MPG
	 * 
	 * @param distanceInKm
	 *            Distance in kilometers
	 * @param numberOfLiters
	 *            Number of liters used
	 * @return Consumption in MPG
	 */

	public double calculateMpgKmL(double distanceInKm, double numberOfLiters) {
		double distanceInMiles = distanceInKm * Constants.kmToMile;
		double numberOfGallons = numberOfLiters * Constants.lToGal;

		return distanceInMiles / numberOfGallons;

	}

	/**
	 * Calculates the consumption in MPG
	 * 
	 * @param distanceInKm
	 *            Distance in kilometers
	 * @param numberOfGallons
	 *            Number of US Gallons used
	 * @return Consumption in MPG
	 */

	public double calculateMpgKmGal(double distanceInKm, double numberOfGallons) {
		double distanceInMiles = distanceInKm * Constants.kmToMile;
		return distanceInMiles / numberOfGallons;
	}

  /**
   * Rewrites the type so that the information is correctly formatted for
   * SQL
   * @param value String
   * @param type String
   * @return String
   */

  protected String writeType(String value, String type) {
    if (type.matches("CHAR\\(\\d+\\).*")
        || type.matches("VARCHAR.*")
        || type.matches("BLOB.*")) {
      return "'" + value.trim().replaceAll("\'","\\'") + "'";
//      return "STRING";
    } else {
//      return type;
      return value.trim();
    }
  }

	/**
	 * Calculates the consumption in MPG
	 * 
	 * @param distanceInMiles
	 *            Distance in miles
	 * @param numberOfLiters
	 *            Number of liters used
	 * @return Consumption in MPG
	 */

	public double calculateMpgML(double distanceInMiles, double numberOfLiters) {
		double numberOfGallons = numberOfLiters * Constants.lToGal;

		return distanceInMiles / numberOfGallons;

	}

	/**
	 * Calculates the consumption in L100Km
	 * 
	 * @param distanceInKm
	 *            Distance in kilometers
	 * @param numberOfLiters
	 *            Number of liters used
	 * @return Consumption in L100Km
	 */

	public double calculateL100KmKmL(double distanceInKm, double numberOfLiters) {
		return (numberOfLiters * 100) / distanceInKm;
	}

	/**
	 *  Show this dialog
	 */

	public void show() {
		dialog.show();
	}

	/**
	 *  Sets the current attribute (progress value) of the ProgressDialog
	 *
	 * @param  aCurrent  The new current (progress) value
	 */

	public void setCurrent(int aCurrent) {
		current = aCurrent;
	}

	/**
	 *  Gets the current attribute (progress value) of the ProgressDialog
	 *
	 * @return    The current (progress) value
	 */

	public int getCurrent() {
		return current;
	}

	/**
	 * A non-polymorphic pack-and-set-visible returning the boolean result
	 * of the dialog.
	 *
	 * @return    User response, {@code true} is "yes".
	 */

	public boolean show() {
		dialog.pack();
		if (result) {
			mYes.getFocus();
		} else {
			mNo.getFocus();
		}

		dialog.show();
		return result;
	}

	/**
	 *  Instances the Listener for user input during progress
	 *
	 * @param  aDoCancel  The new doCancel value
	 */

	public void setDoCancel(ActionListener aDoCancel) {
		doCancel = aDoCancel;
	}

	/**
	 *  Sets the max (complete progress) of the ProgressDialog
	 *
	 * @param  aMax  The new max value
	 */

	public void setMax(int aMax) {
		max = aMax;
	}

	/**
	 *  Gets the max (complete progress) of the ProgressDialog
	 *
	 * @return    The max value
	 */

	public int getMax() {
		return max;
	}

	/**
	 *  Sets the message attribute of the ProgressDialog object
	 *
	 * @param  aMessage  The new message value
	 */

	public void setMessage(String aMessage) {
		message = aMessage;
		if (msgLabel != null) {
			msgLabel.setText(message);
			window.pack();
			//window.show();
		}
	}

	/**
	 *  Gets the message attribute of the ProgressDialog object
	 *
	 * @return    The message value
	 */

	public String getMessage() {
		return message;
	}

	/**
	 *  Sets the min (zero progress) of the ProgressDialog
	 *
	 * @param  aMin  The new min value
	 */

	public void setMin(int aMin) {
		min = aMin;
	}

	/**
	 *  Gets the min (zero progress)of the ProgressDialog
	 *
	 * @return    The min value
	 */

	public int getMin() {
		return min;
	}

	/**
	 *  Close the dialog
	 */

	public void close() {
		if (window != null) {
			window.close();
		}
	}

	/**
	 *  Sets the title attribute of the ProgressDialog
	 *
	 * @param  aTitle  The new title value
	 */

	public void setTitle(String aTitle) {
		title = aTitle;
	}

	/**
	 * The method sets the background color
	 *
	 * @param  background  value to be set
	 */

	public void setBackground(short background) {
		verifyColor(background);
		_background = background;
		//initChtype();
	}

	/**
	 * Sets the black-white mode attribute
	 *
	 * @param  blackWhiteAttribute  new black-white mode attribute
	 */

	public void setBlackWhiteAttribute(short blackWhiteAttribute) {
		_blackWhiteAttribute = blackWhiteAttribute;
	}

	/**
	 * Sets the color mode attribute
	 *
	 * @param  colorAttribute  new color mode attribute
	 */

	public void setColorAttribute(short colorAttribute) {
		_colorAttribute = colorAttribute;
	}

	/**
	 * The method sets the foreground color
	 *
	 * @param  foreground  value to be set
	 */

	public void setForeground(short foreground) {
		verifyColor(foreground);
		_foreground = foreground;
		//initChtype();
	}

	/**
	 * The method gets the foreground color
	 *
	 * @return    the foreground color
	 */

	public short getForeground() {
		return _foreground;
	}

	/**
	 *  Represent the character colors as a string
	 *
	 * @return    the character colors as a string
	 */

	public String toString() {
		if (Toolkit.hasColors()) {
			return "[background=" + getColorName(_background) + ", foreground=" + getColorName(_foreground) + "]";
		}

		return "[modi=" + getModusName(_blackWhiteAttribute) + "]";
	}

	/**
	 *  Gets the colorName attribute of the CharColor object
	 *
	 * @param  index  Description of the Parameter
	 * @return        The colorName value
	 */

	private String getColorName(short index) {
		switch (index) {
						case BLACK:
							return "BLACK";
						case WHITE:
							return "WHITE";
						case GREEN:
							return "GREEN";
						case YELLOW:
							return "YELLOW";
						case MAGENTA:
							return "MAGENTA";
						case CYAN:
							return "CYAN";
						case BLUE:
							return "BLUE";
						case RED:
							return "RED";
						default:
							return "UNKNOWN COLOR";
		}
	}

	/**
	 *  Generate a file filter from a string.
	 *
	 * @param  filterString  string showing acceptable file patterns
	 * @return               Filter object modelled on filterString
	 */

	public FileFilter generateFileFilter(String filterString) {
		return new DefaultFileFilter(filterString);
	}

	/**
	 *  Gets the modusName attribute of the CharColor object
	 *
	 * @param  index  Description of the Parameter
	 * @return        The modusName value
	 */

	private String getModusName(short index) {
		switch (index) {
						case NORMAL:
							return "NORMAL";
						case REVERSE:
							return "REVERSE";
						case BOLD:
							return "BOLD";
						default:
							return "UNKNOWN MODUS";
		}
	}

	/**
	 * Verify the color attribute as being one we support
	 *
	 * @param  attribute                  the color attribute
	 * @throws  IllegalArgumentException  on unknown color attribute
	 */

	private void verifyAttribute(short attribute) {
		if ((attribute != NORMAL) && (attribute != REVERSE) && (attribute != BOLD)) {
			throw new IllegalArgumentException("Unknown color attribute:" + attribute);
		}
	}

	/**
	 * Verify the color attribute as being one we support
	 *
	 * @param  color                      the color
	 * @throws  IllegalArgumentException  on unknown color
	 */

	private void verifyColor(short color) {
		if ((color != BLACK) && (color != RED) && (color != GREEN) && (color != YELLOW) && (color != BLUE) && (color != MAGENTA) && (color != CYAN)
				 && (color != WHITE)) {
			throw new IllegalArgumentException("Unknown color:" + color);
		}
	}

	/**
	 *  Sets the borderColors attribute of the Window object
	 *
	 * @param  aColors  The new borderColors value
	 */

	public void setBorderColors(CharColor aColors) {
		_theme.setColor(Theme.COLOR_WINDOW_BORDER, aColors);
		repaint();
	}

	/**
	 *  Gets the borderColors attribute of the Window object
	 *
	 * @return    The borderColors value
	 */

	public CharColor getBorderColors() {
		return _theme.getColor(Theme.COLOR_WINDOW_BORDER);
	}

	/**
	 * The method defines a new window's closing character. Default is escape.
	 *
	 * {@code null} means no closing character
	 *
	 * @param  character  new window's closing character - {@code null} means no closing character
	 */

	public void setClosingChar(InputChar character) {
		_closingChar = character;
	}

	/**
	 * The method returns the character which, when encountered in the default input handler
	 * causes JCurses to close this window.
	 *
	 * @return    window's closing character - {@code null} means no closing character
	 */

	public InputChar getClosingChar() {
		return _closingChar;
	}

	/**
	 *  Gets the defaultBorderColors attribute of the Window object
	 *
	 * @return    The defaultBorderColors value
	 */

	public CharColor getDefaultBorderColors() {
		return getTheme().getColor(Theme.COLOR_WINDOW_BORDER);
	}

	/**
	 *  Gets the defaultTitleColors attribute of the Window object
	 *
	 * @return    The defaultTitleColors value
	 */

	public CharColor getDefaultTitleColors() {
		return getTheme().getColor(Theme.COLOR_WINDOW_TITLE);
	}

	/**
	 * Sets the root panel of the window. This is the top most widget container in the window's widget hierarchy. It occupies the entire window out of the border
	 * (if exists ).
	 *
	 * @param  root  a Panel suitable to be a root panel.
	 */

	public void setRootPanel(Panel root) {
		_root = root;
		_root.setWindow(this);
		repaint();
	}

	/**
	 * Sets the text to use by painting separators
	 *
	 *
	 * @param  value  separator string
	 *
	 */

	public void setSeparatorString(String value) {
		_separatorString = value;
	}

	/**
	 * Returns the text used by painting separators
	 *
	 *
	 * @return    separator string
	 *
	 */

	public String getSeparatorString() {
		return _separatorString;
	}

	/**
	 * Adds a separator at the specified position
	 *
	 *
	 * @param  index  position to add a separator
	 *
	 */

	public void addSeparator(int index) {
		add(index, SEPARATOR);
	}

	/**
	 * Adds a separator at the end of the list
	 */

	public void addSeparator() {
		addSeparator(getItemsCount());
	}

	/**
	 *  Gets the itemRepresentation attribute of the MenuList object
	 *
	 * @param  item  Description of the Parameter
	 * @return       The itemRepresentation value
	 */

	protected String getItemRepresentation(String item) {
		if (item == SEPARATOR) {
			return getSeparatorString();
		}

		return item;
	}

	/**
	 *  Gets the preferredSize attribute of the MenuList object
	 *
	 * @return    The preferredSize value
	 */

	protected Rectangle getPreferredSize() {
		return new Rectangle(getMaxItemLength() + 2, getItemsCount() + 2);
	}

	/**
	 * Returns the  root panel of the window. This is the top most widget container in the window's widget hierarchy. It occupies the entire window out of the border
	 * (if exists ).
	 *
	 * @return    the root panel of the window
	 */

	public Panel getRootPanel() {
		//Ein kommentar
		return _root;
	}

	/**
	 *  Gets the selectable attribute of the MenuList object
	 *
	 * @param  index  Description of the Parameter
	 * @return        The selectable value
	 */

	protected boolean isSelectable(int index) {
		return (!(getItem(index) == SEPARATOR));
	}

	/**
	 *  Description of the Method
	 *
	 * @param  ch  Description of the Parameter
	 * @return     Description of the Return Value
	 */

	protected boolean handleInput(InputChar ch) {
		if (!ch.equals(getChangeStatusChar())) {
			return super.handleInput(ch);
		}

		return false;
	}

	/**
	 *  Gets the maxItemLength attribute of the MenuList object
	 *
	 * @return    The maxItemLength value
	 */

	private int getMaxItemLength() {
		int result = 0;

		for (int i = 0; i < getItemsCount(); i++) {
			int length = getItemRepresentation((getItem(i))).length();
			result = (length > result) ? length : result;
		}

		return result;
	}

	/**
	 * The method defines whether the window is to paint with a shadow.
	 *
	 * @param  value  true if a shadow is to paint, false otherwise
	 */

	public void setShadow(boolean value) {
		_hasShadow = value;
		repaint();
	}

	/**
	 *  Indicates whether the window is to paint with a shadow.
	 *
	 * @return    true if a shadow is to paint, false otherwise
	 */

	public boolean hasShadow() {
		return _hasShadow;
	}

	/**
	 *  Sets the titleColors attribute of the Window object
	 *
	 * @param  aColors  The new titleColors value
	 */

	public void setTitleColors(CharColor aColors) {
		_theme.setColor(Theme.COLOR_WINDOW_TITLE, aColors);
		repaint();
	}

	/**
	 *  Gets the titleColors attribute of the Window object
	 *
	 * @return    The titleColors value
	 */

	public CharColor getTitleColors() {
		return _theme.getColor(Theme.COLOR_WINDOW_TITLE);
	}

	/**
	 * The method changes the window's visibility status
	 *
	 * @param  aVisible  true, if the window becomes visible, false in other case
	 */

	public void setVisible(boolean aVisible) {
		if (aVisible != isVisible()) {
			if (aVisible) {
				show();
			} else {
				hide();
			}
		}
	}

	/**
	 * The method returns the window's visibility status
	 *
	 * @return    true, if the window becomes visible, false in other case
	 */

	public boolean isVisible() {
		return _visible;
	}

	/**
	 * The method adds a listener to the window
	 *
	 * @param  listener  the listener to add
	 */

	public void addListener(WindowListener listener) {
		_listenerManager.addListener(listener);
	}

	/**
	 * The method closed the window, that is removes it from window stack and
	 * eventually from the rendered display, if the window was visible.
	 */

	public void close() {
		hide();
		_closed = true;
		WindowManager.removeWindow(this);
	}

	/**
	 * The method hides the window
	 */

	public void hide() {
		_visible = false;
		WindowManager.doWindowVisibilityChange(this);
	}

	/**
	 *  Gets the borderColors attribute of the BorderPanel object
	 *
	 * @return    The borderColors value
	 */

	public CharColor getBorderColors() {
		return _colors;
	}

	/**
	 *  Sets the borderColors attribute of the BorderPanel object
	 *
	 * @param  colors  The new borderColors value
	 */

	public void setBorderColors(CharColor colors) {
		_colors = colors;
	}

	/**
	 *  Gets the defaultBorderColors attribute of the BorderPanel object
	 *
	 * @return    The defaultBorderColors value
	 */

	protected CharColor getDefaultBorderColors() {
		return __defaultBorderColors;
	}

	/**
	 *  Gets the clientArea attribute of the BorderPanel object
	 *
	 * @return    The clientArea value
	 */

	protected Rectangle getClientArea() {
		Rectangle rect = (Rectangle) getSize().clone();
		rect.setLocation(1, 1);
		rect.setWidth(rect.getWidth() - 2);
		rect.setHeight(rect.getHeight() - 2);

		return rect;
	}

	/**
	 * The method moves the window to the top of the stack
	 */

	public void moveToTheTop() {
		WindowManager.moveToTop(this);
	}

	/**
	 * The method computes new window's layout. The method must already be called, if anything on the window building is changed, for example, an widget is
	 * removed or isn't more focusable ( because not visible or other ).
	 */

	public void pack() {
		cutIfNeeded();
		configureRootPanel();
		_root.pack();
		loadFocusableChilds();
		loadShortcuts();
	}

	/**
	 * The method remove a listener from the window
	 *
	 * @param  listener  the listener to remove
	 */

	public void removeListener(WindowListener listener) {
		_listenerManager.removeListener(listener);
	}

	/**
	 * The method shows the window
	 */

	public void show() {
		if (!isVisible()) {
			WindowManager.createWindow(this);
			pack();
			_visible = true;
			WindowManager.doWindowVisibilityChange(this);
		}
	}

	/**
	 * Returns the rectangle occupied by the window.
	 *
	 * @return    the rectangle occupied by the window
	 */

	protected Rectangle getRectangle() {
		return _rect;
	}

	/**
	 * Accounts for shadow is any
	 *
	 * @return    the rectangle occupied by the window
	 */

	protected Rectangle getClipRectangle() {
		if (hasShadow()) {
			return new Rectangle(_rect.getX(), _rect.getY(), _rect.getWidth() + 1, _rect.getHeight() + 1);
		}

		return _rect;
	}

	/**
	 * The method is called, if the window gets focus.
	 */

	protected void activate() {
		_listenerManager.handleEvent(new WindowEvent(this, WindowEvent.ACTIVATED));
	}

	/**
	 *  Changes the focus between widgets and propagates the change notification.
	 *  Internal only, should not be called by application code.
	 *
	 * @param  aWidgetIndex  Index of widget to receive focus.
	 */

	protected void changeFocus(int aWidgetIndex) {
		if (aWidgetIndex != _currentIndex) {
			if (isFocusableIndex(_currentIndex)) {
				((Widget) _focusableChildren.get(_currentIndex)).setFocus(false);
			}

			_currentIndex = aWidgetIndex;

			if (isFocusableIndex(aWidgetIndex)) {
				((Widget) _focusableChildren.get(aWidgetIndex)).setFocus(true);
			}
		}
	}

	/**
	 *  Changes the focus between widgets and propagates the change notification.
	 *  Internal only, should not be called by application code. 
	 *
	 * @param  aWidget  the Widge itself to which the focus is to be changed.
	 */

	protected void changeFocus(Widget aWidget) {
		changeFocus(_focusableChildren.indexOf(aWidget));
	}

	/**
	 *  Changes the focus between widgets and propagates the change notification
	 *  merely by incrementing the index, wrapping to zero (0) if necessary.
	 *  Internal only, should not be called by application code. 
	 */

	protected void changeFocus() {
		//changeFocus(Math.min(Math.max(0, _currentIndex + 1), _focusableChildren.size() - 1));
		if (_currentIndex >= _focusableChildren.size() - 1 || _currentIndex < -1) {
			changeFocus(0);
		} else {
			changeFocus(_currentIndex + 1);
		}
	}

	/**
	 * The method is called, if the window is to be closed.
	 */

	protected void closed() {
		_closed = true;
		_listenerManager.handleEvent(new WindowEvent(this, WindowEvent.CLOSED));
	}

	/**
	 * The method is called, if the window loses focus.
	 */

	protected void deactivate() {
		_listenerManager.handleEvent(new WindowEvent(this, WindowEvent.DEACTIVATED));
	}

	/**
	 * The method paint's the window
	 */

	protected void paint() {
		drawThingsIfNeeded();
		_root.paint();
	}

	/**
	 *  Resize to specified size 
	 *
	 * @param  width   d'oh
	 * @param  height  d'oh
	 */

	protected void resize(int width, int height) {
		_rect.setWidth(width);
		_rect.setHeight(height);
	}

	/**
	 *  Gets the currentWidget attribute of the Window object
	 *
	 * @return    The currentWidget value
	 */

	private Widget getCurrentWidget() {
		if (isFocusableIndex(_currentIndex)) {
			return ((Widget) _focusableChildren.elementAt(_currentIndex));
		}

		return null;
	}

	/**
	 *  Gets the defaultClosingChar attribute of the Window object
	 *
	 * @return    The defaultClosingChar value
	 */

	private InputChar getDefaultClosingChar() {
		return __defaultClosingChar;
	}

	/**
	 *  Gets the defaultFocusChangeChar attribute of the Window object
	 *
	 * @return    The defaultFocusChangeChar value
	 */

	private InputChar getDefaultFocusChangeChar() {
		return __defaultFocusChangeChar;
	}

	/**
	 *  Gets the focusableIndex attribute of the Window object
	 *
	 * @param  aIdx  Description of the Parameter
	 * @return       The focusableIndex value
	 */

	private boolean isFocusableIndex(int aIdx) {
		return (aIdx >= 0) && (aIdx < _focusableChildren.size());
	}

	/**
	 *  Gets the text of the Label
	 *
	 * @return    The text value
	 */

	public String getText() {
		return _label;
	}

	/**
	 *  Sets the text  of the Label
	 *
	 * @param  aText  The new text value
	 */

	public void setText(String aText) {
		_label = aText;
		if (_label == null) {
			_label = "";
		}
	}

	/**
	 *  Calculates the preferred size of the Label.
	 *
	 * @return    The preferred size
	 */

	protected Rectangle getPreferredSize() {
		String[] mLines = TextUtils.wrapLines(_label, Integer.MAX_VALUE);

		int mWide = 0;
		for (int mIdx = 0; mIdx < mLines.length; mIdx++) {
			mWide = Math.max(mWide, mLines[mIdx].length());
		}

		return new Rectangle(mWide, mLines.length);
	}

	/**
	 *  The interface method that draws the label in its rectangle in its colors.
	 */

	protected void doPaint() {
		Toolkit.printString(_label, getRectangle(), getColors());
	}

    /**
     * Sets button's label
     *
     * @param  aLabel  buttton's label
     */ 

    public void setLabel(String aLabel) {
        _label = aLabel;
    }

	/**
	 * Input handler to identify shortcuts <br>
	 * There are four important cases:
	 * <ol>
	 * <li>Window close key was entered</li>
	 * <li>Shift focus to next widget key was entered</li>
	 * <li>Some other defined shortcut key was entered</li>
	 * <li>Handling input from a child that has the focus</li>
	 * </ol>
	 * Behandlung der Eingabe. <br>
	 * Vier m?gliche F?lle: <br>
	 * 1. Fenster schliessen. <br>
	 * 2. Zum n?chsten Widget springen. <br>
	 * 3. Shortcut bearbeiten. <br>
	 * 3. Eingabe vom aktuell Fokus habenden Kind bearbeiten lassen.
	 *
	 * @param  inp  object instance representing the input char
	 * @return      true if this char is to be handled as a shortcut
	 */

	private boolean isShortCut(InputChar inp) {
		return (_shortCutsList.indexOf(inp) != -1);
	}

    /**
     * Sets button's shortcut char's colors. If the button has a shortcut char
     * and this char is contained by the label, than the char within the label will be
     * painted in different colors, set by this method
     *
     * @param  aColor  button's shortcut char's colors
     */ 

    public void setShortCutColors(CharColor aColor) {
        setColors(Theme.COLOR_WIDGET_SHORTCUT, aColor);
    }

    /**
     * Adds a listener to the button.
     *
     * @param  listener  listener to add
     */ 

    public void addListener(ActionListener listener) {
        _listenerManager.addListener(listener);
    }

	/**
	 *  Finds a widget from its associated shortcut char
	 *
	 * @param  inp  object instance representing the input char.
	 * @return      The widget indexed in the shortcuts by the input char
	 */

	private Widget getWidgetByShortCut(InputChar inp) {
		return (Widget) _shortCutsTable.get(inp);
	}

    /**
     * Removes a listener from the button.
     *
     * @param  listener  listener to remove
     */ 

    public void removeListener(ActionListener listener) {
        _listenerManager.removeListener(listener);
    }

    /**
     * Set's button's shortcut char. If this shortcut is typed, then the button
     * will handle the char, as described by <code>Widget</code>, and generate an
     * Event as if the button were 'clicked'.
     *
     * @param  c  The new shortCut value
     */ 

    public void setShortCut(char c) {
        _shortCut = new InputChar(c);
    }

    /**
     *  Gets the preferredSize attribute of the Button object
     *
     * @return    The preferredSize value
     */ 

    protected Rectangle getPreferredSize() {
        return new Rectangle(_label.length() + 4, 1);
    }

    /**
     *  Gets the shortCutsList attribute of the Button object
     *
     * @return    The shortCutsList value
     */ 

    protected Vector getShortCutsList() {
        if (getShortCut() == null) {
            return null;
        }
        Vector result = new Vector();
        result.add(getShortCut());
        return result;
    }

	/**
	 *  Create (if necessary) a root panel and do some rectangle math on
	 * the root panel so it fits
	 */

	private void configureRootPanel() {
		if (_root == null) {
			_root = new Panel();
		}

		int x = _rect.getX();
		int y = _rect.getY();
		int width = _rect.getWidth();
		int height = _rect.getHeight();

		if (_border) {
			x++;
			y++;
			width -= 2;
			height -= 2;
		}

		_root.setSize(new Rectangle(width, height));
		_root.setX(x);
		_root.setY(y);
	}

    /**
     *  Gets the focusable attribute of the Button object
     *
     * @return    The focusable value
     */ 

    protected boolean isFocusable() {
        return true;
    }

    /**
     *  Input handler,just looks for the shortcut char
     * and performs the action if the shortcut char is encountered.
     *
     * @param  ch  Description of the Parameter
     * @return     Description of the Return Value
     */ 

    protected boolean handleInput(InputChar ch) {
        if ((ch.equals(__actionChar)) || ((getShortCut() != null) && (getShortCut().equals(ch)))) {
            doAction();
            return true;
        }

        return false;
    }

    /**
     *  Handle getting focus, paint self.
     */ 

    protected void focus() {
        doPaint();
    }

    /**
     *  Description of the Method
     */ 

    protected void unfocus() {
        doPaint();
    }

    /**
     *  Gets the shortCut character of the Button object
     *
     * @return    The shortCut value
     */ 

    private InputChar getShortCut() {
        return _shortCut;
    }

    /**
     *  Description of the Method
     */ 

    private void drawShortCutIfNeeded() {
        InputChar shortCut = getShortCut();
        if (shortCut != null) {
            String c = shortCut.toString();
            if (_label != null) {
                int index = _label.toLowerCase().indexOf(c.toLowerCase());
                if (index != - 1) {
                    Toolkit.printString(_label.substring(index, index + 1), getAbsoluteX() + index + 2, getAbsoluteY(), getShortCutColors());

                }
            }
        }
    }

    /**
     *  Description of the Method
     */ 

    private void doAction() {
        _listenerManager.handleEvent(new ActionEvent(this));
    }

	/**
	 * Draw features like border and title if needed
	 */

	private void drawThingsIfNeeded() {
		if (_border) {
			Toolkit.drawBorder(_rect, getBorderColors());
		}

		paintTitle();
	}

	/**
	 *  Load and show in order (i.e., changing focus) each of the window's focusable children.
	 */

	private void loadFocusableChilds() {
		_focusableChildren = _root.getListOfFocusables();
		if (!isFocusableIndex(_currentIndex)) {
			changeFocus();
		}
	}

	/**
	 *  Load the shortcut table
	 */

	private void loadShortcuts() {
		_shortCutsList.clear();
		_shortCutsTable.clear();

		Vector list = _root.getListOfWidgetsWithShortCuts();

		for (int i = 0; i < list.size(); i++) {
			Widget widget = (Widget) list.elementAt(i);
			Vector shortCuts = widget.getShortCutsList();
			_shortCutsList.addAll(shortCuts);

			for (int j = 0; j < shortCuts.size(); j++) {
				_shortCutsTable.put(shortCuts.elementAt(j), widget);
			}
		}
	}

	/**
	 *  Paint the title
	 */

	private void paintTitle() {
		if (_title != null) {
			Toolkit.printString(_title, _rect.getX() + ((_rect.getWidth() - _title.length()) / 2), _rect.getY(), getTitleColors());
		}
	}

	/**
	 * Set the Window title
	 * @param  aTitle  The title to set.
	 */

	public void setTitle(String aTitle) {
		_title = aTitle;
		repaint();
	}

	/**
	 *  Description of the Method
	 *
	 * @param  obj1  Description of the Parameter
	 * @param  obj2  Description of the Parameter
	 * @return       Description of the Return Value
	 */

	public int compare(Object obj1, Object obj2) {
		if (obj1 instanceof Widget && obj2 instanceof Widget) {
			Widget widget1 = (Widget) obj1;
			Widget widget2 = (Widget) obj2;

			if (isBelow(widget1, widget2)) {
				return 1;
			}

			if (isAbove(widget1, widget2) || isLeft(widget1, widget2)) {
				return -1;
			}

			if (isRight(widget1, widget2)) {
				return 1;
			}

			return 0;
		}

		return obj2.hashCode() - obj1.hashCode();
	}

   /**
	* The method returns the page offset for the given index
	* 
	* @param index the index to calculate the page offset
	* @return start index
	* 
	*/

   public int getPageOffset(int index) {
	   return index-getPageStartIndex(getPageNumber(index));
   }

    /**
     * See section 7.10.3 of the EJB 2.0 specification
     * See section 7.11.3 of the EJB 2.1 specification
     */

    public void ejbCreate() {
        objectkeyHome = lookupObjectkeyBean();
    }

    /**
     * See section 7.10.3 of the EJB 2.0 specification
     * See section 7.11.3 of the EJB 2.1 specification
     */

    public void ejbCreate() {
        objectKeyFacade = lookupObjectkeyFacadeBean();
        projectHome = lookupProjectBean();
        taskHome = lookupTaskBean();
        usercommentsHome = lookupUsercommentsBean();
    }

    /**
     * See section 7.10.3 of the EJB 2.0 specification
     * See section 7.11.3 of the EJB 2.1 specification
     */

    public void ejbCreate() {
        objectKeyFacade = lookupObjectkeyFacadeBean();
        accountHome = lookupAccountBean();
    }

    /**
     * Switches the states
     */

    public void switchState() {
        if (state == CCW) {
            state = CW;
        } else {
            state = CCW;
        }
    }

    /**
     * Given a String containing the name of the state, it returns
     * the occupancy number calculated as L/(L+Kd) using the value of 
     * Kd that corresponds to the state.
     *  
     * @param state
     * @return occupancy number corresponding to that state
     */

    public double getOccupancy(String state) {
        double ligand = getLigandConcentration();
        return ligand / (getDissociationConstant(state) + ligand);
    }

    /**
     * Given a volume, returns the concentration in Mol
     * @param volume (in liters)
     * @return [C] = copynumber / (vol * avogadro)
     */

    public Concentration getConcentration(double volume) {
        return new Concentration(this.getMolecularType(),
            level / (volume * Molecule.AVOGADRO));
    }

    /**
     * Sets the random number seed for this model, and recreates a
     * uniform distribution with that seed.
     */

    public void setRngSeed(long seed) {
    }

    /**
     * Counts the number of rotations since last Gram-Schmidt. If the rotationsCount is above
     * rotationsCountThreshold, Gram-Schmidt is applied to the orientation.
     */

    private void applyGramSchmidtIfNecessary() {
        rotationsCount++;

        if (!this.isOrthogonal()) {
            // TODO: generate an error (RotationMatrixNotOrthogonalError). This should be done in RotationMatrix.
            System.out.println("WARNING: Orientation not orthogonal after " +
                rotationsCount + " rotations. " +
                "Consider decreasing the rotationsCountThreshold. (See tests in OrientationTest.java)");
        }

        if (rotationsCount >= rotationsCountThreshold) {
            this.gramSchmidt();
            rotationsCount = 0;
        }
    }

	/**
	 * Read receptors occupation and update stochsim corresponding dynamicValues
	 */

	public void updateReceptorsDynamicValues() {
		Collection c = cell.getReceptors().getDissociationConstants().keySet();
		Iterator i = c.iterator();
		while (i.hasNext()) {
			String s = (String) i.next();
			this.setDynamicValue(s, cell.getReceptors().getOccupancy(s));
		}
	}

    /**
     * Switches the states
     */

    public void switchState() {
        if (state == BUNDLED) {
            state = APART;
        } else {
            state = BUNDLED;
        }
    }

  /**
   * Sets the model associated with this controller.
   *
   * @param model the model associated with this BaseController
   */

  public void setModel(SimModel model) {
    this.model = model;
    if (model != null) {
      model.setup();
    }
  }

  /**
   * Returns the SimModel currently associated with this BaseController.
   */

  public SimModel getModel() {
    return this.model;
  }

  /**
   * Gets the display cell width. The display cell size is the size in pixels
   * of the cells in which agents, environments and so forth are drawn.
   *
   * @return the display cell size
   */

  public int getCellWidth() {
    return DisplayConstants.CELL_WIDTH;
  }

  /**
   * Sets the display cell width. The display cell size is the size in pixels
   * of the cells in which agents, environments and so forth are drawn. Values
   * between 5 and 50 work well depending on the size of the "space"
   * being displayed.
   *
   * @param cellSize the new display cell size
   * @see #getCellWidth()
   */

  public void setCellWidth(int cellSize) {
    if (runThread == null)
      DisplayConstants.CELL_WIDTH = cellSize;
  }

  /**
   * Gets the display cell height. The display cell size is the size in pixels
   * of the cells in which agents, environments and so forth are drawn.
   *
   * @return the display cell size
   */

  public int getCellHeight() {
    return DisplayConstants.CELL_HEIGHT;
  }

  /**
   * Sets the display cell height. The display cell size is the size in pixels
   * of the cells in which agents, environments and so forth are drawn. Values
   * between 5 and 50 work well depending on the size of the "space"
   * being displayed.
   *
   * @param cellSize the new display cell size
   * @see #getCellHeight()
   */

  public void setCellHeight(int cellSize) {
    if (runThread == null)
      DisplayConstants.CELL_HEIGHT = cellSize;
  }

  /**
   * Gets the display cell depth. The display cell size is the size in pixels
   * of the cells in which agents, environments and so forth are drawn.
   *
   * @return the display cell size
   */

  public int getCellDepth() {
    return DisplayConstants.CELL_DEPTH;
  }

  /**
   * Sets the display cell depth. The display cell size is the size in pixels
   * of the cells in which agents, environments and so forth are drawn. Values
   * between 5 and 50 work well depending on the size of the "space"
   * being displayed.
   *
   * @param cellSize the new display cell size
   * @see #getCellDepth()
   */

  public void setCellDepth(int cellSize) {
    if (runThread == null)
      DisplayConstants.CELL_DEPTH = cellSize;
  }

    /**
     * Tests the PCall.
     */

    @Test public void testPCall() {
        PCall call = new PCall() {
            public void runCode() {
                setName(CompSettings.getDateString());
            }

            public Engine engine() {
                return null; // EngineTypes.GRTE.getEngine();
            }

            public void finalCode() {
                log.info(this + " OK");
            }
        };
        call.execute();
        call.execute();
        call.execute();
        QueuedExecutor exec = (QueuedExecutor) call.getExecutor();
        try {
            Thread.sleep(1000);
            exec.shutdownNow();
            Thread.sleep(600);
        } catch (InterruptedException e) {
            log.error("", e);
        }
        // EngineTypes.GRTE.getEngine().shutdown();
    }

  /**
   * Sets the schedule to be run by the controller.
   *
   * @param schedule the schedule to be run by this BaseController.
   * @see Schedule
   */

  public void setSchedule(Schedule schedule) {
    this.schedule = schedule;
    setupSchedule();
  }

  /**
   * Returns the Schedule associated with BaseController
   */

  public Schedule getSchedule() {
    return schedule;
  }

  /**
   * Gets the current simulation time (tick count) - the current number of
   * execution cycles completed by main schedule.
   *
   * @return the current simulation time
   */

  public long getCurrentTime() {

    return (long) time; // + timeMod);
  }

  /**
   * Gets the current simulation time (tick count) - the current number of
   * execution cycles completed by main schedule.
   *
   * @return the current simulation time
   */

  public double getCurrentTimeDouble() {

    return time; // + timeMod;
  }

    /**
     * Tests the TS.
     */

    @Test public void testCalc() {

        CalcEngine parser = new CalcEngine();
        parser.putVariable("s", new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 });
        double[] res;
        try {

            parser.parseString("d=trend(100);");
            res = parser.getVariable("D");
            Assert.assertEquals(res[99], 100, 0);

            parser.parseString("d = s+2");
            res = parser.getVariable("D");
            Assert.assertEquals(res[3], 6, 0);

            parser.parseString("d = s|1;A= log  (d )");
            res = parser.getVariable("a");
            Assert.assertEquals(res[9], 0, 0);

        } catch (ParseException ex) {
            throw new RuntimeException(ex.toString());
        }

    }

  /**
   * Sets the random seed for the current model. The random number generator
   * will be reset each time the model is run.
   *
   * @param seed the new random seed
   * @see SimModelImpl#setRngSeed(long)
   */

  public void setRandomSeed(long seed) {
    model.setRngSeed(seed);
  }

    /**
     * Tests the TSTypes.
     */

    @Test public void testTypes() {
        Assert.assertEquals(TSTypes.valueOf("DETERMINISTIC"), TSTypes.DETERMINISTIC);
        Assert.assertEquals(TSTypes.valueOf("dETERMINISTIc"), TSTypes.DETERMINISTIC);
        Assert.assertEquals(TSTypes.valueOf("ENDOGENOUS"), TSTypes.ENDOGENOUS);
        Assert.assertEquals(TSTypes.valueOf("eNDOGEnOUS"), TSTypes.ENDOGENOUS);
        Assert.assertEquals(TSTypes.valueOf("EXOGENOUS"), TSTypes.EXOGENOUS);
        Assert.assertEquals(TSTypes.valueOf("EXOgENOUs"), TSTypes.EXOGENOUS);
    }

  /**
   * Gets the current random seed for the current model
   */

  public long getRandomSeed() {
    return model.getRngSeed();
  }

  /**
   * Starts the simulation. Fires a START_EVENT before the simulation
   * thread is actually started.
   */

  public void startSim() {
    // necessary to make pauseAt persist over the course of
    // several runs.
    setPauseAt(pauseAt);

    fireSimEvent(new SimEvent(this, SimEvent.START_EVENT));

    if (runThread == null) {
      if (executeBegin) beginModel();
      schedule = model.getSchedule();
      setupSchedule();
    }
    if (schedule == null) {
      SimUtilities.showMessage("No schedule to run");
      System.exit(0);
    } else if (!go) {
      go = true;
      pauseSim = false;
      doStep = false;
      if (runThread == null) {
        runThread = new Thread(simRun);
        runThread.start();
      }
    } else if (go) {
      pauseSim = false;
      doStep = false;
      notifyMonitor();
    }
  }

  /**
   * Notifies object monitor that controlls pausing.
   */

  protected void notifyMonitor() {
    synchronized (monitor) {
      monitor.notify();
    }
  }

  /**
   * Gets the parameters of the current loaded model.
   * @deprecated replaced by ParameterUtilities.#getModelProperties(SimModel)
   */

  public Hashtable getModelParameters() {
    Hashtable props = new Hashtable(23);
    if (model != null) {
      model.clearPropertyListeners();
      try {
        props = ParameterUtility.getModelProperties(model);
      } catch (Exception ex) {
        SimUtilities.showError("Error retrieving model properties", ex);
        System.exit(0);
      }
    }

    return props;
  }

  /**
   * Pauses the simulation
   */

  public void pauseSim() {
    pauseSim = true;
  }

  /**
   * Stops the simulation.
   */

  public void stopSim() {
    go = false;
    doStep = false;
    pauseSim = false;
    notifyMonitor();
  }

    /**
     * Tests the TS.
     */

    @Test public void testTSEquals() {

        double[] newObs = new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        TSDate date = new TSDate(1960.1, 4);
        TS ts1 = new TS(newObs, "test_series", date);
        TS ts2 = new TS(newObs, "test_series", date);

        Assert.assertEquals(ts1, ts2);
        Assert.assertEquals(ts2, ts1);

        Assert.assertEquals(ts1.hashCode(), ts2.hashCode());

    }

  /**
   * Is this a BatchController. Returns false.
   */

  public boolean isBatch() {
    return false;
  }

   /**
   * Adds a SimEventListener to listen for SimEvents broadcast from
   * this BaseController.
   *
   * @param l the SimEventListener to add
   * @see SimEventListener
   */

  public void addSimEventListener(SimEventListener l) {
    listenerList.add(l);
  }

  /**
   * Removes a SimEventListener from the list of listeners listening for
   * SimEvents broadcast from this BaseController.
   *
   * @param l the SimEventListener to remove.
   * @see SimEventListener
   */

  public void removeSimEventListener(SimEventListener l) {
    listenerList.remove(l);
  }

   /**
   * Fires a SimEvent to the registered listeners.
   *
   * @param evt the SimEvent to fire
   */

  public void fireSimEvent(SimEvent evt) {
    ArrayList copy;
    synchronized(listenerList) {
      copy = (ArrayList)listenerList.clone();
    }

    for (int i = 0, n = copy.size(); i < n; i++) {
      SimEventListener l = (SimEventListener)copy.get(i);
      l.simEventPerformed(evt);
    }
  }

  /**
   * Allows for the storage of objects to persist beyond the life of a
   * single run by objects that do not so persist.
   *
   * @param key a unique identifier for the object to store
   * @param val the actual object to store
   */

  public void putPersistentObj(Object key, Object val) {
    persistentObj.put(key, val);
  }

  /**
   * Gets a stored persistent (over the life of many runs) object.
   *
   * @param key the unique identifier for the persistent object
   */

  public Object getPersistentObj(Object key) {
    return persistentObj.get(key);
  }

    /**
     * Tests the JHelpContextMgr with XML parsing/writing.
     */

    @Test public void testJHelpContextMgr() {
        File file = new File("testdata/" + JHelpContextMgr.helpIDFile);
        JHelpContextMgr newMgr = null;

        ObjectReader constructor = new ObjectReader();

        try {
            newMgr = (JHelpContextMgr) constructor.read(new FileInputStream(
                    file));

        } catch (Throwable t) {
            log.error("Error in XML deserialization", t);
        }
        Assert.assertEquals(newMgr.getID("com.jstatcom.io.TSImportPanel"),
                "jstatcom.impdata");
        JHelpContextMgr.register(new JPanel());
    }

    /**
     * Decreases this table capacity by C0 (down to C0).
     * 
     * @throws IllegalStateException if <code>(_size >= _capacity - C0)</code>
     */

    private void decreaseCapacity() {
        if (_size >= _capacity - C0)
            throw new IllegalStateException();
        final int c = _capacity;
        _capacity -= C0;
        if (c < C1) {
            _elems1[(c >> R1)] = null;
            _elems2 = null;
            _elems3 = null;
        } else if (c < C2) {
            _elems2[(c >> R2)][(c >> R1) & M1] = null;
            _elems3 = null;
        } else {
            _elems3[(c >> R3)][(c >> R2) & M2][(c >> R1) & M1] = null;
        }
    }

    /**
     * Returns the hash code value for this list.  The hash code of a list
     * is defined to be the result of the following calculation:[code]
     *  h = 1;
     *  Iterator i = list.iterator();
     *  while (i.hasNext()) {
     *      Object obj = i.next();
     *      h = 31 * h +  this.getValueComparator().hashCodeOf(obj);
     *  }[/code]
     *
     * @return the hash code value for this list.
     */

    public int hashCode() {
        final FastComparator comp = this.getValueComparator();
        int h = 1;
        for (Node n = _head, end = _tail; (n = n._next) != end;) {
            h = 31 * h + comp.hashCodeOf(n._value);
        }
        return h;
    }

    /**
     * Acquires the lock.
     */

    public void lock() {
        Thread caller = Thread.currentThread();
        synchronized (this) {
            if (caller == _owner) {
                _count++;
            } else {
                try {
                    while (_owner != null) {
                        this.wait();
                    }
                    _owner = caller;
                    _count = 1;
                } catch (InterruptedException exception) {
                    return;
                }
            }
        }
    }

    /**
     * Acquires the lock only if it not held by another thread.
     *
     * @return <code>true</code> if the lock was free and was acquired by the
     *         current thread, or the lock was already held by the current
     *         thread; <code>false</code> otherwise.
     */

    public boolean tryLock() {
        synchronized (this) {
            if (_owner == null) {
                lock();
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Attempts to release this lock. The lock is actually released if at
     * least as many {@link #unlock} as {@link #lock} have been performed
     * on this {@link ReentrantLock} by the current thread.
     *
     * throws IllegalMonitorStateExeception if the current thread does not hold
     *        this lock.
     */

    public void unlock() {
        synchronized (this) {
            if (Thread.currentThread() == _owner) {
                if (--_count == 0) {
                    _owner = null;
                    this.notify();
                }
            } else {
                throw new IllegalMonitorStateException(
                        "Current thread does not hold this lock");
            }
        }
    }

    /**
     * Returns the thread owner of this {@link ReentrantLock}.
     *
     * @return the owner of this lock.
     */

    public Thread getOwner() {
        synchronized (this) {
            return _owner;
        }
    }

    /**
     * Returns the value comparator for this collection (default 
     * {@link FastComparator#DEFAULT}).
     *
     * @return the comparator to use for value equality (or ordering if 
     *        the collection is ordered)
     */

    public FastComparator getValueComparator() {
        return _valueComp;
    }

    /**
     * Appends the specified value to the end of this collection
     * (optional operation).
     * 
     * <p>Note: This default implementation always throws 
     *          <code>UnsupportedOperationException</code>.</p>
     * 
     * @param value the value to be appended to this collection.
     * @return <code>true</code> (as per the general contract of the
     *         <code>Collection.add</code> method).
     * @throws UnsupportedOperationException if not supported.
     */

    public boolean add( E value) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the first occurrence in this collection of the specified value
     * (optional operation).
     *
     * @param value the value to be removed from this collection.
     * @return <code>true</code> if this collection contained the specified
     *         value; <code>false</code> otherwise.
     * @throws UnsupportedOperationException if not supported.
     */

    public boolean remove(Object value) {
        final FastComparator valueComp = this.getValueComparator();
        for (Record r = head(), end = tail(); (r = r.getNext()) != end;) {
            if (valueComp.areEqual(value, valueOf(r))) {
                delete(r);
                return true;
            }
        }
        return false;
    }

    /**
     * Removes all of the values from this collection (optional operation).
     *
     * @throws UnsupportedOperationException if not supported.
     */

    public void clear() {
        // Removes last record until empty.
        for (Record head = head(), r = tail().getPrevious(); r != head; r = r
                .getPrevious()) {
            delete(r);
        }
    }

    /**
     * Indicates if this map supports concurrent operations without 
     * synchronization (default unshared).
     * 
     * @return <code>true</code> if this map is thread-safe; <code>false</code> 
     *         otherwise.
     */

    public boolean isShared() {
        return _isShared;
    }

    /**
     * Indicates if this collection contains the specified value.
     *
     * @param value the value whose presence in this collection 
     *        is to be tested.
     * @return <code>true</code> if this collection contains the specified
     *         value;<code>false</code> otherwise.
     */

    public boolean contains(Object value) {
        final FastComparator valueComp = this.getValueComparator();
        for (Record r = head(), end = tail(); (r = r.getNext()) != end;) {
            if (valueComp.areEqual(value, valueOf(r)))
                return true;
        }
        return false;
    }

    /**
     * Appends all of the values in the specified collection to the end of
     * this collection, in the order that they are returned by the specified
     * collection's iterator or the node order if the specified collection
     * is a {@link FastCollection}.
     *
     * @param c collection whose values are to be added to this collection.
     * @return <code>true</code> if this collection changed as a result of 
     *         the call; <code>false</code> otherwise.
     */

    public boolean addAll(Collection <? extends E> c) {
        if (c instanceof FastCollection)
            return addAll((FastCollection) c);
        boolean modified = false;
        Iterator <? extends E> itr = c.iterator();
        int pos = c.size();
        while (--pos >= 0) {
            if (add(itr.next())) {
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Returns the key comparator for this fast map.
     * 
     * @return the key comparator.
     */

    public FastComparator getKeyComparator() {
        return _keyComparator;
    }

    /**
     * Indicates if this collection contains all of the values of the
     * specified collection.
     *
     * @param  c collection to be checked for containment in this collection.
     * @return <code>true</code> if this collection contains all of the values
     *         of the specified collection; <code>false</code> otherwise.
     */

    public boolean containsAll(Collection <?> c) {
        if (c instanceof FastCollection)
            return containsAll((FastCollection) c);
        Iterator <?> itr = c.iterator();
        int pos = c.size();
        while (--pos >= 0) {
            if (!contains(itr.next())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the value comparator for this fast map.
     * 
     * @return the value comparator.
     */

    public FastComparator getValueComparator() {
        return _values.getValueComparator();
    }

    /**
     * Removes from this collection all the values that are contained in the
     * specified collection.
     *
     * @param c collection that defines which values will be removed from
     *          this collection.
     * @return <code>true</code> if this collection changed as a result of 
     *         the call; <code>false</code> otherwise.
     */

    public boolean removeAll(Collection <?> c) {
        boolean modified = false;
        // Iterates from the tail and removes the record if present in c. 
        for (Record head = head(), r = tail().getPrevious(), previous; r != head; r = previous) {
            previous = r.getPrevious(); // Saves previous.
            if (c.contains(valueOf(r))) {
                delete(r);
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Retains only the values in this collection that are contained in the
     * specified collection.
     *
     * @param c collection that defines which values this set will retain.
     * @return <code>true</code> if this collection changed as a result of 
     *         the call; <code>false</code> otherwise.
     */

    public boolean retainAll(Collection <?> c) {
        boolean modified = false;
        // Iterates from the tail and remove the record if not present in c. 
        for (Record head = head(), r = tail().getPrevious(), previous; r != head; r = previous) {
            previous = r.getPrevious(); // Saves previous.
            if (!c.contains(valueOf(r))) {
                delete(r);
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Compares the specified object with this map for equality.
     * Returns <code>true</code> if the given object is also a map and the two
     * maps represent the same mappings (regardless of collection iteration
     * order).
     * 
     * @param obj the object to be compared for equality with this map.
     * @return <code>true</code> if the specified object is equal to this map;
     *         <code>false</code> otherwise.
     */

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Map) {
            Map <?,?> that = (Map) obj;
            if (this.size() == that.size()) {
                final Set thatEntrySet = that.entrySet();
                for (Entry e = _head, end = _tail; (e = e._next) != end;) {
                    if (!thatEntrySet.contains(e)) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Returns the textual representation of this collection.
     * 
     * @return this collection textual representation.
     */

    public Text toText() {
        final Text sep = Text.valueOf(", ");
        Text text = Text.valueOf('[');
        for (Record r = head(), end = tail(); (r = r.getNext()) != end;) {
            text = text.concat(Text.valueOf(valueOf(r)));
            if (r.getNext() != end) {
                text = text.concat(sep);
            }
        }
        return text.concat(Text.valueOf(']'));
    }

    /**
     * Compares the specified object with this collection for equality.  Returns
     * <code>true</code> if and only both collection contains the same values
     * regardless of the order; unless this collection is a list instance 
     * in which case both collection must be list with the same order. 
     *
     * @param obj the object to be compared for equality with this collection.
     * @return <code>true</code> if the specified object is equal to this
     *         collection; <code>false</code> otherwise.
     */

    public boolean equals(Object obj) {
        if (this instanceof List)
            return equalsList(obj);
        return obj == this
                || (obj instanceof Collection
                        && ((Collection) obj).size() == size() && containsAll((Collection) obj));
    }

    /**
     * Returns the hash code value for this map.
     * 
     * @return the hash code value for this map.
     */

    public int hashCode() {
        int code = 0;
        for (Entry e = _head, end = _tail; (e = e._next) != end;) {
            code += e.hashCode();
        }
        return code;
    }

    /**
     * Returns the hash code for this collection (independent from the 
     * collection order; unless this collection is a list instance).
     *
     * @return the hash code for this collection.
     */

    public int hashCode() {
        if (this instanceof List)
            return hashCodeList();
        final FastComparator valueComp = this.getValueComparator();
        int hash = 0;
        for (Record r = head(), end = tail(); (r = r.getNext()) != end;) {
            hash += valueComp.hashCodeOf(valueOf(r));
        }
        return hash;
    }

    /**
     * Returns the textual representation of this map.
     * 
     * @return the textual representation of the entry set.
     */

    public Text toText() {
        return _entrySet.toText();
    }

    /**
     * Sets the default value for the specified key (typically done at 
     * initialization). 
     * 
     * @param key the key with which the specified value is to be associated.
     * @param defaultValue the default value to be associated with the 
     *        specified key.
     * @return the previous default value associated with specified key, or
     *         <code>null</code> if there was no mapping for key. A
     *         <code>null</code> return can also indicate that the map
     *         previously associated <code>null</code> with the specified key.
     * @throws NullPointerException if the key is <code>null</code>.
     */

    public  V putDefault( K key,  V defaultValue) {
        return ( V ) ((FastMap) _mapRef.getDefault()).put(key,
                defaultValue);
    }

    /**
     * Returns the number of key-value mappings in this map.
     * 
     * @return this map's size.
     */

    public int size() {
        return ((FastMap) _mapRef.get()).size();
    }

    /**
     * Indicates if this map contains no key-value mappings.
     * 
     * @return <code>true</code> if this map contains no key-value mappings;
     *         <code>false</code> otherwise.
     */

    public boolean isEmpty() {
        return ((FastMap) _mapRef.get()).isEmpty();
    }

    /**
     * Indicates if this map contains a mapping for the specified key.
     * 
     * @param key the key whose presence in this map is to be tested.
     * @return <code>true</code> if this map contains a mapping for the
     *         specified key; <code>false</code> otherwise.
     * @throws NullPointerException if the key is <code>null</code>.
     */

    public boolean containsKey(Object key) {
        return ((FastMap) _mapRef.get()).containsKey(key);
    }

    /**
     * Indicates if this map associates one or more keys to the 
     * specified value.
     * 
     * @param value the value whose presence in this map is to be tested.
     * @return <code>true</code> if this map maps one or more keys to the
     *         specified value.
     * @throws NullPointerException if the key is <code>null</code>.
     */

    public boolean containsValue(Object value) {
        return ((FastMap) _mapRef.get()).containsValue(value);
    }

    /**
     * Returns the value to which this map associates the specified key.
     * 
     * @param key the key whose associated value is to be returned.
     * @return the value to which this map maps the specified key, or
     *         <code>null</code> if there is no mapping for the key.
     * @throws NullPointerException if key is <code>null</code>.
     */

    public  V get(Object key) {
        return ( V ) ((FastMap) _mapRef.get()).get(key);
    }

    /**
     * Associates the specified value with the specified key in this map.
     * 
     * @param key the key with which the specified value is to be associated.
     * @param value the value to be associated with the specified key.
     * @return the previous value associated with specified key, or
     *         <code>null</code> if there was no mapping for key. A
     *         <code>null</code> return can also indicate that the map
     *         previously associated <code>null</code> with the specified key.
     * @throws NullPointerException if the key is <code>null</code>.
     */

    public  V put( K key,  V value) {
        return ( V ) localMap().put(key, value);
    }

    /**
     * Copies all of the mappings from the specified map to this map.
     * 
     * @param map the mappings to be stored in this map.
     * @throws NullPointerException the specified map is <code>null</code>,
     *         or the specified map contains <code>null</code> keys.
     */

    public void putAll(Map <? extends K, ? extends V> map) {
        localMap().putAll(map);
    }

    /**
     * Removes the mapping for this key from this map if present
     * (sets the local value to <code>null</code>).
     * 
     * @param key the key whose value is set to <code>null</code>
     * @return <code>put(key, null)</code>
     * @throws NullPointerException if the key is <code>null</code>.
     */

    public  V remove(Object key) {
        return put(( K )key, null);
    }

    /**
     * Removes all mappings from this map (sets the local values to
     * <code>null</code>).
     */

    public void clear() {
        FastMap localMap = localMap();
        for (FastMap.Entry e = localMap.head(), end = localMap.tail(); (e = (FastMap.Entry) e.getNext()) != end;) {
            e.setValue(null);
        }
    }

	/**
	 * Reads this struct from the specified input stream  
	 * (convenience method when using Stream I/O). For better performance,
	 * use of Block I/O (e.g. <code>java.nio.channels.*</code>) is recommended.
	 *
	 * @param in the input stream being read from.
	 * @return the number of bytes read (typically the {@link #size() size}
	 *         of this struct.
	 * @throws IOException if an I/O error occurs.
	 */

	public int read(InputStream in) throws IOException {
		ByteBuffer buffer = getByteBuffer();
		if (buffer.hasArray()) {
			int offset = buffer.arrayOffset() + getByteBufferPosition();
			return in.read(buffer.array(), offset, size());
		} else {
			synchronized (buffer) {
				if (_bytes == null) {
					_bytes = new byte[size()];
				}
				int bytesRead = in.read(_bytes);
				buffer.position(getByteBufferPosition());
				buffer.put(_bytes);
				return bytesRead;
			}
		}
	}

	/**
	 * Writes this struct to the specified output stream  
	 * (convenience method when using Stream I/O). For better performance,
	 * use of Block I/O (e.g. <code>java.nio.channels.*</code>) is recommended.
	 *
	 * @param out the output stream to write to.
	 * @throws IOException if an I/O error occurs.
	 */

	public void write(OutputStream out) throws IOException {
		ByteBuffer buffer = getByteBuffer();
		if (buffer.hasArray()) {
			int offset = buffer.arrayOffset() + getByteBufferPosition();
			out.write(buffer.array(), offset, size());
		} else {
			synchronized (buffer) {
				if (_bytes == null) {
					_bytes = new byte[size()];
				}
				buffer.position(getByteBufferPosition());
				buffer.get(_bytes);
				out.write(_bytes);
			}
		}
	}

	/**
	 * Returns the <code>String</code> representation of this struct
	 * in the form of its constituing bytes (hexadecimal). For example:[code]
	 *     public static class Student extends Struct {
	 *         Utf8String name  = new Utf8String(16);
	 *         Unsigned16 year  = new Unsigned16();
	 *         Float32    grade = new Float32();
	 *     }
	 *     Student student = new Student();
	 *     student.name.set("John Doe");
	 *     student.year.set(2003);
	 *     student.grade.set(12.5f);
	 *     System.out.println(student);
	 *
	 *     4A 6F 68 6E 20 44 6F 65 00 00 00 00 00 00 00 00
	 *     07 D3 00 00 41 48 00 00[/code]
	 *
	 * @return a hexadecimal representation of the bytes content for this 
	 *         struct.
	 */

	public String toString() {
		final int size = size();
		StringBuffer sb = new StringBuffer(size * 3);
		final ByteBuffer buffer = getByteBuffer();
		final int start = getByteBufferPosition();
		for (int i = 0; i < size; i++) {
			int b = buffer.get(start + i) & 0xFF;
			sb.append(HEXA[b >> 4]);
			sb.append(HEXA[b & 0xF]);
			sb.append(((i & 0xF) == 0xF) ? '\n' : ' ');
		}
		return sb.toString();
	}

	/**
	 * Indicates if this struct's members are mapped to the same location
	 * in memory (default <code>false</code>). This method is useful for
	 * applications extending {@link Struct} with new member types in order to 
	 * create unions from these new structs. For example:[code]
	 * public abstract class FortranStruct extends Struct {
	 *     public class FortranString extends Member {...}
     *     protected FortranString[] array(FortranString[] array, int stringLength) { ... }
	 * }
	 * public abstract class FortranUnion extends FortranStruct {
	 *     // Inherits new members and methods.
	 *     public final isUnion() {
	 *         return true;
	 *     }
	 * }[/code]
	 *
	 * @return <code>true</code> if this struct's members are mapped to 
	 *         to the same location in memory; <code>false</code> 
	 *         otherwise.
	 * @see Union
	 */

	public boolean isUnion() {
		return false;
	}

	/**
	 * Returns the byte order for this struct (configurable). 
	 * The byte order is inherited by inner structs. Sub-classes may change 
	 * the byte order by overriding this method. For example:[code]
	 * public class TopStruct extends Struct {
	 *     ... // Members initialization.
	 *     public ByteOrder byteOrder() {
	 *         // TopStruct and its inner structs use hardware byte order.
	 *         return ByteOrder.nativeOrder();
	 *    }
	 * }}[/code]</p></p>
	 *
	 * @return the byte order when reading/writing multibyte values
	 *         (default: network byte order, <code>BIG_ENDIAN</code>).
	 */

	public ByteOrder byteOrder() {
		return (_outer != null) ? _outer.byteOrder() : ByteOrder.BIG_ENDIAN;
	}

    /**
     * Adds a new entry for the specified key and value.
     * 
     * @param hash the hash of the key, generated with {@link #keyHash}.
     * @param key the entry's key.
     * @param value the entry's value.
     */

    private void addEntry(int hash,  K key,  V value) {
        // Updates size.
        if ((_size++ >> R0) >= _entries.length) { // Check if entry table too small. 
            increaseEntryTable();
        }

        if (_tail._next == null) {
            increaseCapacity();
        }
        final Entry newTail = _tail._next;
        // Setups entry parameters.
        _tail._key = key;
        _tail._value = value;
        _tail._keyHash = hash;
        _tail._table = _entries;

        // Connects to bucket.
        final int index = (hash >> R0) & (_entries.length - 1);
        Entry[] tmp = _entries[index];
        if (tmp == NULL_BLOCK) {
            newBlock(index);
            tmp = _entries[index];
        }
        Entry beside = tmp[hash & M0];
        _tail._beside = beside;
        tmp[hash & M0] = _tail;

        // Moves tail forward.
        _tail = newTail;
    }

	/**
	 * Indicates if this struct is packed (configurable).
	 * By default, {@link Member members} of a struct are aligned on the
	 * boundary corresponding to the member base type; padding is performed
	 * if necessary. This directive is inherited by inner structs.
	 * Sub-classes may change the packing directive by overriding this method.
	 * For example:[code]
	 * public class TopStruct extends Struct {
	 *     ... // Members initialization.
	 *     public boolean isPacked() {
	 *         // TopStruct and its inner structs are packed.
	 *         return true;
	 *     }
	 * }}[/code]
	 *
	 * @return <code>true</code> if alignment requirements are ignored.
	 *         <code>false</code> otherwise (default).
	 */

	public boolean isPacked() {
		return (_outer != null) ? _outer.isPacked() : false;
	}

    /**
     * Requires special handling during serialization process.
     *
     * @param  stream the object output stream.
     * @throws IOException if an I/O error occurs.
     */

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.writeInt(_size);
        stream.writeInt(_entries.length);

        stream.writeBoolean(_isShared);
        stream.writeObject(_keyComparator);
        stream.writeObject(_values.getValueComparator());

        for (Entry e = _head, end = _tail; (e = e._next) != end;) {
            stream.writeObject(e._key);
            stream.writeObject(e._value);
        }
    }

    /**
     * Tests the testJSCSArray.
     */

    @Test public void testJSCSArray2() {

        JSCSArray sArray = new JSCSArray("test");

        String[][] test = new String[5][5];
        test[2][3] = "1.345e-20";
        test[1][1] = "Double.NaN";
        test[2][4] = "Double.POSITIVE_INFINITY";
        test[1][3] = "-1233433";

        sArray = new JSCSArray("test", test);
        Assert.assertEquals(sArray.isEqual(sArray.copy()), true);
        Assert.assertEquals(UStringArray.compareStringArrays(sArray.stringArray(),
                test), true);

    }

        /**
         * Indicates if this entry is considered equals to the specified entry
         * (using default value and key equality comparator to ensure symetry).
         * 
         * @param that the object to test for equality.
         * @return <code>true<code> if both entry have equal keys and values.
         *         <code>false<code> otherwise.
         */

        public boolean equals(Object that) {
            if (that instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) that;
                return _key.equals(entry.getKey())
                        && ((_value != null) ? _value.equals(entry.getValue())
                                : (entry.getValue() == null));
            } else {
                return false;
            }
        }

        /**
         * Returns the hash code for this entry.
         * 
         * @return this entry hash code.
         */

        public int hashCode() {
            return _key.hashCode() ^ ((_value != null) ? _value.hashCode() : 0);
        }

    /**
     * Tests the testJSCSArray.
     */

    @Test public void testJSCSArray3() {

        String[][] test = new String[5][5];
        test[2][3] = "1.345e-20";
        test[1][1] = "Double.NaN";
        test[2][4] = "Double.POSITIVE_INFINITY";
        test[1][3] = "-1233433";
        JSCSArray sArray = new JSCSArray("test", test);

        Assert.assertEquals(sArray.stringAt(0, 0), null);
        Assert.assertEquals(sArray.stringAt(2, 3), "1.345e-20");

    }

    /**
     * Sets the byte buffer to use for writing until this writer is closed.
     *
     * @param  byteBuffer the destination byte buffer.
     * @return this UTF-8 writer.
     * @throws IllegalStateException if this writer is being reused and 
     *         it has not been {@link #close closed} or {@link #reset reset}.
     */

    public Utf8ByteBufferWriter setByteBuffer(ByteBuffer byteBuffer) {
        if (_byteBuffer != null)
            throw new IllegalStateException("Writer not closed or reset");
        _byteBuffer = byteBuffer;
        return this;
    }

    /**
     * Writes a single character. This method supports 16-bits
     * character surrogates.
     *
     * @param  c <code>char</code> the character to be written (possibly
     *        a surrogate).
     * @throws IOException if an I/O error occurs.
     */

    public void write(char c) throws IOException {
        if ((c < 0xd800) || (c > 0xdfff)) {
            write((int) c);
        } else if (c < 0xdc00) { // High surrogate.
            _highSurrogate = c;
        } else { // Low surrogate.
            int code = ((_highSurrogate - 0xd800) << 10) + (c - 0xdc00)
                    + 0x10000;
            write(code);
        }
    }

    /**
     * Writes a character given its 31-bits Unicode.
     *
     * @param  code the 31 bits Unicode of the character to be written.
     * @throws IOException if an I/O error occurs.
     */

    public void write(int code) throws IOException {
        if ((code & 0xffffff80) == 0) {
            _byteBuffer.put((byte) code);
        } else { // Writes more than one byte.
            write2(code);
        }
    }

    /**
     * Writes a portion of an array of characters.
     *
     * @param  cbuf the array of characters.
     * @param  off the offset from which to start writing characters.
     * @param  len the number of characters to write.
     * @throws IOException if an I/O error occurs.
     */

    public void write(char cbuf[], int off, int len) throws IOException {
        final int off_plus_len = off + len;
        for (int i = off; i < off_plus_len;) {
            char c = cbuf[i++];
            if (c < 0x80) {
                _byteBuffer.put((byte) c);
            } else {
                write(c);
            }
        }
    }

    /**
     * Writes a portion of a string.
     *
     * @param  str a String.
     * @param  off the offset from which to start writing characters.
     * @param  len the number of characters to write.
     * @throws IOException if an I/O error occurs
     */

    public void write(String str, int off, int len) throws IOException {
        final int off_plus_len = off + len;
        for (int i = off; i < off_plus_len;) {
            char c = str.charAt(i++);
            if (c < 0x80) {
                _byteBuffer.put((byte) c);
            } else {
                write(c);
            }
        }
    }

    /**
     * Writes the specified character sequence.
     *
     * @param  csq the character sequence.
     * @throws IOException if an I/O error occurs
     */

    public void write(CharSequence csq) throws IOException {
        final int length = csq.length();
        for (int i = 0; i < length;) {
            char c = csq.charAt(i++);
            if (c < 0x80) {
                _byteBuffer.put((byte) c);
            } else {
                write(c);
            }
        }
    }

    /**
     * Flushes the stream (this method has no effect, the data is 
     * always directly written to the <code>ByteBuffer</code>).
     *
     * @throws IOException if an I/O error occurs.
     */

    public void flush() throws IOException {
        if (_byteBuffer == null) {
            throw new IOException("Writer closed");
        }
    }

    /**
     * Sets the <code>ByteBuffer</code> to use for reading available bytes
     * from current buffer position.
     *
     * @param  byteBuffer the <code>ByteBuffer</code> source.
     * @return this UTF-8 reader.
     * @throws IllegalStateException if this reader is being reused and 
     *         it has not been {@link #close closed} or {@link #reset reset}.
     */

    public Utf8ByteBufferReader setByteBuffer(ByteBuffer byteBuffer) {
        if (_byteBuffer != null)
            throw new IllegalStateException("Reader not closed or reset");
        _byteBuffer = byteBuffer;
        return this;
    }

    /**
     * Indicates if this stream is ready to be read.
     *
     * @return <code>true</code> if the byte buffer has remaining bytes to 
     *         read; <code>false</code> otherwise.
     * @throws  IOException if an I/O error occurs.
     */

    public boolean ready() throws IOException {
        if (_byteBuffer != null) {
            return _byteBuffer.hasRemaining();
        } else {
            throw new IOException("Reader closed");
        }
    }

    /**
     * Reads a single character.  This method does not block, <code>-1</code>
     * is returned if the buffer's limit has been reached.
     *
     * @return the 31-bits Unicode of the character read, or -1 if there is 
     *         no more remaining bytes to be read.
     * @throws IOException if an I/O error occurs (e.g. incomplete 
     *         character sequence being read).
     */

    public int read() throws IOException {
        if (_byteBuffer != null) {
            if (_byteBuffer.hasRemaining()) {
                byte b = _byteBuffer.get();
                return (b >= 0) ? b : read2(b);
            } else {
                return -1;
            }
        } else {
            throw new IOException("Reader closed");
        }
    }

    /**
     * Reads characters into the specified appendable. This method does not 
     * block.
     *
     * <p> Note: Characters between U+10000 and U+10FFFF are represented
     *     by surrogate pairs (two <code>char</code>).</p>
     *
     * @param  dest the destination buffer.
     * @throws IOException if an I/O error occurs.
     */

    public void read(Appendable dest) throws IOException {
        if (_byteBuffer == null)
            throw new IOException("Reader closed");
        while (_byteBuffer.hasRemaining()) {
            byte b = _byteBuffer.get();
            if (b >= 0) {
                dest.append((char) b); // Most common case.
            } else {
                int code = read2(b);
                if (code < 0x10000) {
                    dest.append((char) code);
                } else if (code <= 0x10ffff) { // Surrogates.
                    dest.append((char) (((code - 0x10000) >> 10) + 0xd800));
                    dest.append((char) (((code - 0x10000) & 0x3ff) + 0xdc00));
                } else {
                    throw new CharConversionException("Cannot convert U+"
                            + Integer.toHexString(code)
                            + " to char (code greater than U+10FFFF)");
                }
            }
        }
    }

    /**
     * Sets the output stream to use for writing until this writer is closed.
     * For example:[code]
     *     Writer writer = new Utf8StreamWriter().setOutputStream(out);
     * [/code] is equivalent but writes faster than [code]
     *     Writer writer = new java.io.OutputStreamWriter(out, "UTF-8");
     * [/code]
     *
     * @param  out the output stream.
     * @return this UTF-8 writer.
     * @throws IllegalStateException if this writer is being reused and 
     *         it has not been {@link #close closed} or {@link #reset reset}.
     */

    public Utf8StreamWriter setOutputStream(OutputStream out) {
        if (_outputStream != null)
            throw new IllegalStateException("Writer not closed or reset");
        _outputStream = out;
        return this;
    }

    /**
     * Writes a character given its 31-bits Unicode.
     *
     * @param  code the 31 bits Unicode of the character to be written.
     * @throws IOException if an I/O error occurs.
     */

    public void write(int code) throws IOException {
        if ((code & 0xffffff80) == 0) {
            _bytes[_index] = (byte) code;
            if (++_index >= _bytes.length) {
                flushBuffer();
            }
        } else { // Writes more than one byte.
            write2(code);
        }
    }

    /**
     * Writes a portion of an array of characters.
     *
     * @param  cbuf the array of characters.
     * @param  off the offset from which to start writing characters.
     * @param  len the number of characters to write.
     * @throws IOException if an I/O error occurs.
     */

    public void write(char cbuf[], int off, int len) throws IOException {
        final int off_plus_len = off + len;
        for (int i = off; i < off_plus_len;) {
            char c = cbuf[i++];
            if (c < 0x80) {
                _bytes[_index] = (byte) c;
                if (++_index >= _bytes.length) {
                    flushBuffer();
                }
            } else {
                write(c);
            }
        }
    }

    /**
     * Writes a portion of a string.
     *
     * @param  str a String.
     * @param  off the offset from which to start writing characters.
     * @param  len the number of characters to write.
     * @throws IOException if an I/O error occurs
     */

    public void write(String str, int off, int len) throws IOException {
        final int off_plus_len = off + len;
        for (int i = off; i < off_plus_len;) {
            char c = str.charAt(i++);
            if (c < 0x80) {
                _bytes[_index] = (byte) c;
                if (++_index >= _bytes.length) {
                    flushBuffer();
                }
            } else {
                write(c);
            }
        }
    }

    /**
     * Writes the specified character sequence.
     *
     * @param  csq the character sequence.
     * @throws IOException if an I/O error occurs
     */

    public void write(CharSequence csq) throws IOException {
        final int length = csq.length();
        for (int i = 0; i < length;) {
            char c = csq.charAt(i++);
            if (c < 0x80) {
                _bytes[_index] = (byte) c;
                if (++_index >= _bytes.length) {
                    flushBuffer();
                }
            } else {
                write(c);
            }
        }
    }

    /**
     * Flushes the stream.  If the stream has saved any characters from the
     * various write() methods in a buffer, write them immediately to their
     * intended destination.  Then, if that destination is another character or
     * byte stream, flush it.  Thus one flush() invocation will flush all the
     * buffers in a chain of Writers and OutputStreams.
     *
     * @throws IOException if an I/O error occurs.
     */

    public void flush() throws IOException {
        flushBuffer();
        _outputStream.flush();
    }

    /**
     * Flushes the internal bytes buffer.
     *
     * @throws IOException if an I/O error occurs
     */

    private void flushBuffer() throws IOException {
        if (_outputStream == null)
            throw new IOException("Stream closed");
        _outputStream.write(_bytes, 0, _index);
        _index = 0;
    }

    /**
     * Sets the input stream to use for reading until this reader is closed.
     * For example:[code]
     *     Reader reader = new Utf8StreamReader().setInputStream(inStream);
     * [/code] is equivalent but reads twice as fast as [code]
     *     Reader reader = new java.io.InputStreamReader(inStream, "UTF-8");
     * [/code]
     *
     * @param  inStream the input stream.
     * @return this UTF-8 reader.
     * @throws IllegalStateException if this reader is being reused and 
     *         it has not been {@link #close closed} or {@link #reset reset}.
     */

    public Utf8StreamReader setInputStream(InputStream inStream) {
        if (_inputStream != null)
            throw new IllegalStateException("Reader not closed or reset");
        _inputStream = inStream;
        return this;
    }

    /**
     * Indicates if this stream is ready to be read.
     *
     * @return <code>true</code> if the next read() is guaranteed not to block
     *         for input; <code>false</code> otherwise.
     * @throws  IOException if an I/O error occurs.
     */

    public boolean ready() throws IOException {
        if (_inputStream == null)
            throw new IOException("Stream closed");
        return ((_end - _start) > 0) || (_inputStream.available() != 0);
    }

    /**
     * Reads a single character.  This method will block until a character is
     * available, an I/O error occurs or the end of the stream is reached.
     *
     * @return the 31-bits Unicode of the character read, or -1 if the end of
     *         the stream has been reached.
     * @throws IOException if an I/O error occurs.
     */

    public int read() throws IOException {
        byte b = _bytes[_start];
        return ((b >= 0) && (_start++ < _end)) ? b : read2();
    }

    /**
     * Sets this reference to the specified value only if 
     * <code>(value.compareTo(this.get()) &gt; 0)</code>.
     * 
     * @param value the minimum value for this reference.
     * @throws IllegalArgumentException if the specified value is not 
     *         {@link Comparable} or an {@link Integer} instance (J2ME).
     */

    public void setMinimum( T value) {
        synchronized (ID_TO_VALUE) {
            if (value instanceof Comparable) {
                Object prevValue = get();
                if (((Comparable) value).compareTo(prevValue) > 0) {
                    ID_TO_VALUE.put(_id, value);
                }
            } else if (value instanceof Integer) {
                Object prevValue = get();
                if (((Integer) value).intValue() > ((Integer) prevValue)
                        .intValue()) {
                    ID_TO_VALUE.put(_id, value);
                }
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    /**
     * Sets this reference to the specified value only if 
     * <code>(value.compareTo(this.get()) &lt; 0)</code>.
     * 
     * @param value the maximum value for this reference.
     * @throws IllegalArgumentException if the specified value is not 
     *         {@link Comparable} or an {@link Integer} instance (J2ME).
     */

    public void setMaximum( T value) {
        synchronized (ID_TO_VALUE) {
            if (value instanceof Comparable) {
                Object prevValue = get();
                if (((Comparable) value).compareTo(prevValue) < 0) {
                    ID_TO_VALUE.put(_id, value);
                }
            } else if (value instanceof Integer) {
                Object prevValue = get();
                if (((Integer) value).intValue() < ((Integer) prevValue)
                        .intValue()) {
                    ID_TO_VALUE.put(_id, value);
                }
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    /**
     * Returns the string representation of the current value of this 
     * reference.
     *
     * @return <code>String.valueOf(this.get())</code>
     */

    public String toString() {
        return String.valueOf(this.get());
    }

    /**
     * Indicates if the specified character is contained by this character set.
     * 
     * @param c the character to test.
     * @return <code>true</code> if this character set contains the specified
     *         character; <code>false</code> otherwise.
     */

    public boolean contains(char c) {
        final int i = c >> 6;
        return i < _mapping.length ? (_mapping[i] & (1L << (c & 63))) != 0
                : false;
    }

    /**
     * Returns the character set containing the characters from this 
     * character set plus the characters from the character set specified.
     * 
     * @param that the set containing the characters to be added.
     * @return <code>this + that</code>
     */

    public CharSet plus(CharSet that) {
        if (that._mapping.length > this._mapping.length)
            return that.plus(this);
        CharSet result = this.copy();
        for (int i = that._mapping.length; --i >= 0;) {
            result._mapping[i] |= that._mapping[i];
        }
        return result;
    }

    /**
     * Returns the character set containing the characters from this 
     * character minus the characters from the character set specified.
     * 
     * @param that the set containing the character to be removed.
     * @return <code>this - that</code>
     */

    public CharSet minus(CharSet that) {
        CharSet result = this.copy();
        for (int i = MathLib.min(this._mapping.length, that._mapping.length); --i >= 0;) {
            result._mapping[i] &= ~that._mapping[i];
        }
        return result;
    }

    /**
     * Returns the textual representation of this character set.
     *  
     * @return the textual representation.
     */

    public String toString() {
        TextBuilder tb = TextBuilder.newInstance();
        tb.append('{');
        int length = _mapping.length << 6;
        for (int i = 0; i < length; i++) {
            if (this.contains((char) i)) {
                if (tb.length() > 1) {
                    tb.append(',');
                    tb.append(' ');
                }
                tb.append('\'');
                tb.append((char) i);
                tb.append('\'');
            }
        }
        tb.append('}');
        return tb.toString();
    }

    /**
     * Returns a copy of this character set.
     *  
     * @return an independant copy.
     */

    private CharSet copy() {
        CharSet charSet = new CharSet(new long[this._mapping.length]);
        for (int i = _mapping.length; --i >= 0;) {
            charSet._mapping[i] = _mapping[i];
        }
        return charSet;
    }

    /**
     * Appends the specified text to this text builder. 
     * If the specified text is <code>null</code> this method 
     * is equivalent to <code>append("null")</code>. 
     *
     * @param text the text to append or <code>null</code>.
     * @return <code>this</code>
     */

    public TextBuilder append(Text text) {
        if (text == null)
            return append("null");
        final int length = text.length();
        for (int i = 0; i < length;) {
            append(text.charAt(i++));
        }
        return this;
    }

    /**
     * Returns the default textual representation of this realtime object.
     * 
     * @return the textual representation of this object.
     */

    public Text toText() {
        return Text.valueOf(getClass().getName()).concat(Text.valueOf('@'))
                .concat(Text.valueOf(System.identityHashCode(this), 16));
    }

    /**
     * Recycles this object and its internals only. This method should only be
     * called when it can be asserted that this object is not going to be 
     * referenced anymore. 
     * This method affects only local objects and has no effect on heap objects
     * or objects allocated outside of the current pool context. 
     * Unlike the {@link #move move} operations, recycling is limited to this
     * object and its internals and has no effect on external 
     * variable members ({@link javolution37.javolution.realtime.Realtime real-time} or not).
     */

    protected void recycle() {
        if (((_pool != null) && _pool.isLocal())) {
            _pool.recycle(this);
        }
    }

    /**
     * Clears this context and releases any associated resource. Dead threads
     * contexts are automatically cleared before finalization.
     */

    public void clear() {
        if (_inner != null)
            _inner.clear();
        _inner = null;
    }

    /**
     * Moves all objects belonging to this pool context to the heap.
     */

    public void clear() {
        super.clear();
        for (int i = ObjectFactory.Count; i > 0;) {
            ObjectPool pool = _pools[--i];
            if (pool != ObjectPool.NULL) {
                pool.clearAll();
            }
        }
        _inUsePoolsLength = 0;
    }

    /**
     * Returns a factory object possibly recycled or preallocated.
     * This method is equivalent to <code>object(PoolContext.current())</code>.
     * 
     * @return a recycled, pre-allocated or new factory object.
     */

    public  T object() {
        final PoolContext poolContext = Context.poolContext(Thread
                .currentThread());
        return (poolContext == null) ? create() : ( T ) poolContext
                .getLocalPool(_index).next();
    }

    /**
     * Cleans-up this factory's objects for future reuse. 
     * When overriden, this method is called on objects being recycled to 
     * dispose of system resources or to clear references to external
     * objects potentially on the heap (it allows these external objects to
     * be garbage collected immediately and therefore reduces the memory 
     * footprint).
     *
     * @param  obj the object product of this factory being recycled.
     * @throws UnsupportedOperationException if this factory does not 
     *         support object clean-up (default).
     */

    protected void cleanup( T obj) {
        throw new UnsupportedOperationException();
    }

    /**
     * Terminates all the concurrent threads associated to this concurrent 
     * context.
     */

    public void clear() {
        if (_threads != null) {
            for (int i=0; i < _threads.length; i++) {
                _threads[i].terminate();
            }
            _threads = null;
        }        
    }

    /**
     * Returns the original cause of the exception or error.
     *
     * @return the exception or error as it has been raised by the
     *         <code>java.lang.Runnable</code>.
     */

    public Throwable getCause() {
        return _cause;
    }

    /**
     * Removes all local settings for this context.
     */

    public void clear() {
        super.clear();
        _references.clear();
    }

    /**
     * Returns the text representation of the current value of this 
     * reference.
     *
     * @return <code>Text.valueOf(this.get())</code>
     */

    public Text toText() {
        return Text.valueOf(this.get());
    }

    /**
     * Overrides parent's <code>toString</code> method.
     */

    public String toString() {
        return "Concurrent-" + super.toString();
    }

    /**
     * Terminates this thread (called when holder context is disposed).
     */

    public void terminate() {
        synchronized (this) {
            _terminate = true;
            this.notify();
        }
    }

    /**
     * Sets the local value (referent) for this reference.
     *
     * @param value the new local value or <code>null</code> to inherit
     *        the outer value.
     */

    public void set( T value) {
        LocalContext ctx = Context.current().inheritedLocalContext;
        if (ctx != null) {
            FastMap references = ctx._references;
            references.put(this, value);
            _hasBeenLocallyOverriden = true;
            return;
        }
        // No local context, sets default value.
        _defaultValue = value;
    }

    /**
     * Returns the default value for this reference.
     *
     * @return the defaultValue.
     */

    public  T getDefault() {
        return _defaultValue;
    }

    /**
     * Returns the local (non-inherited) value for this reference.
     *
     * @return the local value or <code>null</code> if none (value to be 
     *         inherited or not set).
     */

    public  T getLocal() {
        LocalContext ctx = Context.current().inheritedLocalContext;
        return (ctx != null) ? ( T ) ctx._references.get(this)
                : _defaultValue;
    }

    /**
     * Sets the default value for this reference.
     *
     * @param  defaultValue the root value.
     */

    public void setDefault( T defaultValue) {
        _defaultValue = defaultValue;
    }

    /**
     * Allows an application to register a real-time content event handler.
     *
     * <p> If the application does not register a content handler, all
     *     content events reported by the SAX parser will be silently
     *     ignored.</p>
     *
     * <p> Applications may register a new or different handler in the
     *     middle of a parse, and the SAX parser must begin using the new
     *     handler immediately.</p>
     *
     * @param  handler the real-time content handler.
     * @throws NullPointerException if the handler argument is null.
     * @see    #getContentHandler
     */

    public void setContentHandler(ContentHandler handler) {
        if (handler != null) {
            _contentHandler = handler;
        } else {
            throw new NullPointerException();
        }
    }

	/** 
	 * Sets the xml document writer.
	 * 
	 * @param writer the document writer.
	 * @return <code>this</code>
	 */

	public WriterHandler setWriter(Writer writer) {
		_writer = writer;
		return this;
	}

    /**
     * Returns the current real-time content handler.
     *
     * @return the current real-time content handler, or <code>null</code>
     *         if none has been registered.
     * @see    #setContentHandler
     */

    public ContentHandler getContentHandler() {
        return (_contentHandler == DEFAULT_HANDLER) ? null : _contentHandler;
    }

	/** 
	 * Sets the indentation sequence (default none).
	 * 
	 * @param indent a character sequence containing spaces or a tabulation character.
	 */

	public void setIndent(CharSequence indent) {
		_indent = indent;
	}

	/** 
	 * Sets the prolog to write at the beginning of the xml document
	 * (default none).
	 * 
	 * @param prolog the character sequence to be written at the beginning 
	 *        of the document.
	 */

	public void setProlog(CharSequence prolog) {
		_prolog = prolog;
	}

    /**
     * Allows an application to register an error event handler.
     *
     * <p> If the application does not register an error handler, all
     *     error events reported by the SAX parser will be silently
     *     ignored; however, normal processing may not continue.  It is
     *     highly recommended that all SAX applications implement an
     *     error handler to avoid unexpected bugs.</p>
     *
     * <p> Applications may register a new or different handler in the
     *     middle of a parse, and the SAX parser must begin using the new
     *     handler immediately.</p>
     *
     * @param  handler the error handler.
     * @throws NullPointerException if the handler argument is null.
     * @see    #getErrorHandler
     */

    public void setErrorHandler(ErrorHandler handler) {
        if (handler != null) {
            _errorHandler = handler;
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Returns the current error handler.
     *
     * @return the current error handler, or <code>null</code> if none
     *         has been registered.
     * @see    #setErrorHandler
     */

    public ErrorHandler getErrorHandler() {
        return (_errorHandler == DEFAULT_HANDLER) ? null : _errorHandler;
    }

    /**
     * Parses an XML document from the specified input stream (UTF-8 encoding).
     *
     * @param in the input stream with UTF-8 encoding.
     * @throws org.xml.sax.SAXException any SAX exception, possibly
     *         wrapping another exception.
     * @throws IOException an IO exception from the parser,
     *         possibly from a byte stream or character stream
     *         supplied by the application.
     * @see    javolution37.javolution.io.Utf8StreamReader
     */

    public void parse(InputStream in) throws IOException, SAXException {
        _pullParser.setInput(in);
        parseAll();
    }

    /**
     * Parses an XML document from the specified <code>ByteBuffer</code>
     * (UTF-8 encoding).
     *
     * @param  byteBuffer the byte buffer with UTF-8 encoding.
     * @throws org.xml.sax.SAXException any SAX exception, possibly
     *         wrapping another exception.
     * @throws IOException an IO exception from the parser,
     *         possibly from a byte stream or character stream
     *         supplied by the application.
     * @see    javolution37.javolution.io.Utf8ByteBufferReader
     */

    public void parse(ByteBuffer byteBuffer) throws IOException, SAXException {
        _pullParser.setInput(byteBuffer);
        parseAll();
    }

    /**
     * Parses an XML document using the specified reader.
     *
     * @param  reader the document reader.
     * @throws SAXException any SAX exception, possibly wrapping another
     *         exception.
     * @throws IOException an IO exception from the parser, possibly from
     *         a byte stream or character stream supplied by the application.
     */

    public void parse(Reader reader) throws IOException, SAXException {
        _pullParser.setInput(reader);
        parseAll();
    }

    /**
     * Receives notification of a warning. The default behaviour is to take no
     * action.
     *
     * @param  e the warning information encapsulated in a SAX parse exception.
     * @throws org.xml.sax.SAXException any SAX exception.
     */

    public void warning (SAXParseException e) throws SAXException {}

    /**
     * Allows an application to register an entity resolver (ignored by this
     * parser).
     *
     * @param resolver the entity resolver.
     */

    public void setEntityResolver(EntityResolver resolver) {
        _entityResolver = resolver;
    }

    /**
     * Receives notification of recoverable parser error. The default behaviour
     * is to take no action.
     *
     * @param  e the error information encapsulated in a SAX parse exception.
     * @throws org.xml.sax.SAXException any SAX exception.
     */

    public void error (SAXParseException e) throws SAXException {}

    /**
     * Reports a fatal XML parsing error. The default behaviour is to throw
     * the specified exception.
     *
     * @param  e the error information encapsulated in a SAX parse exception.
     * @throws org.xml.sax.SAXException any SAX exception.
     */

    public void fatalError (SAXParseException e) throws SAXException {
	throw e;
    }

    /**
     * Returns the current entity resolver.
     *
     * @return the current entity resolver, or <code>null</code> if none
     *         has been registered.
     * @see    #setEntityResolver
     */

    public EntityResolver getEntityResolver() {
        return _entityResolver;
    }

    /**
     * Allows an application to register a DTD handler (ignored by this parser).
     *
     * @param handler the DTD handler.
     */

    public void setDTDHandler(DTDHandler handler) {
        _dtdHandler = handler;
    }

    /**
     * Returns the current DTD handler.
     *
     * @return the current DTD handler, or <code>null</code> if none
     *         has been registered.
     * @see    #setDTDHandler
     */

    public DTDHandler getDTDHandler() {
        return _dtdHandler;
    }

    /**
     * Clear the attribute list for reuse.
     */

    public void reset() {
        _length = 0;
    }

    /**
     * Adds a new attribute whose values is to be hold by the specified 
     * {@link TextBuilder}.
     * 
     * @param qName the qualified (prefixed) name.
     * @return an empty text builder to hold the attribute value.
     */

    public TextBuilder newAttribute(String qName) {
        addAttribute(qName, null);
        TextBuilder tb = _textBuilders[_length - 1];
        _values[_length - 1] = tb;
        tb.reset();
        return tb;
    }

    /**
     * Removes the attribute at the specified index.
     * 
     * @param index 
     * @param value the attribute value.
     */

    public void remove(int index) {
        _qNames[index] = _qNames[--_length];
        _values[index] = _values[_length];
        TextBuilder tmp = _textBuilders[_length];
        _textBuilders[_length] = _textBuilders[index];
        _textBuilders[index] = tmp;
    }

    /**
     * Converts a String to a CharSequence (for J2ME compatibility)
     * 
     * @param str the String to convert.
     * @return the corresponding CharSequence instance.
     */

    private CharSequence toCharSeq(Object str) {
        if (str instanceof CharSequence)
            return (CharSequence) str;
        return Text.valueOf((String) str);
    }

    /**
     * Returns the string representation of these attributes.
     * 
     * @return this attributes textual representation.
     */

    public String toString() {
        Text text = Text.valueOf('[');
        final Text equ = Text.valueOf('=');
        final Text sep = Text.valueOf(", ");
        for (int i = 0; i < _length;) {
            text = text.concat(Text.valueOf(_qNames[i]).concat(equ).concat(
                    Text.valueOf(_values[i])));
            if (++i != _length) {
                text = text.concat(sep);
            }
        }
        return text.concat(Text.valueOf(']')).toString();
    }

    /**
     * Sets the document namespaces for this writer.
     *
     * @param  prefix the namespace prefix or an empty sequence to set 
     *         the default namespace.
     * @param  uri the namespace uri.
     * @throws IllegalArgumentException if the prefix is "j" (reserved for 
     *         the "http://javolution.org" uri).
     */

    public void setNamespace(String prefix, String uri) {
        if ((prefix.length() == 1) && (prefix.charAt(0) == 'j'))
            throw new IllegalArgumentException("Prefix: \"j\" is reserved.");        
        _namespaces.addLast(toCharSeq(prefix));
        _namespaces.addLast(toCharSeq(uri));
        if (prefix.length() == 0) { // Default namespace mapped
            // Use javolution uri for all classes without namespace
            // (default namespace cannot be used anymore).
            _xml._packagePrefixes.addLast("j");
            _xml._packagePrefixes.addLast("");
        }
    }

    /**
     * Maps a namespace to a Java package. The specified prefix is used to 
     * shorten the class name of the object being serialized.
     *
     * @param  prefix the namespace prefix or empty sequence to set 
     *         the default namespace.
     * @param  packageName of the package associated to the specified prefix.
     * @throws IllegalArgumentException if the prefix is "j" (reserved for 
     *         the "http://javolution.org" uri).
     */

    public void setPackagePrefix(String prefix, String packageName) {
        setNamespace(prefix, "java:" + packageName);
        _xml._packagePrefixes.addLast(prefix);
        _xml._packagePrefixes.addLast(packageName);
    }

    /**
     * Writes the specified object to the given writer in XML format.
     * The writer is closed after serialization. To serialize multiple 
     * objects over a persistent connection {@link XmlOutputStream}
     * should be used instead.
     *
     * @param  obj the object to format.
     * @param  writer the writer to write to.
     * @throws IOException if there's any problem writing.
     */

    public void write( T  obj, Writer writer) throws IOException {
        try {
            _writerHandler.setWriter(writer);
            write(obj, _writerHandler);
        } catch (SAXException e) {
            if (e.getException() instanceof IOException) {
                throw (IOException) e.getException();
            }
        } finally {
            _writerHandler.reset();
        }
    }

    /**
     * Writes the specified object to the given output stream in XML format. 
     * The characters are written using UTF-8 encoding. 
     * The output streamwriter is closed after serialization. To serialize 
     * multiple objects over a persistent connection {@link XmlOutputStream}
     * should be used instead.
     *
     * @param  obj the object to format.
     * @param  out the output stream to write to.
     * @throws IOException if there's any problem writing.
     */

    public void write( T  obj, OutputStream out) throws IOException {
        try {
            _utf8StreamWriter.setOutputStream(out);
            _writerHandler.setWriter(_utf8StreamWriter);
            write(obj, _writerHandler);
        } catch (SAXException e) {
            if (e.getException() instanceof IOException) {
                throw (IOException) e.getException();
            }
        } finally {
            _utf8StreamWriter.reset();
            _writerHandler.reset();
        }
    }

    /**
     * Resets all internal data maintained by this writer including any 
     * namespace associations; objects previously written will not be
     * referred to, they will be send again.
     */

    public void reset() {
        _xml.reset();
        _namespaces.clear();
        _xml._packagePrefixes.clear();
        _areReferencesEnabled = false;
        _expandReferences = false;
        _isClassIdentifierEnabled = true;
    }

    /**
     * Enables/disables xml elements cross references (default 
     * <code>false</code>).
     * When enabled, identifiers attributes are added during serialization; 
     * the name of these attributes is defined by {@link XmlFormat#identifier}.
     * 
     * @param enabled <code>true</code> if an unique identifier attribute is
     *        added to objects being serialized; <code>false</code> otherwise.
     */

    public void setReferencesEnabled(boolean enabled) {
        _areReferencesEnabled = enabled;
    }

    /**
     * Controls whether or not references are expanced (default 
     * <code>false</code>). References are not expanded if currently 
     * being expanded (to avoid infinite recursion).
     * 
     * @param value <code>true</code> to expand references;
     *        <code>false</code> otherwise.
     * @see   XmlFormat#identifier 
     */

    public void setExpandReferences(boolean value) {
        _expandReferences  = value;
    }

    /**
     * Enables/disables class identifier attributes (default <code>true<code>).
     * Disabling the class identifier should only be done if the serialized
     * objects does not need to be deserialized (e.g. pure xml formatting).
     * 
     * @param enabled <code>true</code> to allow for an additional "j:class"
     *        attribute; <code>false</code> otherwise. 
     * @see   XmlElement#add(Object)
     * @see   XmlElement#add(Object, String)
     */

    public void setClassIdentifierEnabled(boolean enabled) {
        _isClassIdentifierEnabled = enabled;
    }

    /**
     * Sets the element name or the root object.
     * 
     * @param name the name of the root element.
     */

    public void setRootName(String name) {
        _rootName = name;
    }

    /**
     * Creates an object from its XML representation read from
     * the specified <code>Reader</code>. This method reads until the  
     * end of stream; to read multiple objects over a persistent connection
     * {@link XmlInputStream} should be used instead.
     *
     * @param  reader the reader containing the XML representation of the
     *         object being created.
     * @return the object corresponding to the xml root element.
     * @throws XmlException if the object cannot be created.
     */

    public  T  read(Reader reader) throws XmlException {
        _xml._parser.setInput(reader);
        return ( T ) parse();
    }

    /**
     * Creates an object from its XML representation read from
     * the specified <code>InputStream</code>. This method reads until the  
     * end of stream; to read multiple objects over a persistent connection
     * {@link XmlInputStream} should be used instead.
     *
     * @param  in the input stream containing the XML representation of the
     *         object being created.
     * @return the object corresponding to the xml root element.
     * @throws XmlException if the object cannot be created.
     */

    public  T  read(InputStream in) throws XmlException {
        _xml._parser.setInput(in);
        return ( T ) parse();
    }

    /**
     * Creates an object from its XML representation read from
     * the specified <code>ByteBuffer</code>. This method reads from 
     * the current buffer position up to the buffer's limit.
     *
     * @param  byteBuffer the byte buffer containing the XML representation 
     *         of the object being created.
     * @return the object corresponding to the xml root element.
     * @throws XmlException if the object cannot be created.
     */

    public  T  read(ByteBuffer byteBuffer) throws XmlException {
        _xml._parser.setInput(byteBuffer);
        return ( T ) parse();
    }

    /**
     * Resets this object reader; objects previously read cannot be refered to,
     * they will have to be send again.
     */

    public void reset() {
        _xml.reset();
    }

    /**
     * Returns the length of this character sequence.
     *
     * @return the number of characters (16-bits Unicode) composing this
     *         character sequence.
     */

    public int length() {
        return length;
    }

    /**
     * Returns the character at the specified index.
     *
     * @param  index the index of the character starting at <code>0</code>.
     * @return the character at the specified index of this character sequence.
     * @throws IndexOutOfBoundsException  if <code>((index < 0) || 
     *         (index >= length))</code>
     */

    public char charAt(int index) {
        if ((index < 0) || (index >= length))
            throw new IndexOutOfBoundsException("index: " + index);
        return data[offset + index];
    }

    /**
     * Returns a new character sequence that is a subsequence of this sequence.
     *
     * @param  start the index of the first character inclusive.
     * @param  end the index of the last character exclusive.
     * @return the character sequence starting at the specified
     *         <code>start</code> position and ending just before the specified
     *         <code>end</code> position.
     * @throws IndexOutOfBoundsException if <code>(start < 0) || (end < 0) ||
     *         (start > end) || (end > this.length())</code>
     */

    public CharSequence subSequence(int start, int end) {
        if ((start < 0) || (end < 0) ||
                 (start > end) || (end > this.length())) 
            throw new IndexOutOfBoundsException();
        CharSequenceImpl chars = (CharSequenceImpl) FACTORY.object();
        chars.data = data;
        chars.offset = offset + start;
        chars.length = end - start;
        return chars;
    }

    /**
     * Returns the <code>String<code> corresponding to this character
     * sequence. The <code>String</code> returned is always allocated on the
     * heap and can safely be referenced elsewhere.
     *
     * @return the <code>java.lang.String</code> for this character sequence.
     */

    public String toString() {
        return new String(data, offset, length);
    }

    /**
     * Returns the hash code for this {@link CharSequenceImpl}.
     *
     * <p> Note: Returns the same hashCode as <code>java.lang.String</code>
     *           (consistent with {@link #equals})</p>
     *
     * @return the hash code value.
     */

    public int hashCode() {
        int h = 0;
        for (int i = 0, j = offset; i < length; i++) {
            h = 31 * h + data[j++];
        }
        return h;
    }

    /**
     * Compares this character sequence against the specified object
     * (<code>String</code> or <code>CharSequence</code>).
     *
     * @param  that the object to compare with.
     * @return <code>true</code> if both objects represent the same sequence;
     *         <code>false</code> otherwise.
     */

    public boolean equals(Object that) {
        if (that instanceof CharSequenceImpl) {
            return equals((CharSequenceImpl) that);
        } else if (that instanceof String) { // J2ME: String not a CharSequence.
            return equals((String) that);
        } else if (that instanceof CharSequence) {
            return equals((CharSequence) that);
        } else {
            return false;
        }
    }

    /**
     * Compares this character sequence against the specified
     * {@link CharSequenceImpl}.
     *
     * @param  that the character sequence to compare with.
     * @return <code>true</code> if both objects represent the same sequence;
     *         <code>false</code> otherwise.
     */

    public boolean equals(CharSequenceImpl that) {
        if (that == null)
            return false;
        if (this.length != that.length)
            return false;
        final char[] thatData = that.data;
        final int end = offset + length;
        for (int i = offset, j = that.offset; i < end;) {
            if (data[i++] != thatData[j++])
                return false;
        }
        return true;
    }

    /**
     * Compares this character sequence against the specified String.
     * 
     * @param  chars the character sequence to compare with.
     * @return <code>true</code> if both objects represent the same sequence;
     *         <code>false</code> otherwise.
     */

    public boolean equals(String str) {
        if (str == null)
            return false;
        if (length != str.length())
            return false;
        for (int i = 0, j = offset; i < length;) {
            if (data[j++] != str.charAt(i++))
                return false;
        }
        return true;
    }

    /**
     * Compares this character sequence against the specified character
     * sequence.
     * 
     * @param  chars the character sequence to compare with.
     * @return <code>true</code> if both objects represent the same sequence;
     *         <code>false</code> otherwise.
     */

    public boolean equals(CharSequence chars) {
        if (chars == null)
            return false;
        if (this.length != chars.length())
            return false;
        for (int i = 0, j = offset; i < length;) {
            if (data[j++] != chars.charAt(i++))
                return false;

        }
        return true;
    }

    /**
     * Returns the default namespace.
     */

    public CharSequenceImpl getDefault() {
        return _default;
    }

    /**
     * Returns the numbers of elements in the namespace stack for the given
     * depth.
     * 
     * @param depth the element depth.
     */

    public int getNamespaceCount(int depth) {
        if (depth > _depth)
            return _nspCounts[_depth];
        return _nspCounts[depth];
    }

    /**
     * Returns the namespace prefix at the specified position.
     * 
     * @param pos the position in the namespace stack.
     * @return the namespace prefix.
     */

    public CharSequenceImpl getNamespacePrefix(int pos) {
        return _namespaces[pos << 1];
    }

    /**
     * Returns the namespace uri at the specified position.
     * 
     * @param pos the position in the namespace stack.
     * @return the namespace uri.
     */

    public CharSequenceImpl getNamespaceUri(int pos) {
        return _namespaces[(pos << 1) + 1];
    }

    /**
     * Returns the namespace for the specified prefix or the default 
     * namespace is the prefix is <code>null</code>.
     * 
     * @param prefix the prefix to search for or <code>null</code>.
     * @return the associated namespace uri.
     */

    public CharSequenceImpl getNamespaceUri(String prefix) {
        if (prefix == null)
            return _default;
        for (int i = _nspCounts[_depth] + _mapCount; i > 0;) {
            CharSequenceImpl pfx = _namespaces[--i << 1];
            if ((pfx != null) && pfx.equals(prefix))
                return _namespaces[(i << 1) + 1];
        }
        if (XML_PREFIX.equals(prefix))
            return XML_URI;
        if (XMLNS_PREFIX.equals(prefix))
            return XMLNS_URI;
        return null;
    }

    /**
     * Adds the specified mapping to the current mapping buffer.
     *
     * @param  prefix the prefix to be mapped or <code>null</code> to 
     *         map the defaut namespace.
     * @param  uri the associated uri.
     * @throws SAXException any SAX exception, possibly wrapping another
     *         exception.
     */

    public void map(CharSequenceImpl prefix, CharSequenceImpl uri) {
        final int i = (_nspCounts[_depth] + _mapCount++) << 1;
        if (i + 1 >= _namespaces.length) resize();
        _namespaces[i] = prefix;
        _namespaces[i + 1] = uri;
        if (prefix == null) { // Maps default namespace.
            _default = uri;
        }
    }

    /**
     * Flushes the current mapping buffer (equivalent to push() then pop()).
     */

    public void flush() {
        if (_mapCount != 0) {
            push();
            pop();
        }
    }

    /**
     * Sets the byte buffer this parser is going to process
     * (UTF-8 encoding).
     *
     * @param  byteBuffer the byte buffer with UTF-8 encoding.
     * @see    Utf8ByteBufferReader
     */

    public void setInput(ByteBuffer byteBuffer) {
        if (_reader != null)
            throw new IllegalStateException("Parser not reset.");
        _byteBufferReader.setByteBuffer(byteBuffer);
        _inputEncoding = "UTF-8";
        setInput(_byteBufferReader);
    }

    /**
     * Pushes the current namespaces.
     */

    public void push() {
        if (++_depth >= _nspCounts.length) resize();
        _nspCounts[_depth] = _nspCounts[_depth - 1] + _mapCount;
        _mapCount = 0;
    }

    /**
     * Pops the current namespaces.
     */

    public void pop() {
        _mapCount = 0;
        final int oldCount = _nspCounts[_depth];
        final int newCount = _nspCounts[--_depth];
        for (int i = oldCount; i > newCount;) {
            if (_namespaces[--i << 1] == null) { // Unmaps default namespace.
                _default = CharSequenceImpl.EMPTY;
                for (int j = i; j > 0;) { // Searches current default.
                    if (_namespaces[--j << 1] == null) {
                        _default = _namespaces[(j << 1) + 1];
                        break;
                    }
                }
            }
        }
    }

    /**
     * Resizes internal arrays.
     */

    private void resize() {
        final int size = _nspCounts.length; // = _namepaces.length;
        int[] tmp0 = new int[size * 2];
        System.arraycopy(_nspCounts, 0, tmp0, 0, size);
        _nspCounts = tmp0;
        CharSequenceImpl[] tmp1 = new CharSequenceImpl[size * 2];
        System.arraycopy(_namespaces, 0, tmp1, 0, size);
        _namespaces = tmp1;
        SIZE.setMinimum(new Integer(_namespaces.length));
    }

    /**
     * Sets the input stream this parser is going to process
     * (UTF-8 encoding).
     *
     * @param in the input stream with UTF-8 encoding.
     * @see    Utf8StreamReader
     */

    public void setInput(InputStream in) {
        if (_reader != null)
            throw new IllegalStateException("Parser not reset.");
        _inputStreamReader.setInputStream(in);
        _inputEncoding = "UTF-8";
        setInput(_inputStreamReader);
    }

    /**
     * Sets the underlying input source for this stream.
     * 
     * @param in the input source.
     * @return <code>this</code> 
     * @throws IllegalStateException if this stream is being reused and 
     *         it has not been {@link #close closed} or {@link #reset reset}.
     */

    public XmlInputStream setInputStream(InputStream in) {
        if (_xmlReader._inputStream != null)
            throw new IllegalStateException("Stream not closed or reset");
        _xmlReader._inputStream = in;
        return this;
    }

    /**
     * Reads an object from the underlying stream using an {@link ObjectReader}.
     * 
     * @return the object read from its xml representation. 
     * @throws IOException if an I/O error occurs.
     */

    public  T  readObject() throws IOException {
        try {
            return _objectReader.read(_xmlReader);
        } finally {
           _xmlReader.resume();
        }
    }

    /**
     * Reads the next byte of data from the input stream.
     * 
     * @return the next byte of data, or -1 if the end of the stream is reached.
     * @throws IOException if an I/O error occurs.
     */

    public int read() throws IOException {
        if (_xmlReader._start < _xmlReader._end) {
            return _xmlReader._bytes[_xmlReader._start++];
        } else { // Use the reader buffer.
            return _xmlReader.fillBuffer() ?
                 _xmlReader._bytes[_xmlReader._start++] : -1;
        }
    }   

        /**
         * Resumes reading after an {@link XmlOutputStream#END_UTF8} byte 
         * is encountered. 
         */

        public void resume() {
            _isHalted = false;
        }

    /**
     * Returns SAX-2 like attributes for the current element.
     * 
     * @return the attributes of the current element.
     */

    public Attributes getSaxAttributes() {
        return _attributes;
    }

    /**
     * Returns the Java(tm) class corresponding to this XML element;
     * the class is identified by the tag name of this xml element or the <code>
     * "j:class" attribute when present. 
     *
     * @return this XML element's corresponding class.
     */

    public Class objectClass() {
        return _objectClass;
    }

    /**
     * Returns the content handler used during serialization (typically
     * a {@link javolution37.javolution.xml.sax.WriterHandler WriterHandler}).
     *
     * @return the content handler receiving the SAX-2 events.
     */

    public ContentHandler formatter() {
        return _formatHandler;
    }

    /**
     * Sets the underlying output destination for this stream.
     * 
     * @param out the output destination.
     * @return <code>this</code> 
     * @throws IllegalStateException if this stream is being reused and 
     *         it has not been {@link #close closed} or {@link #reset reset}.
     */

    public XmlOutputStream setOutputStream(OutputStream out) {
        if (_outputStream != null)
            throw new IllegalStateException("Stream not closed or reset");
        _outputStream = out;
        return this;
    }

    /**
     * Writes an object to the underlying stream using an {@link ObjectWriter}.
     * 
     * @param obj the object writen using its xml representation. 
     * @throws IOException if an I/O error occurs.
     */

    public void writeObject( T  obj) throws IOException {
        if (_outputStream == null) throw new IOException("Stream closed");
        _objectWriter.write(obj, _outputStreamProxy);
        _outputStream.write(END_XML);
        _outputStream.flush();
    }

    /**
     * Writes the specified byte to this output stream
     * 
     * @param b the byte. 
     * @throws IOException if an I/O error occurs.
     */

    public void write(int b) throws IOException {
        if (_outputStream == null) throw new IOException("Stream closed");
        _outputStream.write(b);
    }

    /**
     * Flushes this output stream and forces any buffered output bytes 
     * to be written out.
     *  
     * @throws IOException if an I/O error occurs.
     */

    public void flush() throws IOException {
        if (_outputStream == null) throw new IOException("Stream closed");
        _outputStream.flush();
    }

    /**
     * Writes the specified number of bytes from the specified byte array 
     * starting at the specified offset to this output stream. 
     * 
     * @param b the data.
     * @param off the start offset in the data.
     * @param len the number of bytes to write. 
     * @throws IOException if an I/O error occurs.
     */

    public void write(byte b[], int off, int len) throws IOException {
        if (_outputStream == null) throw new IOException("Stream closed");
        _outputStream.write(b, off, len);
    }

    /**
     * Returns the name to be used when objects associated to this 
     * format are added with no name specified (default <code>null</code>
     * the element name is the object class name).
     *
     * @return the default element name for objects using this format.
     */

    public String defaultName() {
        return null;
    }

    /**
     * Returns the index of the first character in this character data.
     *
     * @return the first character index.
     */

    public int offset() {
        return _offset;
    }

    /**
     * Adds the specified object as a named nested element of known type
     * (<code>null</code> objects are ignored). 
     *
     * @param obj the object added as nested element or <code>null</code>.
     * @param qName the qualified name of the nested element.
     * @param clazz the class identifying the xml format to use.
     */

    public void add(Object obj, String qName, Class clazz) {
        if (obj == null)
            return;
        try {
            // Flushes outer start element if any.
            if (_elemName != null)
                flushStart();

            // Formats the specified object.
            CharSequence elemName = _elemName = toCharSeq(qName);
            format(obj, XmlFormat.getInstance(clazz));
            if (_elemName != null)
                flushStart();
            _formatHandler.endElement(Text.EMPTY, elemName, elemName);

        } catch (SAXException e) {
            throw new XmlException(e);
        }
    }

    /**
     * Returns the character at the specified index.
     *
     * @param  index the index of the character.
     * @return the character at the specified index.
     * @throws IndexOutOfBoundsException if index is negative, or index 
     *         is equal or greater than <code>this.length()</code>.
     */

    public char charAt(int index) {
        if (index >= _length)
            throw new IndexOutOfBoundsException();
        return _chars[index];
    }

    /**
     * Returns a subsequence of this character data.
     *
     * @param  start the index of the first character inclusive.
     * @param  end the index of the last character exclusive.
     * @return a character data subsequence of this one.
     * @throws IndexOutOfBoundsException if <code>(start < 0) || (end < 0) ||
     *         (start > end) || (end > this.length())</code>
     */

    public CharSequence subSequence(int start, int end) {
        if ((start < 0) || (start > end) || (end > _length))
            throw new IndexOutOfBoundsException();
        return CharacterData.valueOf(_chars, _offset + start, end - start);
    }

    /**
     * Returns the names of the identifiers attributes when cross-reference 
     * is enabled. The default implementation returns 
     *  <code>isReference ? "j:ref" : "j:id"</code>. 
     * Format sub-classes may override this method to use different 
     * attribute names. This method may also return <code>null</code> for 
     * objects exclusively manipulated by value (e.g. immutable objects).
     *
     * @param isReference indicates if the attribute name returned is for
     *        a reference or an identifier.
     * @return the name of the attribute identifier or <code>null</code>
     *         if references should not be used.
     * @see ObjectWriter#setReferencesEnabled(boolean)
     */

    public String identifier(boolean isReference) {
        return isReference ? "j:ref" : "j:id";
    }

    /**
     * Calls supermethod and updates layout in a new thread. It also set the
     * symboltables for the childs.
     */

    public void addNotify() {
        super.addNotify();

        Component parent = getParent();
        if (parent != null && parent instanceof EqPanel)
            equationPanelParent = (EqPanel) parent;

        // this method must be called in a new thread, otherwise problems might
        // result (the real dangerous thing is to run updateLayout() in the
        // same command stack as addNotify()
        Runnable thread = new Runnable() {
            public void run() {
                updateLagsSymbol();
            }
        };
        SwingUtilities.invokeLater(thread);

    }

    /**
     * Creates a new attribute for this xml element.
     * 
     * This method allows for custom attribute formatting. For example:[code]
     *     // Formats the color RGB value in hexadecimal.
     *     xml.newAttribute("color").append(_color.getRGB(), 16);
     *     
     *     // Formats the error using 4 digits.
     *     xml.newAttribute("error").append(error, 4, false, false);[/code]
     *
     * @param  name the attribute name.
     * @return the text builder to hold the attribute value.
     */

    public TextBuilder newAttribute(String name) {
        if ((_formatAttributes == null) || (_elemName == null))
            attributeSettingError();
        return _formatAttributes.newAttribute(name);
    }

    /**
     * Gets the column width of the alpha table.
     * 
     * @return column width
     */

    public int getColumnWidth() {
        return ((EqTermDefault) allTerms.get(0)).getColumnWidth();
    }

    /**
     * Allocates a new object corresponding to this xml element.
     * By default, the {@link XmlElement#object} method returns an object 
     * created using the deserialized class public no-arg constructor.
     * Xml formats may perform custom allocations by overriding this method.  
     *
     * @param xml the xml elements.
     * @return the object corresponding to the specified xml element.
     */

    public T allocate(XmlElement xml) {
        return null;
    }

    /**
     * Gets the mouse listener of the underlying coefficients tables.
     * 
     * @return java.awt.event.MouseListener
     */

    public MouseListener getMouseListenerCoeff() {
        return ((EqTermDefault) allTerms.get(0)).getMouseListenerCoeff();
    }

    /**
     * Sets the specified <code>CharSequence</code> attribute
     * (<code>null</code> values are ignored).
     *
     * @param  name the attribute name.
     * @param  value the attribute value or <code>null</code>.
     */

    public void setAttribute(String name, CharSequence value) {
        if ((_formatAttributes == null) || (_elemName == null))
            attributeSettingError();
        if (value == null)
            return;
        _formatAttributes.addAttribute(name, value);
    }

    /**
     * Gets the precision of the underlying coefficients tables.
     * 
     * @return precision
     */

    public int getPrecision() {
        return ((EqTermDefault) allTerms.get(0)).getPrecision();
    }

    /**
     * Gets the cell renderer of the underlying coefficients tables.
     * 
     * @return javax.swing.table.TableCellRenderer
     */

    public TableCellRenderer getRendererCoeff() {
        return ((EqTermDefault) allTerms.get(0)).getRendererCoeff();
    }

    /**
     * Sets the specified <code>String</code> attribute
     * (<code>null</code> values are ignored).
     *
     * @param  name the attribute name.
     * @param  value the attribute value.
     */

    public void setAttribute(String name, String value) {
        if ((_formatAttributes == null) || (_elemName == null))
            attributeSettingError();
        if (value == null)
            return;
        CharSequence csqValue = toCharSeq(value);
        _formatAttributes.addAttribute(name, csqValue);
    }

    /**
     * Gets the lag that this equation term starts with. Usually this is -1.
     * 
     * @return int start lag
     */

    public int getStartLag() {
        return -1 * ((EqTermDefault) allTerms.get(0)).getLagIndex();
    }

	/**
	 * Adds only those columns that are in the sub matrix range.
	 * 
	 * 
	 * @param tc the column to add
	 */

	public void addColumn(TableColumn tc) {
		if (part < 0) {
			super.addColumn(tc);
		} else { // display only columns for the given lag
			int i = tc.getModelIndex();
			if ((part * n) <= i && i < ((part + 1) * n))
				super.addColumn(tc);
		}
	}

	/**
	 * Gets the index represented by this part.
	 * 
	 * @return index
	 */

	public int getIndex() {
		return part;
	}

	/**
	 * Gets the number of columns of this table model.
	 * 
	 * @return col number
	 */

	public int getNumberOfColumns() {
		return n;
	}

	/**
	 * Sets the index of this part of the equation.
	 * 
	 * @param arg index
	 */

	public void setIndex(int arg) {
		if (part != arg)
			part = arg;

	}

	/**
	 * Sets the number of columns in this model.
	 * 
	 * @param arg number of columns
	 */

	public void setNumberOfColumns(int arg) {
		n = Math.max(0, arg);
	}

    /**
     * Gets the symbolname for the coefficients.
     * 
     * @return symbol name
     */

    public String getSymbolNameCoeff() {
        return ((EqTermDefault) allTerms.get(0)).getSymbolNameCoeff();
    }

    /**
     * Gets the data object storing the subset restrictions or <code>null</code>
     * if no subset restrictions are set.
     * 
     * @return data object with subset restrictions
     */

    public JSCNArray getJSCNArraySubsetRes() {
        if (subsetResDef == null)
            return null;

        SymbolTable sTable = getSymbolTable();
        if (sTable == null) // not explicitely set
            sTable = getSymbolScope().getSymbolTable(this);

        if (sTable != null)
            return sTable.get(subsetResDef).getJSCNArray();

        return null;
    }

    /**
     * Gets the underlying <code>SubMatModel</code>.
     * 
     * @return sub matrix model
     */

    public SubMatModel getSubMatModel() {
        return subMatrixModel;
    }

    /**
     * Gets the symbolname for the subset restrictions or <code>null</code> if
     * none was set.
     * 
     * @return symbol name
     */

    public String getSymbolNameSubsetRes() {
        if (subsetResDef == null)
            return null;

        return subsetResDef.name;
    }

    /**
     * Gets the symbolname for the lags. Ther underlying data must be a
     * <code>JSCInt</code>.
     * 
     * @return symbol name
     */

    public String getSymbolNameLags() {
        if (lagsDef == null)
            return null;

        return lagsDef.name;
    }

    /**
     * Manages visibility of this component.
     */

    private void makeVisible() {
        int c = getColumnCount();
        boolean b = isVisible();
        if (c < 1 && b)
            setVisible(false);
        if (c > 0 && !b)
            setVisible(true);
    }

    /**
     * Sets the column count of this table.
     * 
     * @param arg
     *            number of cols
     */

    public void setColumnCount(int arg) {
        if (subMatrixModel == null) {
            subMatrixModel = new SubMatModel(0);
            subMatrixModel.setNumberOfColumns(arg);
            super.setColumnModel(subMatrixModel);
        } else {
            int oldCols = getColumnCount();
            if (oldCols != arg) {
                subMatrixModel.setNumberOfColumns(arg);
                createDefaultColumnsFromModel();
            }
        }
        makeVisible();

    }

    /**
     * Sets the column model for all columns of this table. Works only, if a
     * submatrix model was set before.
     * 
     * @param newModel
     *            the table model
     * @throws IllegalStateException
     *             <code>if (subMatrixModel != null)</code>
     */

    public void setColumnModel(TableColumnModel newModel) {
        if (subMatrixModel != null)
            throw new IllegalStateException("No submatrix model set.");

        super.setColumnModel(newModel);
    }

    /**
     * Gets the symbolname for the subset restrictions corresponding to the
     * coefficients.
     * 
     * @return symbol name
     */

    public String getSymbolNameSubsetRes() {
        return ((EqTermDefault) allTerms.get(0)).getSymbolNameSubsetRes();
    }

    /**
     * Sets the specified <code>boolean</code> attribute.
     * 
     * @param  name the attribute name.
     * @param  value the <code>boolean</code> value for the specified attribute.
     * @see    #getAttribute(String, boolean)
     */

    public void setAttribute(String name, boolean value) {
        newAttribute(name).append(value);
    }

    /**
     * Sets the index denoting the part in the equation for this table.
     * 
     * @param arg
     *            equation index
     */

    public void setIndex(int arg) {
        if (subMatrixModel == null) {
            subMatrixModel = new SubMatModel(arg);
            super.setColumnModel(subMatrixModel);
        } else
            subMatrixModel.setIndex(arg);

    }

    /**
     * Sets the symbol name of the subset restrictions that correspond to the
     * coefficients displayed in this table. How the restrictions affect the
     * behaviour of this table is determined by the cell renderer, mouse
     * listener, editor and table popup.
     * 
     * @param newSymbolNameSubsetRes
     * @throws IllegalArgumentException
     *             if <code>newSymbolNameSubsetRes</code> is not a valid name
     *             for a symbol
     */

    public void setSymbolNameSubsetRes(String newSymbolNameSubsetRes) {
        if (newSymbolNameSubsetRes == null) {
            subsetResDef = null;
            return;
        }

        subsetResDef = new JSCTypeDef(newSymbolNameSubsetRes, JSCTypes.NARRAY);

        SymbolTable sTable = getSymbolTable();
        if (sTable == null) // not explicitely set
            sTable = getSymbolScope().getSymbolTable(this);

        if (sTable != null) {
            sTable.get(subsetResDef).addSymbolListener(new SymbolListener() {
                public void valueChanged(SymbolEvent evt) {
                    resizeAndRepaint();
                }
            });
        }
    }

    /**
     * Gets the symbolname for the names of the variables.
     * 
     * @return symbol name
     */

    public String getSymbolNameVariables() {
        return ((EqTermDefault) allTerms.get(0)).getSymbolNameVariables();
    }

    /**
     * Extends the super method by firering the (boolean) property change
     * "visible".
     * 
     * @param aFlag
     *            true to make the component visible
     */

    public void setVisible(boolean aFlag) {
        boolean oldValue = isVisible();
        super.setVisible(aFlag);
        firePropertyChange("visible", oldValue, isVisible());
    }

    /**
     * Manages visibility of this table on table changed events.
     */

    public void tableChanged(TableModelEvent e) {
        super.tableChanged(e);
        makeVisible();
    }

    /**
     * Gets the popup menu for the underlying coefficients tables.
     * 
     * @return popup menu
     */

    public JPopupMenu getTablePopup() {
        return tablePopup;
    }

	/**
	 * Gets the column width of the alpha table.
	 * 
	 * @return column width
	 */

	public int getColumnWidth() {
		return alpha.getColumnWidth();
	}

    /**
     * Gets whether the underlying coefficients tables are editable.
     * 
     * @return <code>true</code> if editable
     */

    public boolean isEditable() {
        return ((EqTermDefault) allTerms.get(0)).isEditable();
    }

	/**
	 * Gets the mouse listener of the underlying coefficients
	 * tables.
	 * 
	 * @return java.awt.event.MouseListener
	 */

	public MouseListener getMouseListenerCoeff() {
		return mouseListenerCoeff;
	}

    /**
     * Sets the specified <code>int</code> attribute.
     * 
     * @param  name the attribute name.
     * @param  value the <code>int</code> value for the specified attribute.
     * @see    #getAttribute(String, int)
     */

    public void setAttribute(String name, int value) {
        newAttribute(name).append(value);
    }

	/**
	 * Gets the precision of the underlying coefficients
	 * tables.
	 * 
	 * @return precision
	 */

	public int getPrecision() {
		return alpha.getPrecision();
	}

	/**
	 * Gets the cell renderer of the underlying coefficients
	 * tables.
	 * 
	 * @return javax.swing.table.TableCellRenderer
	 */

	public TableCellRenderer getRendererCoeff() {
		return alpha.getCellRenderer();
	}

    /**
     * Manages visibility of tis term.
     * 
     * @param evt
     *            property change event
     */

    public void propertyChange(java.beans.PropertyChangeEvent evt) {
        if (evt.getSource().equals(allTerms.get(0))
                && evt.getPropertyName() == "visible") {
            // also sets term visible/invisible
            setDataAvailable(((Boolean) evt.getNewValue()).booleanValue());
        }
    }

	/**
	 * Gets the symbolname for the alpha coefficients.
	 * 
	 * @return symbol name 
	 */

	public String getSymbolNameAlphaCoeff() {
		return alpha.getSymbolName();
	}

	/**
	 * Gets the symbolname for the subset restrictions
	 * corresponding to the alpha coefficients.
	 * 
	 * @return symbol name 
	 */

	public String getSymbolNameAlphaSubsetRes() {
		return alpha.getSymbolNameSubsetRes();
	}

    /**
     * Sets the column width of the underlying coefficients tables.
     * 
     * @param colWidth
     *            column width
     */

    public void setColumnWidth(int colWidth) {
        for (int i = 0; i < allTerms.size(); i++)
            ((EqTermDefault) allTerms.get(i)).setColumnWidth(colWidth);
    }

	/**
	 * Gets the symbolname for the beta coefficients
	 * of the endogenous variables.
	 * 
	 * @return symbol name 
	 */

	public String getSymbolNameBetaCoeff() {
		return ecTerm1.getSymbolNameCoeff();
	}

	/**
	 * Gets the symbolname for the beta coefficients
	 * of the restricted deterministic variables.
	 * 
	 * @return symbol name 
	 */

	public String getSymbolNameBetaDetCoeff() {
		return ecTerm2.getSymbolNameCoeff();
	}

	/**
	 * Gets the symbolname for the subset restrictions
	 * corresponding to the beta coefficients of the
	 * restricted deterministic variables.
	 * 
	 * @return symbol name 
	 */

	public String getSymbolNameBetaDetSubsetRes() {
		return ecTerm2.getSymbolNameSubsetRes();
	}

    /**
     * Sets whether the underlying coefficients tables are editable.
     * 
     * @param isEditable
     *            <code>true</code> if editable
     */

    public void setEditable(boolean isEditable) {
        for (int i = 0; i < allTerms.size(); i++)
            ((EqTermDefault) allTerms.get(i)).setEditable(isEditable);
    }

    /**
     * Sets the specified <code>long</code> attribute.
     * 
     * @param  name the attribute name.
     * @param  value the <code>long</code> value for the specified attribute.
     * @see    #getAttribute(String, long)
     */

    public void setAttribute(String name, long value) {
        newAttribute(name).append(value);
    }

	/**
	 * Gets the symbolname for the subset restrictions
	 * corresponding to the beta coefficients of the
	 * endogenous variables.
	 * 
	 * @return symbol name 
	 */

	public String getSymbolNameBetaSubsetRes() {
		return ecTerm1.getSymbolNameSubsetRes();
	}

	/**
	 * Gets the symbolname for the names of the deterministic
	 * variables.
	 * 
	 * @return symbol name 
	 */

	public String getSymbolNameDetVarNames() {
		return ecTerm2.getSymbolNameVariables();
	}

    /**
     * Manages visibility of the leading sign label.
     * 
     * @param isVisible
     *            <code>true</code> if visible
     */

    public void setLeadingSignVisible(boolean isVisible) {
        ((EqTermDefault) allTerms.get(0)).setLeadingSignVisible(isVisible);
    }

	/**
	 * Gets the symbolname for the names of the endogenous
	 * variables.
	 * 
	 * @return symbol name 
	 */

	public String getSymbolNameEndVarNames() {
		return ecTerm1.getSymbolNameVariables();
	}

	/**
	 * Gets whether the underlying coefficients tables
	 * are editable.
	 * 
	 * @return <code>true</code> if editable
	 */

	public boolean isEditable() {
		return alpha.isEditable();
	}

