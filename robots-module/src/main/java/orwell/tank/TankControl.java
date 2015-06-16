package orwell.tank;

import lejos.mf.common.UnitMessageType;
import lejos.nxt.*;
import lejos.mf.common.MessageListenerInterface;
import lejos.mf.common.UnitMessage;
import lejos.mf.nxt.MessageFrameworkNXT;
import orwell.tank.inputs.ConnectedToPC;
import orwell.tank.inputs.StartTank;
import orwell.tank.inputs.WaitForPC;


/**
 * Thread to wait for a Bluetooth connection and execute remote commands
 */
class TankControl extends Thread implements MessageListenerInterface {
	protected volatile boolean remoteCtrlAlive;
	private final Tank tank;
	private final MessageFrameworkNXT messageFramework;
	private final UnitMessageBroker unitMessageBroker;

	public TankControl(Tank tank) {
		this.tank = tank;
		this.messageFramework = MessageFrameworkNXT.getInstance();
		this.unitMessageBroker = new UnitMessageBroker(messageFramework);
	}

	public void run() {
		remoteCtrlAlive = true;

		UnitMessage rfidMessage;
		String rfidValueCurrent;
		String rfidValuePrevious = "null";

		while (!Button.ESCAPE.isDown() && remoteCtrlAlive) {
			rfidValueCurrent = Long.toString(rfidSensor.readTransponderAsLong(true));

			if (rfidValueCurrent.compareTo(rfidValuePrevious) == 0) {
				continue;
			} else {
				LCD.clear(4);
				LCD.clear(5);
				LCD.drawString(rfidValueCurrent, 0, 4, false);
				LCD.drawString(Integer.toString(rfidSensor.getStatus()), 0, 5, false);

				rfidMessage = new UnitMessage(UnitMessageType.Rfid, rfidValueCurrent);
				messageFramework.SendMessage(rfidMessage);
				rfidValuePrevious = rfidValueCurrent;
			}
		}
		Sound.buzz();
		UnitMessage stopMessage = new UnitMessage(UnitMessageType.Stop, "Escape Button pressed");
		messageFramework.SendMessage(stopMessage);

		messageFramework.StopListen();
	}

	public void stop_robot() {
		remoteCtrlAlive = false;
	}

	public static void main(String[] args) {
		Tank tank = new Tank();
		TankControl tankControl = new TankControl(tank);
		tankControl.startRemoteControl();
	}

	private void startRemoteControl() {
		tank.accept(new StartTank()); // Ready the tank
		waitForConnectionToProxy();
		start();
	}

	private void waitForConnectionToProxy() {
		tank.accept(new WaitForPC());

		messageFramework.addMessageListener(this);
		messageFramework.StartListen();
		tank.accept(new ConnectedToPC());
	}

	private void checkTankState() {
		StateSenderVisitor stateVisitor = new StateSenderVisitor(unitMessageBroker);
		tank.accept(stateVisitor);
	}


	@Override
	public void receivedNewMessage(UnitMessage msg) {
		IInputVisitor IInputVisitor = UnitMessageDecoder.parseFrom(msg);
		tank.accept(IInputVisitor);

//		if (msg.getPayload().equals("stop")) {
//			stopAllMotors();
//			LCD.clearDisplay();
//			LCD.drawString("STOP", 0, 6);
//		}
//
//		else if (msg.getPayload().equals("stopPrg")) {
//			stopAllMotors();
//			LCD.clearDisplay();
//			LCD.drawString("PROGRAM STOPPED", 0, 6);
//			stop_robot();
//		} else if (msg.getPayload().startsWith("input")) {
//			String inputString = msg.getPayload().toString();
//			LCD.clearDisplay();
//			LCD.drawString("1" + inputString, 0, 1);
//			String inputType = inputString
//					.substring(inputString.indexOf(" ") + 1);
//			// LCD.clearDisplay();
//			LCD.drawString("2" + inputType, 0, 2);
//			if (inputType.startsWith("move")) {
//				String moveOrder = inputType
//						.substring(inputType.indexOf(" ") + 1);
//				// LCD.clearDisplay();
//				LCD.drawString("3" + moveOrder, 0, 3);
//				LCD.drawString(
//						"4" + moveOrder.substring(0, moveOrder.indexOf(" ")),
//						0, 4);
//				LCD.drawString(
//						"5" + moveOrder.substring(moveOrder.indexOf(" ") + 1),
//						0, 5);
//				Double moveLeft = Double.parseDouble(((moveOrder.substring(0,
//						moveOrder.indexOf(" "))))) * 100;
//				Double moveRight = Double.parseDouble((moveOrder
//						.substring(moveOrder.indexOf(" ") + 1))) * 100;
//				// Double moveLeft = 0.50 * 100;
//				// Double moveRight = -0.234 * 100;
//				motorLeft.setPower(Math.abs(moveLeft.intValue()));
//				motorRight.setPower(Math.abs(moveRight.intValue()));
//				LCD.clearDisplay();
//				LCD.drawString("MVL: " + moveLeft, 0, 5);
//				LCD.drawString("MVR: " + Math.abs(moveRight.intValue()), 0, 6);
//				if (moveLeft > 0)
//					motorLeft.backward();
//				else if (moveLeft < 0)
//					motorLeft.forward();
//				else
//					motorLeft.stop();
//				if (moveRight > 0)
//					motorRight.backward();
//				else if (moveRight < 0)
//					motorRight.forward();
//				else
//					motorRight.stop();
//			} else if (inputType.startsWith("fire")) {
//				Sound.buzz();
//			} else if (inputType.startsWith("vict")) {
//				stopAllMotors();
//				Sound.beepSequenceUp();
//			} else if (inputType.startsWith("fail")) {
//				stopAllMotors();
//				Sound.buzz();
//			} else if (inputType.startsWith("draw")) {
//				stopAllMotors();
//				Sound.beepSequence();
//			} else {
//				LCD.drawString("Input Nomatch", 0, 5);
//			}
//		} else {
//			LCD.drawString("No match", 0, 1);
//		}

	}
}
