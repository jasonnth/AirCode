package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.p027n2.components.MicroSectionHeader;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class MicroSectionHeaderEpoxyModel extends AirEpoxyModel<MicroSectionHeader> {
    private static final int DEFAULT_MAX_LINES = 1;
    OnClickListener buttonOnClickListener;
    CharSequence buttonText;
    int buttonTextRes;
    CharSequence description;
    int descriptionRes;
    boolean invertColors;
    boolean isBold;
    int maxLines = 1;
    String sectionId;
    CharSequence title;
    int titleRes;

    public void bind(MicroSectionHeader view) {
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
        view.setTitleMaxLines(this.maxLines);
        view.setButtonOnClickListener(this.buttonOnClickListener);
        view.invertColors(this.invertColors);
    }

    public AirEpoxyModel<MicroSectionHeader> reset() {
        super.reset();
        this.maxLines = 1;
        return this;
    }

    public void unbind(MicroSectionHeader view) {
        super.unbind(view);
        view.setButtonOnClickListener(null);
    }

    public int getDividerViewType() {
        return 1;
    }
}
