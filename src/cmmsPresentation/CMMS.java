/*
 * Main window for the CMMS
 */
package cmmsPresentation;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.wb.swt.SWTResourceManager;


import cmmsBusiness.AccessVehicle;
import cmmsBusiness.VehicleFields;
import cmmsObjects.Vehicle;




import java.util.ArrayList;

public class CMMS{
    /* Interface Constants */
    private static final Point MIN_WINDOW_SIZE = new Point(800, 600);
    private static final String WINDOW_TITLE = "Computerized Maintenance Management System";
    private static final String SEARCH_TEXT_DEFAULT = "Search For...";
    private static final int LAYOUT_COL_COUNT = 4;
    private static final int DEFAULT_TABLE_COL_WIDTH = 100;
    private static final int TABLE_WIDTH = 3;
    private static final int TABLE_HEIGHT = 7;
    private static final int TABLE_COL_COUNT = 14;

    private static int currentVehicleCount = 0;
    private static boolean searching = false;

    private static Display currDisplay;
    private static Shell mainWindow;
    private static GridLayout mainLayout;
    private static GridData gridData;

    private static Menu mainMenu;
    private static Menu fileMenu;
    private static Menu vehicleMenu;
    private static Menu optionsMenu;
    private static Menu	viewMenu;
    private static Menu tableHdrsMenu;
    private static Menu helpMenu;
    
    private static MenuItem fileMenuHdr;
    private static MenuItem vehicleMenuHdr;
    private static MenuItem mntmOptions;
    private static MenuItem viewMenuHdr;
    private static MenuItem helpMenuHdr;

    private static MenuItem filePrintItem;
    private static MenuItem fileExitItem;
    private static MenuItem vehicleAddItem;
    private static MenuItem vehicleRemoveItem;
    private static MenuItem vehicleViewItem;
    private static MenuItem optionsChangeManFields;
    private static MenuItem tableColsItem;
    private static MenuItem helpAboutItem;
    
    private static MenuItem vehicleIDColItem;
    private static MenuItem vehicleTypeColItem;
    private static MenuItem vehicleModelColItem;
    private static MenuItem vehicleLicensePlateColItem;
    private static MenuItem vehicleFuelEconColItem;
    private static MenuItem vehicleKMColItem;
    private static MenuItem vehiclePolicyColItem;
    private static MenuItem vehicleInsTypeColItem;
    private static MenuItem vehicleKMLastServiceColItem;
    private static MenuItem vehicleDateLastServicedColItem;
    private static MenuItem vehicleManufacturerColItem;
    private static MenuItem vehicleOperationalColItem;
    private static MenuItem vehicleRoadWorthyColItem;
    private static MenuItem vehicleYearColItem;

    private static Label searchLabel;

    private static Button clearButton;
    private static Button addVehicleButton;
    private static Button removeVehicleButton;
    private static Button editVehicleButton;
    private static Button viewVehicleButton;
    private static Button updateKmsButton;
    private static Button addServiceButton;
    private static Button btnTodaysTasks;
    private static Button quitButton;

    private static Text searchText;
    private static Combo searchCombo;

    private static Table dataTable;
    private static TableColumn vehicleIDCol;
    private static TableColumn vehicleTypeCol;
    private static TableColumn vehicleModelCol;
    private static TableColumn vehicleLicensePlateCol;
    private static TableColumn vehicleFuelEconCol;
    private static TableColumn vehicleKMCol;
    private static TableColumn vehiclePolicyCol;
    private static TableColumn vehicleInsTypeCol;
    private static TableColumn vehicleKMLastServiceCol;
    private static TableColumn vehicleDateLastServicedCol;
    private static TableColumn vehicleManufacturerCol;
    private static TableColumn vehicleOperationalCol;
    private static TableColumn vehicleRoadWorthyCol;
    private static TableColumn vehicleYearCol;
    
    private static AccessVehicle accessVehicle;
    
    private static ArrayList<Vehicle> searchList;
    
    private static String[] columnHeaders = {
    	"ID",
        "Type",
        "Manufacturer",
        "Model",
        "Year",
        "Kilometers",
        "Last service (KM)",
        "Date Last Serviced",
        "Is Roadworthy",
        "License Plate",
        "Insurance Policy Number",
        "Insurance Policy Type",
        "Is Operational",
        "Fuel Economy (L/100km)"
    };
    
    private static boolean[] columnVisibility = {
    	true,
    	true,
    	true,
    	true,
    	true,
    	true,
    	true,
    	true,
    	true,
    	true,
    	true,
    	true,
    	true,
    	true
    };

    private static TableColumn[] tableCols = new TableColumn[TABLE_COL_COUNT];
    
