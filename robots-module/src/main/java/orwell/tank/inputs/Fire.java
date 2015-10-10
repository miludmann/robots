package orwell.tank.inputs;

import orwell.tank.IInputVisitor;
import orwell.tank.Tank;
import orwell.tank.elements.DisplayScreen;
import orwell.tank.elements.IDrivingTracks;
import orwell.tank.elements.RfidFlagSensor;
import orwell.tank.elements.SoundSpeaker;

import java.util.List;

/**
 * Created by Michaël Ludmann on 6/11/15.
 */
public class Fire implements IInputVisitor {
    private final boolean hasFire;
    private boolean leftWeapon;
    private boolean rightWeapon;

    public Fire(List<String> fireInput) {
        if (2 != fireInput.size()) {
            hasFire = false;
        } else {
            leftWeapon = Boolean.parseBoolean(fireInput.get(0));
            rightWeapon = Boolean.parseBoolean(fireInput.get(1));
            hasFire = true;
        }
    }

    public boolean hasLeftWeaponFired() {
        return leftWeapon;
    }

    public boolean hasRightWeaponFired() {
        return rightWeapon;
    }

    @Override
    public void visit(Tank tank) {

    }

    @Override
    public void visit(IDrivingTracks tracks) {
        if (hasRightWeaponFired()) // bigger weapon
            tracks.simulateRecoil();
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
