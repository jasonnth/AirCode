package com.airbnb.android.listyourspacedls.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.p000v4.app.Fragment;
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
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.host.ListingPromoController;
import com.airbnb.android.core.models.AirAddress;
import com.airbnb.android.core.models.BedType;
import com.airbnb.android.core.models.Country;
import com.airbnb.android.core.models.ListingRoom;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.requests.CreateEmptyListingRoomRequest;
import com.airbnb.android.core.requests.LYSCreateListingRequest;
import com.airbnb.android.core.requests.UpdateBedTypeRequest;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.ListingRoomResponse;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.utils.AirAddressUtil;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.listing.LYSStep;
import com.airbnb.android.listing.adapters.EditAddressAdapter;
import com.airbnb.android.listing.adapters.EditAddressAdapter.Listener;
import com.airbnb.android.listing.adapters.EditAddressAdapter.Mode;
import com.airbnb.android.listing.fragments.AddressAutoCompleteFragment.Builder;
import com.airbnb.android.listing.fragments.CountryFragment;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.android.listyourspacedls.LYSJitneyLogger;
import com.airbnb.android.listyourspacedls.ListYourSpaceDLSGraph;
import com.airbnb.android.listyourspacedls.utils.LYSBatchRequestUtil;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import com.google.common.collect.FluentIterable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p032rx.Observer;

