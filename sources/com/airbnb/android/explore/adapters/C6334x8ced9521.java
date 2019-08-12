package com.airbnb.android.explore.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.adapters.find.C5809SearchInputType;
import com.airbnb.android.core.models.SavedSearch;

/* renamed from: com.airbnb.android.explore.adapters.SearchSuggestionsEpoxyController$GlobalSuggestionBuilder$$Lambda$4 */
final /* synthetic */ class C6334x8ced9521 implements OnClickListener {
    private final GlobalSuggestionBuilder arg$1;
    private final String arg$2;
    private final SavedSearch arg$3;

    private C6334x8ced9521(GlobalSuggestionBuilder globalSuggestionBuilder, String str, SavedSearch savedSearch) {
        this.arg$1 = globalSuggestionBuilder;
        this.arg$2 = str;
        this.arg$3 = savedSearch;
    }

    public static OnClickListener lambdaFactory$(GlobalSuggestionBuilder globalSuggestionBuilder, String str, SavedSearch savedSearch) {
        return new C6334x8ced9521(globalSuggestionBuilder, str, savedSearch);
    }

    public void onClick(View view) {
        SearchSuggestionsEpoxyController.this.handleSuggestionClicked(C5809SearchInputType.SavedSearch, this.arg$2, this.arg$3);
    }
}
