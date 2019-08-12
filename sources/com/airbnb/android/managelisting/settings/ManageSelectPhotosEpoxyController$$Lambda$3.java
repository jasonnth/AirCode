package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.SelectListingRoom;

final /* synthetic */ class ManageSelectPhotosEpoxyController$$Lambda$3 implements OnClickListener {
    private final ManageSelectPhotosEpoxyController arg$1;
    private final SelectListingRoom arg$2;

    private ManageSelectPhotosEpoxyController$$Lambda$3(ManageSelectPhotosEpoxyController manageSelectPhotosEpoxyController, SelectListingRoom selectListingRoom) {
        this.arg$1 = manageSelectPhotosEpoxyController;
        this.arg$2 = selectListingRoom;
    }

    public static OnClickListener lambdaFactory$(ManageSelectPhotosEpoxyController manageSelectPhotosEpoxyController, SelectListingRoom selectListingRoom) {
        return new ManageSelectPhotosEpoxyController$$Lambda$3(manageSelectPhotosEpoxyController, selectListingRoom);
    }

    public void onClick(View view) {
        this.arg$1.listener.roomHighlightsClicked(this.arg$2);
    }
}
