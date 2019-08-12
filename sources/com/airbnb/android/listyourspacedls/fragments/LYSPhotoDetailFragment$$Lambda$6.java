package com.airbnb.android.listyourspacedls.fragments;

import android.view.MenuItem;

final /* synthetic */ class LYSPhotoDetailFragment$$Lambda$6 implements Runnable {
    private final MenuItem arg$1;

    private LYSPhotoDetailFragment$$Lambda$6(MenuItem menuItem) {
        this.arg$1 = menuItem;
    }

    public static Runnable lambdaFactory$(MenuItem menuItem) {
        return new LYSPhotoDetailFragment$$Lambda$6(menuItem);
    }

    public void run() {
        this.arg$1.setVisible(false);
    }
}
