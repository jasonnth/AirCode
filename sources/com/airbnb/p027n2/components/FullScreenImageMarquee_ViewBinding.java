package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.FullScreenImageMarquee_ViewBinding */
public class FullScreenImageMarquee_ViewBinding implements Unbinder {
    private FullScreenImageMarquee target;

    public FullScreenImageMarquee_ViewBinding(FullScreenImageMarquee target2, View source) {
        this.target = target2;
        target2.imageView = (AirImageView) Utils.findRequiredViewAsType(source, R.id.image, "field 'imageView'", AirImageView.class);
        target2.titleTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title, "field 'titleTextView'", AirTextView.class);
        target2.descriptionTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.description, "field 'descriptionTextView'", AirTextView.class);
        target2.imageTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.image_title, "field 'imageTextView'", AirTextView.class);
    }

    public void unbind() {
        FullScreenImageMarquee target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.imageView = null;
        target2.titleTextView = null;
        target2.descriptionTextView = null;
        target2.imageTextView = null;
    }
}
