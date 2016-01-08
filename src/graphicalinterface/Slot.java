/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicalinterface;

import datainputandhandling.DataFileInput;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import networking.DeviceData;
import networking.Speaker;

/**
 *
 * @author kosma
 */
public class Slot extends JButton{
    DataFileInput dif;
    int posX, posY;
    boolean active=false, locked=true;
    public DeviceData sensor;
    public Slot(DataFileInput dif, int posX, int posY)
    {
        this.dif=dif;
        this.posX=posX;
        this.posY=posY;
        //setPreferredSize(new Dimension(25, 25));//avoid
        setBorderPainted(false);
        setBorder(null);
        //button.setFocusable(false);
        setMargin(new Insets(0, 0, 0, 0));
        
        if(active)
        {
            setIcon(new ImageIcon(dif.sih.GetCurrentIcons(dif.map[this.posY][this.posX].type)[1]));
            
            setRolloverIcon(new ImageIcon(dif.sih.GetCurrentIcons(dif.map[this.posY][this.posX].type)[1]));
            
            setPressedIcon(new ImageIcon(dif.sih.GetCurrentIcons(dif.map[this.posY][this.posX].type)[1]));
        }else{
            setIcon(new ImageIcon(dif.sih.GetCurrentIcons(dif.map[this.posY][this.posX].type)[0]));
            
            setRolloverIcon(new ImageIcon(dif.sih.GetCurrentIcons(dif.map[this.posY][this.posX].type)[0]));
            
            setPressedIcon(new ImageIcon(dif.sih.GetCurrentIcons(dif.map[this.posY][this.posX].type)[0]));
        }
    }
    public void unlock()
    {
        if((!active)&&(locked))
        {
            locked=false;
            setRolloverIcon(new ImageIcon(dif.sih.GetCurrentIcons(dif.map[this.posY][this.posX].type)[1]));
        }
    }
    public void lock()
    {
        if(!active&&(!locked))
        {
            locked=true;
            setRolloverIcon(new ImageIcon(dif.sih.GetCurrentIcons(dif.map[this.posY][this.posX].type)[0]));
        }
    }
    public void activate(Speaker sp)
    {
        if(!active&&(!locked))
        {
            active=true;
            locked=true;
            sensor=sp.deviceData;
            sp.deviceData.locationX=posX;
            sp.deviceData.locationY=posY;
            setIcon(new ImageIcon(dif.sih.GetCurrentIcons(dif.map[this.posY][this.posX].type)[1]));
            setPressedIcon(new ImageIcon(dif.sih.GetCurrentIcons(dif.map[this.posY][this.posX].type)[1]));
        }
    }
    public void forceActivate(Speaker sp)
    {
        active=true;
        sensor=sp.deviceData;
        setIcon(new ImageIcon(dif.sih.GetCurrentIcons(dif.map[this.posY][this.posX].type)[1]));
        setPressedIcon(new ImageIcon(dif.sih.GetCurrentIcons(dif.map[this.posY][this.posX].type)[1]));
    }
}
