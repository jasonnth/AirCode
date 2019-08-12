package com.apollographql.apollo.cache.normalized;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public final class RecordSet {
    private final Map<String, Record> recordMap = new LinkedHashMap();

    public Set<String> merge(Record apolloRecord) {
        Record oldRecord = (Record) this.recordMap.get(apolloRecord.key());
        if (oldRecord != null) {
            return oldRecord.mergeWith(apolloRecord);
        }
        this.recordMap.put(apolloRecord.key(), apolloRecord);
        return Collections.emptySet();
    }

    public Collection<Record> allRecords() {
        return this.recordMap.values();
    }
}
