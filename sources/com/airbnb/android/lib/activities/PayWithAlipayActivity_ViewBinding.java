package com.airbnb.android.lib.activities;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.AirWebView;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.android.lib.C0880R;

public class PayWithAlipayActivity_ViewBinding implements Unbinder {
    private PayWithAlipayActivity target;

    public PayWithAlipayActivity_ViewBinding(PayWithAlipayActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public PayWithAlipayActivity_ViewBinding(PayWithAlipayActivity target2, View source) {
        this.target = target2;
        target2.airWebView = (AirWebView) Utils.findRequiredViewAsType(source, C0880R.C0882id.web_view, "field 'airWebView'", AirWebView.class);
        target2.loaderFrame = (LoaderFrame) Utils.findRequiredViewAsType(source, C0880R.C0882id.loader_frame, "field 'loaderFrame'", LoaderFrame.class);
    }

    public void unbind() {
        PayWithAlipayActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.airWebView = null;
        target2.loaderFrame = null;
    }
}
