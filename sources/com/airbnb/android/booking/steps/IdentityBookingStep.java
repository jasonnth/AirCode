package com.airbnb.android.booking.steps;

import android.content.Intent;
import android.os.Bundle;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.booking.controller.BookingController;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.analytics.AccountVerificationAnalytics;
import com.airbnb.android.core.arguments.AccountVerificationStartFragmentArguments;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.identity.FetchIdentityController;
import com.airbnb.android.core.identity.FetchIdentityController.MultiVerificationFlowListener;
import com.airbnb.android.core.identity.IdentityVerificationUtil;
import com.airbnb.android.core.intents.AccountVerificationActivityIntents;
import com.airbnb.android.core.intents.AccountVerificationStartActivityIntents;
import com.airbnb.android.core.models.AccountVerification;
import com.airbnb.android.core.models.FreezeDetails;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.NetworkUtil;
import com.google.common.collect.FluentIterable;
import icepick.State;
import java.util.ArrayList;
import java.util.HashMap;

public class IdentityBookingStep implements ActivityBookingStep {
    protected static final int RC_BACK = 1003;
    private static final int REQUEST_CODE_VERIFY_IDENTITY = 992;
    @State
    boolean completed;
    /* access modifiers changed from: private */
    public final BookingController controller;
    private FetchIdentityController fetchIdentityController;
    private final MultiVerificationFlowListener fetchIdentityListener = new MultiVerificationFlowListener() {
        public void onVerificationsFetchComplete(HashMap<VerificationFlow, ArrayList<AccountVerification>> incompleteVerificationsMap) {
            IdentityBookingStep.this.incompleteVerifications = (ArrayList) incompleteVerificationsMap.get(VerificationFlow.Booking);
            IdentityBookingStep.this.checkForIncompleteVerifications();
        }

        public void onVerificationsFetchError(NetworkException e) {
            NetworkUtil.toastGenericNetworkError(IdentityBookingStep.this.controller.getContext());
        }
    };
    @State
    ArrayList<AccountVerification> incompleteVerifications;
    @State
    boolean isInstantBookableIfGovIdProvided;
    @State
    boolean isVerificationFetchComplete;
    @State
    ArrayList<String> selfiePhotoFilePaths;
    @State
    boolean skipped;

    /* access modifiers changed from: private */
    public void checkForIncompleteVerifications() {
        boolean usingIdentityFlow;
        boolean z = false;
        if (this.incompleteVerifications != null && !this.incompleteVerifications.isEmpty() && this.controller.getReservation() != null) {
            boolean isPhoneRegistration = this.controller.getBookingActivityFacade().getAccountManager().getCurrentUser().isPhoneNumberRegisteredUser();
            Reservation reservation = this.controller.getReservation();
            boolean instantBookWithGovId = isInstantBookWithGovId(reservation);
            if (shouldUseIdentityFlowForFrozenReservation(reservation) || instantBookWithGovId) {
                usingIdentityFlow = true;
            } else {
                usingIdentityFlow = false;
            }
            if (usingIdentityFlow) {
                this.incompleteVerifications = new ArrayList<>(FluentIterable.from((Iterable<E>) this.incompleteVerifications).filter(IdentityBookingStep$$Lambda$1.lambdaFactory$(isPhoneRegistration)).filter(IdentityBookingStep$$Lambda$2.lambdaFactory$(isPhoneRegistration)).filter(IdentityBookingStep$$Lambda$3.lambdaFactory$(instantBookWithGovId)).toList());
            } else {
                this.incompleteVerifications = new ArrayList<>(FluentIterable.from((Iterable<E>) this.incompleteVerifications).filter(IdentityBookingStep$$Lambda$4.lambdaFactory$()).toList());
            }
            BookingController bookingController = this.controller;
            if (!this.isVerificationFetchComplete) {
                z = true;
            }
            bookingController.bookingStepInitialized(this, z);
            this.isVerificationFetchComplete = true;
        }
    }

    static /* synthetic */ boolean lambda$checkForIncompleteVerifications$0(boolean isPhoneRegistration, AccountVerification verification) {
        return !"phone".equals(verification.getType()) || !isPhoneRegistration;
    }

    static /* synthetic */ boolean lambda$checkForIncompleteVerifications$1(boolean isPhoneRegistration, AccountVerification verification) {
        return !"email".equals(verification.getType()) || isPhoneRegistration;
    }

    static /* synthetic */ boolean lambda$checkForIncompleteVerifications$2(boolean instantBookWithGovId, AccountVerification verification) {
        return !AccountVerification.SELFIE.equals(verification.getType()) || !instantBookWithGovId;
    }

    static /* synthetic */ boolean lambda$checkForIncompleteVerifications$3(AccountVerification verification) {
        return !verification.isOnlyRequiredForIdentityFlow();
    }

    public IdentityBookingStep(BookingController controller2) {
        this.controller = controller2;
    }

