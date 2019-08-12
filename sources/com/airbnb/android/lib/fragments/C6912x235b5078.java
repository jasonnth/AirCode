package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.lib.C0880R;

/* renamed from: com.airbnb.android.lib.fragments.UserProfileVerificationsFragment$IdentificationsAdapter$$Lambda$1 */
final /* synthetic */ class C6912x235b5078 implements OnClickListener {
    private static final C6912x235b5078 instance = new C6912x235b5078();

    private C6912x235b5078() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(View view) {
        view.getContext().startActivity(WebViewIntentBuilder.newBuilder(view.getContext(), view.getContext().getString(C0880R.string.verifications_help)).toIntent());
    }
}
