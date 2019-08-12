package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.ImageRow_ViewBinding */
public final class ImageRow_ViewBinding implements Unbinder {
    private ImageRow target;

    public ImageRow_ViewBinding(ImageRow target2, View source) {
        this.target = target2;
        target2.title = (AirTextView) Utils.findRequiredViewAsType(source, R.id.image_row_title, "field 'title'", AirTextView.class);
        target2.subtitle = (AirTextView) Utils.findRequiredViewAsType(source, R.id.image_row_subtitle, "field 'subtitle'", AirTextView.class);
        target2.image = (AirImageView) Utils.findRequiredViewAsType(source, R.id.image_row_image, "field 'image'", AirImageView.class);
    }

    public void unbind() {
        ImageRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.title = null;
        target2.subtitle = null;
        target2.image = null;
    }
}
