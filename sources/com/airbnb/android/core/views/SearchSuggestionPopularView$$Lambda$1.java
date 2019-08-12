package com.airbnb.android.core.views;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.p027n2.primitives.AirTextView;

final /* synthetic */ class SearchSuggestionPopularView$$Lambda$1 implements OnClickListener {
    private final SearchSuggestionPopularView arg$1;
    private final AirTextView arg$2;

    private SearchSuggestionPopularView$$Lambda$1(SearchSuggestionPopularView searchSuggestionPopularView, AirTextView airTextView) {
        this.arg$1 = searchSuggestionPopularView;
        this.arg$2 = airTextView;
    }

    public static OnClickListener lambdaFactory$(SearchSuggestionPopularView searchSuggestionPopularView, AirTextView airTextView) {
        return new SearchSuggestionPopularView$$Lambda$1(searchSuggestionPopularView, airTextView);
    }

    public void onClick(View view) {
        SearchSuggestionPopularView.lambda$setPopularDestinations$0(this.arg$1, this.arg$2, view);
    }
}
