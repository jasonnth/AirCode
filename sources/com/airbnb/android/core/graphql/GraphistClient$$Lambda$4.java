package com.airbnb.android.core.graphql;

import com.apollographql.apollo.ApolloCall.Callback;
import com.apollographql.apollo.api.Query;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.cache.normalized.CacheControl;
import com.apollographql.apollo.exception.ApolloException;
import com.google.common.base.Optional;
import p032rx.Emitter;
import p032rx.functions.Action1;

final /* synthetic */ class GraphistClient$$Lambda$4 implements Action1 {
    private final GraphistClient arg$1;
    private final Query arg$2;

    private GraphistClient$$Lambda$4(GraphistClient graphistClient, Query query) {
        this.arg$1 = graphistClient;
        this.arg$2 = query;
    }

    public static Action1 lambdaFactory$(GraphistClient graphistClient, Query query) {
        return new GraphistClient$$Lambda$4(graphistClient, query);
    }

    public void call(Object obj) {
        this.arg$1.client.query(this.arg$2).cacheControl(CacheControl.NETWORK_ONLY).enqueue(new Callback<Optional<D>>((Emitter) obj) {
            public void onResponse(Response<Optional<D>> response) {
                emitter.onNext(response);
                emitter.onCompleted();
            }

            public void onFailure(ApolloException e) {
                emitter.onError(e);
            }
        });
    }
}
