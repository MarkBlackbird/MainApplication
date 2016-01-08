/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import networking.AlarmEvent.AlarmCode;

/**
 *
 * @author kosma
 */
public class AliveChecker extends Thread{
    public class NoResponseException extends Exception
    {
        //new Exception to handle situation when there is no response
    }
    Speaker parent;
    boolean on=true,echoed=false,warning=false;
    long delay;
    public AliveChecker (Speaker parent)
    {
        this.parent=parent;
    }
    public void close()
    {
        on=false;
    }
    public void receiveEcho(long delay, int devID)
    {
        echoed=true;
        this.delay=delay;
    }
    @Override
    public void run()
    {
        while(on)
        {
            try {
                parent.sendEcho();
                Thread.sleep(5000);
                if(echoed)
                {
                    //System.out.println(delay);
                }else{
                    //throw new NoResponseException();
                }
            } catch (IOException ex) {
                if(warning)
                {
                    on=false;
                    new AlarmEvent(parent.deviceData,parent.mp.parent,AlarmCode.LOST);
                    try {
                        parent.close();
                    } catch (IOException ex1) {
                        Logger.getLogger(AliveChecker.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }else{
                    warning=true;
                    new AlarmEvent(parent.deviceData,parent.mp.parent,AlarmCode.LONG_DELAY);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(AliveChecker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
