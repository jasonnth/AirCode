package com.airbnb.android.identity;

import android.os.Bundle;
import com.airbnb.android.identity.IdentitySelfieReviewFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class IdentitySelfieReviewFragment$$Icepick<T extends IdentitySelfieReviewFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9180H = new Helper("com.airbnb.android.identity.IdentitySelfieReviewFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.selfiePhotoFilePaths = f9180H.getStringArrayList(state, "selfiePhotoFilePaths");
            target.uploadStartTime = f9180H.getLong(state, "uploadStartTime");
            target.uploadCount = f9180H.getInt(state, "uploadCount");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9180H.putStringArrayList(state, "selfiePhotoFilePaths", target.selfiePhotoFilePaths);
        f9180H.putLong(state, "uploadStartTime", target.uploadStartTime);
        f9180H.putInt(state, "uploadCount", target.uploadCount);
    }
}
