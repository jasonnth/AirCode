package com.airbnb.android.lib.adapters;

import android.content.Context;
import android.os.Bundle;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.DashboardAlert;
import com.airbnb.android.core.models.DashboardAlert.DashboardAlertType;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MicroRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.adapters.viewholders.AlertViewModelFactory;
import com.airbnb.android.lib.viewcomponents.viewmodels.ThreadPreviewEpoxyModel_;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.components.MicroRow;
import com.google.common.collect.FluentIterable;
import icepick.State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class AlertsAdapter extends AirEpoxyAdapter {
    @State
    ArrayList<DashboardAlert> alerts;
    private final Context context;
    private final EpoxyModel<MicroRow> emptyRowModel = new MicroRowEpoxyModel_().textRes(C0880R.string.no_alerts_education).showDivider(false).hide();
    private final InboxType inboxType;
    @State
    boolean isLoading;
    private final LoadingRowEpoxyModel_ loadingModel = new LoadingRowEpoxyModel_();
    private final DocumentMarqueeEpoxyModel_ marqueeModel = new DocumentMarqueeEpoxyModel_().titleRes(C0880R.string.alerts);

    public AlertsAdapter(Context context2, InboxType inboxType2, Bundle savedInstanceState) {
        this.context = context2;
        this.inboxType = inboxType2;
        onRestoreInstanceState(savedInstanceState);
        setLoading(this.isLoading);
        updateAllModels();
    }

    public void setAlerts(ArrayList<DashboardAlert> alerts2, boolean isEligibleForNestedListings, boolean isActiveHost) {
        boolean showNestedListingsAlert;
        ArrayList<DashboardAlert> alerts3;
        if (!ListUtils.isEmpty((Collection<?>) alerts2)) {
            Collections.sort(alerts2);
            boolean showListingExpectationAlert = Trebuchet.launch(TrebuchetKeys.LISTING_EXPECTATIONS_DASHBOARD_ALERT_DELIVERED) && isActiveHost && FeatureToggles.showListingExpectationsSettings();
            if (!Trebuchet.launch(TrebuchetKeys.NESTED_LISTINGS_DASHBOARD_ALERT_DELIVERED) || !FeatureToggles.isNestedListingEnabled(isEligibleForNestedListings)) {
                showNestedListingsAlert = false;
            } else {
                showNestedListingsAlert = true;
            }
            if (!showListingExpectationAlert) {
                alerts3 = new ArrayList<>(FluentIterable.from((Iterable<E>) alerts2).filter(AlertsAdapter$$Lambda$1.lambdaFactory$()).toList());
            } else {
                alerts3 = alerts2;
            }
            if (!showNestedListingsAlert) {
                alerts2 = new ArrayList<>(FluentIterable.from((Iterable<E>) alerts3).filter(AlertsAdapter$$Lambda$2.lambdaFactory$()).toList());
            } else {
                alerts2 = alerts3;
            }
        }
        this.alerts = alerts2;
        updateAllModels();
    }

    static /* synthetic */ boolean lambda$setAlerts$0(DashboardAlert alert) {
        return alert.getAlertTypeEnum() != DashboardAlertType.NewListingExpectations;
    }

    static /* synthetic */ boolean lambda$setAlerts$1(DashboardAlert alert) {
        return alert.getAlertTypeEnum() != DashboardAlertType.NewLinkedListings;
    }

    public ArrayList<DashboardAlert> getAlerts() {
        return this.alerts;
    }

    public void setLoading(boolean loading) {
        this.isLoading = loading;
        this.loadingModel.show(loading);
        updateAllModels();
    }

    private void updateAllModels() {
        boolean showEmpty;
        int i;
        this.models.clear();
        if (!ListUtils.isEmpty((Collection<?>) this.alerts) || this.loadingModel.isShown()) {
            showEmpty = false;
        } else {
            showEmpty = true;
        }
        DocumentMarqueeEpoxyModel_ documentMarqueeEpoxyModel_ = this.marqueeModel;
        if (showEmpty) {
            i = C0880R.string.no_alerts;
        } else {
            i = 0;
        }
        documentMarqueeEpoxyModel_.captionRes(i);
        this.emptyRowModel.show(showEmpty);
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.marqueeModel, this.emptyRowModel});
        addModels((Collection<? extends EpoxyModel<?>>) AlertViewModelFactory.createAlerts(this.context, this.alerts, AlertsAdapter$$Lambda$3.lambdaFactory$(this)));
        addModel(this.loadingModel);
        notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    public void handleAlertClick(ThreadPreviewEpoxyModel_ model, DashboardAlert alert) {
        AlertClickListeners.handleAlert(this.context, alert, this.inboxType.isGuestMode());
        model.showUnread(false);
        notifyModelChanged(model);
    }
}
