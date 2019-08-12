package com.airbnb.android.lib.paidamenities.fragments.pending;

import android.content.Context;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import butterknife.BindView;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.models.CurrencyAmount;
import com.airbnb.android.core.models.PaidAmenity;
import com.airbnb.android.core.models.PaidAmenityOrder;
import com.airbnb.android.core.paidamenities.enums.PaidAmenityOrderStatus;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.SpannableUtils;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.paidamenities.fragments.pending.BasePendingAmenityFragment.UserMode;
import com.airbnb.android.lib.paidamenities.requests.UpdatePaidAmenityOrderRequest;
import com.airbnb.android.lib.paidamenities.requests.UpdatePaidAmenityOrderRequest.PaidAmenityOrderServerStatus;
import com.airbnb.android.utils.CurrencyUtils;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import icepick.State;
import java.math.BigDecimal;
import p032rx.Observer;

public class PendingAmenityOrderDetailFragment extends BasePendingAmenityFragment {
    @BindView
    AirButton acceptButton;
    private PendingAmenityOrderDetailAdapter adapter;
    @BindView
    RelativeLayout buttonContainer;
    @BindView
    AirButton declineButton;
    private Listener listener;
    @State
    PaidAmenityOrder paidAmenityOrder;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;
    final RequestListener<Object> updateStatusListener = new C0699RL().onResponse(PendingAmenityOrderDetailFragment$$Lambda$1.lambdaFactory$(this)).onError(PendingAmenityOrderDetailFragment$$Lambda$2.lambdaFactory$(this)).build();
    @State
    UserMode userMode;

    public interface Listener {
        void onPaidAmenityOrderStatusUpdated();
    }

    public class PendingAmenityOrderDetailAdapter extends AirEpoxyAdapter {
        private final SimpleTextRowEpoxyModel_ amenityDescriptionRowEpoxyModel = new SimpleTextRowEpoxyModel_();
        private final DocumentMarqueeEpoxyModel_ documentMarqueeEpoxyModel = new DocumentMarqueeEpoxyModel_();
        private final SimpleTextRowEpoxyModel_ legalContentRowEpoxyModel = new SimpleTextRowEpoxyModel_();
        private final LinkActionRowEpoxyModel_ linkRowEpoxyModel = new LinkActionRowEpoxyModel_();
        private final LoadingRowEpoxyModel_ loadingRowEpoxyModel = new LoadingRowEpoxyModel_();
        private final StandardRowEpoxyModel_ numOrdersRowEpoxyModel = new StandardRowEpoxyModel_();
        private final StandardRowEpoxyModel_ paymentRowEpoxyModel = new StandardRowEpoxyModel_();

        public PendingAmenityOrderDetailAdapter() {
            configureDocumentMarquee();
            configureAmenityOrderDetail();
            configureLegalContentAndUserActions();
        }

        private void configureDocumentMarquee() {
            PaidAmenityOrderStatus paidAmenityOrderStatus = PendingAmenityOrderDetailFragment.this.paidAmenityOrder.getStatus();
            this.documentMarqueeEpoxyModel.titleText((CharSequence) PendingAmenityOrderDetailFragment.this.paidAmenityOrder.getPaidAmenity().getTitle()).captionText((CharSequence) SpannableUtils.makeColoredString(PendingAmenityOrderDetailFragment.this.getResources().getString(paidAmenityOrderStatus.getDisplayStatusTextRes()), PendingAmenityOrderDetailFragment.this.getResources().getColor(paidAmenityOrderStatus.getColorRes())));
            addModel(this.documentMarqueeEpoxyModel);
        }

