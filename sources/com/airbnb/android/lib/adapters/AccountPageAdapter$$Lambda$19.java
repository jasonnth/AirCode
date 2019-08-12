package com.airbnb.android.lib.adapters;

final /* synthetic */ class AccountPageAdapter$$Lambda$19 implements Runnable {
    private final AccountPageAdapter arg$1;

    private AccountPageAdapter$$Lambda$19(AccountPageAdapter accountPageAdapter) {
        this.arg$1 = accountPageAdapter;
    }

    public static Runnable lambdaFactory$(AccountPageAdapter accountPageAdapter) {
        return new AccountPageAdapter$$Lambda$19(accountPageAdapter);
    }

    public void run() {
        AccountPageAdapter.lambda$updateProfileCompletionModel$18(this.arg$1);
    }
}
