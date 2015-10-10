package orwell.tank.events;

import lejos.mf.common.UnitMessage;
import lejos.mf.common.UnitMessageType;
import orwell.tank.INewValueVisitor;
import orwell.tank.Tank;
import orwell.tank.elements.DisplayScreen;
import orwell.tank.elements.IDrivingTracks;
import orwell.tank.elements.RfidFlagSensor;
import orwell.tank.elements.SoundSpeaker;

/**
 * Created by Michaël Ludmann on 6/15/15.
 */
public class RfidNewValue implements INewValueVisitor {
    private final static UnitMessageType MESSAGE_TYPE = UnitMessageType.Rfid;
    private final UnitMessage unitMessage;
    private final String value;

    public RfidNewValue(long value) {
        this.value = Long.toString(value);
        unitMessage = new UnitMessage(MESSAGE_TYPE, getValue());
    }

    @Override
    public void visit(Tank tank) {

    }

    @Override
    public void visit(IDrivingTracks tracks) {

    }

    @Override
    public void visit(RfidFlagSensor rfidFlagSensor) {

    }

    @Override
    public void visit(SoundSpeaker speaker) {
        speaker.playRfidNewValue();
    }

    @Override
    public void visit(DisplayScreen screen) {
        screen.printNewValue(this);
    }

    @Override
    public UnitMessage getUnitMessage() {
        return unitMessage;
    }

    @Override
    public String getValue() {
        return value;
    }
}
