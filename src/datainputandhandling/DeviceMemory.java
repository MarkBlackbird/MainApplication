/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datainputandhandling;

import graphicalinterface.SlotIconHolder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import networking.DeviceData;

/**
 *
 * @author Marek Kos
 */
public class DeviceMemory {
    List<DeviceData> deviceList = new ArrayList<DeviceData>();
    private List<DeviceData> listToSave = new ArrayList<DeviceData>();
    private String context;
    private String fileName;
    public DeviceMemory(String fileName)
    {
        this.fileName=fileName;
        String line = null;
        String []parts = fileName.split("\\\\");
        if(parts.length<=1)
        {
            context="";
        }else{
            context=parts[0];
            for(int i=1;i<parts.length-1;i++)
            {
                context=context+"\\"+parts[i];
            }
            context=context+"\\";
        }
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            String line2;
            FileReader fileReader2;
            BufferedReader bufferedReader2=null;
            while((line = bufferedReader.readLine()) != null) {
                try{
                    fileReader2 = new FileReader(context+line+".txt");

                    // Always wrap FileReader in BufferedReader.
                    bufferedReader2 = new BufferedReader(fileReader2);
                    deviceList.add(new DeviceData());
                    deviceList.get(deviceList.size()-1).ID=Integer.parseInt(line);
                    while((line2 = bufferedReader2.readLine()) != null)
                    {
                        parts = line2.split("=");
                        switch (parts[0])
                        {
                            case "Name": 
                                deviceList.get(deviceList.size()-1).deviceName=parts[1];
                                break;
                            case "deviceCode": 
                                deviceList.get(deviceList.size()-1).deviceCode=DeviceData.DeviceCode.valueOf(parts[1]);
                                break;
                            case "posX": 
                                deviceList.get(deviceList.size()-1).locationX=Integer.parseInt(parts[1]);
                                break;
                            case "posY": 
                                deviceList.get(deviceList.size()-1).locationY=Integer.parseInt(parts[1]);
                                break;
                        }
                    }
                }catch(FileNotFoundException ex) {
                    System.out.println(
                        "Unable to open file '" + 
                        context+line+".txt" + "'");				
                }catch(IOException ex) {
                    System.out.println(
                    "Error reading file '" 
                    + context+line+".txt"+ "'");
                }
                bufferedReader2.close();
            }	

            // Always close files.
            bufferedReader.close();
            
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");				
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");					
            // Or we could just do this: 
            // ex.printStackTrace();
        }
    }
    public void save (DeviceData dd)
    {
        listToSave.add(dd);
        try {
            File file = new File(context+dd.ID+".txt");

            // if file doesnt exists, then create it
            if (!file.exists()) {
		file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Name="+dd.deviceName+"\r\n");
            bw.write("deviceCode="+dd.deviceCode+"\r\n");
            bw.write("posX="+dd.locationX+"\r\n");
            bw.write("posY="+dd.locationY);
            bw.close();
	} catch (IOException e) {
            e.printStackTrace();
	}
    }
    public void saveLinkList()
    {
        try {
            File file = new File(fileName);
            // if file doesnt exists, then create it
            if (!file.exists()) {
		file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            for(int i=0;i<listToSave.size();i++)
            {
                bw.write(listToSave.get(i).ID+"\r\n");
            }
            bw.close();
	} catch (IOException e) {
            e.printStackTrace();
	}
    }
    public DeviceData checkMemory (int ID)
    {
        for(int i=0;i<deviceList.size();i++)
        {
            if(deviceList.get(i).ID==ID)
            {
                return deviceList.get(i);
            }
        }
        return null;
    }
}
