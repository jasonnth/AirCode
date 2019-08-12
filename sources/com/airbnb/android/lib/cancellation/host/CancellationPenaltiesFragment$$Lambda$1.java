package com.airbnb.android.lib.cancellation.host;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CancellationPenaltiesFragment$$Lambda$1 implements OnClickListener {
    private final CancellationPenaltiesFragment arg$1;

    private CancellationPenaltiesFragment$$Lambda$1(CancellationPenaltiesFragment cancellationPenaltiesFragment) {
        this.arg$1 = cancellationPenaltiesFragment;
    }

    public static OnClickListener lambdaFactory$(CancellationPenaltiesFragment cancellationPenaltiesFragment) {
        return new CancellationPenaltiesFragment$$Lambda$1(cancellationPenaltiesFragment);
    }

    public void onClick(View view) {
        this.arg$1.getActivity().onBackPressed();
    }
}
