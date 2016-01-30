/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicalinterface;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import networking.Speaker;

/**
 *
 * @author kosma
 */
public class MenuPanel extends JPanel implements ActionListener{
    int activeOne=0;
    JLabel name, id, type, loc;
    JButton left, right, edit;
    Slot activeSlot=null;
    MainFrame mf;
    public MenuPanel(MainFrame mf) throws IOException
    {
        this.mf=mf;
        name=new JLabel("No selected");
        name.setIcon(new ImageIcon(ImageIO.read(new File("icons\\financier.png"))));
        id=new JLabel("No selected");
        id.setIcon(new ImageIcon(ImageIO.read(new File("icons\\financier.png"))));
        type=new JLabel("No selected");
        type.setIcon(new ImageIcon(ImageIO.read(new File("icons\\financier.png"))));
        //loc=new JLabel("No selected");
        left = new JButton("<");
        left.addActionListener(this);
        edit = new JButton("O");
        edit.addActionListener(this);
        right = new JButton(">");
        right.addActionListener(this);
        JPanel mainPart= new JPanel();
        mainPart.setLayout(new GridLayout(0,1));
        mainPart.add(name);
        mainPart.add(id);
        mainPart.add(type);
        //mainPart.add(loc);
        setLayout(new BorderLayout());
        add(mainPart,BorderLayout.CENTER);
        JPanel controlPart = new JPanel();
        controlPart.setLayout(new GridLayout(1,0));
        controlPart.add(left);
        controlPart.add(edit);
        controlPart.add(right);
        add(controlPart,BorderLayout.SOUTH);
    }
    public void newSelected (Slot sl)
    {
        activeOne=0;
        activeSlot=sl;
        refresh();
    }
    public void refresh()
    {
        //loc.setText(activeSlot.posX+", "+activeSlot.posY);
        if(activeSlot!=null)
        {
            if((activeSlot.sensor.size()>activeOne)&&(activeSlot.sensor.size()>0))
            {
                name.setText(activeSlot.sensor.get(activeOne).deviceName);
                id.setText(activeSlot.sensor.get(activeOne).ID+"");
                type.setText(activeSlot.sensor.get(activeOne).deviceCode.toString());
                edit.setText((activeOne+1)+"/"+activeSlot.sensor.size());
            }else{
                if(activeSlot.sensor.size()<=0)
                {
                    name.setText("Empty");
                    id.setText("Empty");
                    type.setText("Empty");
                    edit.setText("O");
                }else{
                    activeOne=activeSlot.sensor.size()-1;
                    name.setText(activeSlot.sensor.get(activeOne).deviceName);
                    id.setText(activeSlot.sensor.get(activeOne).ID+"");
                    type.setText(activeSlot.sensor.get(activeOne).deviceCode.toString());
                    edit.setText((activeOne+1)+"/"+activeSlot.sensor.size());
                }
            }
            mf.rebuild();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==left)
        {
            activeOne--;
            if(activeOne<0)
            {
                activeOne=0;
            }
            refresh();
        }else{
            if(ae.getSource()==right)
            {
                activeOne++;
                if(activeOne>=activeSlot.sensor.size())
                {
                    activeOne=activeSlot.sensor.size()-1;
                }
                refresh();
            }else{
                if(ae.getSource()==edit)
                {
                    if(activeSlot.sensor.size()>activeOne)
                    {
                        Speaker sp=activeSlot.sensor.get(activeOne).parent;
                        activeSlot.deActivate(sp);
                        mf.mapPanel.newArrival(sp);
                        mf.rebuild();
                    }
                }
            }
        }
    }
}
