package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.views.SuperHeroActionView;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class SuperHeroActionEpoxyModel extends AirEpoxyModel<SuperHeroActionView> {
    OnClickListener actionOnClickListener;
    CharSequence text;

    public void bind(SuperHeroActionView view) {
        super.bind(view);
        if (this.text != null) {
            view.setText(this.text);
        }
        view.setClickable(this.actionOnClickListener != null);
        view.setOnClickListener(this.actionOnClickListener);
    }
}
