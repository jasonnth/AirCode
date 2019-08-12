package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.SelectRoomMedia;

final /* synthetic */ class ManageSelectPhotosEpoxyController$$Lambda$4 implements OnClickListener {
    private final ManageSelectPhotosEpoxyController arg$1;
    private final SelectRoomMedia arg$2;

    private ManageSelectPhotosEpoxyController$$Lambda$4(ManageSelectPhotosEpoxyController manageSelectPhotosEpoxyController, SelectRoomMedia selectRoomMedia) {
        this.arg$1 = manageSelectPhotosEpoxyController;
        this.arg$2 = selectRoomMedia;
    }

    public static OnClickListener lambdaFactory$(ManageSelectPhotosEpoxyController manageSelectPhotosEpoxyController, SelectRoomMedia selectRoomMedia) {
        return new ManageSelectPhotosEpoxyController$$Lambda$4(manageSelectPhotosEpoxyController, selectRoomMedia);
    }

    public void onClick(View view) {
        this.arg$1.listener.captionDetailShotClicked(this.arg$2);
    }
}
