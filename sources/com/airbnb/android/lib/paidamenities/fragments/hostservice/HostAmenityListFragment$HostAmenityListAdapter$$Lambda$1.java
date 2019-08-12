package com.airbnb.android.lib.paidamenities.fragments.hostservice;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.lib.paidamenities.fragments.hostservice.HostAmenityListFragment.HostAmenityListAdapter;

final /* synthetic */ class HostAmenityListFragment$HostAmenityListAdapter$$Lambda$1 implements OnClickListener {
    private final HostAmenityListAdapter arg$1;

    private HostAmenityListFragment$HostAmenityListAdapter$$Lambda$1(HostAmenityListAdapter hostAmenityListAdapter) {
        this.arg$1 = hostAmenityListAdapter;
    }

    public static OnClickListener lambdaFactory$(HostAmenityListAdapter hostAmenityListAdapter) {
        return new HostAmenityListFragment$HostAmenityListAdapter$$Lambda$1(hostAmenityListAdapter);
    }

    public void onClick(View view) {
        HostAmenityListFragment.this.createNewAmenity();
    }
}
