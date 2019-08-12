package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.p027n2.components.LabeledSectionRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class LabeledSectionRowEpoxyModel extends AirEpoxyModel<LabeledSectionRow> {
    int actionRes;
    CharSequence actionText;
    int bodyRes;
    CharSequence bodyText;
    OnClickListener clickListener;
    int labelIconRes;
    int labelRes;
    CharSequence labelText;
    int titleRes;
    CharSequence titleText;

    public void bind(LabeledSectionRow row) {
        super.bind(row);
        if (this.labelRes != 0) {
            row.setLabelText(this.labelRes);
        } else {
            row.setLabelText(this.labelText);
        }
        if (this.titleRes != 0) {
            row.setTitleText(this.titleRes);
        } else {
            row.setTitleText(this.titleText);
        }
        if (this.bodyRes != 0) {
            row.setBodyText(this.bodyRes);
        } else {
            row.setBodyText(this.bodyText);
        }
        if (this.actionRes != 0) {
            row.setActionText(this.actionRes);
        } else {
            row.setActionText(this.actionText);
        }
        if (this.labelIconRes != 0) {
            row.setLabelBackground(this.labelIconRes);
        }
        row.setOnClickListener(this.clickListener);
    }

    public void unbind(LabeledSectionRow view) {
        super.unbind(view);
        view.setOnClickListener(null);
    }
}
