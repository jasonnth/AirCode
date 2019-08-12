package com.airbnb.android.login.utils;

import android.content.Context;
import com.airbnb.p027n2.interfaces.LinkOnClickListener;
import java.util.ArrayList;

final /* synthetic */ class LoginUtils$$Lambda$1 implements LinkOnClickListener {
    private final ArrayList arg$1;
    private final Context arg$2;

    private LoginUtils$$Lambda$1(ArrayList arrayList, Context context) {
        this.arg$1 = arrayList;
        this.arg$2 = context;
    }

    public static LinkOnClickListener lambdaFactory$(ArrayList arrayList, Context context) {
        return new LoginUtils$$Lambda$1(arrayList, context);
    }

    public void onClickLink(int i) {
        LoginUtils.lambda$setupTOSText$0(this.arg$1, this.arg$2, i);
    }
}
