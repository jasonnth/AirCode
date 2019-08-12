package com.apollographql.apollo.internal;

import com.apollographql.apollo.api.C3107Operation;
import com.apollographql.apollo.api.ResponseFieldMapper;
import com.apollographql.apollo.api.internal.Utils;
import java.util.concurrent.ConcurrentHashMap;

public final class ResponseFieldMapperFactory {
    private final ConcurrentHashMap<Class, ResponseFieldMapper> pool = new ConcurrentHashMap<>();

    /* access modifiers changed from: 0000 */
    public ResponseFieldMapper create(C3107Operation operation) {
        Utils.checkNotNull(operation, "operation == null");
        Class operationClass = operation.getClass();
        ResponseFieldMapper mapper = (ResponseFieldMapper) this.pool.get(operationClass);
        if (mapper != null) {
            return mapper;
        }
        this.pool.putIfAbsent(operationClass, operation.responseFieldMapper());
        return (ResponseFieldMapper) this.pool.get(operationClass);
    }
}
