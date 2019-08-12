package com.airbnb.android.lib.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p002v7.widget.RecyclerView.Adapter;
import butterknife.ButterKnife;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.activities.SolitAirActivity;
import com.airbnb.android.core.cancellation.host.HostCancellationParams;
import com.airbnb.android.core.cancellation.host.HostCancellationParams.Builder;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.enums.ReservationCancellationReason;
import com.airbnb.android.core.fragments.EditTextFragment;
import com.airbnb.android.core.fragments.EditTextFragment.EditTextFragmentBuilder;
import com.airbnb.android.core.fragments.EditTextFragment.EditTextFragmentController;
import com.airbnb.android.core.fragments.EditTextFragment.EditTextFragmentListener;
import com.airbnb.android.core.intents.InboxActivityIntents;
import com.airbnb.android.core.intents.ManageListingIntents;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.Incentive;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationCancellationInfo;
import com.airbnb.android.core.responses.ReservationCancellationInfoResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.SettingDeepLink;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.cancellation.host.LateCancellationFragment;
import com.airbnb.android.lib.fragments.HostReservationObjectFragment;
import com.airbnb.android.lib.fragments.ReasonPickerFragment;
import com.airbnb.android.lib.fragments.ReasonPickerFragment.ReasonPickerDataProvider;
import com.airbnb.android.lib.fragments.ReservationCanceledFragment;
import com.airbnb.android.lib.fragments.ReservationCancellationWithUserInputFragment;
import com.airbnb.android.lib.fragments.ReservationCancellationWithUserInputFragment.InputReason;
import com.airbnb.android.lib.fragments.ReservationCancellationWithUserInputFragment.ReservationCancellationWithUserInputController;
import com.airbnb.android.lib.fragments.managelisting.handlers.ReasonPickerAdapter.ReasonPickerCallback;
import com.airbnb.android.lib.fragments.managelisting.handlers.ReservationCancellationAntiDiscriminationAdapter;
import com.airbnb.android.lib.fragments.managelisting.handlers.ReservationCancellationCustomPenaltyAdapter;
import com.airbnb.android.lib.fragments.managelisting.handlers.ReservationCancellationDifferentPriceAdapter;
import com.airbnb.android.lib.fragments.managelisting.handlers.ReservationCancellationEmergencyPolicyAdapter;
import com.airbnb.android.lib.fragments.managelisting.handlers.ReservationCancellationFollowUpAdapter;
import com.airbnb.android.lib.fragments.managelisting.handlers.ReservationCancellationGuestCancelAdapter;
import com.airbnb.android.lib.fragments.managelisting.handlers.ReservationCancellationGuestEmpathyAdapter;
import com.airbnb.android.lib.fragments.managelisting.handlers.ReservationCancellationMainReasonsAdapter;
import com.airbnb.android.lib.fragments.managelisting.handlers.ReservationCancellationMissedEarningsAdapter;
import com.airbnb.android.lib.fragments.managelisting.handlers.ReservationCancellationPenaltyFreeTrialAdapter;
import com.airbnb.android.lib.fragments.managelisting.handlers.ReservationCancellationPlaceUnavailableAdapter;
import com.airbnb.android.lib.fragments.managelisting.handlers.ReservationCancellationReachPenaltyLimitAdapter;
import com.airbnb.android.lib.fragments.managelisting.handlers.ReservationCancellationReviewPenaltiesAdapter;
import com.airbnb.android.lib.fragments.managelisting.handlers.ReservationCancellationUncomfortableWithGuestAdapter;
import com.airbnb.android.lib.requests.ReservationCancellationInfoRequest;
import com.airbnb.android.utils.ListUtils;
import icepick.State;
import java.util.Collection;
import p032rx.Observer;

