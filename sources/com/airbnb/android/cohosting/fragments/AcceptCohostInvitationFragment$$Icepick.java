package com.airbnb.android.cohosting.fragments;

import android.os.Bundle;
import com.airbnb.android.cohosting.fragments.AcceptCohostInvitationFragment;
import com.airbnb.android.core.intents.CohostingIntents;
import com.airbnb.android.core.models.CohostInvitation;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class AcceptCohostInvitationFragment$$Icepick<T extends AcceptCohostInvitationFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7800H = new Helper("com.airbnb.android.cohosting.fragments.AcceptCohostInvitationFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.invitation = (CohostInvitation) f7800H.getParcelable(state, CohostingIntents.INTENT_EXTRA_INVITATION);
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7800H.putParcelable(state, CohostingIntents.INTENT_EXTRA_INVITATION, target.invitation);
    }
}
