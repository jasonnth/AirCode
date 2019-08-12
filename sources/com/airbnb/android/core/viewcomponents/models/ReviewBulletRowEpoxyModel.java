package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.utils.Check;
import com.airbnb.p027n2.components.ReviewBulletRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class ReviewBulletRowEpoxyModel extends AirEpoxyModel<ReviewBulletRow> {
    private static final int NO_TITLE_RES = 0;
    CharSequence title;
    int titleRes;

    public int getDividerViewType() {
        return 4;
    }

    public void bind(ReviewBulletRow row) {
        super.bind(row);
        if (this.titleRes != 0) {
            row.setTitle(this.titleRes);
        } else {
            row.setTitle(this.title);
        }
    }

    public ReviewBulletRowEpoxyModel title(CharSequence title2) {
        this.title = title2;
        this.titleRes = 0;
        return this;
    }

    public ReviewBulletRowEpoxyModel title(int titleRes2) {
        Check.argument(titleRes2 > 0);
        this.titleRes = titleRes2;
        return this;
    }
}
