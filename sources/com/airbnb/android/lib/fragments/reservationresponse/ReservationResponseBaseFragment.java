package com.airbnb.android.lib.fragments.reservationresponse;

import android.os.Bundle;
import com.airbnb.android.core.enums.DeclineReason;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.SettingDeepLink;
import com.airbnb.android.lib.activities.ReservationResponseActivity;
import com.airbnb.android.utils.Strap;

public class ReservationResponseBaseFragment extends AirFragment implements ReservationUpdateListener {

    public enum AnalyticsParams {
        ReservationId("reservation_id"),
        ListingId("listing_id"),
        DeclineReason("decline_reason");
        
        public String key;

        private AnalyticsParams(String key2) {
            this.key = key2;
        }
    }

    public enum MessageType {
        MessageToAirbnb,
        MessageToGuest
    }

    public interface ReservationResponseNavigator {
        boolean hasPendingRequest();

        void onAccept();

        void onDecline();

        void onDeclineReasonSelected(DeclineReason declineReason);

        void onDoneWithAccept();

        void onDoneWithDecline();

        void onRequestDeclined(DeclineReason declineReason, String str, String str2);

        void onShowTips();

        void onTipSelected(SettingDeepLink settingDeepLink, String str);

        void showEditTextFragment(MessageType messageType);
    }

    public Reservation getReservation() {
        return getReservationResponseActivity().getReservation();
    }

    public ReservationResponseNavigator getNavigator() {
        return getReservationResponseActivity().getNavigator();
    }

    public ReservationResponseActivity getReservationResponseActivity() {
        Check.state(getActivity() instanceof ReservationResponseActivity);
        return (ReservationResponseActivity) getActivity();
    }

    public void onUpdateStarted() {
    }

    public void onUpdateFinished(boolean isSuccess) {
    }

    public Strap getNavigationTrackingParams() {
        return super.getNavigationTrackingParams().mo11638kv(AnalyticsParams.ReservationId.key, getReservation().getId()).mo11638kv(AnalyticsParams.ListingId.key, getReservation().getListing().getId());
    }

    public void onStart() {
        super.onStart();
        getReservationResponseActivity().addUpdateListener(this);
    }

    public void onStop() {
        getReservationResponseActivity().removeUpdateListener(this);
        super.onStop();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
}
