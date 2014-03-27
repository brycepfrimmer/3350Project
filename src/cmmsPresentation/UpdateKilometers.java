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

import cmmsBusiness.AccessVehicle;
import cmmsObjects.Vehicle.Vehicle;

import acceptanceTests.Register; 
import acceptanceTests.EventLoop;

public class UpdateKilometers {

    protected Shell shell;
    private Text textKms;
    private Label lblKmsWarning;

    private Vehicle currVehicle;
    private Text textFuelUsage;
    private Label lblFuelUsageWarning;
    private Button btnCancel;
    
    private Button btnUpdateKms;
    
    private AccessVehicle accessVehicle;

    /**
     * Open the window.
     */
    public void open(Vehicle v) {
        Display display = Display.getDefault();
        Register.newWindow(this);
        createContents();
        currVehicle = v;
        FillFields();
        shell.open();
        shell.layout();
        if( EventLoop.isEnabled() )
        {
	        while (!shell.isDisposed()) {
	            if (!display.readAndDispatch()) {
	                display.sleep();
	            }
	        }
        }
    }

    // Fill all the controls with the fields from the
    // vehicle we were passed in
    private void FillFields() {
        textKms.setText(Integer.toString(currVehicle.getKmDriven()));
    }

    /**
     * Create contents of the window.
     * 
     * @wbp.parser.entryPoint
     */
    protected void createContents() {
        shell = new Shell();
        shell.setText("Update Kilometers");
        shell.setSize(508, 168);

        Label lblKilometers = new Label(shell, SWT.NONE);
        lblKilometers.setBounds(10, 10, 55, 15);
        lblKilometers.setText("Kilometers");

        textKms = new Text(shell, SWT.BORDER);
        textKms.setBounds(81, 7, 76, 21);

        lblKmsWarning = new Label(shell, SWT.NONE);
        lblKmsWarning.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
        lblKmsWarning.setBounds(157, 7, 279, 21);

        textFuelUsage = new Text(shell, SWT.BORDER);
        textFuelUsage.setBounds(81, 39, 76, 21);

        Label lblFuelUsage = new Label(shell, SWT.NONE);
        lblFuelUsage.setBounds(10, 42, 68, 15);
        lblFuelUsage.setText("Fuel Used (L)");

        btnUpdateKms = new Button(shell, SWT.NONE);
        btnUpdateKms.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                boolean good = checkFields();
                if (good) {
                    SetFields();

                    shell.close();
                } else {
                    // display message explaining to user what is wrong with
                    // their input
                }
            }
        });
        btnUpdateKms.setBounds(22, 86, 109, 25);
        btnUpdateKms.setText("Update Kilometers");

        lblFuelUsageWarning = new Label(shell, SWT.NONE);
        lblFuelUsageWarning.setForeground(SWTResourceManager
                .getColor(SWT.COLOR_RED));
        lblFuelUsageWarning.setBounds(163, 42, 244, 15);

        btnCancel = new Button(shell, SWT.NONE);
        btnCancel.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                shell.close();
            }
        });
        btnCancel.setBounds(157, 86, 75, 25);
        btnCancel.setText("Cancel");
    }

    private void SetFields() {
    	accessVehicle = new AccessVehicle();
    	accessVehicle.updateKm( currVehicle.getID(), new Integer(textKms.getText()), new Double(
                textFuelUsage.getText()) );
        //currVehicle.updateKm(new Integer(textKms.getText()), new Double(
        //        textFuelUsage.getText()));
    }

    private boolean checkFields() {
        // perform checks on all the fields to make sure they are good
        boolean fieldsOkay = false;
        fieldsOkay = checkKms() && checkFuelUsage();
        return fieldsOkay;
    }

    private boolean checkKms() {
        boolean isValid = false;
        String input = textKms.getText();
        isValid = input.matches("[0-9]+") && input.matches("[0-9]*");
        if (!isValid) {
            lblKmsWarning
                    .setText("Kilometers can only be expressed by numbers");
        } else if (new Integer(input) <= currVehicle.getKmDriven()) {
            lblKmsWarning
                    .setText("Updated kilometers must be more than previous kilometers");
        } else {
            lblKmsWarning.setText("");
        }
        lblKmsWarning.pack();
        return isValid;
    }

    private boolean checkFuelUsage() {
        boolean isValid = false;
        String input = textFuelUsage.getText();
        isValid = input.matches("[0-9]+") && input.matches("[0-9]*");
        if (!isValid) {
            lblFuelUsageWarning
                    .setText("Fuel usage serviced can only be expressed by numbers");
        } else {
            lblFuelUsageWarning.setText("");
        }
        lblFuelUsageWarning.pack();
        return isValid;
    }
}
