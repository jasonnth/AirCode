package com.nytimes.android.external.cache;

import java.util.Map.Entry;

public final class RemovalNotification<K, V> implements Entry<K, V> {
    private final RemovalCause cause;
    private final K key;
    private final V value;

    public static <K, V> RemovalNotification<K, V> create(K key2, V value2, RemovalCause cause2) {
        return new RemovalNotification<>(key2, value2, cause2);
    }

    private RemovalNotification(K key2, V value2, RemovalCause cause2) {
        this.key = key2;
        this.value = value2;
        this.cause = (RemovalCause) Preconditions.checkNotNull(cause2);
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    public final V setValue(V v) {
        throw new UnsupportedOperationException();
    }

    public boolean equals(Object object) {
        if (!(object instanceof Entry)) {
            return false;
        }
        Entry<?, ?> that = (Entry) object;
        if (!Objects.equal(getKey(), that.getKey()) || !Objects.equal(getValue(), that.getValue())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = 0;
        K k = getKey();
        V v = getValue();
        int hashCode = k == null ? 0 : k.hashCode();
        if (v != null) {
            i = v.hashCode();
        }
        return i ^ hashCode;
    }

    public String toString() {
        return getKey() + "=" + getValue();
    }
}
