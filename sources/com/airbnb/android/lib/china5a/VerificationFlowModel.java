package com.airbnb.android.lib.china5a;

import com.airbnb.android.core.identity.AccountVerificationStep;
import p032rx.Observable;

public interface VerificationFlowModel {
    Observable<AccountVerificationStep> getCurrentStep();
}
