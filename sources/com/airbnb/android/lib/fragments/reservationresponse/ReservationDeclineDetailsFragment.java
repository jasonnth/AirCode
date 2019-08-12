package com.airbnb.android.lib.fragments.reservationresponse;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.android.core.enums.DeclineReason;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.reservationresponse.ReservationResponseBaseFragment.AnalyticsParams;
import com.airbnb.android.lib.fragments.reservationresponse.ReservationResponseBaseFragment.MessageType;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.PrimaryButton;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import icepick.State;
import java.io.Serializable;
import java.util.HashMap;

public class ReservationDeclineDetailsFragment extends ReservationResponseBaseFragment {
    private static final String ARG_DECLINE_REASON = "arg_decline_reason";
    @State
    DeclineReason declineReason;
    @State
    boolean isLoading;
    @State
    HashMap<MessageType, String> messages;
    @BindView
    PrimaryButton primaryButton;
    @BindView
    RecyclerView recyclerView;
    private Snackbar snackbar;
    @BindView
    AirToolbar toolbar;

    public static ReservationDeclineDetailsFragment newInstance(DeclineReason reason) {
        return (ReservationDeclineDetailsFragment) ((FragmentBundleBuilder) FragmentBundler.make(new ReservationDeclineDetailsFragment()).putSerializable(ARG_DECLINE_REASON, (Serializable) Check.notNull(reason))).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            this.declineReason = (DeclineReason) getArguments().getSerializable(ARG_DECLINE_REASON);
            this.messages = new HashMap<>();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_ro_decline_landing, container, false);
        ButterKnife.bind(this, view);
        setToolbar(this.toolbar);
        this.recyclerView.setHasFixedSize(true);
        this.primaryButton.setVisibility(0);
        this.primaryButton.setText(C0880R.string.decline_request);
        return view;
    }

    public void onResume() {
        super.onResume();
        this.recyclerView.setAdapter(getReservationResponseActivity().getDeclineDetailsAdapter(this.declineReason));
        updateSubmitState();
    }

    public void onPause() {
        if (this.snackbar != null && this.snackbar.isShownOrQueued()) {
            this.snackbar.dismiss();
            this.snackbar = null;
        }
        super.onPause();
    }

    public void onUpdateStarted() {
        setLoading(true);
    }

    public void onUpdateFinished(boolean isSuccess) {
        setLoading(false);
    }

    @OnClick
    public void onRequestDeclined() {
        MessageType missingMessage = getNextMissingMessage();
        if (missingMessage == null) {
            getNavigator().onRequestDeclined(this.declineReason, getMessage(MessageType.MessageToGuest), getMessage(MessageType.MessageToAirbnb));
        } else {
            this.snackbar = new SnackbarWrapper().view(getView()).body(missingMessage == MessageType.MessageToGuest ? C0880R.string.ro_response_decline_snackbar_guest_message : C0880R.string.ro_response_decline_snackbar_airbnb_message).duration(0).action(C0880R.string.add, ReservationDeclineDetailsFragment$$Lambda$1.lambdaFactory$(this, missingMessage)).buildAndShow();
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ReservationRejectionDeclineReason;
    }

    public Strap getNavigationTrackingParams() {
        return super.getNavigationTrackingParams().mo11639kv(AnalyticsParams.DeclineReason.key, this.declineReason.serverKey);
    }

    public void saveMessage(MessageType messageType, String message) {
        this.messages.put(messageType, message);
    }

    public String getMessage(MessageType messageType) {
        return (String) this.messages.get(messageType);
    }

    private MessageType getNextMissingMessage() {
        if (TextUtils.isEmpty(getMessage(MessageType.MessageToGuest))) {
            return MessageType.MessageToGuest;
        }
        if (!this.declineReason.isPrivateMessageNeeded || !TextUtils.isEmpty(getMessage(MessageType.MessageToAirbnb))) {
            return null;
        }
        return MessageType.MessageToAirbnb;
    }

    private void setLoading(boolean isLoading2) {
        this.isLoading = isLoading2;
        updateSubmitState();
    }

    private void updateSubmitState() {
        if (this.isLoading) {
            this.primaryButton.setLoading();
        } else {
            this.primaryButton.setNormal();
        }
    }
}
