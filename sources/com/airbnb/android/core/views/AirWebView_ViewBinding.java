package com.airbnb.android.core.views;

import android.view.View;
import android.webkit.WebView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.RefreshLoader;

public class AirWebView_ViewBinding implements Unbinder {
    private AirWebView target;

    public AirWebView_ViewBinding(AirWebView target2) {
        this(target2, target2);
    }

    public AirWebView_ViewBinding(AirWebView target2, View source) {
        this.target = target2;
        target2.mWebView = (WebView) Utils.findRequiredViewAsType(source, C0716R.C0718id.child_web_view, "field 'mWebView'", WebView.class);
        target2.refreshLoader = (RefreshLoader) Utils.findRequiredViewAsType(source, C0716R.C0718id.loading_row, "field 'refreshLoader'", RefreshLoader.class);
    }

    public void unbind() {
        AirWebView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mWebView = null;
        target2.refreshLoader = null;
    }
}