    public CMMS()
    {
        CreateWindow();
        OnLoad();
        Open();
    }

    private static void OnLoad() {
        // Read existing database vehicles
        UpdateList();
    }
    
    private static void FillColArray() {
    	tableCols[0] = vehicleIDCol;
        tableCols[1] = vehicleTypeCol;
        tableCols[2] = vehicleModelCol;
        tableCols[3] = vehicleLicensePlateCol;
        tableCols[4] = vehicleFuelEconCol;
        tableCols[5] = vehicleKMCol;
        tableCols[6] = vehiclePolicyCol;
        tableCols[7] = vehicleInsTypeCol;
        tableCols[8] = vehicleKMLastServiceCol;
        tableCols[9] = vehicleDateLastServicedCol;
        tableCols[10] = vehicleManufacturerCol;
        tableCols[11] = vehicleOperationalCol;
        tableCols[12] = vehicleRoadWorthyCol;
        tableCols[13] = vehicleYearCol;
    }

    private static void CreateWindow() {
        mainWindow = new Shell();
        mainWindow.setText(WINDOW_TITLE);
        mainWindow.setMinimumSize(MIN_WINDOW_SIZE);
        mainWindow.setFocus();      

        mainLayout = new GridLayout();
        mainLayout.numColumns = 4;

        currDisplay = Display.getDefault();
        
        CreateMenus();
        CreateControls();
        CreateColumns();
    }

    private static void CreateMenus() {
        mainMenu = new Menu(mainWindow, SWT.BAR);

        CreateFileMenu();
        CreateVehicleMenu();
        CreateOptionsMenu();
        CreateViewMenu();
        CreateHelpMenu();

        mainWindow.setMenuBar(mainMenu);
    }

    private static void CreateFileMenu() {
        fileMenuHdr = new MenuItem(mainMenu, SWT.CASCADE);
        fileMenuHdr.setText("&File");

        fileMenu = new Menu(mainWindow, SWT.DROP_DOWN);
        fileMenuHdr.setMenu(fileMenu);

        filePrintItem = new MenuItem(fileMenu, SWT.PUSH);
        filePrintItem.setText("&Print");

        fileExitItem = new MenuItem(fileMenu, SWT.PUSH);
        fileExitItem.setText("E&xit");
        fileExitItem.addSelectionListener(new FileExitListener());
    }

    private static void CreateVehicleMenu() {
        vehicleMenuHdr = new MenuItem(mainMenu, SWT.CASCADE);
        vehicleMenuHdr.setText("&Vehicle");

        vehicleMenu = new Menu(mainWindow, SWT.DROP_DOWN);
        vehicleMenuHdr.setMenu(vehicleMenu);

        vehicleAddItem = new MenuItem(vehicleMenu, SWT.PUSH);
        vehicleAddItem.setText("&Add");

        vehicleViewItem = new MenuItem(vehicleMenu, SWT.PUSH);
        vehicleViewItem.setText("&View");

        vehicleRemoveItem = new MenuItem(vehicleMenu, SWT.PUSH);
        vehicleRemoveItem.setText("&Remove");
    }
    
    private static void CreateOptionsMenu(){
        mntmOptions = new MenuItem(mainMenu, SWT.CASCADE);
        mntmOptions.setText("Options");
        
        optionsMenu = new Menu(mntmOptions);
        mntmOptions.setMenu(optionsMenu);
        
    	optionsChangeManFields = new MenuItem(optionsMenu, SWT.NONE);
    	optionsChangeManFields.addSelectionListener(new SelectionAdapter() {
    		@Override
    		public void widgetSelected(SelectionEvent e) {
    			ChangeManFields manWindow = new ChangeManFields();
    			//access.addManFields();
    			manWindow.open();
    		}
    	});
    	optionsChangeManFields.setText("Change Mandatory Fields");
    }

