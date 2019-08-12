package com.airbnb.jitney.event.logging.OperationResult.p166v1;

/* renamed from: com.airbnb.jitney.event.logging.OperationResult.v1.OperationResult */
public enum C2452OperationResult {
    Click(1),
    Send(2),
    Cancel(3),
    Import(4),
    Play(5),
    Pause(6),
    Mute(7),
    Unmute(8);
    
    public final int value;

    private C2452OperationResult(int value2) {
        this.value = value2;
    }
}
