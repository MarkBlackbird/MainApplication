/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicalinterface;

import datainputandhandling.DataFileInput;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import networking.NetworkHandler;

/**
 *
 * @author Marek Sienkiewicz
 */
public class MainFrame extends JFrame implements WindowListener{
    MapPanel mapPanel;
    MenuPanel menuPanel;
    public NetworkHandler nh;
    int sizeX, sizeY;
    public MainFrame()
    {
        DataFileInput dif = new DataFileInput("data.txt");
        
        mapPanel = new MapPanel(this,dif);
        try {
            menuPanel = new MenuPanel(this);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setLayout(new BorderLayout());
        add(mapPanel, BorderLayout.CENTER);
        add(menuPanel, BorderLayout.EAST);

        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addWindowListener(this);
        
        rebuild();
        
        nh = new NetworkHandler(10800,mapPanel);
    }

    public void rebuild()
    {
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dialogSize = getSize();
        setLocation(screenSize.width/2-dialogSize.width/2, screenSize.height/2-dialogSize.height/2);
    }
    @Override
    public void windowOpened(WindowEvent we) {}

    @Override
    public void windowClosing(WindowEvent we) {
        try {
            nh.close();
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void windowClosed(WindowEvent we) {}

    @Override
    public void windowIconified(WindowEvent we) {}

    @Override
    public void windowDeiconified(WindowEvent we) {}

    @Override
    public void windowActivated(WindowEvent we) {}

    @Override
    public void windowDeactivated(WindowEvent we) {}
}
