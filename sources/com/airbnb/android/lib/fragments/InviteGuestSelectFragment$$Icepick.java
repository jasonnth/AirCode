package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import com.airbnb.android.lib.fragments.InviteGuestSelectFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class InviteGuestSelectFragment$$Icepick<T extends InviteGuestSelectFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9535H = new Helper("com.airbnb.android.lib.fragments.InviteGuestSelectFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.selectedEmails = f9535H.getStringArrayList(state, "selectedEmails");
            target.query = f9535H.getString(state, "query");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9535H.putStringArrayList(state, "selectedEmails", target.selectedEmails);
        f9535H.putString(state, "query", target.query);
    }
}
