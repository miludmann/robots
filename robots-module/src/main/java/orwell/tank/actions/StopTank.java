package orwell.tank.actions;

import orwell.tank.IActionVisitor;
import orwell.tank.Tank;

import java.util.List;

/**
 * Created by Michaël Ludmann on 6/10/15.
 */
public class StopTank implements IActionVisitor {
    public StopTank(List<String> payloadBody) {

    }

    @Override
    public void visit(Tank tank) {

    }
}
