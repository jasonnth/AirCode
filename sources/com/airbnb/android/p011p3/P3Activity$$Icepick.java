package com.airbnb.android.p011p3;

import android.os.Bundle;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.p009p3.P3State;
import com.airbnb.android.p011p3.P3Activity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.airbnb.android.p3.P3Activity$$Icepick */
public class P3Activity$$Icepick<T extends P3Activity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10532H = new Helper("com.airbnb.android.p3.P3Activity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.state = (P3State) f10532H.getParcelable(state, "state");
            target.incompleteVerifications = (HashMap) f10532H.getSerializable(state, "incompleteVerifications");
            target.initialState = (P3State) f10532H.getParcelable(state, "initialState");
            target.numLocalAttractions = f10532H.getBoxedInt(state, "numLocalAttractions");
            target.verificationFlow = (VerificationFlow) f10532H.getSerializable(state, "verificationFlow");
            target.launchP4OnDatesPickerResult = f10532H.getBoolean(state, "launchP4OnDatesPickerResult");
            target.dismissDatePickerOnResume = f10532H.getBoolean(state, "dismissDatePickerOnResume");
            target.hasLoadedNonCriticalData = f10532H.getBoolean(state, "hasLoadedNonCriticalData");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10532H.putParcelable(state, "state", target.state);
        f10532H.putSerializable(state, "incompleteVerifications", target.incompleteVerifications);
        f10532H.putParcelable(state, "initialState", target.initialState);
        f10532H.putBoxedInt(state, "numLocalAttractions", target.numLocalAttractions);
        f10532H.putSerializable(state, "verificationFlow", target.verificationFlow);
        f10532H.putBoolean(state, "launchP4OnDatesPickerResult", target.launchP4OnDatesPickerResult);
        f10532H.putBoolean(state, "dismissDatePickerOnResume", target.dismissDatePickerOnResume);
        f10532H.putBoolean(state, "hasLoadedNonCriticalData", target.hasLoadedNonCriticalData);
    }
}
