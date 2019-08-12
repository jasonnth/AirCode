package com.airbnb.android.explore.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.adapters.find.C5809SearchInputType;

/* renamed from: com.airbnb.android.explore.adapters.SearchSuggestionsEpoxyController$GlobalSuggestionBuilder$$Lambda$1 */
final /* synthetic */ class C6333x8ced951e implements OnClickListener {
    private final GlobalSuggestionBuilder arg$1;
    private final String arg$2;

    private C6333x8ced951e(GlobalSuggestionBuilder globalSuggestionBuilder, String str) {
        this.arg$1 = globalSuggestionBuilder;
        this.arg$2 = str;
    }

    public static OnClickListener lambdaFactory$(GlobalSuggestionBuilder globalSuggestionBuilder, String str) {
        return new C6333x8ced951e(globalSuggestionBuilder, str);
    }

    public void onClick(View view) {
        SearchSuggestionsEpoxyController.this.handleSuggestionClicked(C5809SearchInputType.CurrentLocation, this.arg$2);
    }
}
