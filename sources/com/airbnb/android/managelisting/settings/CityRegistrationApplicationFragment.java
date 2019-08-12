package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.ListingRegistrationProcess;
import com.airbnb.android.core.requests.CreateListingRegistrationRequest;
import com.airbnb.android.core.requests.ListingRegistrationProcessesRequest;
import com.airbnb.android.core.responses.ListingRegistrationProcessesResponse;
import com.airbnb.android.core.responses.ListingRegistrationResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.listing.adapters.ListingRegistrationSubmissionAdapter;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import p032rx.Observer;

public class CityRegistrationApplicationFragment extends ManageListingBaseFragment {
    private ListingRegistrationSubmissionAdapter adapter;
    final RequestListener<ListingRegistrationResponse> createListingRegistrationRequestListener = new C0699RL().onResponse(CityRegistrationApplicationFragment$$Lambda$3.lambdaFactory$(this)).onError(CityRegistrationApplicationFragment$$Lambda$4.lambdaFactory$(this)).build();
    final RequestListener<ListingRegistrationProcessesResponse> listingRegistrationProcessesRequestListener = new C0699RL().onResponse(CityRegistrationApplicationFragment$$Lambda$1.lambdaFactory$(this)).onError(CityRegistrationApplicationFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    RecyclerView recyclerView;
    private ListingRegistrationProcess registrationProcess;
    @BindView
    AirButton saveButton;
    @BindView
    AirToolbar toolbar;

    static /* synthetic */ void lambda$new$0(CityRegistrationApplicationFragment cityRegistrationApplicationFragment, ListingRegistrationProcessesResponse response) {
        cityRegistrationApplicationFragment.controller.setListingRegistrationProcess(response.getFirstListingRegistrationProcess());
        cityRegistrationApplicationFragment.saveButton.setState(State.Success);
        cityRegistrationApplicationFragment.controller.actionExecutor.cityRegistrationNextSteps();
    }

    static /* synthetic */ void lambda$new$1(CityRegistrationApplicationFragment cityRegistrationApplicationFragment, AirRequestNetworkException error) {
        cityRegistrationApplicationFragment.saveButton.setState(State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(cityRegistrationApplicationFragment.recyclerView, error);
    }

    static /* synthetic */ void lambda$new$3(CityRegistrationApplicationFragment cityRegistrationApplicationFragment, AirRequestNetworkException e) {
        cityRegistrationApplicationFragment.saveButton.setState(State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(cityRegistrationApplicationFragment.recyclerView, e);
    }

    public static CityRegistrationApplicationFragment create() {
        return new CityRegistrationApplicationFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.registrationProcess = this.controller.getListingRegistrationProcess();
        this.adapter = new ListingRegistrationSubmissionAdapter(this.registrationProcess);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_listing_recycler_view_with_save, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setHasFixedSize(true);
        this.saveButton.setText(C7368R.string.submit);
        return view;
    }

    @OnClick
    public void onSubmit() {
        this.saveButton.setState(State.Loading);
        CreateListingRegistrationRequest.forUnregisteredListing(this.registrationProcess).withListener((Observer) this.createListingRegistrationRequestListener).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void fetchListingRegistrationProcesses() {
        ListingRegistrationProcessesRequest.forML(this.controller.getListing().getId()).withListener((Observer) this.listingRegistrationProcessesRequestListener).execute(this.requestManager);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.CityRegistrationReviewAndSubmit;
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }
}
