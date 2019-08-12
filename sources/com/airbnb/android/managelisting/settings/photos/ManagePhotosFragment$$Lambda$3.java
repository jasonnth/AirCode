package com.airbnb.android.managelisting.settings.photos;

import com.airbnb.android.listing.adapters.PhotoManagerAdapter.Listener;

final /* synthetic */ class ManagePhotosFragment$$Lambda$3 implements Listener {
    private final ManagePhotosFragment arg$1;

    private ManagePhotosFragment$$Lambda$3(ManagePhotosFragment managePhotosFragment) {
        this.arg$1 = managePhotosFragment;
    }

    public static Listener lambdaFactory$(ManagePhotosFragment managePhotosFragment) {
        return new ManagePhotosFragment$$Lambda$3(managePhotosFragment);
    }

    public void photoDetailsRequested(long j) {
        this.arg$1.photoSelected(j);
    }
}
