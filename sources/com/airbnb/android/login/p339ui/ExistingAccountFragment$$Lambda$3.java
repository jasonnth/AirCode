package com.airbnb.android.login.p339ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.login.oauth.OAuthOption;

/* renamed from: com.airbnb.android.login.ui.ExistingAccountFragment$$Lambda$3 */
final /* synthetic */ class ExistingAccountFragment$$Lambda$3 implements OnClickListener {
    private final ExistingAccountFragment arg$1;
    private final OAuthOption arg$2;

    private ExistingAccountFragment$$Lambda$3(ExistingAccountFragment existingAccountFragment, OAuthOption oAuthOption) {
        this.arg$1 = existingAccountFragment;
        this.arg$2 = oAuthOption;
    }

    public static OnClickListener lambdaFactory$(ExistingAccountFragment existingAccountFragment, OAuthOption oAuthOption) {
        return new ExistingAccountFragment$$Lambda$3(existingAccountFragment, oAuthOption);
    }

    public void onClick(View view) {
        this.arg$1.logInWithOAuthOption(this.arg$2);
    }
}
