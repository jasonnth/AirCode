package com.airbnb.android.utils;

import com.google.common.util.concurrent.FutureCallback;

public abstract class SimpleFutureCallback<V> implements FutureCallback<V> {
    public void onFailure(Throwable t) {
    }
}