    public void restoreInstanceState(Bundle savedState) {
        if (savedState != null) {
            IcepickWrapper.restoreInstanceState(this, savedState);
        }
        if (this.fetchIdentityController == null) {
            this.fetchIdentityController = new FetchIdentityController(this.controller.getRequestManager(), this.fetchIdentityListener, new VerificationFlow[]{VerificationFlow.Booking, VerificationFlow.VerifiedID, VerificationFlow.FinalizeBooking}, savedState);
        }
        if (this.controller.getReservation() != null) {
            onReservationLoaded();
        }
    }

    public void onReservationLoaded() {
        checkForIncompleteVerifications();
    }

    public void fetchIdentityVerificationState() {
        this.fetchIdentityController.startFetchingIdentityVerificationState(this.controller.getBookingActivityFacade().getAccountManager().getCurrentUserId());
    }

    public void show(boolean isBack) {
        Check.notNull(this.incompleteVerifications);
        Reservation reservation = this.controller.getReservation();
        boolean usingIdentityFlow = shouldUseIdentityFlowForFrozenReservation(reservation) || isInstantBookWithGovId(reservation);
        if (IdentityVerificationUtil.shouldReservationBeFrozen(reservation)) {
            AccountVerificationAnalytics.trackP4VerifiedID(usingIdentityFlow ? InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY : "verified_id", reservation.getFreezeDetails(), reservation.getListing().getId());
        }
        this.controller.setReservationDetails(this.controller.getReservationDetails().toBuilder().requiresVerifications(Boolean.valueOf(true)).usesIdentityFlow(Boolean.valueOf(usingIdentityFlow)).build());
        FreezeDetails freezeDetails = reservation.getFreezeDetails();
        this.controller.getBookingActivityFacade().startActivityForResult(AccountVerificationStartActivityIntents.newIntentForIncompleteVerifications(this.controller.getBookingActivityFacade().getIdentityJitneyLogger(), this.controller.getContext(), AccountVerificationStartFragmentArguments.builder().verificationFlow(freezeDetails.requiredByHost() ? VerificationFlow.VerifiedIDForBookingV2 : VerificationFlow.BookingV2).incompleteVerifications(this.incompleteVerifications).host(reservation.getPrimaryHost()).verificationUser(this.fetchIdentityController.getVerificationUser()).listingId(reservation.getListing().getId()).governmentIdResult(this.fetchIdentityController.getGovernmentIdResult()).firstVerificationStep(null).isMoveToLastStep(isBack).selfiePhotoFilePaths(this.selfiePhotoFilePaths).p4Steps(this.controller.getP4Steps()).reservationFrozenReason(freezeDetails.getReason()).build()), REQUEST_CODE_VERIFY_IDENTITY);
    }

    private boolean shouldUseIdentityFlowForFrozenReservation(Reservation reservation) {
        return IdentityVerificationUtil.shouldUseIdentityFlowForFrozenReservation(reservation, this.fetchIdentityController.getVerificationUser());
    }

    private boolean isInstantBookWithGovId(Reservation reservation) {
        return IdentityVerificationUtil.isInstantBookableIfGovIdProvided(reservation, this.fetchIdentityController.getVerificationUser());
    }

    public boolean exclude() {
        return this.completed || this.skipped || isVerifiedIdEnabled() || this.incompleteVerifications.isEmpty();
    }

    private boolean isVerifiedIdEnabled() {
        Reservation reservation = this.controller.getReservation();
        return reservation.isCheckpointed() && !IdentityVerificationUtil.shouldUseIdentityFlowForFrozenReservation(reservation, this.fetchIdentityController.getVerificationUser());
    }

    public boolean initialized() {
        return (this.incompleteVerifications == null || this.controller.getReservation() == null) ? false : true;
    }

    public int getRequestCode() {
        return REQUEST_CODE_VERIFY_IDENTITY;
    }

    public void onActivityResult(int resultCode, Intent data) {
        if (resultCode == -1) {
            if (data != null) {
                this.selfiePhotoFilePaths = data.getStringArrayListExtra(AccountVerificationActivityIntents.EXTRA_SELFIE_PHOTOS_FILE_PATH);
            }
            if (FeatureToggles.canSkipIdentity()) {
                this.completed = true;
            }
            this.controller.setReservationDetails(this.controller.getReservationDetails().toBuilder().requiresVerifications(Boolean.valueOf(false)).build());
            this.controller.showNextStep(this.controller.getDefaultBookingStepLoader());
        } else if (resultCode == 0) {
            if (data == null || !data.getBooleanExtra(IdentityVerificationUtil.ARG_SKIPPED, false)) {
                this.controller.showPreviousStep();
                return;
            }
            this.skipped = true;
            this.controller.showNextStep(this.controller.getDefaultBookingStepLoader());
        } else if (resultCode == 1003) {
            show(false);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        IcepickWrapper.saveInstanceState(this, outState);
        this.fetchIdentityController.onSaveInstanceState(outState);
    }

    public User getVerificationUser() {
        return this.fetchIdentityController.getVerificationUser();
    }
}
