package com.airbnb.android.lib.host.stats;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Listing;

final /* synthetic */ class HostListingsAdapter$$Lambda$2 implements OnClickListener {
    private final HostListingsAdapter arg$1;
    private final Listing arg$2;

    private HostListingsAdapter$$Lambda$2(HostListingsAdapter hostListingsAdapter, Listing listing) {
        this.arg$1 = hostListingsAdapter;
        this.arg$2 = listing;
    }

    public static OnClickListener lambdaFactory$(HostListingsAdapter hostListingsAdapter, Listing listing) {
        return new HostListingsAdapter$$Lambda$2(hostListingsAdapter, listing);
    }

    public void onClick(View view) {
        this.arg$1.callback.onListingClicked(this.arg$2);
    }
}
