package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import com.airbnb.android.core.models.User;
import com.airbnb.android.lib.fragments.UserProfileFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class UserProfileFragment$$Icepick<T extends UserProfileFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9560H = new Helper("com.airbnb.android.lib.fragments.UserProfileFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.isSelf = f9560H.getBoolean(state, "isSelf");
            target.hideProfilePhoto = f9560H.getBoolean(state, "hideProfilePhoto");
            target.userId = f9560H.getLong(state, "userId");
            target.user = (User) f9560H.getParcelable(state, "user");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9560H.putBoolean(state, "isSelf", target.isSelf);
        f9560H.putBoolean(state, "hideProfilePhoto", target.hideProfilePhoto);
        f9560H.putLong(state, "userId", target.userId);
        f9560H.putParcelable(state, "user", target.user);
    }
}
