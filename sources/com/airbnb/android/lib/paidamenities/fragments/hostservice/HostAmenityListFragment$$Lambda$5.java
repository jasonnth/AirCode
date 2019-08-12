package com.airbnb.android.lib.paidamenities.fragments.hostservice;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class HostAmenityListFragment$$Lambda$5 implements OnClickListener {
    private final HostAmenityListFragment arg$1;

    private HostAmenityListFragment$$Lambda$5(HostAmenityListFragment hostAmenityListFragment) {
        this.arg$1 = hostAmenityListFragment;
    }

    public static OnClickListener lambdaFactory$(HostAmenityListFragment hostAmenityListFragment) {
        return new HostAmenityListFragment$$Lambda$5(hostAmenityListFragment);
    }

    public void onClick(View view) {
        this.arg$1.listener.onExit(this.arg$1.paidAmenities.isEmpty());
    }
}
