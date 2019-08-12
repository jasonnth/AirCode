package com.airbnb.android.core.p009p3.interfaces;

/* renamed from: com.airbnb.android.core.p3.interfaces.OnP3DataChangedListener */
public interface OnP3DataChangedListener {
    void onIdentityForBookingCompleted();

    void onListingLoaded();

    void onPicturePositionChanged(int i);

    void onPricingQuoteLoaded();

    void onStateChanged();
}
