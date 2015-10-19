/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking;

/**
 *
 * @author kosma
 */
public class DeviceData {
    public enum DeviceCode{
        UNKNOWN, TERMOMETER, HUMIDITY_METER, CARDIOTOOL, BIOELECTRIC_MEASUREMENT,
        ATMOSPHERE_SENSOR, MALFUNCTION_SENSOR, ALIVE_SENSOR, ASSISTANCE_DRIVE
    }
    int ID;
    String deviceName;
    DeviceCode deviceCode;
    int locationX,locationY;
    long lastTransmission;
    public DeviceData()
    {
        lastTransmission=0;
        locationX=-1;
        locationY=-1;
        deviceCode=DeviceCode.UNKNOWN;
        ID=0; //Not valid. Needs adjustment
    }
    public DeviceCode castIntToDeviceCode(int num)
    {
        switch(num)
        {
            case 0:
            {
                return DeviceCode.UNKNOWN;
            }
            case 1:
            {
                return DeviceCode.TERMOMETER;
            }
            case 2:
            {
                return DeviceCode.HUMIDITY_METER;
            }
            case 3:
            {
                return DeviceCode.CARDIOTOOL;
            }
            case 4:
            {
                return DeviceCode.BIOELECTRIC_MEASUREMENT;
            }
            case 5:
            {
                return DeviceCode.ATMOSPHERE_SENSOR;
            }
            case 6:
            {
                return DeviceCode.MALFUNCTION_SENSOR;
            }
            case 7:
            {
                return DeviceCode.ALIVE_SENSOR;
            }
            case 8:
            {
                return DeviceCode.ASSISTANCE_DRIVE;
            }
            default:
            {
                return DeviceCode.UNKNOWN;
            }
        }
    }
}
