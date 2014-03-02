package presentation;

import objects.Vehicle;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import businessLogic.CMMSInterface;

public class ViewVehicle implements CMMSInterface {
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;
    private final int MIN_WINDOW_WIDTH = 200;
    private final int MIN_WINDOW_HEIGHT = 200;

    private Display currDisplay;
    private Shell viewWindow;
    private GridLayout mainLayout;
    private Button closeButton;

    private StyledText textDisplay;

    private int currTextLocation = 0;

    private Vehicle[] vehicleList = null;

    /* Passing in one vehicle */
    public void open(Vehicle v) {
        currDisplay = Display.getDefault();
        vehicleList = new Vehicle[1];
        vehicleList[0] = v;

        CreateControls();
        FillHandler();

        viewWindow.open();

        while (!viewWindow.isDisposed()) {
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

    /* Passing in multiple vehicles */
    public void open(Vehicle[] v) {
        currDisplay = Display.getDefault();
        vehicleList = v;

        CreateControls();
        FillHandler();

        viewWindow.open();

        while (!viewWindow.isDisposed()) {
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

    private void FillHandler() {
        for (int i = 0; i < vehicleList.length; i++)
            FillText(i);
    }

    private void FillText(int vehicleSel) {
        String[] info = vehicleList[vehicleSel].ToStrings();

        String hdr = info[VEHICLE_FIELDS.ID.ordinal()] + ": "
                + info[VEHICLE_FIELDS.MANUFACTURER.ordinal()] + " "
                + info[VEHICLE_FIELDS.MODEL.ordinal()];

        String body = "\n\tType: " + info[VEHICLE_FIELDS.TYPE.ordinal()]
                + "\n\tYear: " + info[VEHICLE_FIELDS.YEAR.ordinal()]
                + "\n\tKilometers Driven: "
                + info[VEHICLE_FIELDS.KM_DRIVEN.ordinal()]
                + "\n\tLast Service (KM): "
                + info[VEHICLE_FIELDS.KM_LAST_SERVICE.ordinal()]
                + "\n\tRoad Worthy: "
                + info[VEHICLE_FIELDS.ROADWORTHY.ordinal()]
                + "\n\tLicense Plate Number: "
                + info[VEHICLE_FIELDS.LICENSE_PLATE.ordinal()]
                + "\n\tInsurance Policy Number: "
                + info[VEHICLE_FIELDS.POLICY_NUMBER.ordinal()]
                + "\n\tInsurance Policy Type: "
                + info[VEHICLE_FIELDS.POLICY_TYPE.ordinal()]
                + "\n\tOperational: "
                + info[VEHICLE_FIELDS.OPERATIONAL.ordinal()]
                + "\n\tFuel Economy: "
                + info[VEHICLE_FIELDS.FUEL_ECON.ordinal()] + " L/100Km";

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

        String parts = vehicleList[vehicleSel].getPartsList().toString()
                + "\n\n";

        textStyle = new StyleRange();
        textStyle.start = currTextLocation;
        textStyle.length = partsBoldRange;
        textStyle.fontStyle = SWT.BOLD;

        textDisplay.append(partsHdr);
        textDisplay.append(parts);
        textDisplay.setStyleRange(textStyle);

        currTextLocation += partsHdr.length() + parts.length();
    }

    private void CreateControls() {
        mainLayout = new GridLayout();

        viewWindow = new Shell();
        viewWindow.setLayout(mainLayout);
        viewWindow.setMinimumSize(MIN_WINDOW_WIDTH, MIN_WINDOW_HEIGHT);
        viewWindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        
        textDisplay = new StyledText(viewWindow, SWT.BORDER | SWT.V_SCROLL
                | SWT.H_SCROLL | SWT.READ_ONLY);
        GridData gd = new GridData();
        gd.horizontalAlignment = SWT.FILL;
        gd.verticalAlignment = SWT.FILL;
        gd.grabExcessHorizontalSpace = true;
        gd.grabExcessVerticalSpace = true;
        textDisplay.setLayoutData(gd);
        
        closeButton = new Button(viewWindow,SWT.NONE);
        closeButton.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		viewWindow.close();
        	}
        });
        gd = new GridData();
        gd.horizontalAlignment = SWT.RIGHT;
        closeButton.setText("Close");
        closeButton.setLayoutData(gd);
    }
}