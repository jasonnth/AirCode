package com.airbnb.android.managelisting.settings.photos;

import android.view.MenuItem;

final /* synthetic */ class PhotoFragment$$Lambda$5 implements Runnable {
    private final MenuItem arg$1;

    private PhotoFragment$$Lambda$5(MenuItem menuItem) {
        this.arg$1 = menuItem;
    }

    public static Runnable lambdaFactory$(MenuItem menuItem) {
        return new PhotoFragment$$Lambda$5(menuItem);
    }

    public void run() {
        this.arg$1.setVisible(false);
    }
}
