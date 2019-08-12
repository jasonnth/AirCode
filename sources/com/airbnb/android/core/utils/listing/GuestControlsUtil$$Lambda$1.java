package com.airbnb.android.core.utils.listing;

import com.airbnb.android.core.models.GuestControlType;
import com.airbnb.android.core.models.GuestControls;
import com.google.common.base.Predicate;

final /* synthetic */ class GuestControlsUtil$$Lambda$1 implements Predicate {
    private final GuestControls arg$1;
    private final GuestControls arg$2;

    private GuestControlsUtil$$Lambda$1(GuestControls guestControls, GuestControls guestControls2) {
        this.arg$1 = guestControls;
        this.arg$2 = guestControls2;
    }

    public static Predicate lambdaFactory$(GuestControls guestControls, GuestControls guestControls2) {
        return new GuestControlsUtil$$Lambda$1(guestControls, guestControls2);
    }

    public boolean apply(Object obj) {
        return GuestControlsUtil.lambda$controlsHaveBeenUpdated$0(this.arg$1, this.arg$2, (GuestControlType) obj);
    }
}
