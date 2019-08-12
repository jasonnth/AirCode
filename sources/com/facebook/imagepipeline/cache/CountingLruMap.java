package com.facebook.imagepipeline.cache;

import com.android.internal.util.Predicate;
import com.facebook.common.internal.VisibleForTesting;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class CountingLruMap<K, V> {
    private final LinkedHashMap<K, V> mMap = new LinkedHashMap<>();
    private int mSizeInBytes = 0;
    private final ValueDescriptor<V> mValueDescriptor;

    public CountingLruMap(ValueDescriptor<V> valueDescriptor) {
        this.mValueDescriptor = valueDescriptor;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public synchronized ArrayList<K> getKeys() {
        return new ArrayList<>(this.mMap.keySet());
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public synchronized ArrayList<V> getValues() {
        return new ArrayList<>(this.mMap.values());
    }

    public synchronized int getCount() {
        return this.mMap.size();
    }

    public synchronized int getSizeInBytes() {
        return this.mSizeInBytes;
    }

    public synchronized K getFirstKey() {
        return this.mMap.isEmpty() ? null : this.mMap.keySet().iterator().next();
    }

    public synchronized ArrayList<Entry<K, V>> getMatchingEntries(Predicate<K> predicate) {
        ArrayList<Entry<K, V>> matchingEntries;
        matchingEntries = new ArrayList<>();
        for (Entry<K, V> entry : this.mMap.entrySet()) {
            if (predicate == null || predicate.apply(entry.getKey())) {
                matchingEntries.add(entry);
            }
        }
        return matchingEntries;
    }

    public synchronized boolean contains(K key) {
        return this.mMap.containsKey(key);
    }

    public synchronized V get(K key) {
        return this.mMap.get(key);
    }

    public synchronized V put(K key, V value) {
        V oldValue;
        oldValue = this.mMap.remove(key);
        this.mSizeInBytes -= getValueSizeInBytes(oldValue);
        this.mMap.put(key, value);
        this.mSizeInBytes += getValueSizeInBytes(value);
        return oldValue;
    }

    public synchronized V remove(K key) {
        V oldValue;
        oldValue = this.mMap.remove(key);
        this.mSizeInBytes -= getValueSizeInBytes(oldValue);
        return oldValue;
    }

    public synchronized ArrayList<V> removeAll(Predicate<K> predicate) {
        ArrayList<V> oldValues;
        oldValues = new ArrayList<>();
        Iterator<Entry<K, V>> iterator = this.mMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<K, V> entry = (Entry) iterator.next();
            if (predicate == null || predicate.apply(entry.getKey())) {
                oldValues.add(entry.getValue());
                this.mSizeInBytes -= getValueSizeInBytes(entry.getValue());
                iterator.remove();
            }
        }
        return oldValues;
    }

    public synchronized ArrayList<V> clear() {
        ArrayList<V> oldValues;
        oldValues = new ArrayList<>(this.mMap.values());
        this.mMap.clear();
        this.mSizeInBytes = 0;
        return oldValues;
    }

    private int getValueSizeInBytes(V value) {
        if (value == null) {
            return 0;
        }
        return this.mValueDescriptor.getSizeInBytes(value);
    }
}
