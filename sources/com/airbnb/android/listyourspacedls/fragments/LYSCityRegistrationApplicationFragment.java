package com.airbnb.android.listyourspacedls.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
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
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.listing.adapters.ListingRegistrationSubmissionAdapter;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.p027n2.components.AirToolbar;
import java.util.ArrayList;
import java.util.List;
import p032rx.Observer;

public class LYSCityRegistrationApplicationFragment extends LYSBaseFragment {
    private ListingRegistrationSubmissionAdapter adapter;
    public final NonResubscribableRequestListener<AirBatchResponse> batchListener = new C0699RL().onResponse(LYSCityRegistrationApplicationFragment$$Lambda$4.lambdaFactory$(this)).onError(LYSCityRegistrationApplicationFragment$$Lambda$5.lambdaFactory$(this)).buildWithoutResubscription();
    final RequestListener<ListingRegistrationProcessesResponse> listingRegistrationProcessesRequestListener = new C0699RL().onResponse(LYSCityRegistrationApplicationFragment$$Lambda$1.lambdaFactory$(this)).onError(LYSCityRegistrationApplicationFragment$$Lambda$2.lambdaFactory$(this)).onComplete(LYSCityRegistrationApplicationFragment$$Lambda$3.lambdaFactory$(this)).build();
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    static /* synthetic */ void lambda$new$0(LYSCityRegistrationApplicationFragment lYSCityRegistrationApplicationFragment, ListingRegistrationProcessesResponse response) {
        lYSCityRegistrationApplicationFragment.controller.setListingRegistrationProcess(response.getFirstListingRegistrationProcess());
        lYSCityRegistrationApplicationFragment.controller.showCityRegistrationNextSteps();
    }

    static /* synthetic */ void lambda$new$3(LYSCityRegistrationApplicationFragment lYSCityRegistrationApplicationFragment, AirBatchResponse response) {
        lYSCityRegistrationApplicationFragment.controller.setListing(((SimpleListingResponse) response.singleResponse(SimpleListingResponse.class)).listing);
        lYSCityRegistrationApplicationFragment.fetchListingRegistrationProcesses();
    }

    static /* synthetic */ void lambda$new$4(LYSCityRegistrationApplicationFragment lYSCityRegistrationApplicationFragment, AirRequestNetworkException e) {
        lYSCityRegistrationApplicationFragment.setLoadingFinished(false, null);
        NetworkUtil.tryShowErrorWithSnackbar(lYSCityRegistrationApplicationFragment.recyclerView, e);
    }

    public static LYSCityRegistrationApplicationFragment newInstance() {
        return new LYSCityRegistrationApplicationFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.showSaveAndExit = false;
        this.adapter = new ListingRegistrationSubmissionAdapter(this.controller.getListingRegistrationProcess());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7251R.layout.lys_dls_toolbar_and_recycler_view, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setHasFixedSize(true);
        this.nextButton.setText(C7251R.string.submit);
        return view;
    }

    @OnClick
    public void onNext() {
        this.userAction = UserAction.GoToNext;
        setLoading(null);
        List<BaseRequestV2<?>> requests = new ArrayList<>();
        requests.add(CreateListingRegistrationRequest.forUnregisteredListing(this.controller.getListingRegistrationProcess()));
        requests.add(UpdateListingRequest.forStepIdLYS(this.controller.getListing().getId(), this.controller.getMaxReachedStep().stepId));
        new AirBatchRequest(requests, true, this.batchListener).execute(this.requestManager);
    }

    private void fetchListingRegistrationProcesses() {
        ListingRegistrationProcessesRequest.forLYS(this.controller.getListing().getId()).withListener((Observer) this.listingRegistrationProcessesRequestListener).execute(this.requestManager);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.CityRegistrationReviewAndSubmit;
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onSaveActionPressed() {
    }
}
