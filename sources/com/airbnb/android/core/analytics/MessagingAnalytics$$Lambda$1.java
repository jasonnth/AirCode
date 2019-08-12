package com.airbnb.android.core.analytics;

import com.airbnb.airrequest.Transformer;
import com.airbnb.android.core.analytics.MessagingAnalytics.Action;
import p032rx.Observable;

final /* synthetic */ class MessagingAnalytics$$Lambda$1 implements Transformer {
    private final Action arg$1;

    private MessagingAnalytics$$Lambda$1(Action action) {
        this.arg$1 = action;
    }

    public static Transformer lambdaFactory$(Action action) {
        return new MessagingAnalytics$$Lambda$1(action);
    }

    public Object call(Object obj) {
        return ((Observable) obj).doOnSubscribe(MessagingAnalytics$$Lambda$2.lambdaFactory$(this.arg$1)).doOnNext(MessagingAnalytics$$Lambda$3.lambdaFactory$(this.arg$1)).doOnError(MessagingAnalytics$$Lambda$4.lambdaFactory$(this.arg$1));
    }
}
