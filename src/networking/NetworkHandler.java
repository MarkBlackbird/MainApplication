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

/**
 *
 * @author kosma
 */
public class NetworkHandler extends Thread{
    DataOutputStream out;
    //DataInputStream in;
    boolean connectionallowed=true,on=true;
    ServerSocket serverSocket;
    Socket clientSocket;
    int portNumber, nextFreePort;
    Speaker speakerToPick=null;
    public NetworkHandler(int port)
    {
        portNumber=port;
        nextFreePort=port+1;
        start();
    }
    public void setConAll (boolean p)
    {
        connectionallowed=p;
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
            try{
            while(connectionallowed)
            {
                serverSocket = new ServerSocket(portNumber);
                clientSocket = serverSocket.accept();
                out = new DataOutputStream(clientSocket.getOutputStream());
                out.writeInt(nextFreePort);
                speakerToPick = new Speaker(nextFreePort++);
                serverSocket.close();
            }
            }catch(Exception e){}
        }
    }
}
