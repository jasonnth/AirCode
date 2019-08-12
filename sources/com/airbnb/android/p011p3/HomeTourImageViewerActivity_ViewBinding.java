package com.airbnb.android.p011p3;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.android.p3.HomeTourImageViewerActivity_ViewBinding */
public class HomeTourImageViewerActivity_ViewBinding implements Unbinder {
    private HomeTourImageViewerActivity target;

    public HomeTourImageViewerActivity_ViewBinding(HomeTourImageViewerActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public HomeTourImageViewerActivity_ViewBinding(HomeTourImageViewerActivity target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7532R.C7534id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.imageView = (AirImageView) Utils.findRequiredViewAsType(source, C7532R.C7534id.image, "field 'imageView'", AirImageView.class);
    }

    public void unbind() {
        HomeTourImageViewerActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.imageView = null;
    }
}
