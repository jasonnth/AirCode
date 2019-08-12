package com.airbnb.android.core.identity;

import android.os.Bundle;
import com.airbnb.android.core.identity.FetchIdentityController;
import com.airbnb.android.core.models.GovernmentIdResult;
import com.airbnb.android.core.models.User;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class FetchIdentityController$$Icepick<T extends FetchIdentityController> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8459H = new Helper("com.airbnb.android.core.identity.FetchIdentityController$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.requiresIdentityTreatment = f8459H.getBoolean(state, "requiresIdentityTreatment");
            target.verificationUser = (User) f8459H.getParcelable(state, "verificationUser");
            target.governmentIdResult = (GovernmentIdResult) f8459H.getParcelable(state, "governmentIdResult");
            target.verificationStates = (HashMap) f8459H.getSerializable(state, "verificationStates");
            target.incompleteVerifications = (HashMap) f8459H.getSerializable(state, "incompleteVerifications");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8459H.putBoolean(state, "requiresIdentityTreatment", target.requiresIdentityTreatment);
        f8459H.putParcelable(state, "verificationUser", target.verificationUser);
        f8459H.putParcelable(state, "governmentIdResult", target.governmentIdResult);
        f8459H.putSerializable(state, "verificationStates", target.verificationStates);
        f8459H.putSerializable(state, "incompleteVerifications", target.incompleteVerifications);
    }
}
