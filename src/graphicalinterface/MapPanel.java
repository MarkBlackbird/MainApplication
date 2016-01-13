/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicalinterface;

import datainputandhandling.DataFileInput;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import networking.Speaker;

/**
 *
 * @author kosma
 */
public class MapPanel extends JPanel implements ActionListener{
    public Slot [][]jbArray;
    public DataFileInput dif;
    private List<Speaker> pending = new ArrayList<Speaker>();
    private boolean addingProcessInProgress=false;
    public MainFrame parent;
    
    public MapPanel (MainFrame parent,DataFileInput dif)
    {
        this.parent=parent;
        this.dif=dif;
        setLayout(new GridLayout(dif.numY,dif.numX));
        jbArray = new Slot[dif.numY][dif.numX];
        for(int i=0;i<dif.numY;i++)
        {
            for(int j=0;j<dif.numX;j++)
            {
                jbArray[i][j]=new Slot(dif,j,i);
                jbArray[i][j].addActionListener(this);
                add(jbArray[i][j]);
            }
        }
    }

    public void addExisting (Speaker sp)
    {
        jbArray[sp.deviceData.locationY][sp.deviceData.locationX].forceActivate(sp);
    }
    public void newArrival (Speaker sp)
    {
        pending.add(sp);
        if(!addingProcessInProgress)
        {
            addingProcessInProgress=true;
            Adder ad = new Adder();
            ad.start();
        }
    }
    private class Adder extends Thread 
    {
        @Override
        public void run ()
        {
            if(addingProcessInProgress)
            {
                Speaker first;
                while(!pending.isEmpty())
                {
                    first=pending.get(0);
                    for(int i=0;i<dif.numY;i++)
                    {
                        for(int j=0;j<dif.numX;j++)
                        {
                            jbArray[i][j].unlock();
                        }
                    }

                    while(first==pending.get(0))
                    {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(MapPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if(pending.isEmpty())
                        {
                            addingProcessInProgress=false;
                            break;
                        }
                    }
                    if(!pending.isEmpty())
                    {
                    
                    }
                }
            }
        }
    }
    private class EscapeException extends Exception
    {
        
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        try
        {
            for(int i=0;i<dif.numY;i++)
            {
                for(int j=0;j<dif.numX;j++)
                {
                    if(ae.getSource()==jbArray[i][j])
                    {
                        parent.menuPanel.newSelected(jbArray[i][j]);
                        jbArray[i][j].activate(pending.get(0));
                        throw new EscapeException();
                    }
                }
            }
        }catch(EscapeException e){
            for(int i=0;i<dif.numY;i++)
            {
                for(int j=0;j<dif.numX;j++)
                {
                    if(ae.getSource()!=jbArray[i][j])
                    {
                        jbArray[i][j].lock();
                    }
                }
            }
            pending.remove(0);
        }catch(IndexOutOfBoundsException ioobe){
            String str = ioobe.getLocalizedMessage();
            String [] parts = str.split(": ");
            if(Integer.parseInt(parts[parts.length-1])!=0)
            {
                throw ioobe;
            }
        }
    }
}
