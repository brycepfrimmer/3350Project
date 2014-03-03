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
    private Text serviceKm;
    private Label errorLabel;

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
        shell.setSize(660, 309);
        shell.setText("Add Service Event");
        GridLayout windowLayout = new GridLayout();
        windowLayout.numColumns = 2;
        shell.setLayout(windowLayout);
        
        Label infoLabel = new Label(shell, SWT.NONE);
        infoLabel.setText("ID: "+v.getID()+" -- "+Integer.toString(v.getYear())+" "+v.getManufacturer()+" "+v.getModel());
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        
        Label eventDescLabel = new Label(shell, SWT.NONE);
        eventDescLabel.setText("Enter service event description:");
        new Label(shell, SWT.NONE);
        
        eventDesc = new Text(shell, SWT.BORDER);
        eventDesc.setLayoutData(new GridData(SWT.FILL, SWT.LEFT, true, false, 1, 1));
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        
        Label serviceTimeLabel = new Label(shell, SWT.NONE);
        serviceTimeLabel.setText("Service every X amount of days:");
        new Label(shell, SWT.NONE);
        
        serviceTime = new Text(shell, SWT.BORDER);
        serviceTime.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent arg0) {
                if (serviceTime.getCharCount() <= 0) {
                    serviceKm.setEnabled(true);
                    serviceKm.setEditable(true);
                } else {
                    serviceKm.setEnabled(false);
                    serviceKm.setEditable(false);
                }
            }
        });
        serviceTime.setLayoutData(new GridData(SWT.FILL, SWT.LEFT, true, false, 1, 1));
        new Label(shell, SWT.NONE);
        
        Label orLabel = new Label(shell, SWT.NONE);
        orLabel.setText("OR");
        new Label(shell, SWT.NONE);
        
        Label serviceKmLabel = new Label(shell, SWT.NONE);
        serviceKmLabel.setText("Service every X kilometers:");
        new Label(shell, SWT.NONE);
        
        serviceKm = new Text(shell, SWT.BORDER);
        serviceKm.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent arg0) {
                if (serviceKm.getCharCount() <= 0) {
                    serviceTime.setEnabled(true);
                    serviceTime.setEditable(true);
                } else {
                    serviceTime.setEnabled(false);
                    serviceTime.setEditable(false);
                }
            }
        });
        serviceKm.setLayoutData(new GridData(SWT.FILL, SWT.LEFT, true, false, 1, 1));
        new Label(shell, SWT.NONE);
        
        errorLabel = new Label(shell, SWT.NONE);
        errorLabel.setForeground(SWTResourceManager
                .getColor(SWT.COLOR_RED));
        errorLabel.setText("");
        new Label(shell, SWT.NONE);
        
        Button addButton = new Button(shell, SWT.NONE);
        addButton.setText("Add Event");
        addButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (checkTime()) {
                    v.addServiceEvent(new ServiceItem(eventDesc.getMessage(), Long.parseLong(serviceTime.getMessage())));
                    shell.close();
                } else if (checkKilos()) {
                    v.addServiceEvent(new ServiceItem(eventDesc.getMessage(), Integer.parseInt(serviceKm.getMessage())));
                    shell.close();
                } else {
                    errorLabel.setText("Please fill out a time or kilometer service indicator.");
                }
            }
        });
        
        Button cancelButton = new Button(shell, SWT.NONE);
        cancelButton.setText("Cancel");
        cancelButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                shell.close();
            }
        });
    }
    
    private boolean checkTime() {
        if (serviceTime.getCharCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    private boolean checkKilos() {
        if (serviceKm.getCharCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
