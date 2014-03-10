package cmmsPresentation;

import java.util.ArrayList;



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
import java.util.ArrayList;

import cmmsBusiness.VehicleFields;
import cmmsObjects.Part;
import cmmsObjects.Vehicle;


public class DailyTasks {
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;
    private final int MIN_WINDOW_WIDTH = 200;
    private final int MIN_WINDOW_HEIGHT = 200;

    private Display currDisplay;
    private Shell tasksWindow;
    private GridLayout mainLayout;
    private Button closeButton;

    private StyledText textDisplay;

    private int currTextLocation = 0;
    private int count = 0;

    private ArrayList<Vehicle> vehicleList = null;


    public void open(ArrayList<Vehicle> v) {
        currDisplay = Display.getDefault();
        vehicleList = v;

        CreateControls();
        FillHandler();

        tasksWindow.open();

        while (!tasksWindow.isDisposed()) {
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
        for (int i = 0; i < vehicleList.size(); i++)
            FillText(i);
    }

    private void FillText(int vehicleSel) {
        Vehicle info = vehicleList.get(vehicleSel);
        String hdr;


        String body = info.getTodaysTasks();
        
        if(body == "") {
        	hdr = "";
        	count++;
        }
        else {
            hdr = info.getID() + ": "
                    + info.getManufacturer() + " "
                    + info.getModel();
        }
        
        if(count == vehicleList.size()) {
        	body = "There are no tasks today";
        }

        int hdrBoldRange = hdr.length();

        StyleRange textStyle = new StyleRange();
        textStyle.start = currTextLocation;
        textStyle.length = hdrBoldRange;
        textStyle.fontStyle = SWT.BOLD;

        textDisplay.append(hdr);
        textDisplay.append(body);
        textDisplay.setStyleRange(textStyle);

        currTextLocation += hdr.length() + body.length();
    }

    private void CreateControls() {
        mainLayout = new GridLayout();

        tasksWindow = new Shell();
        tasksWindow.setText("Today's Tasks");
        tasksWindow.setLayout(mainLayout);
        tasksWindow.setMinimumSize(MIN_WINDOW_WIDTH, MIN_WINDOW_HEIGHT);
        tasksWindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        
        textDisplay = new StyledText(tasksWindow, SWT.BORDER | SWT.V_SCROLL
                | SWT.H_SCROLL | SWT.READ_ONLY);
        GridData gd = new GridData();
        gd.horizontalAlignment = SWT.FILL;
        gd.verticalAlignment = SWT.FILL;
        gd.grabExcessHorizontalSpace = true;
        gd.grabExcessVerticalSpace = true;
        textDisplay.setLayoutData(gd);
        
        closeButton = new Button(tasksWindow,SWT.NONE);
        closeButton.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		tasksWindow.close();
        	}
        });
        gd = new GridData();
        gd.horizontalAlignment = SWT.RIGHT;
        closeButton.setText("Close");
        closeButton.setLayoutData(gd);
    }
}