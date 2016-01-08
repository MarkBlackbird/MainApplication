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
    public int ID;
    public String deviceName;
    public DeviceCode deviceCode;
    public int locationX,locationY;
    long lastTransmission;
    public DeviceData()
    {
        lastTransmission=0;
        locationX=-1;
        locationY=-1;
        deviceCode=DeviceCode.UNKNOWN;
        deviceName="Name";
        ID=0; //Not valid. Needs adjustment
    }
    public static DeviceCode castIntToDeviceCode(int num)
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
    
    public int castDeviceCodeToInt(DeviceCode devCode)
    {
        switch(devCode)
        {
            case UNKNOWN:
            {
                return 0;
            }
            case TERMOMETER:
            {
                return 1;
            }
            case HUMIDITY_METER:
            {
                return 2;
            }
            case CARDIOTOOL:
            {
                return 3;
            }
            case BIOELECTRIC_MEASUREMENT:
            {
                return 4;
            }
            case ATMOSPHERE_SENSOR:
            {
                return 5;
            }
            case MALFUNCTION_SENSOR:
            {
                return 6;
            }
            case ALIVE_SENSOR:
            {
                return 7;
            }
            case ASSISTANCE_DRIVE:
            {
                return 8;
            }
            default:
            {
                return 9;
            }
        }
    }
}
