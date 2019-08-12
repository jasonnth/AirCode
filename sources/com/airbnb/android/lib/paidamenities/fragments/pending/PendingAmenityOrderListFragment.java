package com.airbnb.android.lib.paidamenities.fragments.pending;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.models.PaidAmenityOrder;
import com.airbnb.android.core.paidamenities.enums.PaidAmenityOrderStatus;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.SpannableUtils;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.paidamenities.fragments.pending.BasePendingAmenityFragment.UserMode;
import com.airbnb.android.lib.paidamenities.requests.FetchPaidAmenityOrdersRequest;
import com.airbnb.android.lib.paidamenities.responses.PaidAmenityOrdersResponse;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.google.common.collect.Lists;
import icepick.State;
import java.util.ArrayList;
import java.util.Iterator;
import p032rx.Observer;

public class PendingAmenityOrderListFragment extends BasePendingAmenityFragment {
    private PendingServicesListAdapter adapter;
    final RequestListener<PaidAmenityOrdersResponse> fetchPaidAmenityOrdersListener = new C0699RL().onResponse(PendingAmenityOrderListFragment$$Lambda$1.lambdaFactory$(this)).onError(PendingAmenityOrderListFragment$$Lambda$2.lambdaFactory$(this)).build();
    @State
    ArrayList<PaidAmenityOrder> paidAmenityOrders;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public class PendingServicesListAdapter extends AirEpoxyAdapter {
        private final DocumentMarqueeEpoxyModel_ documentMarqueeEpoxyModel = new DocumentMarqueeEpoxyModel_();
        private final LinkActionRowEpoxyModel_ linkRowEpoxyModel = new LinkActionRowEpoxyModel_();
        private final LoadingRowEpoxyModel_ loadingRowEpoxyModel = new LoadingRowEpoxyModel_();

        public PendingServicesListAdapter() {
            configureDocumentMarqueeEpoxyModel_();
            if (PendingAmenityOrderListFragment.this.paidAmenityOrders == null || PendingAmenityOrderListFragment.this.paidAmenityOrders.isEmpty()) {
                this.models.add(this.loadingRowEpoxyModel);
                return;
            }
            configurePaidAmenityOrders();
            configureRequestAnotherService();
        }

        /* access modifiers changed from: private */
        public void setLoading() {
            removeAllAfterModel(this.documentMarqueeEpoxyModel);
            addModel(this.loadingRowEpoxyModel);
        }

        /* access modifiers changed from: private */
        public void setAmenityOrders() {
            removeModel(this.loadingRowEpoxyModel);
            configurePaidAmenityOrders();
            configureRequestAnotherService();
        }

        private void configureDocumentMarqueeEpoxyModel_() {
            switch (PendingAmenityOrderListFragment.this.userMode) {
                case Host:
                    this.documentMarqueeEpoxyModel.titleRes(C0880R.string.host_pending_paid_amenity_order_list_marquee_title);
                    break;
                case Guest:
                    this.documentMarqueeEpoxyModel.titleRes(C0880R.string.guest_pending_paid_amenity_order_list_marquee_title);
                    break;
            }
            this.models.add(this.documentMarqueeEpoxyModel);
        }

        private void configurePaidAmenityOrders() {
            Iterator it = PendingAmenityOrderListFragment.this.paidAmenityOrders.iterator();
            while (it.hasNext()) {
                addModel(paidAmenityOrderToRowModel((PaidAmenityOrder) it.next()));
            }
        }

        private void configureRequestAnotherService() {
            if (PendingAmenityOrderListFragment.this.userMode.equals(UserMode.Guest)) {
                this.linkRowEpoxyModel.textRes(C0880R.string.purchase_amenities_request_another_service_button_text).showDivider(false).clickListener(C7098xac7466da.lambdaFactory$(this));
                addModel(this.linkRowEpoxyModel);
            }
        }

        private StandardRowEpoxyModel_ paidAmenityOrderToRowModel(PaidAmenityOrder paidAmenityOrder) {
            StandardRowEpoxyModel_ rowModel = new StandardRowEpoxyModel_();
            PaidAmenityOrderStatus orderStatus = paidAmenityOrder.getStatus();
            rowModel.title((CharSequence) paidAmenityOrder.getPaidAmenity().getTitle()).subtitle((CharSequence) SpannableUtils.makeColoredString(PendingAmenityOrderListFragment.this.getResources().getString(orderStatus.getDisplayStatusTextRes()), PendingAmenityOrderListFragment.this.getResources().getColor(orderStatus.getColorRes()))).clickListener(C7099xac7466db.lambdaFactory$(this, paidAmenityOrder));
            return rowModel;
        }

        static /* synthetic */ void lambda$paidAmenityOrderToRowModel$1(PendingServicesListAdapter pendingServicesListAdapter, PaidAmenityOrder paidAmenityOrder, View v) {
            PendingAmenityOrderListFragment.this.logClickAmenity(paidAmenityOrder);
            PendingAmenityOrderListFragment.this.goToPendingAmenityOrderDetail(paidAmenityOrder);
        }
    }

