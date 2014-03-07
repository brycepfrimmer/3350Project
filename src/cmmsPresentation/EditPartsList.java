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
import cmmsObjects.Vehicle;


public class EditPartsList {

    protected Shell shlEditPartsList;

    private Vehicle currVehicle;
    private Button btnGoBack;
    private Button btnAdd;
    private Button btnRemove;
    private Table partsListTable;
    private ArrayList<Part> list;
    
    private AccessVehicle access;
    /**
     * Open the window.
     */
    public void open(Vehicle v) {
        Display display = Display.getDefault();
        createContents();
        access = new AccessVehicle();
        currVehicle = v;
        //list = access.getPartsList(v.getID());
        list = currVehicle.getPartsList();
        if (!list.isEmpty()) {
            updateList();
        }
        shlEditPartsList.open();
        shlEditPartsList.layout();
        while (!shlEditPartsList.isDisposed()) {
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
        shlEditPartsList = new Shell();
        shlEditPartsList.setText("Parts List");
        shlEditPartsList.setSize(689, 474);

        btnGoBack = new Button(shlEditPartsList, SWT.NONE);
        btnGoBack.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                shlEditPartsList.close();
            }
        });
        btnGoBack.setBounds(588, 401, 75, 25);
        btnGoBack.setText("Go Back");

        btnAdd = new Button(shlEditPartsList, SWT.NONE);
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

        btnRemove = new Button(shlEditPartsList, SWT.NONE);
        btnRemove.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int selected = partsListTable.getSelectionCount();
                if (selected == 0) {
                    // Display no selection error
                    MessageBox mb = new MessageBox(shlEditPartsList,
                            SWT.ICON_ERROR | SWT.OK);
                    mb.setMessage("Error: You have not selected any vehicles to remove");
                    mb.setText("Removing Vehicles");
                    mb.open();
                } else if (selected > 1) {
                    // Display multiple selection error
                    MessageBox mb = new MessageBox(shlEditPartsList,
                            SWT.ICON_WARNING | SWT.YES | SWT.NO);
                    mb.setMessage("Warning: You have selected multiple vehicles.\nDo you wish to continue?");
                    mb.setText("Removing Vehicles");
                    int response = mb.open();

                    if (response == SWT.YES) {
                        int[] selections = partsListTable.getSelectionIndices();
                        for (int i = 0; i < selected; i++) {
                            //DELROY HAD
                            //list.remove(new Part(partsListTable.getItem(
                            //we want below for ensurance on duplicate or non-existent parts I assume
                            //discuss with cody
                            currVehicle.removePart(partsListTable.getItem(
                                    selections[i]).getText(0));
                        }

                        // Update list with the new Vehicles
                        updateList();
                    }
                } else {
                    // Display multiple selection error
                    MessageBox mb = new MessageBox(shlEditPartsList,
                            SWT.ICON_WARNING | SWT.YES | SWT.NO);
                    mb.setMessage("Warning: Do you want to delete the selected object?");
                    mb.setText("Removing Vehicles");
                    int response = mb.open();

                    if (response == SWT.YES) {
                        // Should only have one item selected
                        //list.remove(new Part(partsListTable.getItem(
                        //see comment above <same situation>
                        currVehicle.removePart(partsListTable.getItem(
                                partsListTable.getSelectionIndex()).getText());
                        // Update list with the new Vehicles
                        updateList();
                    }
                }
            }
        });

        btnRemove.setBounds(588, 74, 75, 25);
        btnRemove.setText("Remove");

        partsListTable = new Table(shlEditPartsList, SWT.BORDER
                | SWT.FULL_SELECTION);
        partsListTable.setBounds(10, 10, 568, 416);
        partsListTable.setHeaderVisible(true);
        partsListTable.setLinesVisible(true);
    }

    private void updateList() {
        TableItem ti; // Table item for adding parts to the table
        
        //access.setPartsList(currVehicle.getID(), list);

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
