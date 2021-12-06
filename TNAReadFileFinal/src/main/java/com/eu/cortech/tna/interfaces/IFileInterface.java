/******************************************************************************
 *                                                                            *
 *    Copyright   : Cortech Information Ltd (2021)                            *
 *                                                                            *
 *    Name        : IFileInterface                                            *
 *                                                                            *
 *    Usage       : Interface file                                            *
 *                                                                            *
 *    Description : This class defines the signatures for the file functions. *
 *                  As an iterface it csnbe used to define futctionality for  8
 *                  other file actiivities - such as flat file, tab etc.       *
 *                                                                            *
 *    History                                                                 *
 *    =======                                                                 *
 *    Name          Date       Release                                        *
 *    McCormack T   03/12/2021 Initial Release                                *
 *                                                                            *
 ******************************************************************************/
package main.java.com.eu.cortech.tna.interfaces;

import java.io.File;
import java.util.ArrayList;

/**
 * Interface for File functions.
 * 
 * @author mccormackt
 */
public interface IFileInterface
{
    // Method to import the contents of a CSV file.
    public ArrayList<String[]> fileImport( String strFileName );
    
    // Method to import the contents of a CSV file.
    public ArrayList<String[]> fileImport(File DataFile);
    
    // Method to export the contents to a CSV file.
    public boolean fileExport( String strOutputFile, ArrayList<String[]> arrData);    
}
