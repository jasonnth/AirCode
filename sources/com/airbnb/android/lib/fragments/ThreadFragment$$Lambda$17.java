package com.airbnb.android.lib.fragments;

import com.airbnb.android.core.responses.MessageTranslationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ThreadFragment$$Lambda$17 implements Action1 {
    private final ThreadFragment arg$1;

    private ThreadFragment$$Lambda$17(ThreadFragment threadFragment) {
        this.arg$1 = threadFragment;
    }

    public static Action1 lambdaFactory$(ThreadFragment threadFragment) {
        return new ThreadFragment$$Lambda$17(threadFragment);
    }

    public void call(Object obj) {
        this.arg$1.adapter.setTranslatedMessages(((MessageTranslationResponse) obj).translatedMessages);
    }
}
