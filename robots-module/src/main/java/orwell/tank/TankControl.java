package orwell.tank;

import lejos.mf.common.UnitMessage;
import lejos.mf.common.UnitMessageType;
import lejos.mf.nxt.MessageFrameworkNXT;
import lejos.nxt.Button;
import lejos.nxt.Sound;
import orwell.tank.config.TankFileBom;
import orwell.tank.config.TankFileParser;
import orwell.tank.elements.DisplayScreen;
import orwell.tank.exception.ParseIniException;
import orwell.tank.inputs.ConnectedToProxy;
import orwell.tank.inputs.StartTank;
import orwell.tank.inputs.WaitForProxy;
import orwell.tank.messaging.UnitMessageBroker;

import java.io.FileNotFoundException;


/**
 * Thread to wait for a Bluetooth connection and execute remote commands
 */
class TankControl extends Thread {
    private static final long THREAD_SLEEP_MS = 10;
    private final Tank tank;
    private MessageFrameworkNXT messageFramework;
    private UnitMessageBroker unitMessageBroker;

    public TankControl(Tank tank) {
        this.tank = tank;
    }

    public static void main(String[] args) {
        TankControl tankControl = TankControl.build();
        if (null == tankControl) {
            Button.waitForAnyPress();
            System.exit(0);
        }
        tankControl.startRemoteControl();
    }

    public static TankControl build() {
        try {
            TankFileParser tankFileParser = new TankFileParser();
            TankFileBom tankFileBom = tankFileParser.parse();
            Tank tank = new Tank(tankFileBom);
            return new TankControl(tank);
        } catch (FileNotFoundException e) {
            DisplayScreen.printError(".ini not found");
            Sound.buzz();
        } catch (ParseIniException e) {
            DisplayScreen.printError("Parse ini failed");
            Sound.buzz();
        }
        return null;
    }

    public void run() {
        tank.setIsTankAlive(true);

        while (!Button.ESCAPE.isDown() && tank.isAlive()) {
            try {
                Thread.currentThread().sleep(THREAD_SLEEP_MS);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        stopProgram();
    }

    private void stopProgram() {
        UnitMessage stopMessage = new UnitMessage(UnitMessageType.Stop, "Tank Program Stopped");
        messageFramework.SendMessage(stopMessage);
        stopMessageBroker();
    }

    private void startRemoteControl() {
        tank.accept(new StartTank()); // Ready the tank
        waitForConnectionToProxy(); // Establish BT connection
        startMessageBroker(); // Start listening to sensors
        start();
    }

    private void waitForConnectionToProxy() {
        tank.accept(new WaitForProxy());
        messageFramework = MessageFrameworkNXT.getInstance();

        tank.accept(new ConnectedToProxy());
    }

    private void startMessageBroker() {
        unitMessageBroker = new UnitMessageBroker(messageFramework, tank);
        unitMessageBroker.startListen();
    }

    private void stopMessageBroker() {
        unitMessageBroker.stopListen();
    }
}
