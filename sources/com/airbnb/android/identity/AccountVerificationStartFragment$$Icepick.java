package com.airbnb.android.identity;

import android.os.Bundle;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.models.GovernmentIdResult;
import com.airbnb.android.core.models.TripRole;
import com.airbnb.android.core.models.User;
import com.airbnb.android.identity.AccountVerificationStartFragment;
import com.airbnb.android.lib.analytics.ManageListingAnalytics;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class AccountVerificationStartFragment$$Icepick<T extends AccountVerificationStartFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9176H = new Helper("com.airbnb.android.identity.AccountVerificationStartFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.requiredSteps = f9176H.getParcelableArrayList(state, "requiredSteps");
            target.listingId = f9176H.getLong(state, "listingId");
            target.host = (User) f9176H.getParcelable(state, TripRole.ROLE_KEY_HOST);
            target.verificationUser = (User) f9176H.getParcelable(state, "verificationUser");
            target.flow = (VerificationFlow) f9176H.getSerializable(state, ManageListingAnalytics.FLOW);
            target.governmentIdResult = (GovernmentIdResult) f9176H.getParcelable(state, "governmentIdResult");
            target.useIdentityFlow = f9176H.getBoolean(state, "useIdentityFlow");
            target.isMoveToLast = f9176H.getBoolean(state, "isMoveToLast");
            target.p4Steps = f9176H.getString(state, "p4Steps");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9176H.putParcelableArrayList(state, "requiredSteps", target.requiredSteps);
        f9176H.putLong(state, "listingId", target.listingId);
        f9176H.putParcelable(state, TripRole.ROLE_KEY_HOST, target.host);
        f9176H.putParcelable(state, "verificationUser", target.verificationUser);
        f9176H.putSerializable(state, ManageListingAnalytics.FLOW, target.flow);
        f9176H.putParcelable(state, "governmentIdResult", target.governmentIdResult);
        f9176H.putBoolean(state, "useIdentityFlow", target.useIdentityFlow);
        f9176H.putBoolean(state, "isMoveToLast", target.isMoveToLast);
        f9176H.putString(state, "p4Steps", target.p4Steps);
    }
}
