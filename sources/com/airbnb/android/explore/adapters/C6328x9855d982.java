package com.airbnb.android.explore.adapters;

import com.airbnb.android.core.adapters.find.C5809SearchInputType;
import com.airbnb.android.core.views.SearchSuggestionPopularView.OnItemClickListener;

/* renamed from: com.airbnb.android.explore.adapters.SearchSuggestionsEpoxyController$ChinaSuggestionBuilder$$Lambda$5 */
final /* synthetic */ class C6328x9855d982 implements OnItemClickListener {
    private final ChinaSuggestionBuilder arg$1;

    private C6328x9855d982(ChinaSuggestionBuilder chinaSuggestionBuilder) {
        this.arg$1 = chinaSuggestionBuilder;
    }

    public static OnItemClickListener lambdaFactory$(ChinaSuggestionBuilder chinaSuggestionBuilder) {
        return new C6328x9855d982(chinaSuggestionBuilder);
    }

    public void onItemClick(String str) {
        SearchSuggestionsEpoxyController.this.handleSuggestionClicked(C5809SearchInputType.PopularDestination, str);
    }
}
