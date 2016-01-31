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
 * Klasa przechowująca listę ikon.
 * Zawiera dwuwymiarową tablicę wczytanych ikon.
 *
 * @author Karolina
 */
public class SlotIconHolder {
    BufferedImage [][] imageArray;
	
	/**
     * Konstruktor pozwalający na załadowanie zdjęć z danych wczytanych z pliku.
     *
     * @param data Parametr zawierający dane wczytane z pliku.
     */
    public SlotIconHolder (DataFileInput data)
    {
        imageArray=new BufferedImage[data.numberOfImages][3];
        String line = null;

        try {
            FileReader fileReader = 
                new FileReader(data.iconNameArchive);

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
                   
                    csi++;
                }
            }	
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
        }
    }
	
	/**
     * Funkcja zwracająca serię obrazków odpowiadających danemu podanemu typowi segmentu mapy
     *
     * @param number Parametr informujący o typie Slotu dla którego zwracane są obrazy
     */	
    public BufferedImage [] getIcons (int number)
    {
        return imageArray[number];
    }
}
