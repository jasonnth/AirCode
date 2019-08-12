package com.airbnb.android.lib.host.stats;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.lib.host.stats.HostListingsAdapter.Callback;

final /* synthetic */ class HostListingsAdapter$$Lambda$1 implements OnClickListener {
    private final Callback arg$1;

    private HostListingsAdapter$$Lambda$1(Callback callback) {
        this.arg$1 = callback;
    }

    public static OnClickListener lambdaFactory$(Callback callback) {
        return new HostListingsAdapter$$Lambda$1(callback);
    }

    public void onClick(View view) {
        this.arg$1.onAllListingsClicked();
    }
}
