package com.airbnb.android.core.host;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class HostReactivationFragment$$Lambda$6 implements OnClickListener {
    private final HostReactivationFragment arg$1;

    private HostReactivationFragment$$Lambda$6(HostReactivationFragment hostReactivationFragment) {
        this.arg$1 = hostReactivationFragment;
    }

    public static OnClickListener lambdaFactory$(HostReactivationFragment hostReactivationFragment) {
        return new HostReactivationFragment$$Lambda$6(hostReactivationFragment);
    }

    public void onClick(View view) {
        this.arg$1.makeHostReactivationCopyRequest();
    }
}
