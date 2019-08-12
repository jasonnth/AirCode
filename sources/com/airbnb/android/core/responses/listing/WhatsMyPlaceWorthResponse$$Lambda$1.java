package com.airbnb.android.core.responses.listing;

import com.airbnb.android.core.models.C6120RoomType;
import com.airbnb.android.core.models.WhatsMyPlaceWorth;
import com.google.common.base.Predicate;

final /* synthetic */ class WhatsMyPlaceWorthResponse$$Lambda$1 implements Predicate {
    private final C6120RoomType arg$1;

    private WhatsMyPlaceWorthResponse$$Lambda$1(C6120RoomType roomType) {
        this.arg$1 = roomType;
    }

    public static Predicate lambdaFactory$(C6120RoomType roomType) {
        return new WhatsMyPlaceWorthResponse$$Lambda$1(roomType);
    }

    public boolean apply(Object obj) {
        return this.arg$1.equals(((WhatsMyPlaceWorth) obj).getRoomType());
    }
}
