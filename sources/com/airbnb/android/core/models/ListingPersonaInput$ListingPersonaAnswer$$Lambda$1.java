package com.airbnb.android.core.models;

import com.airbnb.android.core.models.ListingPersonaInput.ListingPersonaAnswer;
import com.google.common.base.Predicate;

final /* synthetic */ class ListingPersonaInput$ListingPersonaAnswer$$Lambda$1 implements Predicate {
    private final int arg$1;

    private ListingPersonaInput$ListingPersonaAnswer$$Lambda$1(int i) {
        this.arg$1 = i;
    }

    public static Predicate lambdaFactory$(int i) {
        return new ListingPersonaInput$ListingPersonaAnswer$$Lambda$1(i);
    }

    public boolean apply(Object obj) {
        return ListingPersonaAnswer.lambda$fromServerKey$0(this.arg$1, (ListingPersonaAnswer) obj);
    }
}
