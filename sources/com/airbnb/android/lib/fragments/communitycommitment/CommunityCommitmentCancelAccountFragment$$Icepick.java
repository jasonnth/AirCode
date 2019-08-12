package com.airbnb.android.lib.fragments.communitycommitment;

import android.os.Bundle;
import com.airbnb.android.lib.fragments.communitycommitment.CommunityCommitmentCancelAccountFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CommunityCommitmentCancelAccountFragment$$Icepick<T extends CommunityCommitmentCancelAccountFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9562H = new Helper("com.airbnb.android.lib.fragments.communitycommitment.CommunityCommitmentCancelAccountFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.hasLoggedToBottom = f9562H.getBoolean(state, "hasLoggedToBottom");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9562H.putBoolean(state, "hasLoggedToBottom", target.hasLoggedToBottom);
    }
}
