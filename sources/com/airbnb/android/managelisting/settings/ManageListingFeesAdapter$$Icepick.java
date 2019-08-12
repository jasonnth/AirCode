package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import com.airbnb.android.managelisting.settings.ManageListingFeesAdapter;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ManageListingFeesAdapter$$Icepick<T extends ManageListingFeesAdapter> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10179H = new Helper("com.airbnb.android.managelisting.settings.ManageListingFeesAdapter$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.includedGuests = f10179H.getInt(state, "includedGuests");
            target.includedGuestPrice = f10179H.getBoxedInt(state, "includedGuestPrice");
            target.cleaningFee = f10179H.getBoxedInt(state, "cleaningFee");
            target.securityDeposit = f10179H.getBoxedInt(state, "securityDeposit");
            target.weekendPrice = f10179H.getBoxedInt(state, "weekendPrice");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10179H.putInt(state, "includedGuests", target.includedGuests);
        f10179H.putBoxedInt(state, "includedGuestPrice", target.includedGuestPrice);
        f10179H.putBoxedInt(state, "cleaningFee", target.cleaningFee);
        f10179H.putBoxedInt(state, "securityDeposit", target.securityDeposit);
        f10179H.putBoxedInt(state, "weekendPrice", target.weekendPrice);
    }
}
