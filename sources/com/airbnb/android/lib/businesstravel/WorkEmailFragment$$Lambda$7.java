package com.airbnb.android.lib.businesstravel;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class WorkEmailFragment$$Lambda$7 implements OnClickListener {
    private final WorkEmailFragment arg$1;

    private WorkEmailFragment$$Lambda$7(WorkEmailFragment workEmailFragment) {
        this.arg$1 = workEmailFragment;
    }

    public static OnClickListener lambdaFactory$(WorkEmailFragment workEmailFragment) {
        return new WorkEmailFragment$$Lambda$7(workEmailFragment);
    }

    public void onClick(View view) {
        this.arg$1.showRemoveWorkEmailDialog();
    }
}
