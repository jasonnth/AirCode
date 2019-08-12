package com.airbnb.android.lib.tripassistant;

import android.os.Bundle;
import com.airbnb.android.core.intents.ThreadBlockActivityIntents;
import com.airbnb.android.core.models.HelpThread;
import com.airbnb.android.lib.tripassistant.HelpThreadFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class HelpThreadFragment$$Icepick<T extends HelpThreadFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9664H = new Helper("com.airbnb.android.lib.tripassistant.HelpThreadFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.thread = (HelpThread) f9664H.getParcelable(state, ThreadBlockActivityIntents.ARG_THREAD);
            target.savedSelection = (NodeSelection) f9664H.getParcelable(state, "savedSelection");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9664H.putParcelable(state, ThreadBlockActivityIntents.ARG_THREAD, target.thread);
        f9664H.putParcelable(state, "savedSelection", target.savedSelection);
    }
}
