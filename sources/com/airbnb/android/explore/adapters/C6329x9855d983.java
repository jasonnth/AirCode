package com.airbnb.android.explore.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.LocationTypeaheadSuggestionItemForChina;
import com.airbnb.android.explore.data.AutocompleteData;

/* renamed from: com.airbnb.android.explore.adapters.SearchSuggestionsEpoxyController$ChinaSuggestionBuilder$$Lambda$6 */
final /* synthetic */ class C6329x9855d983 implements OnClickListener {
    private final ChinaSuggestionBuilder arg$1;
    private final AutocompleteData arg$2;
    private final String arg$3;
    private final LocationTypeaheadSuggestionItemForChina arg$4;

    private C6329x9855d983(ChinaSuggestionBuilder chinaSuggestionBuilder, AutocompleteData autocompleteData, String str, LocationTypeaheadSuggestionItemForChina locationTypeaheadSuggestionItemForChina) {
        this.arg$1 = chinaSuggestionBuilder;
        this.arg$2 = autocompleteData;
        this.arg$3 = str;
        this.arg$4 = locationTypeaheadSuggestionItemForChina;
    }

    public static OnClickListener lambdaFactory$(ChinaSuggestionBuilder chinaSuggestionBuilder, AutocompleteData autocompleteData, String str, LocationTypeaheadSuggestionItemForChina locationTypeaheadSuggestionItemForChina) {
        return new C6329x9855d983(chinaSuggestionBuilder, autocompleteData, str, locationTypeaheadSuggestionItemForChina);
    }

    public void onClick(View view) {
        ChinaSuggestionBuilder.lambda$buildChinaAutocomplete$5(this.arg$1, this.arg$2, this.arg$3, this.arg$4, view);
    }
}
