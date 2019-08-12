package com.airbnb.android.explore.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.utils.geocoder.models.Autocompletable;
import com.airbnb.android.explore.data.AutocompleteData;

/* renamed from: com.airbnb.android.explore.adapters.SearchSuggestionsEpoxyController$GlobalSuggestionBuilder$$Lambda$8 */
final /* synthetic */ class C6338x8ced9525 implements OnClickListener {
    private final GlobalSuggestionBuilder arg$1;
    private final AutocompleteData arg$2;
    private final String arg$3;
    private final Autocompletable arg$4;
    private final String arg$5;

    private C6338x8ced9525(GlobalSuggestionBuilder globalSuggestionBuilder, AutocompleteData autocompleteData, String str, Autocompletable autocompletable, String str2) {
        this.arg$1 = globalSuggestionBuilder;
        this.arg$2 = autocompleteData;
        this.arg$3 = str;
        this.arg$4 = autocompletable;
        this.arg$5 = str2;
    }

    public static OnClickListener lambdaFactory$(GlobalSuggestionBuilder globalSuggestionBuilder, AutocompleteData autocompleteData, String str, Autocompletable autocompletable, String str2) {
        return new C6338x8ced9525(globalSuggestionBuilder, autocompleteData, str, autocompletable, str2);
    }

    public void onClick(View view) {
        GlobalSuggestionBuilder.lambda$addVerticalOptionModel$5(this.arg$1, this.arg$2, this.arg$3, this.arg$4, this.arg$5, view);
    }
}
