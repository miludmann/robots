package orwell.tank;

import lejos.nxt.I2CPort;
import lejos.nxt.NXTMotor;
import orwell.tank.config.TankFileBom;
import orwell.tank.elements.*;

import java.util.ArrayList;


/**
 * Created by Michaël Ludmann on 6/10/15.
 */
public class Tank {
    private final lejos.nxt.TachoMotorPort leftMotorPort;
    private final lejos.nxt.TachoMotorPort rightMotorPort;
    private final I2CPort rfidPort;
    private final DisplayScreen displayScreen;
    private final IDrivingTracks drivingTracks;
    private final RfidFlagSensor rfidFlagSensor;
    private final SoundSpeaker soundSpeaker;
    private String bluetoothName;
    private String routingId;
    private ArrayList<ISensor> sensors;
    private EnumConnectionState connectionState;
    private volatile boolean isTankAlive = false;

    public Tank(TankFileBom tankFileBom) {
//        this.drivingTracks = new DrivingTracksRegulated(
//                new NXTRegulatedMotor(MOTOR_PORT_LEFT),
//                new NXTRegulatedMotor(rightMotorPort));
        this.leftMotorPort = tankFileBom.getLeftMortPort();
        this.rightMotorPort = tankFileBom.getRightMotorPort();
        this.rfidPort = tankFileBom.getRfidSensorPort();
        this.drivingTracks = new DrivingTracksNonRegulated(
                new NXTMotor(leftMotorPort),
                new NXTMotor(rightMotorPort),
                tankFileBom.isLeftMotorInverted(),
                tankFileBom.isRightMotorInverted()
        );
        this.rfidFlagSensor = new RfidFlagSensor(rfidPort);
        sensors = new ArrayList<>();
        sensors.add(rfidFlagSensor);
        this.soundSpeaker = new SoundSpeaker();
        this.displayScreen = new DisplayScreen();
        setConnectionState(EnumConnectionState.NOT_CONNECTED);
    }

    public void accept(final ITankVisitor visitor) {
        visitor.visit(drivingTracks);
        visitor.visit(rfidFlagSensor);
        visitor.visit(soundSpeaker);
        visitor.visit(displayScreen);
        visitor.visit(this);
    }

    public void shutdown() {
        this.isTankAlive = false;
        setConnectionState(EnumConnectionState.NOT_CONNECTED);
    }

    public void setIsTankAlive(boolean isTankAlive) {
        this.isTankAlive = isTankAlive;
    }

    public boolean isAlive() {
        return isTankAlive;
    }

    public ArrayList<ISensor> getSensors() {
        return sensors;
    }

    public EnumConnectionState getConnectionState() {
        return connectionState;
    }

    public void setConnectionState(EnumConnectionState connectionState) {
        this.connectionState = connectionState;
    }
}
