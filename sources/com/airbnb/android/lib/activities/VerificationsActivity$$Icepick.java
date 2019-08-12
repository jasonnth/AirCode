package com.airbnb.android.lib.activities;

import android.os.Bundle;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.verifications.VerificationsState;
import com.airbnb.android.lib.activities.VerificationsActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class VerificationsActivity$$Icepick<T extends VerificationsActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9481H = new Helper("com.airbnb.android.lib.activities.VerificationsActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.listing = (Listing) f9481H.getParcelable(state, "listing");
            target.verificationsState = (VerificationsState) f9481H.getParcelable(state, "verificationsState");
            target.verificationFlow = (VerificationFlow) f9481H.getSerializable(state, "verificationFlow");
            target.debugEnabled = f9481H.getBoolean(state, "debugEnabled");
            target.isDotsProgressBarDisplayed = f9481H.getBoolean(state, "isDotsProgressBarDisplayed");
            target.isVerificationsStateUninitialized = f9481H.getBoolean(state, "isVerificationsStateUninitialized");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9481H.putParcelable(state, "listing", target.listing);
        f9481H.putParcelable(state, "verificationsState", target.verificationsState);
        f9481H.putSerializable(state, "verificationFlow", target.verificationFlow);
        f9481H.putBoolean(state, "debugEnabled", target.debugEnabled);
        f9481H.putBoolean(state, "isDotsProgressBarDisplayed", target.isDotsProgressBarDisplayed);
        f9481H.putBoolean(state, "isVerificationsStateUninitialized", target.isVerificationsStateUninitialized);
    }
}
