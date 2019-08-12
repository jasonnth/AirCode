package com.airbnb.android.core.utils.listing;

import android.content.Context;
import com.airbnb.android.core.models.BedType;
import com.google.common.base.Function;

final /* synthetic */ class BedDetailsDisplay$$Lambda$2 implements Function {
    private final Context arg$1;

    private BedDetailsDisplay$$Lambda$2(Context context) {
        this.arg$1 = context;
    }

    public static Function lambdaFactory$(Context context) {
        return new BedDetailsDisplay$$Lambda$2(context);
    }

    public Object apply(Object obj) {
        return this.arg$1.getResources().getQuantityString(((BedType) obj).getType().pluralsRes, ((BedType) obj).getQuantity(), new Object[]{Integer.valueOf(((BedType) obj).getQuantity())});
    }
}
