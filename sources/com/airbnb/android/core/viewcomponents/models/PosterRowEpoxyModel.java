package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.p027n2.components.PosterRow;
import com.airbnb.p027n2.components.PosterRow.PosterOrientation;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class PosterRowEpoxyModel extends AirEpoxyModel<PosterRow> {
    String imageUrl;
    PosterOrientation posterOrientation;
    String subtitle;
    String title;

    public void bind(PosterRow view) {
        super.bind(view);
        view.setTitle(this.title);
        view.setSubtitle(this.subtitle);
        view.setImageUrl(this.imageUrl);
        view.setPosterOrientation(this.posterOrientation);
    }
}
