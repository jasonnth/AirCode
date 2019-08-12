package com.airbnb.android.lib.adapters.viewholders;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.DashboardAlert;
import com.airbnb.android.lib.adapters.viewholders.AlertViewModelFactory.AlertClickListener;
import com.airbnb.android.lib.viewcomponents.viewmodels.ThreadPreviewEpoxyModel_;

final /* synthetic */ class AlertViewModelFactory$$Lambda$3 implements OnClickListener {
    private final AlertClickListener arg$1;
    private final ThreadPreviewEpoxyModel_ arg$2;
    private final DashboardAlert arg$3;

    private AlertViewModelFactory$$Lambda$3(AlertClickListener alertClickListener, ThreadPreviewEpoxyModel_ threadPreviewEpoxyModel_, DashboardAlert dashboardAlert) {
        this.arg$1 = alertClickListener;
        this.arg$2 = threadPreviewEpoxyModel_;
        this.arg$3 = dashboardAlert;
    }

    public static OnClickListener lambdaFactory$(AlertClickListener alertClickListener, ThreadPreviewEpoxyModel_ threadPreviewEpoxyModel_, DashboardAlert dashboardAlert) {
        return new AlertViewModelFactory$$Lambda$3(alertClickListener, threadPreviewEpoxyModel_, dashboardAlert);
    }

    public void onClick(View view) {
        this.arg$1.handleClick(this.arg$2, this.arg$3);
    }
}
