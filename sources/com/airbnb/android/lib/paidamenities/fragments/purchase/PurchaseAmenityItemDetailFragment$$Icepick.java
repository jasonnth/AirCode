package com.airbnb.android.lib.paidamenities.fragments.purchase;

import android.os.Bundle;
import com.airbnb.android.core.models.PaidAmenity;
import com.airbnb.android.lib.paidamenities.fragments.purchase.PurchaseAmenityItemDetailFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import java.util.HashMap;
import java.util.Map;

public class PurchaseAmenityItemDetailFragment$$Icepick<T extends PurchaseAmenityItemDetailFragment> extends BasePurchaseAmenityFragment$$Icepick<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9622H = new Helper("com.airbnb.android.lib.paidamenities.fragments.purchase.PurchaseAmenityItemDetailFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.paidAmenity = (PaidAmenity) f9622H.getParcelable(state, "paidAmenity");
            target.amenityServingsCount = f9622H.getInt(state, "amenityServingsCount");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9622H.putParcelable(state, "paidAmenity", target.paidAmenity);
        f9622H.putInt(state, "amenityServingsCount", target.amenityServingsCount);
    }
}
