package main.java.com.eu.cortech.tna.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;

public class ChangeListener implements ActionListener
{    

        
    public void stateChanged(ChangeEvent event) 
    {
        AbstractButton aButton = (AbstractButton) event.getSource();
    
        ButtonModel aModel = aButton.getModel();  
    }
    

    @Override
    public void actionPerformed(ActionEvent e)
    {
        JButton jButton = (JButton) e.getSource();    
    }

    
}


