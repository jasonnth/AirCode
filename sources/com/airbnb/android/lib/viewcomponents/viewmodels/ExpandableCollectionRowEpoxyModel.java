package com.airbnb.android.lib.viewcomponents.viewmodels;

import com.airbnb.p027n2.components.ExpandableCollectionRow;
import com.airbnb.p027n2.components.ExpandableCollectionRow.RowItem;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import java.util.List;

public abstract class ExpandableCollectionRowEpoxyModel extends AirEpoxyModel<ExpandableCollectionRow> {
    int bottomTextRes;
    int expandTextRes;
    List<RowItem> rowItems;

    public void bind(ExpandableCollectionRow view) {
        super.bind(view);
        if (this.rowItems != null) {
            view.setRowItems(this.rowItems);
            view.setExpandText(this.expandTextRes);
            view.setBottomText(this.bottomTextRes);
        }
    }

    public int getDividerViewType() {
        return 0;
    }
}
