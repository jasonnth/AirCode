package com.airbnb.android.lib.fragments.communitycommitment;

import android.os.Bundle;
import com.airbnb.android.lib.fragments.communitycommitment.CommunityCommitmentLearnMoreFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CommunityCommitmentLearnMoreFragment$$Icepick<T extends CommunityCommitmentLearnMoreFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9565H = new Helper("com.airbnb.android.lib.fragments.communitycommitment.CommunityCommitmentLearnMoreFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.hasScrolledToBottom = f9565H.getBoolean(state, "hasScrolledToBottom");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9565H.putBoolean(state, "hasScrolledToBottom", target.hasScrolledToBottom);
    }
}
