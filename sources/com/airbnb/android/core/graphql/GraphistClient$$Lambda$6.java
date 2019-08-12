package com.airbnb.android.core.graphql;

import p032rx.functions.Func1;

final /* synthetic */ class GraphistClient$$Lambda$6 implements Func1 {
    private final GraphistResponse arg$1;

    private GraphistClient$$Lambda$6(GraphistResponse graphistResponse) {
        this.arg$1 = graphistResponse;
    }

    public static Func1 lambdaFactory$(GraphistResponse graphistResponse) {
        return new GraphistClient$$Lambda$6(graphistResponse);
    }

    public Object call(Object obj) {
        return GraphistResponse.fromGraphistResponse(this.arg$1, true, (Throwable) obj);
    }
}
