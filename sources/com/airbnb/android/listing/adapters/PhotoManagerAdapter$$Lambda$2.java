package com.airbnb.android.listing.adapters;

import com.airbnb.p027n2.components.photorearranger.PhotoRearrangerAdapter.Listener;
import com.airbnb.p027n2.components.photorearranger.PhotoRearrangerItem;

final /* synthetic */ class PhotoManagerAdapter$$Lambda$2 implements Listener {
    private final PhotoManagerAdapter arg$1;

    private PhotoManagerAdapter$$Lambda$2(PhotoManagerAdapter photoManagerAdapter) {
        this.arg$1 = photoManagerAdapter;
    }

    public static Listener lambdaFactory$(PhotoManagerAdapter photoManagerAdapter) {
        return new PhotoManagerAdapter$$Lambda$2(photoManagerAdapter);
    }

    public void itemSelected(PhotoRearrangerItem photoRearrangerItem) {
        this.arg$1.itemClicked(photoRearrangerItem);
    }
}
