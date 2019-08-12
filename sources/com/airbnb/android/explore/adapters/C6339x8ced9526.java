package com.airbnb.android.explore.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.utils.geocoder.models.Autocompletable;
import com.airbnb.android.explore.data.AutocompleteData;

/* renamed from: com.airbnb.android.explore.adapters.SearchSuggestionsEpoxyController$GlobalSuggestionBuilder$$Lambda$9 */
final /* synthetic */ class C6339x8ced9526 implements OnClickListener {
    private final GlobalSuggestionBuilder arg$1;
    private final AutocompleteData arg$2;
    private final String arg$3;
    private final String arg$4;
    private final Autocompletable arg$5;
    private final boolean arg$6;

    private C6339x8ced9526(GlobalSuggestionBuilder globalSuggestionBuilder, AutocompleteData autocompleteData, String str, String str2, Autocompletable autocompletable, boolean z) {
        this.arg$1 = globalSuggestionBuilder;
        this.arg$2 = autocompleteData;
        this.arg$3 = str;
        this.arg$4 = str2;
        this.arg$5 = autocompletable;
        this.arg$6 = z;
    }

    public static OnClickListener lambdaFactory$(GlobalSuggestionBuilder globalSuggestionBuilder, AutocompleteData autocompleteData, String str, String str2, Autocompletable autocompletable, boolean z) {
        return new C6339x8ced9526(globalSuggestionBuilder, autocompleteData, str, str2, autocompletable, z);
    }

    public void onClick(View view) {
        GlobalSuggestionBuilder.lambda$addAutoCompleteEpoxyModel$6(this.arg$1, this.arg$2, this.arg$3, this.arg$4, this.arg$5, this.arg$6, view);
    }
}