        private void configureAmenityOrderDetail() {
            PaidAmenity paidAmenity = PendingAmenityOrderDetailFragment.this.paidAmenityOrder.getPaidAmenity();
            this.numOrdersRowEpoxyModel.title(C0880R.string.paid_amenities_number_of_order_requested_row_title_text).infoText((CharSequence) String.valueOf(PendingAmenityOrderDetailFragment.this.paidAmenityOrder.getNumberOfUnits()));
            this.paymentRowEpoxyModel.title(C0880R.string.paid_amenities_payment_row_title_text).infoText((CharSequence) getFormattedCurrencyWithMultiplier(paidAmenity.getLocalizedTotalPrice(), PendingAmenityOrderDetailFragment.this.paidAmenityOrder.getNumberOfUnits()));
            this.amenityDescriptionRowEpoxyModel.text(paidAmenity.getDescription());
            addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.numOrdersRowEpoxyModel, this.paymentRowEpoxyModel, this.amenityDescriptionRowEpoxyModel});
        }

        private void configureLegalContentAndUserActions() {
            switch (PendingAmenityOrderDetailFragment.this.userMode) {
                case Guest:
                    configureGuestLegalContentAndActions();
                    return;
                case Host:
                    configureHostLegalContentAndActions();
                    return;
                default:
                    return;
            }
        }

        private void configureGuestLegalContentAndActions() {
            Check.state(PendingAmenityOrderDetailFragment.this.userMode.equals(UserMode.Guest));
            if (PendingAmenityOrderDetailFragment.this.paidAmenityOrder.getStatus().equals(PaidAmenityOrderStatus.Pending)) {
                configureLegalContentRow(PendingAmenityOrderDetailFragment.this.getString(C0880R.string.guest_pending_paid_amenity_order_detail_disclaimer_text));
                configureCancelLinkRow();
            }
        }

        private void configureHostLegalContentAndActions() {
            Check.state(PendingAmenityOrderDetailFragment.this.userMode.equals(UserMode.Host));
            switch (PendingAmenityOrderDetailFragment.this.paidAmenityOrder.getStatus()) {
                case Pending:
                    configureLegalContentRow(PendingAmenityOrderDetailFragment.this.getString(C0880R.string.host_pending_paid_amenity_pending_order_detail_disclaimer_text));
                    PendingAmenityOrderDetailFragment.this.setUpHostActionButtons();
                    return;
                case Accepted:
                    configureLegalContentRow(PendingAmenityOrderDetailFragment.this.getString(C0880R.string.host_pending_paid_amenity_accepted_order_detail_disclaimer_text));
                    configureCancelLinkRow();
                    return;
                case Declined:
                case Cancelled:
                    return;
                default:
                    BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Unknown paid amenity order status"));
                    return;
            }
        }

        private void configureLegalContentRow(String content) {
            this.legalContentRowEpoxyModel.text(content).small();
            addModel(this.legalContentRowEpoxyModel);
        }

        private void configureCancelLinkRow() {
            PendingAmenityOrderDetailFragment.this.buttonContainer.setVisibility(8);
            this.linkRowEpoxyModel.textRes(C0880R.string.cancel_paid_amenities_order_text).clickListener(C7096x8763339d.lambdaFactory$(this)).showDivider(false);
            addModel(this.linkRowEpoxyModel);
        }

        static /* synthetic */ void lambda$configureCancelLinkRow$0(PendingAmenityOrderDetailAdapter pendingAmenityOrderDetailAdapter, View v) {
            PendingAmenityOrderDetailFragment.this.logCancelPaidAmenityOrder();
            pendingAmenityOrderDetailAdapter.setLoading();
            PendingAmenityOrderDetailFragment.this.cancelPaidAmenityOrder();
        }

        private String getFormattedCurrencyWithMultiplier(CurrencyAmount unitPrice, int multiplier) {
            return CurrencyUtils.formatCurrencyAmount(unitPrice.getAmount().multiply(BigDecimal.valueOf((long) multiplier)).doubleValue(), unitPrice.getCurrency());
        }

        private void setLoading() {
            removeAllAfterModel(this.documentMarqueeEpoxyModel);
            addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.loadingRowEpoxyModel});
        }

        /* access modifiers changed from: private */
        public void restoreFromLoading() {
            removeModel(this.loadingRowEpoxyModel);
            configureAmenityOrderDetail();
        }
    }

    public static PendingAmenityOrderDetailFragment newInstanceAsGuest(PaidAmenityOrder paidAmenityOrder2) {
        return (PendingAmenityOrderDetailFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new PendingAmenityOrderDetailFragment()).putInt("user_mode", UserMode.Guest.ordinal())).putParcelable("paid_amenity_order", paidAmenityOrder2)).build();
    }

    public static PendingAmenityOrderDetailFragment newInstanceAsHost(PaidAmenityOrder paidAmenityOrder2) {
        return (PendingAmenityOrderDetailFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new PendingAmenityOrderDetailFragment()).putInt("user_mode", UserMode.Host.ordinal())).putParcelable("paid_amenity_order", paidAmenityOrder2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_pending_amenity_order_detail, container, false);
        bindViews(view);
        Bundle args = getArguments();
        this.userMode = UserMode.values()[args.getInt("user_mode")];
        this.paidAmenityOrder = (PaidAmenityOrder) args.getParcelable("paid_amenity_order");
        Check.notNull(this.userMode);
        Check.notNull(this.paidAmenityOrder);
        setToolbar(this.toolbar);
        this.adapter = new PendingAmenityOrderDetailAdapter();
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setHasFixedSize(true);
        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.listener = (Listener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement Listener interface.");
        }
    }

    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }

    /* access modifiers changed from: private */
    public void cancelPaidAmenityOrder() {
        switch (this.userMode) {
            case Guest:
                updatePaidAmenityOrder(PaidAmenityOrderServerStatus.STATUS_CANCELLED_BY_GUEST);
                return;
            case Host:
                updatePaidAmenityOrder(PaidAmenityOrderServerStatus.STATUS_CANCELLED_BY_HOST);
                return;
            default:
                return;
        }
    }

    private void updatePaidAmenityOrder(PaidAmenityOrderServerStatus newStatus) {
        UpdatePaidAmenityOrderRequest.forStatus(this.paidAmenityOrder, newStatus).withListener((Observer) this.updateStatusListener).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void setUpHostActionButtons() {
        Check.state(this.userMode.equals(UserMode.Host));
        this.buttonContainer.setVisibility(0);
        this.acceptButton.setOnClickListener(PendingAmenityOrderDetailFragment$$Lambda$3.lambdaFactory$(this));
        this.declineButton.setOnClickListener(PendingAmenityOrderDetailFragment$$Lambda$4.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$setUpHostActionButtons$0(PendingAmenityOrderDetailFragment pendingAmenityOrderDetailFragment, View v) {
        pendingAmenityOrderDetailFragment.paidAmenityJitneyLogger.logHostFulfillClickAccept(pendingAmenityOrderDetailFragment.paidAmenityOrder.getId());
        pendingAmenityOrderDetailFragment.acceptButton.setState(AirButton.State.Loading);
        pendingAmenityOrderDetailFragment.declineButton.setEnabled(false);
        pendingAmenityOrderDetailFragment.updatePaidAmenityOrder(PaidAmenityOrderServerStatus.STATUS_ACCEPTED);
    }

    static /* synthetic */ void lambda$setUpHostActionButtons$1(PendingAmenityOrderDetailFragment pendingAmenityOrderDetailFragment, View v) {
        pendingAmenityOrderDetailFragment.paidAmenityJitneyLogger.logHostFulfillClickDecline(pendingAmenityOrderDetailFragment.paidAmenityOrder.getId());
        pendingAmenityOrderDetailFragment.declineButton.setState(AirButton.State.Loading);
        pendingAmenityOrderDetailFragment.acceptButton.setEnabled(false);
        pendingAmenityOrderDetailFragment.updatePaidAmenityOrder(PaidAmenityOrderServerStatus.STATUS_DECLINED);
    }

    private void restoreHostActionButtonState() {
        Check.state(this.userMode.equals(UserMode.Host));
        this.acceptButton.setState(AirButton.State.Normal);
        this.declineButton.setState(AirButton.State.Normal);
        this.acceptButton.setEnabled(true);
        this.declineButton.setEnabled(true);
    }

    private void restoreGuestRowModels() {
        Check.state(this.userMode.equals(UserMode.Guest));
        this.adapter.restoreFromLoading();
    }

    /* access modifiers changed from: private */
    public void logCancelPaidAmenityOrder() {
        if (this.userMode.equals(UserMode.Host)) {
            this.paidAmenityJitneyLogger.logHostFulfillClickCancel(this.paidAmenityOrder.getId());
        } else {
            this.paidAmenityJitneyLogger.logGuestAmendClickCancel(this.paidAmenityOrder.getId());
        }
    }

    static /* synthetic */ void lambda$new$2(PendingAmenityOrderDetailFragment pendingAmenityOrderDetailFragment, Object response) {
        if (pendingAmenityOrderDetailFragment.userMode.equals(UserMode.Host)) {
            pendingAmenityOrderDetailFragment.restoreHostActionButtonState();
        } else {
            pendingAmenityOrderDetailFragment.restoreGuestRowModels();
        }
        pendingAmenityOrderDetailFragment.getActivity().getSupportFragmentManager().popBackStackImmediate();
        pendingAmenityOrderDetailFragment.listener.onPaidAmenityOrderStatusUpdated();
    }
}
