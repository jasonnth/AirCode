package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.p027n2.components.LabelRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class LabelRowEpoxyModel extends AirEpoxyModel<LabelRow> {
    int labelRes;
    CharSequence labelText;
    int subtitleRes;
    CharSequence subtitleText;
    int titleRes;
    CharSequence titleText;

    public void bind(LabelRow row) {
        super.bind(row);
        if (this.labelRes != 0) {
            row.setLabel(this.labelRes);
        } else {
            row.setLabel(this.labelText);
        }
        if (this.titleRes != 0) {
            row.setTitle(this.titleRes);
        } else {
            row.setTitle(this.titleText);
        }
        if (this.subtitleRes != 0) {
            row.setSubtitle(this.subtitleRes);
        } else {
            row.setSubtitle(this.subtitleText);
        }
    }

    public int getDividerViewType() {
        return 0;
    }
}
