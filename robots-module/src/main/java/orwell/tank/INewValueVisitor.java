package orwell.tank;

import lejos.mf.common.UnitMessage;

/**
 * Created by Michaël Ludmann on 6/10/15.
 */
public interface INewValueVisitor extends ITankVisitor {

    String getValue();

    UnitMessage getUnitMessage();
}
