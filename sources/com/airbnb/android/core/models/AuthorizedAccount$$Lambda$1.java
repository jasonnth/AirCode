package com.airbnb.android.core.models;

import java.util.Comparator;

final /* synthetic */ class AuthorizedAccount$$Lambda$1 implements Comparator {
    private static final AuthorizedAccount$$Lambda$1 instance = new AuthorizedAccount$$Lambda$1();

    private AuthorizedAccount$$Lambda$1() {
    }

    public static Comparator lambdaFactory$() {
        return instance;
    }

    public int compare(Object obj, Object obj2) {
        return ((AuthorizedAccount) obj).mUsername.compareTo(((AuthorizedAccount) obj2).mUsername);
    }
}
