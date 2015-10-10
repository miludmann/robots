package orwell.tank.elements;

import lejos.nxt.Sound;

/**
 * Created by Michaël Ludmann on 6/18/15.
 */
public class Silence implements SoundElement {
    private int timeMs;

    public Silence(int timeMs) {
        this.timeMs = timeMs;
    }

    @Override
    public void play() {
        Sound.pause(timeMs);
    }
}
