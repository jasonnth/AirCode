package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.DashboardAlert;

final /* synthetic */ class AlertClickListeners$$Lambda$1 implements OnClickListener {
    private final DashboardAlert arg$1;
    private final boolean arg$2;

    private AlertClickListeners$$Lambda$1(DashboardAlert dashboardAlert, boolean z) {
        this.arg$1 = dashboardAlert;
        this.arg$2 = z;
    }

    public static OnClickListener lambdaFactory$(DashboardAlert dashboardAlert, boolean z) {
        return new AlertClickListeners$$Lambda$1(dashboardAlert, z);
    }

    public void onClick(View view) {
        AlertClickListeners.handleAlert(view.getContext(), this.arg$1, this.arg$2);
    }
}
