package orwell.tank.elements;

import lejos.nxt.Sound;

/**
 * Created by Michaël Ludmann on 6/11/15.
 */
class Tone implements SoundElement {
    private final int frequency;
    private final int duration;
    private final int volume;

    public Tone(int frequency, int duration, int volume) {
        this.frequency = frequency;
        this.duration = duration;
        this.volume = volume;
    }

    @Override
    public void play() {
        Sound.playTone(frequency, duration, volume);
    }
}
