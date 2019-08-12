package com.airbnb.android.managelisting.settings.photos;

import android.os.Bundle;
import android.support.p002v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.BindView;
import com.airbnb.android.core.models.SelectListingRoom;
import com.airbnb.android.core.models.SelectRoomMedia;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.managelisting.settings.ManageListingBaseFragment;
import com.airbnb.android.managelisting.settings.ManageSelectPhotosEpoxyController;
import com.airbnb.android.managelisting.settings.ManageSelectPhotosEpoxyController.Listener;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.AirToolbar;

public class ManageSelectPhotosFragment extends ManageListingBaseFragment {
    private static final int NUM_COLUMNS = 2;
    private ManageSelectPhotosEpoxyController epoxyController;
    private final Listener listener = new Listener() {
        public void captionDetailShotClicked(SelectRoomMedia detailShot) {
            Toast.makeText(ManageSelectPhotosFragment.this.getContext(), "Under construction", 0).show();
        }

        public void roomHighlightsClicked(SelectListingRoom room) {
            Toast.makeText(ManageSelectPhotosFragment.this.getContext(), "Under construction", 0).show();
        }
    };
    @BindView
    AirRecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static ManageSelectPhotosFragment create() {
        return new ManageSelectPhotosFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_view_pro_photos, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.epoxyController = new ManageSelectPhotosEpoxyController(getContext(), this.controller.getSelectRoomDescriptions(), this.listener);
        this.epoxyController.setSpanCount(2);
        this.epoxyController.setListingRooms(this.controller.getSelectListing().getRooms());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.setSpanSizeLookup(this.epoxyController.getSpanSizeLookup());
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.setEpoxyControllerAndBuildModels(this.epoxyController);
        return view;
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }
}
