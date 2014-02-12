/****
 * Main window for the CMMS
 * 
 * Contributions
 * Darwin - 1.5hours - February 8, 2014
 * Cody -  couple minutes - February 9, 2014
 * Darwin - 0.5 hours - February 11, 2014
 */
package CMMS;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.events.SelectionAdapter;
import java.util.ArrayList;

public class CMMS {
	/* Interface Constants */
	private static final Point MIN_WINDOW_SIZE = new Point(800,600);
	private static final String WINDOW_TITLE = "Computerized Maintenance Management System";
	private static final int LAYOUT_COL_COUNT = 3;
	private static final int DEFAULT_TABLE_COL_WIDTH = 100; 
	
	private static int currentVehicleCount = 0;
	
	private static Display currDisplay;
	private static Shell mainWindow;
	private static GridLayout mainLayout;
	private static GridData gridData;
	
	private static Menu mainMenu;
	private static Menu fileMenu;
	private static Menu vehicleMenu;
	private static Menu helpMenu;
	
	private static MenuItem fileMenuHdr;
	private static MenuItem vehicleMenuHdr;
	private static MenuItem helpMenuHdr;
	
	private static MenuItem filePrintItem;
	private static MenuItem fileExitItem;
	private static MenuItem vehicleAddItem;
	private static MenuItem vehicleRemoveItem;
	private static MenuItem vehicleViewItem;
	private static MenuItem helpAboutItem;
	
	private static Label searchLabel;
	
	private static Button searchButton;
	private static Button addVehicleButton;
	private static Button removeVehicleButton;
	private static Button viewVehicleButton;
	
	private static Text searchText;
	
	private static Table dataTable;
	private static TableColumn vehicleIDCol;
	private static TableColumn vehicleTypeCol;
	private static TableColumn vehicleModelCol;
	private static TableColumn vehicleLicensePlateCol;
	private static TableColumn vehicleFuelEconCol;
	private static TableColumn vehicleKMCol;
	private static TableColumn vehicleInsuranceCol;
	private static TableColumn vehicleKMLastServiceCol;
	private static TableColumn vehicleManufacturerCol;
	private static TableColumn vehicleOperationalCol;
	private static TableColumn vehicleRoadWorthyCol;
	
	private static Interface dbInterface;
	
	public static void main(String[] args) {
		dbInterface = new Interface();
		CreateWindow();
		OnLoad();
		Open();
	}
	
	private static void OnLoad() {
		// Read existing database vehicles
	}

	private static void CreateWindow() {
		mainWindow = new Shell();
		mainWindow.setText(WINDOW_TITLE);
		mainWindow.setMinimumSize(MIN_WINDOW_SIZE);
		
		mainLayout = new GridLayout();
		mainLayout.numColumns = LAYOUT_COL_COUNT;
		
		CreateMenus();
		CreateControls();
		CreateColumns();
	}
	
