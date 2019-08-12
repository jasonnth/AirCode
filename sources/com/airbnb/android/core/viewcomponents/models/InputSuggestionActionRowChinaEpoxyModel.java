package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.p027n2.components.InputSuggestionActionRowChina;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class InputSuggestionActionRowChinaEpoxyModel extends AirEpoxyModel<InputSuggestionActionRowChina> {
    CharSequence label;
    OnClickListener onClickListener;
    CharSequence subtitle;
    CharSequence title;

    public void bind(InputSuggestionActionRowChina view) {
        super.bind(view);
        view.setTitle(this.title);
        view.setSubtitle(this.subtitle);
        view.setLabel(this.label);
        view.setOnClickListener(this.onClickListener);
        if (this.onClickListener == null) {
            view.setClickable(false);
        }
    }

    public void unbind(InputSuggestionActionRowChina view) {
        super.unbind(view);
        view.setOnClickListener(null);
    }

    public int getDividerViewType() {
        return 0;
    }
}
