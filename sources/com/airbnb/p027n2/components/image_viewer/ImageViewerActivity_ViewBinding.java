package com.airbnb.p027n2.components.image_viewer;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.components.AirToolbar;

/* renamed from: com.airbnb.n2.components.image_viewer.ImageViewerActivity_ViewBinding */
public class ImageViewerActivity_ViewBinding implements Unbinder {
    private ImageViewerActivity target;

    public ImageViewerActivity_ViewBinding(ImageViewerActivity target2, View source) {
        this.target = target2;
        target2.background = Utils.findRequiredView(source, R.id.background, "field 'background'");
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.imageViewer = (ImageViewer) Utils.findRequiredViewAsType(source, R.id.image_viewer, "field 'imageViewer'", ImageViewer.class);
    }

    public void unbind() {
        ImageViewerActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.background = null;
        target2.toolbar = null;
        target2.imageViewer = null;
    }
}
