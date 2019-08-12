package com.airbnb.android.core.identity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.analytics.AccountVerificationAnalytics;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.identity.FetchIdentityController.MultiVerificationFlowListener;
import com.airbnb.android.core.models.AccountVerification;
import com.airbnb.android.core.models.GovernmentIdResult;
import com.airbnb.android.core.models.GovernmentIdResult.Status;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.requests.GetGovernmentIdResultsRequest;
import com.airbnb.android.core.responses.GovernmentIdResultsResponse;
import com.airbnb.android.core.utils.Check;
import com.google.common.collect.FluentIterable;
import java.util.ArrayList;
import java.util.HashMap;
import p032rx.Observer;

public class IdentityClient implements MultiVerificationFlowListener {
    private static final int GOVERNMENT_ID_RESULT_CHECK_INTERVAL = 5000;
    private Context context;
    private FetchIdentityController controller;
    final RequestListener<GovernmentIdResultsResponse> fetchGovernmentIdResultsListenerForSubmit = new C0699RL().onResponse(IdentityClient$$Lambda$1.lambdaFactory$(this)).onError(IdentityClient$$Lambda$2.lambdaFactory$(this)).build();
    private final Handler handler = new Handler();
    private IdentityClientListener listener;
    private RequestManager requestManager;
    private Reservation reservation;

    public interface IdentityClientListener {
        void identityCheckError(NetworkException networkException);

        void identityCheckSuccess();

        void showErrorMessage(String str, String str2, String str3);

        void showPendingMessage(String str, String str2);
    }

    static /* synthetic */ void lambda$new$0(IdentityClient identityClient, GovernmentIdResultsResponse response) {
        GovernmentIdResult latestResult = response.getLatestResult();
        if ((latestResult == null ? null : latestResult.getStatusFromString()) != Status.Awaiting) {
            identityClient.listener.identityCheckSuccess();
        } else {
            identityClient.handler.postDelayed(IdentityClient$$Lambda$8.lambdaFactory$(identityClient), 5000);
        }
    }

    public void doIdentityCheck(Context context2, RequestManager requestManager2, Reservation reservation2, IdentityClientListener listener2) {
        this.context = context2;
        this.requestManager = requestManager2;
        this.reservation = reservation2;
        this.listener = listener2;
        Check.notNull(listener2);
        this.controller = new FetchIdentityController(requestManager2, (MultiVerificationFlowListener) this, new VerificationFlow[]{VerificationFlow.Booking, VerificationFlow.VerifiedID, VerificationFlow.FinalizeBooking}, (Bundle) null);
        this.controller.startFetchingIdentityVerificationState(reservation2.getGuest().getId());
    }

    /* access modifiers changed from: private */
    public void checkGovernmentIdResult() {
        new GetGovernmentIdResultsRequest(this.reservation.getGuest().getId()).withListener((Observer) this.fetchGovernmentIdResultsListenerForSubmit).execute(this.requestManager);
    }

    private boolean shouldUseIdentityFlowForFrozenReservation(Reservation reservation2) {
        return IdentityVerificationUtil.shouldUseIdentityFlowForFrozenReservation(reservation2, this.controller.getVerificationUser());
    }

    private boolean isInstantBookWithGovId(Reservation reservation2) {
        return IdentityVerificationUtil.isInstantBookableIfGovIdProvided(reservation2, this.controller.getVerificationUser());
    }

    private boolean isVerifiedIdEnabled() {
        return this.reservation.isCheckpointed() && !IdentityVerificationUtil.shouldUseIdentityFlowForFrozenReservation(this.reservation, this.controller.getVerificationUser());
    }

    public void onVerificationsFetchComplete(HashMap<VerificationFlow, ArrayList<AccountVerification>> incompleteVerificationsMap) {
        ArrayList<AccountVerification> incompleteVerifications;
        ArrayList<AccountVerification> incompleteVerifications2 = (ArrayList) incompleteVerificationsMap.get(VerificationFlow.Booking);
        boolean isPhoneRegistration = this.reservation.getGuest().isPhoneNumberRegisteredUser();
        boolean instantBookWithGovId = isInstantBookWithGovId(this.reservation);
        if (shouldUseIdentityFlowForFrozenReservation(this.reservation) || instantBookWithGovId) {
            incompleteVerifications = new ArrayList<>(FluentIterable.from((Iterable<E>) incompleteVerifications2).filter(IdentityClient$$Lambda$3.lambdaFactory$(isPhoneRegistration)).filter(IdentityClient$$Lambda$4.lambdaFactory$(isPhoneRegistration)).filter(IdentityClient$$Lambda$5.lambdaFactory$(instantBookWithGovId)).toList());
        } else {
            incompleteVerifications = new ArrayList<>(FluentIterable.from((Iterable<E>) incompleteVerifications2).filter(IdentityClient$$Lambda$6.lambdaFactory$()).toList());
        }
        if (isVerifiedIdEnabled() || incompleteVerifications.isEmpty()) {
            this.listener.identityCheckSuccess();
            return;
        }
        Status status = this.controller.getGovernmentIdResult() == null ? null : this.controller.getGovernmentIdResult().getStatusFromString();
        GovernmentIdResult latestResult = this.controller.getGovernmentIdResult();
        if (IdentityVerificationUtil.isInstantBookableIfGovIdProvided(this.reservation, this.controller.getVerificationUser()) && status == Status.Awaiting) {
            this.listener.showPendingMessage(this.context.getString(C0716R.string.account_verification_pending_warning), this.context.getString(C0716R.string.p4_required_government_id_pending));
            this.handler.postDelayed(IdentityClient$$Lambda$7.lambdaFactory$(this), 5000);
        } else if (IdentityVerificationUtil.shouldUseIdentityFlowForFrozenReservation(this.reservation, this.controller.getVerificationUser()) && status == Status.Denied) {
            AccountVerificationAnalytics.trackP4VerificationBlock(latestResult);
            this.listener.showErrorMessage(latestResult.getLocalizedDenialReasonTitle(), latestResult.getLocalizedDenialReason(), this.context.getString(C0716R.string.account_verification_upload_id_again));
        } else if (!incompleteVerifications.isEmpty()) {
            this.listener.showErrorMessage(null, this.context.getString(C0716R.string.account_verification_p4_confirm_details_desc), this.context.getString(C0716R.string.account_verification_p4_confirm_details));
        } else {
            this.listener.identityCheckSuccess();
        }
    }

    static /* synthetic */ boolean lambda$onVerificationsFetchComplete$2(boolean isPhoneRegistration, AccountVerification verification) {
        return !"phone".equals(verification.getType()) || !isPhoneRegistration;
    }

    static /* synthetic */ boolean lambda$onVerificationsFetchComplete$3(boolean isPhoneRegistration, AccountVerification verification) {
        return !"email".equals(verification.getType()) || isPhoneRegistration;
    }

    static /* synthetic */ boolean lambda$onVerificationsFetchComplete$4(boolean instantBookWithGovId, AccountVerification verification) {
        return !AccountVerification.SELFIE.equals(verification.getType()) || !instantBookWithGovId;
    }

    static /* synthetic */ boolean lambda$onVerificationsFetchComplete$5(AccountVerification verification) {
        return !verification.isOnlyRequiredForIdentityFlow();
    }

    public void onVerificationsFetchError(NetworkException e) {
        this.listener.identityCheckError(e);
    }
}
