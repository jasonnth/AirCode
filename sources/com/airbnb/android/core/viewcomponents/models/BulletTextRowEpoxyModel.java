package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.p027n2.components.BulletTextRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class BulletTextRowEpoxyModel extends AirEpoxyModel<BulletTextRow> {
    CharSequence text;
    int textRes;

    public void bind(BulletTextRow view) {
        super.bind(view);
        view.setText(this.textRes != 0 ? view.getResources().getString(this.textRes) : this.text);
    }

    public int getDividerViewType() {
        return -1;
    }
}
