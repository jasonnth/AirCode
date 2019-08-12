package com.apollographql.apollo.cache.normalized;

import com.apollographql.apollo.internal.cache.normalized.RecordWeigher;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class Record {
    private final Map<String, Object> fields;
    private final String key;
    private volatile int sizeInBytes = -1;

    public static class Builder {
        private final Map<String, Object> fields;
        private final String key;

        public Builder(String key2) {
            this(key2, new LinkedHashMap());
        }

        public Builder(String key2, Map<String, Object> fields2) {
            this.key = key2;
            this.fields = fields2;
        }

        public Builder addField(String key2, Object value) {
            this.fields.put(key2, value);
            return this;
        }

        public String key() {
            return this.key;
        }

        public Record build() {
            return new Record(this.key, this.fields);
        }
    }

    public static Builder builder(String key2) {
        return new Builder(key2);
    }

    public Builder toBuilder() {
        return new Builder(key(), this.fields);
    }

    public Record(String key2, Map<String, Object> fields2) {
        this.key = key2;
        this.fields = fields2;
    }

    public Object field(String fieldKey) {
        return this.fields.get(fieldKey);
    }

    public boolean hasField(String fieldKey) {
        return this.fields.containsKey(fieldKey);
    }

    public String key() {
        return this.key;
    }

    public Set<String> mergeWith(Record otherRecord) {
        Set<String> changedKeys = new HashSet<>();
        for (Entry<String, Object> field : otherRecord.fields.entrySet()) {
            Object newFieldValue = field.getValue();
            Object oldFieldValue = this.fields.get(field.getKey());
            if ((oldFieldValue == null && newFieldValue != null) || (oldFieldValue != null && !oldFieldValue.equals(newFieldValue))) {
                this.fields.put(field.getKey(), newFieldValue);
                changedKeys.add(key() + "." + ((String) field.getKey()));
                adjustSizeEstimate(newFieldValue, oldFieldValue);
            }
        }
        return changedKeys;
    }

    public Map<String, Object> fields() {
        return this.fields;
    }

    public int sizeEstimateBytes() {
        if (this.sizeInBytes == -1) {
            this.sizeInBytes = RecordWeigher.calculateBytes(this);
        }
        return this.sizeInBytes;
    }

    private void adjustSizeEstimate(Object newFieldValue, Object oldFieldValue) {
        if (this.sizeInBytes != -1) {
            this.sizeInBytes += RecordWeigher.byteChange(newFieldValue, oldFieldValue);
        }
    }
}
