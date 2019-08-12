package com.airbnb.android.places.viewmodels;

import android.support.p000v4.content.ContextCompat;
import com.airbnb.android.places.C7627R;
import com.airbnb.android.places.views.PlaceActivityHeaderView;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class PlaceActivityHeaderEpoxyModel extends AirEpoxyModel<PlaceActivityHeaderView> {
    String actionKicker;
    int actionKickerColor;
    String boldSubtitle;
    String helpText;
    String title;

    public void bind(PlaceActivityHeaderView view) {
        int colorToUse;
        super.bind(view);
        view.setTitle(this.title);
        view.setSubtitle(this.boldSubtitle, this.helpText);
        if (this.actionKickerColor != 0) {
            colorToUse = this.actionKickerColor;
        } else {
            colorToUse = ContextCompat.getColor(view.getContext(), C7627R.color.n2_hof);
        }
        view.setActionKicker(this.actionKicker, colorToUse);
    }

    public int getDividerViewType() {
        return 0;
    }
}
