package com.airbnb.android.explore.adapters;

import com.airbnb.android.core.models.SavedSearch;
import com.google.common.base.Predicate;

/* renamed from: com.airbnb.android.explore.adapters.SearchSuggestionsEpoxyController$ChinaSuggestionBuilder$$Lambda$7 */
final /* synthetic */ class C6330x9855d984 implements Predicate {
    private final String arg$1;

    private C6330x9855d984(String str) {
        this.arg$1 = str;
    }

    public static Predicate lambdaFactory$(String str) {
        return new C6330x9855d984(str);
    }

    public boolean apply(Object obj) {
        return ChinaSuggestionBuilder.lambda$buildAutocompleteSavedSearches$6(this.arg$1, (SavedSearch) obj);
    }
}
