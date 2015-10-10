package orwell.tank.inputs;

import orwell.tank.Tank;
import orwell.tank.elements.DisplayScreen;
import orwell.tank.elements.IDrivingTracks;
import orwell.tank.elements.RfidFlagSensor;
import orwell.tank.elements.SoundSpeaker;

/**
 * Created by Michaël Ludmann on 07/06/15.
 */
public class GameVictory implements IGameState {
    @Override
    public void visit(DisplayScreen screen) {
        screen.printVictory();
    }

    @Override
    public void visit(Tank tank) {

    }

    @Override
    public void visit(IDrivingTracks tracks) {
        tracks.stop();
        tracks.setPower(0.75, 0.75);
        tracks.pause(500);
        tracks.setPower(-0.75, -0.75);
        tracks.pause(500);
        tracks.setPower(0.75, 0.75);
        tracks.pause(500);
        tracks.setPower(-0.75, -0.75);
        tracks.pause(500);
        tracks.stop();
    }

    @Override
    public void visit(RfidFlagSensor rfidFlagSensor) {

    }

    @Override
    public void visit(SoundSpeaker speaker) {
        speaker.setVolume(100);
        speaker.playVictory();
        speaker.setDefaultVolume();
    }
}
