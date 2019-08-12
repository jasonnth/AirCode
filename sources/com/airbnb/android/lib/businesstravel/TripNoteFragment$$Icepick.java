package com.airbnb.android.lib.businesstravel;

import android.os.Bundle;
import com.airbnb.android.lib.businesstravel.TripNoteFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class TripNoteFragment$$Icepick<T extends TripNoteFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9496H = new Helper("com.airbnb.android.lib.businesstravel.TripNoteFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.tripNote = f9496H.getString(state, "tripNote");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9496H.putString(state, "tripNote", target.tripNote);
    }
}
