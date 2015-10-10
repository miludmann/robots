package orwell.tank.inputs;

import orwell.tank.EnumConnectionState;
import orwell.tank.IInputVisitor;
import orwell.tank.Tank;
import orwell.tank.elements.DisplayScreen;
import orwell.tank.elements.IDrivingTracks;
import orwell.tank.elements.RfidFlagSensor;
import orwell.tank.elements.SoundSpeaker;

/**
 * Created by Michaël Ludmann on 6/15/15.
 */
public class WaitForProxy implements IInputVisitor {
    @Override
    public void visit(Tank tank) {
        tank.setConnectionState(EnumConnectionState.NOT_CONNECTED);
    }

    @Override
    public void visit(IDrivingTracks tracks) {

    }

    @Override
    public void visit(RfidFlagSensor rfidFlagSensor) {

    }

    @Override
    public void visit(SoundSpeaker speaker) {
        speaker.playWaitingForPC();
    }

    @Override
    public void visit(DisplayScreen screen) {
        screen.printWaitingForPC();
    }
}
