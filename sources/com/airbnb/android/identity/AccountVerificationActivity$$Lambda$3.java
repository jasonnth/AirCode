package com.airbnb.android.identity;

final /* synthetic */ class AccountVerificationActivity$$Lambda$3 implements Runnable {
    private final AccountVerificationActivity arg$1;

    private AccountVerificationActivity$$Lambda$3(AccountVerificationActivity accountVerificationActivity) {
        this.arg$1 = accountVerificationActivity;
    }

    public static Runnable lambdaFactory$(AccountVerificationActivity accountVerificationActivity) {
        return new AccountVerificationActivity$$Lambda$3(accountVerificationActivity);
    }

    public void run() {
        this.arg$1.showFragmentForCurrentStep();
    }
}
