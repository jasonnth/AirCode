package com.airbnb.p027n2.components.photorearranger;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.photorearranger.RearrangableLabeledPhotoCell_ViewBinding */
public class RearrangableLabeledPhotoCell_ViewBinding implements Unbinder {
    private RearrangableLabeledPhotoCell target;

    public RearrangableLabeledPhotoCell_ViewBinding(RearrangableLabeledPhotoCell target2, View source) {
        this.target = target2;
        target2.imageView = (AirImageView) Utils.findRequiredViewAsType(source, R.id.photo, "field 'imageView'", AirImageView.class);
        target2.labelView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.label, "field 'labelView'", AirTextView.class);
        target2.placeholderTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.placeholder_text, "field 'placeholderTextView'", AirTextView.class);
    }

    public void unbind() {
        RearrangableLabeledPhotoCell target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.imageView = null;
        target2.labelView = null;
        target2.placeholderTextView = null;
    }
}
