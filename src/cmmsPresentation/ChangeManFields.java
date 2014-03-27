package cmmsPresentation;


import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import cmmsBusiness.AccessManFields;
import cmmsObjects.ManFields;

import acceptanceTests.Register; 
import acceptanceTests.EventLoop;

public class ChangeManFields {
	protected Shell shell;
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
	
	private AccessManFields accessFields;
	
	/**
	 * Open the window
	 * 
	 */
	public void open(){
		Display display = Display.getDefault();
		Register.newWindow(this);
		accessFields = new AccessManFields();
		manFields = accessFields.getManFields();
		//manFields = dbInterface.getManFields();
		createContents();
		shell.open();
		shell.layout();
		if( EventLoop.isEnabled() )
		{
			while (!shell.isDisposed()){
				if (!display.readAndDispatch()){
					display.sleep();
				}
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
		shell = new Shell();
		shell.setText("Change Mandatory Fields");
		
		btnCheckID = new Button(shell, SWT.CHECK);
		btnCheckID.setSelection(manFields.getId());
		btnCheckID.setBounds(0, 10, 93, 16);
		btnCheckID.setText("ID");
		
		btnCheckType = new Button(shell, SWT.CHECK);
		btnCheckType.setText("Type");
		btnCheckType.setSelection(manFields.getType());
		btnCheckType.setBounds(0, 32, 93, 16);
		
		btnCheckMan = new Button(shell, SWT.CHECK);
		btnCheckMan.setText("Manufacturer");
		btnCheckMan.setSelection(manFields.getManufacturer());
		btnCheckMan.setBounds(0, 54, 93, 16);
		
		btnCheckModel = new Button(shell, SWT.CHECK);
		btnCheckModel.setText("Model");
		btnCheckModel.setSelection(manFields.getModel());
		btnCheckModel.setBounds(0, 76, 93, 16);
		
		btnCheckKmsDriven = new Button(shell, SWT.CHECK);
		btnCheckKmsDriven.setText("Kilometers");
		btnCheckKmsDriven.setSelection(manFields.getKmsDriven());
		btnCheckKmsDriven.setBounds(0, 98, 93, 16);
		
		btnCheckKmsLastServiced = new Button(shell, SWT.CHECK);
		btnCheckKmsLastServiced.setText("Kilometers Last Serviced");
		btnCheckKmsLastServiced.setSelection(manFields.getKmsLastServiced());
		btnCheckKmsLastServiced.setBounds(0, 120, 148, 16);
		
		btnCheckInsInfo = new Button(shell, SWT.CHECK);
		btnCheckInsInfo.setText("Insurance Information");
		btnCheckInsInfo.setSelection(manFields.getInsInfo());
		btnCheckInsInfo.setBounds(0, 142, 138, 16);
		
		btnCheckYear = new Button(shell, SWT.CHECK);
		btnCheckYear.setText("Year");
		btnCheckYear.setSelection(manFields.getYear());
		btnCheckYear.setBounds(0, 164, 93, 16);
		
		btnAcceptChanges = new Button(shell, SWT.NONE);
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
				accessFields.updateManFields(manFields);
				shell.close();
			}
		});
		btnAcceptChanges.setBounds(10, 226, 98, 25);
		btnAcceptChanges.setText("Accept Changes");
		
		btnCancel = new Button(shell, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		btnCancel.setBounds(114, 226, 75, 25);
		btnCancel.setText("Cancel");
	}
}
