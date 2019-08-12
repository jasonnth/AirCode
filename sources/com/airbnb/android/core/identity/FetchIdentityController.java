package com.airbnb.android.core.identity;

import android.os.Bundle;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.airrequest.SimpleRequestListener;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.models.AccountVerification;
import com.airbnb.android.core.models.GovernmentIdResult;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.requests.AccountVerificationsRequest;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.requests.GetGovernmentIdResultsRequest;
import com.airbnb.android.core.requests.UserRequest;
import com.airbnb.android.core.requests.VerificationsRequest.Filter;
import com.airbnb.android.core.responses.AccountVerificationsResponse;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.GovernmentIdResultsResponse;
import com.airbnb.android.core.responses.UserResponse;
import com.google.common.collect.FluentIterable;
import icepick.State;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import p032rx.Observer;

public class FetchIdentityController {
    public final NonResubscribableRequestListener<AirBatchResponse> fetchVerificationsAndCountryRequestListener = new NonResubscribableRequestListener<AirBatchResponse>() {
        public void onResponse(AirBatchResponse data) {
            FetchIdentityController.this.setPreviousUserVerifications();
            if (FetchIdentityController.this.listener != null) {
                VerificationFlow verificationFlow = (VerificationFlow) FetchIdentityController.this.verificationFlows.iterator().next();
                FetchIdentityController.this.incompleteVerifications.put(verificationFlow, new ArrayList(FetchIdentityController.this.getIncompleteVerificationList(verificationFlow)));
                FetchIdentityController.this.listener.onVerificationsFetchComplete((ArrayList) FetchIdentityController.this.incompleteVerifications.get(verificationFlow));
                return;
            }
            for (VerificationFlow flow : FetchIdentityController.this.verificationFlows) {
                FetchIdentityController.this.incompleteVerifications.put(flow, new ArrayList(FetchIdentityController.this.getIncompleteVerificationList(flow)));
            }
            FetchIdentityController.this.multiVerificationFlowListener.onVerificationsFetchComplete(FetchIdentityController.this.incompleteVerifications);
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            if (FetchIdentityController.this.listener != null) {
                FetchIdentityController.this.listener.onVerificationsFetchError(e);
            } else {
                FetchIdentityController.this.multiVerificationFlowListener.onVerificationsFetchError(e);
            }
        }
    };
    @State
    GovernmentIdResult governmentIdResult;
    @State
    HashMap<VerificationFlow, ArrayList<AccountVerification>> incompleteVerifications;
    /* access modifiers changed from: private */
    public Listener listener;
    /* access modifiers changed from: private */
    public MultiVerificationFlowListener multiVerificationFlowListener;
    private final RequestManager requestManager;
    @State
    boolean requiresIdentityTreatment;
    /* access modifiers changed from: private */
    public final Set<VerificationFlow> verificationFlows;
    @State
    HashMap<VerificationFlow, ArrayList<AccountVerification>> verificationStates;
    @State
    User verificationUser;

    public interface Listener {
        void onVerificationsFetchComplete(ArrayList<AccountVerification> arrayList);

        void onVerificationsFetchError(NetworkException networkException);
    }

    public interface MultiVerificationFlowListener {
        void onVerificationsFetchComplete(HashMap<VerificationFlow, ArrayList<AccountVerification>> hashMap);

        void onVerificationsFetchError(NetworkException networkException);
    }

    public FetchIdentityController(RequestManager requestManager2, Listener listener2, VerificationFlow verificationFlow, Bundle savedState) {
        this.requestManager = requestManager2;
        this.verificationFlows = new HashSet();
        this.verificationFlows.add(verificationFlow);
        this.listener = listener2;
        this.requiresIdentityTreatment = verificationFlow.requiresIdentityTreatment();
        init(savedState);
    }

    public FetchIdentityController(RequestManager requestManager2, MultiVerificationFlowListener multiVerificationFlowListener2, VerificationFlow[] verificationFlows2, Bundle savedState) {
        this.requestManager = requestManager2;
        this.verificationFlows = new HashSet();
        this.verificationFlows.addAll(Arrays.asList(verificationFlows2));
        this.multiVerificationFlowListener = multiVerificationFlowListener2;
        this.requiresIdentityTreatment = ((VerificationFlow) FluentIterable.from((Iterable<E>) this.verificationFlows).firstMatch(FetchIdentityController$$Lambda$1.lambdaFactory$()).orNull()) != null;
        init(savedState);
    }

