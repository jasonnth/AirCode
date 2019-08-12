package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.ReservationCancellationLogger;
import com.airbnb.android.core.enums.ReservationCancellationReason;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.managelisting.handlers.ReasonPickerAdapter.ReasonPickerCallback;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.Strap;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import icepick.State;
import java.io.Serializable;

public class ReasonPickerFragment extends AirFragment {
    private static final String HIDE_CANCELLATION_FEE_KEY = "hide_cancellation_fee";
    private static final String IS_MODAL = "is_modal";
    private static final String REASON_KEY = "reason";
    @State
    boolean hideCancellationFee;
    @State
    boolean isLoading;
    @State
    boolean isModal;
    @BindView
    FrameLayout loader;
    ReservationCancellationLogger logger;
    @BindView
    AirButton nextButton;
    @State
    ReservationCancellationReason reason;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public interface ReasonPickerDataProvider {
        String getNextButtonText(ReservationCancellationReason reservationCancellationReason);

        Adapter getReasonsAdapter(ReservationCancellationReason reservationCancellationReason, boolean z, boolean z2);

        Reservation getReservation();

        boolean shouldShowDoneMenu(ReservationCancellationReason reservationCancellationReason);

        boolean shouldShowNextButton(ReservationCancellationReason reservationCancellationReason);
    }

    private ReasonPickerDataProvider getDataProvider() {
        Check.state(getActivity() instanceof ReasonPickerDataProvider);
        return (ReasonPickerDataProvider) getActivity();
    }

    private ReasonPickerCallback getReasonPickerCallback() {
        Check.state(getActivity() instanceof ReasonPickerCallback);
        return (ReasonPickerCallback) getActivity();
    }

    public static ReasonPickerFragment newInstanceWithState(ReservationCancellationReason reason2, boolean hideCancellationFee2) {
        return (ReasonPickerFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new ReasonPickerFragment()).putSerializable("reason", (Serializable) Check.notNull(reason2))).putSerializable(HIDE_CANCELLATION_FEE_KEY, Boolean.valueOf(hideCancellationFee2))).putSerializable(IS_MODAL, Boolean.valueOf(false))).build();
    }

    public static ReasonPickerFragment newModalInstanceWithState(ReservationCancellationReason reason2, boolean hideCancellationFee2) {
        return (ReasonPickerFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new ReasonPickerFragment()).putSerializable("reason", (Serializable) Check.notNull(reason2))).putSerializable(HIDE_CANCELLATION_FEE_KEY, Boolean.valueOf(hideCancellationFee2))).putSerializable(IS_MODAL, Boolean.valueOf(true))).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(getDataProvider().shouldShowDoneMenu(this.reason));
        ((AirbnbGraph) CoreApplication.instance(getContext()).component()).inject(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_reason_picker, container, false);
        ButterKnife.bind(this, view);
        setToolbar(this.toolbar);
        if (getArguments() != null) {
            this.reason = (ReservationCancellationReason) getArguments().getSerializable("reason");
            this.hideCancellationFee = getArguments().getBoolean(HIDE_CANCELLATION_FEE_KEY);
            this.isModal = getArguments().getBoolean(IS_MODAL);
        }
        ReasonPickerDataProvider dataProvider = getDataProvider();
        this.nextButton.setText(dataProvider.getNextButtonText(this.reason));
        this.nextButton.setVisibility(dataProvider.shouldShowNextButton(this.reason) ? 0 : 8);
        if (this.isModal) {
            this.toolbar.setNavigationIcon(2);
            this.toolbar.setNavigationOnClickListener(ReasonPickerFragment$$Lambda$1.lambdaFactory$(this));
        }
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setVerticalScrollBarEnabled(false);
        this.recyclerView.setAdapter(dataProvider.getReasonsAdapter(this.reason, this.hideCancellationFee, this.isModal));
        setLoadingVisibility();
        setHasOptionsMenu(getDataProvider().shouldShowDoneMenu(this.reason));
        this.logger.onReasonSelected(dataProvider.getReservation(), this.reason);
        return view;
    }

    private void setLoadingVisibility() {
        ViewUtils.setVisibleIf((View) this.loader, this.isLoading);
    }

    public void setLoading(boolean loading) {
        this.isLoading = loading;
        setLoadingVisibility();
    }

    public void finishLoadingRefreshAdapter() {
        setLoading(false);
        this.recyclerView.setAdapter(getDataProvider().getReasonsAdapter(this.reason, this.hideCancellationFee, this.isModal));
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(C0880R.C0883menu.fragment_edit_text, menu);
        menu.findItem(C0880R.C0882id.done).setTitle(C0880R.string.done);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C0880R.C0882id.done) {
            return false;
        }
        getActivity().setResult(-1);
        getActivity().finish();
        return true;
    }

    @OnClick
    public void clickNext() {
        getReasonPickerCallback().onCancelReservationClicked(this.reason, this.hideCancellationFee);
    }

    public NavigationTag getNavigationTrackingTag() {
        return ReservationCancellationLogger.getNavigationTag(this.reason, false);
    }

    public Strap getNavigationTrackingParams() {
        Reservation reservation = getDataProvider().getReservation();
        return Strap.make().mo11639kv("event_data", getNavigationTrackingTag().trackingName).mo11638kv("listing_id", reservation.getListing().getId()).mo11638kv("reservation_id", reservation.getId());
    }
}
