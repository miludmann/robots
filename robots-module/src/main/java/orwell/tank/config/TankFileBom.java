package orwell.tank.config;

import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;

/**
 * Created by Michaël Ludmann on 8/27/15.
 */
public class TankFileBom {

    private MotorPort leftMortPort;
    private MotorPort rightMotorPort;
    private boolean isLeftMotorInverted;
    private boolean isRightMotorInverted;
    private SensorPort rfidSensorPort;

    public MotorPort getLeftMortPort() {
        return leftMortPort;
    }

    public void setLeftMortPort(MotorPort leftMortPort) {
        this.leftMortPort = leftMortPort;
    }

    public MotorPort getRightMotorPort() {
        return rightMotorPort;
    }

    public void setRightMotorPort(MotorPort rightMotorPort) {
        this.rightMotorPort = rightMotorPort;
    }

    public boolean isLeftMotorInverted() {
        return isLeftMotorInverted;
    }

    public void setIsLeftMotorInverted(boolean isLeftMotorInverted) {
        this.isLeftMotorInverted = isLeftMotorInverted;
    }

    public boolean isRightMotorInverted() {
        return isRightMotorInverted;
    }

    public void setIsRightMotorInverted(boolean isRightMotorInverted) {
        this.isRightMotorInverted = isRightMotorInverted;
    }

    public SensorPort getRfidSensorPort() {
        return rfidSensorPort;
    }

    public void setRfidSensorPort(SensorPort rfidSensorPort) {
        this.rfidSensorPort = rfidSensorPort;
    }
}
