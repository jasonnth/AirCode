package com.airbnb.android.core.models;

import com.google.common.base.Function;

final /* synthetic */ class ListingRegistrationQuestion$$Lambda$1 implements Function {
    private final ListingRegistrationQuestion arg$1;

    private ListingRegistrationQuestion$$Lambda$1(ListingRegistrationQuestion listingRegistrationQuestion) {
        this.arg$1 = listingRegistrationQuestion;
    }

    public static Function lambdaFactory$(ListingRegistrationQuestion listingRegistrationQuestion) {
        return new ListingRegistrationQuestion$$Lambda$1(listingRegistrationQuestion);
    }

    public Object apply(Object obj) {
        return ListingRegistrationQuestion.lambda$getCheckmarkSelectedTexts$0(this.arg$1, (String) obj);
    }
}
