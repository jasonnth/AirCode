package com.airbnb.android.core.graphql;

import com.apollographql.apollo.api.Response;
import p032rx.functions.Func1;

final /* synthetic */ class GraphistClient$$Lambda$5 implements Func1 {
    private static final GraphistClient$$Lambda$5 instance = new GraphistClient$$Lambda$5();

    private GraphistClient$$Lambda$5() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return GraphistResponse.fromApolloResponse((Response) obj, false);
    }
}
