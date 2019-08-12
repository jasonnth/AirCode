package com.airbnb.android.lib.fragments.completeprofile;

import android.net.Uri;
import android.os.Bundle;
import com.airbnb.android.lib.fragments.completeprofile.CompleteProfilePhotoFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CompleteProfilePhotoFragment$$Icepick<T extends CompleteProfilePhotoFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9569H = new Helper("com.airbnb.android.lib.fragments.completeprofile.CompleteProfilePhotoFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.croppedPhotoUri = (Uri) f9569H.getParcelable(state, "croppedPhotoUri");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9569H.putParcelable(state, "croppedPhotoUri", target.croppedPhotoUri);
    }
}
