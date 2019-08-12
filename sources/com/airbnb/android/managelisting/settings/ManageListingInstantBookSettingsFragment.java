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
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.ibdeactivation.DeactivateIBActivity;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import p032rx.Observer;

public class ManageListingInstantBookSettingsFragment extends ManageListingBaseFragment {
    private static final int REQUEST_CODE_DEACTIVATE_IB = 1500;
    /* access modifiers changed from: private */
    public ManageListingInstantBookSettingsAdapter adapter;
    private final Listener listener = new Listener() {
        public void onAirbnbRequirementsSelected() {
            ManageListingInstantBookSettingsFragment.this.controller.actionExecutor.guestRequirements();
        }

        public void onDisableInstantBookSelected() {
            Listing listing = ManageListingInstantBookSettingsFragment.this.controller.getListing();
            if (listing.isListed() && listing.isInstantBookEnabled()) {
                ManageListingInstantBookSettingsFragment.this.startActivityForResult(DeactivateIBActivity.newIntent(ManageListingInstantBookSettingsFragment.this.getContext(), listing), 1500);
            } else if (ManageListingInstantBookSettingsFragment.this.adapter != null) {
                ManageListingInstantBookSettingsFragment.this.adapter.setInstantBookDisabled();
            }
        }
    };
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirButton saveButton;
    @BindView
    AirToolbar toolbar;
    final RequestListener<SimpleListingResponse> updateListingListener = new C0699RL().onResponse(ManageListingInstantBookSettingsFragment$$Lambda$1.lambdaFactory$(this)).onError(ManageListingInstantBookSettingsFragment$$Lambda$2.lambdaFactory$(this)).build();

    public static ManageListingInstantBookSettingsFragment create() {
        return (ManageListingInstantBookSettingsFragment) FragmentBundler.make(new ManageListingInstantBookSettingsFragment()).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.adapter = new ManageListingInstantBookSettingsAdapter(this.controller.getListing(), this.listener, savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_listing_recycler_view_with_save, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.adapter);
        return view;
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.adapter.hasChanged(this.controller.getListing());
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
        UpdateListingRequest.forField(this.controller.getListing().getId(), ListingRequestConstants.JSON_INSTANT_BOOK_VISIBILITY_KEY, this.adapter.getCategory().serverKey).withListener((Observer) this.updateListingListener).execute(this.requestManager);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    static /* synthetic */ void lambda$new$0(ManageListingInstantBookSettingsFragment manageListingInstantBookSettingsFragment, SimpleListingResponse response) {
        manageListingInstantBookSettingsFragment.saveButton.setState(State.Success);
        manageListingInstantBookSettingsFragment.controller.setListing(response.listing);
        manageListingInstantBookSettingsFragment.getFragmentManager().popBackStack();
    }

    static /* synthetic */ void lambda$new$1(ManageListingInstantBookSettingsFragment manageListingInstantBookSettingsFragment, AirRequestNetworkException e) {
        manageListingInstantBookSettingsFragment.saveButton.setState(State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(manageListingInstantBookSettingsFragment.getView(), e);
        manageListingInstantBookSettingsFragment.adapter.setInputEnabled(true);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingInstantBooking;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1500) {
            this.controller.actionExecutor.invalidateData();
            if (resultCode == -1) {
                this.adapter.setInstantBookDisabled();
            } else {
                this.adapter.setInstantBookEnabled();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
