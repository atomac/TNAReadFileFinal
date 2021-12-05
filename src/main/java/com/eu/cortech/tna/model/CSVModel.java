/******************************************************************************
 *                                                                            *
 *    Copyright   : Cortech Information Ltd (2021)                            *
 *                                                                            *
 *    Name        : CSVModel                                                  *
 *                                                                            *
 *    Usage       : Class file                                                *
 *                                                                            *
 *    Description : This class defines the model for the CSV data. This sets  *
 *                  columns, rows and data.                                   *
 *                                                                            *
 *    History                                                                 *
 *    =======                                                                 *
 *    Name          Date       Release                                        *
 *    McCormack T   03/12/2021 Initial Release                                *
 *                                                                            *
 ******************************************************************************/
package main.java.com.eu.cortech.tna.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

/**
 * Set the CSV Model for row and columns on a JTable frame.
 * 
 * @author mccormackt
 */
public class CSVModel extends AbstractTableModel 
{
    private final String[] columnNames = { "File Name", "Origin", "Metadata", "Hash" };
    
    private ArrayList<String[]> Data = new ArrayList<String[]>();

    /**
     * Set the array data object.
     * 
     * @param rs2 - data array.
     */
    public void addCSVData(ArrayList<String[]> rs2)
    {
        this.Data = rs2;
        this.fireTableDataChanged();
    }

    /** 
     * Obtain number of columns in the data model
     * 
     * @return number of columns.
     */
    @Override
    public int getColumnCount() 
    {
        return columnNames.length;// length;
    }

    /** 
     * Obtain number of rows in the data model
     * 
     * @return number of rows.
     */
    @Override
    public int getRowCount()
    {
        return Data.size();
    }

    /** 
     * Obtain name of the column defined by the index.
     * 
     * @param col - column name index.
     * @return number of columns.
     */
    @Override
    public String getColumnName(int col)
    {
        return columnNames[col];
    }

    /** 
     * Obtain value of the cell value.
     * 
     * @param row - row cell index.
     * @param col - col cell index.
     * @return cell value at col, row.
     */
    @Override
    public Object getValueAt(int row, int col) 
    {
        return Data.get(row);
    }
    
}

