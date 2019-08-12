package com.airbnb.android.lib.fragments.reservationresponse;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.analytics.HostReservationObjectJitneyLogger;
import com.airbnb.android.core.calendar.CalendarStore;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.tripprovider.TripInformationProvider;
import com.airbnb.android.core.requests.UpdateReservationRequest;
import com.airbnb.android.core.utils.InstantBookUpsellManager;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.analytics.ROAnalytics;
import com.airbnb.android.lib.reservationresponse.AcceptReservationConfirmationFragment;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.BottomButtonBar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.TextRow;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ReservationResponseLandingFragment extends ReservationResponseBaseFragment {
    @BindView
    BottomButtonBar buttonBar;
    protected CalendarStore calendarStore;
    @BindView
    DocumentMarquee documentMarquee;
    InstantBookUpsellManager instantBookUpsellManager;
    HostReservationObjectJitneyLogger jitneyLogger;
    @BindView
    TextRow reflectionMessageView;
    @BindView
    AirToolbar toolbar;
    final RequestListener<BaseResponse> updateRequestListener = new C0699RL().onResponse(ReservationResponseLandingFragment$$Lambda$1.lambdaFactory$(this)).onError(ReservationResponseLandingFragment$$Lambda$2.lambdaFactory$(this)).build();

    static /* synthetic */ void lambda$new$0(ReservationResponseLandingFragment reservationResponseLandingFragment, BaseResponse data) {
        TripInformationProvider provider = TripInformationProvider.create(reservationResponseLandingFragment.getReservation());
        long listingId = provider.getListing().getId();
        reservationResponseLandingFragment.jitneyLogger.reservationObjectAcceptConfirmation(provider);
        reservationResponseLandingFragment.buttonBar.setLoading(false);
        reservationResponseLandingFragment.toolbar.setNavigationIcon(1);
        reservationResponseLandingFragment.calendarStore.refreshDays(listingId, provider.getStartDate(), provider.getEndDate());
        reservationResponseLandingFragment.instantBookUpsellManager.onRequestAccepted(listingId);
        if (reservationResponseLandingFragment.instantBookUpsellManager.shouldShowUpsell(listingId)) {
            reservationResponseLandingFragment.instantBookUpsellManager.onUpsellAfterAcceptance(listingId);
            reservationResponseLandingFragment.getAirActivity().showFragment(AcceptReservationConfirmationFragment.newInstance(provider), C0880R.C0882id.content_container, FragmentTransitionType.SlideInFromSide, true, AcceptReservationConfirmationFragment.class.getSimpleName());
            return;
        }
        reservationResponseLandingFragment.getNavigator().onDoneWithAccept();
    }

    static /* synthetic */ void lambda$new$1(ReservationResponseLandingFragment reservationResponseLandingFragment, AirRequestNetworkException e) {
        reservationResponseLandingFragment.buttonBar.setLoading(false);
        reservationResponseLandingFragment.toolbar.setNavigationIcon(2);
        NetworkUtil.tryShowErrorWithSnackbar(reservationResponseLandingFragment.getView(), e);
    }

    public static ReservationResponseBaseFragment newInstance() {
        return new ReservationResponseLandingFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_ro_response_landing, container, false);
        bindViews(view);
        ((AirbnbGraph) AirbnbApplication.instance(getContext()).component()).inject(this);
        setToolbar(this.toolbar);
        this.documentMarquee.setTitle((CharSequence) getTitle());
        this.documentMarquee.setCaption((CharSequence) getSubtitle());
        if (FeatureToggles.shouldShowDecisionCriteriaMessage()) {
            this.reflectionMessageView.setText(C0880R.string.host_decision_criteria_message);
            this.reflectionMessageView.expand();
        } else {
            this.reflectionMessageView.setVisibility(8);
        }
        this.buttonBar.setPositiveActionText(C0880R.string.ro_response_accept);
        this.buttonBar.setPositiveAction(ReservationResponseLandingFragment$$Lambda$3.lambdaFactory$(this));
        this.buttonBar.setNegativeActionText(C0880R.string.ro_response_decline);
        this.buttonBar.setNegativeAction(ReservationResponseLandingFragment$$Lambda$4.lambdaFactory$(this));
        return view;
    }

    /* access modifiers changed from: private */
    public void onAcceptReservation() {
        ROAnalytics.trackRespondNowSelectOptionForReservation("click_accept", getReservation());
        UpdateReservationRequest.forAccept(getReservation().getId()).withListener(this.updateRequestListener).execute(this.requestManager);
        this.buttonBar.setLoading(true);
        this.toolbar.setNavigationIcon(0);
    }

    /* access modifiers changed from: private */
    public void onDeclineReservation() {
        ROAnalytics.trackRespondNowSelectOptionForReservation("click_decline", getReservation());
        getNavigator().onDecline();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ReservationRejectionIntro;
    }

    private String getTitle() {
        String nightsPluralStr = getResources().getQuantityString(C0880R.plurals.x_nights, getReservation().getStayDuration(), new Object[]{Integer.valueOf(getReservation().getStayDuration())});
        return getString(C0880R.string.ro_accept_sheet_title, getReservation().getGuest().getFirstName(), nightsPluralStr);
    }

    private String getSubtitle() {
        String price;
        if (getReservation().isDeferredPayment()) {
            return getString(C0880R.string.ro_response_dialog_host_subtitle_reservation_deferred_payment, getReservation().getGuest().getFirstName());
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(getString(C0880R.string.date_name_format));
        AirDate checkInDate = getReservation().getCheckinDate();
        AirDate checkOutDate = getReservation().getCheckoutDate();
        if (getReservation().getHostPayoutBreakdown() != null) {
            price = getReservation().getHostPayoutBreakdown().getTotal().formattedForDisplay();
        } else {
            price = this.mCurrencyHelper.formatNativeCurrency((double) getReservation().getPayoutPriceNative(), true);
        }
        return getString(C0880R.string.ro_response_landing_page_subtitle, getReservation().getGuest().getFirstName(), getReservation().getListing().getName(), getReservation().getListing().getCity(), checkInDate.formatDate((DateFormat) dateFormat), checkOutDate.formatDate((DateFormat) dateFormat), price);
    }
}
