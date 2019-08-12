package com.airbnb.android.identity;

import android.os.Bundle;
import com.airbnb.android.identity.AccountVerificationSelfieConfirmFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class AccountVerificationSelfieConfirmFragment$$Icepick<T extends AccountVerificationSelfieConfirmFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9173H = new Helper("com.airbnb.android.identity.AccountVerificationSelfieConfirmFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.selfiePhotoFilePaths = f9173H.getStringArrayList(state, "selfiePhotoFilePaths");
            target.uploadStartTime = f9173H.getLong(state, "uploadStartTime");
            target.hasClickedConfirm = f9173H.getBoolean(state, "hasClickedConfirm");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9173H.putStringArrayList(state, "selfiePhotoFilePaths", target.selfiePhotoFilePaths);
        f9173H.putLong(state, "uploadStartTime", target.uploadStartTime);
        f9173H.putBoolean(state, "hasClickedConfirm", target.hasClickedConfirm);
    }
}
