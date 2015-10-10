package orwell.tank.inputs;

import orwell.tank.IInputVisitor;
import orwell.tank.Tank;
import orwell.tank.elements.DisplayScreen;
import orwell.tank.elements.IDrivingTracks;
import orwell.tank.elements.RfidFlagSensor;
import orwell.tank.elements.SoundSpeaker;

import java.util.List;

/**
 * Created by Michaël Ludmann on 6/10/15.
 */
public class GameState implements IInputVisitor {
    private final IGameState gameState;

    public GameState(List<String> gameStateInput) {
        if (1 != gameStateInput.size()) {
            gameState = null;
        } else {
            switch (gameStateInput.get(0)) {
                case "start":
                    gameState = new GameStart();
                    break;
                case "fail":
                    gameState = new GameDefeat();
                    break;
                case "vict":
                    gameState = new GameVictory();
                    break;
                case "draw":
                    gameState = new GameDraw();
                    break;
                default:
                    gameState = null;
                    break;
            }
        }
    }

    @Override
    public void visit(Tank tank) {
        if (null != gameState)
            gameState.visit(tank);
    }

    @Override
    public void visit(IDrivingTracks tracks) {
        if (null != gameState)
            gameState.visit(tracks);
    }

    @Override
    public void visit(RfidFlagSensor rfidFlagSensor) {
        if (null != gameState)
            gameState.visit(rfidFlagSensor);
    }

    @Override
    public void visit(SoundSpeaker speaker) {
        if (null != gameState)
            gameState.visit(speaker);
    }

    @Override
    public void visit(DisplayScreen screen) {
        if (null != gameState)
            gameState.visit(screen);
    }
}
