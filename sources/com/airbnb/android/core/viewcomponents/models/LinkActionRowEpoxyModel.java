package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.p027n2.components.LinkActionRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class LinkActionRowEpoxyModel extends AirEpoxyModel<LinkActionRow> {
    private static final boolean DEFAULT_ENABLED = true;
    OnClickListener clickListener;
    boolean enabled = DEFAULT_ENABLED;
    CharSequence text;
    int textRes;

    public void bind(LinkActionRow view) {
        super.bind(view);
        if (this.text != null) {
            view.setText(this.text);
        }
        if (this.textRes != 0) {
            view.setText(this.textRes);
        }
        view.setEnabled(this.enabled);
        view.setOnClickListener(this.clickListener);
    }

    public void unbind(LinkActionRow view) {
        super.unbind(view);
        view.setOnClickListener(null);
    }

    public AirEpoxyModel<LinkActionRow> reset() {
        this.enabled = DEFAULT_ENABLED;
        return super.reset();
    }

    public int getDividerViewType() {
        return 0;
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
