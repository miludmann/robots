package orwell.tank.inputs;

import orwell.tank.Tank;
import orwell.tank.elements.DisplayScreen;
import orwell.tank.elements.IDrivingTracks;
import orwell.tank.elements.RfidFlagSensor;
import orwell.tank.elements.SoundSpeaker;

/**
 * Created by Michaël Ludmann on 10/10/15.
 */
public class GameStart extends StartTank implements IGameState {

    @Override
    public void visit(Tank tank) {

    }

    @Override
    public void visit(IDrivingTracks tracks) {
        tracks.stop(); // when a new game starts, ensure the tank is not moving
    }

    @Override
    public void visit(RfidFlagSensor rfidFlagSensor) {
        super.visit(rfidFlagSensor);
    }

    @Override
    public void visit(SoundSpeaker speaker) {
        super.visit(speaker);
    }

    @Override
    public void visit(DisplayScreen screen) {
        super.visit(screen);
    }
}
