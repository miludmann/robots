package orwell.tank.elements;

import lejos.nxt.LCD;
import lejos.nxt.NXTMotor;

import static java.lang.Math.abs;


/**
 * Created by Michaël Ludmann on 6/10/15.
 */
public class DrivingTracksNonRegulated implements IDrivingTracks {
    private final NXTMotor leftMotor;
    private final NXTMotor rightMotor;
    private final boolean isLeftInverted;
    private final boolean isRightInverted;

    public DrivingTracksNonRegulated(NXTMotor leftMotor, NXTMotor rightMotor,
                                     boolean isLeftInverted, boolean isRightInverted) {
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
        this.isLeftInverted = isLeftInverted;
        this.isRightInverted = isRightInverted;
    }

    @Override
    public void setPower(double powerLeft, double powerRight) {
        setPowerToMotor(leftMotor, isLeftInverted ? -powerLeft : powerLeft);
        setPowerToMotor(rightMotor, isRightInverted ? -powerRight : powerRight);
    }

    @Override
    public void pause(int timeMs) {
        try {
            Thread.sleep(timeMs);
        } catch (InterruptedException e) {
            LCD.drawString(e.getMessage(), 0, 0);
        }
    }

    @Override
    public void stop() {
        leftMotor.stop();
        rightMotor.stop();
    }

    @Override
    public void simulateRecoil() {

    }

    private void setPowerToMotor(NXTMotor motor, double power) {
        motor.setPower((int) (abs(power) * 100));
        if (0 < power)
            motor.backward();
        else if (0 > power)
            motor.forward();
        else
            motor.flt();
    }
}
