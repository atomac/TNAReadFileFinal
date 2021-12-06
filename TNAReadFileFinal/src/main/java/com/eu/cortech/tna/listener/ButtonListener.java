/******************************************************************************
 *                                                                            *
 *    Copyright   : Cortech Information Ltd (2021)                            *
 *                                                                            *
 *    Name        : ButtonListener                                            *
 *                                                                            *
 *    Usage       : Class file                                                *
 *                                                                            *
 *    Description : This function is used to define the ButtonListner. The    *
 *                  actions defined are "Add", "Delete", "Update" and "Save". *
 *                  This class also includes a Table Model Listener to react  * 
 *                  to editable JTable cell changes.                          *
 *                                                                            *
 *    History                                                                 *
 *    =======                                                                 *
 *    Name          Date       Release                                        *
 *    McCormack T   03/12/2021 Initial Release                                *
 *                                                                            *
 ******************************************************************************/
package main.java.com.eu.cortech.tna.listener;

import main.java.com.eu.cortech.tna.file.CSVFile;
import main.java.com.eu.cortech.tna.gui.GuiCSV;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * This class Handles Button events
 */
public class ButtonListener implements ActionListener
{
    //Establish constructors.
    private final GuiCSV parent;
    
    private final JTable jTable;
    
    private TableModel tableModel;
    
    private CSVFile csvFile = new CSVFile();
 
    // Define button names.
    private static final String ADD_BUTTON = "Add";
    private static final String SAVE_BUTTON = "Save";
    private static final String UPDATE_BUTTON = "Update";
    private static final String DELETE_BUTTON = "Delete";

    // Class references to cell changes.
    String strOldName;
    String strNewName;
    String column;

    // Column and row index values.
    int indCol;
    int indRow;
    
    /**
     * CLass for the Button Listener with required constructors.
     * 
     * @param gui - GUI to accept this class output.
     * @param jTable - table of array data.
     */
    public ButtonListener(GuiCSV gui, JTable jTable) 
    {    
        this.parent = gui;  
        this.jTable = jTable;
    
        // Define the Table ModelListner for the cell value change/
    
        this.jTable.getModel().addTableModelListener((TableModelListener) new TableModelListener()
        {

            @Override
            public void tableChanged(TableModelEvent e)
            {
              //  TableCellListener tcl = (TableCellListener) e.getSource();
                if (e.getType() == TableModelEvent.UPDATE) 
                {
                    
                //    EditAttributeCommand command = CommandProcessor.getInstance().getCommand(EditAttributeCommand.class);
                //    RemoveAttributeCommand removecommand = CommandProcessor.getInstance().getCommand(RemoveAttributeCommand.class);
                    tableModel = (TableModel) e.getSource();
                    strNewName = (String) tableModel.getValueAt(e.getLastRow(), 1);               
    
                    indCol = e.getColumn();
                    indRow = e.getLastRow();
                }
            }

        });
    }

    /**
     * Action event performed.
     * 
     * @param ae - Action event.
     */
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        Object obj = ae.getSource();

        // Verify instance of JButton.
        if (obj instanceof JButton)
        {
            JButton jButton = (JButton) obj;
            
            String strButton = jButton.getText();
         
            // Check if the button depressed is "Add". Not active yet
            if (strButton.equals(ADD_BUTTON))
            {
                JOptionPane.showMessageDialog(null, "ADD option is not yet avaiable as it outside the" +
                                          " the scope of this example but is included for completeness.");
                
             //   this.parent.getOutputfile();
     //           this.parent.addTableRow(this.parent.getData() );//   as( GUI.getOutputfile(), this.parent.getData() );
         
     //           this.parent.addTableRow();//   as( GUI.getOutputfile(), this.parent.getData() );
            }
            // Check if the button depressed is "Save". Save the array list to an output file.
            else if (strButton.equals(SAVE_BUTTON))
            {
                csvFile.fileExport( this.parent.getOutputfile(), this.parent.getData() );
            }
            // Check if the button depressed is "Update". Update the cell value.
            else if (strButton.equals(UPDATE_BUTTON))
            {                
                this.parent.checkValueChange(indRow, indCol, strNewName);
            }
            // Check if the button depressed is "Delete". Not active yet.
            else if (strButton.equals(DELETE_BUTTON))
            {
                JOptionPane.showMessageDialog(null, "DELETE option is not yet avaiable as it outside the" +
                                " the scope of this example but is included for completeness.");
            }

        }

    }

}
