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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kosma
 */
public class Speaker extends Thread{

    DataOutputStream out;
    DataInputStream in;
    boolean connectionallowed=true,on=true;
    ServerSocket serverSocket;
    Socket clientSocket;
    int portNumber;
    DeviceData deviceData;

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
            
        }
    }
}
