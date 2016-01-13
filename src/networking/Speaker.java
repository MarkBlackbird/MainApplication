/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking;

import graphicalinterface.MapPanel;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kosma
 */
public class Speaker extends Thread{

    public class ConformityFailureException extends Exception
    {
        
    }
    DataOutputStream out;
    DataInputStream in;
    boolean connectionallowed=true,on=true, echo=false;
    ServerSocket serverSocket;
    Socket clientSocket;
    public int portNumber;
    long time;
    public DeviceData deviceData;
    AliveChecker aliche;
    NetworkHandler nh;
    public MapPanel mp;
    List<AlarmEvent> alarmList = new ArrayList<AlarmEvent>();

    public Speaker(int port, MapPanel mp,NetworkHandler nh) throws IOException, ConformityFailureException
    {
        this.mp=mp;
        this.nh=nh;
        boolean newOne=false;
        portNumber=port;
        serverSocket = new ServerSocket(portNumber);
        clientSocket = serverSocket.accept();
        out = new DataOutputStream(clientSocket.getOutputStream());
        in = new DataInputStream(clientSocket.getInputStream());
        
        int ID = in.readInt();
        deviceData=mp.dif.devmem.checkMemory(ID);
        if(deviceData==null)
        {
            deviceData=new DeviceData(this);
            deviceData.ID = ID;
            deviceData.deviceCode = deviceData.castIntToDeviceCode(in.readInt());
            deviceData.deviceName = in.readUTF();
            newOne=true;
        }else{
            if(deviceData.inUse)
            {
                throw new ConformityFailureException();
            }else{
                deviceData.inUse=true;
            }
            if(deviceData.castIntToDeviceCode(in.readInt())!=deviceData.deviceCode)
            {
                throw new ConformityFailureException();
            }
            if(0<deviceData.deviceName.compareTo(in.readUTF()))
            {
                throw new ConformityFailureException();
            }
            deviceData.parent=this;
            mp.addExisting(this);
        }
        deviceData.lastTransmission=System.currentTimeMillis();
        start();
        //aliche=new AliveChecker(this); useless junk do not use
        //aliche.start();
        if(newOne)
            mp.newArrival(this);
        System.out.println("Connected with "+ deviceData.deviceName +" at port "+port);
    }
    public void sendEcho () throws IOException
    {
        if(out!=null)
        {
            echo=true;
            time = System.currentTimeMillis();
            out.writeInt(-1); //echo
        }
    }
    public long receiveEcho () throws IOException
    {
        int ID_Received = in.readInt(); //not used
        time-=System.currentTimeMillis();
        System.out.println("Echoed for: "+(-time)+" with device "+ID_Received);
        aliche.receiveEcho(-time, ID_Received);
        return -time;
    }
    public void setConAll (boolean p)
    {
        connectionallowed=p;
    }
     public void sendData (int number)
    {
        try {
            if(out!=null)
            {
                out.writeInt(1); //info about data send: integer
                out.writeInt(number);
            }
        } catch (IOException ex) {
            Logger.getLogger(NetworkHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void sendData (String str)
    {
        try {
            if(out!=null)
            {
                out.writeInt(2);//info about data send: String
                out.writeUTF(str);
            }
        } catch (IOException ex) {
            Logger.getLogger(NetworkHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void close() throws IOException
    {
        mp.dif.devmem.save(deviceData);
        mp.jbArray[deviceData.locationY][deviceData.locationX].deActivate(this);
        int it=0;
        while(it<nh.speakers.size())
        {
            if(nh.speakers.get(it)!=this)
            {
                it++;
            }else{
                break;
            }
        }
        nh.speakers.remove(it);
        on=false;
        if(serverSocket!=null)
        {
            serverSocket.close();
        }
        //aliche.close();
        on=false;
        //sumthing sumthing save device
    }
    @Override
    public void run()
    {
        while(on)
        {
            int number = 0;
            try {
                number = in.readInt();
                String str;
                switch(number)
                {
                    case 1: //next is int
                    {
                        int alarmType = in.readInt(); //Type of alarm
                        int magLength = in.readInt(); //length of maginitude
                        int[] magnitude = new int[magLength];
                        for(int i=0;i<magLength;i++)
                        {
                            magnitude[i]=in.readInt();
                        }
                        alarmList.add(new AlarmEvent(deviceData,mp.parent,AlarmEvent.castIntToAlarmCode(alarmType),magnitude));
                        break;
                    }
                    case 2: //next is String, Right now not used
                    {
                        str = in.readUTF();
                        
                        break;
                    }
                    case -1: //next is echo request
                    {
                        
                        break;
                    }
                    case -2:
                    {
                        receiveEcho();       
                        break;
                    }
                 }
            } catch (IOException ex) {
                on=false;
                alarmList.add(new AlarmEvent(deviceData,mp.parent,AlarmEvent.AlarmCode.LOST));
                try {
                    close();
                } catch (IOException ex1) {
                    //Logger.getLogger(Speaker.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
    }
}
