package com.airbnb.android.cityregistration.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.requests.CreateListingRegistrationRequest;
import com.airbnb.android.core.requests.ListingRegistrationProcessesRequest;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.ListingRegistrationProcessesResponse;
import com.airbnb.android.core.responses.ListingRegistrationResponse;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import java.util.ArrayList;
import java.util.List;
import p032rx.Observer;

public abstract class CityRegistrationBaseSubmissionFragment extends CityRegistrationBaseFragment {
    public final NonResubscribableRequestListener<AirBatchResponse> batchListener = new C0699RL().onResponse(CityRegistrationBaseSubmissionFragment$$Lambda$5.lambdaFactory$(this)).onError(CityRegistrationBaseSubmissionFragment$$Lambda$6.lambdaFactory$(this)).buildWithoutResubscription();
    final RequestListener<ListingRegistrationResponse> createListingRegistrationRequestListener = new C0699RL().onResponse(CityRegistrationBaseSubmissionFragment$$Lambda$3.lambdaFactory$(this)).onError(CityRegistrationBaseSubmissionFragment$$Lambda$4.lambdaFactory$(this)).build();
    final RequestListener<ListingRegistrationProcessesResponse> listingRegistrationProcessesRequestListener = new C0699RL().onResponse(CityRegistrationBaseSubmissionFragment$$Lambda$1.lambdaFactory$(this)).onError(CityRegistrationBaseSubmissionFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirButton saveButton;
    @BindView
    AirToolbar toolbar;

    static /* synthetic */ void lambda$new$0(CityRegistrationBaseSubmissionFragment cityRegistrationBaseSubmissionFragment, ListingRegistrationProcessesResponse response) {
        cityRegistrationBaseSubmissionFragment.controller.setListingRegistrationProcess(response.getFirstListingRegistrationProcess());
        cityRegistrationBaseSubmissionFragment.saveButton.setState(State.Success);
        cityRegistrationBaseSubmissionFragment.controller.getActionExecutor().cityRegistrationNextSteps();
    }

    static /* synthetic */ void lambda$new$1(CityRegistrationBaseSubmissionFragment cityRegistrationBaseSubmissionFragment, AirRequestNetworkException error) {
        cityRegistrationBaseSubmissionFragment.saveButton.setState(State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(cityRegistrationBaseSubmissionFragment.recyclerView, error);
    }

    static /* synthetic */ void lambda$new$3(CityRegistrationBaseSubmissionFragment cityRegistrationBaseSubmissionFragment, AirRequestNetworkException e) {
        cityRegistrationBaseSubmissionFragment.saveButton.setState(State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(cityRegistrationBaseSubmissionFragment.recyclerView, e);
    }

    static /* synthetic */ void lambda$new$4(CityRegistrationBaseSubmissionFragment cityRegistrationBaseSubmissionFragment, AirBatchResponse response) {
        cityRegistrationBaseSubmissionFragment.controller.setListing(((SimpleListingResponse) response.singleResponse(SimpleListingResponse.class)).listing);
        cityRegistrationBaseSubmissionFragment.fetchListingRegistrationProcesses();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    public void onSubmit() {
        this.saveButton.setState(State.Loading);
        if (this.controller.isLYS()) {
            List<BaseRequestV2<?>> requests = new ArrayList<>();
            requests.add(CreateListingRegistrationRequest.forUnregisteredListing(this.listingRegistrationProcess));
            requests.add(UpdateListingRequest.forStepIdLYS(this.listing.getId(), "REGISTRATION"));
            new AirBatchRequest(requests, true, this.batchListener).execute(this.requestManager);
            return;
        }
        CreateListingRegistrationRequest.forUnregisteredListing(this.listingRegistrationProcess).withListener((Observer) this.createListingRegistrationRequestListener).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void fetchListingRegistrationProcesses() {
        ListingRegistrationProcessesRequest.forML(getListingId()).withListener((Observer) this.listingRegistrationProcessesRequestListener).execute(this.requestManager);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.CityRegistrationReviewAndSubmit;
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.controller.isLYS();
    }

    /* access modifiers changed from: protected */
    public boolean onSaveActionPressed() {
        return true;
    }
}
