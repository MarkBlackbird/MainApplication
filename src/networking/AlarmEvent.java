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
public class AlarmEvent {
    public enum AlarmCode{
        UNKNOWN, TEMPERATURE, HUMIDITY, LOW_VOLTAGE,
        LOW_MEMORY, HR, ATM_PRESSURE, BLOOD_PRESSURE,
        NO_PATIENT, OXYGEN_CONTENT, FUMES_CONTENT, FIRE,
        FLOOD, RADIATION, DATA_CORRUPTED, BLOOD_FLOW,
        SATURATION, BODY_TEMPERATURE, PATIENT_ELECTROCUTTED, ABNORMAL_ECG,
        ABNORMAL_EEG, ABNORMAL_EOG, ABNORMAL_EMG, CRITICAL_DEVICE_FAILURE,
        RISK_OF_DEATH, DEATH, LONG_DELAY, DEVICE_DISCONNECTED_BY_USER, 
        PATIENT_ASKS_FOR_ASSISTANCE
    }
    int [] magnitude;
    AlarmCode alarmCode;
    public AlarmEvent()
    {
        alarmCode=AlarmCode.UNKNOWN;
        magnitude=null;
    }
    public AlarmEvent(int []mag)
    {
        alarmCode=AlarmCode.UNKNOWN;
        magnitude=mag;
    }
    public AlarmEvent(AlarmCode ac)
    {
        alarmCode=ac;
        magnitude=null;
    }
    public AlarmEvent(AlarmCode ac,int []mag)
    {
        alarmCode=ac;
        magnitude=mag;
    }
    public static AlarmCode castIntToAlarmCode(int num)
    {
        switch(num)
        {
            case 0:
            {
                return AlarmCode.UNKNOWN;
            }
            case 1:
            {
                return AlarmCode.TEMPERATURE;
            }
            case 2:
            {
                return AlarmCode.HUMIDITY;
            }
            case 3:
            {
                return AlarmCode.LOW_VOLTAGE;
            }
            case 4:
            {
                return AlarmCode.LOW_MEMORY;
            }
            case 5:
            {
                return AlarmCode.HR;
            }
            case 6:
            {
                return AlarmCode.ATM_PRESSURE;
            }
            case 7:
            {
                return AlarmCode.BLOOD_PRESSURE;
            }
            case 8:
            {
                return AlarmCode.NO_PATIENT;
            }
            case 9:
            {
                return AlarmCode.OXYGEN_CONTENT;
            }
            case 10:
            {
                return AlarmCode.FUMES_CONTENT;
            }
            case 11:
            {
                return AlarmCode.FIRE;
            }
            case 12:
            {
                return AlarmCode.FLOOD;
            }
            case 13:
            {
                return AlarmCode.RADIATION;
            }
            case 14:
            {
                return AlarmCode.DATA_CORRUPTED;
            }
            case 15:
            {
                return AlarmCode.BLOOD_FLOW;
            }
            case 16:
            {
                return AlarmCode.SATURATION;
            }
            case 17:
            {
                return AlarmCode.BODY_TEMPERATURE;
            }
            case 18:
            {
                return AlarmCode.PATIENT_ELECTROCUTTED;
            }
            case 19:
            {
                return AlarmCode.ABNORMAL_ECG;
            }
            case 20:
            {
                return AlarmCode.ABNORMAL_EEG;
            }
            case 21:
            {
                return AlarmCode.ABNORMAL_EOG;
            }
            case 22:
            {
                return AlarmCode.ABNORMAL_EMG;
            }
            case 23:
            {
                return AlarmCode.CRITICAL_DEVICE_FAILURE;
            }
            case 24:
            {
                return AlarmCode.RISK_OF_DEATH;
            }
            case 25:
            {
                return AlarmCode.DEATH;
            }
            case 26:
            {
                return AlarmCode.LONG_DELAY;
            }
            case 27:
            {
                return AlarmCode.DEVICE_DISCONNECTED_BY_USER;
            }
            case 28:
            {
                return AlarmCode.PATIENT_ASKS_FOR_ASSISTANCE;
            }
            default:
            {
                return AlarmCode.UNKNOWN;
            }
        }
    }
    
    public static int castAlarmCodeToInt(AlarmCode devCode)
    {
        switch(devCode)
        {
            case UNKNOWN:
            {
                return 0;
            }
            case TEMPERATURE:
            {
                return 1;
            }
            case HUMIDITY:
            {
                return 2;
            }
            case LOW_VOLTAGE:
            {
                return 3;
            }
            case LOW_MEMORY:
            {
                return 4;
            }
            case HR:
            {
                return 5;
            }
            case ATM_PRESSURE:
            {
                return 6;
            }
            case BLOOD_PRESSURE:
            {
                return 7;
            }
            case NO_PATIENT:
            {
                return 8;
            }
            case OXYGEN_CONTENT:
            {
                return 9;
            }
            case FUMES_CONTENT:
            {
                return 10;
            }
            case FIRE:
            {
                return 11;
            }
            case FLOOD:
            {
                return 12;
            }
            case RADIATION:
            {
                return 13;
            }
            case DATA_CORRUPTED:
            {
                return 14;
            }
            case BLOOD_FLOW:
            {
                return 15;
            }
            case SATURATION:
            {
                return 16;
            }
            case BODY_TEMPERATURE:
            {
                return 17;
            }
            case PATIENT_ELECTROCUTTED:
            {
                return 18;
            }
            case ABNORMAL_ECG:
            {
                return 19;
            }
            case ABNORMAL_EEG:
            {
                return 20;
            }
            case ABNORMAL_EOG:
            {
                return 21;
            }
            case ABNORMAL_EMG:
            {
                return 22;
            }
            case CRITICAL_DEVICE_FAILURE:
            {
                return 23;
            }
            case RISK_OF_DEATH:
            {
                return 24;
            }
            case DEATH:
            {
                return 25;
            }
            case LONG_DELAY:
            {
                return 26;
            }
            case DEVICE_DISCONNECTED_BY_USER:
            {
                return 27;
            }
            case PATIENT_ASKS_FOR_ASSISTANCE:
            {
                return 28;
            }
            default:
            {
                return 0;
            }
        }
    }
}
