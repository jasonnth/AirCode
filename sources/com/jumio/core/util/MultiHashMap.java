package com.jumio.core.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MultiHashMap<K, V> extends HashMap<K, List<V>> {
    public void putOne(K k, V v) {
        if (k != null && v != null) {
            if (!containsKey(k)) {
                List<V> vals = defaultList();
                vals.add(v);
                put(k, vals);
                return;
            }
            ((List) get(k)).add(v);
        }
    }

    public int count(K k) {
        if (containsKey(k)) {
            return ((List) get(k)).size();
        }
        return 0;
    }

    private List<V> defaultList() {
        return new LinkedList();
    }

    public List<V> put(K k, List<V> value) {
        if (value != null) {
            return (List) super.put(k, value);
        }
        return null;
    }

    public void putAll(Map<? extends K, ? extends List<V>> map) {
        super.putAll(map);
    }

    public List<V> remove(Object key) {
        return (List) super.remove(key);
    }
}
