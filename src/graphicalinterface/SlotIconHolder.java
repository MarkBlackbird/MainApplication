/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicalinterface;

import datainputandhandling.DataFileInput;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author kosma
 */
public class SlotIconHolder {
    BufferedImage [][] imageArray;
    public SlotIconHolder (DataFileInput data)
    {
        imageArray=new BufferedImage[data.numberOfImages][3];
        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(data.iconNameArchive);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            int csi=0;
            String dataPosition = bufferedReader.readLine();
            while((line = bufferedReader.readLine()) != null) {
                String [] helper = line.split("\\.");
                if(csi<data.numberOfImages)
                {
                    imageArray[csi][0]=ImageIO.read(new File(dataPosition+line));
                    imageArray[csi][2]=imageArray[csi][0];
                    imageArray[csi][1]=ImageIO.read(new File(dataPosition+helper[0]+"_high."+helper[1]));
                    //imageArray[csi][2]=ImageIO.read(new File(dataPosition+helper[0]+"_inverted."+helper[1]));
                    csi++;
                }
            }	

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                data.iconNameArchive + "'");				
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + data.iconNameArchive + "'");					
            // Or we could just do this: 
            // ex.printStackTrace();
        }
    }
    public BufferedImage [] GetCurrentIcons (int number)
    {
        return imageArray[number];
    }
}
