/******************************************************************************
 *                                                                            *
 *    Copyright   : Cortech Information Ltd (2021)                            *
 *                                                                            *
 *    Name        : GuiCSV                                                    *
 *                                                                            *
 *    Usage       : Class file                                                *
 *                                                                            *
 *    Description : This function is used to define the Gui for the CSV file  *
 *                  processing. This includes all of the GUI constructs as    *
 *                  well as the action and event listeners.                   * 
 *                                                                            *
 *                  The data displayed is part of of JTable with functions    *
 *                  to "Add", "Delete", "Update" and "Save". For the purpose  *
 *                  of this demonstration, the only functions that are active *
 *                  are the "Update" and "Save" functions. The "Add" and the  *
 *                  "Delete" buttons are included for completeness.           *
 *                                                                            *
 *    History                                                                 *
 *    =======                                                                 *
 *    Name          Date       Release                                        *
 *    McCormack T   03/12/2021 Initial Release                                *
 *                                                                            *
 ******************************************************************************/
package main.java.com.eu.cortech.tna.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import main.java.com.eu.cortech.tna.file.CSVFile;
import main.java.com.eu.cortech.tna.listener.ButtonListener;

/**
 * Main Class for the Gui CSV.
 * 
 * @author mccormackt.
 *
 */
public class GuiCSV implements TableModelListener
{    
    private static final long serialVersionUID = 1L;

    // Decalare variables.
    String strNewName;
    String strOldName;
    String strColumn;
    
    // Declare header values.
    String[]   arrHeader;
    
    // CSVData array.
    ArrayList<String[]> arrCSVData;
    
    // Local variables for manipulation
    Object[][] arrObject;
 //   Object[][] arrClone;
    
    private static JTable table;
    
    private TableModel tableModel;
    
    // Local variables for the actual data array size.
    private int numArrRow;
    private int numArrCol;
    
    // Input and output files.
    private static final String strTNAdataFile = "./src/main/resources/tnadata.csv";
    private static final String strTNAdataOutFile = "./src/main/resources/tnadataout.csv";

    // Constructor to describe the csv file.
    private File csv;
    
    /**
     * Main class to execute the GUI.
     */
    public GuiCSV() 
    {            
        instantiateGUI();
    }

    /**
     * @return Returns the CSV file held by this class
     */
    public File getCSV() 
    {
        return this.csv;
    }

    /**
     * @param csv CSV file from which data will be extracted
     */
    public void setCSV(File csv) 
    {
        this.csv = csv;
    }
    
    /**
     * Method to update the column values
     * 
     * @param csvFileName - output CSV file
     * @param column - column title for update.
     * @param oldValue - previous cell value in column.
     * @param newValue - new cell value in column.
     */
    private void updateRowValues(String csvFileName, String column, String oldValue, String newValue)
    {
        // Iterate for the actual number of rows.
        for (int i = 0; i < numArrRow; i++)
        {
            // Iterate for the actual number of columns.
            for (int j = 0; j < numArrCol; j++)
            {
                // Check the column to replace the cell value with a new value.
                if (arrCSVData.get(i)[j].toLowerCase().contains(column.toLowerCase()) )  
                {
                   // Having found the column, iterate over the rows in the column, check
                   // that the old value is as required.
                   for ( int k = 0; k < numArrRow; k++)
                   {
                       // Check that the old value matches the previous. Replace the entry with
                       // the new value.
                       if (arrCSVData.get(k)[j].equals(oldValue)) 
                       {
                           arrCSVData.get(k)[j] = newValue;
                           
                           // Action completed. Break/
                           break;
                       }
                   }
            
                  
                }
            }
        }
                 
        // Assign the csvFile constructor. Export the data file.
        CSVFile csvFile = new CSVFile(this);
        csvFile.fileExport(csvFileName, arrCSVData);             
    }

