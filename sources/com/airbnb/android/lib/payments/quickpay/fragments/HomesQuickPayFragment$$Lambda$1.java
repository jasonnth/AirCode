package com.airbnb.android.lib.payments.quickpay.fragments;

import com.airbnb.android.core.responses.ReservationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class HomesQuickPayFragment$$Lambda$1 implements Action1 {
    private final HomesQuickPayFragment arg$1;

    private HomesQuickPayFragment$$Lambda$1(HomesQuickPayFragment homesQuickPayFragment) {
        this.arg$1 = homesQuickPayFragment;
    }

    public static Action1 lambdaFactory$(HomesQuickPayFragment homesQuickPayFragment) {
        return new HomesQuickPayFragment$$Lambda$1(homesQuickPayFragment);
    }

    public void call(Object obj) {
        this.arg$1.returnReservationResult((ReservationResponse) obj);
    }
}