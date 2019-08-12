package com.airbnb.android.lib.utils;

import android.util.Log;
import com.google.common.collect.ImmutableMap.Builder;

public class SafeImmutableMapBuilder<K, V> extends Builder<K, V> {
    private static final String TAG = SafeImmutableMapBuilder.class.getSimpleName();

    public SafeImmutableMapBuilder<K, V> put(K key, V value) {
        if (value != null) {
            return (SafeImmutableMapBuilder) super.put(key, value);
        }
        Log.e(TAG, "tried to put null value for key: " + key);
        return this;
    }
}