    public static PendingAmenityOrderListFragment newInstanceAsGuest(String confirmationCode) {
        return (PendingAmenityOrderListFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new PendingAmenityOrderListFragment()).putInt("user_mode", UserMode.Guest.ordinal())).putString("confirmation_code", confirmationCode)).build();
    }

    public static PendingAmenityOrderListFragment newInstanceAsHost(String confirmationCode) {
        return (PendingAmenityOrderListFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new PendingAmenityOrderListFragment()).putInt("user_mode", UserMode.Host.ordinal())).putString("confirmation_code", confirmationCode)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.recycler_view_with_toolbar_dark_foreground, container, false);
        bindViews(view);
        this.userMode = UserMode.values()[getArguments().getInt("user_mode")];
        this.confirmationCode = getArguments().getString("confirmation_code");
        if (this.paidAmenityOrders == null) {
            fetchPaidAmenityOrders();
        }
        setToolbar(this.toolbar);
        this.adapter = new PendingServicesListAdapter();
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setHasFixedSize(true);
        return view;
    }

    public void updatePaidAmenityOrderStatus() {
        super.updatePaidAmenityOrderStatus();
        this.adapter.setLoading();
        fetchPaidAmenityOrders();
    }

    private void fetchPaidAmenityOrders() {
        FetchPaidAmenityOrdersRequest.forConfirmationCode(this.confirmationCode).withListener((Observer) this.fetchPaidAmenityOrdersListener).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void requestAnotherService() {
        Check.state(this.userMode.equals(UserMode.Guest));
        getGuestPendingAmenityActivity().goToRequestAnotherService();
    }

    /* access modifiers changed from: private */
    public void logClickAmenity(PaidAmenityOrder paidAmenityOrder) {
        if (this.userMode.equals(UserMode.Host)) {
            this.paidAmenityJitneyLogger.logHostFulfillClickService(paidAmenityOrder.getId());
        } else {
            this.paidAmenityJitneyLogger.logGuestAmendClickExisting(paidAmenityOrder.getId());
        }
    }

    static /* synthetic */ void lambda$new$0(PendingAmenityOrderListFragment pendingAmenityOrderListFragment, PaidAmenityOrdersResponse response) {
        if (pendingAmenityOrderListFragment.paidAmenityOrders == null) {
            pendingAmenityOrderListFragment.paidAmenityOrders = Lists.newArrayList();
        } else {
            pendingAmenityOrderListFragment.paidAmenityOrders.clear();
        }
        pendingAmenityOrderListFragment.paidAmenityOrders.addAll(response.paidAmenityOrders);
        pendingAmenityOrderListFragment.adapter.setAmenityOrders();
    }
}
