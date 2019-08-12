package com.apollographql.apollo.cache.normalized;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CacheReference {
    private static final Pattern SERIALIZATION_REGEX_PATTERN = Pattern.compile("ApolloCacheReference\\{(.*)\\}");
    private final String key;

    public CacheReference(String key2) {
        this.key = key2;
    }

    public String key() {
        return this.key;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CacheReference that = (CacheReference) o;
        if (this.key != null) {
            return this.key.equals(that.key);
        }
        if (that.key != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        if (this.key != null) {
            return this.key.hashCode();
        }
        return 0;
    }

    public String toString() {
        return this.key;
    }

    public String serialize() {
        return String.format("ApolloCacheReference{%s}", new Object[]{this.key});
    }

    public static CacheReference deserialize(String serializedCacheReference) {
        Matcher matcher = SERIALIZATION_REGEX_PATTERN.matcher(serializedCacheReference);
        if (matcher.find() && matcher.groupCount() == 1) {
            return new CacheReference(matcher.group(1));
        }
        throw new IllegalArgumentException("Not a cache reference: " + serializedCacheReference + " Must be of the form:" + "ApolloCacheReference{%s}");
    }

    public static boolean canDeserialize(String value) {
        return SERIALIZATION_REGEX_PATTERN.matcher(value).matches();
    }
}
