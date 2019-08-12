package com.airbnb.android.core.viewcomponents.models;

import android.content.Context;
import com.airbnb.p027n2.components.InfoPanelRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.airbnb.p027n2.interfaces.LinkOnClickListener;

public abstract class InfoPanelRowEpoxyModel extends AirEpoxyModel<InfoPanelRow> {
    CharSequence content;
    int contentRes;
    LinkOnClickListener linkOnClickListener;
    String linkText;
    CharSequence title;
    int titleRes;

    public void bind(InfoPanelRow row) {
        super.bind(row);
        Context context = row.getContext();
        CharSequence titleText = this.titleRes != 0 ? context.getString(this.titleRes) : this.title;
        CharSequence contentText = this.contentRes != 0 ? context.getString(this.contentRes) : this.content;
        row.setTitle(titleText);
        row.setContent(contentText);
        row.setLinkClickListener(this.linkOnClickListener, contentText.toString(), this.linkText);
    }
}
