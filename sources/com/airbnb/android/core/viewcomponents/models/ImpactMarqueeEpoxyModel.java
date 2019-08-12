package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.p027n2.components.ImpactMarquee;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class ImpactMarqueeEpoxyModel extends AirEpoxyModel<ImpactMarquee> {
    int backgroundColor;
    CharSequence subtitle;
    CharSequence title;
    int titleRes;

    public void bind(ImpactMarquee view) {
        super.bind(view);
        if (this.titleRes != 0) {
            view.setTitle(this.titleRes);
        } else {
            view.setTitle(this.title);
        }
        view.setSubtitle(this.subtitle);
        if (this.backgroundColor != 0) {
            view.setBackgroundColor(this.backgroundColor);
        }
    }

    public int getDividerViewType() {
        return 3;
    }
}
