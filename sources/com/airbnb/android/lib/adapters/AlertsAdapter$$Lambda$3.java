package com.airbnb.android.lib.adapters;

import com.airbnb.android.core.models.DashboardAlert;
import com.airbnb.android.lib.adapters.viewholders.AlertViewModelFactory.AlertClickListener;
import com.airbnb.android.lib.viewcomponents.viewmodels.ThreadPreviewEpoxyModel_;

final /* synthetic */ class AlertsAdapter$$Lambda$3 implements AlertClickListener {
    private final AlertsAdapter arg$1;

    private AlertsAdapter$$Lambda$3(AlertsAdapter alertsAdapter) {
        this.arg$1 = alertsAdapter;
    }

    public static AlertClickListener lambdaFactory$(AlertsAdapter alertsAdapter) {
        return new AlertsAdapter$$Lambda$3(alertsAdapter);
    }

    public void handleClick(ThreadPreviewEpoxyModel_ threadPreviewEpoxyModel_, DashboardAlert dashboardAlert) {
        this.arg$1.handleAlertClick(threadPreviewEpoxyModel_, dashboardAlert);
    }
}
