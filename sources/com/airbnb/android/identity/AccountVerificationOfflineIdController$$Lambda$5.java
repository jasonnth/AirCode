package com.airbnb.android.identity;

import com.airbnb.android.core.identity.AccountVerificationStep;
import java.util.Date;

final /* synthetic */ class AccountVerificationOfflineIdController$$Lambda$5 implements Runnable {
    private final AccountVerificationOfflineIdController arg$1;
    private final Date arg$2;

    private AccountVerificationOfflineIdController$$Lambda$5(AccountVerificationOfflineIdController accountVerificationOfflineIdController, Date date) {
        this.arg$1 = accountVerificationOfflineIdController;
        this.arg$2 = date;
    }

    public static Runnable lambdaFactory$(AccountVerificationOfflineIdController accountVerificationOfflineIdController, Date date) {
        return new AccountVerificationOfflineIdController$$Lambda$5(accountVerificationOfflineIdController, date);
    }

    public void run() {
        this.arg$1.controller.showFragmentForStep(AccountVerificationIdExpiredFragment.newInstance(this.arg$1.fragment.getActivity(), this.arg$2, this.arg$1.governmentIdType), AccountVerificationStep.OfflineId);
    }
}
