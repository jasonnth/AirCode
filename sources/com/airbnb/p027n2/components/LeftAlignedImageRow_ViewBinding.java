package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.LeftAlignedImageRow_ViewBinding */
public class LeftAlignedImageRow_ViewBinding implements Unbinder {
    private LeftAlignedImageRow target;

    public LeftAlignedImageRow_ViewBinding(LeftAlignedImageRow target2, View source) {
        this.target = target2;
        target2.imageView = (AirImageView) Utils.findRequiredViewAsType(source, R.id.image, "field 'imageView'", AirImageView.class);
        target2.titleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title, "field 'titleText'", AirTextView.class);
        target2.subtitleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.subtitle, "field 'subtitleText'", AirTextView.class);
    }

    public void unbind() {
        LeftAlignedImageRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.imageView = null;
        target2.titleText = null;
        target2.subtitleText = null;
    }
}
