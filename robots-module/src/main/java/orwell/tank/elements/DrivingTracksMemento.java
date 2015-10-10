package orwell.tank.elements;

/**
 * Created by Michaël Ludmann on 6/11/15.
 */
class DrivingTracksMemento {
    private final double powerLeft;
    private final double powerRight;

    public DrivingTracksMemento(double powerLeft, double powerRight) {
        this.powerLeft = powerLeft;
        this.powerRight = powerRight;
    }

    public double getSavedPowerLeft() {
        return powerLeft;
    }

    public double getSavedPowerRight() {
        return powerRight;
    }

}
