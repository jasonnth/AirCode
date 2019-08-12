package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.p027n2.components.SectionHeader;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class SectionHeaderEpoxyModel extends AirEpoxyModel<SectionHeader> {
    OnClickListener buttonOnClickListener;
    CharSequence buttonText;
    int buttonTextRes;
    CharSequence description;
    int descriptionRes;
    CharSequence title;
    int titleRes;

    public void bind(SectionHeader view) {
        super.bind(view);
        if (this.titleRes != 0) {
            view.setTitle(this.titleRes);
        } else {
            view.setTitle(this.title);
        }
        if (this.descriptionRes != 0) {
            view.setDescription(this.descriptionRes);
        } else {
            view.setDescription(this.description);
        }
        if (this.buttonTextRes != 0) {
            view.setButtonText(this.buttonTextRes);
        } else {
            view.setButtonText(this.buttonText);
        }
        view.setButtonOnClickListener(this.buttonOnClickListener);
    }

    public void unbind(SectionHeader view) {
        super.unbind(view);
        view.setButtonOnClickListener(null);
    }

    public int getDividerViewType() {
        return 1;
    }
}
