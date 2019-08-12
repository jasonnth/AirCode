package com.airbnb.android.login.p339ui;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.android.login.ui.MoreOptionsActivity$$Lambda$2 */
final /* synthetic */ class MoreOptionsActivity$$Lambda$2 implements OnClickListener {
    private final MoreOptionsActivity arg$1;

    private MoreOptionsActivity$$Lambda$2(MoreOptionsActivity moreOptionsActivity) {
        this.arg$1 = moreOptionsActivity;
    }

    public static OnClickListener lambdaFactory$(MoreOptionsActivity moreOptionsActivity) {
        return new MoreOptionsActivity$$Lambda$2(moreOptionsActivity);
    }

    public void onClick(View view) {
        MoreOptionsActivity.lambda$init$1(this.arg$1, view);
    }
}
