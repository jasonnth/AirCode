package com.airbnb.android.lib.fragments.inbox;

import android.os.Bundle;
import com.airbnb.android.lib.fragments.alerts.AlertsFragment;
import com.airbnb.android.lib.fragments.inbox.InboxFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class InboxFragment$$Icepick<T extends InboxFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9573H = new Helper("com.airbnb.android.lib.fragments.inbox.InboxFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.loadingInitialInbox = f9573H.getBoolean(state, "loadingInitialInbox");
            target.loadingAlerts = f9573H.getBoolean(state, "loadingAlerts");
            target.loadingSuperHero = f9573H.getBoolean(state, "loadingSuperHero");
            target.refreshOnResume = f9573H.getBoolean(state, "refreshOnResume");
            target.alerts = f9573H.getParcelableArrayList(state, AlertsFragment.RESULT_UPDATED_ALERTS);
            target.threadTagsToArchive = f9573H.getStringArrayList(state, "threadTagsToArchive");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9573H.putBoolean(state, "loadingInitialInbox", target.loadingInitialInbox);
        f9573H.putBoolean(state, "loadingAlerts", target.loadingAlerts);
        f9573H.putBoolean(state, "loadingSuperHero", target.loadingSuperHero);
        f9573H.putBoolean(state, "refreshOnResume", target.refreshOnResume);
        f9573H.putParcelableArrayList(state, AlertsFragment.RESULT_UPDATED_ALERTS, target.alerts);
        f9573H.putStringArrayList(state, "threadTagsToArchive", target.threadTagsToArchive);
    }
}
