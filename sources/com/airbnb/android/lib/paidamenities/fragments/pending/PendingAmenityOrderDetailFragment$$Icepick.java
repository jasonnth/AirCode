package com.airbnb.android.lib.paidamenities.fragments.pending;

import android.os.Bundle;
import com.airbnb.android.core.models.PaidAmenityOrder;
import com.airbnb.android.lib.paidamenities.fragments.pending.BasePendingAmenityFragment.UserMode;
import com.airbnb.android.lib.paidamenities.fragments.pending.PendingAmenityOrderDetailFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import java.util.HashMap;
import java.util.Map;

public class PendingAmenityOrderDetailFragment$$Icepick<T extends PendingAmenityOrderDetailFragment> extends BasePendingAmenityFragment$$Icepick<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9618H = new Helper("com.airbnb.android.lib.paidamenities.fragments.pending.PendingAmenityOrderDetailFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.userMode = (UserMode) f9618H.getSerializable(state, "userMode");
            target.paidAmenityOrder = (PaidAmenityOrder) f9618H.getParcelable(state, "paidAmenityOrder");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9618H.putSerializable(state, "userMode", target.userMode);
        f9618H.putParcelable(state, "paidAmenityOrder", target.paidAmenityOrder);
    }
}
