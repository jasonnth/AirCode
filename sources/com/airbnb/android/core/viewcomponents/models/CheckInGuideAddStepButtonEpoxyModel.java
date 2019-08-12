package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.p027n2.components.CheckInGuideAddStepButton;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class CheckInGuideAddStepButtonEpoxyModel extends AirEpoxyModel<CheckInGuideAddStepButton> {
    OnClickListener clickListener;
    CharSequence text;
    int textRes;

    public void bind(CheckInGuideAddStepButton view) {
        super.bind(view);
        view.setText(this.textRes != 0 ? view.getContext().getString(this.textRes) : this.text);
        view.setButtonOnClickListener(this.clickListener);
    }

    public void unbind(CheckInGuideAddStepButton view) {
        super.unbind(view);
        view.setOnClickListener(null);
    }
}
