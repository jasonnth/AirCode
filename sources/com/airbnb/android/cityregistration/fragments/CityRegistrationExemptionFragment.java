package com.airbnb.android.cityregistration.fragments;

import android.content.Intent;
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
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.cityregistration.C5630R;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.fragments.datepicker.DatePickerDialog;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.requests.CreateListingRegistrationRequest;
import com.airbnb.android.core.requests.UpdateListingRegistrationRequest;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.BatchOperation;
import com.airbnb.android.core.responses.ListingRegistrationResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.listing.adapters.CityRegistrationExemptionAdapter;
import com.airbnb.android.listing.adapters.CityRegistrationExemptionAdapter.ListenerV2;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import java.util.ArrayList;
import java.util.List;

public class CityRegistrationExemptionFragment extends CityRegistrationBaseFragment {
    /* access modifiers changed from: private */
    public CityRegistrationExemptionAdapter adapter;
    public final NonResubscribableRequestListener<AirBatchResponse> batchListener = new C0699RL().onResponse(CityRegistrationExemptionFragment$$Lambda$1.lambdaFactory$(this)).onError(CityRegistrationExemptionFragment$$Lambda$2.lambdaFactory$(this)).buildWithoutResubscription();
    private ListenerV2 listener = new ListenerV2() {
        public void showDateSelection() {
            AirDate today = AirDate.today();
            AirDate initialDate = today;
            String expiryDate = CityRegistrationExemptionFragment.this.adapter.getExpiryDate();
            if (expiryDate != null) {
                AirDate potentialDate = AirDate.fromISODateString(expiryDate);
                if (potentialDate != null) {
                    initialDate = potentialDate;
                }
            }
            DatePickerDialog.newInstance(initialDate, false, CityRegistrationExemptionFragment.this.getTargetFragment(), 0, today, null, DatePickerDialog.DATE_PICKER_OK).show(CityRegistrationExemptionFragment.this.getFragmentManager(), (String) null);
        }

        public void inputIsValid(boolean isValid) {
            CityRegistrationExemptionFragment.this.enableSaveButton(isValid);
        }
    };
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirButton saveButton;
    @BindView
    AirToolbar toolbar;

    static /* synthetic */ void lambda$new$0(CityRegistrationExemptionFragment cityRegistrationExemptionFragment, AirBatchResponse response) {
        cityRegistrationExemptionFragment.saveButton.setState(State.Success);
        cityRegistrationExemptionFragment.listingRegistrationProcess.setListingRegistration(((ListingRegistrationResponse) ((BatchOperation) response.operations.get(0)).convertedResponse).listingRegistration);
        cityRegistrationExemptionFragment.controller.setListingRegistrationProcess(cityRegistrationExemptionFragment.listingRegistrationProcess);
        cityRegistrationExemptionFragment.controller.finishOk();
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
        this.adapter = new CityRegistrationExemptionAdapter(getLicense(), getExpiryDate(), getZipCode(), this.listingRegistrationProcess.getContent(), this.listingRegistrationProcess, this.listener, savedInstanceState);
    }

    private String getLicense() {
        if (this.listingRegistrationProcess.getListingRegistration() == null) {
            return this.listing.getLicense();
        }
        return this.listingRegistrationProcess.getListingRegistration().getExemptionData().getPermitNumber();
    }

    private String getExpiryDate() {
        if (this.listingRegistrationProcess.getListingRegistration() == null) {
            return null;
        }
        return this.listingRegistrationProcess.getListingRegistration().getExemptionData().getExpirationDate();
    }

    private String getZipCode() {
        if (this.listingRegistrationProcess.getListingRegistration() == null) {
            return null;
        }
        return this.listingRegistrationProcess.getListingRegistration().getExemptionData().getZipcode();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch (requestCode) {
                case DatePickerDialog.DATE_PICKER_OK /*2002*/:
                    this.adapter.setExpiryDate(((AirDate) data.getParcelableExtra("date")).getIsoDateString());
                    return;
                default:
                    return;
            }
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(this.controller.isLYS() ? C5630R.layout.fragment_city_registration_lys : C5630R.layout.fragment_listing_recycler_view_with_save, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.adapter);
        this.saveButton.setEnabled(this.adapter.allInputIsValid(this.listingRegistrationProcess));
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: protected */
    public boolean onSaveActionPressed() {
        saveLicenseAndUpdateListingRegistration(canSaveChanges());
        return false;
    }

    private void saveLicenseAndUpdateListingRegistration(boolean hasChanges) {
        if (!hasChanges) {
            this.controller.finishOk();
        } else {
            save(true);
        }
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.adapter.hasChanged(this.listing);
    }

    @OnClick
    public void onSave() {
        save(false);
    }

    private void save(boolean forFieldLYSWithStepId) {
        this.adapter.setInputEnabled(false);
        this.saveButton.setState(State.Loading);
        String license = this.adapter.getLicense();
        String expiryDate = this.adapter.getExpiryDate();
        String zipcode = this.adapter.getZipCode();
        List<BaseRequestV2<?>> requests = new ArrayList<>();
        if (this.listingRegistrationProcess.getListingRegistration() == null) {
            requests.add(CreateListingRegistrationRequest.forExistingPermitNumber(this.listingRegistrationProcess, license, expiryDate, zipcode));
        } else {
            requests.add(UpdateListingRegistrationRequest.forExemption(this.listingRegistrationProcess, license, expiryDate, zipcode));
        }
        if (forFieldLYSWithStepId) {
            requests.add(UpdateListingRequest.forFieldLYSWithStepId(this.listing.getId(), ListingRequestConstants.JSON_LICENSE_KEY, license, "REGISTRATION"));
        } else {
            requests.add(UpdateListingRequest.forLicenseField(this.listing.getId(), license));
        }
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
