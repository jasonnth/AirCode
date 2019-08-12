package com.airbnb.android.core.analytics;

import com.airbnb.airrequest.Transformer;
import p032rx.Observable;
import p032rx.functions.Action1;

final /* synthetic */ class MessagingJitneyLogger$$Lambda$1 implements Transformer {
    private final Action1 arg$1;

    private MessagingJitneyLogger$$Lambda$1(Action1 action1) {
        this.arg$1 = action1;
    }

    public static Transformer lambdaFactory$(Action1 action1) {
        return new MessagingJitneyLogger$$Lambda$1(action1);
    }

    public Object call(Object obj) {
        return ((Observable) obj).doOnSubscribe(MessagingJitneyLogger$$Lambda$2.lambdaFactory$(this.arg$1)).doOnNext(MessagingJitneyLogger$$Lambda$3.lambdaFactory$(this.arg$1)).doOnError(MessagingJitneyLogger$$Lambda$4.lambdaFactory$(this.arg$1));
    }
}
