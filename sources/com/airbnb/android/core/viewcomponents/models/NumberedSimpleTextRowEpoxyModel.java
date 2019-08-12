package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.NumberedSimpleTextRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class NumberedSimpleTextRowEpoxyModel extends AirEpoxyModel<NumberedSimpleTextRow> {
    CharSequence content;
    int contentRes;
    int stepColorRes;
    int stepNumber;

    public void bind(NumberedSimpleTextRow view) {
        super.bind(view);
        if (this.contentRes != 0) {
            view.setContent(this.contentRes);
        } else {
            view.setContent(this.content);
        }
        view.setStepNumber(Integer.valueOf(this.stepNumber));
        if (this.stepColorRes != 0) {
            view.setStepNumberColor(this.stepColorRes);
        }
    }

    public void unbind(NumberedSimpleTextRow view) {
        super.unbind(view);
    }

    public NumberedSimpleTextRowEpoxyModel tightPadding() {
        layout(C0716R.layout.view_holder_numbered_simple_text_row_tiny_half_padding);
        return this;
    }

    public NumberedSimpleTextRowEpoxyModel paddingBottom() {
        layout(C0716R.layout.view_holder_numbered_simple_text_row_padding_bottom);
        return this;
    }

    public int getDividerViewType() {
        return 0;
    }
}
