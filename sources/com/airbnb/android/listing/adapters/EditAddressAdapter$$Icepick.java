package com.airbnb.android.listing.adapters;

import android.os.Bundle;
import com.airbnb.android.core.models.AirAddress;
import com.airbnb.android.listing.adapters.EditAddressAdapter;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class EditAddressAdapter$$Icepick<T extends EditAddressAdapter> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9787H = new Helper("com.airbnb.android.listing.adapters.EditAddressAdapter$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.address = (AirAddress) f9787H.getParcelable(state, "address");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9787H.putParcelable(state, "address", target.address);
    }
}
