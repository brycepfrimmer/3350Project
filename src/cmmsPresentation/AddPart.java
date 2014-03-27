package cmmsPresentation;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

import acceptanceTests.Register; 
import acceptanceTests.EventLoop;

import cmmsBusiness.AccessVehicle;
import cmmsObjects.Vehicle.Vehicle;

public class AddPart {
    protected Shell shlAddPart;
	
    protected Shell shell;
    private Text textPart;

    private Vehicle currVehicle;
    private Button btnCancel;
    private Label lblPart;
    private Label lblAddWarning;
    
    private AccessVehicle accessVehicle;

    /**
     * Open the window.
     */
    public void open(Vehicle v) {
        Display display = Display.getDefault();
        createContents();
        Register.newWindow(this);
        accessVehicle = new AccessVehicle();
        currVehicle = v;
        shell.open();
        shell.layout();
        if (EventLoop.isEnabled()) 
        { 
	        while (!shell.isDisposed()) {
	            if (!display.readAndDispatch()) {
	                display.sleep();
	            }
	        }
        }
    }

    /**
     * Create contents of the window.
     * 
     * @wbp.parser.entryPoint
     */
    protected void createContents() {
        shell = new Shell();
        shell.setText("Add Part");
        shell.setSize(313, 168);

        textPart = new Text(shell, SWT.BORDER);
        textPart.setBounds(72, 31, 161, 21);

        Button btnAddPart = new Button(shell, SWT.NONE);
        btnAddPart.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (SetFields()) {
                    shell.close();
                } else {
                    lblAddWarning
                            .setText("That part already exists in the parts list");
                    lblAddWarning.pack();
                }
            }
        });
        btnAddPart.setBounds(22, 86, 58, 25);
        btnAddPart.setText("Add Part");

        btnCancel = new Button(shell, SWT.NONE);
        btnCancel.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                shell.close();
            }
        });
        btnCancel.setBounds(86, 86, 75, 25);
        btnCancel.setText("Cancel");

        lblPart = new Label(shell, SWT.NONE);
        lblPart.setBounds(10, 34, 56, 15);
        lblPart.setText("Part Name");

        lblAddWarning = new Label(shell, SWT.NONE);
        lblAddWarning.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
        lblAddWarning.setBounds(72, 58, 161, 15);
    }

    private boolean SetFields() {
    	String part = textPart.getText();
    	boolean isValid = !currVehicle.searchPartsList(part);
    	if(isValid) {
    		accessVehicle.addPart(currVehicle.getID(), part);
    	}
        return isValid;
    }

}
