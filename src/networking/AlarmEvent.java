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
}
