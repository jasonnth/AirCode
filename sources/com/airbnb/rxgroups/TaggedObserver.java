package com.airbnb.rxgroups;

import p032rx.Observer;

public interface TaggedObserver<T> extends Observer<T> {
    String getTag();
}