    private void init(Bundle savedState) {
        IcepickWrapper.restoreInstanceState(this, savedState);
        this.requestManager.subscribe(this);
        if (savedState == null) {
            this.verificationStates = new HashMap<>();
            this.incompleteVerifications = new HashMap<>();
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        IcepickWrapper.saveInstanceState(this, outState);
    }

    /* access modifiers changed from: private */
    public void setPreviousUserVerifications() {
        Set<String> previousVerifications = new HashSet<>();
        for (VerificationFlow flow : this.verificationStates.keySet()) {
            Iterator it = ((ArrayList) this.verificationStates.get(flow)).iterator();
            while (it.hasNext()) {
                AccountVerification verification = (AccountVerification) it.next();
                if (verification.isComplete()) {
                    previousVerifications.add(verification.getType());
                }
            }
        }
        this.verificationUser.setVerifications(new ArrayList(previousVerifications));
    }

    public void startFetchingIdentityVerificationState(long userId) {
        List<BaseRequestV2<?>> requests = new ArrayList<>();
        requests.add(UserRequest.newRequestForVerifications(userId).withListener((Observer) new SimpleRequestListener<UserResponse>() {
            public void onResponse(UserResponse response) {
                FetchIdentityController.this.verificationUser = response.user;
            }
        }));
        requests.add(new GetGovernmentIdResultsRequest(userId).withListener((Observer) new SimpleRequestListener<GovernmentIdResultsResponse>() {
            public void onResponse(GovernmentIdResultsResponse response) {
                FetchIdentityController.this.governmentIdResult = response.getLatestResult();
            }
        }));
        final Map<Filter, List<VerificationFlow>> filterMap = getFilterMap();
        for (Filter filter : filterMap.keySet()) {
            final VerificationFlow verificationFlow = (VerificationFlow) ((List) filterMap.get(filter)).get(0);
            requests.add(AccountVerificationsRequest.forFlow(verificationFlow).withListener((Observer) new SimpleRequestListener<AccountVerificationsResponse>() {
                public void onResponse(AccountVerificationsResponse response) {
                    for (VerificationFlow v : (List) filterMap.get(verificationFlow.getRequestFilter())) {
                        FetchIdentityController.this.verificationStates.put(v, new ArrayList(response.accountActivationVerifications));
                    }
                }
            }));
        }
        new AirBatchRequest(requests, this.fetchVerificationsAndCountryRequestListener).execute(this.requestManager);
    }

    private Map<Filter, List<VerificationFlow>> getFilterMap() {
        Map<Filter, List<VerificationFlow>> filterMap = new HashMap<>();
        for (VerificationFlow verificationFlow : this.verificationFlows) {
            if (filterMap.containsKey(verificationFlow.getRequestFilter())) {
                ((List) filterMap.get(verificationFlow.getRequestFilter())).add(verificationFlow);
            } else {
                List<VerificationFlow> list = new ArrayList<>();
                list.add(verificationFlow);
                filterMap.put(verificationFlow.getRequestFilter(), list);
            }
        }
        return filterMap;
    }

    public User getVerificationUser() {
        return this.verificationUser;
    }

    public GovernmentIdResult getGovernmentIdResult() {
        return this.governmentIdResult;
    }

    public List<AccountVerification> getIncompleteVerificationList(VerificationFlow flow) {
        if (!this.verificationStates.containsKey(flow)) {
            return null;
        }
        return FluentIterable.from((Iterable) this.verificationStates.get(flow)).filter(FetchIdentityController$$Lambda$2.lambdaFactory$()).filter(FetchIdentityController$$Lambda$3.lambdaFactory$(this, flow)).filter(FetchIdentityController$$Lambda$4.lambdaFactory$(this)).toList();
    }

    static /* synthetic */ boolean lambda$getIncompleteVerificationList$0(AccountVerification verification) {
        return !verification.isComplete();
    }

    /* access modifiers changed from: private */
    public boolean filterOfflineIdAndSelfie(AccountVerification verification) {
        return !verification.isOnlyRequiredForIdentityFlow() || !this.requiresIdentityTreatment || this.verificationFlows.contains(VerificationFlow.VerifiedID) || this.verificationFlows.contains(VerificationFlow.UserProfile);
    }

    /* access modifiers changed from: private */
    public boolean filterPhoneAndEmail(AccountVerification verification, VerificationFlow flow) {
        if (verification.isPhoneOrEmail() && flow.excludePhoneAndEmail() && !flow.requiresIdentityTreatment()) {
            return false;
        }
        return true;
    }
}
