package com.airbnb.android.lib.utils.webintent;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ResolveInfo;

final /* synthetic */ class WebIntentDispatch$$Lambda$1 implements OnClickListener {
    private final WebIntentDispatch arg$1;
    private final Intent arg$2;
    private final WebBrowserListAdapter arg$3;

    private WebIntentDispatch$$Lambda$1(WebIntentDispatch webIntentDispatch, Intent intent, WebBrowserListAdapter webBrowserListAdapter) {
        this.arg$1 = webIntentDispatch;
        this.arg$2 = intent;
        this.arg$3 = webBrowserListAdapter;
    }

    public static OnClickListener lambdaFactory$(WebIntentDispatch webIntentDispatch, Intent intent, WebBrowserListAdapter webBrowserListAdapter) {
        return new WebIntentDispatch$$Lambda$1(webIntentDispatch, intent, webBrowserListAdapter);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.arg$1.startWebBroswerIntent(this.arg$2, (ResolveInfo) this.arg$3.getItem(i));
    }
}
