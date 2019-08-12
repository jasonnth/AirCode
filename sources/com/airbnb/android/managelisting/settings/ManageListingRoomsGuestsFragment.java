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
import com.airbnb.android.core.enums.BathroomType;
import com.airbnb.android.core.enums.LegacyBedType;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.listing.adapters.RoomsAndGuestsAdapter;
import com.airbnb.android.listing.adapters.RoomsAndGuestsAdapter.Listener;
import com.airbnb.android.listing.adapters.RoomsAndGuestsAdapter.Mode;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import p032rx.Observer;

public class ManageListingRoomsGuestsFragment extends ManageListingBaseFragment {
    private RoomsAndGuestsAdapter adapter;
    private final Listener listener = new Listener() {
        public void bedDetails() {
            ManageListingRoomsGuestsFragment.this.controller.actionExecutor.bedDetails();
        }

        public void logBathroomsSelectNum(float numBathrooms) {
        }

        public void logBathroomsSelectType(BathroomType bathroomType) {
        }

        public void logBedroomsNumRooms(int numRooms) {
        }

        public void logBedroomsNumBeds(int numBeds) {
        }

        public void logBedroomsSelectBedType(LegacyBedType bedType) {
        }

        public void logBedroomsSelectNumGuests(int numGuests) {
        }
    };
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirButton saveButton;
    @BindView
    AirToolbar toolbar;
    final RequestListener<SimpleListingResponse> updateListingListener = new C0699RL().onResponse(ManageListingRoomsGuestsFragment$$Lambda$1.lambdaFactory$(this)).onError(ManageListingRoomsGuestsFragment$$Lambda$2.lambdaFactory$(this)).onComplete(ManageListingRoomsGuestsFragment$$Lambda$3.lambdaFactory$(this)).build();

    public static ManageListingRoomsGuestsFragment create() {
        return (ManageListingRoomsGuestsFragment) FragmentBundler.make(new ManageListingRoomsGuestsFragment()).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.adapter = new RoomsAndGuestsAdapter(this.controller.shouldShowSelectML() ? Mode.SelectManageListing : Mode.ManageListing, getContext(), this.controller.getListing(), this.controller.getListingRooms(), savedInstanceState, this.listener);
    }

    public void dataUpdated() {
        super.dataUpdated();
        this.adapter.updateBedDetailsRows(this.controller.getListingRooms());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_listing_recycler_view_with_save, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.adapter);
        return view;
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void saveClicked() {
        this.adapter.setInputEnabled(false);
        if (!canSaveChanges()) {
            this.saveButton.setState(State.Success);
            getFragmentManager().popBackStack();
            return;
        }
        this.saveButton.setState(State.Loading);
        UpdateListingRequest.forFields(this.controller.getListing().getId(), this.adapter.getSettings()).withListener((Observer) this.updateListingListener).execute(this.requestManager);
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.adapter.hasChanged(this.controller.getListing());
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    static /* synthetic */ void lambda$new$0(ManageListingRoomsGuestsFragment manageListingRoomsGuestsFragment, SimpleListingResponse response) {
        manageListingRoomsGuestsFragment.controller.setListing(response.listing);
        manageListingRoomsGuestsFragment.saveButton.setState(State.Success);
        manageListingRoomsGuestsFragment.getFragmentManager().popBackStack();
    }

    static /* synthetic */ void lambda$new$1(ManageListingRoomsGuestsFragment manageListingRoomsGuestsFragment, AirRequestNetworkException e) {
        NetworkUtil.tryShowErrorWithSnackbar(manageListingRoomsGuestsFragment.getView(), e);
        manageListingRoomsGuestsFragment.saveButton.setState(State.Normal);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingRoomsAndGuests;
    }
}
