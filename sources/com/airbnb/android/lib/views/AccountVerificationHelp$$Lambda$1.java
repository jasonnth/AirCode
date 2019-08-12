package com.airbnb.android.lib.views;

import android.content.Context;
import com.airbnb.p027n2.interfaces.LinkOnClickListener;

final /* synthetic */ class AccountVerificationHelp$$Lambda$1 implements LinkOnClickListener {
    private final String arg$1;
    private final Context arg$2;

    private AccountVerificationHelp$$Lambda$1(String str, Context context) {
        this.arg$1 = str;
        this.arg$2 = context;
    }

    public static LinkOnClickListener lambdaFactory$(String str, Context context) {
        return new AccountVerificationHelp$$Lambda$1(str, context);
    }

    public void onClickLink(int i) {
        AccountVerificationHelp.lambda$init$0(this.arg$1, this.arg$2, i);
    }
}
