package com.airbnb.android.p011p3;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.LoaderFrame;

/* renamed from: com.airbnb.android.p3.P3Activity_ViewBinding */
public class P3Activity_ViewBinding implements Unbinder {
    private P3Activity target;

    public P3Activity_ViewBinding(P3Activity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public P3Activity_ViewBinding(P3Activity target2, View source) {
        this.target = target2;
        target2.rootView = (ViewGroup) Utils.findRequiredViewAsType(source, C7532R.C7534id.root, "field 'rootView'", ViewGroup.class);
        target2.loaderFrame = (LoaderFrame) Utils.findRequiredViewAsType(source, C7532R.C7534id.loader_frame, "field 'loaderFrame'", LoaderFrame.class);
        target2.contentContainer = (FrameLayout) Utils.findRequiredViewAsType(source, C7532R.C7534id.content_container, "field 'contentContainer'", FrameLayout.class);
    }

    public void unbind() {
        P3Activity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.rootView = null;
        target2.loaderFrame = null;
        target2.contentContainer = null;
    }
}
