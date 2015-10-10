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
public class Move implements IInputVisitor {
    private final boolean hasMove;
    private double leftMove;
    private double rightMove;

    public Move(List<String> moveInput) {
        if (2 != moveInput.size()) {
            hasMove = false;
        } else {
            leftMove = Double.parseDouble(moveInput.get(0));
            rightMove = Double.parseDouble(moveInput.get(1));
            hasMove = true;
        }
    }

    public boolean hasMove() {
        return hasMove;
    }

    public double getRightMove() {
        return rightMove;
    }

    public double getLeftMove() {
        return leftMove;
    }

    @Override
    public void visit(Tank tank) {

    }

    @Override
    public void visit(IDrivingTracks tracks) {
        if (hasMove()) {
            tracks.setPower(leftMove, rightMove);
        }
    }

    @Override
    public void visit(RfidFlagSensor rfidFlagSensor) {

    }

    @Override
    public void visit(SoundSpeaker speaker) {
        speaker.playActionTone(this);
    }

    @Override
    public void visit(DisplayScreen screen) {
        screen.printAction(this);
    }
}
