package com.miteksystems.misnap.events;

public class OnTorchStateEvent {
    public final int currentTorchState;
    public final String function;

    public OnTorchStateEvent(String function2, int torchState) {
        this.currentTorchState = torchState;
        this.function = function2;
    }
}
