package com.airbnb.android.lib.viewcomponents.viewmodels;

import com.airbnb.p027n2.components.LinkableLegalTextRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class LinkableLegalTextModel extends AirEpoxyModel<LinkableLegalTextRow> {
    CharSequence fxBody;
    CharSequence termsBody;
    CharSequence termsTitle;

    public void bind(LinkableLegalTextRow view) {
        super.bind(view);
        view.setTermsTitle(this.termsTitle);
        view.setTermsBody(this.termsBody);
        view.setFxBody(this.fxBody);
    }

    public int getDividerViewType() {
        return 0;
    }
}
