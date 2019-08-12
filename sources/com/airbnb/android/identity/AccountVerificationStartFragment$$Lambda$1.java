package com.airbnb.android.identity;

import com.airbnb.android.core.models.User;
import com.airbnb.p027n2.interfaces.LinkOnClickListener;

final /* synthetic */ class AccountVerificationStartFragment$$Lambda$1 implements LinkOnClickListener {
    private final AccountVerificationStartFragment arg$1;
    private final User arg$2;

    private AccountVerificationStartFragment$$Lambda$1(AccountVerificationStartFragment accountVerificationStartFragment, User user) {
        this.arg$1 = accountVerificationStartFragment;
        this.arg$2 = user;
    }

    public static LinkOnClickListener lambdaFactory$(AccountVerificationStartFragment accountVerificationStartFragment, User user) {
        return new AccountVerificationStartFragment$$Lambda$1(accountVerificationStartFragment, user);
    }

    public void onClickLink(int i) {
        AccountVerificationStartFragment.lambda$onCreateViewVerificationOneDotOne$0(this.arg$1, this.arg$2, i);
    }
}
