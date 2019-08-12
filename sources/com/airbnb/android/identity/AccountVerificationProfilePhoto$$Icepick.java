package com.airbnb.android.identity;

import android.os.Bundle;
import android.os.Parcelable;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.identity.AccountVerificationProfilePhoto;
import com.airbnb.android.identity.AccountVerificationProfilePhoto.ProfilePhotoState;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.View;
import java.util.HashMap;
import java.util.Map;

public class AccountVerificationProfilePhoto$$Icepick<T extends AccountVerificationProfilePhoto> extends View<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9172H = new Helper("com.airbnb.android.identity.AccountVerificationProfilePhoto$$Icepick.", BUNDLERS);

    public Parcelable restore(T target, Parcelable p) {
        Bundle state = (Bundle) p;
        target.profilePhotoState = (ProfilePhotoState) f9172H.getSerializable(state, "profilePhotoState");
        target.profilePhotoFilePath = f9172H.getString(state, "profilePhotoFilePath");
        target.profilePhotoUrl = f9172H.getString(state, "profilePhotoUrl");
        target.verificationFlow = (VerificationFlow) f9172H.getSerializable(state, "verificationFlow");
        target.photoSelectionLayoutVisibility = f9172H.getInt(state, "photoSelectionLayoutVisibility");
        return super.restore(target, f9172H.getParent(state));
    }

    public Parcelable save(T target, Parcelable p) {
        Bundle state = f9172H.putParent(super.save(target, p));
        f9172H.putSerializable(state, "profilePhotoState", target.profilePhotoState);
        f9172H.putString(state, "profilePhotoFilePath", target.profilePhotoFilePath);
        f9172H.putString(state, "profilePhotoUrl", target.profilePhotoUrl);
        f9172H.putSerializable(state, "verificationFlow", target.verificationFlow);
        f9172H.putInt(state, "photoSelectionLayoutVisibility", target.photoSelectionLayoutVisibility);
        return state;
    }
}