public class LYSAddressFragment extends LYSBaseFragment {
    private static final int REQUEST_CODE_ADDRESS_AUTOCOMPLETE = 200;
    private static final int REQUEST_CODE_COUNTRY_SELECTED = 201;
    private final NonResubscribableRequestListener<AirBatchResponse> addressAndRoomsListener = new C0699RL().onResponse(LYSAddressFragment$$Lambda$4.lambdaFactory$(this)).onError(LYSAddressFragment$$Lambda$5.lambdaFactory$(this)).buildWithoutResubscription();
    public final NonResubscribableRequestListener<AirBatchResponse> batchBedTypeListener = new C0699RL().onResponse(LYSAddressFragment$$Lambda$6.lambdaFactory$(this)).onError(LYSAddressFragment$$Lambda$7.lambdaFactory$(this)).buildWithoutResubscription();
    public final NonResubscribableRequestListener<AirBatchResponse> batchFetchDataListener = new C0699RL().onResponse(LYSAddressFragment$$Lambda$8.lambdaFactory$(this)).onError(LYSAddressFragment$$Lambda$9.lambdaFactory$(this)).onComplete(LYSAddressFragment$$Lambda$10.lambdaFactory$(this)).buildWithoutResubscription();
    final RequestListener<SimpleListingResponse> createListingRequestListener = new C0699RL().onResponse(LYSAddressFragment$$Lambda$1.lambdaFactory$(this)).onError(LYSAddressFragment$$Lambda$2.lambdaFactory$(this)).onComplete(LYSAddressFragment$$Lambda$3.lambdaFactory$(this)).build();
    /* access modifiers changed from: private */
    public EditAddressAdapter editAddressAdapter;
    private final Listener editAddressListener = new Listener() {
        public void showCountrySelectModal() {
            AirAddress address = LYSAddressFragment.this.editAddressAdapter.getAddress();
            LYSAddressFragment.this.editAddressAdapter.setInputEnabled(false);
            LYSAddressFragment.this.startActivityForResult(CountryFragment.createIntent(LYSAddressFragment.this.getContext(), address.countryCode(), NavigationTag.LYSLocationCountry), LYSAddressFragment.REQUEST_CODE_COUNTRY_SELECTED);
        }

        public void showAddressAutoCompleteModal() {
            long listingId = 0;
            if (LYSAddressFragment.this.controller.isListingCreated()) {
                listingId = LYSAddressFragment.this.controller.getListing().getId();
            }
            Intent intent = new Builder(LYSAddressFragment.this.getContext(), NavigationTag.LYSLocationAddressAutoComplete).setCountryAndStreet(LYSAddressFragment.this.editAddressAdapter.getAddress()).setListingId(listingId).build();
            LYSAddressFragment.this.editAddressAdapter.setInputEnabled(false);
            LYSAddressFragment.this.startActivityForResult(intent, 200);
        }

        public void dismissErrorSnackbar() {
            LYSAddressFragment.this.dismissSnackbar();
        }
    };
    /* access modifiers changed from: private */
    public Snackbar errorSnackbar;
    LYSJitneyLogger jitneyLogger;
    ListingPromoController listingPromoController;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static Fragment newInstance() {
        return new LYSAddressFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        this.editAddressAdapter = new EditAddressAdapter(getContext(), AirAddressUtil.fromListingAndLocale(this.controller.getListing(), getContext()), this.editAddressListener, savedInstanceState, Mode.ListYourSpace);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        ((ListYourSpaceDLSGraph) CoreApplication.instance(getContext()).component()).inject(this);
        View view = inflater.inflate(C7251R.layout.lys_dls_toolbar_and_recycler_view, parent, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        showTip(C7251R.string.lys_dls_location_section_tip, LYSAddressFragment$$Lambda$11.lambdaFactory$(this));
        this.recyclerView.setAdapter(this.editAddressAdapter);
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.editAddressAdapter.onSaveInstanceState(outState);
    }

    public void onPause() {
        super.onPause();
        KeyboardUtils.dismissSoftKeyboard(getView());
        dismissSnackbar();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.editAddressAdapter.setInputEnabled(true);
        if (resultCode == -1) {
            switch (requestCode) {
                case 200:
                    AirAddress address = (AirAddress) data.getParcelableExtra("address");
                    if (address != null) {
                        this.editAddressAdapter.setAutoCompleteAddress(address);
                        return;
                    }
                    String street = data.getStringExtra("street");
                    if (street != null) {
                        this.editAddressAdapter.setStreet(street);
                        return;
                    }
                    return;
                case REQUEST_CODE_COUNTRY_SELECTED /*201*/:
                    Country country = (Country) data.getParcelableExtra("country");
                    this.editAddressAdapter.setCountry(country.getLocalizedName(), country.getAlpha_2());
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveActionPressed() {
        if (this.editAddressAdapter.validateInputsAndShowError()) {
            saveUserEnteredAddress();
        }
    }

    @OnClick
    public void onClickNext() {
        dismissSnackbar();
        if (this.editAddressAdapter.validateInputsAndShowError()) {
            this.userAction = UserAction.GoToNext;
            saveUserEnteredAddress();
        }
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.editAddressAdapter.hasChanged(AirAddressUtil.fromListing(this.controller.getListing()));
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.LYSLocationAddress;
    }

    /* access modifiers changed from: private */
    public void showTipDialog() {
        this.controller.showTipModal(C7251R.string.lys_dls_location_section_tip_title, C7251R.string.lys_dls_location_section_tip_text, NavigationTag.LYSLocationAddressTip);
    }

    /* access modifiers changed from: private */
    public void dismissSnackbar() {
        if (this.errorSnackbar != null && this.errorSnackbar.isShown()) {
            this.errorSnackbar.dismiss();
        }
    }

    private Strap getAddressFields(AirAddress address) {
        return Strap.make().mo11639kv("street", address.streetAddressOne()).mo11639kv(ListingRequestConstants.JSON_APT_KEY, address.streetAddressTwo()).mo11639kv("city", address.city()).mo11639kv("state", address.state()).mo11639kv(ListingRequestConstants.JSON_ZIP_KEY, address.postalCode()).mo11639kv("country_code", address.countryCode()).mo11640kv(ListingRequestConstants.JSON_USER_DEFINED_LOCATION, false).mo11639kv(ListingRequestConstants.JSON_LIST_YOUR_SPACE_LAST_FINISHED_STEP_ID_KEY, this.controller.getMaxReachedStep().stepId);
    }

    private void saveUserEnteredAddress() {
        if (!canSaveChanges()) {
            navigateInFlow(LYSStep.Address);
            return;
        }
        setLoading(this.editAddressAdapter);
        AirAddress address = this.editAddressAdapter.getAddress();
        this.jitneyLogger.logLocationAddressFormEvent(address, Long.valueOf(this.mAccountManager.getCurrentUserId()), Long.valueOf(this.controller.getListing().getId()));
        if (!this.controller.isListingCreated()) {
            this.controller.getListing().setCity(address.city());
            this.controller.getListing().setState(address.state());
            this.controller.getListing().setCountryCode(address.countryCode());
            LYSCreateListingRequest.forLYSDLS(this.controller.getListing()).withListener((Observer) this.createListingRequestListener).execute(this.requestManager);
            return;
        }
        executeAddressAndRoomsRequest(false);
    }

    static /* synthetic */ void lambda$new$1(LYSAddressFragment lYSAddressFragment, SimpleListingResponse response) {
        lYSAddressFragment.controller.setListing(response.listing);
        lYSAddressFragment.controller.addUploadManagerListenerForListing(lYSAddressFragment.controller.getListing().getId());
        lYSAddressFragment.mAccountManager.incrementListingCount();
        lYSAddressFragment.listingPromoController.refreshListingsInfo();
        lYSAddressFragment.executeAddressAndRoomsRequest(true);
    }

    static /* synthetic */ void lambda$new$2(LYSAddressFragment lYSAddressFragment, AirRequestNetworkException e) {
        lYSAddressFragment.errorSnackbar = NetworkUtil.tryShowErrorWithSnackbar(lYSAddressFragment.getView(), e);
        lYSAddressFragment.setLoadingFinished(false, lYSAddressFragment.editAddressAdapter);
    }

    private void executeAddressAndRoomsRequest(boolean sendRoomsRequests) {
        List<BaseRequestV2<?>> requests = new ArrayList<>();
        requests.add(UpdateListingRequest.forFieldsLYS(this.controller.getListing().getId(), getAddressFields(this.editAddressAdapter.getAddress())));
        if (sendRoomsRequests && FeatureToggles.showHostSideBedDetails()) {
            for (ListingRoom room : this.controller.getBedDetails()) {
                if (room.getId() == 0 && !room.getBedTypes().isEmpty()) {
                    requests.add(new CreateEmptyListingRoomRequest(this.controller.getListing().getId(), room.getRoomNumber()));
                }
            }
        }
        new AirBatchRequest(requests, this.addressAndRoomsListener).execute(this.requestManager);
    }

    static /* synthetic */ void lambda$new$5(LYSAddressFragment lYSAddressFragment, AirBatchResponse response) {
        lYSAddressFragment.controller.setListing(((SimpleListingResponse) response.singleResponse(SimpleListingResponse.class)).listing);
        lYSAddressFragment.editAddressAdapter.setAddress(AirAddressUtil.fromListing(lYSAddressFragment.controller.getListing()));
        FluentIterable<ListingRoom> newRooms = response.filterResponses(ListingRoomResponse.class).transform(LYSAddressFragment$$Lambda$14.lambdaFactory$());
        if (newRooms.isEmpty()) {
            lYSAddressFragment.executeFetchDataBatchRequest();
            return;
        }
        Iterator it = newRooms.iterator();
        while (it.hasNext()) {
            ListingRoom newRoom = (ListingRoom) it.next();
            lYSAddressFragment.controller.getRoomByNumber(newRoom.getRoomNumber()).setId(newRoom.getId());
        }
        lYSAddressFragment.executeBedTypeRequests();
    }

    static /* synthetic */ void lambda$new$6(LYSAddressFragment lYSAddressFragment, AirRequestNetworkException e) {
        lYSAddressFragment.errorSnackbar = NetworkUtil.tryShowErrorWithSnackbar(lYSAddressFragment.getView(), e);
        lYSAddressFragment.setLoadingFinished(false, lYSAddressFragment.editAddressAdapter);
    }

    /* access modifiers changed from: private */
    public void executeBedTypeRequests() {
        List<BaseRequestV2<?>> requests = new ArrayList<>();
        for (ListingRoom room : this.controller.getBedDetails()) {
            for (BedType bedType : ListUtils.ensureNotNull(room.getBedTypes())) {
                requests.add(new UpdateBedTypeRequest(this.controller.getListing().getId(), room.getId(), bedType.getType().serverDescKey, bedType.getQuantity()));
            }
        }
        new AirBatchRequest(requests, this.batchBedTypeListener).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void executeFetchDataBatchRequest() {
        LYSBatchRequestUtil.getListingBatchRequest(this.controller.getListing().getId(), this.batchFetchDataListener).execute(this.requestManager);
    }

    static /* synthetic */ void lambda$new$10(LYSAddressFragment lYSAddressFragment, AirBatchResponse response) {
        lYSAddressFragment.controller = LYSBatchRequestUtil.setListingBatchResponse(lYSAddressFragment.controller, response);
        lYSAddressFragment.editAddressAdapter.setAddress(AirAddressUtil.fromListing(lYSAddressFragment.controller.getListing()));
        lYSAddressFragment.navigateInFlow(LYSStep.Address);
    }
}
