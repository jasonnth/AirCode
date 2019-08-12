package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.listing.AmenityGroup;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.managelisting.settings.ManageListingAmenityListController.ControllerInterface;
import com.airbnb.p027n2.components.AirToolbar;

public class ManageListingAmenityListFragment extends ManageListingBaseFragment implements ControllerInterface {
    private ManageListingAmenityListController amenityListController;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static ManageListingAmenityListFragment create() {
        return new ManageListingAmenityListFragment();
    }

    public void onRowClicked(int titleRes, AmenityGroup amenityGroup) {
        this.controller.actionExecutor.amenityGroupDetail(titleRes, amenityGroup);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.amenityListController = new ManageListingAmenityListController(this, getContext(), this.controller.shouldShowSelectML(), this.controller.getSelectListingAmenityIds());
        this.amenityListController.setAmenitiesIds(this.controller.getListing().getAmenityIdsArray());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_listing_recycler_view_only, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.amenityListController.getAdapter());
        return view;
    }

    public void dataUpdated() {
        this.amenityListController.setAmenitiesIds(this.controller.getListing().getAmenityIdsArray());
    }

    public void onDestroyView() {
        this.recyclerView.setAdapter(null);
        super.onDestroyView();
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }
}
