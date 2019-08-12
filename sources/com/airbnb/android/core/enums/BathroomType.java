package com.airbnb.android.core.enums;

import com.google.common.collect.FluentIterable;

public enum BathroomType {
    PrivateBathroom("private"),
    SharedBathroom("shared"),
    Unknown("");
    
    public final String serverKey;

    private BathroomType(String serverKey2) {
        this.serverKey = serverKey2;
    }

    public static BathroomType getTypeFromKeyOrDefault(String bathroomTypeKey) {
        return (BathroomType) FluentIterable.from((E[]) values()).firstMatch(BathroomType$$Lambda$1.lambdaFactory$(bathroomTypeKey)).mo41059or(Unknown);
    }
}
