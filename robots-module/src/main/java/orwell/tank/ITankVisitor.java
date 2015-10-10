package orwell.tank;

import orwell.tank.elements.DisplayScreen;
import orwell.tank.elements.IDrivingTracks;
import orwell.tank.elements.RfidFlagSensor;
import orwell.tank.elements.SoundSpeaker;

/**
 * Created by Michaël Ludmann on 6/10/15.
 */
public interface ITankVisitor {
    void visit(Tank tank);

    void visit(IDrivingTracks tracks);

    void visit(RfidFlagSensor rfidFlagSensor);

    void visit(SoundSpeaker speaker);

    void visit(DisplayScreen screen);
}
