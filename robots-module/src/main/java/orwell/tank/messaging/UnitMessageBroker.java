package orwell.tank.messaging;

import lejos.mf.common.MessageListenerInterface;
import lejos.mf.common.UnitMessage;
import lejos.mf.nxt.MessageFrameworkNXT;
import orwell.tank.IInputVisitor;
import orwell.tank.INewValueVisitor;
import orwell.tank.ISensorListener;
import orwell.tank.Tank;
import orwell.tank.elements.ISensor;

/**
 * Created by Michaël Ludmann on 6/16/15.
 */
public class UnitMessageBroker implements MessageListenerInterface, ISensorListener {
    private final MessageFrameworkNXT messageFramework;
    private final Tank tank;

    public UnitMessageBroker(MessageFrameworkNXT messageFramework, Tank tank) {
        this.messageFramework = messageFramework;

        this.tank = tank;
    }

    private void startListenToProxy() {
        messageFramework.addMessageListener(this);
        messageFramework.StartListen();
    }

    private void startListenToSensors() {
        for (ISensor sensor : tank.getSensors()) {
            sensor.addSensorListener(this);
            sensor.startListen();
        }
    }

    private void stopListenToProxy() {
        messageFramework.StopListen();
    }

    private void stopListenToSensors() {
        for (ISensor sensor : tank.getSensors()) {
            sensor.stopListen();
        }
    }

    public void startListen() {
        startListenToProxy();
        startListenToSensors();
    }

    public void stopListen() {
        stopListenToSensors();
        stopListenToProxy();
    }

    @Override
    public void receivedNewValue(INewValueVisitor newValueVisitor) {
        if (null != newValueVisitor.getUnitMessage()) {
            messageFramework.SendMessage(newValueVisitor.getUnitMessage());
            tank.accept(newValueVisitor);
        }
    }

    @Override
    public void receivedNewMessage(UnitMessage msg) {
        IInputVisitor IInputVisitor = UnitMessageDecoder.parseFrom(msg);
        tank.accept(IInputVisitor);
    }
}
