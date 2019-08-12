package com.airbnb.android.lib.fragments.verifications;

import android.net.Uri;
import android.os.Bundle;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.lib.fragments.verifications.PhotoVerificationFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class PhotoVerificationFragment$$Icepick<T extends PhotoVerificationFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9589H = new Helper("com.airbnb.android.lib.fragments.verifications.PhotoVerificationFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.croppedPhotoUri = (Uri) f9589H.getParcelable(state, "croppedPhotoUri");
            target.viewState = f9589H.getInt(state, "viewState");
            target.verificationFlow = (VerificationFlow) f9589H.getSerializable(state, "verificationFlow");
            target.listing = (Listing) f9589H.getParcelable(state, "listing");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9589H.putParcelable(state, "croppedPhotoUri", target.croppedPhotoUri);
        f9589H.putInt(state, "viewState", target.viewState);
        f9589H.putSerializable(state, "verificationFlow", target.verificationFlow);
        f9589H.putParcelable(state, "listing", target.listing);
    }
}
