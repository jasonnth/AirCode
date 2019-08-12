package com.airbnb.android.identity;

final /* synthetic */ class AccountVerificationEmailConfirmationFragment$$Lambda$5 implements Runnable {
    private final AccountVerificationEmailConfirmationFragment arg$1;

    private AccountVerificationEmailConfirmationFragment$$Lambda$5(AccountVerificationEmailConfirmationFragment accountVerificationEmailConfirmationFragment) {
        this.arg$1 = accountVerificationEmailConfirmationFragment;
    }

    public static Runnable lambdaFactory$(AccountVerificationEmailConfirmationFragment accountVerificationEmailConfirmationFragment) {
        return new AccountVerificationEmailConfirmationFragment$$Lambda$5(accountVerificationEmailConfirmationFragment);
    }

    public void run() {
        this.arg$1.startPolling();
    }
}
