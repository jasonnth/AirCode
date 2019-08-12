package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.p027n2.components.ValueRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class ValueRowEpoxyModel extends AirEpoxyModel<ValueRow> {
    int subtitleRes;
    CharSequence subtitleText;
    int titleRes;
    CharSequence titleText;
    int valueRes;
    CharSequence valueText;

    public void bind(ValueRow row) {
        super.bind(row);
        if (this.valueRes != 0) {
            row.setValue(this.valueRes);
        } else {
            row.setValue(this.valueText);
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
