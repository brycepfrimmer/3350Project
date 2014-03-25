package cmmsPresentation;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
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

import cmmsBusiness.AccessVehicle;
import cmmsObjects.Part;
import cmmsObjects.ServiceItem;
import cmmsObjects.Vehicle.Vehicle;

public class AddServiceEvent {

    protected Shell shell;
    private Text eventDesc;
    private Text serviceTime;
    private Text serviceKm;
    private Label errorLabel;
    private ArrayList<Part> partsList;
    
    private AccessVehicle accessVehicle;
    private Vehicle currVehicle;
    private Part part;

    /**
     * Open the window.
     */
    public void open(Vehicle v, Part p) {
        Display display = Display.getDefault();
        this.currVehicle = v;
        this.part = p;
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
     * 
     * @wbp.parser.entryPoint
     */
    protected void createContents() {
        shell = new Shell();
        shell.setSize(440, 298);
        shell.setText("Add Service Event");
        accessVehicle = new AccessVehicle();
        GridLayout windowLayout = new GridLayout();
        windowLayout.numColumns = 2;
        shell.setLayout(windowLayout);
        
        Label infoLabel = new Label(shell, SWT.NONE);
        infoLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
        infoLabel.setText("ID: " + part.getPartDesc());
        
        Label eventDescLabel = new Label(shell, SWT.NONE);
        eventDescLabel.setText("Enter service event description:");
        
        eventDesc = new Text(shell, SWT.BORDER);
        eventDesc.setLayoutData(new GridData(SWT.FILL, SWT.LEFT, true, false, 1, 1));
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        
        Label serviceTimeLabel = new Label(shell, SWT.NONE);
        serviceTimeLabel.setText("Service every X amount of days:");
        
                
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
        
        Label orLabel = new Label(shell, SWT.NONE);
        orLabel.setText("OR");
        new Label(shell, SWT.NONE);
        
        Label serviceKmLabel = new Label(shell, SWT.NONE);
        serviceKmLabel.setText("Service every X kilometers:");
        
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
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        
        Button cancelButton = new Button(shell, SWT.NONE);
        cancelButton.setText("Cancel");
        cancelButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                shell.close();
            }
        });
        
        Button addButton = new Button(shell, SWT.NONE);
        addButton.setText("Add Event");
        addButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	accessVehicle = new AccessVehicle();
                if (serviceTime.getText() != "" && checkDesc() && checkTime()) {
//                	accessVehicle.addServiceEvent( v, new ServiceItem(eventDesc.getText(), Long.parseLong(serviceTime.getText()), v.getDateLastServiced()),
//                                      new Part(partsComboBox.getItem(partsComboBox.getSelectionIndex())));
                    part.addServiceItem(new ServiceItem(eventDesc.getText(), Long.parseLong(serviceTime.getText()), currVehicle.getDateLastServiced()));
                    //access.updateVehicle();
                    shell.close();
                } else if (serviceKm.getText() != "" && checkDesc() && checkKilos()) {
//                	accessVehicle.addServiceEvent( v, new ServiceItem(eventDesc.getText(), Integer.parseInt(serviceKm.getText()), v.getKmLastServiced()),
//                                      new Part(partsComboBox.getItem(partsComboBox.getSelectionIndex())));
                    part.addServiceItem(new ServiceItem(eventDesc.getText(), Integer.parseInt(serviceKm.getText()), currVehicle.getKmLastServiced()));
                    //access.updateVehicle();
                    shell.close();
                } else if (checkDesc()) {
                    errorLabel.setText("Please enter a valid time or kilometer service indicator.");
                    errorLabel.pack();
                } 
                else {
                    errorLabel.setText("Please select a part from the parts list.");
                    errorLabel.pack();
                }
            }
        });
    }
    
    
    private boolean checkDesc() {
        if (eventDesc.getCharCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    private boolean checkTime() {
        if (serviceTime.getCharCount() > 0 && serviceTime.getText().matches("[0-9]+") && serviceTime.getText().matches("[0-9]*")) {
            return true;
        } else {
            return false;
        }
    }
    
    private boolean checkKilos() {
        if (serviceKm.getCharCount() > 0 && serviceKm.getText().matches("[0-9]+") && serviceKm.getText().matches("[0-9]*")) {
            return true;
        } else {
            return false;
        }
    }
}
