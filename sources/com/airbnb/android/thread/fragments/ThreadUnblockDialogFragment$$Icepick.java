package com.airbnb.android.thread.fragments;

import android.os.Bundle;
import com.airbnb.android.thread.fragments.ThreadUnblockDialogFragment;
import com.braintreepayments.api.models.PostalAddress;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ThreadUnblockDialogFragment$$Icepick<T extends ThreadUnblockDialogFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f2561H = new Helper("com.airbnb.android.thread.fragments.ThreadUnblockDialogFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.recipientName = f2561H.getString(state, PostalAddress.RECIPIENT_NAME_KEY);
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f2561H.putString(state, PostalAddress.RECIPIENT_NAME_KEY, target.recipientName);
    }
}
