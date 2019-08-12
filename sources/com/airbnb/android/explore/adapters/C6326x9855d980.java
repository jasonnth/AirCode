package com.airbnb.android.explore.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.adapters.find.C5809SearchInputType;

/* renamed from: com.airbnb.android.explore.adapters.SearchSuggestionsEpoxyController$ChinaSuggestionBuilder$$Lambda$3 */
final /* synthetic */ class C6326x9855d980 implements OnClickListener {
    private final ChinaSuggestionBuilder arg$1;

    private C6326x9855d980(ChinaSuggestionBuilder chinaSuggestionBuilder) {
        this.arg$1 = chinaSuggestionBuilder;
    }

    public static OnClickListener lambdaFactory$(ChinaSuggestionBuilder chinaSuggestionBuilder) {
        return new C6326x9855d980(chinaSuggestionBuilder);
    }

    public void onClick(View view) {
        SearchSuggestionsEpoxyController.this.handleSuggestionClicked(C5809SearchInputType.Anywhere);
    }
}
