package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.p027n2.components.fixed_footers.FixedActionFooter;

public abstract class QuickPayButtonSpacerModel extends FixedActionFooterEpoxyModel {
    public void bind(FixedActionFooter row) {
        super.bind(row);
        row.setVisibility(4);
    }
}
