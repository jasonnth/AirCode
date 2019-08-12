package com.apollographql.apollo.cache.normalized.sql;

import com.apollographql.apollo.api.internal.Utils;
import com.apollographql.apollo.cache.normalized.NormalizedCacheFactory;
import com.apollographql.apollo.cache.normalized.RecordFieldAdapter;

public final class SqlNormalizedCacheFactory implements NormalizedCacheFactory<SqlNormalizedCache> {
    private final ApolloSqlHelper helper;

    public SqlNormalizedCacheFactory(ApolloSqlHelper helper2) {
        this.helper = (ApolloSqlHelper) Utils.checkNotNull(helper2, "helper == null");
    }

    public SqlNormalizedCache createNormalizedCache(RecordFieldAdapter recordFieldAdapter) {
        return new SqlNormalizedCache(recordFieldAdapter, this.helper);
    }
}
