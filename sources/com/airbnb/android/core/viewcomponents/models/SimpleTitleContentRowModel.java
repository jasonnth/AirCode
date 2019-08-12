package com.airbnb.android.core.viewcomponents.models;

import android.content.res.Resources;
import com.airbnb.p027n2.components.SimpleTitleContentRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class SimpleTitleContentRowModel extends AirEpoxyModel<SimpleTitleContentRow> {
    int contentRes;
    CharSequence contentText;
    int titleRes;
    CharSequence titleText;

    public void bind(SimpleTitleContentRow view) {
        super.bind(view);
        Resources resources = view.getResources();
        view.setTitle(this.titleRes != 0 ? resources.getString(this.titleRes) : this.titleText);
        view.setContentText(this.contentRes != 0 ? resources.getString(this.contentRes) : this.contentText);
    }

    public int getDividerViewType() {
        return 0;
    }
}
