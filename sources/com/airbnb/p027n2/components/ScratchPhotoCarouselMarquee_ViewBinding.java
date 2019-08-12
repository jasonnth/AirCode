package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.collections.Carousel;

/* renamed from: com.airbnb.n2.components.ScratchPhotoCarouselMarquee_ViewBinding */
public class ScratchPhotoCarouselMarquee_ViewBinding implements Unbinder {
    private ScratchPhotoCarouselMarquee target;

    public ScratchPhotoCarouselMarquee_ViewBinding(ScratchPhotoCarouselMarquee target2, View source) {
        this.target = target2;
        target2.carousel = (Carousel) Utils.findRequiredViewAsType(source, R.id.photo_carousel, "field 'carousel'", Carousel.class);
    }

    public void unbind() {
        ScratchPhotoCarouselMarquee target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.carousel = null;
    }
}