public class ReservationCancellationActivity extends SolitAirActivity implements EditTextFragmentController, EditTextFragmentListener, ReasonPickerDataProvider, ReservationCancellationWithUserInputController, ReasonPickerCallback {
    private static final int ADDITIONAL_INFO_MAX_LENGTH = 239;
    private static final String ARG_RESERVATION = "reservation";
    private static final String LOADING_FRAGMENT_TAG = "reason_picker_fragment_loading";
    private static final String NONDISCRIMINATION_POLICY_URL = "https://www.airbnb.com/help/article/1405/airbnb-s-nondiscrimination-policy--our-commitment-to-inclusion-and-respect";
    final NonResubscribableRequestListener<ReservationCancellationInfoResponse> cancellationInfoRequestListener = new C0699RL().onResponse(ReservationCancellationActivity$$Lambda$1.lambdaFactory$(this)).onError(ReservationCancellationActivity$$Lambda$2.lambdaFactory$(this)).buildWithoutResubscription();
    @State
    HostCancellationParams cancellationParams;
    @State
    ReservationCancellationReason cancellationReason;
    @State
    InputReason inputReason;
    @State
    Reservation reservation;
    @State
    ReservationCancellationInfo reservationCancellationInfo;

    public static Intent intentForReservation(Context context, Reservation reservation2) {
        Intent intent = new Intent(context, ReservationCancellationActivity.class);
        intent.putExtra("reservation", reservation2);
        return intent;
    }

    static /* synthetic */ void lambda$new$0(ReservationCancellationActivity reservationCancellationActivity, ReservationCancellationInfoResponse r) {
        reservationCancellationActivity.reservationCancellationInfo = r.reservationCancellationInfo;
        ReasonPickerFragment reasonPickerFragment = reservationCancellationActivity.getLoadingReasonPickerFragment();
        if (reasonPickerFragment != null) {
            reasonPickerFragment.finishLoadingRefreshAdapter();
        }
    }

