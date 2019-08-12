package com.airbnb.android.core.models;

import com.airbnb.android.core.models.ListingPersonaInput.ListingPersonaQuestion;
import com.google.common.base.Predicate;

final /* synthetic */ class ListingPersonaInput$ListingPersonaQuestion$$Lambda$1 implements Predicate {
    private final int arg$1;

    private ListingPersonaInput$ListingPersonaQuestion$$Lambda$1(int i) {
        this.arg$1 = i;
    }

    public static Predicate lambdaFactory$(int i) {
        return new ListingPersonaInput$ListingPersonaQuestion$$Lambda$1(i);
    }

    public boolean apply(Object obj) {
        return ListingPersonaQuestion.lambda$fromServerKey$0(this.arg$1, (ListingPersonaQuestion) obj);
    }
}
