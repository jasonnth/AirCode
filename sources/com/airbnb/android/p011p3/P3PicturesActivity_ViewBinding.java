package com.airbnb.android.p011p3;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.image_viewer.ImageViewer;

/* renamed from: com.airbnb.android.p3.P3PicturesActivity_ViewBinding */
public class P3PicturesActivity_ViewBinding implements Unbinder {
    private P3PicturesActivity target;

    public P3PicturesActivity_ViewBinding(P3PicturesActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public P3PicturesActivity_ViewBinding(P3PicturesActivity target2, View source) {
        this.target = target2;
        target2.background = Utils.findRequiredView(source, C7532R.C7534id.background, "field 'background'");
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7532R.C7534id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.imageViewer = (ImageViewer) Utils.findRequiredViewAsType(source, C7532R.C7534id.image_viewer, "field 'imageViewer'", ImageViewer.class);
    }

    public void unbind() {
        P3PicturesActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.background = null;
        target2.toolbar = null;
        target2.imageViewer = null;
    }
}
