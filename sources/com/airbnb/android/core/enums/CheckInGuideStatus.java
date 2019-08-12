package com.airbnb.android.core.enums;

import com.google.common.collect.FluentIterable;

public enum CheckInGuideStatus {
    NotCreated(null),
    NotPublished(Integer.valueOf(0)),
    Published(Integer.valueOf(1));
    
    public final Integer serverKey;

    private CheckInGuideStatus(Integer serverKey2) {
        this.serverKey = serverKey2;
    }

    public static CheckInGuideStatus getTypeFromKeyOrDefault(Integer statusKey) {
        return (CheckInGuideStatus) FluentIterable.from((E[]) values()).firstMatch(CheckInGuideStatus$$Lambda$1.lambdaFactory$(statusKey)).mo41059or(NotCreated);
    }
}
