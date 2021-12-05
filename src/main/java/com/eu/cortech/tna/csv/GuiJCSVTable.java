/******************************************************************************
 *                                                                            *
 *    Copyright   : Cortech Information Ltd (2021)                            *
 *                                                                            *
 *    Name        : GuiJCSVTable                                              *
 *                                                                            *
 *    Usage       : Main Class file                                           *
 *                                                                            *
 *    Description : This is the main class for the GUI to retrieve data from  *
 *                  a CSV file. This data is displayed as a JTable on a swing *
 *                  frame to allow the "Add", "Update", "Delete" and "Save".  *
 *                                                                            *
 *    History                                                                 *
 *    =======                                                                 *
 *    Name          Date       Release                                        *
 *    McCormack T   03/12/2021 Initial Release                                *
 *                                                                            *
 ******************************************************************************/
package main.java.com.eu.cortech.tna.csv;

import main.java.com.eu.cortech.tna.gui.GuiCSV;

/**
 * Main executable class.
 * 
 * @author mccormackt
 *
 */
public class GuiJCSVTable 
{
    private static GuiCSV guiCSV;

    /**
     * Main class too execute gui.
     * 
     * @param args
     */
    public static void main(String[] args) 
    {
        guiCSV = new GuiCSV();
     //   GuiSCV.display();
    }

}
