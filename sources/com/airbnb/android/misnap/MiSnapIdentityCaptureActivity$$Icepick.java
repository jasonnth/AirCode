package com.airbnb.android.misnap;

import android.os.Bundle;
import com.airbnb.android.misnap.MiSnapController.State;
import com.airbnb.android.misnap.MiSnapIdentityCaptureActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class MiSnapIdentityCaptureActivity$$Icepick<T extends MiSnapIdentityCaptureActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10195H = new Helper("com.airbnb.android.misnap.MiSnapIdentityCaptureActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.currentState = (State) f10195H.getSerializable(state, "currentState");
            target.isAutoCaptureMode = f10195H.getBoolean(state, "isAutoCaptureMode");
            target.isBarcodRequest = f10195H.getBoolean(state, "isBarcodRequest");
            target.isShowingError = f10195H.getBoolean(state, "isShowingError");
            target.hasBarcode = f10195H.getBoolean(state, "hasBarcode");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10195H.putSerializable(state, "currentState", target.currentState);
        f10195H.putBoolean(state, "isAutoCaptureMode", target.isAutoCaptureMode);
        f10195H.putBoolean(state, "isBarcodRequest", target.isBarcodRequest);
        f10195H.putBoolean(state, "isShowingError", target.isShowingError);
        f10195H.putBoolean(state, "hasBarcode", target.hasBarcode);
    }
}
