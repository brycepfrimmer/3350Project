package cmmsPresentation;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

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

import cmmsBusiness.AccessManFields;
import cmmsBusiness.AccessVehicle;
import cmmsObjects.ManFields;
import cmmsObjects.Vehicle.Vehicle;
import acceptanceTests.Register; 
import acceptanceTests.EventLoop;

import org.eclipse.swt.widgets.Combo;

public class EditVehicle {

    protected Shell shell;
    private Text textVehicleID;
    private Text textType;
    private Text textManufacturer;
    private Text textModel;
    private Combo comboYear;
    private Text textKms;
    private Text textKmsLS;
    private Text textLPN;
    private Text textInsPolNum;
    private Text textInsType;
    private Button btnOperational;
    private Button btnRoadworthy;
    private DateTime dateTime;
    
    private Label lblVehicleIDWarning;
    private Label lblTypeWarning;
    private Label lblManufacturerWarning;
    private Label lblModelWarning;
    private Label lblYearWarning;
    private Label lblKmsWarning;
    private Label lblKmsLSWarning;
    private Label lblLPNWarning;
    private Label lblInsPolNumWarning;
    private Label lblInsTypeWarning;

    private Vehicle currVehicle;
    private Button btnEditPartsList;
    
    private Button btnUpdate;
    
    private AccessVehicle accessVehicle;
    private ManFields manFields;
    private AccessManFields accessFields;
    private int maxYear;

    /**
     * Open the window.
     */
    public void open(Vehicle v) {
        Display display = Display.getDefault();
        Register.newWindow(this);
        accessFields = new AccessManFields();
        manFields = accessFields.getManFields();
        maxYear = (Calendar.getInstance().get(Calendar.YEAR) + 2);
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
        textVehicleID.setText(currVehicle.getID());
        shell.setText("Edit " + currVehicle.getID());
        textType.setText(currVehicle.getType());
        textManufacturer.setText(currVehicle.getManufacturer());
        textModel.setText(currVehicle.getModel());
        comboYear.setText(Integer.toString(currVehicle.getYear()));
        textKms.setText(Integer.toString(currVehicle.getKmDriven()));
        textKmsLS.setText(Integer.toString(currVehicle.getKmLastServiced()));
        btnRoadworthy.setSelection(currVehicle.isRoadWorthy());
        textLPN.setText(currVehicle.getLicensePlate());
        textInsPolNum.setText(currVehicle.getInsurance().getPolicyNum());
        textInsType.setText(currVehicle.getInsurance().getType());
        btnOperational.setSelection(currVehicle.isOperational());
        dateTime.setDate(currVehicle.getDateLastServiced().get(Calendar.YEAR), 
        		currVehicle.getDateLastServiced().get(Calendar.MONTH), currVehicle.getDateLastServiced().get(Calendar.DATE));
    }

