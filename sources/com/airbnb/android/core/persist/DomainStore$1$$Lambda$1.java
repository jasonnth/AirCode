package com.airbnb.android.core.persist;

import com.airbnb.android.core.models.Domain;
import com.google.common.base.Function;

final /* synthetic */ class DomainStore$1$$Lambda$1 implements Function {
    private static final DomainStore$1$$Lambda$1 instance = new DomainStore$1$$Lambda$1();

    private DomainStore$1$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ((Domain) obj).mDomainName;
    }
}
