/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicalinterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Marek Sienkiewicz
 */
public class AlarmDialog extends JDialog implements ActionListener{
    JButton closeButton;
    MainFrame mf;
    public AlarmDialog (MainFrame mf, String str)
    {
        this.mf=mf;
        try {
            //super(mf,str);
            createAndShowGUI(str);
        } catch (IOException ex) {
            Logger.getLogger(AlarmDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void createAndShowGUI(String str) throws IOException
    {
        // Must be called before creating JDialog for
        // the desired effect
        //JDialog.setDefaultLookAndFeelDecorated(true);
        setUndecorated(true);
        setAlwaysOnTop(true);
        // Set some layout
        setLayout(new BorderLayout());
        
        closeButton = new JButton("Ok.");
        closeButton.addActionListener(this);
        add(closeButton, BorderLayout.SOUTH);
        JLabel ico = new JLabel(str);
        ico.setIcon(new ImageIcon(ImageIO.read(new File("icons\\rarenep.png"))));
        add(ico, BorderLayout.CENTER);

        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //setSize(sizeX,sizeY);
        Dimension dialogSize = getSize();
        setLocation(screenSize.width/2-dialogSize.width/2, screenSize.height/2-dialogSize.height/2);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==closeButton)
        {
            mf.rebuild();
            dispose();
        }
    }
}
