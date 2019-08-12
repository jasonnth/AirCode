package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.Adapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.ReservationCancellationLogger;
import com.airbnb.android.core.enums.ReservationCancellationReason;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationCancellationInfo;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.ReasonPickerFragment.ReasonPickerDataProvider;
import com.airbnb.android.lib.fragments.managelisting.handlers.ReasonPickerAdapter.ReasonPickerCallback;
import com.airbnb.android.lib.fragments.managelisting.handlers.ReservationCancellationConfirmationInputAdapter;
import com.airbnb.android.lib.fragments.managelisting.handlers.ReservationCancellationKnowMoreAdapter;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import icepick.State;

public class ReservationCancellationWithUserInputFragment extends AirFragment {
    private static final String EXPLANATION_STRING_KEY = "explanation_string";
    private static final String INPUT_REASON_KEY = "input_reason";
    private static final String RESERVATION_CANCELLATION_INFO_KEY = "reservation_cancellation_info";
    @State
    String explanationString;
    @State
    InputReason inputReason;
    ReservationCancellationLogger logger;
    @BindView
    AirButton nextButton;
    @BindView
    RecyclerView recyclerView;
    private ReservationCancellationInfo reservationCancellationInfo;
    @BindView
    AirToolbar toolbar;

    public enum InputReason {
        KnowMore,
        Confirmation
    }

    public interface ReservationCancellationWithUserInputController {
        void editUserInputClicked(InputReason inputReason, String str);
    }

    private ReasonPickerDataProvider getReasonPickerDataProvider() {
        Check.state(getActivity() instanceof ReasonPickerDataProvider);
        return (ReasonPickerDataProvider) getActivity();
    }

    private ReservationCancellationWithUserInputController getReservationCancellationWithUserInputController() {
        Check.state(getActivity() instanceof ReservationCancellationWithUserInputController);
        return (ReservationCancellationWithUserInputController) getActivity();
    }

    private ReasonPickerCallback getReasonPickerCallback() {
        Check.state(getActivity() instanceof ReasonPickerCallback);
        return (ReasonPickerCallback) getActivity();
    }

    public static ReservationCancellationWithUserInputFragment newKnowMoreInstance(ReservationCancellationInfo reservationCancellationInfo2) {
        return newKnowMoreInstance(reservationCancellationInfo2, null);
    }

    public static ReservationCancellationWithUserInputFragment newKnowMoreInstance(ReservationCancellationInfo reservationCancellationInfo2, String explanationString2) {
        return (ReservationCancellationWithUserInputFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new ReservationCancellationWithUserInputFragment()).putSerializable(INPUT_REASON_KEY, InputReason.KnowMore)).putParcelable(RESERVATION_CANCELLATION_INFO_KEY, reservationCancellationInfo2)).putString(EXPLANATION_STRING_KEY, explanationString2)).build();
    }

    public static ReservationCancellationWithUserInputFragment newConfirmationInstance(ReservationCancellationInfo reservationCancellationInfo2) {
        return newConfirmationInstance(reservationCancellationInfo2, null);
    }

    public static ReservationCancellationWithUserInputFragment newConfirmationInstance(ReservationCancellationInfo reservationCancellationInfo2, String explanationString2) {
        return (ReservationCancellationWithUserInputFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new ReservationCancellationWithUserInputFragment()).putSerializable(INPUT_REASON_KEY, InputReason.Confirmation)).putParcelable(RESERVATION_CANCELLATION_INFO_KEY, reservationCancellationInfo2)).putString(EXPLANATION_STRING_KEY, explanationString2)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) CoreApplication.instance(getContext()).component()).inject(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_reason_picker, container, false);
        ButterKnife.bind(this, view);
        setToolbar(this.toolbar);
        this.inputReason = (InputReason) getArguments().getSerializable(INPUT_REASON_KEY);
        if (getArguments().containsKey(EXPLANATION_STRING_KEY)) {
            this.explanationString = getArguments().getString(EXPLANATION_STRING_KEY);
        }
        this.reservationCancellationInfo = (ReservationCancellationInfo) getArguments().getParcelable(RESERVATION_CANCELLATION_INFO_KEY);
        this.nextButton.setText(getReasonPickerDataProvider().getNextButtonText(getReservationCancellationReason()));
        this.nextButton.setEnabled(!TextUtils.isEmpty(this.explanationString));
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setVerticalScrollBarEnabled(false);
        this.recyclerView.setAdapter(getAdapter());
        this.logger.onReasonSelectedWithNote(getReservation(), getReservationCancellationReason(), this.explanationString);
        return view;
    }

    private Reservation getReservation() {
        return getReasonPickerDataProvider().getReservation();
    }

    private Adapter getAdapter() {
        if (this.inputReason == InputReason.KnowMore) {
            return new ReservationCancellationKnowMoreAdapter(getReasonPickerCallback(), this.reservationCancellationInfo, getReservationCancellationWithUserInputController(), this.explanationString);
        }
        return new ReservationCancellationConfirmationInputAdapter(getReasonPickerCallback(), this.reservationCancellationInfo, getReservationCancellationWithUserInputController(), this.explanationString, getContext(), getReservation().getGuest());
    }

    private ReservationCancellationReason getReservationCancellationReason() {
        if (this.inputReason == InputReason.Confirmation) {
            return ReservationCancellationReason.ConfirmationNote;
        }
        return ReservationCancellationReason.Other;
    }

    @OnClick
    public void clickNext() {
        getReasonPickerCallback().onHostMessageUpdate(this.inputReason, this.explanationString);
        getReasonPickerCallback().onCancelReservationClicked(getReservationCancellationReason(), false);
    }

    public NavigationTag getNavigationTrackingTag() {
        return ReservationCancellationLogger.getNavigationTag(getReservationCancellationReason(), hasExplanation());
    }

    private boolean hasExplanation() {
        return !TextUtils.isEmpty(this.explanationString);
    }

    public Strap getNavigationTrackingParams() {
        Reservation reservation = getReservation();
        return Strap.make().mo11639kv("event_data", getNavigationTrackingTag().trackingName).mo11638kv("listing_id", reservation.getListing().getId()).mo11638kv("reservation_id", reservation.getId()).mo11639kv("note", this.explanationString);
    }
}
