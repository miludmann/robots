package orwell.tank.elements;

import orwell.tank.inputs.Fire;
import orwell.tank.inputs.Move;

import java.util.LinkedList;

/**
 * Created by Michaël Ludmann on 6/11/15.
 */
public class SoundSpeaker {
    private static final int DEFAULT_VOLUME = 80;
    private static final int G5_FREQ = 784;
    private static final int G4_FREQ = 392;
    private static final int G3_FREQ = 196;
    private static final int G2_FREQ = 98;
    private static final int C4_FREQ = 262;
    private static final int C2_FREQ = 65;
    private static final int D4_FREQ = 294;
    private static final int E4_FREQ = 330;
    private static final int B4_FREQ = 494;
    private static final int A4_FREQ = 440;
    private final LinkedList<SoundElement> soundElementQueue;
    private SoundPlayer player;
    private volatile boolean shouldContinue;
    private int volume;


    public SoundSpeaker() {
        setDefaultVolume();
        soundElementQueue = new LinkedList<>();
        startSoundSpeaker();
    }

    private void startSoundSpeaker() {
        if (!shouldContinue) {
            player = new SoundPlayer();
            player.setDaemon(true);
            shouldContinue = true;
            player.start();
        }
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setDefaultVolume() {
        this.volume = DEFAULT_VOLUME;
    }

    public void stopSoundSpeaker() {
        shouldContinue = false;
    }

    protected void playTone(int frequency, int duration) {
        soundElementQueue.add(new Tone(frequency, duration, volume));
    }

    public void playActionTone(Move move) {
        playTone(700, 50);
    }

    public void playActionTone(Fire fire) {
        if (fire.hasLeftWeaponFired())
            playTone(250, 300);
        if (fire.hasRightWeaponFired()) // bigger weapon
            playTone(150, 800);
    }

    public void playStopProgram() {
        for (int i = 4; i < 12; i++) {
            playTone(G5_FREQ * i / 4, 100);
            pause(100);
        }
    }

    public void playStopTank() {
        playTone(800, 50);
    }

    public void playWaitingForPC() {
        for (int i = 4; i < 8; i++) {
            playTone(G4_FREQ, 100);
        }
    }

    public void playConnectedToPC() {
        playTone(G4_FREQ, 300);
    }

    public void playStartTank() {
        for (int i = 4; i < 12; i++) {
            playTone(G2_FREQ * i / 4, 100);
            pause(100);
        }
    }

    public void playRfidNewValue() {
        playTone(C4_FREQ, 100);
        pause(100);
        playTone(C4_FREQ, 100);
    }

    private boolean shouldContinue() {
        return shouldContinue;
    }

    public void playNotHandled() {
        playTone(C2_FREQ, 200);
    }

    public void playVictory() {
        playTone(C4_FREQ, 500);
        pause(200);
        playTone(C4_FREQ, 500);
        pause(200);
        playTone(D4_FREQ, 500);
        pause(200);
        playTone(E4_FREQ, 500);
    }

    public void playDefeat() {
        playTone(C4_FREQ, 500);
        pause(300);
        playTone(C4_FREQ, 500);
        pause(300);
        playTone(B4_FREQ, 500);
        pause(300);
        playTone(A4_FREQ, 700);
    }

    protected void pause(int timeMs) {
        soundElementQueue.add(new Silence(timeMs));
    }

    public void playDraw() {
        playTone(C4_FREQ, 500);
        pause(100);
        playTone(D4_FREQ, 500);
        pause(100);
        playTone(C4_FREQ, 300);
    }


    private class SoundPlayer extends Thread {

        @Override
        public void run() {
            while (shouldContinue()) {
                playNextSound();
            }
        }

        private void playNextSound() {
            if (!soundElementQueue.isEmpty()) {
                SoundElement soundElement = soundElementQueue.remove(0);
                soundElement.play();
            }
        }
    }
}
