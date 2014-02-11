//package window.builder;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;

public class test {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			test window = new test();
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
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		Button btnAdd = new Button(shell, SWT.NONE);
		btnAdd.setBounds(346, 37, 94, 28);
		btnAdd.setText("Add");
		
		Button btnRemove = new Button(shell, SWT.NONE);
		btnRemove.setBounds(346, 66, 94, 28);
		btnRemove.setText("Remove");
		
		Button btnView = new Button(shell, SWT.NONE);
		btnView.setBounds(346, 95, 94, 28);
		btnView.setText("View");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setBounds(346, 126, 94, 28);
		btnNewButton.setText("Repair List");

	}
}
