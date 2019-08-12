package com.apollographql.apollo.internal.cache.normalized;

import com.apollographql.apollo.cache.normalized.CacheReference;
import com.apollographql.apollo.cache.normalized.Record;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map.Entry;

public final class RecordWeigher {
    public static int byteChange(Object newValue, Object oldValue) {
        return weighField(newValue) - weighField(oldValue);
    }

    public static int calculateBytes(Record record) {
        int size = record.key().getBytes().length + 16;
        for (Entry<String, Object> field : record.fields().entrySet()) {
            size += ((String) field.getKey()).getBytes().length + weighField(field.getValue());
        }
        return size;
    }

    private static int weighField(Object field) {
        if (field instanceof List) {
            int size = 16;
            for (Object listItem : (List) field) {
                size += weighField(listItem);
            }
            return size;
        } else if (field instanceof String) {
            return ((String) field).getBytes().length;
        } else {
            if (field instanceof Boolean) {
                return 16;
            }
            if (field instanceof BigDecimal) {
                return 32;
            }
            if (field instanceof CacheReference) {
                return ((CacheReference) field).key().getBytes().length + 16;
            }
            throw new IllegalStateException("Unknown field type in Record. " + field.getClass().getName());
        }
    }
}
