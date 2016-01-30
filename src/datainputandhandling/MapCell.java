/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datainputandhandling;

import networking.DeviceData;

/**
 *
 * @author Karolina Szydziak
 */
public class MapCell {
    public int type;
    public DeviceData sensor;
    public MapCell(int type)
    {
        this.type=type;
    }
    public MapCell(DataFileInput dif)
    {
        type=(int)(Math.random()*dif.numberOfImages);
    }
}
