package com.airbnb.android.lib.host.stats;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class HostDemandsDetailFragment$$Lambda$6 implements OnClickListener {
    private final HostDemandsDetailFragment arg$1;

    private HostDemandsDetailFragment$$Lambda$6(HostDemandsDetailFragment hostDemandsDetailFragment) {
        this.arg$1 = hostDemandsDetailFragment;
    }

    public static OnClickListener lambdaFactory$(HostDemandsDetailFragment hostDemandsDetailFragment) {
        return new HostDemandsDetailFragment$$Lambda$6(hostDemandsDetailFragment);
    }

    public void onClick(View view) {
        HostDemandsDetailFragment.lambda$setupListingSelector$2(this.arg$1, view);
    }
}
