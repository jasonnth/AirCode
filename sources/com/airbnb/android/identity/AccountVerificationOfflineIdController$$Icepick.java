package com.airbnb.android.identity;

import android.os.Bundle;
import com.airbnb.android.core.models.JumioCredential;
import com.airbnb.android.identity.AccountVerificationOfflineIdController;
import com.braintreepayments.api.models.PostalAddress;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AccountVerificationOfflineIdController$$Icepick<T extends AccountVerificationOfflineIdController> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9168H = new Helper("com.airbnb.android.identity.AccountVerificationOfflineIdController$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.jumioCredential = (JumioCredential) f9168H.getParcelable(state, "jumioCredential");
            target.pendingStartIdScanForDocumentType = f9168H.getBoolean(state, "pendingStartIdScanForDocumentType");
            target.scanReference = f9168H.getString(state, "scanReference");
            target.governmentIdType = (GovernmentIdType) f9168H.getSerializable(state, "governmentIdType");
            target.misnapCapturedPhoto = (File) f9168H.getSerializable(state, "misnapCapturedPhoto");
            target.misnapCapturedBackPhoto = (File) f9168H.getSerializable(state, "misnapCapturedBackPhoto");
            target.misnapCapturedBarCode = f9168H.getString(state, "misnapCapturedBarCode");
            target.countryCode = f9168H.getString(state, PostalAddress.COUNTRY_CODE_KEY);
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9168H.putParcelable(state, "jumioCredential", target.jumioCredential);
        f9168H.putBoolean(state, "pendingStartIdScanForDocumentType", target.pendingStartIdScanForDocumentType);
        f9168H.putString(state, "scanReference", target.scanReference);
        f9168H.putSerializable(state, "governmentIdType", target.governmentIdType);
        f9168H.putSerializable(state, "misnapCapturedPhoto", target.misnapCapturedPhoto);
        f9168H.putSerializable(state, "misnapCapturedBackPhoto", target.misnapCapturedBackPhoto);
        f9168H.putString(state, "misnapCapturedBarCode", target.misnapCapturedBarCode);
        f9168H.putString(state, PostalAddress.COUNTRY_CODE_KEY, target.countryCode);
    }
}
