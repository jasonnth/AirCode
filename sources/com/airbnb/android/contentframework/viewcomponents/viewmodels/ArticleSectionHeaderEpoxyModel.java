package com.airbnb.android.contentframework.viewcomponents.viewmodels;

import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.airbnb.p027n2.primitives.AirTextView;

public abstract class ArticleSectionHeaderEpoxyModel extends AirEpoxyModel<AirTextView> {
    private final String headerText;

    public ArticleSectionHeaderEpoxyModel(String text) {
        this.headerText = text;
    }

    public void bind(AirTextView view) {
        super.bind(view);
        view.setText(this.headerText);
    }
}
