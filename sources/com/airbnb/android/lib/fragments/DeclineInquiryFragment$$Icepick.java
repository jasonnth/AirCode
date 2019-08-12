package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import com.airbnb.android.core.messaging.p007db.ThreadDataModel;
import com.airbnb.android.lib.fragments.DeclineInquiryFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class DeclineInquiryFragment$$Icepick<T extends DeclineInquiryFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9529H = new Helper("com.airbnb.android.lib.fragments.DeclineInquiryFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.message = f9529H.getString(state, "message");
            target.threadId = f9529H.getLong(state, ThreadDataModel.THREADID);
            target.listingId = f9529H.getLong(state, "listingId");
            target.userName = f9529H.getString(state, "userName");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9529H.putString(state, "message", target.message);
        f9529H.putLong(state, ThreadDataModel.THREADID, target.threadId);
        f9529H.putLong(state, "listingId", target.listingId);
        f9529H.putString(state, "userName", target.userName);
    }
}
