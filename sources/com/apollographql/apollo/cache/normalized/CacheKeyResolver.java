package com.apollographql.apollo.cache.normalized;

import com.apollographql.apollo.api.C3107Operation;
import com.apollographql.apollo.api.C3107Operation.Variables;
import com.apollographql.apollo.api.Field;
import com.apollographql.apollo.api.Mutation;
import com.apollographql.apollo.api.Query;
import java.util.Map;

public abstract class CacheKeyResolver {
    public static final CacheKeyResolver DEFAULT = new CacheKeyResolver() {
        public CacheKey fromFieldRecordSet(Field field, Map<String, Object> map) {
            return CacheKey.NO_KEY;
        }

        public CacheKey fromFieldArguments(Field field, Variables variables) {
            return CacheKey.NO_KEY;
        }
    };
    public static final CacheKey MUTATION_ROOT_KEY = CacheKey.from("MUTATION_ROOT");
    public static final CacheKey QUERY_ROOT_KEY = CacheKey.from("QUERY_ROOT");

    public abstract CacheKey fromFieldArguments(Field field, Variables variables);

    public abstract CacheKey fromFieldRecordSet(Field field, Map<String, Object> map);

    public static CacheKey rootKeyForOperation(C3107Operation operation) {
        if (operation instanceof Query) {
            return QUERY_ROOT_KEY;
        }
        if (operation instanceof Mutation) {
            return MUTATION_ROOT_KEY;
        }
        throw new IllegalArgumentException("Unknown operation type.");
    }
}
