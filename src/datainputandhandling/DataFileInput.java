/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datainputandhandling;

import graphicalinterface.SlotIconHolder;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Karolina Szydziak
 */
public class DataFileInput {
    public int numberOfImages,portNumber;
    public String mapNameArchive,iconNameArchive;
    boolean ready=false;
    public SlotIconHolder sih;
    public MapCell[][] map;
    public int numX, numY;
    public DeviceMemory devmem;
    public DataFileInput(String fileName)
    {
        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            String[] parts;
            while((line = bufferedReader.readLine()) != null) {
                parts = line.split("=");
                switch (parts[0])
                {
                    case "numberOfImages": 
                        numberOfImages=Integer.parseInt(parts[1]);
                        break;
                    case "mapNameArchive":
                        mapNameArchive=parts[1];
                        break;
                    case "iconNameArchive":
                        iconNameArchive=parts[1];
                        break;
                    case "portNumber":
                        portNumber=Integer.parseInt(parts[1]);
                        break;
                    case "numX":
                        numX=Integer.parseInt(parts[1]);
                        break;
                    case "numY":
                        numY=Integer.parseInt(parts[1]);
                        break;
                    case "deviceMemoryLocation":
                        devmem=new DeviceMemory(parts[1]);
                        break;
                }
            }	

            // Always close files.
            bufferedReader.close();
            fileReader = new FileReader(mapNameArchive);
            bufferedReader = new BufferedReader(fileReader);
            sih= new SlotIconHolder(this);
            map=new MapCell[numY][numX];
            for(int i=0;i<numY;i++)
            {
                line = bufferedReader.readLine();
                parts = line.split(",");
                for(int j=0;j<numX;j++)
                {
                    map[i][j]=new MapCell(Integer.parseInt(parts[j]));
                }
            }
            ready=true;
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
}
