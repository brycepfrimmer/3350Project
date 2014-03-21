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


public class UpdateKilometers {

    protected Shell shlUpdateKms;
    private Text textKms;
    private Label lblKmsWarning;

    private Vehicle currVehicle;
    private Text textFuelUsage;
    private Label lblFuelUsageWarning;
    private Button btnCancel;
    
    private AccessVehicle accessVehicle;

    /**
     * Open the window.
     */
    public void open(Vehicle v) {
        Display display = Display.getDefault();
        createContents();
        currVehicle = v;
        FillFields();
        shlUpdateKms.open();
        shlUpdateKms.layout();
        while (!shlUpdateKms.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
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
        shlUpdateKms = new Shell();
        shlUpdateKms.setText("Update Kilometers");
        shlUpdateKms.setSize(508, 168);

        Label lblKilometers = new Label(shlUpdateKms, SWT.NONE);
        lblKilometers.setBounds(10, 10, 55, 15);
        lblKilometers.setText("Kilometers");

        textKms = new Text(shlUpdateKms, SWT.BORDER);
        textKms.setBounds(81, 7, 76, 21);

        lblKmsWarning = new Label(shlUpdateKms, SWT.NONE);
        lblKmsWarning.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
        lblKmsWarning.setBounds(157, 7, 279, 21);

        textFuelUsage = new Text(shlUpdateKms, SWT.BORDER);
        textFuelUsage.setBounds(81, 39, 76, 21);

        Label lblFuelUsage = new Label(shlUpdateKms, SWT.NONE);
        lblFuelUsage.setBounds(10, 42, 68, 15);
        lblFuelUsage.setText("Fuel Used (L)");

        Button btnUpdateKms = new Button(shlUpdateKms, SWT.NONE);
        btnUpdateKms.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                boolean good = checkFields();
                if (good) {
                    SetFields();

                    shlUpdateKms.close();
                } else {
                    // display message explaining to user what is wrong with
                    // their input
                }
            }
        });
        btnUpdateKms.setBounds(22, 86, 109, 25);
        btnUpdateKms.setText("Update Kilometers");

        lblFuelUsageWarning = new Label(shlUpdateKms, SWT.NONE);
        lblFuelUsageWarning.setForeground(SWTResourceManager
                .getColor(SWT.COLOR_RED));
        lblFuelUsageWarning.setBounds(163, 42, 244, 15);

        btnCancel = new Button(shlUpdateKms, SWT.NONE);
        btnCancel.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                shlUpdateKms.close();
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
