package com.airbnb.android.core.graphql;

import com.apollographql.apollo.api.Response;
import p032rx.functions.Func1;

final /* synthetic */ class GraphistClient$$Lambda$3 implements Func1 {
    private static final GraphistClient$$Lambda$3 instance = new GraphistClient$$Lambda$3();

    private GraphistClient$$Lambda$3() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return GraphistResponse.fromApolloResponse((Response) obj, true);
    }
}
