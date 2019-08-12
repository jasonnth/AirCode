package com.airbnb.android.profile_completion;

import com.google.common.base.Predicate;

final /* synthetic */ class ProfileCompletionManager$$Lambda$1 implements Predicate {
    private static final ProfileCompletionManager$$Lambda$1 instance = new ProfileCompletionManager$$Lambda$1();

    private ProfileCompletionManager$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ((CompletionStep) obj).isBadgingStep();
    }
}
