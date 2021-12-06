/******************************************************************************
 *                                                                            *
 *    Copyright   : Cortech Information Ltd (2021)                            *
 *                                                                            *
 *    Name        : CSVFile                                                   *
 *                                                                            *
 *    Usage       : Class file                                                *
 *                                                                            *
 *    Description : This function is used to define the CSV file processing.  *
 *                  In one instance the contents of the CSV file is read in   *
 *                  and processed into an array buffer. In operation, any     *
 *                  values that have been added, amended or deleted can be    *
 *                  exported to the same file that was used for the import    *
 *                  or exported to another different name file.               *
 *                                                                            *
 *    History                                                                 *
 *    =======                                                                 *
 *    Name          Date       Release                                        *
 *    McCormack T   03/12/2021 Initial Release                                *
 *                                                                            *
 ******************************************************************************/
package main.java.com.eu.cortech.tna.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import main.java.com.eu.cortech.tna.interfaces.IFileInterface;
import main.java.com.eu.cortech.tna.gui.GuiCSV;

/**
 * Main CSVFile class.
 * 
 * @author mccormackt
 *
 */
public class CSVFile implements IFileInterface
{    
    private final ArrayList<String[]> arrTNSData = new ArrayList<String[]>();

    private String[] arrStr;

    private static GuiCSV parent;
    
    
    /**
     * Method to get the GUI parent
     * 
     * @return the guiCSV parent
     */
    public static GuiCSV getParent()
    {
        return parent;
    }

    
    /**
     * Methid to set the FUI Parent.
     * 
     * @param parent the parent to set
     */
    public void setParent(GuiCSV parent)
    {
        this.parent = parent;
    }

    /**
     * Constructor for the GuiCSV.
     * 
     * @param gui - guiCSV constructor.
     */
    public CSVFile( final GuiCSV gui )
    {    
        setParent(gui);   
    }

    /**
     * Empty constructor for the CSVFile.
     */
    public CSVFile()
    {
    }

    /**
     * Method to import the contents of a CSV file.
     * 
     * @param DataFile - the input data file.
     * @return array of csv data.
     */
    public ArrayList<String[]> fileImport(File DataFile) 
    {
        // Oen and read the data file.
        try
        {
            BufferedReader brd = new BufferedReader(new FileReader(DataFile));

            // Continue to read the CSV data file. separate the contents by ",".
            while (brd.ready())
            {
                String st = brd.readLine();
                arrStr = st.split(",");
             
                // Add the split string to the arraylist
                arrTNSData.add(arrStr);
            }

        }
        // Catch any exception. Output message.
        catch (Exception e) 
        {
            String errmsg = e.getMessage();
            System.out.println("File not found:" + errmsg);
        }
        
        // Return the data array.
        return arrTNSData;
    }
    
    /**
     * Method to export the contents of the data array to an export file.
     * 
     * @param strOutputFile - the output data file.
     * @param arrData - data array to output.
     * @return true if successful, false otherwise. 
     */
    @Override
    public boolean fileExport( String strOutputFile, ArrayList<String[]> arrData) 
    {

        // Define the column for the header titles.
        try
        {            
            String[] columnName = new String[getParent().getNumArrCol()];

            // Set the Default Table Model for the header.
            DefaultTableModel dtm = new DefaultTableModel(columnName,0);
                        
            // Iterate for the data row. Add to table model.
            for (Object s : arrData) 
            {
                dtm.addRow( (Object[]) s);
            }
            
            // Set the table to the default table model.
            JTable jTable = new JTable(new DefaultTableModel());
            
            // Set the table model.
            jTable.setModel(dtm);
            
            // Retrieve the tale model.
            TableModel model = jTable.getModel();
            
            // Define the csv file writer constructor for the output file.
            FileWriter csv = new FileWriter(new File(strOutputFile));
            
            // Iterate for the number of actual rows.
            for (int i = 0; i < getParent().getNumArrRow(); i++) 
            {    
                // Iterate for the number of actual columns. Write the call value
                // to the output file
                for (int j = 0; j < getParent().getNumArrCol(); j++)
                {
                    csv.write(model.getValueAt(i, j).toString()+ ",");
                }
                
                // Write new line.
                csv.write("\n");
            }

            // Close file constructor.
            csv.close();
            
            return true;
        }
        // Catch exception and print stack trace.
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        
        return false;
    }
    
    /**
     * Method to import the contents of a CSV file.
     * 
     * @param strFileName - the input data file as string. 
     * @return array of csv data.
     */
    @Override
    public ArrayList<String[]> fileImport( String strFileName ) 
    {
        File fileName = new File( strFileName );
        
        return fileImport( fileName );
    }

}