    /**
     * Create contents of the window.
     * 
     * @wbp.parser.entryPoint
     */
    protected void createContents() {
        shell = new Shell();
        shell.setText("Edit Vehicle");
        shell.setSize(660, 485);

        Label lblVehicleId = new Label(shell, SWT.NONE);
        lblVehicleId.setBounds(10, 10, 55, 15);
        lblVehicleId.setText("Vehicle ID");

        textVehicleID = new Text(shell, SWT.BORDER);
        textVehicleID.setBounds(98, 7, 76, 21);

        Label lblType = new Label(shell, SWT.NONE);
        lblType.setBounds(10, 41, 55, 15);
        lblType.setText("Type");

        textType = new Text(shell, SWT.BORDER);
        textType.setBounds(98, 37, 76, 21);

        Label lblieCarTruck = new Label(shell, SWT.NONE);
        lblieCarTruck.setBounds(180, 41, 126, 15);
        lblieCarTruck.setText("(ie. Car, Truck, Forklift)");

        Label lblManufacturer = new Label(shell, SWT.NONE);
        lblManufacturer.setBounds(10, 72, 76, 15);
        lblManufacturer.setText("Manufacturer");

        textManufacturer = new Text(shell, SWT.BORDER);
        textManufacturer.setBounds(98, 66, 76, 21);

        Label lblModel = new Label(shell, SWT.NONE);
        lblModel.setBounds(10, 103, 55, 15);
        lblModel.setText("Model");

        textModel = new Text(shell, SWT.BORDER);
        textModel.setBounds(98, 97, 76, 21);

        Label lblYear = new Label(shell, SWT.NONE);
        lblYear.setBounds(10, 134, 55, 15);
        lblYear.setText("Year");

        comboYear = new Combo(shell, SWT.BORDER);
        comboYear.setBounds(98, 128, 76, 21);
        for(int i = maxYear, j = 0; j <= (maxYear - 1900); i--, j++) {
        	comboYear.add(Integer.toString(i));
        }
        comboYear.setTextLimit(4);

        Label lblKilometers = new Label(shell, SWT.NONE);
        lblKilometers.setBounds(10, 163, 55, 15);
        lblKilometers.setText("Kilometers");

        textKms = new Text(shell, SWT.BORDER);
        textKms.setBounds(98, 157, 76, 21);

        Label lblKilometers_1 = new Label(shell, SWT.NONE);
        lblKilometers_1.setBounds(10, 184, 76, 30);
        lblKilometers_1.setText("Kilometers\nLast Serviced");

        textKmsLS = new Text(shell, SWT.BORDER);
        textKmsLS.setBounds(98, 193, 76, 21);
        
        dateTime = new DateTime(shell, SWT.BORDER);
        dateTime.setBounds(98, 236, 80, 24);

        btnRoadworthy = new Button(shell, SWT.CHECK);
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

        Label lblLicensePlateNumber = new Label(shell, SWT.NONE);
        lblLicensePlateNumber.setBounds(129, 307, 126, 15);
        lblLicensePlateNumber.setText("License Plate Number");

        textLPN = new Text(shell, SWT.BORDER);
        textLPN.setBounds(261, 304, 103, 21);

        Label lblInsurancePolicyNumber = new Label(shell, SWT.NONE);
        lblInsurancePolicyNumber.setBounds(10, 357, 140, 15);
        lblInsurancePolicyNumber.setText("Insurance Policy Number");

        textInsPolNum = new Text(shell, SWT.BORDER);
        textInsPolNum.setBounds(156, 354, 150, 21);

        Label lblInsuranceType = new Label(shell, SWT.NONE);
        lblInsuranceType.setBounds(322, 357, 89, 15);
        lblInsuranceType.setText("Insurance Type");

        textInsType = new Text(shell, SWT.BORDER);
        textInsType.setBounds(418, 354, 150, 21);

        Label label = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
        label.setBounds(10, 342, 625, 2);

        Label label_1 = new Label(shell, SWT.SEPARATOR
                | SWT.HORIZONTAL);
        label_1.setBounds(10, 288, 625, 2);

        btnOperational = new Button(shell, SWT.CHECK);
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

        btnUpdate = new Button(shell, SWT.NONE);
        btnUpdate.addSelectionListener(new SelectionAdapter() {
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
        btnUpdate.setBounds(98, 412, 75, 25);
        btnUpdate.setText("Update");

        Button btnCancel = new Button(shell, SWT.NONE);
        btnCancel.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                shell.close();
            }
        });
        btnCancel.setBounds(180, 412, 75, 25);
        btnCancel.setText("Cancel");

        lblVehicleIDWarning = new Label(shell, SWT.NONE);
        lblVehicleIDWarning.setForeground(SWTResourceManager
                .getColor(SWT.COLOR_RED));
        lblVehicleIDWarning.setBounds(179, 7, 360, 21);

        lblTypeWarning = new Label(shell, SWT.NONE);
        lblTypeWarning.setForeground(SWTResourceManager
                .getColor(SWT.COLOR_RED));
        lblTypeWarning.setBounds(312, 35, 280, 21);

        lblManufacturerWarning = new Label(shell, SWT.NONE);
        lblManufacturerWarning.setForeground(SWTResourceManager
                .getColor(SWT.COLOR_RED));
        lblManufacturerWarning.setBounds(180, 66, 359, 21);

        lblModelWarning = new Label(shell, SWT.NONE);
        lblModelWarning.setForeground(SWTResourceManager
                .getColor(SWT.COLOR_RED));
        lblModelWarning.setBounds(180, 97, 313, 21);

        lblYearWarning = new Label(shell, SWT.NONE);
        lblYearWarning.setForeground(SWTResourceManager
                .getColor(SWT.COLOR_RED));
        lblYearWarning.setBounds(180, 128, 388, 21);

        lblKmsWarning = new Label(shell, SWT.NONE);
        lblKmsWarning
                .setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
        lblKmsWarning.setBounds(179, 157, 377, 21);

