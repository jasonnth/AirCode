package com.airbnb.android.explore.adapters;

import android.content.Context;
import android.view.View;
import com.airbnb.android.core.models.FilterRemovalSuggestionItem;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.explore.viewcomponents.viewmodels.FilterRemovalPillEpoxyModel_;
import com.airbnb.epoxy.EpoxyModel;
import java.util.List;

public class FilterRemovalSuggestionCarouselAdapter extends AirEpoxyAdapter {
    private final CarouselItemClickListener carouselClickListener;
    private final Context context;

    public interface CarouselItemClickListener {
        void onCarouselItemClicked(View view, FilterRemovalSuggestionItem filterRemovalSuggestionItem);
    }

    public FilterRemovalSuggestionCarouselAdapter(CarouselItemClickListener carouselClickListener2, Context context2) {
        super(false);
        this.carouselClickListener = carouselClickListener2;
        this.context = context2;
    }

    public void setItems(List<FilterRemovalSuggestionItem> filters) {
        this.models.clear();
        for (FilterRemovalSuggestionItem filter : filters) {
            this.models.add(buildEpoxyModel(filter));
        }
        notifyDataSetChanged();
    }

    private EpoxyModel<?> buildEpoxyModel(FilterRemovalSuggestionItem filter) {
        return new FilterRemovalPillEpoxyModel_().item(filter).clickListener(FilterRemovalSuggestionCarouselAdapter$$Lambda$1.lambdaFactory$(this, filter));
    }
}
