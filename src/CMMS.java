/****
 * Main window for the CMMS
 * 
 * Contributions
 * Darwin - 0.5hours - February 8, 2014
 * 5:30am - 
 * Cody -  couple minutes - February 9, 2014
 */

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
import org.eclipse.swt.events.SelectionAdapter;
import java.util.ArrayList;

public class CMMS {
	/* Interface Constants */
	private static final Point MIN_WINDOW_SIZE = new Point(800,600);
	private static final String WINDOW_TITLE = "Computerized Maintenance Management System";
	private static final int COL_COUNT = 3;
	private static final int MAX_VEHICLES = 1024;
	
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
	
	private static Interface dbInterface;
	
	public static void main(String[] args) {		
		dbInterface = new Interface();
		CreateWindow();
		Open();
	}
	
	private static void CreateWindow() {
		mainWindow = new Shell();
		mainWindow.setText(WINDOW_TITLE);
		mainWindow.setMinimumSize(MIN_WINDOW_SIZE);
		
		mainLayout = new GridLayout();
		mainLayout.numColumns = COL_COUNT;
		
		CreateMenus();
		CreateControls();
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
		gridData = new GridData();
		gridData.horizontalSpan = 2;
		gridData.verticalSpan = 3;
		gridData.heightHint = (MIN_WINDOW_SIZE.y / 2);
		gridData.horizontalAlignment = SWT.FILL;
		gridData.verticalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		dataTable.setLayoutData(gridData);
		dataTable.setItemCount(MAX_VEHICLES);
		
		addVehicleButton = new Button(mainWindow, SWT.NONE);
		addVehicleButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddVehicle addWindow = new AddVehicle();
				addWindow.open();
				//want to know when the add window is done so that we can update the list of vehicles
				//addWindow.open() will wait until we need it to HOW DO WE ADD TO THE dataTable?!?!?!
				ArrayList<Vehicle> list = dbInterface.getVehicles();
				TableItem newItem = null;
				for (int z = 0; z < list.size(); z++) {
					newItem = new TableItem(dataTable, 0);
					newItem.setText(list.get(z).getID());
				}
				System.out.println("DONE ADD");
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
	
	private static void Open() {
		currDisplay = Display.getDefault();
		
		mainWindow.setLayout(mainLayout);
		mainWindow.pack();
		mainWindow.open();
		
		while (!mainWindow.isDisposed()) {
			if (!currDisplay.readAndDispatch()) {
				currDisplay.sleep();
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
