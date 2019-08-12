package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.TextRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class TextRowEpoxyModel extends AirEpoxyModel<TextRow> {
    int maxLines;
    CharSequence readMoreText;
    CharSequence text;
    int textRes;

    public void bind(TextRow view) {
        super.bind(view);
        if (this.maxLines != 0) {
            view.setMaxLines(this.maxLines);
        } else {
            view.clearMaxLines();
        }
        if (this.textRes != 0) {
            view.setText(this.textRes);
        } else {
            view.setText(this.text);
        }
        if (this.readMoreText != null) {
            view.setReadMoreText(this.readMoreText);
        } else {
            view.setReadMoreText(view.getResources().getString(C0716R.string.read_more_lower_cased));
        }
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
