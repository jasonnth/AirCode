package com.airbnb.android.cityregistration.fragments;

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
import com.airbnb.android.cityregistration.C5630R;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.listing.adapters.ListingRegistrationAdapter;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import p032rx.Observer;

public class CityRegistrationReviewFragment extends CityRegistrationBaseFragment {
    private ListingRegistrationAdapter adapter;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirButton saveButton;
    @BindView
    AirToolbar toolbar;
    final RequestListener<SimpleListingResponse> updateListingLicenseRequestListener = new C0699RL().onResponse(CityRegistrationReviewFragment$$Lambda$1.lambdaFactory$(this)).onError(CityRegistrationReviewFragment$$Lambda$2.lambdaFactory$(this)).build();

    static /* synthetic */ void lambda$new$0(CityRegistrationReviewFragment cityRegistrationReviewFragment, SimpleListingResponse response) {
        cityRegistrationReviewFragment.controller.setListing(response.listing);
        cityRegistrationReviewFragment.saveButton.setState(State.Success);
        cityRegistrationReviewFragment.controller.finishOk();
    }

    static /* synthetic */ void lambda$new$1(CityRegistrationReviewFragment cityRegistrationReviewFragment, AirRequestNetworkException error) {
        cityRegistrationReviewFragment.saveButton.setState(State.Normal);
        cityRegistrationReviewFragment.adapter.setInputEnabled(true);
        NetworkUtil.tryShowErrorWithSnackbar(cityRegistrationReviewFragment.recyclerView, error);
    }

    public static CityRegistrationReviewFragment create() {
        return new CityRegistrationReviewFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.adapter = new ListingRegistrationAdapter(this.listingRegistrationProcess, this.listing.getLicense(), savedInstanceState, CityRegistrationReviewFragment$$Lambda$3.lambdaFactory$(this));
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(this.controller.isLYS() ? C5630R.layout.fragment_city_registration_lys : C5630R.layout.fragment_listing_recycler_view_with_save, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setHasFixedSize(true);
        setSaveButtonVisible(this.adapter.hasChanged(this.listing.getLicense()));
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.adapter.hasChanged(this.listing.getLicense());
    }

    /* access modifiers changed from: protected */
    public boolean onSaveActionPressed() {
        if (!canSaveChanges()) {
            return true;
        }
        saveLicense();
        return false;
    }

    /* access modifiers changed from: private */
    public void setSaveButtonVisible(boolean visible) {
        this.saveButton.setVisibility(visible ? 0 : 8);
    }

    @OnClick
    public void onSave() {
        String license = this.adapter.getLicense();
        this.adapter.setInputEnabled(false);
        this.saveButton.setState(State.Loading);
        UpdateListingRequest.forLicenseField(this.controller.getListing().getId(), license).withListener((Observer) this.updateListingLicenseRequestListener).execute(this.requestManager);
    }

    private void saveLicense() {
        UpdateListingRequest.forFieldLYSWithStepId(this.listing.getId(), ListingRequestConstants.JSON_LICENSE_KEY, this.adapter.getLicense(), "REGISTRATION").withListener((Observer) this.updateListingLicenseRequestListener).execute(this.requestManager);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.CityRegistrationApplicationStatus;
    }

    public Strap getNavigationTrackingParams() {
        Strap params = super.getNavigationTrackingParams();
        if (this.listingRegistrationProcess != null) {
            params.mo11639kv("status", this.listingRegistrationProcess.getListingRegistration().getStatus().getServerKey());
        }
        return params;
    }
}
