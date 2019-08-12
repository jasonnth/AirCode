package com.airbnb.android.core.models;

import com.airbnb.android.core.models.ReferralContact.ReferralItem;
import java.util.Comparator;

final /* synthetic */ class ReferralContact$$Lambda$1 implements Comparator {
    private static final ReferralContact$$Lambda$1 instance = new ReferralContact$$Lambda$1();

    private ReferralContact$$Lambda$1() {
    }

    public static Comparator lambdaFactory$() {
        return instance;
    }

    public int compare(Object obj, Object obj2) {
        return ReferralContact.lambda$getItems$0((ReferralItem) obj, (ReferralItem) obj2);
    }
}
