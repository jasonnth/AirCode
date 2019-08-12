package com.airbnb.android.sharing.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.sharing.shareables.Shareable;

final /* synthetic */ class ShareAdapter$$Lambda$1 implements OnClickListener {
    private final ResolveInfo arg$1;
    private final Intent arg$2;
    private final Shareable arg$3;
    private final Context arg$4;

    private ShareAdapter$$Lambda$1(ResolveInfo resolveInfo, Intent intent, Shareable shareable, Context context) {
        this.arg$1 = resolveInfo;
        this.arg$2 = intent;
        this.arg$3 = shareable;
        this.arg$4 = context;
    }

    public static OnClickListener lambdaFactory$(ResolveInfo resolveInfo, Intent intent, Shareable shareable, Context context) {
        return new ShareAdapter$$Lambda$1(resolveInfo, intent, shareable, context);
    }

    public void onClick(View view) {
        ShareAdapter.lambda$new$0(this.arg$1, this.arg$2, this.arg$3, this.arg$4, view);
    }
}
