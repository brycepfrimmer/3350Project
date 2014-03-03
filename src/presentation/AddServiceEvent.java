package presentation;

import objects.ServiceItem;
import objects.Vehicle;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import businessLogic.CMMSInterface;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class AddServiceEvent implements CMMSInterface {

    protected Shell shell;
    private Text eventDesc;
    private Text serviceTime;
    private Text serviceKilos;

    /**
     * Open the window.
     */
    public void open(Vehicle v) {
        Display display = Display.getDefault();
        createContents(v);
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
     * 
     * @wbp.parser.entryPoint
     */
    protected void createContents(final Vehicle v) {
        shell = new Shell();
        shell.setSize(660, 430);
        shell.setText("Add Service Event");
        shell.setLayout(new GridLayout(5, false));
        
        Label lblNewLabel_4 = new Label(shell, SWT.NONE);
        lblNewLabel_4.setText("ID: ");
        
        Label lblNewLabel = new Label(shell, SWT.NONE);
        lblNewLabel.setText(v.getID());
        
        Label lblNewLabel_1 = new Label(shell, SWT.NONE);
        lblNewLabel_1.setText(Integer.toString(v.getYear()));
        
        Label lblNewLabel_2 = new Label(shell, SWT.NONE);
        lblNewLabel_2.setText(v.getManufacturer());
        
        Label lblNewLabel_3 = new Label(shell, SWT.NONE);
        lblNewLabel_3.setText(v.getModel());
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        
        Label lblNewLabel_5 = new Label(shell, SWT.NONE);
        lblNewLabel_5.setText("Enter service event description:");
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        
        eventDesc = new Text(shell, SWT.BORDER);
        eventDesc.setLayoutData(new GridData(SWT.FILL, SWT.LEFT, true, false, 1, 1));
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        
        Label lblNewLabel_6 = new Label(shell, SWT.NONE);
        lblNewLabel_6.setText("Service every X amount of days:");
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        
        serviceTime = new Text(shell, SWT.BORDER);
        serviceTime.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent arg0) {
                if (serviceTime.getCharCount() <= 0) {
                    serviceKilos.setEnabled(true);
                    serviceKilos.setEditable(true);
                } else {
                    serviceKilos.setEnabled(false);
                    serviceKilos.setEditable(false);
                }
            }
        });
        serviceTime.setLayoutData(new GridData(SWT.FILL, SWT.LEFT, true, false, 1, 1));
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        
        Label lblNewLabel_7 = new Label(shell, SWT.NONE);
        lblNewLabel_7.setText("OR");
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        
        Label lblServiceEveryX = new Label(shell, SWT.NONE);
        lblServiceEveryX.setText("Service every X kilometers:");
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        
        serviceKilos = new Text(shell, SWT.BORDER);
        serviceKilos.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent arg0) {
                if (serviceKilos.getCharCount() <= 0) {
                    serviceTime.setEnabled(true);
                    serviceTime.setEditable(true);
                } else {
                    serviceTime.setEnabled(false);
                    serviceTime.setEditable(false);
                }
            }
        });
        serviceKilos.setLayoutData(new GridData(SWT.FILL, SWT.LEFT, true, false, 1, 1));
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        
        final Label lblNewLabel_8 = new Label(shell, SWT.NONE);
        lblNewLabel_8.setForeground(SWTResourceManager
                .getColor(SWT.COLOR_RED));
        lblNewLabel_8.setText("");
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        
        Button btnNewButton = new Button(shell, SWT.NONE);
        btnNewButton.setText("Add Event");
        btnNewButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (checkTime()) {
                    v.addServiceEvent(new ServiceItem(eventDesc.getMessage(), Long.parseLong(serviceTime.getMessage())));
                    shell.close();
                } else if (checkKilos()) {
                    v.addServiceEvent(new ServiceItem(eventDesc.getMessage(), Integer.parseInt(serviceKilos.getMessage())));
                    shell.close();
                } else {
                    lblNewLabel_8.setText("Please fill out a time or kilometer service indicator.");
                }
            }
        });
        
        Button btnNewButton_1 = new Button(shell, SWT.NONE);
        btnNewButton_1.setText("Cancel");
        btnNewButton_1.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                shell.close();
            }
        });
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
    }
    
    private boolean checkTime() {
        if (serviceTime.getCharCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    private boolean checkKilos() {
        if (serviceKilos.getCharCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
