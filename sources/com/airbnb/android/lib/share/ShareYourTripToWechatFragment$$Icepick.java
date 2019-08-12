package com.airbnb.android.lib.share;

import android.os.Bundle;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.lib.share.ShareYourTripAnalytics.EntryPoint;
import com.airbnb.android.lib.share.ShareYourTripToWechatFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ShareYourTripToWechatFragment$$Icepick<T extends ShareYourTripToWechatFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9660H = new Helper("com.airbnb.android.lib.share.ShareYourTripToWechatFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.entryPoint = (EntryPoint) f9660H.getSerializable(state, "entryPoint");
            target.reservation = (Reservation) f9660H.getParcelable(state, "reservation");
            target.photoMemories = f9660H.getParcelableArrayList(state, "photoMemories");
            target.tripTitleOverride = f9660H.getString(state, "tripTitleOverride");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9660H.putSerializable(state, "entryPoint", target.entryPoint);
        f9660H.putParcelable(state, "reservation", target.reservation);
        f9660H.putParcelableArrayList(state, "photoMemories", target.photoMemories);
        f9660H.putString(state, "tripTitleOverride", target.tripTitleOverride);
    }
}
