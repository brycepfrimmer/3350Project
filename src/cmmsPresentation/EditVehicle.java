package cmmsPresentation;


import java.sql.Date;

import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

import cmmsBusiness.AccessVehicle;
import cmmsObjects.Vehicle;
import cmmsObjects.VehicleInfo;


public class EditVehicle {

    protected Shell shlEditVehicle;
    private Text textVehicleID;
    private Text textType;
    private Text textManufacturer;
    private Text textModel;
    private Text textYear;
    private Text textKms;
    private Text textKmsLS;
    private Text textLPN;
    private Text textInsPolNum;
    private Text textInsType;
    private Button btnOperational;
    private Button btnRoadworthy;
    private DateTime dateTime;
    
    private Text textVehicleIDWarning;
    private Text textTypeWarning;
    private Text textManufacturerWarning;
    private Text textModelWarning;
    private Text textYearWarning;
    private Text textKmsWarning;
    private Text textKmsLSWarning;
    private Label lblLPNWarning;
    private Text textInsPolNumWarning;
    private Text textInsTypeWarning;

    private Vehicle currVehicle;
    private Button btnEditPartsList;
    
    private VehicleInfo info;
    private AccessVehicle accessVehicle;

    /**
     * Open the window.
     */
    public void open(Vehicle v) {
        Display display = Display.getDefault();
        createContents();
        currVehicle = v;
        FillFields();
        shlEditVehicle.open();
        shlEditVehicle.layout();
        while (!shlEditVehicle.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

    // Fill all the controls with the fields from the
    // vehicle we were passed in
    private void FillFields() {
        textVehicleID.setText(currVehicle.getID());
        shlEditVehicle.setText("Edit " + currVehicle.getID());
        textType.setText(currVehicle.getType());
        textManufacturer.setText(currVehicle.getManufacturer());
        textModel.setText(currVehicle.getModel());
        textYear.setText(Integer.toString(currVehicle.getYear()));
        textKms.setText(Integer.toString(currVehicle.getKmDriven()));
        textKmsLS.setText(Integer.toString(currVehicle.getKmLastServiced()));
        btnRoadworthy.setSelection(currVehicle.isRoadWorthy());
        textLPN.setText(currVehicle.getLicensePlate());
        textInsPolNum.setText(currVehicle.getInsurance().getPolicyNum());
        textInsType.setText(currVehicle.getInsurance().getType());
        btnOperational.setSelection(currVehicle.isOperational());
        dateTime.setData(currVehicle.getDateLastServiced());
    }

    /**
     * Create contents of the window.
     * 
     * @wbp.parser.entryPoint
     */
    protected void createContents() {
        shlEditVehicle = new Shell();
        shlEditVehicle.setText("Edit Vehicle");
        shlEditVehicle.setSize(660, 485);

        Label lblVehicleId = new Label(shlEditVehicle, SWT.NONE);
        lblVehicleId.setBounds(10, 10, 55, 15);
        lblVehicleId.setText("Vehicle ID");

        textVehicleID = new Text(shlEditVehicle, SWT.BORDER);
        textVehicleID.setBounds(98, 7, 76, 21);

        Label lblType = new Label(shlEditVehicle, SWT.NONE);
        lblType.setBounds(10, 41, 55, 15);
        lblType.setText("Type");

        textType = new Text(shlEditVehicle, SWT.BORDER);
        textType.setBounds(98, 37, 76, 21);

        Label lblieCarTruck = new Label(shlEditVehicle, SWT.NONE);
        lblieCarTruck.setBounds(180, 41, 126, 15);
        lblieCarTruck.setText("(ie. Car, Truck, Forklift)");

        Label lblManufacturer = new Label(shlEditVehicle, SWT.NONE);
        lblManufacturer.setBounds(10, 72, 76, 15);
        lblManufacturer.setText("Manufacturer");

        textManufacturer = new Text(shlEditVehicle, SWT.BORDER);
        textManufacturer.setBounds(98, 66, 76, 21);

        Label lblModel = new Label(shlEditVehicle, SWT.NONE);
        lblModel.setBounds(10, 103, 55, 15);
        lblModel.setText("Model");

        textModel = new Text(shlEditVehicle, SWT.BORDER);
        textModel.setBounds(98, 97, 76, 21);

        Label lblYear = new Label(shlEditVehicle, SWT.NONE);
        lblYear.setBounds(10, 134, 55, 15);
        lblYear.setText("Year");

        textYear = new Text(shlEditVehicle, SWT.BORDER);
        textYear.setBounds(98, 128, 76, 21);

        Label lblKilometers = new Label(shlEditVehicle, SWT.NONE);
        lblKilometers.setBounds(10, 163, 55, 15);
        lblKilometers.setText("Kilometers");

        textKms = new Text(shlEditVehicle, SWT.BORDER);
        textKms.setBounds(98, 157, 76, 21);

        Label lblKilometers_1 = new Label(shlEditVehicle, SWT.NONE);
        lblKilometers_1.setBounds(10, 184, 76, 30);
        lblKilometers_1.setText("Kilometers\nLast Serviced");

        textKmsLS = new Text(shlEditVehicle, SWT.BORDER);
        textKmsLS.setBounds(98, 193, 76, 21);
        
        dateTime = new DateTime(shlEditVehicle, SWT.BORDER);
        dateTime.setBounds(98, 236, 80, 24);

        btnRoadworthy = new Button(shlEditVehicle, SWT.CHECK);
        btnRoadworthy.setBounds(10, 306, 113, 16);
        btnRoadworthy.setText("Roadworthy");
        btnRoadworthy.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.character == SWT.CR) {
                    btnRoadworthy.setSelection(!btnRoadworthy.getSelection());
                }
            }
        });

        Label lblLicensePlateNumber = new Label(shlEditVehicle, SWT.NONE);
        lblLicensePlateNumber.setBounds(129, 307, 126, 15);
        lblLicensePlateNumber.setText("License Plate Number");

        textLPN = new Text(shlEditVehicle, SWT.BORDER);
        textLPN.setBounds(261, 304, 103, 21);

        Label lblInsurancePolicyNumber = new Label(shlEditVehicle, SWT.NONE);
        lblInsurancePolicyNumber.setBounds(10, 357, 140, 15);
        lblInsurancePolicyNumber.setText("Insurance Policy Number");

        textInsPolNum = new Text(shlEditVehicle, SWT.BORDER);
        textInsPolNum.setBounds(156, 354, 150, 21);

        Label lblInsuranceType = new Label(shlEditVehicle, SWT.NONE);
        lblInsuranceType.setBounds(322, 357, 89, 15);
        lblInsuranceType.setText("Insurance Type");

        textInsType = new Text(shlEditVehicle, SWT.BORDER);
        textInsType.setBounds(418, 354, 150, 21);

        Label label = new Label(shlEditVehicle, SWT.SEPARATOR | SWT.HORIZONTAL);
        label.setBounds(10, 342, 625, 2);

        Label label_1 = new Label(shlEditVehicle, SWT.SEPARATOR
                | SWT.HORIZONTAL);
        label_1.setBounds(10, 288, 625, 2);

        btnOperational = new Button(shlEditVehicle, SWT.CHECK);
        btnOperational.setBounds(10, 378, 93, 16);
        btnOperational.setText("Operational");
        btnOperational.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.character == SWT.CR) {
                    btnOperational.setSelection(!btnOperational.getSelection());
                }
            }
        });

        Button btnUpdate = new Button(shlEditVehicle, SWT.NONE);
        btnUpdate.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                boolean good = checkFields();
                if (good) {
                    SetFields();

                    shlEditVehicle.close();
                } else {
                    // display message explaining to user what is wrong with
                    // their input
                }
            }
        });
        btnUpdate.setBounds(98, 412, 75, 25);
        btnUpdate.setText("Update");

        Button btnCancel = new Button(shlEditVehicle, SWT.NONE);
        btnCancel.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                shlEditVehicle.close();
            }
        });
        btnCancel.setBounds(180, 412, 75, 25);
        btnCancel.setText("Cancel");

        textVehicleIDWarning = new Text(shlEditVehicle, SWT.NONE);
        textVehicleIDWarning.setForeground(SWTResourceManager
                .getColor(SWT.COLOR_RED));
        textVehicleIDWarning.setEditable(false);
        textVehicleIDWarning.setBounds(179, 7, 360, 21);

        textTypeWarning = new Text(shlEditVehicle, SWT.NONE);
        textTypeWarning.setForeground(SWTResourceManager
                .getColor(SWT.COLOR_RED));
        textTypeWarning.setEditable(false);
        textTypeWarning.setBounds(312, 35, 280, 21);

        textManufacturerWarning = new Text(shlEditVehicle, SWT.NONE);
        textManufacturerWarning.setForeground(SWTResourceManager
                .getColor(SWT.COLOR_RED));
        textManufacturerWarning.setEditable(false);
        textManufacturerWarning.setBounds(180, 66, 359, 21);

        textModelWarning = new Text(shlEditVehicle, SWT.NONE);
        textModelWarning.setForeground(SWTResourceManager
                .getColor(SWT.COLOR_RED));
        textModelWarning.setEditable(false);
        textModelWarning.setBounds(180, 97, 313, 21);

        textYearWarning = new Text(shlEditVehicle, SWT.NONE);
        textYearWarning.setForeground(SWTResourceManager
                .getColor(SWT.COLOR_RED));
        textYearWarning.setEditable(false);
        textYearWarning.setBounds(180, 128, 388, 21);

        textKmsWarning = new Text(shlEditVehicle, SWT.NONE);
        textKmsWarning.setEditable(false);
        textKmsWarning
                .setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
        textKmsWarning.setBounds(179, 157, 377, 21);

        textKmsLSWarning = new Text(shlEditVehicle, SWT.NONE);
        textKmsLSWarning.setForeground(SWTResourceManager
                .getColor(SWT.COLOR_RED));
        textKmsLSWarning.setEditable(false);
        textKmsLSWarning.setBounds(180, 193, 313, 21);

        lblLPNWarning = new Label(shlEditVehicle, SWT.WRAP);
        lblLPNWarning.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
        lblLPNWarning.setBounds(370, 307, 201, 15);

        textInsPolNumWarning = new Text(shlEditVehicle, SWT.NONE);
        textInsPolNumWarning.setForeground(SWTResourceManager
                .getColor(SWT.COLOR_RED));
        textInsPolNumWarning.setEditable(false);
        textInsPolNumWarning.setBounds(156, 379, 189, 21);

        textInsTypeWarning = new Text(shlEditVehicle, SWT.NONE);
        textInsTypeWarning.setEditable(false);
        textInsTypeWarning.setForeground(SWTResourceManager
                .getColor(SWT.COLOR_RED));
        textInsTypeWarning.setBounds(399, 381, 193, 21);

        btnEditPartsList = new Button(shlEditVehicle, SWT.NONE);
        btnEditPartsList.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                EditPartsList editParts = new EditPartsList();
                editParts.open(currVehicle);
            }
        });
        btnEditPartsList.setBounds(10, 412, 82, 25);
        btnEditPartsList.setText("Edit Parts List");
        
        Label lblNewLabel = new Label(shlEditVehicle, SWT.WRAP);
        lblNewLabel.setBounds(10, 236, 76, 46);
        lblNewLabel.setText("Date Last Serviced");
    }

    private void SetFields() {
    	
    	info = new VehicleInfo();
    	accessVehicle = new AccessVehicle();
    	info.setID(textVehicleID.getText());
        info.setType(textType.getText());
        info.setManufacturer(textManufacturer.getText());
        info.setModel(textModel.getText());
        info.setYear(new Integer(textYear.getText()));
        info.setRoadWorthy(btnRoadworthy.getSelection());
        info.setLicensePlate(textLPN.getText());
        info.setOperational(btnOperational.getSelection());
        info.setInsurance(textInsPolNum.getText(), textInsType.getText());
        info.setKmDriven(new Integer(textKms.getText()));
        info.setKmLastServiced(new Integer(textKmsLS.getText()));
        info.setDateLastServiced(Date.valueOf(dateTime.getYear() + "-" + (dateTime.getMonth()+1) + "-" + dateTime.getDay()));
        accessVehicle.updateVehicle( currVehicle.getID(), info );
        currVehicle = accessVehicle.getVehicle( textVehicleID.getText());
        /*
        currVehicle.setID(textVehicleID.getText());
        currVehicle.setType(textType.getText());
        currVehicle.setManufacturer(textManufacturer.getText());
        currVehicle.setModel(textModel.getText());
        currVehicle.setYear(new Integer(textYear.getText()));
        currVehicle.setRoadWorthy(btnRoadworthy.getSelection());
        currVehicle.setLicensePlate(textLPN.getText());
        currVehicle.setOperational(btnOperational.getSelection());
        currVehicle
                .setInsurance(textInsPolNum.getText(), textInsType.getText());
        currVehicle.setKmDriven(new Integer(textKms.getText()));
        currVehicle.setKmLastServiced(new Integer(textKmsLS.getText()));
        */
    }

    private boolean checkFields() {
        // perform checks on all the fields to make sure they are good
        boolean fieldsOkay = false;
        fieldsOkay = checkID() && checkType() && checkManufacturer()
                && checkModel() && checkYear() && checkLPN()
                && checkInsPolNum() && checkInsType() && checkKms()
                && checkKmsLS();
        return fieldsOkay;
    }

    private boolean checkID() {
        boolean isValid = false;
        String input = textVehicleID.getText();
        isValid = input.matches("[0-9a-zA-Z]+");
        if (!isValid) {
            textVehicleIDWarning
                    .setText("Vehicle ID can only include numbers or letter and no spaces");
        } else {
            textVehicleIDWarning.setText("");
        }
        return isValid;
    }

    private boolean checkType() {
        boolean isValid = false;
        String input = textType.getText();
        isValid = input.matches("[0-9a-zA-Z.*\\s+.*]+")
                && !input.trim().isEmpty();
        if (!isValid) {
            textTypeWarning
                    .setText("Type can only include numbers and letters");
        } else {
            textTypeWarning.setText("");
        }
        return isValid;
    }

    private boolean checkManufacturer() {
        boolean isValid = false;
        String input = textManufacturer.getText();
        isValid = input.matches("[0-9a-zA-Z.*\\s+.*]+")
                && !input.trim().isEmpty();
        if (!isValid) {
            textManufacturerWarning
                    .setText("Manufacturer can only include numbers and letters");
        } else {
            textManufacturerWarning.setText("");
        }
        return isValid;
    }

    private boolean checkModel() {
        boolean isValid = false;
        String input = textModel.getText();
        isValid = input.matches("[0-9a-zA-Z.*\\s+.*]+")
                && !input.trim().isEmpty();
        if (!isValid) {
            textModelWarning
                    .setText("Model can only include numbers or letter");
        } else {
            textModelWarning.setText("");
        }
        return isValid;
    }

    private boolean checkYear() {
        boolean isValid = false;
        String input = textYear.getText();
        isValid = input.matches("[0-9]+") && input.matches("[0-9]*");
        if (!isValid) {
            textYearWarning.setText("Years only have numbers in them");
        } else {
            textYearWarning.setText("");
        }
        return isValid;
    }

    private boolean checkLPN() {
        boolean isValid = false;
        String input = textLPN.getText();
        isValid = input.matches("[0-9a-zA-Z.*\\s+.*]+")
                && !input.trim().isEmpty();
        // checks to see if road worthy button is selected, if so LPN must exist
        if (btnRoadworthy.getSelection() && input == "") {
            lblLPNWarning
                    .setText("If a vehicle is road worthy, it must also have a license plate #");
            isValid = false;
        } else if (!btnRoadworthy.getSelection() && input == "") {
            isValid = true;
        } else if (!isValid) {
            lblLPNWarning
                    .setText("License plate number can only include numbers or letter");
        } else {
            lblLPNWarning.setText("");
        }
        lblLPNWarning.pack();
        return isValid;
    }

    private boolean checkInsPolNum() {
        boolean isValid = false;
        String input = textInsPolNum.getText();
        isValid = input.matches("[0-9a-zA-Z]+");
        if (!isValid) {
            textInsPolNumWarning
                    .setText("Insurance policy number can only include numbers or letter");
        } else {
            textInsPolNumWarning.setText("");
        }
        return isValid;
    }

    private boolean checkInsType() {
        boolean isValid = false;
        String input = textInsType.getText();
        isValid = input.matches("[0-9a-zA-Z.*\\s+.*]+")
                && !input.trim().isEmpty();
        if (!isValid) {
            textInsTypeWarning
                    .setText("Insurance type can only include numbers or letters");
        } else {
            textInsTypeWarning.setText("");
        }
        return isValid;
    }

    private boolean checkKms() {
        boolean isValid = false;
        String input = textKms.getText();
        isValid = input.matches("[0-9]+") && input.matches("[0-9]*");
        if (!isValid) {
            textKmsWarning
                    .setText("Kilometers can only be expressed by numbers");
        } else {
            textKmsWarning.setText("");
        }
        return isValid;
    }

    private boolean checkKmsLS() {
        boolean isValid = false;
        String input = textKmsLS.getText();
        isValid = input.matches("[0-9]+") && input.matches("[0-9]*");
        if (!isValid) {
            textKmsLSWarning
                    .setText("Kilometers last serviced can only be expressed by numbers");
        } else {
            textKmsLSWarning.setText("");
        }
        return isValid;
    }
}
