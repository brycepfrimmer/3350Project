package cmmsPresentation;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;

import cmmsBusiness.AccessVehicle;
import cmmsObjects.Part;
import cmmsObjects.Vehicle.Vehicle;

import acceptanceTests.Register; 
import acceptanceTests.EventLoop;

public class EditPartsList {

    protected Shell shell;

    private Vehicle currVehicle;
    private Button btnGoBack;
    private Button btnAdd;
    private Button btnRemove;
    private Button btnAddServiceEvent;
    private Table partsListTable;
    private ArrayList<Part> list;
    
    private AccessVehicle accessVehicle;
    /**
     * Open the window.
     */
    public void open(Vehicle v) {
        Display display = Display.getDefault();
        Register.newWindow(this);
        createContents();
        accessVehicle = new AccessVehicle();
        currVehicle = v;
//        list = accessVehicle.getPartsList(v.getID());
        list = currVehicle.getPartsList();
        if (!list.isEmpty()) {
            updateList();
        }
        shell.open();
        shell.layout();
        if( EventLoop.isEnabled() )
        {
	        while (!shell.isDisposed()) {
	            if (!display.readAndDispatch()) {
	                display.sleep();
	            }
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
        shell.setText("Parts List");
        shell.setSize(720, 474);

        btnGoBack = new Button(shell, SWT.NONE);
        btnGoBack.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                shell.close();
            }
        });
        btnGoBack.setBounds(588, 401, 75, 25);
        btnGoBack.setText("Go Back");

        btnAdd = new Button(shell, SWT.NONE);
        btnAdd.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                AddPart add = new AddPart();
                add.open(currVehicle);
                updateList();
            }
        });
        btnAdd.setBounds(588, 43, 75, 25);
        btnAdd.setText("Add");

        btnRemove = new Button(shell, SWT.NONE);
        btnRemove.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int selected = partsListTable.getSelectionCount();
                accessVehicle = new AccessVehicle();
                if (selected == 0) {
                    // Display no selection error
                    MessageBox mb = new MessageBox(shell,
                            SWT.ICON_ERROR | SWT.OK);
                    mb.setMessage("Error: You have not selected any vehicles to remove");
                    mb.setText("Removing Vehicles");
                    mb.open();
                } else if (selected > 1) {
                    // Display multiple selection error
                    MessageBox mb = new MessageBox(shell,
                            SWT.ICON_WARNING | SWT.YES | SWT.NO);
                    mb.setMessage("Warning: You have selected multiple vehicles.\nDo you wish to continue?");
                    mb.setText("Removing Vehicles");
                    int response = mb.open();

                    if (response == SWT.YES) {
                        int[] selections = partsListTable.getSelectionIndices();
                        for (int i = 0; i < selected; i++) {
                            accessVehicle.removePart( currVehicle, partsListTable.getItem(
                                    selections[i]).getText(0) );
                        }

                        // Update list with the new Vehicles
                        updateList();
                    }
                } else {
                    // Display multiple selection error
                    MessageBox mb = new MessageBox(shell,
                            SWT.ICON_WARNING | SWT.YES | SWT.NO);
                    mb.setMessage("Warning: Do you want to delete the selected object?");
                    mb.setText("Removing Vehicles");
                    int response = mb.open();

                    if (response == SWT.YES) {
                        // Should only have one item selected
                        accessVehicle.removePart( currVehicle, partsListTable.getItem(
                                 partsListTable.getSelectionIndex()).getText() );
                        // Update list with the new Vehicles
                        updateList();
                    }
                }
            }
        });

        btnRemove.setBounds(588, 74, 75, 25);
        btnRemove.setText("Remove");
        
        btnAddServiceEvent = new Button(shell, SWT.NONE);
        btnAddServiceEvent.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int selected = partsListTable.getSelectionCount();
                if (selected == 0) {
                    // Display no selection error
                    MessageBox mb = new MessageBox(shell, SWT.ICON_ERROR
                            | SWT.OK);
                    mb.setMessage("Error: You have not selected a Vehicle to update.");
                    mb.setText("Add Service Event");
                    mb.open();
                } else if (selected == 1) {
                    // Display UpdateKilometers form
                	
            		AddServiceEvent addse = new AddServiceEvent();
            		addse.open(currVehicle, currVehicle.getPart(partsListTable.getItem(partsListTable.getSelectionIndex()).getText()));
                    // Update list with the new Vehicles
                    updateList();
                } else {
                    // Display multiple selection error
                    MessageBox mb = new MessageBox(shell, SWT.ICON_ERROR
                            | SWT.OK);
                    mb.setMessage("Error: You have selected too many Parts to update.");
                    mb.setText("Add Service Event");
                    mb.open();
                }
            }
        });
        btnAddServiceEvent.setBounds(588, 105, 75, 25);
        btnAddServiceEvent.setText("Add Service Event");
        
        btnAddServiceEvent.pack();

        partsListTable = new Table(shell, SWT.BORDER
                | SWT.FULL_SELECTION);
        partsListTable.setBounds(10, 10, 568, 416);
        partsListTable.setHeaderVisible(true);
        partsListTable.setLinesVisible(true);
        partsListTable.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		if(partsListTable.getSelectionCount() == 0) {
        			btnAddServiceEvent.setEnabled(false);
	        		btnRemove.setEnabled(false);
        		}
        		else {
	        		btnAddServiceEvent.setEnabled(true);
	        		btnRemove.setEnabled(true);
        		}
        	}
        });
    }
   

    private void updateList() {
        TableItem ti; // Table item for adding parts to the table

        // Stop Drawing Table, Empty Table, Rebuild Table, Start Drawing Table
        partsListTable.setRedraw(false);

        if (partsListTable.getItemCount() >= 1)
            partsListTable.remove(0, partsListTable.getItemCount() - 1);

        partsListTable.setRedraw(true);

        for (Part part : list) {
            ti = new TableItem(partsListTable, SWT.NONE);
            ti.setText(part.toString());
        }
    }
}
