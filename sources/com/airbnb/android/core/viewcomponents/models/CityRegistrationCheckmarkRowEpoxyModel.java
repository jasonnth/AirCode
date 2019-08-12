package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.p027n2.components.CityRegistrationCheckmarkRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class CityRegistrationCheckmarkRowEpoxyModel extends AirEpoxyModel<CityRegistrationCheckmarkRow> {
    int iconDrawableRes;
    CharSequence subtitle;
    int subtitleRes;
    CharSequence title;
    int titleRes;

    public void bind(CityRegistrationCheckmarkRow view) {
        super.bind(view);
        if (this.titleRes != 0) {
            view.setTitle(this.titleRes);
        } else {
            view.setTitle(this.title);
        }
        if (this.subtitleRes != 0) {
            view.setSubtitle(this.subtitleRes);
        } else {
            view.setSubtitle(this.subtitle);
        }
        if (this.iconDrawableRes != 0) {
            view.setIcon(this.iconDrawableRes);
        } else {
            view.clearIcon();
        }
    }

    public int getDividerViewType() {
        return 0;
    }
}
