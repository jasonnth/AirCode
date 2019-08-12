package com.airbnb.android.booking.steps;

import android.os.Bundle;
import com.airbnb.android.booking.steps.IdentityBookingStep;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class IdentityBookingStep$$Icepick<T extends IdentityBookingStep> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7443H = new Helper("com.airbnb.android.booking.steps.IdentityBookingStep$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.incompleteVerifications = f7443H.getParcelableArrayList(state, "incompleteVerifications");
            target.isInstantBookableIfGovIdProvided = f7443H.getBoolean(state, "isInstantBookableIfGovIdProvided");
            target.selfiePhotoFilePaths = f7443H.getStringArrayList(state, "selfiePhotoFilePaths");
            target.skipped = f7443H.getBoolean(state, "skipped");
            target.completed = f7443H.getBoolean(state, InternalLogger.EVENT_PARAM_EXTRAS_COMPLETED);
            target.isVerificationFetchComplete = f7443H.getBoolean(state, "isVerificationFetchComplete");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7443H.putParcelableArrayList(state, "incompleteVerifications", target.incompleteVerifications);
        f7443H.putBoolean(state, "isInstantBookableIfGovIdProvided", target.isInstantBookableIfGovIdProvided);
        f7443H.putStringArrayList(state, "selfiePhotoFilePaths", target.selfiePhotoFilePaths);
        f7443H.putBoolean(state, "skipped", target.skipped);
        f7443H.putBoolean(state, InternalLogger.EVENT_PARAM_EXTRAS_COMPLETED, target.completed);
        f7443H.putBoolean(state, "isVerificationFetchComplete", target.isVerificationFetchComplete);
    }
}
