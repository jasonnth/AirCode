package com.airbnb.android.core.requests;

import com.airbnb.android.core.models.PreBookingQuestion;
import com.google.common.base.Function;

final /* synthetic */ class UpdateBookingSettingsRequest$RequestBody$$Lambda$1 implements Function {
    private static final UpdateBookingSettingsRequest$RequestBody$$Lambda$1 instance = new UpdateBookingSettingsRequest$RequestBody$$Lambda$1();

    private UpdateBookingSettingsRequest$RequestBody$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return RequestBody.lambda$new$0((PreBookingQuestion) obj);
    }
}
