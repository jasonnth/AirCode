package com.airbnb.android.explore.viewcomponents.viewmodels;

import android.view.View;
import com.airbnb.android.explore.C0857R;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class FiltersSpacerEpoxyModel extends AirEpoxyModel<View> {
    boolean large;

    public void bind(View view) {
        super.bind(view);
        view.getLayoutParams().height = view.getResources().getDimensionPixelSize(C0857R.dimen.n2_vertical_padding_large) + (this.large ? view.getResources().getDimensionPixelSize(C0857R.dimen.n2_vertical_padding_small) : 0);
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
