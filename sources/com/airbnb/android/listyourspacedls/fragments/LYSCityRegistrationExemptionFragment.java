package com.airbnb.android.listyourspacedls.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingRegistration;
import com.airbnb.android.core.models.ListingRegistrationProcess;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.requests.CreateListingRegistrationRequest;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.ListingRegistrationResponse;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.listing.LYSStep;
import com.airbnb.android.listing.adapters.CityRegistrationExemptionAdapter;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import java.util.ArrayList;
import java.util.List;

public class LYSCityRegistrationExemptionFragment extends LYSBaseFragment {
    private CityRegistrationExemptionAdapter adapter;
    public final NonResubscribableRequestListener<AirBatchResponse> batchListener = new C0699RL().onResponse(LYSCityRegistrationExemptionFragment$$Lambda$1.lambdaFactory$(this)).onError(LYSCityRegistrationExemptionFragment$$Lambda$2.lambdaFactory$(this)).onComplete(LYSCityRegistrationExemptionFragment$$Lambda$3.lambdaFactory$(this)).buildWithoutResubscription();
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    static /* synthetic */ void lambda$new$0(LYSCityRegistrationExemptionFragment lYSCityRegistrationExemptionFragment, AirBatchResponse response) {
        ListingRegistration listingRegistration = ((ListingRegistrationResponse) response.singleResponse(ListingRegistrationResponse.class)).listingRegistration;
        Listing listing = ((SimpleListingResponse) response.singleResponse(SimpleListingResponse.class)).listing;
        ListingRegistrationProcess registrationProcess = (ListingRegistrationProcess) Check.notNull(lYSCityRegistrationExemptionFragment.controller.getListingRegistrationProcess());
        registrationProcess.setListingRegistration(listingRegistration);
        lYSCityRegistrationExemptionFragment.controller.setListingRegistrationProcess(registrationProcess);
        lYSCityRegistrationExemptionFragment.controller.setListing(listing);
        lYSCityRegistrationExemptionFragment.controller.nextStep(LYSStep.CityRegistration);
    }

    public static LYSCityRegistrationExemptionFragment newInstance() {
        return new LYSCityRegistrationExemptionFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        this.adapter = new CityRegistrationExemptionAdapter(this.controller.getListing().getLicense(), ((ListingRegistrationProcess) Check.notNull(this.controller.getListingRegistrationProcess())).getContent(), LYSCityRegistrationExemptionFragment$$Lambda$4.lambdaFactory$(this), savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        boolean z = false;
        View view = inflater.inflate(C7251R.layout.lys_dls_toolbar_and_recycler_view, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.adapter);
        if (this.nextButton != null) {
            AirButton airButton = this.nextButton;
            if (!TextUtils.isEmpty(this.adapter.getLicense())) {
                z = true;
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
        saveLicenseAndUpdateListingRegistration(canSaveChanges());
    }

    @OnClick
    public void onNext() {
        this.userAction = UserAction.GoToNext;
        saveLicenseAndUpdateListingRegistration(canSaveChanges());
    }

    private void saveLicenseAndUpdateListingRegistration(boolean hasChanges) {
        if (!hasChanges) {
            navigateInFlow(LYSStep.CityRegistration);
            return;
        }
        setLoading(this.adapter);
        String license = this.adapter.getLicense();
        List<BaseRequestV2<?>> requests = new ArrayList<>();
        requests.add(CreateListingRegistrationRequest.forExistingPermitNumber(this.controller.getListingRegistrationProcess(), license));
        requests.add(UpdateListingRequest.forFieldLYSWithStepId(this.controller.getListing().getId(), ListingRequestConstants.JSON_LICENSE_KEY, license, this.controller.getMaxReachedStep().stepId));
        new AirBatchRequest(requests, true, this.batchListener).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void enableNextButton(boolean enabled) {
        if (this.nextButton != null) {
            this.nextButton.setEnabled(enabled);
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.CityRegistrationExistingLicense;
    }
}
