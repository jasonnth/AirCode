package com.airbnb.android.booking.fragments;

import com.airbnb.android.core.models.PreBookingQuestion;
import com.google.common.base.Function;

final /* synthetic */ class BookingEditTextFragment$$Lambda$4 implements Function {
    private static final BookingEditTextFragment$$Lambda$4 instance = new BookingEditTextFragment$$Lambda$4();

    private BookingEditTextFragment$$Lambda$4() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return BookingEditTextFragment.lambda$getPrebookingQuestions$3((PreBookingQuestion) obj);
    }
}
