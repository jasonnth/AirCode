package com.airbnb.android.lib.fragments.reservationresponse;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.enums.DeclineReason;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.reservationresponse.ReservationResponseBaseFragment.AnalyticsParams;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;

public class ReservationDeclineConfirmationFragment extends ReservationResponseBaseFragment {
    @BindView
    AirTextView confirmationMessageView;
    @BindView
    AirTextView confirmationTitleView;
    @BindView
    AirButton showTipsButton;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_ro_decline_confirmation, container, false);
        bindViews(view);
        this.confirmationTitleView.setText(C0880R.string.ro_response_request_declined);
        this.confirmationMessageView.setText(getDeclineMessage(getReservationResponseActivity().getDeclineReason()));
        if (ListUtils.isEmpty((Collection<?>) getReservation().getRejectionTips())) {
            this.showTipsButton.setVisibility(8);
        }
        return view;
    }

    public Strap getNavigationTrackingParams() {
        return super.getNavigationTrackingParams().mo11639kv(AnalyticsParams.DeclineReason.key, getReservationResponseActivity().getDeclineReason().serverKey);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ReservationRejectionConfirmation;
    }

    @OnClick
    public void onDone() {
        getNavigator().onDoneWithDecline();
    }

    @OnClick
    public void onShowTips() {
        getNavigator().onShowTips();
    }

    private String getDeclineMessage(DeclineReason declineReason) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(getString(C0880R.string.date_name_format));
        return declineReason.getConfirmationMessage(getContext(), getReservation().getCheckinDate().formatDate((DateFormat) dateFormat), getReservation().getCheckoutDate().formatDate((DateFormat) dateFormat), getReservation().getGuest().getFirstName());
    }
}
