package com.airbnb.android.contentframework.controller;

import java.util.List;
import p032rx.functions.Action1;

final /* synthetic */ class StoryPublishController$$Lambda$4 implements Action1 {
    private final List arg$1;

    private StoryPublishController$$Lambda$4(List list) {
        this.arg$1 = list;
    }

    public static Action1 lambdaFactory$(List list) {
        return new StoryPublishController$$Lambda$4(list);
    }

    public void call(Object obj) {
        this.arg$1.add((String) obj);
    }
}
