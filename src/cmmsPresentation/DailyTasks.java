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

import cmmsObjects.Vehicle.Vehicle;

import acceptanceTests.Register; 
import acceptanceTests.EventLoop;

public class DailyTasks {
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;
    private final int MIN_WINDOW_WIDTH = 200;
    private final int MIN_WINDOW_HEIGHT = 200;

    private Display display;
    private Shell shell;
    private GridLayout mainLayout;
    private Button closeButton;

    private StyledText textDisplay;

    private int currTextLocation = 0;
    private int count = 0;

    private ArrayList<Vehicle> vehicleList = null;


    public void open(ArrayList<Vehicle> v) {
        display = Display.getDefault();
        Register.newWindow(this);
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
        else if(vehicleSel == 0){
            hdr = info.getID() + ": "
                    + info.getManufacturer() + " "
                    + info.getModel();
        }
        else {
        	 hdr = "\n" + info.getID() + ": "
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

        shell = new Shell();
        shell.setText("Today's Tasks");
        shell.setLayout(mainLayout);
        shell.setMinimumSize(MIN_WINDOW_WIDTH, MIN_WINDOW_HEIGHT);
        shell.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        
        textDisplay = new StyledText(shell, SWT.BORDER | SWT.V_SCROLL
                | SWT.H_SCROLL | SWT.READ_ONLY);
        GridData gd = new GridData();
        gd.horizontalAlignment = SWT.FILL;
        gd.verticalAlignment = SWT.FILL;
        gd.grabExcessHorizontalSpace = true;
        gd.grabExcessVerticalSpace = true;
        textDisplay.setLayoutData(gd);
        
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
}