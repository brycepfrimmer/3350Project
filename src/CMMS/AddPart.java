package CMMS;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class AddPart {

	protected Shell shlAddPart;
	private Text textPart;

	private Vehicle currVehicle;
	private Button btnCancel;
	private Label lblPart;

	/**
	 * Open the window.
	 */
	public void open(Vehicle v) {
		Display display = Display.getDefault();
		createContents();
		currVehicle = v;
		FillFields();
		shlAddPart.open();
		shlAddPart.layout();
		while (!shlAddPart.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	// Fill all the controls with the fields from the 
	// vehicle we were passed in
	private void FillFields() {
		textPart.setText(Integer.toString(currVehicle.getKmDriven()));	
	}

	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shlAddPart = new Shell();
		shlAddPart.setText("Add Part");
		shlAddPart.setSize(271, 168);
		
		textPart = new Text(shlAddPart, SWT.BORDER);
		textPart.setBounds(72, 31, 161, 21);
		
		Button btnAddPart = new Button(shlAddPart, SWT.NONE);
		btnAddPart.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SetFields();
					
				shlAddPart.close();
			}
		});
		btnAddPart.setBounds(22, 86, 58, 25);
		btnAddPart.setText("Add Part");
		
		btnCancel = new Button(shlAddPart, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlAddPart.close();
			}
		});
		btnCancel.setBounds(86, 86, 75, 25);
		btnCancel.setText("Cancel");
		
		lblPart = new Label(shlAddPart, SWT.NONE);
		lblPart.setBounds(10, 34, 56, 15);
		lblPart.setText("Part Name");
	}
	
	private void SetFields() {
		currVehicle.getPartsList().addPart(textPart.getText());
	}

}