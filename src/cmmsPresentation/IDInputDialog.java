package cmmsPresentation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
 
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
				
				shell.dispose();
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