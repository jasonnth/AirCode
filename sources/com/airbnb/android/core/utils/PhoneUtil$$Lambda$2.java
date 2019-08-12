package com.airbnb.android.core.utils;

import android.content.Context;
import com.airbnb.android.core.views.OptionsMenuFactory.Listener;

final /* synthetic */ class PhoneUtil$$Lambda$2 implements Listener {
    private final Context arg$1;
    private final String arg$2;

    private PhoneUtil$$Lambda$2(Context context, String str) {
        this.arg$1 = context;
        this.arg$2 = str;
    }

    public static Listener lambdaFactory$(Context context, String str) {
        return new PhoneUtil$$Lambda$2(context, str);
    }

    public void itemSelected(Object obj) {
        PhoneUtil.actionSelected(this.arg$1, ((Integer) obj).intValue(), this.arg$2);
    }
}
