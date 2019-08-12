package com.airbnb.android.core.enums;

import com.google.common.collect.FluentIterable;

public enum LegacyBedType {
    RealBed("real_bed"),
    PullOutSofa("pull_out_sofa"),
    AirBed("airbed"),
    Futon("futon"),
    Couch("couch");
    
    public final String serverDescKey;

    private LegacyBedType(String serverDescKey2) {
        this.serverDescKey = serverDescKey2;
    }

    public static LegacyBedType getTypeFromKey(String bedTypeKey) {
        return (LegacyBedType) FluentIterable.from((E[]) values()).firstMatch(LegacyBedType$$Lambda$1.lambdaFactory$(bedTypeKey)).orNull();
    }

    public static LegacyBedType getTypeFromKeyOrDefault(String bedTypeKey) {
        LegacyBedType type = getTypeFromKey(bedTypeKey);
        return type != null ? type : RealBed;
    }
}
