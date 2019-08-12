package com.airbnb.android.explore;

import com.airbnb.android.explore.data.AutocompleteData;
import com.google.common.base.Function;

final /* synthetic */ class ExploreJitneyLogger$$Lambda$10 implements Function {
    private final ExploreJitneyLogger arg$1;

    private ExploreJitneyLogger$$Lambda$10(ExploreJitneyLogger exploreJitneyLogger) {
        this.arg$1 = exploreJitneyLogger;
    }

    public static Function lambdaFactory$(ExploreJitneyLogger exploreJitneyLogger) {
        return new ExploreJitneyLogger$$Lambda$10(exploreJitneyLogger);
    }

    public Object apply(Object obj) {
        return this.arg$1.createTuple((AutocompleteData) obj);
    }
}
