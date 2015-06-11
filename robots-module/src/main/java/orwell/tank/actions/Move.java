package orwell.tank.actions;

import orwell.tank.IActionVisitor;
import orwell.tank.Tank;
import orwell.tank.elements.RfidFlagSensor;
import orwell.tank.elements.SoundSpeaker;
import orwell.tank.elements.DrivingTracksRegulated;

import java.util.List;

/**
 * Created by Michaël Ludmann on 6/10/15.
 */
public class Move implements IActionVisitor {
    private double leftMove;
    private double rightMove;
    private boolean hasMove;

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

    @Override
    public void visit(Tank tank) {

    }

    @Override
    public void visit(DrivingTracksRegulated tracks) {
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
}
