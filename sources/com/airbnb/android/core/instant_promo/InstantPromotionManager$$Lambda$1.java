package com.airbnb.android.core.instant_promo;

import com.airbnb.android.core.instant_promo.models.InstantPromotion;
import java.util.Comparator;

final /* synthetic */ class InstantPromotionManager$$Lambda$1 implements Comparator {
    private static final InstantPromotionManager$$Lambda$1 instance = new InstantPromotionManager$$Lambda$1();

    private InstantPromotionManager$$Lambda$1() {
    }

    public static Comparator lambdaFactory$() {
        return instance;
    }

    public int compare(Object obj, Object obj2) {
        return InstantPromotionManager.lambda$getInstantPromoForConfigAndLogErfExposure$0((InstantPromotion) obj, (InstantPromotion) obj2);
    }
}
