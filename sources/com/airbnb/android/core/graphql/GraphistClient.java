package com.airbnb.android.core.graphql;

import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.C3107Operation.Data;
import com.apollographql.apollo.api.Query;
import com.google.common.base.Optional;
import p032rx.Emitter.BackpressureMode;
import p032rx.Observable;

public class GraphistClient {
    private ApolloClient client;

    public GraphistClient(ApolloClient apolloClient) {
        this.client = apolloClient;
    }

    public <D extends Data> Observable<GraphistResponse<D>> fetchDoubleRepsonse(Query<D, Optional<D>, ?> query) {
        return fetchFromCache(query).flatMap(GraphistClient$$Lambda$1.lambdaFactory$(this, query));
    }

    static /* synthetic */ Observable lambda$fetchDoubleRepsonse$1(GraphistClient graphistClient, Query query, GraphistResponse cacheResponse) {
        Observable<GraphistResponse<D>> networkObservable = graphistClient.fetchFromNetwork(query);
        return (cacheResponse.hasErrors() || cacheResponse.data() == null || !cacheResponse.data().isPresent()) ? networkObservable : Observable.concat(Observable.just(cacheResponse), networkObservable.onErrorReturn(GraphistClient$$Lambda$6.lambdaFactory$(cacheResponse)));
    }

    public <D extends Data> Observable<GraphistResponse<D>> fetchFromCache(Query<D, Optional<D>, ?> query) {
        return Observable.create(GraphistClient$$Lambda$2.lambdaFactory$(this, query), BackpressureMode.BUFFER).map(GraphistClient$$Lambda$3.lambdaFactory$());
    }

    public <D extends Data> Observable<GraphistResponse<D>> fetchFromNetwork(Query<D, Optional<D>, ?> query) {
        return Observable.create(GraphistClient$$Lambda$4.lambdaFactory$(this, query), BackpressureMode.BUFFER).map(GraphistClient$$Lambda$5.lambdaFactory$());
    }
}
