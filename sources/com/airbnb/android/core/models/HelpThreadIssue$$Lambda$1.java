package com.airbnb.android.core.models;

import com.google.common.base.Predicate;

final /* synthetic */ class HelpThreadIssue$$Lambda$1 implements Predicate {
    private static final HelpThreadIssue$$Lambda$1 instance = new HelpThreadIssue$$Lambda$1();

    private HelpThreadIssue$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ((HelpThreadNode) obj).hasAttachments();
    }
}
