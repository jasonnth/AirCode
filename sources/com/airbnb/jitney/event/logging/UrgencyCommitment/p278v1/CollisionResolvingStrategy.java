package com.airbnb.jitney.event.logging.UrgencyCommitment.p278v1;

/* renamed from: com.airbnb.jitney.event.logging.UrgencyCommitment.v1.CollisionResolvingStrategy */
public enum CollisionResolvingStrategy {
    STATIC_PRIORITY_VALUE(0),
    PRIORITY_LIST_BY_BEV(1),
    RANDOM_MESSAGE_BY_BEV_AND_SEARCH(2),
    MANUAL_RULES_V0(3),
    MANUAL_RULES_V1(4);
    
    public final int value;

    private CollisionResolvingStrategy(int value2) {
        this.value = value2;
    }
}
