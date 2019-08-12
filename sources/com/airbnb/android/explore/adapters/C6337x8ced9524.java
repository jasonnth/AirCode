package com.airbnb.android.explore.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.adapters.find.C5809SearchInputType;
import com.airbnb.android.core.models.TravelDestination;

/* renamed from: com.airbnb.android.explore.adapters.SearchSuggestionsEpoxyController$GlobalSuggestionBuilder$$Lambda$7 */
final /* synthetic */ class C6337x8ced9524 implements OnClickListener {
    private final GlobalSuggestionBuilder arg$1;
    private final TravelDestination arg$2;

    private C6337x8ced9524(GlobalSuggestionBuilder globalSuggestionBuilder, TravelDestination travelDestination) {
        this.arg$1 = globalSuggestionBuilder;
        this.arg$2 = travelDestination;
    }

    public static OnClickListener lambdaFactory$(GlobalSuggestionBuilder globalSuggestionBuilder, TravelDestination travelDestination) {
        return new C6337x8ced9524(globalSuggestionBuilder, travelDestination);
    }

    public void onClick(View view) {
        SearchSuggestionsEpoxyController.this.handleSuggestionClicked(C5809SearchInputType.PopularDestination, this.arg$2.getTitle());
    }
}
