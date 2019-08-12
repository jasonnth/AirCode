package com.appboy.events;

import com.appboy.models.ResponseError;
import com.appboy.models.outgoing.Feedback;

public final class SubmitFeedbackFailed {

    /* renamed from: a */
    private final Feedback f2786a;

    /* renamed from: b */
    private final ResponseError f2787b;

    public SubmitFeedbackFailed(Feedback feedback, ResponseError responseError) {
        this.f2786a = feedback;
        this.f2787b = responseError;
    }
}
