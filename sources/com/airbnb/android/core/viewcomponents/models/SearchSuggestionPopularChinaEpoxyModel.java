package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.models.PopularDestinationGroup;
import com.airbnb.android.core.views.SearchSuggestionPopularView;
import com.airbnb.android.core.views.SearchSuggestionPopularView.OnItemClickListener;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import java.util.List;

public abstract class SearchSuggestionPopularChinaEpoxyModel extends AirEpoxyModel<SearchSuggestionPopularView> {
    OnItemClickListener itemClickListener;
    List<PopularDestinationGroup> popularDestinations;

    public void bind(SearchSuggestionPopularView view) {
        super.bind(view);
        view.setPopularDestinations(this.popularDestinations);
        view.setItemClickListener(this.itemClickListener);
    }

    public void unbind(SearchSuggestionPopularView view) {
        super.unbind(view);
        view.setItemClickListener(null);
    }
}
