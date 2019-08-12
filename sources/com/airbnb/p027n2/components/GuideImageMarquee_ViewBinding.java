package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.GuideImageMarquee_ViewBinding */
public class GuideImageMarquee_ViewBinding implements Unbinder {
    private GuideImageMarquee target;

    public GuideImageMarquee_ViewBinding(GuideImageMarquee target2, View source) {
        this.target = target2;
        target2.imageView = (AirImageView) Utils.findRequiredViewAsType(source, R.id.marquee_image, "field 'imageView'", AirImageView.class);
    }

    public void unbind() {
        GuideImageMarquee target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.imageView = null;
    }
}
