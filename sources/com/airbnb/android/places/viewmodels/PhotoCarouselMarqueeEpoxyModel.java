package com.airbnb.android.places.viewmodels;

import com.airbnb.p027n2.components.ScratchPhotoCarouselMarquee;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import java.util.ArrayList;
import java.util.List;

public abstract class PhotoCarouselMarqueeEpoxyModel extends AirEpoxyModel<ScratchPhotoCarouselMarquee> {
    List<String> photoUrls;

    public void bind(ScratchPhotoCarouselMarquee view) {
        super.bind(view);
        view.setPhotoUrls(this.photoUrls);
    }

    public void unbind(ScratchPhotoCarouselMarquee view) {
        super.unbind(view);
        view.setPhotoUrls(new ArrayList());
    }

    public int getDividerViewType() {
        return 2;
    }
}
