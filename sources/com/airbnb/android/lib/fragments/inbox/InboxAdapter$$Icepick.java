package com.airbnb.android.lib.fragments.inbox;

import android.os.Bundle;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.lib.fragments.inbox.InboxAdapter;
import com.airbnb.android.superhero.SuperHeroMessage;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class InboxAdapter$$Icepick<T extends InboxAdapter> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9572H = new Helper("com.airbnb.android.lib.fragments.inbox.InboxAdapter$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.lastInsertedThread = (Thread) f9572H.getParcelable(state, "lastInsertedThread");
            target.superHeroMessageToPreview = (SuperHeroMessage) f9572H.getParcelable(state, "superHeroMessageToPreview");
            target.savedThreads = f9572H.getParcelableArrayList(state, "savedThreads");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9572H.putParcelable(state, "lastInsertedThread", target.lastInsertedThread);
        f9572H.putParcelable(state, "superHeroMessageToPreview", target.superHeroMessageToPreview);
        f9572H.putParcelableArrayList(state, "savedThreads", target.savedThreads);
    }
}
