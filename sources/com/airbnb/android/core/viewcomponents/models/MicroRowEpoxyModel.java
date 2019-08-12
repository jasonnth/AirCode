package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.utils.TextUtil;
import com.airbnb.p027n2.components.MicroRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class MicroRowEpoxyModel extends AirEpoxyModel<MicroRow> {
    OnClickListener clickListener;
    int htmlSafeText;
    CharSequence text;
    int textRes;

    public void bind(MicroRow microRow) {
        super.bind(microRow);
        if (this.textRes != 0) {
            microRow.setText(this.textRes);
        } else if (this.text != null) {
            microRow.setText(this.text);
        } else if (this.htmlSafeText != 0) {
            microRow.setText((CharSequence) TextUtil.fromHtmlSafe(microRow.getContext().getString(this.htmlSafeText)));
        }
        microRow.setOnClickListener(this.clickListener);
    }

    public void unbind(MicroRow microRow) {
        super.unbind(microRow);
        microRow.setOnClickListener(null);
    }

    public int getDividerViewType() {
        return 0;
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
