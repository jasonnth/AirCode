package com.airbnb.android.lib.fragments.inbox.saved_messages;

import android.os.Bundle;
import com.airbnb.android.core.messaging.p007db.ThreadDataModel;
import com.airbnb.android.lib.fragments.inbox.saved_messages.SavedMessagesFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class SavedMessagesFragment$$Icepick<T extends SavedMessagesFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9576H = new Helper("com.airbnb.android.lib.fragments.inbox.saved_messages.SavedMessagesFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.refreshOnResume = f9576H.getBoolean(state, "refreshOnResume");
            target.isEditMode = f9576H.getBoolean(state, "isEditMode");
            target.messages = f9576H.getParcelableArrayList(state, "messages");
            target.listingId = f9576H.getLong(state, "listingId");
            target.threadId = f9576H.getLong(state, ThreadDataModel.THREADID);
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9576H.putBoolean(state, "refreshOnResume", target.refreshOnResume);
        f9576H.putBoolean(state, "isEditMode", target.isEditMode);
        f9576H.putParcelableArrayList(state, "messages", target.messages);
        f9576H.putLong(state, "listingId", target.listingId);
        f9576H.putLong(state, ThreadDataModel.THREADID, target.threadId);
    }
}
