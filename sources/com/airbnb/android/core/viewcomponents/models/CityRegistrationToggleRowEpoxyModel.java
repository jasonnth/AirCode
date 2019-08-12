package com.airbnb.android.core.viewcomponents.models;

import android.content.Context;
import com.airbnb.p027n2.components.CityRegistrationToggleRow;
import com.airbnb.p027n2.components.CityRegistrationToggleRow.OnCheckChangedListener;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class CityRegistrationToggleRowEpoxyModel extends AirEpoxyModel<CityRegistrationToggleRow> {
    public OnCheckChangedListener checkChangedListener;
    public boolean checked;
    public CharSequence subtitle;
    public int subtitleRes;
    public CharSequence title;
    public int titleRes;

    public void bind(CityRegistrationToggleRow row) {
        super.bind(row);
        Context context = row.getContext();
        CharSequence actualTitle = this.titleRes != 0 ? context.getString(this.titleRes) : this.title;
        CharSequence actualSubtitle = this.subtitleRes != 0 ? context.getString(this.subtitleRes) : this.subtitle;
        row.setTitle(actualTitle);
        row.setSubtitleText(actualSubtitle);
        row.setChecked(this.checked);
        row.setCheckChangedListener(this.checkChangedListener);
    }

    public void unbind(CityRegistrationToggleRow row) {
        super.unbind(row);
        row.setCheckChangedListener(null);
    }

    public int getDividerViewType() {
        return 0;
    }
}
