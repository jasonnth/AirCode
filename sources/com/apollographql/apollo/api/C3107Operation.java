package com.apollographql.apollo.api;

import com.apollographql.apollo.api.C3107Operation.Data;
import com.apollographql.apollo.api.C3107Operation.Variables;
import java.util.Collections;
import java.util.Map;

/* renamed from: com.apollographql.apollo.api.Operation */
public interface C3107Operation<D extends Data, T, V extends Variables> {
    public static final Variables EMPTY_VARIABLES = new Variables();

    /* renamed from: com.apollographql.apollo.api.Operation$Data */
    public interface Data {
    }

    /* renamed from: com.apollographql.apollo.api.Operation$Variables */
    public static class Variables {
        protected Variables() {
        }

        public Map<String, Object> valueMap() {
            return Collections.emptyMap();
        }
    }

    String queryDocument();

    ResponseFieldMapper<D> responseFieldMapper();

    V variables();

    T wrapData(D d);
}
