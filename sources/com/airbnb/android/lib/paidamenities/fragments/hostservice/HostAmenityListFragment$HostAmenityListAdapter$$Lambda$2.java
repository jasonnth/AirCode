package com.airbnb.android.lib.paidamenities.fragments.hostservice;

import android.view.View;
import android.view.View.OnLongClickListener;
import com.airbnb.android.core.models.PaidAmenity;
import com.airbnb.android.lib.paidamenities.fragments.hostservice.HostAmenityListFragment.HostAmenityListAdapter;

final /* synthetic */ class HostAmenityListFragment$HostAmenityListAdapter$$Lambda$2 implements OnLongClickListener {
    private final HostAmenityListAdapter arg$1;
    private final PaidAmenity arg$2;

    private HostAmenityListFragment$HostAmenityListAdapter$$Lambda$2(HostAmenityListAdapter hostAmenityListAdapter, PaidAmenity paidAmenity) {
        this.arg$1 = hostAmenityListAdapter;
        this.arg$2 = paidAmenity;
    }

    public static OnLongClickListener lambdaFactory$(HostAmenityListAdapter hostAmenityListAdapter, PaidAmenity paidAmenity) {
        return new HostAmenityListFragment$HostAmenityListAdapter$$Lambda$2(hostAmenityListAdapter, paidAmenity);
    }

    public boolean onLongClick(View view) {
        return HostAmenityListAdapter.lambda$amenityToRowEpoxyModel$1(this.arg$1, this.arg$2, view);
    }
}
