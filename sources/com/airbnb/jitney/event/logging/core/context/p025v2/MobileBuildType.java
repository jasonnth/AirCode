package com.airbnb.jitney.event.logging.core.context.p025v2;

/* renamed from: com.airbnb.jitney.event.logging.core.context.v2.MobileBuildType */
public enum MobileBuildType {
    Debug(1),
    Alpha(2),
    Beta(3),
    QA(4),
    Release(5),
    China(6);
    
    public final int value;

    private MobileBuildType(int value2) {
        this.value = value2;
    }
}
