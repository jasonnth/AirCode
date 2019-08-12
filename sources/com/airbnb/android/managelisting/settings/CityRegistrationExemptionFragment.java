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
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.ListingRegistration;
import com.airbnb.android.core.models.ListingRegistrationProcess;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.requests.CreateListingRegistrationRequest;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.BatchOperation;
import com.airbnb.android.core.responses.ListingRegistrationResponse;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.listing.adapters.CityRegistrationExemptionAdapter;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import java.util.ArrayList;
import java.util.List;

public class CityRegistrationExemptionFragment extends ManageListingBaseFragment {
    private CityRegistrationExemptionAdapter adapter;
    public final NonResubscribableRequestListener<AirBatchResponse> batchListener = new C0699RL().onResponse(CityRegistrationExemptionFragment$$Lambda$1.lambdaFactory$(this)).onError(CityRegistrationExemptionFragment$$Lambda$2.lambdaFactory$(this)).buildWithoutResubscription();
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirButton saveButton;
    @BindView
    AirToolbar toolbar;

    static /* synthetic */ void lambda$new$0(CityRegistrationExemptionFragment cityRegistrationExemptionFragment, AirBatchResponse response) {
        cityRegistrationExemptionFragment.saveButton.setState(State.Success);
        ListingRegistration listingRegistration = ((ListingRegistrationResponse) ((BatchOperation) response.operations.get(0)).convertedResponse).listingRegistration;
        ListingRegistrationProcess registrationProcess = cityRegistrationExemptionFragment.controller.getListingRegistrationProcess();
        registrationProcess.setListingRegistration(listingRegistration);
        cityRegistrationExemptionFragment.controller.setListingRegistrationProcess(registrationProcess);
        cityRegistrationExemptionFragment.controller.setListing(((SimpleListingResponse) ((BatchOperation) response.operations.get(1)).convertedResponse).listing);
        cityRegistrationExemptionFragment.controller.actionExecutor.popToHome();
    }

    static /* synthetic */ void lambda$new$1(CityRegistrationExemptionFragment cityRegistrationExemptionFragment, AirRequestNetworkException e) {
        cityRegistrationExemptionFragment.saveButton.setState(State.Normal);
        cityRegistrationExemptionFragment.adapter.setInputEnabled(true);
        NetworkUtil.tryShowErrorWithSnackbar(cityRegistrationExemptionFragment.recyclerView, e);
    }

    public static CityRegistrationExemptionFragment create() {
        return new CityRegistrationExemptionFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.adapter = new CityRegistrationExemptionAdapter(this.controller.getListing().getLicense(), this.controller.getListingRegistrationProcess().getContent(), CityRegistrationExemptionFragment$$Lambda$3.lambdaFactory$(this), savedInstanceState);
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
        this.adapter.setInputEnabled(false);
        this.saveButton.setState(State.Loading);
        String license = this.adapter.getLicense();
        List<BaseRequestV2<?>> requests = new ArrayList<>();
        requests.add(CreateListingRegistrationRequest.forExistingPermitNumber(this.controller.getListingRegistrationProcess(), license));
        requests.add(UpdateListingRequest.forLicenseField(this.controller.getListing().getId(), license));
        new AirBatchRequest(requests, true, this.batchListener).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void enableSaveButton(boolean enabled) {
        this.saveButton.setEnabled(enabled);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.CityRegistrationExistingLicense;
    }
}
