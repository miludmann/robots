package orwell.tank.inputs;

import orwell.tank.IInputVisitor;
import orwell.tank.Tank;
import orwell.tank.elements.DisplayScreen;
import orwell.tank.elements.IDrivingTracks;
import orwell.tank.elements.RfidFlagSensor;
import orwell.tank.elements.SoundSpeaker;

/**
 * Created by Michaël Ludmann on 6/16/15.
 */
public class StartTank implements IInputVisitor {
    @Override
    public void visit(Tank tank) {

    }

    @Override
    public void visit(IDrivingTracks tracks) {

    }

    @Override
    public void visit(RfidFlagSensor rfidFlagSensor) {
        rfidFlagSensor.startListen();
    }

    @Override
    public void visit(SoundSpeaker speaker) {
        speaker.playStartTank();
    }

    @Override
    public void visit(DisplayScreen screen) {
        screen.printStartTank();
    }
}
