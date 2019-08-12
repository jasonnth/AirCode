package com.airbnb.android.explore.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.adapters.find.C5809SearchInputType;

/* renamed from: com.airbnb.android.explore.adapters.SearchSuggestionsEpoxyController$ChinaSuggestionBuilder$$Lambda$1 */
final /* synthetic */ class C6324x9855d97e implements OnClickListener {
    private final ChinaSuggestionBuilder arg$1;
    private final String arg$2;

    private C6324x9855d97e(ChinaSuggestionBuilder chinaSuggestionBuilder, String str) {
        this.arg$1 = chinaSuggestionBuilder;
        this.arg$2 = str;
    }

    public static OnClickListener lambdaFactory$(ChinaSuggestionBuilder chinaSuggestionBuilder, String str) {
        return new C6324x9855d97e(chinaSuggestionBuilder, str);
    }

    public void onClick(View view) {
        SearchSuggestionsEpoxyController.this.handleSuggestionClicked(C5809SearchInputType.CurrentLocation, this.arg$2);
    }
}
