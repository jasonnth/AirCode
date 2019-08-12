package com.airbnb.android.explore.adapters;

import com.airbnb.android.core.adapters.find.C5809SearchInputType;
import com.airbnb.android.core.models.SavedSearch;
import com.airbnb.android.core.views.SearchSuggestionRecentView.OnItemClickListener;

/* renamed from: com.airbnb.android.explore.adapters.SearchSuggestionsEpoxyController$ChinaSuggestionBuilder$$Lambda$4 */
final /* synthetic */ class C6327x9855d981 implements OnItemClickListener {
    private final ChinaSuggestionBuilder arg$1;

    private C6327x9855d981(ChinaSuggestionBuilder chinaSuggestionBuilder) {
        this.arg$1 = chinaSuggestionBuilder;
    }

    public static OnItemClickListener lambdaFactory$(ChinaSuggestionBuilder chinaSuggestionBuilder) {
        return new C6327x9855d981(chinaSuggestionBuilder);
    }

    public void onItemClick(SavedSearch savedSearch) {
        SearchSuggestionsEpoxyController.this.handleSuggestionClicked(C5809SearchInputType.SavedSearch, savedSearch.getSearchParams().getLocation(), savedSearch);
    }
}
