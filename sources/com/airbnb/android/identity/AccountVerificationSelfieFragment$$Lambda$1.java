package com.airbnb.android.identity;

final /* synthetic */ class AccountVerificationSelfieFragment$$Lambda$1 implements Runnable {
    private final AccountVerificationSelfieFragment arg$1;

    private AccountVerificationSelfieFragment$$Lambda$1(AccountVerificationSelfieFragment accountVerificationSelfieFragment) {
        this.arg$1 = accountVerificationSelfieFragment;
    }

    public static Runnable lambdaFactory$(AccountVerificationSelfieFragment accountVerificationSelfieFragment) {
        return new AccountVerificationSelfieFragment$$Lambda$1(accountVerificationSelfieFragment);
    }

    public void run() {
        this.arg$1.onSetFinished();
    }
}
