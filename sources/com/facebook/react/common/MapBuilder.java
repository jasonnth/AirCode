package com.facebook.react.common;

import java.util.HashMap;
import java.util.Map;

public class MapBuilder {

    public static final class Builder<K, V> {
        private Map mMap;
        private boolean mUnderConstruction;

        private Builder() {
            this.mMap = MapBuilder.newHashMap();
            this.mUnderConstruction = true;
        }

        public Builder<K, V> put(K k, V v) {
            if (!this.mUnderConstruction) {
                throw new IllegalStateException("Underlying map has already been built");
            }
            this.mMap.put(k, v);
            return this;
        }

        public Map<K, V> build() {
            if (!this.mUnderConstruction) {
                throw new IllegalStateException("Underlying map has already been built");
            }
            this.mUnderConstruction = false;
            return this.mMap;
        }
    }

    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap<>();
    }

    /* renamed from: of */
    public static <K, V> Map<K, V> m1881of() {
        return newHashMap();
    }

    /* renamed from: of */
    public static <K, V> Map<K, V> m1882of(K k1, V v1) {
        Map map = m1881of();
        map.put(k1, v1);
        return map;
    }

    /* renamed from: of */
    public static <K, V> Map<K, V> m1883of(K k1, V v1, K k2, V v2) {
        Map map = m1881of();
        map.put(k1, v1);
        map.put(k2, v2);
        return map;
    }

    /* renamed from: of */
    public static <K, V> Map<K, V> m1884of(K k1, V v1, K k2, V v2, K k3, V v3) {
        Map map = m1881of();
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        return map;
    }

    /* renamed from: of */
    public static <K, V> Map<K, V> m1885of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        Map map = m1881of();
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        return map;
    }

    /* renamed from: of */
    public static <K, V> Map<K, V> m1886of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        Map map = m1881of();
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        map.put(k5, v5);
        return map;
    }

    /* renamed from: of */
    public static <K, V> Map<K, V> m1887of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6) {
        Map map = m1881of();
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        map.put(k5, v5);
        map.put(k6, v6);
        return map;
    }

    /* renamed from: of */
    public static <K, V> Map<K, V> m1888of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7) {
        Map map = m1881of();
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        map.put(k5, v5);
        map.put(k6, v6);
        map.put(k7, v7);
        return map;
    }

    public static <K, V> Builder<K, V> builder() {
        return new Builder<>();
    }
}
