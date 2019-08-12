package com.airbnb.p027n2.collections;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;

/* renamed from: com.airbnb.n2.collections.BannerContainer_ViewBinding */
public class BannerContainer_ViewBinding implements Unbinder {
    private BannerContainer target;

    public BannerContainer_ViewBinding(BannerContainer target2, View source) {
        this.target = target2;
        target2.imageCarousel = (Carousel) Utils.findRequiredViewAsType(source, R.id.image_carousel, "field 'imageCarousel'", Carousel.class);
    }

    public void unbind() {
        BannerContainer target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.imageCarousel = null;
    }
}
