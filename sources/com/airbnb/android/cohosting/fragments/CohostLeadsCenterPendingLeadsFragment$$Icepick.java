package com.airbnb.android.cohosting.fragments;

import android.os.Bundle;
import com.airbnb.android.cohosting.fragments.CohostLeadsCenterPendingLeadsFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CohostLeadsCenterPendingLeadsFragment$$Icepick<T extends CohostLeadsCenterPendingLeadsFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7802H = new Helper("com.airbnb.android.cohosting.fragments.CohostLeadsCenterPendingLeadsFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.respondedInquiries = f7802H.getParcelableArrayList(state, "respondedInquiries");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7802H.putParcelableArrayList(state, "respondedInquiries", target.respondedInquiries);
    }
}