	private static void CreateMenus() {
		mainMenu = new Menu(mainWindow, SWT.BAR);
		
		CreateFileMenu();
		CreateVehicleMenu();
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
	
	private static void CreateHelpMenu() {
		helpMenuHdr = new MenuItem(mainMenu, SWT.CASCADE);
		helpMenuHdr.setText("&Help");
		
		helpMenu = new Menu(mainWindow, SWT.DROP_DOWN);
		helpMenuHdr.setMenu(helpMenu);
		
		helpAboutItem = new MenuItem(helpMenu, SWT.PUSH);
		helpAboutItem.setText("A&bout");
	}
		
	private static void CreateControls() {
		searchLabel = new Label(mainWindow, SWT.NONE);
		searchLabel.setText("Search:");
		
		searchText = new Text(mainWindow, SWT.BORDER);
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		searchText.setLayoutData(gridData);
		searchText.setText("Search For...");
		
		searchButton = new Button(mainWindow, SWT.NONE);
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		searchButton.setLayoutData(gridData);
		searchButton.setText("Search");
		
		dataTable = new Table(mainWindow, SWT.BORDER);
		dataTable.setItemCount(currentVehicleCount);
		dataTable.setHeaderVisible(true);
		dataTable.setLinesVisible(true);
		gridData = new GridData();
		gridData.horizontalSpan = 2;
		gridData.verticalSpan = 3;
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
		gridData = new GridData();
		gridData.grabExcessVerticalSpace = false;
		gridData.horizontalAlignment = SWT.FILL;
		gridData.verticalAlignment = SWT.TOP;
		removeVehicleButton.setLayoutData(gridData);
		removeVehicleButton.setText("Remove Vehicle");
		
		viewVehicleButton = new Button(mainWindow, SWT.NONE);
		gridData = new GridData();
		gridData.grabExcessVerticalSpace = false;
		gridData.horizontalAlignment = SWT.FILL;
		gridData.verticalAlignment = SWT.TOP;
		viewVehicleButton.setLayoutData(gridData);
		viewVehicleButton.setText("View Vehicle");	
	}
	
	private static void UpdateList() {
		TableItem ti; // Table item for adding vehicles to the table
		ArrayList<Vehicle> list = dbInterface.getVehicles();
		
		// Stop Drawing Table, Empty Table, Rebuild Table, Start Drawing Table
		dataTable.setRedraw(false);
		
		dataTable.remove(0, dataTable.getItemCount()-1);
		for( Vehicle v : list) {
			ti = new TableItem(dataTable, SWT.NONE);
			//ti.setText(v.ToString());
		}
		
		dataTable.setRedraw(true);
	}

	private static void CreateColumns() {
		vehicleIDCol = new TableColumn(dataTable, SWT.BORDER);
		vehicleIDCol.setText("ID");
		vehicleIDCol.setWidth(DEFAULT_TABLE_COL_WIDTH);
		vehicleIDCol.setMoveable(true);
		vehicleIDCol.setResizable(true);
		
		vehicleTypeCol = new TableColumn(dataTable, SWT.BORDER);
		vehicleTypeCol.setText("Type");
		vehicleTypeCol.setWidth(DEFAULT_TABLE_COL_WIDTH);
		vehicleTypeCol.setMoveable(true);
		vehicleTypeCol.setResizable(true);
		
		vehicleModelCol = new TableColumn(dataTable, SWT.BORDER);
		vehicleModelCol.setText("Model");
		vehicleModelCol.setWidth(DEFAULT_TABLE_COL_WIDTH);
		vehicleModelCol.setMoveable(true);
		vehicleModelCol.setResizable(true);
		
		vehicleLicensePlateCol = new TableColumn(dataTable, SWT.BORDER);
		vehicleLicensePlateCol.setText("License Plate");
		vehicleLicensePlateCol.setWidth(DEFAULT_TABLE_COL_WIDTH);
		vehicleLicensePlateCol.setMoveable(true);
		vehicleLicensePlateCol.setResizable(true);
		
		vehicleFuelEconCol = new TableColumn(dataTable, SWT.BORDER);
		vehicleFuelEconCol.setText("Fuel Economy");
		vehicleFuelEconCol.setWidth(DEFAULT_TABLE_COL_WIDTH);
		vehicleFuelEconCol.setMoveable(true);
		vehicleFuelEconCol.setResizable(true);
		
		vehicleKMCol = new TableColumn(dataTable, SWT.BORDER);
		vehicleKMCol.setText("Kilometers");
		vehicleKMCol.setWidth(DEFAULT_TABLE_COL_WIDTH);
		vehicleKMCol.setMoveable(true);
		vehicleKMCol.setResizable(true);
		
		vehicleInsuranceCol = new TableColumn(dataTable, SWT.BORDER);
		vehicleInsuranceCol.setText("Insurance Policy");
		vehicleInsuranceCol.setWidth(DEFAULT_TABLE_COL_WIDTH);
		vehicleInsuranceCol.setMoveable(true);
		vehicleInsuranceCol.setResizable(true);
		
		vehicleKMLastServiceCol = new TableColumn(dataTable, SWT.BORDER);
		vehicleKMLastServiceCol.setText("Last service (KM)");
		vehicleKMLastServiceCol.setWidth(DEFAULT_TABLE_COL_WIDTH);
		vehicleKMLastServiceCol.setMoveable(true);
		vehicleKMLastServiceCol.setResizable(true);
		
		vehicleManufacturerCol = new TableColumn(dataTable, SWT.BORDER);
		vehicleManufacturerCol.setText("Manufacturer");
		vehicleManufacturerCol.setWidth(DEFAULT_TABLE_COL_WIDTH);
		vehicleManufacturerCol.setMoveable(true);
		vehicleManufacturerCol.setResizable(true);
		
		vehicleOperationalCol = new TableColumn(dataTable, SWT.BORDER);
	 	vehicleOperationalCol.setText("Is Operational");
	 	vehicleOperationalCol.setWidth(DEFAULT_TABLE_COL_WIDTH);
		vehicleOperationalCol.setMoveable(true);
		vehicleOperationalCol.setResizable(true);
		
		vehicleRoadWorthyCol = new TableColumn(dataTable, SWT.BORDER);
		vehicleRoadWorthyCol.setText("Is Roadworthy");
		vehicleRoadWorthyCol.setWidth(DEFAULT_TABLE_COL_WIDTH);
		vehicleRoadWorthyCol.setMoveable(true);
		vehicleRoadWorthyCol.setResizable(true);
	}

	private static void Open() {
		currDisplay = Display.getDefault();
		
		mainWindow.setLayout(mainLayout);
		mainWindow.pack();
		mainWindow.open();
		
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
