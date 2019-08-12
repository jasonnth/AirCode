package com.airbnb.android.login.p339ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.login.oauth.OAuthOption;

/* renamed from: com.airbnb.android.login.ui.MoreOptionsActivity$$Lambda$1 */
final /* synthetic */ class MoreOptionsActivity$$Lambda$1 implements OnClickListener {
    private final MoreOptionsActivity arg$1;
    private final OAuthOption arg$2;

    private MoreOptionsActivity$$Lambda$1(MoreOptionsActivity moreOptionsActivity, OAuthOption oAuthOption) {
        this.arg$1 = moreOptionsActivity;
        this.arg$2 = oAuthOption;
    }

    public static OnClickListener lambdaFactory$(MoreOptionsActivity moreOptionsActivity, OAuthOption oAuthOption) {
        return new MoreOptionsActivity$$Lambda$1(moreOptionsActivity, oAuthOption);
    }

    public void onClick(View view) {
        MoreOptionsActivity.lambda$init$0(this.arg$1, this.arg$2, view);
    }
}
