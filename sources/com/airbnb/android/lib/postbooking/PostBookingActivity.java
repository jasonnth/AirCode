package com.airbnb.android.lib.postbooking;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.businesstravel.BusinessTravelAccountManager;
import com.airbnb.android.core.businesstravel.BusinessTravelUtils;
import com.airbnb.android.core.deeplinks.HomeActivityIntents;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.intents.PostBookingActivityIntents;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.TripTemplate;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.requests.TripTemplatesRequest;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.BatchOperation;
import com.airbnb.android.core.responses.PostHomeBookingResponse;
import com.airbnb.android.core.responses.TripTemplatesResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.explore.requests.PostHomeBookingRequest;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.businesstravel.models.BTMobileSignupPromotion;
import com.airbnb.android.lib.businesstravel.models.BusinessTravelIntentPrediction;
import com.airbnb.android.lib.businesstravel.network.IntentPredictionRequest;
import com.airbnb.android.lib.businesstravel.network.IntentPredictionResponse;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.erf.Experiments;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import icepick.State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PostBookingActivity extends AirActivity implements PostBookingFlowController {
    public final NonResubscribableRequestListener<AirBatchResponse> batchRequestListener = new C0699RL().onResponse(PostBookingActivity$$Lambda$1.lambdaFactory$(this)).onError(PostBookingActivity$$Lambda$2.lambdaFactory$(this)).onComplete(PostBookingActivity$$Lambda$3.lambdaFactory$(this)).buildWithoutResubscription();
    @State
    BTMobileSignupPromotion btMobileSignupPromotion;
    BusinessTravelAccountManager businessTravelAccountManager;
    @State
    boolean canShowMTPostHomeBookingPage;
    @State
    PostBookingState currentState;
    @State
    boolean isBlockedByBatchRequest;
    @State
    boolean isFetchingPostBookingData;
    @State
    Reservation reservation;
    @State
    boolean shouldSendIntentPredictionRequest;
    @State
    ArrayList<TripTemplate> tripSuggestions;

    static /* synthetic */ void lambda$new$0(PostBookingActivity postBookingActivity, Boolean success) {
        postBookingActivity.isFetchingPostBookingData = false;
        if (postBookingActivity.isBlockedByBatchRequest) {
            postBookingActivity.moveToNextStep();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_simple_fragment);
        ((AirbnbGraph) AirbnbApplication.instance(this).component()).inject(this);
        this.reservation = (Reservation) getIntent().getParcelableExtra(PostBookingActivityIntents.ARG_RESERVATION);
        Check.notNull(this.reservation);
        this.shouldSendIntentPredictionRequest = BusinessTravelUtils.shouldSendIntentPredictionRequest(this.businessTravelAccountManager);
        if (savedInstanceState == null) {
            fetchPostBookingData();
            moveToNextStep();
        }
    }

    private void fetchPostBookingData() {
        List<BaseRequestV2<?>> requests;
        TripTemplatesRequest tripTemplatesRequest = TripTemplatesRequest.forReservation(this.reservation).withLimit(10);
        PostHomeBookingRequest phbRequest = PostHomeBookingRequest.newInstance(this.reservation.getConfirmationCode());
        if (this.shouldSendIntentPredictionRequest) {
            requests = Lists.newArrayList((E[]) new BaseRequestV2[]{tripTemplatesRequest, phbRequest, IntentPredictionRequest.withFalsePrediction(this.reservation.getConfirmationCode(), false)});
        } else {
            requests = Lists.newArrayList((E[]) new BaseRequestV2[]{tripTemplatesRequest, phbRequest});
        }
        new AirBatchRequest(requests, this.batchRequestListener).execute(this.requestManager);
        this.isFetchingPostBookingData = true;
    }

    public Reservation getReservation() {
        return this.reservation;
    }

    public ArrayList<TripTemplate> getTripSuggestions() {
        return this.tripSuggestions;
    }

    public BTMobileSignupPromotion getBTMobileSignupPromotion() {
        return this.btMobileSignupPromotion;
    }

    public void onCurrentStateFinished() {
        moveToNextStep();
    }

    public boolean isNextStepReady() {
        if (this.currentState != PostBookingState.Landing) {
            return true;
        }
        return this.isFetchingPostBookingData;
    }

    /* access modifiers changed from: private */
    public void handleBatchResponse(AirBatchResponse airBatchResponse) {
        TripTemplatesResponse tripTemplatesResponse = (TripTemplatesResponse) ((BatchOperation) airBatchResponse.operations.get(0)).convertedResponse;
        if (!ListUtils.isEmpty((Collection<?>) ((PostHomeBookingResponse) ((BatchOperation) airBatchResponse.operations.get(1)).convertedResponse).phb.sections)) {
            this.canShowMTPostHomeBookingPage = true;
        }
        if (this.shouldSendIntentPredictionRequest) {
            this.btMobileSignupPromotion = getBusinessTravelMobileSignupPromotion((IntentPredictionResponse) ((BatchOperation) airBatchResponse.operations.get(2)).convertedResponse);
        }
        this.tripSuggestions = Lists.newArrayList();
        if (!ListUtils.isEmpty((Collection<?>) tripTemplatesResponse.tripTemplates)) {
            this.tripSuggestions.addAll(tripTemplatesResponse.tripTemplates);
        }
    }

    /* access modifiers changed from: private */
    public void handleBatchResponseError(NetworkException networkException) {
        this.tripSuggestions = Lists.newArrayList();
        this.canShowMTPostHomeBookingPage = false;
    }

    private BTMobileSignupPromotion getBusinessTravelMobileSignupPromotion(IntentPredictionResponse response) {
        BusinessTravelIntentPrediction intentPrediction = (BusinessTravelIntentPrediction) FluentIterable.from((Iterable<E>) response.intentPredictions).firstMatch(PostBookingActivity$$Lambda$4.lambdaFactory$()).orNull();
        if (intentPrediction != null) {
            return intentPrediction.getBTMobileSignupPromotion();
        }
        return null;
    }

    private void moveToNextStep() {
        this.currentState = getNextState();
        switch (this.currentState) {
            case Landing:
                showFragmentForCurrentState();
                return;
            case BusinessTravelPromo:
            case ConfirmAndUpsell:
            case Referral:
            case MTPostHomeBookingList:
            case MTPostHomeBookingSplash:
                this.isBlockedByBatchRequest = false;
                showFragmentForCurrentState();
                return;
            case WaitForResponse:
                this.isBlockedByBatchRequest = true;
                return;
            case Done:
                moveToTripInfo();
                return;
            default:
                return;
        }
    }

    private PostBookingState getNextState() {
        if (this.currentState == null) {
            return PostBookingState.Landing;
        }
        if (this.currentState != PostBookingState.Landing) {
            return PostBookingState.Done;
        }
        if (this.isFetchingPostBookingData) {
            return PostBookingState.WaitForResponse;
        }
        if (this.canShowMTPostHomeBookingPage) {
            if (Experiments.shouldShowListPHB()) {
                return PostBookingState.MTPostHomeBookingList;
            }
            if (Experiments.shouldShowSplashPHB()) {
                return PostBookingState.MTPostHomeBookingSplash;
            }
        }
        if (shouldShowBusinessTravelP5()) {
            return PostBookingState.BusinessTravelPromo;
        }
        if (ListUtils.isEmpty((Collection<?>) this.tripSuggestions) || !FeatureToggles.isUpsellTripsAfterReservationEnabled()) {
            return PostBookingState.Referral;
        }
        return PostBookingState.ConfirmAndUpsell;
    }

    private boolean shouldShowBusinessTravelP5() {
        return this.btMobileSignupPromotion != null;
    }

    private void moveToTripInfo() {
        if (FeatureToggles.showMTNovemberTripsTab()) {
            startActivity(HomeActivityIntents.intentForTrips(this));
        } else {
            startActivity(ReactNativeIntents.intentForItineraryCheckinCard(this, this.reservation.getConfirmationCode()));
        }
        finish();
    }

    private void showFragmentForCurrentState() {
        Fragment fragment = Fragment.instantiate(this, this.currentState.fragmentClassName);
        showFragment(fragment, C0880R.C0882id.content_container, FragmentTransitionType.SlideInFromSide, false, fragment.getClass().getSimpleName());
    }
}
