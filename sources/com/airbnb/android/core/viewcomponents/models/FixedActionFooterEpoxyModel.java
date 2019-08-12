package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.p027n2.components.fixed_footers.FixedActionFooter;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class FixedActionFooterEpoxyModel extends AirEpoxyModel<FixedActionFooter> {
    boolean buttonEnabled;
    OnClickListener clickListener;
    boolean loading;
    CharSequence text;
    int textRes;

    public void bind(FixedActionFooter row) {
        super.bind(row);
        row.setButtonText(this.textRes != 0 ? row.getContext().getString(this.textRes) : this.text);
        row.setButtonEnabled(this.buttonEnabled);
        row.setButtonLoading(this.loading);
        row.setButtonOnClickListener(this.clickListener);
    }
}
