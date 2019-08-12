package com.airbnb.android.listyourspacedls.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.ListingRegistrationProcess;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.listing.LYSStep;
import com.airbnb.android.listing.adapters.ListingRegistrationAdapter;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import p032rx.Observer;

public class LYSCityRegistrationReviewFragment extends LYSBaseFragment {
    private ListingRegistrationAdapter adapter;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;
    final RequestListener<SimpleListingResponse> updateListingLicenseRequestListener = new C0699RL().onResponse(LYSCityRegistrationReviewFragment$$Lambda$1.lambdaFactory$(this)).onError(LYSCityRegistrationReviewFragment$$Lambda$2.lambdaFactory$(this)).onComplete(LYSCityRegistrationReviewFragment$$Lambda$3.lambdaFactory$(this)).build();

    static /* synthetic */ void lambda$new$0(LYSCityRegistrationReviewFragment lYSCityRegistrationReviewFragment, SimpleListingResponse response) {
        lYSCityRegistrationReviewFragment.controller.setListing(response.listing);
        lYSCityRegistrationReviewFragment.navigateInFlow(LYSStep.CityRegistration);
    }

    public static LYSCityRegistrationReviewFragment newInstance() {
        return new LYSCityRegistrationReviewFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.adapter = new ListingRegistrationAdapter(this.controller.getListingRegistrationProcess(), this.controller.getListing().getLicense(), savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7251R.layout.lys_dls_toolbar_and_recycler_view, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setHasFixedSize(true);
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.adapter.hasChanged(this.controller.getListing().getLicense());
    }

    /* access modifiers changed from: protected */
    public void onSaveActionPressed() {
        saveLicense(canSaveChanges());
    }

    @OnClick
    public void onNext() {
        this.userAction = UserAction.GoToNext;
        saveLicense(canSaveChanges());
    }

    private void saveLicense(boolean hasChanges) {
        if (!hasChanges) {
            navigateInFlow(LYSStep.CityRegistration);
            return;
        }
        setLoading(this.adapter);
        UpdateListingRequest.forFieldLYSWithStepId(this.controller.getListing().getId(), ListingRequestConstants.JSON_LICENSE_KEY, this.adapter.getLicense(), this.controller.getMaxReachedStep().stepId).withListener((Observer) this.updateListingLicenseRequestListener).execute(this.requestManager);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.CityRegistrationApplicationStatus;
    }

    public Strap getNavigationTrackingParams() {
        Strap params = super.getNavigationTrackingParams();
        ListingRegistrationProcess listingRegistrationProcess = this.controller.getListingRegistrationProcess();
        if (listingRegistrationProcess != null) {
            params.mo11639kv("status", listingRegistrationProcess.getListingRegistration().getStatus().getServerKey());
        }
        return params;
    }
}
