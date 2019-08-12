package com.airbnb.jitney.event.logging.CancellationFlowHostNavigationMethod.p050v1;

/* renamed from: com.airbnb.jitney.event.logging.CancellationFlowHostNavigationMethod.v1.CancellationFlowHostNavigationMethod */
public enum C1896CancellationFlowHostNavigationMethod {
    Back(1),
    Next(2),
    AskGuest(3),
    MessageGuest(4),
    FinishCancel(5);
    
    public final int value;

    private C1896CancellationFlowHostNavigationMethod(int value2) {
        this.value = value2;
    }
}
