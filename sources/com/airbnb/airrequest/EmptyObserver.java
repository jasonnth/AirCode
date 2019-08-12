package com.airbnb.airrequest;

import p032rx.Observer;

final class EmptyObserver<T> implements Observer<T> {
    private static final EmptyObserver INSTANCE = new EmptyObserver();

    private EmptyObserver() {
    }

    static <T> EmptyObserver<T> instance() {
        return INSTANCE;
    }

    public void onCompleted() {
    }

    public void onError(Throwable e) {
    }

    public void onNext(T t) {
    }
}
