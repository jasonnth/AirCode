package com.airbnb.android.explore.viewcomponents.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.p027n2.components.ExploreEmptyState;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class ExploreEmptyStateEpoxyModel extends AirEpoxyModel<ExploreEmptyState> {
    CharSequence button;
    OnClickListener clickListener;
    CharSequence title;
    int titleRes;

    public void bind(ExploreEmptyState view) {
        super.bind(view);
        view.setTitle(this.titleRes != 0 ? view.getContext().getString(this.titleRes) : this.title);
        view.setButton(this.button);
        view.showDivider(this.showDivider.booleanValue());
        view.setButtonClickListener(this.clickListener);
    }

    public void unbind(ExploreEmptyState view) {
        super.unbind(view);
        view.setOnClickListener(null);
    }

    public int getDividerViewType() {
        return 0;
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
