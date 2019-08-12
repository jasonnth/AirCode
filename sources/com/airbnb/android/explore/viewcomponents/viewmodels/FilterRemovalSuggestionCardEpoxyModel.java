package com.airbnb.android.explore.viewcomponents.viewmodels;

import android.support.p002v7.widget.GridLayoutManager.LayoutParams;
import com.airbnb.android.core.models.FilterRemovalSuggestionItem;
import com.airbnb.android.explore.C0857R;
import com.airbnb.android.explore.adapters.FilterRemovalSuggestionCarouselAdapter.CarouselItemClickListener;
import com.airbnb.android.explore.views.FilterRemovalSuggestionCard;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import java.util.List;

public abstract class FilterRemovalSuggestionCardEpoxyModel extends AirEpoxyModel<FilterRemovalSuggestionCard> {
    CarouselItemClickListener carouselItemClickListener;
    List<FilterRemovalSuggestionItem> items;
    boolean noTopPadding;
    CharSequence subtitle;
    CharSequence title;

    public void bind(FilterRemovalSuggestionCard view) {
        super.bind(view);
        view.setup(this.title, this.subtitle, this.items, this.carouselItemClickListener);
        ((LayoutParams) view.getLayoutParams()).topMargin = this.noTopPadding ? 0 : view.getResources().getDimensionPixelSize(C0857R.dimen.n2_vertical_padding_medium);
    }

    public int getDividerViewType() {
        return 0;
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
