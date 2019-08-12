package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.ListingRegistrationProcess;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.listing.adapters.CityRegistrationExemptionAdapter;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import p032rx.Observer;

public class CityRegistrationReviewExemptedFragment extends ManageListingBaseFragment {
    private CityRegistrationExemptionAdapter adapter;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirButton saveButton;
    @BindView
    AirToolbar toolbar;
    private final NonResubscribableRequestListener<SimpleListingResponse> updateListingLicenseRequestListener = new C0699RL().onResponse(CityRegistrationReviewExemptedFragment$$Lambda$1.lambdaFactory$(this)).onError(CityRegistrationReviewExemptedFragment$$Lambda$2.lambdaFactory$(this)).buildWithoutResubscription();

    static /* synthetic */ void lambda$new$0(CityRegistrationReviewExemptedFragment cityRegistrationReviewExemptedFragment, SimpleListingResponse response) {
        cityRegistrationReviewExemptedFragment.controller.setListing(response.listing);
        cityRegistrationReviewExemptedFragment.saveButton.setState(State.Success);
        cityRegistrationReviewExemptedFragment.getFragmentManager().popBackStack();
    }

    static /* synthetic */ void lambda$new$1(CityRegistrationReviewExemptedFragment cityRegistrationReviewExemptedFragment, AirRequestNetworkException error) {
        cityRegistrationReviewExemptedFragment.saveButton.setState(State.Normal);
        cityRegistrationReviewExemptedFragment.adapter.setInputEnabled(true);
        NetworkUtil.tryShowErrorWithSnackbar(cityRegistrationReviewExemptedFragment.recyclerView, error);
    }

    public static CityRegistrationReviewExemptedFragment create() {
        return new CityRegistrationReviewExemptedFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.adapter = new CityRegistrationExemptionAdapter(this.controller.getListing().getLicense(), this.controller.getListingRegistrationProcess().getContent(), CityRegistrationReviewExemptedFragment$$Lambda$3.lambdaFactory$(this), savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        boolean z = false;
        View view = inflater.inflate(C7368R.layout.fragment_listing_recycler_view_with_save, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.adapter);
        AirButton airButton = this.saveButton;
        if (!TextUtils.isEmpty(this.adapter.getLicense())) {
            z = true;
        }
        airButton.setEnabled(z);
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.adapter.hasChanged(this.controller.getListing());
    }

    @OnClick
    public void onSave() {
        if (!canSaveChanges()) {
            this.saveButton.setState(State.Success);
            getFragmentManager().popBackStack();
            return;
        }
        String license = this.adapter.getLicense();
        this.saveButton.setState(State.Loading);
        this.adapter.setInputEnabled(false);
        UpdateListingRequest.forLicenseField(this.controller.getListing().getId(), license).withListener((Observer) this.updateListingLicenseRequestListener).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void enableSaveButton(boolean enabled) {
        this.saveButton.setEnabled(enabled);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.CityRegistrationExistingLicense;
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
