package com.airbnb.android.react;

import com.squareup.otto.Bus;

final /* synthetic */ class AccountModule$$Lambda$1 implements Runnable {
    private final AccountModule arg$1;
    private final Bus arg$2;

    private AccountModule$$Lambda$1(AccountModule accountModule, Bus bus) {
        this.arg$1 = accountModule;
        this.arg$2 = bus;
    }

    public static Runnable lambdaFactory$(AccountModule accountModule, Bus bus) {
        return new AccountModule$$Lambda$1(accountModule, bus);
    }

    public void run() {
        this.arg$2.register(this.arg$1);
    }
}
