package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.p027n2.components.InlineCaution;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class InlineCautionEpoxyModel extends AirEpoxyModel<InlineCaution> {
    OnClickListener actionListener;
    String actionText;
    String contentText;

    public void bind(InlineCaution c) {
        super.bind(c);
        String cautionText = this.contentText + this.actionText;
        c.setCautionTextAndAction(cautionText, this.contentText.length(), cautionText.length(), this.actionListener);
    }
}
