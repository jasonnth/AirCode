package com.airbnb.android.lib.host.stats;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class HostListingSelectorFragment$$Lambda$2 implements OnClickListener {
    private final HostListingSelectorFragment arg$1;

    private HostListingSelectorFragment$$Lambda$2(HostListingSelectorFragment hostListingSelectorFragment) {
        this.arg$1 = hostListingSelectorFragment;
    }

    public static OnClickListener lambdaFactory$(HostListingSelectorFragment hostListingSelectorFragment) {
        return new HostListingSelectorFragment$$Lambda$2(hostListingSelectorFragment);
    }

    public void onClick(View view) {
        this.arg$1.getActivity().onBackPressed();
    }
}