    /**
     * Instantiates GUI
     */
    private void instantiateGUI() 
    { 
        // Obtain the data and column headers.
        arrObject = testData();
        
        // Create frame for for GUI display
        JFrame frame = new JFrame();
        
        // Set frame appearance.
        frame.setMinimumSize(new Dimension(600, 300));
        frame.setLayout(new BorderLayout());

        // Using the header and the data array, set the Table model and set to JTable for
        // display.
        DefaultTableModel defaultTableModel = new DefaultTableModel(arrObject, arrHeader);  
        table = new JTable(defaultTableModel); 
//        
//        jTableAdd = table;
//        

        // Add a border
        table.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        table.setRowMargin (5);
        
   //     table.getModel().addTableModelListener(changeListner);
        
        // Set the JTable display to scroll
        frame.add(new JScrollPane(table));        

        // Establish the button panel
        JPanel btnPnl = new JPanel(new BorderLayout());
        JPanel bottombtnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        // Set the listener for the buttons
        ActionListener actListener = new ButtonListener(this, table);
        
        // Set the Table model listener
        table.getModel().addTableModelListener(this);
        
        // Set the button labels
        JButton jAdd = new JButton("Add");
        JButton jUpdate = new JButton("Update");
        JButton jDelete = new JButton("Delete");
        JButton jSave = new JButton("Save");
        
        // EStablish the button listeners.
        jAdd.addActionListener(actListener);
        jUpdate.addActionListener(actListener); 
        jDelete.addActionListener(actListener);  
        jSave.addActionListener(actListener); 

        // Add the buttons to the button panel.
        bottombtnPnl.add(jAdd);
        bottombtnPnl.add(jUpdate);
        bottombtnPnl.add(jDelete);
        bottombtnPnl.add(jSave);

        // Set the location of the buttonsonthe panel.
        btnPnl.add(bottombtnPnl, BorderLayout.CENTER);

        table.getTableHeader().setReorderingAllowed(false);
        
        // Add the headers, table and buttons to the GUI frame.
        frame.add(table.getTableHeader(), BorderLayout.NORTH);
        frame.add(table, BorderLayout.CENTER);
        frame.add(btnPnl, BorderLayout.SOUTH);

        frame.setTitle("JTable CSV Data.");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Method to react to the Table Model Listener. Use to determine the previous and
     * new cell value.
     * 
     * @param indRow - row of the old value cell.
     * @param indCol - column of the old value cell
     * @param strNewValue - cell new value.
     */
    public void checkValueChange( int indRow,  int indCol, String strNewValue )
    {
        // Set the cell old value and the column header.
        strOldName = (String) arrObject[indRow][indCol];
        strColumn = (String) arrHeader[indCol];

        // Update the cell value and output to file.
        updateRowValues(strTNAdataOutFile, strColumn, strOldName, strNewValue);
    }
    
    /**
     *  Method the add a table row to JTable.
     */
    
    public void addTableRow()
    {
  //      String[] columnName =new String[getNumArrCol()];

     //   DefaultTableModel dtm = new DefaultTableModel(columnName,0);
     //   DefaultTableModel dtm = new DefaultTableModel();
        
 //       ArrayList<String[]> arrList = new  ArrayList<String[]>();
        
        
        
 //       JTable jTable = new JTable(new DefaultTableModel());
        
      //  jTable.setModel(dtm);
        
      //  TableModel model = jTableAdd.getModel();
//        DefaultTableModel tableModel = new DefaultTableModel();
//        JTable table = new JTable(tableModel);
        
        
       // DefaultTableModel model = (DefaultTableModel) jTableAdd.getModel();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        

        Object[] objectRow = new Object[getNumArrCol()];
        
        // Insert new table row.
        model.insertRow(getNumArrRow(), objectRow);
        
      //  model.addRow(objectRow);
    }

    /**
     * Method to retrieve the date from CSV data file.
     * 
     * @return Object[][] of test data to populate the JTable.
     */
    private Object[][] testData() 
    {  
        // Set the CSV file
        setCSV(new File(strTNAdataFile));
        
        // Define the csvFile constructor.
        CSVFile csvFile = new CSVFile(this);

        // Retrieve the CSV data. 
        arrCSVData = csvFile.fileImport(getCSV());
        
        // Set the first row of the data array - the column headers.
        String[] strCSVValue = arrCSVData.get(0);
        
        // Set the column headers.
        arrHeader = testHeaders( strCSVValue );

        // Determine the actual size (row,columns) for the data array.
        findSize( arrCSVData );
      
        // Set the data array for process without the headers.
        Object[][] arrData = new Object[numArrRow-1][numArrCol];

        // Iterate for the actual number of rows. Retrieve the row.
        for ( int j = 0; j < numArrRow; j++)
        {
            String[] str = arrCSVData.get(j);
 
            // If not the first row, process
            if ( j != 0)
            {
                // Iterate for the actual number of columns.
                for ( int i = 0; i < numArrCol; i++)
                {                    
                    //Ensure that the row/cell is not whitespace. Remove quotes and set 
                    // to the array listing
                    if ((!str[i].equals("")) && (!str[i].equals(" ")) && (str[i] != null))
                    {  
                        arrData[j-1][i] = str[i].replaceAll("\"", "");
                    }
                }
            }
        }

        // Return data array list.
        return arrData;
    }

    /**
     * Method to return the csv data column headers.
     * 
     * @return String[] of test column headers
     */
    private String[] testHeaders( String[] arrHeader ) 
    {
        int nColumns;
        
        String strValue;
        
        List<String> unIndexHead = new ArrayList<>();
           
        // Deviation from requirements. Denote headers by initial "#". Set number of columns.
        if (arrHeader[0].contains("#"))
        {
            nColumns = arrHeader.length;
         
            // Iterate for the number of columns
            for ( int i = 0; i < nColumns; i++ )
            {
                // If initial header cell, remove "#" and add to header index.
                if (i == 0)
                {
                    strValue = arrHeader[0].substring(1, 2).toUpperCase() + arrHeader[0].substring(2);
                    
                    unIndexHead.add(strValue);
                }
                // Otherwise process other column headers.
                else
                {
                    // Check that the column header value is not blank and not null. 
                    // Remove whitespace and add to header index.
                    if ((!arrHeader[i].equals("")) && (arrHeader[i] != null))
                    {
                        arrHeader[i] = arrHeader[i].replaceAll("\\s","");
                        strValue = arrHeader[i].substring(0, 1).toUpperCase() + arrHeader[i].substring(1);
                        
                        unIndexHead.add(strValue);
                    }
                }
            }
             
        }
        
        // Cast arraylist to String array. and return.
        String[] columnNames = unIndexHead.toArray(new String[0]);

        return columnNames;

    }

    /**
     * Method to determine actual row and column.
     * 
     * @param arrCSVData - data array list.
     */
    private void findSize( ArrayList<String[]> arrCSVData )
    {
        numArrCol = 0;
        numArrRow = 0;
    
        // Define the row width - number of columns.
        String[] str = arrCSVData.get(0);
        
        int nSize = str.length;
        numArrCol = nSize;
        
        // Iterate over the array list. Increment the number of rows.
        for (String[] strArr: arrCSVData)
        {          
            if (strArr[0].matches("\\S+"))
            {
                numArrRow = (numArrRow + 1);
            }
        }

    }


    /**
     * Makes component visible
     */
//    public void display() 
//    {
//        this.se   setVisible(true);
//    }

    
    
    /**
     * Method to return the CSV data. If no CSV file has been set, 
     * returns null.
     * 
     * @return Data from CSV file as Object[][]
     */
    public ArrayList<String[]> getData() 
    {
        return this.arrCSVData;
    }
    
    /**
     * Method to return the output file.
     * 
     * @return the OutputFile
     */
    public String getOutputfile()
    {
        return strTNAdataOutFile;
    }
    
    /**
     * Method to return the actual number of rows of the data.
     * 
     * @return the actual number of rows.
     */
    public int getNumArrRow()
    {
        return numArrRow;
    }

    
    /**
     * Method to set the actual number of rows of the data.
     * 
     * @param numArrRow the actual number of rows of the data.
     */
    public void setNumArrRow(int numArrRow)
    {
        this.numArrRow = numArrRow;
    }

    
    /**
     * Method to return the actual number of columns of the data.
     * 
     * @return the numArrCol
     */
    public int getNumArrCol()
    {
        return numArrCol;
    }

    
    /**
     * @param numArrCol the numArrCol to set
     */
    public void setNumArrCol(int numArrCol)
    {
        this.numArrCol = numArrCol;
    }

    @Override
    public void tableChanged(TableModelEvent e)
    {

        // TODO Auto-generated method stub
        
    }
    
}
