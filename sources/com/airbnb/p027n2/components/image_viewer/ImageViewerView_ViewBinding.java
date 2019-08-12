package com.airbnb.p027n2.components.image_viewer;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.image_viewer.ImageViewerView_ViewBinding */
public class ImageViewerView_ViewBinding implements Unbinder {
    private ImageViewerView target;

    public ImageViewerView_ViewBinding(ImageViewerView target2, View source) {
        this.target = target2;
        target2.imageView = (AirImageView) Utils.findRequiredViewAsType(source, R.id.image, "field 'imageView'", AirImageView.class);
        target2.textView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.caption, "field 'textView'", AirTextView.class);
    }

    public void unbind() {
        ImageViewerView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.imageView = null;
        target2.textView = null;
    }
}
