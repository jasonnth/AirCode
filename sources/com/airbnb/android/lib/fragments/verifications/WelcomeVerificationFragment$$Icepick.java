package com.airbnb.android.lib.fragments.verifications;

import android.os.Bundle;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.lib.fragments.verifications.WelcomeVerificationFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class WelcomeVerificationFragment$$Icepick<T extends WelcomeVerificationFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9590H = new Helper("com.airbnb.android.lib.fragments.verifications.WelcomeVerificationFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.verificationFlow = (VerificationFlow) f9590H.getSerializable(state, "verificationFlow");
            target.listing = (Listing) f9590H.getParcelable(state, "listing");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9590H.putSerializable(state, "verificationFlow", target.verificationFlow);
        f9590H.putParcelable(state, "listing", target.listing);
    }
}