    private static void CreateViewMenu() {
    	viewMenuHdr = new MenuItem(mainMenu, SWT.CASCADE);
    	viewMenuHdr.setText("View");
    	
    	viewMenu = new Menu(viewMenuHdr);
    	viewMenuHdr.setMenu(viewMenu);
    	
    	tableColsItem = new MenuItem(viewMenu, SWT.CASCADE);
    	tableColsItem.setText("Table Columns");
    	
    	tableHdrsMenu = new Menu(tableColsItem);
    	tableColsItem.setMenu(tableHdrsMenu);
    
    	vehicleIDColItem = new MenuItem(tableHdrsMenu, SWT.CHECK);
    	vehicleIDColItem.setSelection(true);
    	vehicleIDColItem.addSelectionListener(new SelectionAdapter() {
    		@Override
    		public void widgetSelected(SelectionEvent e) {
    			PackColumns();
    		}
    	});
    	vehicleIDColItem.setText("Vehicle ID");
    	
        vehicleTypeColItem = new MenuItem(tableHdrsMenu, SWT.CHECK);
        vehicleTypeColItem.setSelection(true);
        vehicleTypeColItem.addSelectionListener(new SelectionAdapter() {
    		@Override
    		public void widgetSelected(SelectionEvent e) {
    			PackColumns();
    		}
    	});
        vehicleTypeColItem.setText("Vehicle Type");
        
        vehicleModelColItem = new MenuItem(tableHdrsMenu, SWT.CHECK);
        vehicleModelColItem.setSelection(true);
        vehicleModelColItem.addSelectionListener(new SelectionAdapter() {
    		@Override
    		public void widgetSelected(SelectionEvent e) {
    			PackColumns();
    		}
    	});
        vehicleModelColItem.setText("Vehicle Model");
        
        vehicleLicensePlateColItem = new MenuItem(tableHdrsMenu, SWT.CHECK);
        vehicleLicensePlateColItem.setSelection(true);
        vehicleLicensePlateColItem.addSelectionListener(new SelectionAdapter() {
    		@Override
    		public void widgetSelected(SelectionEvent e) {
    			PackColumns();
    		}
    	});
        vehicleLicensePlateColItem.setText("License Plate");
        
        vehicleFuelEconColItem = new MenuItem(tableHdrsMenu, SWT.CHECK);
        vehicleFuelEconColItem.setSelection(true);
        vehicleFuelEconColItem.addSelectionListener(new SelectionAdapter() {
    		@Override
    		public void widgetSelected(SelectionEvent e) {
    			PackColumns();
    		}
    	});
        vehicleFuelEconColItem.setText("Fuel Economy");

        vehicleKMColItem = new MenuItem(tableHdrsMenu, SWT.CHECK);
        vehicleKMColItem.setSelection(true);
        vehicleKMColItem.addSelectionListener(new SelectionAdapter() {
    		@Override
    		public void widgetSelected(SelectionEvent e) {
    			PackColumns();
    		}
    	});
        vehicleKMColItem.setText("Kilometers");
        
        vehiclePolicyColItem = new MenuItem(tableHdrsMenu, SWT.CHECK);
        vehiclePolicyColItem.setSelection(true);
        vehiclePolicyColItem.addSelectionListener(new SelectionAdapter() {
    		@Override
    		public void widgetSelected(SelectionEvent e) {
    			PackColumns();
    		}
    	});
        vehiclePolicyColItem.setText("Insurance Policy");
        
        vehicleInsTypeColItem = new MenuItem(tableHdrsMenu, SWT.CHECK);
        vehicleInsTypeColItem.setSelection(true);
        vehicleInsTypeColItem.addSelectionListener(new SelectionAdapter() {
    		@Override
    		public void widgetSelected(SelectionEvent e) {
    			PackColumns();
    		}
    	});
        vehicleInsTypeColItem.setText("Insurance Type");
        
        vehicleKMLastServiceColItem = new MenuItem(tableHdrsMenu, SWT.CHECK);
        vehicleKMLastServiceColItem.setSelection(true);
        vehicleKMLastServiceColItem.addSelectionListener(new SelectionAdapter() {
    		@Override
    		public void widgetSelected(SelectionEvent e) {
    			PackColumns();
    		}
    	});
        vehicleKMLastServiceColItem.setText("Last Service (KM)");
        
        vehicleDateLastServicedColItem = new MenuItem(tableHdrsMenu, SWT.CHECK);
        vehicleDateLastServicedColItem.setSelection(true);
        vehicleDateLastServicedColItem.addSelectionListener(new SelectionAdapter() {
    		@Override
    		public void widgetSelected(SelectionEvent e) {
    			PackColumns();
    		}
    	});
        vehicleDateLastServicedColItem.setText("Date Last Serviced");
        
        vehicleManufacturerColItem = new MenuItem(tableHdrsMenu, SWT.CHECK);
        vehicleManufacturerColItem.setSelection(true);
        vehicleManufacturerColItem.addSelectionListener(new SelectionAdapter() {
    		@Override
    		public void widgetSelected(SelectionEvent e) {
    			PackColumns();
    		}
    	});
        vehicleManufacturerColItem.setText("Manufacturer");
        
        vehicleOperationalColItem = new MenuItem(tableHdrsMenu, SWT.CHECK);
        vehicleOperationalColItem.setSelection(true);
        vehicleOperationalColItem.addSelectionListener(new SelectionAdapter() {
    		@Override
    		public void widgetSelected(SelectionEvent e) {
    			PackColumns();
    		}
    	});
        vehicleOperationalColItem.setText("Operational");
        
        vehicleRoadWorthyColItem = new MenuItem(tableHdrsMenu, SWT.CHECK);
        vehicleRoadWorthyColItem.setSelection(true);
        vehicleRoadWorthyColItem.addSelectionListener(new SelectionAdapter() {
    		@Override
    		public void widgetSelected(SelectionEvent e) {
    			PackColumns();
    		}
    	});
        vehicleRoadWorthyColItem.setText("Roadworthy");
        
        vehicleYearColItem = new MenuItem(tableHdrsMenu, SWT.CHECK);
        vehicleYearColItem.setSelection(true);
        vehicleYearColItem.addSelectionListener(new SelectionAdapter() {
    		@Override
    		public void widgetSelected(SelectionEvent e) {
    			PackColumns();
    		}
    	});
        vehicleYearColItem.setText("Year");
    }
    
