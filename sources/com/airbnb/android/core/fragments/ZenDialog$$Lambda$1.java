package com.airbnb.android.core.fragments;

import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.p027n2.interfaces.LinkOnClickListener;

final /* synthetic */ class ZenDialog$$Lambda$1 implements LinkOnClickListener {
    private final ZenDialog arg$1;
    private final String arg$2;

    private ZenDialog$$Lambda$1(ZenDialog zenDialog, String str) {
        this.arg$1 = zenDialog;
        this.arg$2 = str;
    }

    public static LinkOnClickListener lambdaFactory$(ZenDialog zenDialog, String str) {
        return new ZenDialog$$Lambda$1(zenDialog, str);
    }

    public void onClickLink(int i) {
        this.arg$1.startActivity(WebViewIntentBuilder.newBuilder(this.arg$1.getActivity(), this.arg$2).toIntent());
    }
}
