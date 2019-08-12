package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.models.SavedSearch;
import com.airbnb.android.core.views.SearchSuggestionRecentView;
import com.airbnb.android.core.views.SearchSuggestionRecentView.OnItemClickListener;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import java.util.List;

public abstract class SearchSuggestionRecentChinaEpoxyModel extends AirEpoxyModel<SearchSuggestionRecentView> {
    OnItemClickListener itemClickListener;
    List<SavedSearch> savedSearches;

    public void bind(SearchSuggestionRecentView view) {
        super.bind(view);
        view.setRecentSearchItem(this.savedSearches);
        view.setItemClickListener(this.itemClickListener);
    }

    public void unbind(SearchSuggestionRecentView view) {
        super.unbind(view);
        view.setItemClickListener(null);
    }
}