    private static void CreateHelpMenu() {
        helpMenuHdr = new MenuItem(mainMenu, SWT.CASCADE);
        helpMenuHdr.setText("&Help");

        helpMenu = new Menu(mainWindow, SWT.DROP_DOWN);
        helpMenuHdr.setMenu(helpMenu);

        helpAboutItem = new MenuItem(helpMenu, SWT.PUSH);
        helpAboutItem.setText("A&bout");
    }

    /***************************************
     * Controls are added into a grid layout that keeps them structured and in
     * place nicely.
     * 
     * Controls span columns and rows as part of the formatting. The table spans
     * 5 rows and 2 columns. This leaves one column for the buttons on the right
     * and 5 cells in the last column for buttons. If we were to use any number
     * less for the row spanning, "extra" buttons would start in the next row.
     ***************************************/
    private static void CreateControls() {
        searchLabel = new Label(mainWindow, SWT.NONE);
        searchLabel.setText("Search:");
        
        searchCombo = new Combo(mainWindow, SWT.NONE);
        searchCombo.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusGained(FocusEvent e) {
        		if (searchCombo.getText() != "")
        			searchCombo.setText("");
        	}
        });
        for (String s : columnHeaders)
        	searchCombo.add(s);
        
        searchText = new Text(mainWindow, SWT.BORDER); 
        searchText.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusGained(FocusEvent e) {
        		searchText.setText("");
        		searching = true;
        	}
        	@Override
        	public void focusLost(FocusEvent e) {
        		if (searchText.getText().compareTo("") == 0 || 
        			searchText.getText().compareTo("\t") == 0)
        			searchText.setText(SEARCH_TEXT_DEFAULT);
        		searching = false;
        	}
        });
        searchText.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {        		
        		// This way we fill the table with table items
        		if (!searching)
        			searching = true;
        		
        		// Search for Stuff
        		String searchVal = null;
        		if (e.keyCode >= 97 && e.keyCode <= 122 ||
        			e.keyCode >= 48 && e.keyCode <= 57)
        			searchVal = searchText.getText() + e.character;
        		else if (e.keyCode == SWT.BS && searchText.getText().length() > 0)
        			searchVal = searchText.getText().substring(0, searchText.getText().length() - 1);
        		
        		if (searchVal != null) {
        			Vehicle[] accessReturn = accessVehicle.getVehicles(searchCombo.getText(), searchVal);
                
	        		searchList = new ArrayList<Vehicle>();
	        		for (Vehicle v : accessReturn) {
	        			searchList.add(v);
	        		}
	        		
	        		UpdateList();
        		}
        	}
        });
        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.grabExcessHorizontalSpace = true;
        searchText.setLayoutData(gridData);
        searchText.setText(SEARCH_TEXT_DEFAULT);
        
        clearButton = new Button(mainWindow, SWT.NONE); 
        clearButton.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
            	searchText.setText(SEARCH_TEXT_DEFAULT);
            	searching = false;
            	UpdateList();
        	}
        });
        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        clearButton.setLayoutData(gridData); 
        clearButton.setText("Clear");

        dataTable = new Table(mainWindow, SWT.FULL_SELECTION | SWT.MULTI
                | SWT.BORDER);
        dataTable.setItemCount(currentVehicleCount);
        dataTable.setHeaderVisible(true);
        dataTable.setLinesVisible(true);
        dataTable.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		if(dataTable.getSelectionCount() == 0) {
        			removeVehicleButton.setEnabled(false);
	        		editVehicleButton.setEnabled(false);
	        		viewVehicleButton.setEnabled(false);
	        		updateKmsButton.setEnabled(false);
	        		addServiceButton.setEnabled(false);
        		}
        		else {
	        		removeVehicleButton.setEnabled(true);
	        		editVehicleButton.setEnabled(true);
	        		viewVehicleButton.setEnabled(true);
	        		updateKmsButton.setEnabled(true);
	        		addServiceButton.setEnabled(true);
        		}
        	}
        });
        gridData = new GridData();
        gridData.horizontalSpan = TABLE_WIDTH;
        gridData.verticalSpan = 8;
        gridData.heightHint = (MIN_WINDOW_SIZE.y / 2);
        gridData.horizontalAlignment = SWT.FILL;
        gridData.verticalAlignment = SWT.FILL;
        gridData.grabExcessHorizontalSpace = true;
        gridData.grabExcessVerticalSpace = true;
        dataTable.setLayoutData(gridData);

        addVehicleButton = new Button(mainWindow, SWT.NONE);
        addVehicleButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {            	
                AddVehicle addWindow = new AddVehicle();
                addWindow.open();
                
            	clearButton.notifyListeners(SWT.Selection, null);
                // Update list with the new Vehicles
                UpdateList();
            }
        });
        gridData = new GridData();
        gridData.grabExcessVerticalSpace = false;
        gridData.verticalIndent = 50;
        gridData.horizontalAlignment = SWT.FILL;
        gridData.verticalAlignment = SWT.CENTER;
        addVehicleButton.setLayoutData(gridData);
        addVehicleButton.setText("Add Vehicle");

        removeVehicleButton = new Button(mainWindow, SWT.NONE);
        removeVehicleButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int selected = dataTable.getSelectionCount();
                if (selected == 0) {
                    // Display no selection error
                    MessageBox mb = new MessageBox(mainWindow, SWT.ICON_ERROR
                            | SWT.OK);
                    mb.setMessage("Error: You have not selected any vehicles to remove");
                    mb.setText("Removing Vehicles");
                    mb.open();
                } else if (selected > 1) {
                    // Display multiple selection error
                    MessageBox mb = new MessageBox(mainWindow, SWT.ICON_WARNING
                            | SWT.YES | SWT.NO);
                    mb.setMessage("Warning: You have selected multiple vehicles.\nDo you wish to continue?");
                    mb.setText("Removing Vehicles");
                    int response = mb.open();

                    if (response == SWT.YES) {
                        int[] selections = dataTable.getSelectionIndices();
                        for (int i = 0; i < selected; i++) {
//                        	accessVehicle.removeVehicle(dataTable.getItem(
//                        			selections[i]).getText(0));
                        }

                        // Update list with the new Vehicles
                        UpdateList();
                    }
                } else {
                    // Display multiple selection error
                    MessageBox mb = new MessageBox(mainWindow, SWT.ICON_WARNING
                            | SWT.YES | SWT.NO);
                    mb.setMessage("Warning: Do you want to delete the selected object?");
                    mb.setText("Removing Vehicles");
                    int response = mb.open();

                    if (response == SWT.YES) {
                        // Should only have one item selected
//                    	accessVehicle.removeVehicle(dataTable.getItem(
//                    			dataTable.getSelectionIndex()).getText(VehicleFields.ID.ordinal()));
                        // Update list with the new Vehicles
                        UpdateList();
                    }
                }
            }
        });
        gridData = new GridData();
        gridData.grabExcessVerticalSpace = false;
        gridData.horizontalAlignment = SWT.FILL;
        gridData.verticalAlignment = SWT.TOP;
        removeVehicleButton.setLayoutData(gridData);
        removeVehicleButton.setText("Remove Vehicle");

        editVehicleButton = new Button(mainWindow, SWT.NONE);
        editVehicleButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int selected = dataTable.getSelectionCount();
                if (selected == 0) {
                    // Display no selection error
                    MessageBox mb = new MessageBox(mainWindow, SWT.ICON_ERROR
                            | SWT.OK);
                    mb.setMessage("Error: You have not selected a Vehicle to edit.");
                    mb.setText("Editing Vehicles");
                    mb.open();
                } else if (selected == 1) {
                    // Display EditVehicle form
                	Vehicle v = accessVehicle.getVehicle(dataTable.getItem(
                			dataTable.getSelectionIndex()).getText(
                    		VehicleFields.ID.ordinal()));
                    EditVehicle editWindow = new EditVehicle();
                    editWindow.open(v);

                    // Update list with the new Vehicles
                    UpdateList();
                } else {
                    // Display multiple selection error
                    MessageBox mb = new MessageBox(mainWindow, SWT.ICON_ERROR
                            | SWT.OK);
                    mb.setMessage("Error: You have selected too many Vehicles to edit.");
                    mb.setText("Editing Vehicles");
                    mb.open();
                }
            }
        });
        gridData = new GridData();
        gridData.grabExcessVerticalSpace = false;
        gridData.horizontalAlignment = SWT.FILL;
        gridData.verticalAlignment = SWT.TOP;
        editVehicleButton.setLayoutData(gridData);
        editVehicleButton.setText("Edit Vehicle");

        viewVehicleButton = new Button(mainWindow, SWT.NONE);
        viewVehicleButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int selected = dataTable.getSelectionCount();
                if (selected == 0) {
                    // Display no selection error
                    MessageBox mb = new MessageBox(mainWindow, SWT.ICON_ERROR
                            | SWT.OK);
                    mb.setMessage("Error: You have not selected any Vehicles to view.");
                    mb.setText("Viewing Vehicles");
                    mb.open();
                } else if (selected == 1) {
                	Vehicle v = accessVehicle.getVehicle(dataTable.getItem(
                			dataTable.getSelectionIndex()).getText(
                			VehicleFields.ID.ordinal()));
                    ViewVehicle viewWindow = new ViewVehicle();
                    viewWindow.open(v);
                } else {
                    int[] selections = dataTable.getSelectionIndices();
                    Vehicle[] v = new Vehicle[selections.length];

                    for (int i = 0; i < selected; i++)
                    	v[i] = accessVehicle.getVehicle(dataTable.getItem(
                    			selections[i]).getText(
                    					VehicleFields.ID.ordinal()));
                    ViewVehicle viewWindow = new ViewVehicle();
                    viewWindow.open(v);
                }
            }
        });
        gridData = new GridData();
        gridData.grabExcessVerticalSpace = false;
        gridData.horizontalAlignment = SWT.FILL;
        gridData.verticalAlignment = SWT.TOP;
        viewVehicleButton.setLayoutData(gridData);
        viewVehicleButton.setText("View Vehicle");

        updateKmsButton = new Button(mainWindow, SWT.NONE);
        updateKmsButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int selected = dataTable.getSelectionCount();
                if (selected == 0) {
                    // Display no selection error
                    MessageBox mb = new MessageBox(mainWindow, SWT.ICON_ERROR
                            | SWT.OK);
                    mb.setMessage("Error: You have not selected a Vehicle to update.");
                    mb.setText("Updating Kilometers");
                    mb.open();
                } else if (selected == 1) {
                    // Display UpdateKilometers form
                	Vehicle v = accessVehicle.getVehicle(dataTable.getItem(
                			dataTable.getSelectionIndex()).getText(
                    		VehicleFields.ID.ordinal()));
                    UpdateKilometers updateKms = new UpdateKilometers();
                    updateKms.open(v);

                    // Update list with the new Vehicles
                    UpdateList();
                } else {
                    // Display multiple selection error
                    MessageBox mb = new MessageBox(mainWindow, SWT.ICON_ERROR
                            | SWT.OK);
                    mb.setMessage("Error: You have selected too many Vehicles to update.");
                    mb.setText("Updating Vehicles");
                    mb.open();
                }
            }
        });
        gridData = new GridData();
        gridData.grabExcessVerticalSpace = false;
        gridData.horizontalAlignment = SWT.FILL;
        gridData.verticalAlignment = SWT.TOP;
        updateKmsButton.setLayoutData(gridData);
        updateKmsButton.setText("Update Kilometers");
        
        addServiceButton = new Button(mainWindow, SWT.NONE);
        addServiceButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int selected = dataTable.getSelectionCount();
                if (selected == 0) {
                    // Display no selection error
                    MessageBox mb = new MessageBox(mainWindow, SWT.ICON_ERROR
                            | SWT.OK);
                    mb.setMessage("Error: You have not selected a Vehicle to update.");
                    mb.setText("Add Service Event");
                    mb.open();
                } else if (selected == 1) {
                    // Display UpdateKilometers form
                	Vehicle v = accessVehicle.getVehicle(dataTable.getItem(
                			dataTable.getSelectionIndex()).getText(
                					VehicleFields.ID.ordinal()));
                	if(v.partsListIsEmpty()) {
                		MessageBox mb = new MessageBox(mainWindow, SWT.ICON_ERROR | SWT.OK);
                		mb.setMessage("Error: " + v.getID() + " does not have any parts");
                		mb.setText("Add Service Event");
                		mb.open();
                	}
                	else {
                		AddServiceEvent addse = new AddServiceEvent();
                		addse.open(v);
                	}
                    // Update list with the new Vehicles
                    UpdateList();
                } else {
                    // Display multiple selection error
                    MessageBox mb = new MessageBox(mainWindow, SWT.ICON_ERROR
                            | SWT.OK);
                    mb.setMessage("Error: You have selected too many Vehicles to update.");
                    mb.setText("Add Service Event");
                    mb.open();
                }
            }
        });
        gridData = new GridData();
        gridData.grabExcessVerticalSpace = false;
        gridData.horizontalAlignment = SWT.FILL;
        gridData.verticalAlignment = SWT.BOTTOM;
        addServiceButton.setLayoutData(gridData);
        addServiceButton.setText("Add Service Event");
        
        btnTodaysTasks = new Button(mainWindow, SWT.NONE);
        btnTodaysTasks.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent arg0) {
        		openDailyTasks();
        	}
        });
        btnTodaysTasks.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
        btnTodaysTasks.setText("Today's Tasks");
        
        
        quitButton = new Button(mainWindow, SWT.NONE);
        quitButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                mainWindow.close();
                currDisplay.dispose();
            }
        });
        

        gridData = new GridData();
        gridData.grabExcessVerticalSpace = false;
        gridData.horizontalAlignment = SWT.FILL;
        gridData.verticalAlignment = SWT.BOTTOM;
        quitButton.setLayoutData(gridData);
        quitButton.setText("Quit");
    }
    
    private static void UpdateList() {
        TableItem ti; // Table item for adding vehicles to the table
        accessVehicle = new AccessVehicle();
        
        ArrayList<Vehicle> list;
        if (!searching)
        	list = accessVehicle.getAllVehicles();
        else
        	list = searchList; 

        // Stop Drawing Table, Empty Table, Rebuild Table, Start Drawing Table
        dataTable.setRedraw(false);

        if (dataTable.getItemCount() >= 1)
            dataTable.remove(0, dataTable.getItemCount() - 1);

        dataTable.setRedraw(true);
        
        for (Vehicle v : list) {
        	if (v != null) {
	            ti = new TableItem(dataTable, SWT.NONE);
	            if (v.checkRequiresService()) {
	            	ti.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
	            }
	            System.out.println("Vehicles: " + v.ToStrings());
	            ti.setText(v.ToStrings());
        	}
        }
        
        PackColumns();
        dataTable.select(0);
    }
    
    private static void UpdateVisibility() {
    	columnVisibility[0] = vehicleIDColItem.getSelection();
    	columnVisibility[1] = vehicleTypeColItem.getSelection();
    	columnVisibility[2] = vehicleModelColItem.getSelection();
    	columnVisibility[3] = vehicleLicensePlateColItem.getSelection();
    	columnVisibility[4] = vehicleFuelEconColItem.getSelection();
    	columnVisibility[5] = vehicleKMColItem.getSelection();
    	columnVisibility[6] = vehiclePolicyColItem.getSelection();
    	columnVisibility[7] = vehicleInsTypeColItem.getSelection();
    	columnVisibility[8] = vehicleKMLastServiceColItem.getSelection();
    	columnVisibility[9] = vehicleDateLastServicedColItem.getSelection();
    	columnVisibility[10] = vehicleManufacturerColItem.getSelection();
    	columnVisibility[11] = vehicleOperationalColItem.getSelection();
    	columnVisibility[12] = vehicleRoadWorthyColItem.getSelection();
    	columnVisibility[13] = vehicleYearColItem.getSelection();
    }
    
    private static void PackColumns() {
    	UpdateVisibility();
    	
        for (int i = 0; i < TABLE_COL_COUNT; i++) {
        	if (columnVisibility[i]) {
        		tableCols[i].pack();
        		tableCols[i].setResizable(true);
        	}
        	else {
        		tableCols[i].setWidth(0);
        		tableCols[i].setResizable(false);
        	}
        }
    }
    
    private static void CreateColumns() {
        vehicleIDCol = new TableColumn(dataTable, SWT.BORDER);
        vehicleIDCol.setText(columnHeaders[0]);
        vehicleIDCol.setWidth(DEFAULT_TABLE_COL_WIDTH);
        vehicleIDCol.setMoveable(true);
        vehicleIDCol.setResizable(true);

        vehicleTypeCol = new TableColumn(dataTable, SWT.BORDER);
        vehicleTypeCol.setText(columnHeaders[1]);
        vehicleTypeCol.setWidth(DEFAULT_TABLE_COL_WIDTH);
        vehicleTypeCol.setMoveable(true);
        vehicleTypeCol.setResizable(true);

        vehicleManufacturerCol = new TableColumn(dataTable, SWT.BORDER);
        vehicleManufacturerCol.setText(columnHeaders[2]);
        vehicleManufacturerCol.setWidth(DEFAULT_TABLE_COL_WIDTH);
        vehicleManufacturerCol.setMoveable(true);
        vehicleManufacturerCol.setResizable(true);

        vehicleModelCol = new TableColumn(dataTable, SWT.BORDER);
        vehicleModelCol.setText(columnHeaders[3]);
        vehicleModelCol.setWidth(DEFAULT_TABLE_COL_WIDTH);
        vehicleModelCol.setMoveable(true);
        vehicleModelCol.setResizable(true);

        vehicleYearCol = new TableColumn(dataTable, SWT.BORDER);
        vehicleYearCol.setText(columnHeaders[4]);
        vehicleYearCol.setWidth(DEFAULT_TABLE_COL_WIDTH);
        vehicleYearCol.setMoveable(true);
        vehicleYearCol.setResizable(true);

        vehicleKMCol = new TableColumn(dataTable, SWT.BORDER);
        vehicleKMCol.setText(columnHeaders[5]);
        vehicleKMCol.setWidth(DEFAULT_TABLE_COL_WIDTH);
        vehicleKMCol.setMoveable(true);
        vehicleKMCol.setResizable(true);

        vehicleKMLastServiceCol = new TableColumn(dataTable, SWT.BORDER);
        vehicleKMLastServiceCol.setText(columnHeaders[6]);
        vehicleKMLastServiceCol.setWidth(DEFAULT_TABLE_COL_WIDTH);
        vehicleKMLastServiceCol.setMoveable(true);
        vehicleKMLastServiceCol.setResizable(true);

        vehicleDateLastServicedCol = new TableColumn(dataTable, SWT.BORDER);
        vehicleDateLastServicedCol.setText(columnHeaders[7]);
        vehicleDateLastServicedCol.setWidth(DEFAULT_TABLE_COL_WIDTH);
        vehicleDateLastServicedCol.setMoveable(true);
        vehicleDateLastServicedCol.setResizable(true);
        
        vehicleRoadWorthyCol = new TableColumn(dataTable, SWT.BORDER);
        vehicleRoadWorthyCol.setText(columnHeaders[8]);
        vehicleRoadWorthyCol.setWidth(DEFAULT_TABLE_COL_WIDTH);
        vehicleRoadWorthyCol.setMoveable(true);
        vehicleRoadWorthyCol.setResizable(true);

        vehicleLicensePlateCol = new TableColumn(dataTable, SWT.BORDER);
        vehicleLicensePlateCol.setText(columnHeaders[9]);
        vehicleLicensePlateCol.setWidth(DEFAULT_TABLE_COL_WIDTH);
        vehicleLicensePlateCol.setMoveable(true);
        vehicleLicensePlateCol.setResizable(true);

        vehiclePolicyCol = new TableColumn(dataTable, SWT.BORDER);
        vehiclePolicyCol.setText(columnHeaders[10]);
        vehiclePolicyCol.setWidth(DEFAULT_TABLE_COL_WIDTH);
        vehiclePolicyCol.setMoveable(true);
        vehiclePolicyCol.setResizable(true);

        vehicleInsTypeCol = new TableColumn(dataTable, SWT.BORDER);
        vehicleInsTypeCol.setText(columnHeaders[11]);
        vehicleInsTypeCol.setWidth(DEFAULT_TABLE_COL_WIDTH);
        vehicleInsTypeCol.setMoveable(true);
        vehicleInsTypeCol.setResizable(true);

        vehicleOperationalCol = new TableColumn(dataTable, SWT.BORDER);
        vehicleOperationalCol.setText(columnHeaders[12]);
        vehicleOperationalCol.setWidth(DEFAULT_TABLE_COL_WIDTH);
        vehicleOperationalCol.setMoveable(true);
        vehicleOperationalCol.setResizable(true);

        vehicleFuelEconCol = new TableColumn(dataTable, SWT.BORDER);
        vehicleFuelEconCol.setText(columnHeaders[13]);
        vehicleFuelEconCol.setWidth(DEFAULT_TABLE_COL_WIDTH);
        vehicleFuelEconCol.setMoveable(true);
        vehicleFuelEconCol.setResizable(true);
        
    	// avoids null pointer exceptions in packing
    	FillColArray();
    	
    	// resize the columns to fit the header text
        PackColumns();
    } 
    
    private static void openDailyTasks(){
    	accessVehicle = new AccessVehicle();
        
        ArrayList<Vehicle> list;
        list = accessVehicle.getAllVehicles();
        DailyTasks tasks = new DailyTasks();
        tasks.open(list);
    }

    private static void Open() {
        mainWindow.setLayout(mainLayout);
        mainWindow.pack();

        mainWindow.open();
        
        openDailyTasks();

        while (!mainWindow.isDisposed()) {
            try {
                if (!currDisplay.readAndDispatch()) {
                    currDisplay.sleep();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                System.out.println(e.toString());
            }
        }
    }

    private static class FileExitListener implements SelectionListener {
        public void widgetDefaultSelected(SelectionEvent event) {
            mainWindow.close();
            currDisplay.dispose();
        }

        public void widgetSelected(SelectionEvent event) {
            mainWindow.close();
            currDisplay.dispose();
        }
    }
}
