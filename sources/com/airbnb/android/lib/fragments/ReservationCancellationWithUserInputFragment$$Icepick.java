package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import com.airbnb.android.lib.fragments.ReservationCancellationWithUserInputFragment;
import com.airbnb.android.lib.fragments.ReservationCancellationWithUserInputFragment.InputReason;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ReservationCancellationWithUserInputFragment$$Icepick<T extends ReservationCancellationWithUserInputFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9550H = new Helper("com.airbnb.android.lib.fragments.ReservationCancellationWithUserInputFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.inputReason = (InputReason) f9550H.getSerializable(state, "inputReason");
            target.explanationString = f9550H.getString(state, "explanationString");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9550H.putSerializable(state, "inputReason", target.inputReason);
        f9550H.putString(state, "explanationString", target.explanationString);
    }
}
