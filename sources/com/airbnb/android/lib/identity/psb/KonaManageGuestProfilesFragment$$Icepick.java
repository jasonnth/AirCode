package com.airbnb.android.lib.identity.psb;

import android.os.Bundle;
import com.airbnb.android.core.interfaces.GuestIdentity;
import com.airbnb.android.lib.identity.psb.KonaManageGuestProfilesFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class KonaManageGuestProfilesFragment$$Icepick<T extends KonaManageGuestProfilesFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9600H = new Helper("com.airbnb.android.lib.identity.psb.KonaManageGuestProfilesFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.totalGuestCount = f9600H.getInt(state, "totalGuestCount");
            target.identityToRemove = (GuestIdentity) f9600H.getParcelable(state, "identityToRemove");
            target.guestIdentifications = f9600H.getParcelableArrayList(state, "guestIdentifications");
            target.isP4Redesign = f9600H.getBoolean(state, "isP4Redesign");
            target.p4Steps = f9600H.getString(state, "p4Steps");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9600H.putInt(state, "totalGuestCount", target.totalGuestCount);
        f9600H.putParcelable(state, "identityToRemove", target.identityToRemove);
        f9600H.putParcelableArrayList(state, "guestIdentifications", target.guestIdentifications);
        f9600H.putBoolean(state, "isP4Redesign", target.isP4Redesign);
        f9600H.putString(state, "p4Steps", target.p4Steps);
    }
}
