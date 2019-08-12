package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.interfaces.GuestIdentity.Type;
import com.airbnb.android.lib.fragments.CreateGuestIdentityFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CreateGuestIdentityFragment$$Icepick<T extends CreateGuestIdentityFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9526H = new Helper("com.airbnb.android.lib.fragments.CreateGuestIdentityFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.dateOfExpiry = (AirDate) f9526H.getParcelable(state, "dateOfExpiry");
            target.nationalityCountryCode = f9526H.getString(state, "nationalityCountryCode");
            target.nationalityCountryString = f9526H.getString(state, "nationalityCountryString");
            target.identityType = (Type) f9526H.getParcelable(state, "identityType");
            target.doneButtonEnabled = f9526H.getBoolean(state, "doneButtonEnabled");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9526H.putParcelable(state, "dateOfExpiry", target.dateOfExpiry);
        f9526H.putString(state, "nationalityCountryCode", target.nationalityCountryCode);
        f9526H.putString(state, "nationalityCountryString", target.nationalityCountryString);
        f9526H.putParcelable(state, "identityType", target.identityType);
        f9526H.putBoolean(state, "doneButtonEnabled", target.doneButtonEnabled);
    }
}
