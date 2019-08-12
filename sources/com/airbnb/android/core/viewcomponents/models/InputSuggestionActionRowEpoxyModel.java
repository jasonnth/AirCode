package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.p027n2.components.InputSuggestionActionRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class InputSuggestionActionRowEpoxyModel extends AirEpoxyModel<InputSuggestionActionRow> {
    boolean hasSubRows;
    int iconRes;
    CharSequence label;
    OnClickListener onClickListener;
    CharSequence subtitle;
    int subtitleRes;
    CharSequence title;
    int titleRes;

    public void bind(InputSuggestionActionRow view) {
        boolean z = false;
        super.bind(view);
        if (this.titleRes != 0) {
            view.setTitle(this.titleRes);
        } else {
            view.setTitle(this.title);
        }
        if (this.subtitleRes != 0) {
            view.setSubtitle(this.subtitleRes);
        } else {
            view.setSubtitle(this.subtitle);
        }
        view.setLabel(this.label);
        view.setIcon(this.iconRes);
        view.setOnClickListener(this.onClickListener);
        if (this.onClickListener == null) {
            view.setClickable(false);
        }
        if (!this.hasSubRows) {
            z = true;
        }
        view.showDivider(z);
    }

    public void unbind(InputSuggestionActionRow view) {
        super.unbind(view);
        view.setOnClickListener(null);
    }

    public int getDividerViewType() {
        return 0;
    }
}
