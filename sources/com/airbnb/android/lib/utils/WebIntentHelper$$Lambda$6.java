package com.airbnb.android.lib.utils;

import com.airbnb.android.core.p008mt.models.PrimaryCategory;
import com.google.common.base.Predicate;

final /* synthetic */ class WebIntentHelper$$Lambda$6 implements Predicate {
    private static final WebIntentHelper$$Lambda$6 instance = new WebIntentHelper$$Lambda$6();

    private WebIntentHelper$$Lambda$6() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return WebIntentHelper.lambda$parseExperienceCategories$3((PrimaryCategory) obj);
    }
}
