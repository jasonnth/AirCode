package com.airbnb.android.sharing.enums;

import android.content.Context;
import android.content.pm.ResolveInfo;
import java.util.Comparator;

final /* synthetic */ class CustomShareActivities$$Lambda$1 implements Comparator {
    private final Context arg$1;

    private CustomShareActivities$$Lambda$1(Context context) {
        this.arg$1 = context;
    }

    public static Comparator lambdaFactory$(Context context) {
        return new CustomShareActivities$$Lambda$1(context);
    }

    public int compare(Object obj, Object obj2) {
        return CustomShareActivities.lambda$getComparator$0(this.arg$1, (ResolveInfo) obj, (ResolveInfo) obj2);
    }
}
