package com.airbnb.android.core.instant_promo.models;

import com.airbnb.android.core.instant_promo.models.InstantPromotion.TemplateType;
import com.google.common.base.Predicate;

final /* synthetic */ class InstantPromotion$TemplateType$$Lambda$1 implements Predicate {
    private final String arg$1;

    private InstantPromotion$TemplateType$$Lambda$1(String str) {
        this.arg$1 = str;
    }

    public static Predicate lambdaFactory$(String str) {
        return new InstantPromotion$TemplateType$$Lambda$1(str);
    }

    public boolean apply(Object obj) {
        return TemplateType.lambda$fromKey$0(this.arg$1, (TemplateType) obj);
    }
}
