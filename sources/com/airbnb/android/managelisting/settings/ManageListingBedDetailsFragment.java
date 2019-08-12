package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.listing.adapters.BedDetailsAdapter;
import com.airbnb.android.listing.adapters.BedDetailsAdapter.Listener;
import com.airbnb.android.listing.adapters.BedDetailsAdapter.Mode;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.components.AirToolbar;

public class ManageListingBedDetailsFragment extends ManageListingBaseFragment {
    private BedDetailsAdapter adapter;
    private final Listener listener = new Listener() {
        public void roomSelected(int roomNumber) {
            ManageListingBedDetailsFragment.this.controller.actionExecutor.singleRoomBedDetails(roomNumber);
        }
    };
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static ManageListingBedDetailsFragment create() {
        return new ManageListingBedDetailsFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.adapter = new BedDetailsAdapter(Mode.ManageListing, getContext(), this.controller.getListingRooms(), this.controller.getListing().getBedrooms(), this.listener);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_listing_recycler_view_only, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.adapter);
        return view;
    }

    public void dataUpdated() {
        super.dataUpdated();
        this.adapter.updateRooms(this.controller.getListingRooms());
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingBedDetails;
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }
}
