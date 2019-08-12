package com.airbnb.android.lib.businesstravel;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class WorkEmailFragment$$Lambda$5 implements OnClickListener {
    private final WorkEmailFragment arg$1;

    private WorkEmailFragment$$Lambda$5(WorkEmailFragment workEmailFragment) {
        this.arg$1 = workEmailFragment;
    }

    public static OnClickListener lambdaFactory$(WorkEmailFragment workEmailFragment) {
        return new WorkEmailFragment$$Lambda$5(workEmailFragment);
    }

    public void onClick(View view) {
        WorkEmailFragment.lambda$onCreateView$4(this.arg$1, view);
    }
}
