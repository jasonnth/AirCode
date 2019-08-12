package com.airbnb.android.core.utils;

import android.content.Context;
import p032rx.functions.Func1;

final /* synthetic */ class PhoneUtil$$Lambda$1 implements Func1 {
    private final Context arg$1;
    private final String arg$2;

    private PhoneUtil$$Lambda$1(Context context, String str) {
        this.arg$1 = context;
        this.arg$2 = str;
    }

    public static Func1 lambdaFactory$(Context context, String str) {
        return new PhoneUtil$$Lambda$1(context, str);
    }

    public Object call(Object obj) {
        return PhoneUtil.actionTitle(this.arg$1, ((Integer) obj).intValue(), this.arg$2);
    }
}
