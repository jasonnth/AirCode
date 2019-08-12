package com.airbnb.android.utils;

import android.os.Bundle;
import android.os.Parcelable;
import com.airbnb.android.utils.ExtendableBundleBuilder;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class ExtendableBundleBuilder<T extends ExtendableBundleBuilder<?>> {
    private final Bundle bundle = new Bundle();

    protected ExtendableBundleBuilder() {
    }

    public T putBoolean(String key, boolean value) {
        this.bundle.putBoolean(key, value);
        return this;
    }

    public T putByte(String key, byte value) {
        this.bundle.putByte(key, value);
        return this;
    }

    public T putChar(String key, char value) {
        this.bundle.putChar(key, value);
        return this;
    }

    public T putShort(String key, short value) {
        this.bundle.putShort(key, value);
        return this;
    }

    public T putInt(String key, int value) {
        this.bundle.putInt(key, value);
        return this;
    }

    public T putBundle(String key, Bundle value) {
        this.bundle.putBundle(key, value);
        return this;
    }

    public T putLong(String key, long value) {
        this.bundle.putLong(key, value);
        return this;
    }

    public T putFloat(String key, float value) {
        this.bundle.putFloat(key, value);
        return this;
    }

    public T putDouble(String key, double value) {
        this.bundle.putDouble(key, value);
        return this;
    }

    public T putString(String key, String value) {
        this.bundle.putString(key, value);
        return this;
    }

    public T putCharSequence(String key, CharSequence charSequence) {
        this.bundle.putCharSequence(key, charSequence);
        return this;
    }

    public T putIntArray(String key, int[] value) {
        this.bundle.putIntArray(key, value);
        return this;
    }

    public T putStringArray(String key, String[] value) {
        this.bundle.putStringArray(key, value);
        return this;
    }

    public T putStringArrayList(String key, ArrayList<String> array) {
        this.bundle.putStringArrayList(key, array);
        return this;
    }

    public T putParcelable(String key, Parcelable value) {
        this.bundle.putParcelable(key, value);
        return this;
    }

    public T putParcelableArrayList(String key, ArrayList<? extends Parcelable> value) {
        this.bundle.putParcelableArrayList(key, value);
        return this;
    }

    public T putAll(Bundle bundle2) {
        this.bundle.putAll(bundle2);
        return this;
    }

    public T putSerializable(String key, Serializable value) {
        this.bundle.putSerializable(key, value);
        return this;
    }

    public Bundle toBundle() {
        return new Bundle(this.bundle);
    }
}