        lblKmsLSWarning = new Label(shell, SWT.NONE);
        lblKmsLSWarning.setForeground(SWTResourceManager
                .getColor(SWT.COLOR_RED));
        lblKmsLSWarning.setBounds(180, 193, 313, 21);

        lblLPNWarning = new Label(shell, SWT.WRAP);
        lblLPNWarning.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
        lblLPNWarning.setBounds(370, 307, 201, 15);

        lblInsPolNumWarning = new Label(shell, SWT.NONE);
        lblInsPolNumWarning.setForeground(SWTResourceManager
                .getColor(SWT.COLOR_RED));
        lblInsPolNumWarning.setBounds(156, 379, 189, 21);

        lblInsTypeWarning = new Label(shell, SWT.NONE);
        lblInsTypeWarning.setForeground(SWTResourceManager
                .getColor(SWT.COLOR_RED));
        lblInsTypeWarning.setBounds(399, 381, 193, 21);

        btnEditPartsList = new Button(shell, SWT.NONE);
        btnEditPartsList.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                EditPartsList editParts = new EditPartsList();
                editParts.open(currVehicle);
            }
        });
        btnEditPartsList.setBounds(10, 412, 82, 25);
        btnEditPartsList.setText("Edit Parts List");
        
        Label lblNewLabel = new Label(shell, SWT.WRAP);
        lblNewLabel.setBounds(10, 236, 76, 46);
        lblNewLabel.setText("Date Last Serviced");
    }

    private void SetFields() {
    	accessVehicle = new AccessVehicle();  
    	
    	if (currVehicle == null) {
    		System.out.println("currVehicle is null");
    	}
    	
    	GregorianCalendar date = new GregorianCalendar();
        currVehicle.setID(textVehicleID.getText());
        currVehicle.setType(textType.getText());
        currVehicle.setManufacturer(textManufacturer.getText());
        currVehicle.setModel(textModel.getText());
        currVehicle.setYear(new Integer(comboYear.getText()));
        currVehicle.setRoadWorthy(btnRoadworthy.getSelection());
        currVehicle.setLicensePlate(textLPN.getText());
        currVehicle.setOperational(btnOperational.getSelection());
        currVehicle.setInsurance(textInsPolNum.getText(), textInsType.getText());
        currVehicle.setKmDriven(new Integer(textKms.getText()));
        currVehicle.setKmLastServiced(new Integer(textKmsLS.getText()));
        date.setTime(Date.valueOf(dateTime.getYear() + "-" + (dateTime.getMonth()+1) + "-" + dateTime.getDay()));
        currVehicle.setDateLastServiced(date);
        accessVehicle.updateVehicle( currVehicle );   
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
        boolean mand = manFields.getId();
        accessVehicle = new AccessVehicle();
        String input = textVehicleID.getText();
        if (mand) {
        	isValid = input.matches("[0-9a-zA-Z]+");
        } else {
        	isValid = true;
        }

        if (mand && input == ""){
        	lblVehicleIDWarning.setText("Vehicle ID is a required field");
        } else if (!isValid) {
            lblVehicleIDWarning.setText("Vehicle ID can only include numbers and letters and no spaces");
        }
        Vehicle temp = accessVehicle.getVehicle(input);
        if( temp != null && !currVehicle.getID().equals(temp.getID()) && input.equals(temp.getID())) {
            lblVehicleIDWarning.setText("This vehicle already exists");
            isValid = false;
        } 
        else {
            lblVehicleIDWarning.setText("");
        }

        lblVehicleIDWarning.pack();
        return isValid;
    }

    private boolean checkType() {
    	boolean isValid = false;
        boolean mand = manFields.getType();
        String input = textType.getText();
        if(mand){
        	isValid = input.matches("[0-9a-zA-Z.*\\s+.*]+") && !input.trim().isEmpty();
        }
        else{
        	isValid = true;
        }
        if(mand && input == "") {
        	lblTypeWarning.setText("Vehicle type is a required field");
        }
        else if (!isValid) {
            lblTypeWarning.setText("Type can only include numbers and letters");
        }
        else {
            lblTypeWarning.setText("");
        }
        lblTypeWarning.pack();
        return isValid;
    }

    private boolean checkManufacturer() {
    	 boolean isValid = false;
         boolean mand = manFields.getManufacturer();
         String input = textManufacturer.getText();
         if(mand){
         	isValid = input.matches("[0-9a-zA-Z.*\\s+.*]+") && !input.trim().isEmpty();
         }
         else{
         	isValid = true;
         }
         if(mand && input == ""){
         	lblManufacturerWarning.setText("Manufacturer is a required field");
         }
         else if (!isValid) {
             lblManufacturerWarning.setText("Manufacturer can only include numbers and letters");
         } 
         else {
             lblManufacturerWarning.setText("");
         }
         lblManufacturerWarning.pack();
         return isValid;
    }

    private boolean checkModel() {
    	 boolean isValid = false;
         boolean mand = manFields.getModel();
         String input = textModel.getText();
         if(mand){
         	isValid = input.matches("[0-9a-zA-Z.*\\s+.*]+") && !input.trim().isEmpty();
         }
         else{
         	isValid = true;
         }
         if (mand && input == ""){
         	lblModelWarning.setText("Vehicle Model is a required field");
         }
         else if (!isValid) {
             lblModelWarning
                     .setText("Model can only include numbers and letters");
         } else {
             lblModelWarning.setText("");
         }
         lblModelWarning.pack();
         return isValid;
    }

    private boolean checkYear() {
    	boolean isValid = false;
        boolean mand = manFields.getYear();
        String input = comboYear.getText();
        if(mand){
        	isValid = input.matches("[0-9]+") && input.matches("[0-9]*");
        }
        else{
        	isValid = true;
        }
        if(mand && input == ""){
        	lblYearWarning.setText("Year is a required field");
        }
        else if (!isValid) {
            lblYearWarning.setText("Years can only be represented by a number");
        } 
        else if(Integer.parseInt(input) > maxYear || Integer.parseInt(input) < 1900) {
        	lblYearWarning.setText("Only years between 1900 and " + maxYear + " are allowed");
        	isValid = false;
        } else {
            lblYearWarning.setText("");
        }
        lblYearWarning.pack();
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
        } else if (!btnRoadworthy.getSelection()) {
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
        boolean mand = manFields.getInsInfo();
        String input = textInsPolNum.getText();
        if(mand){
        	isValid = input.matches("[0-9a-zA-Z]+");
        }
        else{
        	isValid = true;
        }
        if(mand && input == "")
        {
        	lblInsPolNumWarning.setText("Insurance policy number is a required field");
        }
        else if (!isValid) {
            lblInsPolNumWarning.setText("Insurance policy number can only include numbers and letters");
        } 
        else {
            lblInsPolNumWarning.setText("");
        }
        lblInsPolNumWarning.pack();
        return isValid;
    }

    private boolean checkInsType() {
    	boolean isValid = false;
        boolean mand = manFields.getInsInfo();
        String input = textInsType.getText();
        if(mand){
        	isValid = input.matches("[0-9a-zA-Z.*\\s+.*]+") && !input.trim().isEmpty();
        }
        else{
        	isValid = true;
        }
        if(mand && input == ""){
        	lblInsTypeWarning.setText("Insurance type is a required field");
        }
        else if (!isValid) {
            lblInsTypeWarning.setText("Insurance type can only include numbers and letters");
        } else {
            lblInsTypeWarning.setText("");
        }
        lblInsTypeWarning.pack();
        return isValid;
    }

    private boolean checkKms() {
    	 boolean isValid = false;
         boolean mand = manFields.getKmsDriven();
         String input = textKms.getText();
         if(mand){
         	isValid = input.matches("[0-9]+") && input.matches("[0-9]*");
         }
         else{
         	isValid = true;
         }
         if (mand && input == ""){
         	lblKmsWarning.setText("Kilometers is a required field");
         }
         else if (!isValid) {
             lblKmsWarning.setText("Kilometers can only be represented by numbers");
         } else {
             lblKmsWarning.setText("");
         }
         lblKmsWarning.pack();
         return isValid;
    }

    private boolean checkKmsLS() {
    	boolean isValid = false;
        boolean mand = manFields.getKmsLastServiced();
        String input = textKmsLS.getText();
        if(mand){
        	isValid = input.matches("[0-9]+") && input.matches("[0-9]*");
        }
        else{
        	isValid = true;
        }
        if(mand && input == ""){
        	lblKmsLSWarning.setText("Kilometers last serviced is a required field");
        }
        else if (!isValid) {
            lblKmsLSWarning
                    .setText("Kilometers last serviced can only be represented by numbers");
        } else {
            lblKmsLSWarning.setText("");
        }
        lblKmsLSWarning.pack();
        return isValid;
    }
}
