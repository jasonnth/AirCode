package com.airbnb.rxgroups;

import p032rx.Subscription;

public interface RequestSubscription extends Subscription {
    void cancel();

    boolean isCancelled();
}
