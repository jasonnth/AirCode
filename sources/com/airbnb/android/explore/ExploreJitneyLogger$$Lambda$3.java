package com.airbnb.android.explore;

import com.airbnb.android.core.models.C6120RoomType;
import com.google.common.base.Function;

final /* synthetic */ class ExploreJitneyLogger$$Lambda$3 implements Function {
    private final ExploreJitneyLogger arg$1;

    private ExploreJitneyLogger$$Lambda$3(ExploreJitneyLogger exploreJitneyLogger) {
        this.arg$1 = exploreJitneyLogger;
    }

    public static Function lambdaFactory$(ExploreJitneyLogger exploreJitneyLogger) {
        return new ExploreJitneyLogger$$Lambda$3(exploreJitneyLogger);
    }

    public Object apply(Object obj) {
        return ExploreJitneyLogger.lambda$homeFilterImpression$2(this.arg$1, (C6120RoomType) obj);
    }
}