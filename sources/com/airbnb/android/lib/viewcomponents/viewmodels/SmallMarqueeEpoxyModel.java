package com.airbnb.android.lib.viewcomponents.viewmodels;

import com.airbnb.p027n2.components.SmallMarquee;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class SmallMarqueeEpoxyModel extends AirEpoxyModel<SmallMarquee> {
    CharSequence title;
    int titleRes;

    public void bind(SmallMarquee marquee) {
        super.bind(marquee);
        if (this.titleRes != 0) {
            marquee.setMarqueeTitle(this.titleRes);
        } else {
            marquee.setMarqueeTitle(this.title);
        }
    }

    public int getDividerViewType() {
        return 3;
    }
}
