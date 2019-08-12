package com.airbnb.android.core.activities;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.views.AirWebView;
import com.airbnb.p027n2.components.AirToolbar;

public class WebViewActivity_ViewBinding implements Unbinder {
    private WebViewActivity target;

    public WebViewActivity_ViewBinding(WebViewActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public WebViewActivity_ViewBinding(WebViewActivity target2, View source) {
        this.target = target2;
        target2.airWebView = (AirWebView) Utils.findRequiredViewAsType(source, C0716R.C0718id.air_webview, "field 'airWebView'", AirWebView.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0716R.C0718id.toolbar, "field 'toolbar'", AirToolbar.class);
    }

    public void unbind() {
        WebViewActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.airWebView = null;
        target2.toolbar = null;
    }
}
