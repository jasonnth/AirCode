package com.airbnb.android.identity;

import android.os.Bundle;
import com.airbnb.android.core.models.User;
import com.airbnb.android.identity.AccountVerificationNonBookingActivity;
import com.airbnb.android.identity.AccountVerificationNonBookingActivity.EntryPoint;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class AccountVerificationNonBookingActivity$$Icepick<T extends AccountVerificationNonBookingActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9167H = new Helper("com.airbnb.android.identity.AccountVerificationNonBookingActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.incompleteVerifications = f9167H.getParcelableArrayList(state, "incompleteVerifications");
            target.verificationUser = (User) f9167H.getParcelable(state, "verificationUser");
            target.entryPoint = (EntryPoint) f9167H.getSerializable(state, "entryPoint");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9167H.putParcelableArrayList(state, "incompleteVerifications", target.incompleteVerifications);
        f9167H.putParcelable(state, "verificationUser", target.verificationUser);
        f9167H.putSerializable(state, "entryPoint", target.entryPoint);
    }
}
