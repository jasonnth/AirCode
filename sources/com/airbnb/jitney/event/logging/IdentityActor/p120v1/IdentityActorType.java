package com.airbnb.jitney.event.logging.IdentityActor.p120v1;

/* renamed from: com.airbnb.jitney.event.logging.IdentityActor.v1.IdentityActorType */
public enum IdentityActorType {
    USER(1),
    SERVER(2),
    CLIENT(3),
    JUMIO(4),
    MITEK(5);
    
    public final int value;

    private IdentityActorType(int value2) {
        this.value = value2;
    }
}
