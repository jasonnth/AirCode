package com.airbnb.android.identity;

import android.os.Bundle;
import com.airbnb.android.identity.AccountVerificationMiSnapIDCaptureFragment;
import com.braintreepayments.api.models.PostalAddress;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class AccountVerificationMiSnapIDCaptureFragment$$Icepick<T extends AccountVerificationMiSnapIDCaptureFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9166H = new Helper("com.airbnb.android.identity.AccountVerificationMiSnapIDCaptureFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.frontPhotoPath = f9166H.getString(state, "frontPhotoPath");
            target.backPhotoPath = f9166H.getString(state, "backPhotoPath");
            target.capturedBarcode = f9166H.getString(state, "capturedBarcode");
            target.countryCode = f9166H.getString(state, PostalAddress.COUNTRY_CODE_KEY);
            target.governmentIdType = (GovernmentIdType) f9166H.getSerializable(state, "governmentIdType");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9166H.putString(state, "frontPhotoPath", target.frontPhotoPath);
        f9166H.putString(state, "backPhotoPath", target.backPhotoPath);
        f9166H.putString(state, "capturedBarcode", target.capturedBarcode);
        f9166H.putString(state, PostalAddress.COUNTRY_CODE_KEY, target.countryCode);
        f9166H.putSerializable(state, "governmentIdType", target.governmentIdType);
    }
}
