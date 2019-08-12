package com.airbnb.android.explore.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.adapters.find.C5809SearchInputType;
import com.airbnb.android.core.models.SavedSearch;

/* renamed from: com.airbnb.android.explore.adapters.SearchSuggestionsEpoxyController$ChinaSuggestionBuilder$$Lambda$8 */
final /* synthetic */ class C6331x9855d985 implements OnClickListener {
    private final ChinaSuggestionBuilder arg$1;
    private final String arg$2;
    private final SavedSearch arg$3;

    private C6331x9855d985(ChinaSuggestionBuilder chinaSuggestionBuilder, String str, SavedSearch savedSearch) {
        this.arg$1 = chinaSuggestionBuilder;
        this.arg$2 = str;
        this.arg$3 = savedSearch;
    }

    public static OnClickListener lambdaFactory$(ChinaSuggestionBuilder chinaSuggestionBuilder, String str, SavedSearch savedSearch) {
        return new C6331x9855d985(chinaSuggestionBuilder, str, savedSearch);
    }

    public void onClick(View view) {
        SearchSuggestionsEpoxyController.this.handleSuggestionClicked(C5809SearchInputType.SavedSearch, this.arg$2, this.arg$3);
    }
}
