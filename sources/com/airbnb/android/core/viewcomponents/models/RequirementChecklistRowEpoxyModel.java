package com.airbnb.android.core.viewcomponents.models;

import android.graphics.drawable.Drawable;
import com.airbnb.android.core.utils.Check;
import com.airbnb.p027n2.components.RequirementChecklistRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class RequirementChecklistRowEpoxyModel extends AirEpoxyModel<RequirementChecklistRow> {
    private static final int NO_RES_ID = 0;
    int rowDrawableRes;
    CharSequence title;
    int titleRes;

    public int getDividerViewType() {
        return 4;
    }

    public void bind(RequirementChecklistRow row) {
        super.bind(row);
        if (this.titleRes != 0) {
            row.setTitle(this.titleRes);
        } else {
            row.setTitle(this.title);
        }
        if (this.rowDrawableRes != 0) {
            row.setRowDrawable(this.rowDrawableRes);
        } else {
            row.setRowDrawable((Drawable) null);
        }
    }

    public RequirementChecklistRowEpoxyModel title(CharSequence title2) {
        this.title = title2;
        this.titleRes = 0;
        return this;
    }

    public RequirementChecklistRowEpoxyModel title(int titleRes2) {
        Check.argument(titleRes2 > 0);
        this.titleRes = titleRes2;
        return this;
    }
}
