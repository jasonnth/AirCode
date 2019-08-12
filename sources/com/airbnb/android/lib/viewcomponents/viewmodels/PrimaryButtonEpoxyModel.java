package com.airbnb.android.lib.viewcomponents.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.p027n2.components.PrimaryButton;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class PrimaryButtonEpoxyModel extends AirEpoxyModel<PrimaryButton> {
    OnClickListener clickListener;
    CharSequence title;
    int titleRes;

    public void bind(PrimaryButton view) {
        super.bind(view);
        view.setText(this.titleRes != 0 ? view.getContext().getString(this.titleRes) : this.title);
        view.setOnClickListener(this.clickListener);
        view.setClickable(true);
    }

    public void unbind(PrimaryButton view) {
        super.unbind(view);
        view.setOnClickListener(null);
    }

    public PrimaryButtonEpoxyModel title(CharSequence title2) {
        this.titleRes = 0;
        this.title = title2;
        return this;
    }
}
