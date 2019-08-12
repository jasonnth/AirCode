package com.airbnb.android.cohosting.fragments;

import android.os.Bundle;
import com.airbnb.android.cohosting.fragments.CohostLeadsCenterBrowseLeadsFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CohostLeadsCenterBrowseLeadsFragment$$Icepick<T extends CohostLeadsCenterBrowseLeadsFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7801H = new Helper("com.airbnb.android.cohosting.fragments.CohostLeadsCenterBrowseLeadsFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.cohostInquiries = f7801H.getParcelableArrayList(state, "cohostInquiries");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7801H.putParcelableArrayList(state, "cohostInquiries", target.cohostInquiries);
    }
}
