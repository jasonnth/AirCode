package com.airbnb.android.listing.controllers;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class WhatsMyPlaceWorthEpoxyController$$Lambda$2 implements OnClickListener {
    private final WhatsMyPlaceWorthEpoxyController arg$1;

    private WhatsMyPlaceWorthEpoxyController$$Lambda$2(WhatsMyPlaceWorthEpoxyController whatsMyPlaceWorthEpoxyController) {
        this.arg$1 = whatsMyPlaceWorthEpoxyController;
    }

    public static OnClickListener lambdaFactory$(WhatsMyPlaceWorthEpoxyController whatsMyPlaceWorthEpoxyController) {
        return new WhatsMyPlaceWorthEpoxyController$$Lambda$2(whatsMyPlaceWorthEpoxyController);
    }

    public void onClick(View view) {
        this.arg$1.listener.capacityRequested();
    }
}