package cmmsPresentation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import cmmsBusiness.AccessVehicle;
 
public class IDInputDialog extends Dialog {
	private int vehicleID = -1;
	
	public IDInputDialog(Shell parent) {
		super(parent);
	}
	
	public int open() {
		Shell shell = new Shell(getParent());
		shell.setText("Clone Vehicle");
 
		draw(shell); // Contents of Dialog
		shell.pack();
		shell.open();
 
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
 
		return vehicleID;
	}
 
	private void draw(final Shell shell) { 
		shell.setLayout(new GridLayout(2, true));
 
		Label vehicleIDLabel = new Label(shell, SWT.HORIZONTAL | SWT.SHADOW_OUT);
		vehicleIDLabel.setText("New Vehicle ID:");
		final Text vehicleIDText = new Text(shell, SWT.BORDER | SWT.LEFT);
 
		final Button okButton = new Button(shell, SWT.NONE);
		okButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (vehicleIDText.getText().matches("[0-9a-zA-Z]+"))
					vehicleID = new Integer(vehicleIDText.getText());
				
				AccessVehicle av = new AccessVehicle();
				if (!av.CheckID(vehicleIDText.getText())) {
					shell.dispose();
				}
				else {
	                // Display no selection error
	                MessageBox mb = new MessageBox(shell, SWT.ICON_ERROR
	                        | SWT.OK);
	                mb.setMessage("Error: The ID you entered is invalid");
	                mb.setText("Invalid ID");
	                mb.open();
				}
			} 
		});
		okButton.setText("OK");
		
		final Button cancelButton = new Button(shell, SWT.NONE);
		cancelButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			} 
		});
		cancelButton.setText("Cancel");
	}
 
}