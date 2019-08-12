package com.apollographql.apollo.internal.field;

import com.apollographql.apollo.api.C3107Operation.Variables;
import com.apollographql.apollo.api.Field;
import com.apollographql.apollo.api.Field.ObjectField;
import com.apollographql.apollo.api.Field.ObjectListField;
import com.apollographql.apollo.api.Field.ScalarListField;
import com.apollographql.apollo.cache.CacheHeaders;
import com.apollographql.apollo.cache.normalized.CacheKey;
import com.apollographql.apollo.cache.normalized.CacheKeyResolver;
import com.apollographql.apollo.cache.normalized.CacheReference;
import com.apollographql.apollo.cache.normalized.Record;
import com.apollographql.apollo.internal.cache.normalized.ReadableStore;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class CacheFieldValueResolver implements FieldValueResolver<Record> {
    private final CacheHeaders cacheHeaders;
    private final CacheKeyResolver cacheKeyResolver;
    private final ReadableStore readableCache;
    private final Variables variables;

    public CacheFieldValueResolver(ReadableStore readableCache2, Variables variables2, CacheKeyResolver cacheKeyResolver2, CacheHeaders cacheHeaders2) {
        this.readableCache = readableCache2;
        this.variables = variables2;
        this.cacheKeyResolver = cacheKeyResolver2;
        this.cacheHeaders = cacheHeaders2;
    }

    public <T> T valueFor(Record record, Field field) throws IOException {
        if (field instanceof ObjectField) {
            return valueFor(record, (ObjectField) field);
        }
        if (field instanceof ScalarListField) {
            return fieldValue(record, field);
        }
        if (field instanceof ObjectListField) {
            return valueFor(record, (ObjectListField) field);
        }
        return fieldValue(record, field);
    }

    private Record valueFor(Record record, ObjectField field) throws IOException {
        CacheReference cacheReference;
        CacheKey fieldCacheKey = this.cacheKeyResolver.fromFieldArguments(field, this.variables);
        if (fieldCacheKey != CacheKey.NO_KEY) {
            cacheReference = new CacheReference(fieldCacheKey.key());
        } else {
            cacheReference = (CacheReference) fieldValue(record, field);
        }
        if (cacheReference != null) {
            return this.readableCache.read(cacheReference.key(), this.cacheHeaders);
        }
        return null;
    }

    private List<Record> valueFor(Record record, ObjectListField field) throws IOException {
        List<CacheReference> values = (List) fieldValue(record, field);
        List<Record> result = new ArrayList<>();
        for (CacheReference reference : values) {
            result.add(this.readableCache.read(reference.key(), this.cacheHeaders));
        }
        return result;
    }

    private <T> T fieldValue(Record record, Field field) throws IOException {
        String fieldKey = field.cacheKey(this.variables);
        if (record.hasField(fieldKey)) {
            return record.field(fieldKey);
        }
        throw new IOException("Missing value: " + field.fieldName());
    }
}
