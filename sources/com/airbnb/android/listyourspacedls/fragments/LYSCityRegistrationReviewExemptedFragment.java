package com.airbnb.android.listyourspacedls.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.ListingRegistrationProcess;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.listing.LYSStep;
import com.airbnb.android.listing.adapters.CityRegistrationExemptionAdapter;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import p032rx.Observer;

public class LYSCityRegistrationReviewExemptedFragment extends LYSBaseFragment {
    private CityRegistrationExemptionAdapter adapter;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;
    private final NonResubscribableRequestListener<SimpleListingResponse> updateListingLicenseRequestListener = new C0699RL().onResponse(LYSCityRegistrationReviewExemptedFragment$$Lambda$1.lambdaFactory$(this)).onError(LYSCityRegistrationReviewExemptedFragment$$Lambda$2.lambdaFactory$(this)).onComplete(LYSCityRegistrationReviewExemptedFragment$$Lambda$3.lambdaFactory$(this)).buildWithoutResubscription();

    static /* synthetic */ void lambda$new$0(LYSCityRegistrationReviewExemptedFragment lYSCityRegistrationReviewExemptedFragment, SimpleListingResponse response) {
        lYSCityRegistrationReviewExemptedFragment.controller.setListing(response.listing);
        lYSCityRegistrationReviewExemptedFragment.navigateInFlow(LYSStep.CityRegistration);
    }

    public static LYSCityRegistrationReviewExemptedFragment newInstance() {
        return new LYSCityRegistrationReviewExemptedFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.adapter = new CityRegistrationExemptionAdapter(this.controller.getListing().getLicense(), this.controller.getListingRegistrationProcess().getContent(), LYSCityRegistrationReviewExemptedFragment$$Lambda$4.lambdaFactory$(this), savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        boolean z = true;
        View view = inflater.inflate(C7251R.layout.lys_dls_toolbar_and_recycler_view, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        this.recyclerView.setAdapter(this.adapter);
        if (this.nextButton != null) {
            AirButton airButton = this.nextButton;
            if (TextUtils.isEmpty(this.adapter.getLicense())) {
                z = false;
            }
            airButton.setEnabled(z);
        }
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

    /* access modifiers changed from: private */
    public void enableSaveButton(boolean enabled) {
        if (this.nextButton != null) {
            this.nextButton.setEnabled(enabled);
        }
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
