package datainputandhandling;

import networking.DeviceData;

/**
 * Klasa przechowująca dane pojedynczej komórki mapy.
 * Zawiera w sobie takie pola jak dane o przechowywanym urządzeniu i kod jego typu.
 * @author Karolina
 */
public class MapCell {
    public int type;
	
	/**
     * Konstruktor wpisujący typ pola
     *
     * @param type Parametr definiujący typ urządzenia.
     */
    public MapCell(int type)
    {
        this.type=type;
    }
	
	/**
     * Konstruktor losujący typ pola
     *
     * @param dif Parametr z danymi wczytanymi z pliku.
     */
    public MapCell(DataFileInput dif)
    {
        type=(int)(Math.random()*dif.numberOfImages);
    }
}
