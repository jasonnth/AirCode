package com.airbnb.android.lib.fragments.alerts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.activities.AutoFragmentActivity;
import com.airbnb.android.core.activities.AutoFragmentActivity.Builder;
import com.airbnb.android.core.events.AlertsChangedEvent;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.DashboardAlert;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.requests.DashboardAlertsRequest;
import com.airbnb.android.core.requests.UserRequest;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.DashboardAlertsResponse;
import com.airbnb.android.core.responses.UserResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.views.AirSwipeRefreshLayout;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.adapters.AlertsAdapter;
import com.airbnb.android.lib.utils.ThemeUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import java.util.List;
import p032rx.Observer;

public class AlertsFragment extends AirFragment {
    private static final String ARG_INBOX_TYPE = "inbox_type";
    private static final String ARG_PRELOADED_LIST = "preloaded_list";
    public static final String RESULT_UPDATED_ALERTS = "alerts";
    private AlertsAdapter alertsAdapter;
    final RequestListener<DashboardAlertsResponse> alertsListener = new C0699RL().onResponse(AlertsFragment$$Lambda$1.lambdaFactory$(this)).onError(AlertsFragment$$Lambda$2.lambdaFactory$(this)).onComplete(AlertsFragment$$Lambda$3.lambdaFactory$(this)).build();
    public final NonResubscribableRequestListener<AirBatchResponse> batchListener = new C0699RL().onResponse(AlertsFragment$$Lambda$4.lambdaFactory$(this)).onError(AlertsFragment$$Lambda$5.lambdaFactory$(this)).onComplete(AlertsFragment$$Lambda$6.lambdaFactory$(this)).buildWithoutResubscription();
    private InboxType inboxType;
    private boolean isActiveHost;
    private boolean isEligibleForNestedListings;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirSwipeRefreshLayout swipeRefreshLayout;
    @BindView
    AirToolbar toolbar;

    public static Intent createIntent(Context context, InboxType inboxType2, ArrayList<DashboardAlert> preloadedList) {
        return ((Builder) ((Builder) AutoFragmentActivity.create(context, AlertsFragment.class).putSerializable("inbox_type", inboxType2)).putParcelableArrayList(ARG_PRELOADED_LIST, preloadedList)).build();
    }

    static /* synthetic */ void lambda$new$2(AlertsFragment alertsFragment, Boolean successful) {
        alertsFragment.swipeRefreshLayout.setRefreshing(false);
        alertsFragment.alertsAdapter.setLoading(false);
    }

    static /* synthetic */ void lambda$new$3(AlertsFragment alertsFragment, AirBatchResponse response) {
        DashboardAlertsResponse alertsResponse = (DashboardAlertsResponse) response.singleResponse(DashboardAlertsResponse.class);
        UserResponse userResponse = (UserResponse) response.singleResponse(UserResponse.class);
        alertsFragment.isEligibleForNestedListings = userResponse.user.isEligibleForNestedListings();
        alertsFragment.isActiveHost = userResponse.user.isActiveHost();
        alertsFragment.alertsAdapter.setAlerts(new ArrayList(alertsResponse.dashboardAlerts), alertsFragment.isEligibleForNestedListings, alertsFragment.isActiveHost);
    }

    static /* synthetic */ void lambda$new$5(AlertsFragment alertsFragment, Boolean successful) {
        alertsFragment.swipeRefreshLayout.setRefreshing(false);
        alertsFragment.alertsAdapter.setLoading(false);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.inboxType = (InboxType) Check.notNull(getArguments().getSerializable("inbox_type"));
        this.alertsAdapter = new AlertsAdapter(getContext(), this.inboxType, savedInstanceState);
        if (savedInstanceState == null) {
            this.alertsAdapter.setAlerts(getArguments().getParcelableArrayList(ARG_PRELOADED_LIST), this.isEligibleForNestedListings, this.isActiveHost);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = ThemeUtils.inflaterForPhonePadding(inflater).inflate(C0880R.layout.alerts_fragment, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.mBus.register(this);
        this.recyclerView.setHasFixedSize(true);
        this.swipeRefreshLayout.setScrollableChild(this.recyclerView);
        this.swipeRefreshLayout.setOnRefreshListener(AlertsFragment$$Lambda$7.lambdaFactory$(this));
        this.recyclerView.setAdapter(this.alertsAdapter);
        if (savedInstanceState == null) {
            load(true);
        }
        getAirActivity().setOnBackPressedListener(AlertsFragment$$Lambda$8.lambdaFactory$(this));
        getAirActivity().setOnHomePressedListener(AlertsFragment$$Lambda$9.lambdaFactory$(this));
        return view;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mBus.unregister(this);
        getAirActivity().setOnBackPressedListener(null);
        getAirActivity().setOnHomePressedListener(null);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.alertsAdapter.onSaveInstanceState(outState);
    }

    @Subscribe
    public void onAlertsChanged(AlertsChangedEvent alertsChangedEvent) {
        load(false);
    }

    /* access modifiers changed from: private */
    public void load(boolean isFirstLoad) {
        boolean z = true;
        this.alertsAdapter.setLoading(true);
        if (this.inboxType != InboxType.Host || !isFirstLoad) {
            BaseRequestV2 withListener = DashboardAlertsRequest.forInboxType(this.inboxType, getContext()).withListener((Observer) this.alertsListener);
            if (isFirstLoad) {
                z = false;
            }
            withListener.skipCache(z).execute(this.requestManager);
            return;
        }
        List<BaseRequestV2<?>> requests = new ArrayList<>();
        requests.add(DashboardAlertsRequest.forHost(getContext()));
        requests.add(UserRequest.newRequestForDashboardAlerts(this.mAccountManager.getCurrentUserId()));
        new AirBatchRequest(requests, false, this.batchListener).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void setResultAndFinish() {
        Intent intent = new Intent();
        intent.putExtra(RESULT_UPDATED_ALERTS, this.alertsAdapter.getAlerts());
        getAirActivity().setResult(-1, intent);
        getAirActivity().finish();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.Alerts;
    }

    public Strap getNavigationTrackingParams() {
        return this.inboxType.addLoggingParams(super.getNavigationTrackingParams());
    }
}
