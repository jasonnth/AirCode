package com.airbnb.android.places.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.places.views.ResyTimeSlotView;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class ResyTimeSlotEpoxyModel extends AirEpoxyModel<ResyTimeSlotView> {
    OnClickListener clickListener;
    boolean isLast;
    boolean isSelected;
    String subtitle;
    String title;

    public void bind(ResyTimeSlotView view) {
        super.bind(view);
        view.setTitle(this.title);
        view.setSubtitle(this.subtitle);
        view.setRightMargin(this.isLast);
        view.setSelected(this.isSelected);
        view.setOnClickListener(this.clickListener);
    }
}
