package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.p027n2.components.InlineContext;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class InlineContextEpoxyModel extends AirEpoxyModel<InlineContext> {
    CharSequence status;
    CharSequence statusDetails;

    public void bind(InlineContext c) {
        super.bind(c);
        c.setStatusText(this.status);
        c.setStatusDetailsText(this.statusDetails);
    }
}
