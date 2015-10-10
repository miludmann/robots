package orwell.tank.elements;

import lejos.nxt.LCD;
import orwell.tank.events.RfidNewValue;
import orwell.tank.inputs.Fire;
import orwell.tank.inputs.Move;

/**
 * Created by Michaël Ludmann on 6/12/15.
 */
public class DisplayScreen extends LCD {

    private static final int MOVE_LINE = 2;
    private static final boolean IS_MOVE_INVERTED = false;

    private static final int FIRE_LINE = 2;
    private static final boolean IS_FIRE_INVERTED = false;

    private static final int WAITING_LINE = 6;
    private static final boolean IS_WAITING_INVERTED = true;

    private static final int CONNECTED_LINE = 6;
    private static final boolean IS_CONNECTED_INVERTED = true;

    private static final int STOP_TANK_LINE = 5;
    private static final boolean IS_STOP_TANK_INVERTED = false;

    private static final int START_TANK_LINE = 5;
    private static final boolean IS_START_TANK_INVERTED = false;

    private static final int RFID_LINE = 4;
    private static final boolean IS_RFID_INVERTED = false;

    private static final int NOT_HANDLED_LINE = 2;
    private static final boolean IS_NOT_HANDLED_INVERTED = true;

    private static final int LOG_LINE = 7;
    private static final boolean IS_LOG_INVERTED = false;

    private static final int GAMESTATE_LINE = 1;
    private static final boolean IS_GAMESTATE_INVERTED = true;

    public static void printLog(String log) {
        LCD.clear(LOG_LINE);
        LCD.drawString(log, 0, LOG_LINE, IS_LOG_INVERTED);
    }

    public static void printError(String log) {
        LCD.clear(LOG_LINE);
        LCD.drawString(log, 0, LOG_LINE, !IS_LOG_INVERTED);
    }

    public void printWaitingForPC() {
        LCD.clear(WAITING_LINE);
        LCD.drawString(" Waiting for PC ", 0, WAITING_LINE, IS_WAITING_INVERTED);
    }

    public void printConnected() {
        LCD.clear(CONNECTED_LINE);
        LCD.drawString("Connected!", 0, CONNECTED_LINE, IS_CONNECTED_INVERTED);
    }

    public void printNotConnected() {
        LCD.clear(STOP_TANK_LINE);
        LCD.clear(CONNECTED_LINE);
        LCD.drawString("Disconnected!", 0, CONNECTED_LINE, !IS_CONNECTED_INVERTED);
    }

    public void printStopTank() {
        LCD.clear(STOP_TANK_LINE);
        LCD.clear(MOVE_LINE);
        LCD.drawString("TANK STOPPED", 0, STOP_TANK_LINE, IS_STOP_TANK_INVERTED);
    }

    public void printStartTank() {
        LCD.clear(START_TANK_LINE);
        LCD.drawString("TANK STARTED", 0, START_TANK_LINE, IS_START_TANK_INVERTED);
    }

    public void printNewValue(RfidNewValue newValue) {
        LCD.clear(STOP_TANK_LINE);
        LCD.clear(RFID_LINE);
        LCD.drawString("RFID " + newValue.getValue(), 0, RFID_LINE, IS_RFID_INVERTED);
    }

    public void printAction(Fire fire) {
        LCD.clear(STOP_TANK_LINE);
        LCD.clear(FIRE_LINE);
        LCD.drawString("Fire " + fire.hasLeftWeaponFired() + " "
                + fire.hasRightWeaponFired(), 0, FIRE_LINE, IS_FIRE_INVERTED);
    }

    public void printAction(Move move) {
        LCD.clear(STOP_TANK_LINE);
        LCD.clear(MOVE_LINE);
        LCD.drawString("Move " + move.getLeftMove() + " " + move.getRightMove(),
                0, MOVE_LINE, IS_MOVE_INVERTED);
    }

    public void printNotHandled() {
        LCD.clear(STOP_TANK_LINE);
        LCD.clear(NOT_HANDLED_LINE);
        LCD.drawString("Msg not handled", 0, NOT_HANDLED_LINE, IS_NOT_HANDLED_INVERTED);
    }

    public void printVictory() {
        LCD.clear(GAMESTATE_LINE);
        LCD.drawString("VICTORY \\o/", 0, GAMESTATE_LINE, IS_GAMESTATE_INVERTED);
    }

    public void printDefeat() {
        LCD.clear(GAMESTATE_LINE);
        LCD.drawString("DEFEAT :(", 0, GAMESTATE_LINE, IS_GAMESTATE_INVERTED);
    }

    public void printDraw() {
        LCD.clear(GAMESTATE_LINE);
        LCD.drawString("DRAW :/", 0, GAMESTATE_LINE, IS_GAMESTATE_INVERTED);
    }
}
