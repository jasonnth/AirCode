package com.airbnb.android.core.graphql;

import com.apollographql.apollo.api.Query;
import p032rx.functions.Func1;

final /* synthetic */ class GraphistClient$$Lambda$1 implements Func1 {
    private final GraphistClient arg$1;
    private final Query arg$2;

    private GraphistClient$$Lambda$1(GraphistClient graphistClient, Query query) {
        this.arg$1 = graphistClient;
        this.arg$2 = query;
    }

    public static Func1 lambdaFactory$(GraphistClient graphistClient, Query query) {
        return new GraphistClient$$Lambda$1(graphistClient, query);
    }

    public Object call(Object obj) {
        return GraphistClient.lambda$fetchDoubleRepsonse$1(this.arg$1, this.arg$2, (GraphistResponse) obj);
    }
}
