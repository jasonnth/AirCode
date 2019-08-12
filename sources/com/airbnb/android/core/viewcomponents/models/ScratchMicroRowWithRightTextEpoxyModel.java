package com.airbnb.android.core.viewcomponents.models;

import android.content.Context;
import com.airbnb.p027n2.components.ScratchMicroRowWithRightText;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class ScratchMicroRowWithRightTextEpoxyModel extends AirEpoxyModel<ScratchMicroRowWithRightText> {
    CharSequence text;
    int textRes;
    CharSequence title;
    int titleRes;

    public void bind(ScratchMicroRowWithRightText view) {
        super.bind(view);
        Context context = view.getContext();
        CharSequence actualTitle = this.titleRes != 0 ? context.getString(this.titleRes) : this.title;
        CharSequence actualText = this.textRes != 0 ? context.getString(this.textRes) : this.text;
        view.setTitle(actualTitle);
        view.setText(actualText);
    }

    public int getDividerViewType() {
        return 0;
    }
}
