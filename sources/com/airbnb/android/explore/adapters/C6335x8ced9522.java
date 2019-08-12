package com.airbnb.android.explore.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.LocationTypeaheadSuggestionItemForChina;
import com.airbnb.android.explore.data.AutocompleteData;

/* renamed from: com.airbnb.android.explore.adapters.SearchSuggestionsEpoxyController$GlobalSuggestionBuilder$$Lambda$5 */
final /* synthetic */ class C6335x8ced9522 implements OnClickListener {
    private final GlobalSuggestionBuilder arg$1;
    private final AutocompleteData arg$2;
    private final String arg$3;
    private final LocationTypeaheadSuggestionItemForChina arg$4;

    private C6335x8ced9522(GlobalSuggestionBuilder globalSuggestionBuilder, AutocompleteData autocompleteData, String str, LocationTypeaheadSuggestionItemForChina locationTypeaheadSuggestionItemForChina) {
        this.arg$1 = globalSuggestionBuilder;
        this.arg$2 = autocompleteData;
        this.arg$3 = str;
        this.arg$4 = locationTypeaheadSuggestionItemForChina;
    }

    public static OnClickListener lambdaFactory$(GlobalSuggestionBuilder globalSuggestionBuilder, AutocompleteData autocompleteData, String str, LocationTypeaheadSuggestionItemForChina locationTypeaheadSuggestionItemForChina) {
        return new C6335x8ced9522(globalSuggestionBuilder, autocompleteData, str, locationTypeaheadSuggestionItemForChina);
    }

    public void onClick(View view) {
        GlobalSuggestionBuilder.lambda$buildChinaAutocomplete$2(this.arg$1, this.arg$2, this.arg$3, this.arg$4, view);
    }
}
