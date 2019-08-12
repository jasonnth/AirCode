package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import com.airbnb.android.core.enums.ROLaunchSource;
import com.airbnb.android.core.intents.ThreadBlockActivityIntents;
import com.airbnb.android.core.messaging.p007db.ThreadDataModel;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.Post;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.lib.fragments.ThreadFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ThreadFragment$$Icepick<T extends ThreadFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9559H = new Helper("com.airbnb.android.lib.fragments.ThreadFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.inboxType = (InboxType) f9559H.getSerializable(state, "inboxType");
            target.threadId = f9559H.getLong(state, ThreadDataModel.THREADID);
            target.scrollToPostId = f9559H.getBoxedLong(state, "scrollToPostId");
            target.source = (ROLaunchSource) f9559H.getSerializable(state, "source");
            target.thread = (Thread) f9559H.getParcelable(state, ThreadBlockActivityIntents.ARG_THREAD);
            target.reloadOnResume = f9559H.getBoolean(state, "reloadOnResume");
            target.forceReloadOnResume = f9559H.getBoolean(state, "forceReloadOnResume");
            target.reservation = (Reservation) f9559H.getParcelable(state, "reservation");
            target.postToUnflag = (Post) f9559H.getParcelable(state, "postToUnflag");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9559H.putSerializable(state, "inboxType", target.inboxType);
        f9559H.putLong(state, ThreadDataModel.THREADID, target.threadId);
        f9559H.putBoxedLong(state, "scrollToPostId", target.scrollToPostId);
        f9559H.putSerializable(state, "source", target.source);
        f9559H.putParcelable(state, ThreadBlockActivityIntents.ARG_THREAD, target.thread);
        f9559H.putBoolean(state, "reloadOnResume", target.reloadOnResume);
        f9559H.putBoolean(state, "forceReloadOnResume", target.forceReloadOnResume);
        f9559H.putParcelable(state, "reservation", target.reservation);
        f9559H.putParcelable(state, "postToUnflag", target.postToUnflag);
    }
}
