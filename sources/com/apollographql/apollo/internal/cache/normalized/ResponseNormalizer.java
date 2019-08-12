package com.apollographql.apollo.internal.cache.normalized;

import com.apollographql.apollo.api.C3107Operation;
import com.apollographql.apollo.api.C3107Operation.Variables;
import com.apollographql.apollo.api.Field;
import com.apollographql.apollo.api.internal.Optional;
import com.apollographql.apollo.cache.normalized.CacheKey;
import com.apollographql.apollo.cache.normalized.CacheKeyResolver;
import com.apollographql.apollo.cache.normalized.CacheReference;
import com.apollographql.apollo.cache.normalized.Record;
import com.apollographql.apollo.cache.normalized.Record.Builder;
import com.apollographql.apollo.cache.normalized.RecordSet;
import com.apollographql.apollo.internal.reader.ResponseReaderShadow;
import com.apollographql.apollo.internal.util.SimpleStack;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class ResponseNormalizer<R> implements ResponseReaderShadow<R> {
    public static final ResponseNormalizer NO_OP_NORMALIZER = new ResponseNormalizer() {
        public void willResolveRootQuery(C3107Operation operation) {
        }

        public void willResolve(Field field, Variables variables) {
        }

        public void didResolve(Field field, Variables variables) {
        }

        public void didParseScalar(Object value) {
        }

        public void willParseObject(Field field, Optional objectSource) {
        }

        public void didParseObject(Field field, Optional objectSource) {
        }

        public void didParseList(List array) {
        }

        public void willParseElement(int atIndex) {
        }

        public void didParseElement(int atIndex) {
        }

        public void didParseNull() {
        }

        public Collection<Record> records() {
            return Collections.emptyList();
        }

        public Set<String> dependentKeys() {
            return Collections.emptySet();
        }

        public CacheKey resolveCacheKey(Field field, Object record) {
            return CacheKey.NO_KEY;
        }
    };
    private Builder currentRecordBuilder;
    private Set<String> dependentKeys = Collections.emptySet();
    private List<String> path;
    private SimpleStack<List<String>> pathStack;
    private RecordSet recordSet = new RecordSet();
    private SimpleStack<Record> recordStack;
    private SimpleStack<Object> valueStack;

    public abstract CacheKey resolveCacheKey(Field field, R r);

    public Collection<Record> records() {
        return this.recordSet.allRecords();
    }

    public Set<String> dependentKeys() {
        return this.dependentKeys;
    }

    public void willResolveRootQuery(C3107Operation operation) {
        this.pathStack = new SimpleStack<>();
        this.recordStack = new SimpleStack<>();
        this.valueStack = new SimpleStack<>();
        this.dependentKeys = new HashSet();
        this.path = new ArrayList();
        this.currentRecordBuilder = Record.builder(CacheKeyResolver.rootKeyForOperation(operation).key());
        this.recordSet = new RecordSet();
    }

    public void willResolve(Field field, Variables variables) {
        this.path.add(field.cacheKey(variables));
    }

    public void didResolve(Field field, Variables variables) {
        this.path.remove(this.path.size() - 1);
        Object value = this.valueStack.pop();
        String cacheKey = field.cacheKey(variables);
        this.dependentKeys.add(this.currentRecordBuilder.key() + "." + cacheKey);
        this.currentRecordBuilder.addField(cacheKey, value);
        if (this.recordStack.isEmpty()) {
            this.recordSet.merge(this.currentRecordBuilder.build());
        }
    }

    public void didParseScalar(Object value) {
        this.valueStack.push(value);
    }

    public void willParseObject(Field field, Optional<R> objectSource) {
        this.pathStack.push(this.path);
        CacheKey cacheKey = objectSource.isPresent() ? resolveCacheKey(field, objectSource.get()) : CacheKey.NO_KEY;
        String cacheKeyValue = cacheKey.key();
        if (cacheKey == CacheKey.NO_KEY) {
            cacheKeyValue = pathToString();
        } else {
            this.path = new ArrayList();
            this.path.add(cacheKeyValue);
        }
        this.recordStack.push(this.currentRecordBuilder.build());
        this.currentRecordBuilder = Record.builder(cacheKeyValue);
    }

    public void didParseObject(Field field, Optional<R> optional) {
        this.path = (List) this.pathStack.pop();
        Record completedRecord = this.currentRecordBuilder.build();
        this.valueStack.push(new CacheReference(completedRecord.key()));
        this.dependentKeys.add(completedRecord.key());
        this.recordSet.merge(completedRecord);
        this.currentRecordBuilder = ((Record) this.recordStack.pop()).toBuilder();
    }

    public void didParseList(List array) {
        List<Object> parsedArray = new ArrayList<>(array.size());
        int size = array.size();
        for (int i = 0; i < size; i++) {
            parsedArray.add(0, this.valueStack.pop());
        }
        this.valueStack.push(parsedArray);
    }

    public void willParseElement(int atIndex) {
        this.path.add(Integer.toString(atIndex));
    }

    public void didParseElement(int atIndex) {
        this.path.remove(this.path.size() - 1);
    }

    public void didParseNull() {
        this.valueStack.push(null);
    }

    private String pathToString() {
        StringBuilder stringBuilder = new StringBuilder();
        int size = this.path.size();
        for (int i = 0; i < size; i++) {
            stringBuilder.append((String) this.path.get(i));
            if (i < size - 1) {
                stringBuilder.append(".");
            }
        }
        return stringBuilder.toString();
    }
}
