package com.airbnb.android.managelisting.settings.photos;

final /* synthetic */ class ManagePhotosFragment$$Lambda$4 implements Runnable {
    private final ManagePhotosFragment arg$1;

    private ManagePhotosFragment$$Lambda$4(ManagePhotosFragment managePhotosFragment) {
        this.arg$1 = managePhotosFragment;
    }

    public static Runnable lambdaFactory$(ManagePhotosFragment managePhotosFragment) {
        return new ManagePhotosFragment$$Lambda$4(managePhotosFragment);
    }

    public void run() {
        this.arg$1.updateMenuViewState();
    }
}
