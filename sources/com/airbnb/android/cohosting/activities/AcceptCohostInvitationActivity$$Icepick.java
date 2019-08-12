package com.airbnb.android.cohosting.activities;

import android.os.Bundle;
import com.airbnb.android.cohosting.activities.AcceptCohostInvitationActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class AcceptCohostInvitationActivity$$Icepick<T extends AcceptCohostInvitationActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7785H = new Helper("com.airbnb.android.cohosting.activities.AcceptCohostInvitationActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.invitationId = f7785H.getLong(state, "invitationId");
            target.invitationCode = f7785H.getString(state, "invitationCode");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7785H.putLong(state, "invitationId", target.invitationId);
        f7785H.putString(state, "invitationCode", target.invitationCode);
    }
}
