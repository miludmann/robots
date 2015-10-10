package orwell.tank.elements;

/**
 * Created by Michaël Ludmann on 6/11/15.
 */
public interface IDrivingTracks {

    void setPower(double powerLeft, double powerRight);

    void pause(int timeMs);

    void stop();

    void simulateRecoil();
}
