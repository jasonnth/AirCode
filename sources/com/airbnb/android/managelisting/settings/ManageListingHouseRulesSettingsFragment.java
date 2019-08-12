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
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.requests.GuestControlsRequest;
import com.airbnb.android.core.requests.ListingRequest;
import com.airbnb.android.core.responses.GuestControlsResponse;
import com.airbnb.android.core.responses.ListingResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.listing.adapters.HouseRulesSettingsAdapter;
import com.airbnb.android.listing.adapters.HouseRulesSettingsAdapter.Listener;
import com.airbnb.android.listing.utils.TextSetting;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import p032rx.Observer;

public class ManageListingHouseRulesSettingsFragment extends ManageListingBaseFragment implements Listener {
    private HouseRulesSettingsAdapter adapter;
    final RequestListener<ListingResponse> listingListener = new C0699RL().onResponse(ManageListingHouseRulesSettingsFragment$$Lambda$1.lambdaFactory$(this)).onError(ManageListingHouseRulesSettingsFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirButton saveButton;
    @BindView
    AirToolbar toolbar;
    final RequestListener<GuestControlsResponse> updateGuestControlsListener = new C0699RL().onResponse(ManageListingHouseRulesSettingsFragment$$Lambda$3.lambdaFactory$(this)).onError(ManageListingHouseRulesSettingsFragment$$Lambda$4.lambdaFactory$(this)).build();

    static /* synthetic */ void lambda$new$0(ManageListingHouseRulesSettingsFragment manageListingHouseRulesSettingsFragment, ListingResponse response) {
        manageListingHouseRulesSettingsFragment.saveButton.setState(State.Success);
        manageListingHouseRulesSettingsFragment.controller.setListing(response.listing);
        manageListingHouseRulesSettingsFragment.getFragmentManager().popBackStack();
    }

    static /* synthetic */ void lambda$new$2(ManageListingHouseRulesSettingsFragment manageListingHouseRulesSettingsFragment, AirRequestNetworkException error) {
        manageListingHouseRulesSettingsFragment.adapter.setInputEnabled(true);
        manageListingHouseRulesSettingsFragment.saveButton.setState(State.Normal);
        NetworkUtil.tryShowRetryableErrorWithSnackbar(manageListingHouseRulesSettingsFragment.getView(), (NetworkException) error, ManageListingHouseRulesSettingsFragment$$Lambda$5.lambdaFactory$(manageListingHouseRulesSettingsFragment));
    }

    static /* synthetic */ void lambda$new$4(ManageListingHouseRulesSettingsFragment manageListingHouseRulesSettingsFragment, AirRequestNetworkException error) {
        manageListingHouseRulesSettingsFragment.adapter.setInputEnabled(true);
        manageListingHouseRulesSettingsFragment.saveButton.setState(State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(manageListingHouseRulesSettingsFragment.getView(), error);
    }

    public static ManageListingHouseRulesSettingsFragment create() {
        return new ManageListingHouseRulesSettingsFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.adapter = new HouseRulesSettingsAdapter(getContext(), this.controller.getListing(), this.controller.getListing().getGuestControls(), this, savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_listing_recycler_view_with_save, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.adapter);
        return view;
    }

    public void dataUpdated() {
        super.dataUpdated();
        this.adapter.updateAdditionalRulesAndExpectationsRows(this.controller.getListing());
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.adapter.hasChanged(this.controller.getListing().getGuestControls());
    }

    @OnClick
    public void onSave() {
        this.adapter.setInputEnabled(false);
        if (!canSaveChanges()) {
            this.saveButton.setState(State.Success);
            getFragmentManager().popBackStack();
            return;
        }
        this.saveButton.setState(State.Loading);
        GuestControlsRequest.updateGuestControls(this.controller.getListing().getId(), this.adapter.getGuestControls()).withListener((Observer) this.updateGuestControlsListener).execute(this.requestManager);
    }

    public void onAdditionalHouseRulesClicked() {
        this.controller.actionExecutor.textSetting(TextSetting.ManageListingAdditionalRules);
    }

    public void onGuestExpectationsClicked() {
        this.controller.actionExecutor.guestExpectations();
    }

    public void onLearnMoreClicked(View v) {
        this.controller.actionExecutor.houseRulesLegalInfo();
    }

    /* access modifiers changed from: private */
    public void executeListingRequest() {
        ListingRequest.forV1LegacyManageListing(this.controller.getListing().getId()).withListener((Observer) this.listingListener).skipCache().execute(this.requestManager);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingHouseRules;
    }
}
