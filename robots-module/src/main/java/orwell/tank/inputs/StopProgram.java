package orwell.tank.inputs;

import orwell.tank.IInputVisitor;
import orwell.tank.Tank;
import orwell.tank.elements.DisplayScreen;
import orwell.tank.elements.IDrivingTracks;
import orwell.tank.elements.RfidFlagSensor;
import orwell.tank.elements.SoundSpeaker;

import java.util.List;

/**
 * Created by Michaël Ludmann on 6/10/15.
 */
public class StopProgram implements IInputVisitor {
    public StopProgram(List<String> strings) {

    }

    @Override
    public void visit(Tank tank) {
        tank.shutdown();
    }

    @Override
    public void visit(IDrivingTracks tracks) {
        tracks.stop();
    }

    @Override
    public void visit(RfidFlagSensor rfidFlagSensor) {
        rfidFlagSensor.stop();
    }

    @Override
    public void visit(SoundSpeaker speaker) {
        speaker.playStopProgram();
    }

    @Override
    public void visit(DisplayScreen screen) {
        screen.printNotConnected();
    }
}
