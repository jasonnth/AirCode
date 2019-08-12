package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.p027n2.components.SearchParamsRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class SearchParamsRowEpoxyModel extends AirEpoxyModel<SearchParamsRow> {
    OnClickListener clickListener;
    String details;
    String location;

    public void bind(SearchParamsRow searchParamsRow) {
        super.bind(searchParamsRow);
        searchParamsRow.setLocationText(this.location);
        searchParamsRow.setDetailsText(this.details);
        searchParamsRow.setOnClickListener(this.clickListener);
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
