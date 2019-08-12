package com.airbnb.android.core.viewcomponents.models;

import android.content.Context;
import android.view.View.OnClickListener;
import com.airbnb.p027n2.components.InfoRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class InfoRowEpoxyModel extends AirEpoxyModel<InfoRow> {
    OnClickListener clickListener;
    String info;
    int infoRes;
    CharSequence subtitle;
    int subtitleRes;
    CharSequence title;
    int titleRes;

    public void bind(InfoRow row) {
        super.bind(row);
        Context context = row.getContext();
        row.setTitle(this.titleRes != 0 ? context.getString(this.titleRes) : this.title);
        row.setSubtitleText(this.subtitleRes != 0 ? context.getString(this.subtitleRes) : this.subtitle);
        row.setInfo(this.infoRes != 0 ? context.getString(this.infoRes) : this.info);
        row.setOnClickListener(this.clickListener);
    }

    public int getDividerViewType() {
        return 0;
    }
}