    static /* synthetic */ void lambda$new$1(ReservationCancellationActivity reservationCancellationActivity, AirRequestNetworkException e) {
        ReasonPickerFragment reasonPickerFragment = reservationCancellationActivity.getLoadingReasonPickerFragment();
        if (reasonPickerFragment != null) {
            reasonPickerFragment.finishLoadingRefreshAdapter();
        }
        NetworkUtil.tryShowErrorWithSnackbar(reasonPickerFragment.getView(), e);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_simple_fragment);
        ButterKnife.bind((Activity) this);
        if (savedInstanceState == null) {
            this.reservation = (Reservation) getIntent().getParcelableExtra("reservation");
            ReasonPickerFragment fragment = new ReasonPickerFragment();
            this.cancellationParams = HostCancellationParams.builder().build();
            if (this.reservation.getCheckinDate().isAfter(AirDate.today())) {
                showFragment(fragment, C0880R.C0882id.content_container, FragmentTransitionType.SlideInFromSide, true, LOADING_FRAGMENT_TAG);
                fragment.setLoading(true);
                new ReservationCancellationInfoRequest(this.reservation.getConfirmationCode()).withListener((Observer) this.cancellationInfoRequestListener).execute(this.requestManager);
                return;
            }
            showFragment(LateCancellationFragment.newInstance(), C0880R.C0882id.content_container, FragmentTransitionType.SlideInFromSide, true);
        }
    }

    private ReasonPickerFragment getLoadingReasonPickerFragment() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(LOADING_FRAGMENT_TAG);
        if (fragment == null) {
            return null;
        }
        Check.state(fragment instanceof ReasonPickerFragment);
        return (ReasonPickerFragment) fragment;
    }

    private void startActivityForDeepLink(SettingDeepLink settingDeepLink) {
        startActivity(ManageListingIntents.intentForExistingListingSetting(this, this.reservation.getListing().getId(), settingDeepLink));
    }

    public void onKeepReservationClicked() {
        setResult(0);
        finish();
    }

    public void onReasonClicked(ReservationCancellationReason reason, boolean hideCancellationFee) {
        Fragment fragment;
        if (reason == ReservationCancellationReason.ChangeReservation) {
            HostReservationObjectFragment.startReservationAlterationFlow(this, this.reservation);
        } else if (reason == ReservationCancellationReason.GoodGuest) {
            startActivityForDeepLink(SettingDeepLink.InstantBook);
        } else if (reason == ReservationCancellationReason.ClearExpecation) {
            startActivityForDeepLink(SettingDeepLink.HouseRules);
        } else {
            if (ReservationCancellationReason.isTopLevelReason(reason)) {
                this.cancellationReason = reason;
            }
            populateCancellationParams(reason);
            switch (reason) {
                case Unavailable:
                case PriceOrTripLength:
                case GuestCancellation:
                case Emergency:
                    fragment = ReasonPickerFragment.newInstanceWithState(reason, hideCancellationFee);
                    break;
                case Concerned:
                    fragment = ReasonPickerFragment.newInstanceWithState(this.reservationCancellationInfo.isHostBehaviorReasonLimitReached() ? ReservationCancellationReason.IbPenaltyReachLimit : ReservationCancellationReason.AntiDiscrimination, hideCancellationFee);
                    break;
                case AntiDiscrimination:
                    fragment = ReasonPickerFragment.newInstanceWithState(ReservationCancellationReason.Concerned, hideCancellationFee);
                    break;
                case Other:
                case OtherGuestConcerns:
                    fragment = ReservationCancellationWithUserInputFragment.newKnowMoreInstance(this.reservationCancellationInfo);
                    break;
                default:
                    fragment = getReviewPenaltiesFragment(reason, hideCancellationFee);
                    break;
            }
            showFragment(fragment, C0880R.C0882id.content_container, FragmentTransitionType.SlideInFromSide, true);
        }
    }

    public void onViewNondiscriminationPolicyClicked() {
        startActivity(WebViewIntentBuilder.newBuilder(getApplicationContext(), NONDISCRIMINATION_POLICY_URL).toIntent());
    }

    public void onShowModal(ReservationCancellationReason reason) {
        showModal(ReasonPickerFragment.newModalInstanceWithState(reason, true), C0880R.C0882id.content_container, C0880R.C0882id.modal_container, true);
    }

    public void onHostMessageUpdate(InputReason inputReason2, String message) {
        if (inputReason2 == InputReason.KnowMore) {
            Builder builder = this.cancellationParams.toBuilder();
            if (message.length() >= ADDITIONAL_INFO_MAX_LENGTH) {
                message = message.substring(0, ADDITIONAL_INFO_MAX_LENGTH);
            }
            this.cancellationParams = builder.additionalInfo(message).build();
            return;
        }
        this.cancellationParams = this.cancellationParams.toBuilder().message(message).build();
    }

    private Fragment getReviewPenaltiesFragment(ReservationCancellationReason inputReason2, boolean hideCancellationFee) {
        ReservationCancellationReason outputReason;
        if (this.reservationCancellationInfo == null) {
            return null;
        }
        if ((inputReason2 == ReservationCancellationReason.GuestBadReview || inputReason2 == ReservationCancellationReason.GuestBrokeHouseRules) && !ListUtils.isEmpty((Collection<?>) this.reservationCancellationInfo.getCustomCancellationPenaltyMobile())) {
            outputReason = ReservationCancellationReason.CustomPenalty;
            hideCancellationFee = true;
        } else if (hasPenaltyFreeCancellationTrial()) {
            outputReason = ReservationCancellationReason.PenaltyFreeTrial;
            hideCancellationFee = true;
        } else {
            outputReason = ReservationCancellationReason.ReviewPenalties;
        }
        return ReasonPickerFragment.newInstanceWithState(outputReason, hideCancellationFee);
    }

    private void startGuestMessagingActivity() {
        startActivity(InboxActivityIntents.intentForInbox(getApplicationContext(), InboxType.Host));
    }

    public void onCancelReservationClicked(ReservationCancellationReason reason, boolean hideCancellationFee) {
        Fragment fragment;
        if (reason == ReservationCancellationReason.GuestCancellation || reason == ReservationCancellationReason.IbPenaltyReachLimit) {
            startGuestMessagingActivity();
        } else if (reason == ReservationCancellationReason.ChangeReservation) {
            HostReservationObjectFragment.startReservationAlterationFlow(this, this.reservation);
        } else {
            switch (reason) {
                case Emergency:
                case Other:
                case OtherGuestConcerns:
                case DifferentTripLength:
                case DifferentPrice:
                    fragment = getReviewPenaltiesFragment(ReservationCancellationReason.ReviewPenalties, hideCancellationFee);
                    break;
                case AntiDiscrimination:
                    fragment = ReasonPickerFragment.newInstanceWithState(ReservationCancellationReason.Concerned, hideCancellationFee);
                    break;
                case GuestBadReview:
                case GuestBrokeHouseRules:
                    fragment = getReviewPenaltiesFragment(reason, true);
                    break;
                case PenaltyFreeTrial:
                case CustomPenalty:
                case ReviewPenalties:
                    fragment = ReasonPickerFragment.newInstanceWithState(ReservationCancellationReason.MissedEarnings, hideCancellationFee);
                    break;
                case MissedEarnings:
                    fragment = ReasonPickerFragment.newInstanceWithState(ReservationCancellationReason.GuestEmpathy, hideCancellationFee);
                    break;
                case ConfirmationNote:
                    fragment = ReservationCanceledFragment.newInstance(this.reservation, this.reservationCancellationInfo, this.cancellationParams);
                    break;
                case Canceled:
                    fragment = ReasonPickerFragment.newInstanceWithState(ReservationCancellationReason.FollowUp, hideCancellationFee);
                    break;
                default:
                    fragment = ReservationCancellationWithUserInputFragment.newConfirmationInstance(this.reservationCancellationInfo);
                    break;
            }
            showFragment(fragment, C0880R.C0882id.content_container, FragmentTransitionType.SlideInFromSide, true);
        }
    }

    public Adapter getReasonsAdapter(ReservationCancellationReason reason, boolean hideCancellationFee, boolean isModal) {
        if (reason == null) {
            return new ReservationCancellationMainReasonsAdapter(this, this.reservationCancellationInfo, this.reservation, (this.reservationCancellationInfo == null || this.reservationCancellationInfo.getReservationToCancelInfo() == null || !this.reservationCancellationInfo.getReservationToCancelInfo().isAllowHostBehaviorReason()) ? false : true);
        }
        switch (reason) {
            case Unavailable:
                return new ReservationCancellationPlaceUnavailableAdapter(this, this.reservationCancellationInfo);
            case PriceOrTripLength:
                return new ReservationCancellationDifferentPriceAdapter(this, this.reservationCancellationInfo);
            case GuestCancellation:
                return new ReservationCancellationGuestCancelAdapter(this, this.reservationCancellationInfo, getApplicationContext(), this.reservation.getGuest());
            case Emergency:
                return new ReservationCancellationEmergencyPolicyAdapter(this, this.reservationCancellationInfo);
            case Concerned:
                return new ReservationCancellationUncomfortableWithGuestAdapter(this, this.reservationCancellationInfo);
            case AntiDiscrimination:
                return new ReservationCancellationAntiDiscriminationAdapter(this, this.reservationCancellationInfo, getApplicationContext(), this.reservation.getGuest());
            case PenaltyFreeTrial:
                return new ReservationCancellationPenaltyFreeTrialAdapter(this, this.reservationCancellationInfo, this.reservation);
            case CustomPenalty:
                return new ReservationCancellationCustomPenaltyAdapter(this, this.reservationCancellationInfo);
            case ReviewPenalties:
                return new ReservationCancellationReviewPenaltiesAdapter(this, getApplicationContext(), this.reservation, this.reservationCancellationInfo, hasPenaltyFreeCancellationTrial(), isModal);
            case MissedEarnings:
                return new ReservationCancellationMissedEarningsAdapter(this, this.reservationCancellationInfo, hideCancellationFee);
            case GuestEmpathy:
                return new ReservationCancellationGuestEmpathyAdapter(this, getApplicationContext(), this.reservation.getGuest(), this.reservationCancellationInfo);
            case IbPenaltyReachLimit:
                return new ReservationCancellationReachPenaltyLimitAdapter(this, this.reservationCancellationInfo);
            case FollowUp:
                return new ReservationCancellationFollowUpAdapter(this, this.reservationCancellationInfo);
            default:
                BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Invalid ReservationCancellationReason for getting reason adapter" + reason));
                return null;
        }
    }

    public void populateCancellationParams(ReservationCancellationReason reason) {
        if (reason != null) {
            Builder builder = this.cancellationParams.toBuilder();
            if (reason.getServerKey() == null) {
                reason = ReservationCancellationReason.Other;
            }
            this.cancellationParams = builder.reason(reason.getServerKey()).build();
        }
    }

    public boolean shouldShowNextButton(ReservationCancellationReason reason) {
        return (reason == null || (reason == ReservationCancellationReason.ReviewPenalties && hasPenaltyFreeCancellationTrial()) || reason == ReservationCancellationReason.Unavailable || reason == ReservationCancellationReason.PriceOrTripLength || reason == ReservationCancellationReason.Concerned || reason == ReservationCancellationReason.Other || reason == ReservationCancellationReason.FollowUp) ? false : true;
    }

    public String getNextButtonText(ReservationCancellationReason reason) {
        if (reason == ReservationCancellationReason.GuestCancellation || reason == ReservationCancellationReason.IbPenaltyReachLimit) {
            return getString(C0880R.string.reservation_cancellation_guest_cancel_text, new Object[]{this.reservation.getGuest().getFirstName()});
        } else if (reason == ReservationCancellationReason.ConfirmationNote) {
            return getString(C0880R.string.cancellation_confirmation_button_text);
        } else {
            return getString(C0880R.string.next);
        }
    }

    public EditTextFragmentListener getEditTextFragmentListener() {
        return this;
    }

    public void onMessageSaved(String message) {
        Fragment fragment;
        Check.notNull(this.inputReason);
        if (this.inputReason == InputReason.KnowMore) {
            fragment = ReservationCancellationWithUserInputFragment.newKnowMoreInstance(this.reservationCancellationInfo, message);
        } else {
            fragment = ReservationCancellationWithUserInputFragment.newConfirmationInstance(this.reservationCancellationInfo, message);
        }
        showFragment(fragment, C0880R.C0882id.content_container, FragmentTransitionType.SlideInFromSide, true);
    }

    public void editUserInputClicked(InputReason inputReason2, String explanationText) {
        EditTextFragment editTextFragment;
        Check.notNull(inputReason2);
        this.inputReason = inputReason2;
        if (inputReason2 == InputReason.KnowMore) {
            editTextFragment = new EditTextFragmentBuilder().showHeader(true).setHeaderTitle(getApplicationContext().getString(C0880R.string.reservation_cancellation_not_share_with_guest)).setText(explanationText).build();
        } else {
            editTextFragment = new EditTextFragmentBuilder().showHeader(true).setHeaderTitle(getApplicationContext().getString(C0880R.string.reservation_cancellation_confrimation_input_personal_note, new Object[]{this.reservation.getGuest().getFirstName()})).setText(explanationText).setUser(this.reservation.getGuest()).build();
        }
        showFragment(editTextFragment, C0880R.C0882id.content_container, FragmentTransitionType.SlideInFromSide, true);
    }

    public boolean shouldShowDoneMenu(ReservationCancellationReason reason) {
        return reason == ReservationCancellationReason.FollowUp;
    }

    public Reservation getReservation() {
        return this.reservation;
    }

    private boolean hasPenaltyFreeCancellationTrial() {
        return this.reservation.hasIncentive(Incentive.PENALTY_FREE_CANCELLATION_TRIAL);
    }
}
