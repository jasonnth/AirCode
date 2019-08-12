package com.airbnb.android.lib.adapters.viewholders;

import android.content.Context;
import com.airbnb.android.core.models.DashboardAlert;
import com.airbnb.android.lib.adapters.viewholders.AlertViewModelFactory.AlertClickListener;
import com.google.common.base.Function;

final /* synthetic */ class AlertViewModelFactory$$Lambda$2 implements Function {
    private final Context arg$1;
    private final AlertClickListener arg$2;

    private AlertViewModelFactory$$Lambda$2(Context context, AlertClickListener alertClickListener) {
        this.arg$1 = context;
        this.arg$2 = alertClickListener;
    }

    public static Function lambdaFactory$(Context context, AlertClickListener alertClickListener) {
        return new AlertViewModelFactory$$Lambda$2(context, alertClickListener);
    }

    public Object apply(Object obj) {
        return AlertViewModelFactory.createAlert(this.arg$1, (DashboardAlert) obj, this.arg$2);
    }
}
