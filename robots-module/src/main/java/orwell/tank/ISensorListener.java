package orwell.tank;

/**
 * Created by Michaël Ludmann on 6/16/15.
 */
public interface ISensorListener {

    void receivedNewValue(INewValueVisitor newValueVisitor);
}
