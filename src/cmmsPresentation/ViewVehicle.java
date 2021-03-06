package cmmsPresentation;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import cmmsObjects.Part;
import cmmsObjects.Vehicle.Vehicle;
import cmmsObjects.Vehicle.VehicleFields;

import acceptanceTests.Register; 
import acceptanceTests.EventLoop;

public class ViewVehicle {
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;
    private final int MIN_WINDOW_WIDTH = 200;
    private final int MIN_WINDOW_HEIGHT = 200;

    private Display display;
    private Shell shell;
    private GridLayout mainLayout;
    private Button closeButton;
    private Button btnPrint;
    
    private PrintDialog printDialog;
    private PrinterData printerData;
    private Printer printer;


    private StyledText textDisplay;


    private int currTextLocation = 0;

    private Vehicle[] vehicleList = null;
    

    /* Passing in one vehicle */
    public void open(Vehicle v) {
        display = Display.getDefault();
        Register.newWindow(this);
        vehicleList = new Vehicle[1];
        vehicleList[0] = v;

        CreateControls();
        FillHandler();

        shell.open();
        if( EventLoop.isEnabled() )
        {
	        while (!shell.isDisposed()) {
	            try {
	                if (!display.readAndDispatch()) {
	                    display.sleep();
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	                System.out.println(e.getMessage());
	                System.out.println(e.toString());
	            }
	        }
        }
    }

    /* Passing in multiple vehicles */
    public void open(Vehicle[] v) {
        display = Display.getDefault();
        vehicleList = v;

        CreateControls();
        FillHandler();

        shell.open();
        
        if( EventLoop.isEnabled() )
        {
	        while (!shell.isDisposed()) {
	            try {
	                if (!display.readAndDispatch()) {
	                    display.sleep();
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	                System.out.println(e.getMessage());
	                System.out.println(e.toString());
	            }
	        }
        }
    }

    private void FillHandler() {
        for (int i = 0; i < vehicleList.length; i++)
            FillText(i);
    }

    private void FillText(int vehicleSel) {
        String[] info = vehicleList[vehicleSel].ToStrings();

        String hdr = info[VehicleFields.ID.ordinal()] + ": "
                + info[VehicleFields.MANUFACTURER.ordinal()] + " "
                + info[VehicleFields.MODEL.ordinal()];

        String body = "\n\tType: " + info[VehicleFields.TYPE.ordinal()]
                + "\n\tYear: " + info[VehicleFields.YEAR.ordinal()]
                + "\n\tKilometers Driven: "
                + info[VehicleFields.KM_DRIVEN.ordinal()]
                + "\n\tLast Service (KM): "
                + info[VehicleFields.KM_LAST_SERVICE.ordinal()]
                + "\n\tRoad Worthy: "
                + info[VehicleFields.ROADWORTHY.ordinal()]
                + "\n\tLicense Plate Number: "
                + info[VehicleFields.LICENSE_PLATE.ordinal()]
                + "\n\tInsurance Policy Number: "
                + info[VehicleFields.POLICY_NUMBER.ordinal()]
                + "\n\tInsurance Policy Type: "
                + info[VehicleFields.POLICY_TYPE.ordinal()]
                + "\n\tOperational: "
                + info[VehicleFields.OPERATIONAL.ordinal()]
                + "\n\tFuel Economy: "
                + info[VehicleFields.FUEL_ECON.ordinal()] + " L/100Km";

        int hdrBoldRange = hdr.length();

        StyleRange textStyle = new StyleRange();
        textStyle.start = currTextLocation;
        textStyle.length = hdrBoldRange;
        textStyle.fontStyle = SWT.BOLD;

        textDisplay.append(hdr);
        textDisplay.append(body);
        textDisplay.setStyleRange(textStyle);

        currTextLocation += hdr.length() + body.length();

        String partsHdr = "\n\t\tPARTS";
        int partsBoldRange = partsHdr.length();

        String parts = "";
        ArrayList<Part> pList = vehicleList[vehicleSel].getPartsList();
        for (int i = 0; i < pList.size(); i++) {
            parts += pList.get(i).toString();
        }
        parts += "\n\n";

        textStyle = new StyleRange();
        textStyle.start = currTextLocation;
        textStyle.length = partsBoldRange;
        textStyle.fontStyle = SWT.BOLD;

        textDisplay.append(partsHdr);
        textDisplay.append(parts);
        textDisplay.setStyleRange(textStyle);

        currTextLocation += partsHdr.length() + parts.length();
    }

    /**
     * @wbp.parser.entryPoint
     */
    private void CreateControls() {
        mainLayout = new GridLayout();
        mainLayout.numColumns = 2;

        shell = new Shell();
        shell.setLayout(mainLayout);
        shell.setMinimumSize(MIN_WINDOW_WIDTH, MIN_WINDOW_HEIGHT);
        shell.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        shell.setText("View Vehicle");
        
        textDisplay = new StyledText(shell, SWT.BORDER | SWT.V_SCROLL
                | SWT.H_SCROLL | SWT.READ_ONLY);
        GridData gd = new GridData();
        gd.horizontalSpan = 2;
        gd.horizontalAlignment = SWT.FILL;
        gd.verticalAlignment = SWT.FILL;
        gd.grabExcessHorizontalSpace = true;
        gd.grabExcessVerticalSpace = true;
        textDisplay.setLayoutData(gd);
        
        btnPrint = new Button(shell, SWT.NONE);
        btnPrint.setText("Print");
        btnPrint.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		printVehicle();
        	}
        });
        
        closeButton = new Button(shell,SWT.NONE);
        closeButton.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		shell.close();
        	}
        });
        gd = new GridData();
        gd.horizontalAlignment = SWT.RIGHT;
        closeButton.setText("Close");
        closeButton.setLayoutData(gd);
    }
    
    private void printVehicle() {
    	printDialog = new PrintDialog(shell, SWT.NONE);
    	printerData = printDialog.open();
    	printer = new Printer(printerData);
    	
    	if(printerData != null) {
	    	if(printerData.printToFile) {
	    		printerData.fileName = "print.out";
	    	}
	    	
	    	if(printer != null) {
	    	
	    		textDisplay.print(printer).run();
	    	}
	    	
	    	printer.dispose();
    	}
    }
    
}