package com.airbnb.android.identity;

import android.os.Bundle;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.intents.AccountVerificationStartActivityIntents;
import com.airbnb.android.core.models.User;
import com.airbnb.android.identity.AccountVerificationStartActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class AccountVerificationStartActivity$$Icepick<T extends AccountVerificationStartActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9175H = new Helper("com.airbnb.android.identity.AccountVerificationStartActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.verificationUser = (User) f9175H.getParcelable(state, "verificationUser");
            target.verificationFlow = (VerificationFlow) f9175H.getSerializable(state, "verificationFlow");
            target.verificationSteps = f9175H.getParcelableArrayList(state, "verificationSteps");
            target.useIdentityFlow = f9175H.getBoolean(state, "useIdentityFlow");
            target.bundle = f9175H.getBundle(state, AccountVerificationStartActivityIntents.EXTRA_BUNDLE);
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9175H.putParcelable(state, "verificationUser", target.verificationUser);
        f9175H.putSerializable(state, "verificationFlow", target.verificationFlow);
        f9175H.putParcelableArrayList(state, "verificationSteps", target.verificationSteps);
        f9175H.putBoolean(state, "useIdentityFlow", target.useIdentityFlow);
        f9175H.putBundle(state, AccountVerificationStartActivityIntents.EXTRA_BUNDLE, target.bundle);
    }
}
