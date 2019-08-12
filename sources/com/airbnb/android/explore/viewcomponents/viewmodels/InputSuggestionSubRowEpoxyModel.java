package com.airbnb.android.explore.viewcomponents.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.p027n2.components.InputSuggestionSubRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class InputSuggestionSubRowEpoxyModel extends AirEpoxyModel<InputSuggestionSubRow> {
    String boldText;
    int boldTextRes;
    boolean invertColors;
    boolean lastSubRow;
    OnClickListener onClickListener;
    String title;
    int titleRes;

    public void bind(InputSuggestionSubRow view) {
        boolean z = false;
        super.bind(view);
        if (this.titleRes != 0) {
            this.title = view.getResources().getString(this.titleRes);
        }
        if (this.boldTextRes != 0) {
            this.boldText = view.getResources().getString(this.boldTextRes);
        }
        view.configureText(this.title, this.boldText);
        view.setOnClickListener(this.onClickListener);
        if (this.onClickListener == null) {
            view.setClickable(false);
        }
        view.invertColors(this.invertColors);
        if (!this.lastSubRow) {
            z = true;
        }
        view.showSubRowDivider(z);
        view.showDivider(this.lastSubRow);
    }

    public void unbind(InputSuggestionSubRow view) {
        super.unbind(view);
        view.setOnClickListener(null);
    }

    public int getDividerViewType() {
        return 0;
    }
}
