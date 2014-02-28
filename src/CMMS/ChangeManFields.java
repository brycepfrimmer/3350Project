package CMMS;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ChangeManFields implements CMMSInterface {
	protected Shell shlChangeManFields;
	private Button btnCheckID;
	private Button btnCheckType;
	private Button btnCheckMan;
	private Button btnCheckModel;
	private Button btnCheckKmsDriven;
	private Button btnCheckKmsLastServiced;
	private Button btnCheckInsInfo;
	private Button btnCheckYear;
	private Button btnAcceptChanges;
	private Button btnCancel;
	
	private ManFields manFields;
	
	/**
	 * Open the window
	 * 
	 */
	public void open(){
		Display display = Display.getDefault();
		manFields = dbInterface.getManFields();
		createContents();
		shlChangeManFields.open();
		shlChangeManFields.layout();
		while (!shlChangeManFields.isDisposed()){
			if (!display.readAndDispatch()){
				display.sleep();
			}
		}
	}
	
	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 * 
	 * 
	 */
	protected void createContents(){
		shlChangeManFields = new Shell();
		shlChangeManFields.setText("Change Mandatory Fields");
		
		btnCheckID = new Button(shlChangeManFields, SWT.CHECK);
		btnCheckID.setSelection(manFields.getId());
		btnCheckID.setBounds(0, 10, 93, 16);
		btnCheckID.setText("ID");
		
		btnCheckType = new Button(shlChangeManFields, SWT.CHECK);
		btnCheckType.setText("Type");
		btnCheckType.setSelection(manFields.getType());
		btnCheckType.setBounds(0, 32, 93, 16);
		
		btnCheckMan = new Button(shlChangeManFields, SWT.CHECK);
		btnCheckMan.setText("Manufacturer");
		btnCheckMan.setSelection(manFields.getManufacturer());
		btnCheckMan.setBounds(0, 54, 93, 16);
		
		btnCheckModel = new Button(shlChangeManFields, SWT.CHECK);
		btnCheckModel.setText("Model");
		btnCheckModel.setSelection(manFields.getModel());
		btnCheckModel.setBounds(0, 76, 93, 16);
		
		btnCheckKmsDriven = new Button(shlChangeManFields, SWT.CHECK);
		btnCheckKmsDriven.setText("Kilometers");
		btnCheckKmsDriven.setSelection(manFields.getKmsDriven());
		btnCheckKmsDriven.setBounds(0, 98, 93, 16);
		
		btnCheckKmsLastServiced = new Button(shlChangeManFields, SWT.CHECK);
		btnCheckKmsLastServiced.setText("Kilometers Last Serviced");
		btnCheckKmsLastServiced.setSelection(manFields.getKmsLastServiced());
		btnCheckKmsLastServiced.setBounds(0, 120, 148, 16);
		
		btnCheckInsInfo = new Button(shlChangeManFields, SWT.CHECK);
		btnCheckInsInfo.setText("Insurance Information");
		btnCheckInsInfo.setSelection(manFields.getInsInfo());
		btnCheckInsInfo.setBounds(0, 142, 138, 16);
		
		btnCheckYear = new Button(shlChangeManFields, SWT.CHECK);
		btnCheckYear.setText("Year");
		btnCheckYear.setSelection(manFields.getYear());
		btnCheckYear.setBounds(0, 164, 93, 16);
		
		btnAcceptChanges = new Button(shlChangeManFields, SWT.NONE);
		btnAcceptChanges.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				manFields.setType(btnCheckType.getSelection());
				manFields.setId(btnCheckID.getSelection());
				manFields.setManufacturer(btnCheckMan.getSelection());
				manFields.setModel(btnCheckModel.getSelection());
				manFields.setKmsDriven(btnCheckKmsDriven.getSelection());
				manFields.setKmsLastServiced(btnCheckKmsLastServiced.getSelection());
				manFields.setInsInfo(btnCheckInsInfo.getSelection());
				manFields.setYear(btnCheckYear.getSelection());
				shlChangeManFields.close();
			}
		});
		btnAcceptChanges.setBounds(10, 226, 98, 25);
		btnAcceptChanges.setText("Accept Changes");
		
		btnCancel = new Button(shlChangeManFields, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlChangeManFields.close();
			}
		});
		btnCancel.setBounds(114, 226, 75, 25);
		btnCancel.setText("Cancel");
	}
}
