package com.airbnb.android.identity;

import android.os.Bundle;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.identity.AccountVerificationStep;
import com.airbnb.android.core.models.TripRole;
import com.airbnb.android.core.models.User;
import com.airbnb.android.identity.AccountVerificationActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class AccountVerificationActivity$$Icepick<T extends AccountVerificationActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9160H = new Helper("com.airbnb.android.identity.AccountVerificationActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.requiredSteps = f9160H.getParcelableArrayList(state, "requiredSteps");
            target.currentStep = (AccountVerificationStep) f9160H.getParcelable(state, "currentStep");
            target.selfiePhotoFilePaths = f9160H.getStringArrayList(state, "selfiePhotoFilePaths");
            target.verificationFlow = (VerificationFlow) f9160H.getSerializable(state, "verificationFlow");
            target.verificationUser = (User) f9160H.getParcelable(state, "verificationUser");
            target.host = (User) f9160H.getParcelable(state, TripRole.ROLE_KEY_HOST);
            target.isMoveToLastStep = f9160H.getBoolean(state, "isMoveToLastStep");
            target.isModal = f9160H.getBoolean(state, "isModal");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9160H.putParcelableArrayList(state, "requiredSteps", target.requiredSteps);
        f9160H.putParcelable(state, "currentStep", target.currentStep);
        f9160H.putStringArrayList(state, "selfiePhotoFilePaths", target.selfiePhotoFilePaths);
        f9160H.putSerializable(state, "verificationFlow", target.verificationFlow);
        f9160H.putParcelable(state, "verificationUser", target.verificationUser);
        f9160H.putParcelable(state, TripRole.ROLE_KEY_HOST, target.host);
        f9160H.putBoolean(state, "isMoveToLastStep", target.isMoveToLastStep);
        f9160H.putBoolean(state, "isModal", target.isModal);
    }
}
