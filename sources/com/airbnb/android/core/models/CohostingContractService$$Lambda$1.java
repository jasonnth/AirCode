package com.airbnb.android.core.models;

import com.google.common.base.Predicate;

final /* synthetic */ class CohostingContractService$$Lambda$1 implements Predicate {
    private final int arg$1;

    private CohostingContractService$$Lambda$1(int i) {
        this.arg$1 = i;
    }

    public static Predicate lambdaFactory$(int i) {
        return new CohostingContractService$$Lambda$1(i);
    }

    public boolean apply(Object obj) {
        return CohostingContractService.lambda$getTitleFromIndex$0(this.arg$1, (CohostingContractService) obj);
    }
}
