package com.airbnb.android.explore.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.adapters.find.C5809SearchInputType;

/* renamed from: com.airbnb.android.explore.adapters.SearchSuggestionsEpoxyController$GlobalSuggestionBuilder$$Lambda$6 */
final /* synthetic */ class C6336x8ced9523 implements OnClickListener {
    private final GlobalSuggestionBuilder arg$1;

    private C6336x8ced9523(GlobalSuggestionBuilder globalSuggestionBuilder) {
        this.arg$1 = globalSuggestionBuilder;
    }

    public static OnClickListener lambdaFactory$(GlobalSuggestionBuilder globalSuggestionBuilder) {
        return new C6336x8ced9523(globalSuggestionBuilder);
    }

    public void onClick(View view) {
        SearchSuggestionsEpoxyController.this.handleSuggestionClicked(C5809SearchInputType.Anywhere);
    }
}
