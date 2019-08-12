package com.airbnb.android.cohosting.utils;

import android.content.Context;
import com.airbnb.p027n2.interfaces.LinkOnClickListener;

final /* synthetic */ class CohostingUtil$$Lambda$1 implements LinkOnClickListener {
    private final Context arg$1;

    private CohostingUtil$$Lambda$1(Context context) {
        this.arg$1 = context;
    }

    public static LinkOnClickListener lambdaFactory$(Context context) {
        return new CohostingUtil$$Lambda$1(context);
    }

    public void onClickLink(int i) {
        CohostingUtil.openTermsOfService(this.arg$1);
    }
}
