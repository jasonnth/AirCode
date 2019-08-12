package com.airbnb.jitney.event.logging.NativeMeasurementType.p159v1;

/* renamed from: com.airbnb.jitney.event.logging.NativeMeasurementType.v1.NativeMeasurementType */
public enum C2445NativeMeasurementType {
    TTI(1),
    FullPageLoadTime(2),
    FrameRate(3),
    PaginationTTI(4),
    ColdStartTime(5),
    WarmStartTime(6),
    PreMainTime(7),
    ActionDuration(8),
    RenderDuration(9);
    
    public final int value;

    private C2445NativeMeasurementType(int value2) {
        this.value = value2;
    }
}
