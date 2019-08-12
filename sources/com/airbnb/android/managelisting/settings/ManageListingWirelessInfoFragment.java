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
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingWirelessInfo;
import com.airbnb.android.core.requests.WirelessInfoRequest;
import com.airbnb.android.core.responses.WirelessInfoResponse;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import p032rx.Observer;

public class ManageListingWirelessInfoFragment extends ManageListingBaseFragment {
    private ManageListingWirelessInfoAdapter adapter;
    final RequestListener<WirelessInfoResponse> deleteWifiInfoListener = new C0699RL().onResponse(ManageListingWirelessInfoFragment$$Lambda$3.lambdaFactory$(this)).onError(ManageListingWirelessInfoFragment$$Lambda$4.lambdaFactory$(this)).build();
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirButton saveButton;
    @BindView
    AirToolbar toolbar;
    final RequestListener<WirelessInfoResponse> updateWifiInfoListener = new C0699RL().onResponse(ManageListingWirelessInfoFragment$$Lambda$1.lambdaFactory$(this)).onError(ManageListingWirelessInfoFragment$$Lambda$2.lambdaFactory$(this)).build();

    public static ManageListingWirelessInfoFragment create() {
        return new ManageListingWirelessInfoFragment();
    }

    static /* synthetic */ void lambda$new$1(ManageListingWirelessInfoFragment manageListingWirelessInfoFragment, AirRequestNetworkException e) {
        manageListingWirelessInfoFragment.saveButton.setState(State.Normal);
        manageListingWirelessInfoFragment.adapter.setEnabled(true);
        NetworkUtil.tryShowErrorWithSnackbar(manageListingWirelessInfoFragment.getView(), e);
    }

    static /* synthetic */ void lambda$new$3(ManageListingWirelessInfoFragment manageListingWirelessInfoFragment, AirRequestNetworkException e) {
        manageListingWirelessInfoFragment.saveButton.setState(State.Normal);
        manageListingWirelessInfoFragment.adapter.setEnabled(true);
        NetworkUtil.tryShowErrorWithSnackbar(manageListingWirelessInfoFragment.getView(), e);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.adapter = new ManageListingWirelessInfoAdapter(getContext(), this.controller.getListing(), savedInstanceState, ManageListingWirelessInfoFragment$$Lambda$5.lambdaFactory$(this));
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
    public boolean setFlagSecure() {
        return BuildHelper.isReleaseBuild();
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.adapter.hasChanged(this.controller.getListing());
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void saveButtonClicked() {
        if (!canSaveChanges()) {
            this.saveButton.setState(State.Success);
            getFragmentManager().popBackStack();
            return;
        }
        this.saveButton.setState(State.Loading);
        this.adapter.setEnabled(false);
        makeUpdateInfoRequest();
    }

    private void makeUpdateInfoRequest() {
        ListingWirelessInfo wirelessInfo = this.controller.getListing().getWirelessInfo();
        boolean hasPreviousInfo = wirelessInfo != null && wirelessInfo.hasValidId();
        if (this.adapter.hasEmptySsid()) {
            if (hasPreviousInfo) {
                WirelessInfoRequest.delete(wirelessInfo.getId()).withListener((Observer) this.deleteWifiInfoListener).execute(this.requestManager);
                return;
            }
            this.saveButton.setState(State.Normal);
            getFragmentManager().popBackStack();
        } else if (hasPreviousInfo) {
            WirelessInfoRequest.updateExisting(wirelessInfo.getId(), this.adapter.getUpdatedWifiInfo()).withListener((Observer) this.updateWifiInfoListener).execute(this.requestManager);
        } else {
            WirelessInfoRequest.create(this.controller.getListing().getId(), this.adapter.getUpdatedWifiInfo()).withListener((Observer) this.updateWifiInfoListener).execute(this.requestManager);
        }
    }

    /* access modifiers changed from: private */
    public void handleWifiInfoUpdateResult(ListingWirelessInfo wirelessInfo) {
        this.saveButton.setState(State.Success);
        Listing listing = this.controller.getListing();
        if (wirelessInfo != null) {
            listing.setWirelessInfo(wirelessInfo);
        } else {
            listing.setWirelessInfo(new ListingWirelessInfo(listing.getId()));
        }
        this.controller.setListing(listing);
        getFragmentManager().popBackStack();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingWirelessInfo;
    }
}
