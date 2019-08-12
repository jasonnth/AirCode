package com.airbnb.android.explore.viewcomponents.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.p027n2.components.LinkActionRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class SeeMoreSeeLessRowModel extends AirEpoxyModel<LinkActionRow> {
    OnClickListener clickListener;
    int collapsedText;
    boolean expanded;
    int expandedText;

    public void bind(LinkActionRow view) {
        super.bind(view);
        view.setText(this.expanded ? this.expandedText : this.collapsedText);
        view.setOnClickListener(this.clickListener);
        view.showDivider(false);
    }

    public void unbind(LinkActionRow view) {
        super.unbind(view);
        view.setOnClickListener(null);
    }
}
