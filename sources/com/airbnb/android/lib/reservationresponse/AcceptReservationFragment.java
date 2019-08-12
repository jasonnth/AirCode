package com.airbnb.android.lib.reservationresponse;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.analytics.HostReservationObjectJitneyLogger;
import com.airbnb.android.core.calendar.CalendarStore;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.tripprovider.TripInformationProvider;
import com.airbnb.android.core.requests.UpdateReservationRequest;
import com.airbnb.android.core.utils.InstantBookUpsellManager;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.reservationresponse.ReservationResponseBaseFragment.ReservationResponseNavigator;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import icepick.State;

public class AcceptReservationFragment extends AirFragment {
    private static final String ARG_PROVIDER = "trip_provider";
    private static final String ARG_SK_CANCELLATION = "sk_cancellation_policy";
    @BindView
    AirButton acceptButton;
    protected CalendarStore calendarStore;
    @BindView
    AirButton cancelButton;
    InstantBookUpsellManager instantBookUpsellManager;
    @State
    boolean isRequestAccepted;
    HostReservationObjectJitneyLogger jitneyLogger;
    @BindView
    SheetMarquee marquee;
    ReservationResponseNavigator onAcceptListener;
    @State
    TripInformationProvider provider;
    final RequestListener<BaseResponse> updateRequestListener = new C0699RL().onResponse(AcceptReservationFragment$$Lambda$1.lambdaFactory$(this)).onError(AcceptReservationFragment$$Lambda$2.lambdaFactory$(this)).build();

    static /* synthetic */ void lambda$new$1(AcceptReservationFragment acceptReservationFragment, BaseResponse data) {
        long listingId = acceptReservationFragment.provider.getListing().getId();
        acceptReservationFragment.isRequestAccepted = true;
        acceptReservationFragment.jitneyLogger.reservationObjectAcceptConfirmation(acceptReservationFragment.provider);
        acceptReservationFragment.acceptButton.setState(AirButton.State.Success);
        acceptReservationFragment.cancelButton.setEnabled(true);
        acceptReservationFragment.calendarStore.refreshDays(listingId, acceptReservationFragment.provider.getStartDate(), acceptReservationFragment.provider.getEndDate());
        acceptReservationFragment.instantBookUpsellManager.onRequestAccepted(listingId);
        if (acceptReservationFragment.instantBookUpsellManager.shouldShowUpsell(listingId)) {
            acceptReservationFragment.instantBookUpsellManager.onUpsellAfterAcceptance(listingId);
            acceptReservationFragment.getAirActivity().showFragment(AcceptReservationConfirmationFragment.newInstance(acceptReservationFragment.provider), C0880R.C0882id.content_container, FragmentTransitionType.SlideInFromSide, true, AcceptReservationConfirmationFragment.class.getSimpleName());
            return;
        }
        new SnackbarWrapper().view(acceptReservationFragment.getView()).action(C0880R.string.done, AcceptReservationFragment$$Lambda$3.lambdaFactory$(acceptReservationFragment)).body(C0880R.string.ro_accept_sheet_success).buildAndShow();
    }

    static /* synthetic */ void lambda$new$2(AcceptReservationFragment acceptReservationFragment, AirRequestNetworkException e) {
        acceptReservationFragment.acceptButton.setState(AirButton.State.Normal);
        acceptReservationFragment.cancelButton.setEnabled(true);
        NetworkUtil.tryShowErrorWithSnackbar(acceptReservationFragment.getView(), e);
    }

    public static AcceptReservationFragment newInstanceForTripProvider(TripInformationProvider provider2) {
        return (AcceptReservationFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new AcceptReservationFragment()).putParcelable("trip_provider", provider2)).putBoolean(ARG_SK_CANCELLATION, false)).build();
    }

    public static AcceptReservationFragment newInstanceForTripProvider(TripInformationProvider provider2, boolean hostAgreedSouthKoreanPreapproval) {
        return (AcceptReservationFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new AcceptReservationFragment()).putParcelable("trip_provider", provider2)).putBoolean(ARG_SK_CANCELLATION, hostAgreedSouthKoreanPreapproval)).build();
    }

    /* access modifiers changed from: private */
    public void navigateBack() {
        if (!this.isRequestAccepted) {
            getActivity().finish();
        } else if (this.onAcceptListener != null) {
            this.onAcceptListener.onDoneWithAccept();
        } else {
            getActivity().setResult(-1);
            getActivity().finish();
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ReservationResponseNavigator) {
            this.onAcceptListener = (ReservationResponseNavigator) context;
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(C0880R.layout.fragment_accept_reservation, container, false);
        bindViews(view);
        ((AirbnbGraph) AirbnbApplication.instance(getContext()).component()).inject(this);
        if (getActionBar() != null) {
            getActionBar().hide();
        }
        this.provider = (TripInformationProvider) getArguments().getParcelable("trip_provider");
        this.acceptButton.setText(C0880R.string.ro_accept_sheet_button);
        setMarquee();
        return view;
    }

    @OnClick
    public void clickAccept() {
        UpdateReservationRequest.forAccept(this.provider.getReservation().getId(), getArguments().getBoolean(ARG_SK_CANCELLATION)).withListener(this.updateRequestListener).execute(this.requestManager);
        this.acceptButton.setState(AirButton.State.Loading);
        this.cancelButton.setEnabled(false);
    }

    @OnClick
    public void clickCancel() {
        if (this.cancelButton.isEnabled()) {
            navigateBack();
        }
    }

    public boolean isRequestAccepted() {
        return this.isRequestAccepted;
    }

    private void setMarquee() {
        String nightsPluralStr = getResources().getQuantityString(C0880R.plurals.x_nights, this.provider.getReservedNightsCount(), new Object[]{Integer.valueOf(this.provider.getReservedNightsCount())});
        this.marquee.setTitle(getString(C0880R.string.ro_accept_sheet_title, this.provider.getGuestIfPossible().getName(), nightsPluralStr));
    }
}
