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
 * @author kosma
 */
public class MainFrame extends JFrame implements WindowListener{
    MapPanel mapPanel;
    JPanel menuPanel;
    public NetworkHandler nh;
    int sizeX, sizeY;
    public MainFrame()
    {
        DataFileInput dif = new DataFileInput("data.txt");
        sizeX=dif.numX*88+100;//88 is size of icon, 100 is for menuPanel
        sizeY=dif.numY*88;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(sizeX,sizeY);
        setLocation(screenSize.width/2-sizeX/2, screenSize.height/2-sizeY/2);
        mapPanel = new MapPanel(this,dif);//todo create custom class
        menuPanel = new JPanel();//todo create custom class
        
        //test lines delete after creating custom classes;
        menuPanel.add(new JButton("Menu"));
        
        setLayout(new BorderLayout());
        add(mapPanel, BorderLayout.CENTER);
        add(menuPanel, BorderLayout.EAST);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addWindowListener(this);
        
        nh = new NetworkHandler(10800,mapPanel);
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
