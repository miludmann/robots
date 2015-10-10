package orwell.tank.config;

import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;
import orwell.tank.elements.DisplayScreen;
import orwell.tank.exception.ParseIniException;
import orwell.tank.utils.IniFile;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Michaël Ludmann on 8/27/15.
 */
public class TankFileParser {

    private static final String TANK_FILENAME_ELEMENT = "tank";
    private static final String TANK_FILENAME_EXT = ".ini";
    private static final String DEFAULT_TANK_FILENAME = "tank.defaults.ini";
    private static final String LEFT_MOTOR_PORT_FIELD = "leftMotorPort";
    private static final String RIGHT_MOTOR_PORT_FIELD = "rightMotorPort";
    private static final String IS_LEFT_MOTOR_INVERTED_FIELD = "isLeftMotorInverted";
    private static final String IS_RIGHT_MOTOR_INVERTED_FIELD = "isRightMotorInverted";
    private static final String RFID_SENSOR_PORT_FIELD = "rfidSensorPort";
    private final IniFile iniFile;

    public TankFileParser() throws FileNotFoundException {
        String validatedFileName = getValidatedFileName();
        if (null == validatedFileName) {
            throw new FileNotFoundException("Ini file not found");
        }
        this.iniFile = new IniFile(validatedFileName);
    }

    private String getValidatedFileName() {
        // Try to find custom config
        File.listFiles(); // required to init File.totalFiles static value
        for (byte i = 0; i < File.totalFiles; i++) {
            File file = File.listFiles()[i];
            DisplayScreen.printLog(file.getName());
            if (isCustomFileValid(file)) {
                DisplayScreen.printLog(".ini " + file.getName());
                return file.getName();
            }
        }

        // Return the default config if it exists
        if ((new File(DEFAULT_TANK_FILENAME)).exists()) {
            DisplayScreen.printLog(".ini " + DEFAULT_TANK_FILENAME);
            return DEFAULT_TANK_FILENAME;
        }

        return null;
    }

    private boolean isCustomFileValid(File file) {
        return file.exists() &&
                !file.getName().equalsIgnoreCase(DEFAULT_TANK_FILENAME) &&
                file.getName().toLowerCase().indexOf(TANK_FILENAME_ELEMENT.toLowerCase()) >= 0 &&
                file.getName().toLowerCase().indexOf(TANK_FILENAME_EXT.toLowerCase()) >= 0;
    }

    public TankFileBom parse() throws ParseIniException {
        TankFileBom tankFileBom = new TankFileBom();

        setLeftMotorPortTo(tankFileBom);
        setRightMotorPortTo(tankFileBom);
        setIsLeftMotorInvertedTo(tankFileBom);
        setIsRightMotorInvertedTo(tankFileBom);
        setRfidSensorPortTo(tankFileBom);

        return tankFileBom;
    }

    private void setLeftMotorPortTo(TankFileBom tankFileBom) throws ParseIniException {
        String value = iniFile.getProperty(LEFT_MOTOR_PORT_FIELD);
        switch (value) {
            case "A":
                tankFileBom.setLeftMortPort(MotorPort.A);
                break;
            case "B":
                tankFileBom.setLeftMortPort(MotorPort.B);
                break;
            case "C":
                tankFileBom.setLeftMortPort(MotorPort.C);
                break;
            default:
                throw new ParseIniException("IncorrectIniValue");
        }
    }

    private void setRightMotorPortTo(TankFileBom tankFileBom) throws ParseIniException {
        String value = iniFile.getProperty(RIGHT_MOTOR_PORT_FIELD);
        switch (value) {
            case "A":
                tankFileBom.setRightMotorPort(MotorPort.A);
                break;
            case "B":
                tankFileBom.setRightMotorPort(MotorPort.B);
                break;
            case "C":
                tankFileBom.setRightMotorPort(MotorPort.C);
                break;
            default:
                throw new ParseIniException("IncorrectIniValue");
        }
    }

    public void setIsLeftMotorInvertedTo(TankFileBom tankFileBom) {
        tankFileBom.setIsLeftMotorInverted(iniFile.getBoolean(IS_LEFT_MOTOR_INVERTED_FIELD));
    }

    public void setIsRightMotorInvertedTo(TankFileBom tankFileBom) {
        tankFileBom.setIsRightMotorInverted(iniFile.getBoolean(IS_RIGHT_MOTOR_INVERTED_FIELD));
    }

    private void setRfidSensorPortTo(TankFileBom tankFileBom) throws ParseIniException {
        int value = iniFile.getInt(RFID_SENSOR_PORT_FIELD);
        switch (value) {
            case 1:
                tankFileBom.setRfidSensorPort(SensorPort.S1);
                break;
            case 2:
                tankFileBom.setRfidSensorPort(SensorPort.S2);
                break;
            case 3:
                tankFileBom.setRfidSensorPort(SensorPort.S3);
                break;
            case 4:
                tankFileBom.setRfidSensorPort(SensorPort.S4);
                break;
            default:
                throw new ParseIniException("IncorrectIniValue");
        }
    }
}
