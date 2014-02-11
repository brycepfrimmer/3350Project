import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class AddVehicle {

	protected Shell shell;
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

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AddVehicle window = new AddVehicle();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(660, 430);
		shell.setText("SWT Application");
		
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
		
		textYear = new Text(shell, SWT.BORDER);
		textYear.setBounds(98, 128, 76, 21);
		
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
		
		btnRoadworthy = new Button(shell, SWT.CHECK);
		btnRoadworthy.setBounds(10, 236, 113, 16);
		btnRoadworthy.setText("Roadworthy");
		
		Label lblLicensePlateNumber = new Label(shell, SWT.NONE);
		lblLicensePlateNumber.setBounds(129, 239, 126, 15);
		lblLicensePlateNumber.setText("License Plate Number");
		
		textLPN = new Text(shell, SWT.BORDER);
		textLPN.setBounds(258, 236, 103, 21);

		Label lblInsurancePolicyNumber = new Label(shell, SWT.NONE);
		lblInsurancePolicyNumber.setBounds(10, 288, 140, 15);
		lblInsurancePolicyNumber.setText("Insurance Policy Number");
		
		textInsPolNum = new Text(shell, SWT.BORDER);
		textInsPolNum.setBounds(156, 282, 150, 21);
		
		Label lblInsuranceType = new Label(shell, SWT.NONE);
		lblInsuranceType.setBounds(323, 288, 89, 15);
		lblInsuranceType.setText("Insurance Type");
		
		textInsType = new Text(shell, SWT.BORDER);
		textInsType.setBounds(418, 282, 150, 21);
		
		Label label = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(10, 269, 558, 2);
		
		Label label_1 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(10, 220, 558, 2);

		btnOperational = new Button(shell, SWT.CHECK);
		btnOperational.setBounds(10, 324, 93, 16);
		btnOperational.setText("Operational");

		Button btnCreate = new Button(shell, SWT.NONE);
		btnCreate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean good = checkFields();
				if(good) {
					Vehicle newVehicle = new Vehicle(textVehicleID.getText(), textType.getText(), textManufacturer.getText(),
							textModel.getText(), btnRoadworthy.getSelection(), textLPN.getText(), btnOperational.getSelection(),
							textInsPolNum.getText(), textInsType.getText(), new Integer(textKms.getText()), new Integer(textKmsLS.getText()));
					Interface temp = new Interface();
					temp.addVehicle(newVehicle);
					shell.close();
				} else {
					//display message explaining to user what is wrong with their input
				}
			}
		});
		btnCreate.setBounds(180, 357, 75, 25);
		btnCreate.setText("Create");
		
		Button btnCancel = new Button(shell, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		btnCancel.setBounds(271, 357, 75, 25);
		btnCancel.setText("Cancel");
	}
	
	private boolean checkFields() {
		//perform checks on all the fields to make sure they are good
		boolean fieldsOkay = false;
		fieldsOkay = checkID() && checkType() && checkManufacturer() && checkModel() && checkLPN() && checkInsPolNum()
				&& checkInsType() && checkKms() && checkKmsLS();
		return fieldsOkay;
	}
	
	private boolean checkID()
	{
		boolean isValid = false;
		String input = textVehicleID.getText();
		isValid = input.matches("[0-9a-zA-Z]+");
		return isValid;
	}
	private boolean checkType()
	{
		boolean isValid = false;
		String input = textType.getText();
		isValid = input.matches("[0-9a-zA-Z.*\\s+.*]+")  && !input.trim().isEmpty();
		return isValid;
	}
	private boolean checkManufacturer()
	{
		boolean isValid = false;
		String input = textManufacturer.getText();
		isValid = input.matches("[0-9a-zA-Z.*\\s+.*]+")  && !input.trim().isEmpty();
		return isValid;
	}
	private boolean checkModel()
	{
		boolean isValid = false;
		String input = textModel.getText();
		isValid = input.matches("[0-9a-zA-Z.*\\s+.*]+")  && !input.trim().isEmpty();
		return isValid;
	}
	private boolean checkLPN()
	{
		boolean isValid = false;
		String input = textLPN.getText();
		isValid = input.matches("[0-9a-zA-Z.*\\s+.*]+")  && !input.trim().isEmpty();
		return isValid;
	}
	private boolean checkInsPolNum()
	{
		boolean isValid = false;
		String input = textInsPolNum.getText();
		isValid = input.matches("[0-9a-zA-Z]+");
		return isValid;
	}
	private boolean checkInsType()
	{
		boolean isValid = false;
		String input = textInsType.getText();
		isValid = input.matches("[0-9a-zA-Z.*\\s+.*]+")  && !input.trim().isEmpty();
		return isValid;
	}
	private boolean checkKms()
	{
		boolean isValid = false;
		String input = textKms.getText();
		isValid = input.matches("[0-9]+") && input.matches("[0-9]*");
		return isValid;
	}
	private boolean checkKmsLS()
	{
		boolean isValid = false;
		String input = textKmsLS.getText();
		isValid = input.matches("[0-9]+") && input.matches("[0-9]*");
		return isValid;
	}
}
