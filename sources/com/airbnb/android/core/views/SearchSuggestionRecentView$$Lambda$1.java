package com.airbnb.android.core.views;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.SavedSearch;

final /* synthetic */ class SearchSuggestionRecentView$$Lambda$1 implements OnClickListener {
    private final SearchSuggestionRecentView arg$1;
    private final SavedSearch arg$2;

    private SearchSuggestionRecentView$$Lambda$1(SearchSuggestionRecentView searchSuggestionRecentView, SavedSearch savedSearch) {
        this.arg$1 = searchSuggestionRecentView;
        this.arg$2 = savedSearch;
    }

    public static OnClickListener lambdaFactory$(SearchSuggestionRecentView searchSuggestionRecentView, SavedSearch savedSearch) {
        return new SearchSuggestionRecentView$$Lambda$1(searchSuggestionRecentView, savedSearch);
    }

    public void onClick(View view) {
        SearchSuggestionRecentView.lambda$setRecentSearchItem$0(this.arg$1, this.arg$2, view);
    }
}
