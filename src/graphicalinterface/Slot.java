/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicalinterface;

import datainputandhandling.DataFileInput;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import networking.AlarmEvent;
import networking.DeviceData;
import networking.Speaker;

/**
 *
 * @author Marek Sienkiewicz
 */
public class Slot extends JButton{
    DataFileInput dif;
    int posX, posY;
    boolean active=false, locked=true;
    public List<DeviceData> sensor = new ArrayList<DeviceData>();
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
            setIcon(new ImageIcon(dif.sih.getIcons(dif.map[this.posY][this.posX].type)[1]));
            
            setRolloverIcon(new ImageIcon(dif.sih.getIcons(dif.map[this.posY][this.posX].type)[1]));
            
            setPressedIcon(new ImageIcon(dif.sih.getIcons(dif.map[this.posY][this.posX].type)[1]));
        }else{
            setIcon(new ImageIcon(dif.sih.getIcons(dif.map[this.posY][this.posX].type)[0]));
            
            setRolloverIcon(new ImageIcon(dif.sih.getIcons(dif.map[this.posY][this.posX].type)[0]));
            
            setPressedIcon(new ImageIcon(dif.sih.getIcons(dif.map[this.posY][this.posX].type)[0]));
        }
    }
    public void unlock()
    {
        if(locked)
        {
            locked=false;
            if(!active)
            {
                setRolloverIcon(new ImageIcon(dif.sih.getIcons(dif.map[this.posY][this.posX].type)[1]));
            }
        }
    }
    public void lock()
    {
        if(!locked)
        {
            locked=true;
            if(!active)
            {
                setRolloverIcon(new ImageIcon(dif.sih.getIcons(dif.map[this.posY][this.posX].type)[0]));
            }
        }
    }
    public void activate(Speaker sp)
    {
        if(!locked)
        {
            active=true;
            locked=true;
            sensor.add(sp.deviceData);
            sp.deviceData.locationX=posX;
            sp.deviceData.locationY=posY;
            setIcon(new ImageIcon(dif.sih.getIcons(dif.map[this.posY][this.posX].type)[1]));
            setPressedIcon(new ImageIcon(dif.sih.getIcons(dif.map[this.posY][this.posX].type)[1]));
        }
    }
    public void forceActivate(Speaker sp)
    {
        active=true;
        sensor.add(sp.deviceData);
        setIcon(new ImageIcon(dif.sih.getIcons(dif.map[this.posY][this.posX].type)[1]));
        setPressedIcon(new ImageIcon(dif.sih.getIcons(dif.map[this.posY][this.posX].type)[1]));
        setRolloverIcon(new ImageIcon(dif.sih.getIcons(dif.map[this.posY][this.posX].type)[1]));
    }
    public void deActivate(Speaker sp)
    {
        active=false;
        sensor.remove(sp.deviceData);
        if(sensor.size()==0)
        {
            setIcon(new ImageIcon(dif.sih.getIcons(dif.map[this.posY][this.posX].type)[0]));
            setPressedIcon(new ImageIcon(dif.sih.getIcons(dif.map[this.posY][this.posX].type)[0]));
            setRolloverIcon(new ImageIcon(dif.sih.getIcons(dif.map[this.posY][this.posX].type)[0]));
            sp.mp.parent.menuPanel.refresh();
        }
    }
}
