package com.airbnb.android.core.viewcomponents.models;

import android.content.res.Resources;
import android.view.View.OnClickListener;
import com.airbnb.p027n2.components.BasicRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class BasicRowEpoxyModel extends AirEpoxyModel<BasicRow> {
    OnClickListener clickListener;
    int subtitleRes;
    CharSequence subtitleText;
    int titleRes;
    CharSequence titleText;

    public void bind(BasicRow view) {
        super.bind(view);
        Resources resources = view.getResources();
        view.setTitle(this.titleRes != 0 ? resources.getString(this.titleRes) : this.titleText);
        view.setSubtitleText(this.subtitleRes != 0 ? resources.getString(this.subtitleRes) : this.subtitleText);
        view.setOnClickListener(this.clickListener);
    }

    public void unbind(BasicRow view) {
        super.unbind(view);
        view.setOnClickListener(null);
    }

    public int getDividerViewType() {
        return 0;
    }
}
