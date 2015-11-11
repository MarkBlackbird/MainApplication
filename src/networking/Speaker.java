/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking;

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

    DataOutputStream out;
    DataInputStream in;
    boolean connectionallowed=true,on=true, echo=false;
    ServerSocket serverSocket;
    Socket clientSocket;
    int portNumber;
    long time;
    DeviceData deviceData;
    AliveChecker aliche;
    List<AlarmEvent> alarmList = new ArrayList<AlarmEvent>();

    public Speaker(int port) throws IOException
    {
        portNumber=port;
        serverSocket = new ServerSocket(portNumber);
        clientSocket = serverSocket.accept();
        out = new DataOutputStream(clientSocket.getOutputStream());
        in = new DataInputStream(clientSocket.getInputStream());
        deviceData=new DeviceData();
        deviceData.ID = in.readInt();
        deviceData.deviceCode = deviceData.castIntToDeviceCode(in.readInt());
        deviceData.deviceName = in.readUTF();
        deviceData.lastTransmission=System.currentTimeMillis();
        start();
        aliche=new AliveChecker(this);
        aliche.start();
        System.out.println("Connected with "+ deviceData.deviceName +" at port "+port);
    }
    public long sendEcho () throws IOException
    {
        if(out!=null)
        {
            echo=true;
            time = System.currentTimeMillis();
            out.writeInt(-1); //echo
        }
        return -1; //Failed
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
        on=false;
        if(serverSocket!=null)
        {
            serverSocket.close();
        }
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
                        alarmList.add(new AlarmEvent(AlarmEvent.castIntToAlarmCode(alarmType),magnitude));
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
                new AlarmEvent(AlarmEvent.AlarmCode.LONG_DELAY);
            }
        }
    }
}
