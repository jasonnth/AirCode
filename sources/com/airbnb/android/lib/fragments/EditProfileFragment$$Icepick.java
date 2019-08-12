package com.airbnb.android.lib.fragments;

import android.net.Uri;
import android.os.Bundle;
import com.airbnb.android.core.interfaces.EditProfileInterface.ProfileSection;
import com.airbnb.android.lib.fragments.EditProfileFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class EditProfileFragment$$Icepick<T extends EditProfileFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9531H = new Helper("com.airbnb.android.lib.fragments.EditProfileFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.currentRequestState = f9531H.getInt(state, "currentRequestState");
            target.currentSection = (ProfileSection) f9531H.getParcelable(state, "currentSection");
            target.updatedValue = f9531H.getString(state, "updatedValue");
            target.croppedPhotoUri = (Uri) f9531H.getParcelable(state, "croppedPhotoUri");
            target.scrollValue = f9531H.getInt(state, "scrollValue");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9531H.putInt(state, "currentRequestState", target.currentRequestState);
        f9531H.putParcelable(state, "currentSection", target.currentSection);
        f9531H.putString(state, "updatedValue", target.updatedValue);
        f9531H.putParcelable(state, "croppedPhotoUri", target.croppedPhotoUri);
        f9531H.putInt(state, "scrollValue", target.scrollValue);
    }
}
