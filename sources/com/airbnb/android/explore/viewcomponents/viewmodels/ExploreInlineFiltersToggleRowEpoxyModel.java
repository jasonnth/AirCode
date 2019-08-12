package com.airbnb.android.explore.viewcomponents.viewmodels;

import android.content.Context;
import com.airbnb.p027n2.components.FindInlineFiltersToggleRow;
import com.airbnb.p027n2.components.FindInlineFiltersToggleRow.OnCheckChangedListener;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class ExploreInlineFiltersToggleRowEpoxyModel extends AirEpoxyModel<FindInlineFiltersToggleRow> {
    public OnCheckChangedListener checkChangedListener;
    public boolean checked;
    public int subtitleRes;
    public CharSequence title;
    public int titleRes;

    public void bind(FindInlineFiltersToggleRow row) {
        super.bind(row);
        Context context = row.getContext();
        CharSequence actualTitle = this.titleRes != 0 ? context.getString(this.titleRes) : this.title;
        CharSequence actualSubtitle = this.subtitleRes != 0 ? context.getString(this.subtitleRes) : null;
        row.setTitle(actualTitle);
        row.setSubtitleText(actualSubtitle);
        row.setChecked(this.checked);
        row.setCheckChangedListener(this.checkChangedListener);
    }

    public void unbind(FindInlineFiltersToggleRow row) {
        super.unbind(row);
        row.setCheckChangedListener(null);
    }

    public int getDividerViewType() {
        return 0;
    }
}
