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
    int portNumber;
    List<Speaker> speakers=new ArrayList<Speaker>();
    MapPanel mp;
    public NetworkHandler(int port, MapPanel mp)
    {
        portNumber=port;
        this.mp=mp;
        start();
    }
    public void setConAll (boolean p)
    {
        connectionallowed=p;
    }
    public void saveData()
    {
        for(int i=0;i<speakers.size();i++)
        {
            mp.dif.devmem.save(speakers.get(i).deviceData);
        }
        mp.dif.devmem.saveLinkList();
    }
    public void close() throws IOException
    {
        //saveData();
        on=false;
        if(serverSocket!=null)
        {
            serverSocket.close();
        }
        for(int i=0;i<speakers.size();i++)
        {
            speakers.get(i).close();
        }
        mp.dif.devmem.saveLinkList();
    }
    private int findNextAvaiblePort()
    {
        int nextFreePort;
        nextFreePort=portNumber+1;
        int it=0;
        while(it<speakers.size())
        {
            if(speakers.get(it).portNumber==nextFreePort)
            {
                nextFreePort++;
                it=0;
            }else{
                it++;
            }
        }
        return nextFreePort;
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
                int nextFreePort=findNextAvaiblePort();
                out.writeInt(nextFreePort);
                speakers.add(new Speaker(nextFreePort++,mp,this));
                serverSocket.close();
            }
            }catch(Exception e){}
        }
    }
}
