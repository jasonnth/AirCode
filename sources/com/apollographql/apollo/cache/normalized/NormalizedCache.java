package com.apollographql.apollo.cache.normalized;

import com.apollographql.apollo.cache.CacheHeaders;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class NormalizedCache {
    private RecordFieldAdapter recordFieldAdapter;

    public abstract Record loadRecord(String str, CacheHeaders cacheHeaders);

    public abstract Set<String> merge(Record record, CacheHeaders cacheHeaders);

    public NormalizedCache(RecordFieldAdapter recordFieldAdapter2) {
        this.recordFieldAdapter = recordFieldAdapter2;
    }

    /* access modifiers changed from: protected */
    public RecordFieldAdapter recordAdapter() {
        return this.recordFieldAdapter;
    }

    public Set<String> merge(Collection<Record> recordSet, CacheHeaders cacheHeaders) {
        Set<String> aggregatedDependentKeys = new LinkedHashSet<>();
        for (Record record : recordSet) {
            aggregatedDependentKeys.addAll(merge(record, cacheHeaders));
        }
        return aggregatedDependentKeys;
    }
}
