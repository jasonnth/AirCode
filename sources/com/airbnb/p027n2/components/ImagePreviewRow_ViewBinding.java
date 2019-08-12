package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.ImagePreviewRow_ViewBinding */
public class ImagePreviewRow_ViewBinding implements Unbinder {
    private ImagePreviewRow target;

    public ImagePreviewRow_ViewBinding(ImagePreviewRow target2, View source) {
        this.target = target2;
        target2.titleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title, "field 'titleText'", AirTextView.class);
        target2.subtitleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.optional_subtitle, "field 'subtitleText'", AirTextView.class);
        target2.imagePreview = (AirImageView) Utils.findRequiredViewAsType(source, R.id.image_preview, "field 'imagePreview'", AirImageView.class);
        target2.sectionDivider = Utils.findRequiredView(source, R.id.section_divider, "field 'sectionDivider'");
    }

    public void unbind() {
        ImagePreviewRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleText = null;
        target2.subtitleText = null;
        target2.imagePreview = null;
        target2.sectionDivider = null;
    }
}
