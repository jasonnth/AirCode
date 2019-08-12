package com.airbnb.android.lib.cancellation.host;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.activities.TransparentActionBarActivity;
import com.airbnb.android.core.enums.ReservationCancellationReason;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.Incentive;
import com.airbnb.android.core.models.Price;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.requests.DeleteReservationRequest;
import com.airbnb.android.core.responses.ReservationResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.PrimaryButton;
import com.airbnb.p027n2.components.StandardRow;
import com.airbnb.p027n2.components.UserDetailsActionRow;
import icepick.State;
import p032rx.Observer;

public class CancellationOverviewFragment extends AirFragment {
    private static final String ARG_MESSAGE = "message";
    private static final String ARG_REASON = "reason";
    private static final String ARG_RESERVATION = "reservation";
    @BindView
    StandardRow breakdownFeeRow;
    final RequestListener<ReservationResponse> deleteReservationRequestListener = new C0699RL().onResponse(CancellationOverviewFragment$$Lambda$1.lambdaFactory$(this)).onError(CancellationOverviewFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    UserDetailsActionRow guestSummary;
    @State
    String message;
    @BindView
    StandardRow originalPayoutRow;
    @State
    ReservationCancellationReason reason;
    @State
    Reservation reservation;
    @BindView
    PrimaryButton submitButton;
    @BindView
    AirToolbar toolbar;

    static /* synthetic */ void lambda$new$0(CancellationOverviewFragment cancellationOverviewFragment, ReservationResponse data) {
        Activity activity = cancellationOverviewFragment.getActivity();
        activity.setResult(-1);
        activity.finish();
        cancellationOverviewFragment.startActivity(TransparentActionBarActivity.intentForFragment(cancellationOverviewFragment.getContext(), CancellationConfirmationFragment.newInstance(cancellationOverviewFragment.reservation)));
    }

    static /* synthetic */ void lambda$new$1(CancellationOverviewFragment cancellationOverviewFragment, AirRequestNetworkException e) {
        NetworkUtil.tryShowErrorWithSnackbar(cancellationOverviewFragment.submitButton, e);
        cancellationOverviewFragment.submitButton.setNormal();
    }

    public static CancellationOverviewFragment newInstance(Reservation reservation2, ReservationCancellationReason reason2, String message2) {
        return (CancellationOverviewFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new CancellationOverviewFragment()).putParcelable("reservation", reservation2)).putSerializable("reason", reason2)).putString("message", message2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(C0880R.layout.fragment_cancellation_overview, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        if (savedInstanceState == null) {
            this.reservation = (Reservation) getArguments().getParcelable("reservation");
            this.reason = (ReservationCancellationReason) getArguments().getSerializable("reason");
            this.message = getArguments().getString("message");
        }
        setupGuestSummary();
        setupPayoutBreakdownSection();
        return view;
    }

    @OnClick
    public void onSubmitCancellation() {
        this.submitButton.setLoading();
        new DeleteReservationRequest(this.reservation.getId(), this.reason.getServerKey(), this.message).withListener((Observer) this.deleteReservationRequestListener).execute(this.requestManager);
    }

    private void setupGuestSummary() {
        User guest = this.reservation.getGuest();
        this.guestSummary.setUserImageUrl(guest.getPictureUrl());
        this.guestSummary.setTitleText(guest.getName());
        this.guestSummary.setSubtitleText(guest.getLocation());
        this.guestSummary.setExtraText(getUserExtraText());
    }

    private void setupPayoutBreakdownSection() {
        this.originalPayoutRow.setInfoText((CharSequence) this.reservation.getPricingQuote().getHostPayoutBreakdown().getTotal().formattedForDisplay());
        this.originalPayoutRow.setSubtitleText((CharSequence) Price.getAccomodationText(this.reservation.getPricingQuote().getHostPayoutBreakdown().getPriceItems()));
        if (!this.reservation.hasIncentive(Incentive.PENALTY_FREE_CANCELLATION_TRIAL)) {
            this.breakdownFeeRow.setInfoText((CharSequence) getString(C0880R.string.cancellation_fee_display, this.mCurrencyHelper.formatNativeCurrency((double) this.reservation.getCancellationHostFeeNative(), true)));
            this.breakdownFeeRow.setSubtitleText((CharSequence) getString(C0880R.string.cancellation_days_from_trip, numDaysUntilReservationString()));
            return;
        }
        this.breakdownFeeRow.setVisibility(8);
    }

    private String getUserExtraText() {
        if (AirDate.isInPast(this.reservation.getCheckinDate())) {
            return getString(C0880R.string.ro_on_trip);
        }
        return getString(C0880R.string.trip_start_message, numDaysUntilReservationString());
    }

    private String numDaysUntilReservationString() {
        int numDays = AirDate.today().getDaysUntil(this.reservation.getStartDate());
        return getResources().getQuantityString(C0880R.plurals.x_days, numDays, new Object[]{Integer.valueOf(numDays)});
    }

    public Strap getNavigationTrackingParams() {
        return Strap.make().mo11638kv("reservation_id", this.reservation.getId()).mo11638kv("listing_id", this.reservation.getListing().getId());
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.HostCancellationPenalties;
    }
}
