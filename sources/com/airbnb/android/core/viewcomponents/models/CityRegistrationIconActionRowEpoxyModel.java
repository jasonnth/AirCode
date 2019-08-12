package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.p027n2.components.CityRegistrationIconActionRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class CityRegistrationIconActionRowEpoxyModel extends AirEpoxyModel<CityRegistrationIconActionRow> {
    CharSequence action;
    int actionRes;
    OnClickListener clickListener;
    int iconDrawableRes;
    CharSequence subtitle;
    int subtitleRes;
    CharSequence title;
    int titleRes;

    public void bind(CityRegistrationIconActionRow view) {
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
        if (this.actionRes != 0) {
            view.setAction(this.actionRes);
        } else {
            view.setAction(this.action);
        }
        if (this.iconDrawableRes != 0) {
            view.setIconRes(this.iconDrawableRes);
        } else {
            view.hideIcon();
        }
        if (this.clickListener != null) {
            view.setOnClickListener(this.clickListener);
        }
    }
}
