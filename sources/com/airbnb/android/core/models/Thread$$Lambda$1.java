package com.airbnb.android.core.models;

import com.airbnb.android.utils.ListUtils.Condition;

final /* synthetic */ class Thread$$Lambda$1 implements Condition {
    private final Post arg$1;
    private final Long arg$2;

    private Thread$$Lambda$1(Post post, Long l) {
        this.arg$1 = post;
        this.arg$2 = l;
    }

    public static Condition lambdaFactory$(Post post, Long l) {
        return new Thread$$Lambda$1(post, l);
    }

    public boolean check(Object obj) {
        return Thread.lambda$addPost$0(this.arg$1, this.arg$2, (Post) obj);
    }
}
