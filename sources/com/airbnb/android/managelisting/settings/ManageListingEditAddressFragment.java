package com.airbnb.android.managelisting.settings;

import android.content.Intent;
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
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.AirAddress;
import com.airbnb.android.core.requests.ListingRegistrationProcessesRequest;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.responses.ListingRegistrationProcessesResponse;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.utils.AirAddressUtil;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.listing.adapters.EditAddressAdapter;
import com.airbnb.android.listing.adapters.EditAddressAdapter.Listener;
import com.airbnb.android.listing.adapters.EditAddressAdapter.Mode;
import com.airbnb.android.listing.fragments.AddressAutoCompleteFragment.Builder;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import p032rx.Observer;

public class ManageListingEditAddressFragment extends ManageListingBaseFragment {
    private final int REQUEST_CODE_ADDRESS_AUTOCOMPLETE = 100;
    /* access modifiers changed from: private */
    public EditAddressAdapter adapter;
    private final Listener editAddressListener = new Listener() {
        public void showCountrySelectModal() {
        }

        public void showAddressAutoCompleteModal() {
            ManageListingEditAddressFragment.this.startActivityForResult(new Builder(ManageListingEditAddressFragment.this.getContext(), NavigationTag.ManageListingEditAddressAutoComplete).setCountryAndStreet(ManageListingEditAddressFragment.this.controller.getListing().getCountryCode(), ManageListingEditAddressFragment.this.adapter.getAddress().streetAddressOne()).setListingId(ManageListingEditAddressFragment.this.controller.getListingId()).build(), 100);
        }

        public void dismissErrorSnackbar() {
        }
    };
    final RequestListener<ListingRegistrationProcessesResponse> listingRegistrationProcessesListener = new C0699RL().onResponse(ManageListingEditAddressFragment$$Lambda$1.lambdaFactory$(this)).onError(ManageListingEditAddressFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirButton saveButton;
    @BindView
    AirToolbar toolbar;
    final RequestListener<SimpleListingResponse> updateListingListener = new C0699RL().onResponse(ManageListingEditAddressFragment$$Lambda$3.lambdaFactory$(this)).onError(ManageListingEditAddressFragment$$Lambda$4.lambdaFactory$(this)).build();

    static /* synthetic */ void lambda$new$0(ManageListingEditAddressFragment manageListingEditAddressFragment, ListingRegistrationProcessesResponse response) {
        manageListingEditAddressFragment.controller.setListingRegistrationProcess(response.getFirstListingRegistrationProcess());
        manageListingEditAddressFragment.saveButton.setState(State.Success);
        manageListingEditAddressFragment.getFragmentManager().popBackStack();
    }

    static /* synthetic */ void lambda$new$1(ManageListingEditAddressFragment manageListingEditAddressFragment, AirRequestNetworkException error) {
        manageListingEditAddressFragment.adapter.setInputEnabled(true);
        NetworkUtil.tryShowErrorWithSnackbar(manageListingEditAddressFragment.getView(), error);
        manageListingEditAddressFragment.saveButton.setState(State.Normal);
    }

    static /* synthetic */ void lambda$new$2(ManageListingEditAddressFragment manageListingEditAddressFragment, SimpleListingResponse response) {
        manageListingEditAddressFragment.controller.setListing(response.listing);
        if (FeatureToggles.isListingRegistrationEnabled()) {
            ListingRegistrationProcessesRequest.forML(manageListingEditAddressFragment.controller.getListing().getId()).withListener((Observer) manageListingEditAddressFragment.listingRegistrationProcessesListener).execute(manageListingEditAddressFragment.requestManager);
            return;
        }
        manageListingEditAddressFragment.saveButton.setState(State.Success);
        manageListingEditAddressFragment.getFragmentManager().popBackStack();
    }

    static /* synthetic */ void lambda$new$3(ManageListingEditAddressFragment manageListingEditAddressFragment, AirRequestNetworkException error) {
        manageListingEditAddressFragment.adapter.setInputEnabled(true);
        NetworkUtil.tryShowErrorWithSnackbar(manageListingEditAddressFragment.getView(), error);
        manageListingEditAddressFragment.saveButton.setState(State.Normal);
    }

    public static ManageListingEditAddressFragment create() {
        return new ManageListingEditAddressFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.adapter = new EditAddressAdapter(getContext(), AirAddressUtil.fromListing(this.controller.getListing()), this.editAddressListener, savedInstanceState, Mode.ManageListing);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_listing_recycler_view_with_save, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.adapter);
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.adapter.hasChanged(AirAddressUtil.fromListing(this.controller.getListing()));
    }

    @OnClick
    public void onSave() {
        if (!canSaveChanges()) {
            this.saveButton.setState(State.Success);
            getFragmentManager().popBackStack();
        } else if (this.adapter.validateInputsAndShowError()) {
            this.saveButton.setState(State.Loading);
            this.adapter.setInputEnabled(false);
            executeUpdateListingRequest(this.adapter.getAddress());
        }
    }

    private void executeUpdateListingRequest(AirAddress address) {
        UpdateListingRequest.forFields(this.controller.getListing().getId(), Strap.make().mo11639kv("street", address.streetAddressOne()).mo11639kv(ListingRequestConstants.JSON_APT_KEY, address.streetAddressTwo()).mo11639kv("city", address.city()).mo11639kv("state", address.state()).mo11639kv(ListingRequestConstants.JSON_ZIP_KEY, address.postalCode()).mo11640kv(ListingRequestConstants.JSON_USER_DEFINED_LOCATION, false)).withListener((Observer) this.updateListingListener).execute(this.requestManager);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == -1) {
            AirAddress address = (AirAddress) data.getParcelableExtra("address");
            if (address != null) {
                this.adapter.setAutoCompleteAddress(address);
                return;
            }
            String street = data.getStringExtra("street");
            if (street != null) {
                this.adapter.setStreet(street);
            }
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingEditAddress;
    }
}
