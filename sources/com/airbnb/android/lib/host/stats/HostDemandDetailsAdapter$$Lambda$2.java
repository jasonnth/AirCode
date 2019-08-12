package com.airbnb.android.lib.host.stats;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.intents.P3ActivityIntents;
import com.airbnb.android.core.models.Listing;

final /* synthetic */ class HostDemandDetailsAdapter$$Lambda$2 implements OnClickListener {
    private final Listing arg$1;

    private HostDemandDetailsAdapter$$Lambda$2(Listing listing) {
        this.arg$1 = listing;
    }

    public static OnClickListener lambdaFactory$(Listing listing) {
        return new HostDemandDetailsAdapter$$Lambda$2(listing);
    }

    public void onClick(View view) {
        view.getContext().startActivity(P3ActivityIntents.withListing(view.getContext(), this.arg$1));
    }
}
