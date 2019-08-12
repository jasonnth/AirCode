package com.airbnb.android.booking.fragments;

import android.os.Bundle;
import com.airbnb.android.booking.fragments.BookingEditTextFragment;
import com.airbnb.android.booking.fragments.BookingEditTextFragment.Type;
import com.airbnb.android.core.models.User;
import icepick.Bundler;
import icepick.Injector.Helper;
import java.util.HashMap;
import java.util.Map;

public class BookingEditTextFragment$$Icepick<T extends BookingEditTextFragment> extends BookingV2BaseFragment$$Icepick<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7428H = new Helper("com.airbnb.android.booking.fragments.BookingEditTextFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.user = (User) f7428H.getParcelable(state, "user");
            target.message = f7428H.getString(state, "message");
            target.type = (Type) f7428H.getSerializable(state, "type");
            target.isKeyboardUp = f7428H.getBoolean(state, "isKeyboardUp");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7428H.putParcelable(state, "user", target.user);
        f7428H.putString(state, "message", target.message);
        f7428H.putSerializable(state, "type", target.type);
        f7428H.putBoolean(state, "isKeyboardUp", target.isKeyboardUp);
    }
}
