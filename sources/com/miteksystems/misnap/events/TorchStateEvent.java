package com.miteksystems.misnap.events;

public class TorchStateEvent {
    public String function;
    public final boolean newState;

    public TorchStateEvent(String function2) {
        this.function = function2;
        this.newState = false;
        if (!this.function.equals("GET") && !this.function.equals("SET")) {
            this.function = "GET";
        }
    }

    public TorchStateEvent(String function2, boolean newState2) {
        this.function = function2;
        this.newState = newState2;
    }
}
