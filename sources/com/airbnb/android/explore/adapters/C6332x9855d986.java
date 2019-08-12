package com.airbnb.android.explore.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.utils.geocoder.models.Autocompletable;
import com.airbnb.android.explore.data.AutocompleteData;

/* renamed from: com.airbnb.android.explore.adapters.SearchSuggestionsEpoxyController$ChinaSuggestionBuilder$$Lambda$9 */
final /* synthetic */ class C6332x9855d986 implements OnClickListener {
    private final ChinaSuggestionBuilder arg$1;
    private final AutocompleteData arg$2;
    private final String arg$3;
    private final String arg$4;
    private final Autocompletable arg$5;

    private C6332x9855d986(ChinaSuggestionBuilder chinaSuggestionBuilder, AutocompleteData autocompleteData, String str, String str2, Autocompletable autocompletable) {
        this.arg$1 = chinaSuggestionBuilder;
        this.arg$2 = autocompleteData;
        this.arg$3 = str;
        this.arg$4 = str2;
        this.arg$5 = autocompletable;
    }

    public static OnClickListener lambdaFactory$(ChinaSuggestionBuilder chinaSuggestionBuilder, AutocompleteData autocompleteData, String str, String str2, Autocompletable autocompletable) {
        return new C6332x9855d986(chinaSuggestionBuilder, autocompleteData, str, str2, autocompletable);
    }

    public void onClick(View view) {
        ChinaSuggestionBuilder.lambda$addAutoCompleteEpoxyModel$8(this.arg$1, this.arg$2, this.arg$3, this.arg$4, this.arg$5, view);
    }
}
