package com.airbnb.android.core.views;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class SearchSuggestionPopularView$$Lambda$2 implements OnClickListener {
    private final SearchSuggestionPopularView arg$1;
    private final String arg$2;

    private SearchSuggestionPopularView$$Lambda$2(SearchSuggestionPopularView searchSuggestionPopularView, String str) {
        this.arg$1 = searchSuggestionPopularView;
        this.arg$2 = str;
    }

    public static OnClickListener lambdaFactory$(SearchSuggestionPopularView searchSuggestionPopularView, String str) {
        return new SearchSuggestionPopularView$$Lambda$2(searchSuggestionPopularView, str);
    }

    public void onClick(View view) {
        SearchSuggestionPopularView.lambda$refreshGridLayout$1(this.arg$1, this.arg$2, view);
    }
}
