package com.airbnb.android.explore;

import com.airbnb.android.core.models.find.TopLevelSearchParams;
import com.airbnb.jitney.event.logging.ExploreSubtab.p098v1.C2139ExploreSubtab;
import com.airbnb.jitney.event.logging.Search.p246v1.SearchSubtabClickServerLogFailEvent.Builder;
import com.airbnb.jitney.event.logging.SearchContext.p249v1.C2731SearchContext;
import java.util.Arrays;
import p032rx.functions.Action1;

final /* synthetic */ class ExploreJitneyLogger$$Lambda$12 implements Action1 {
    private final ExploreJitneyLogger arg$1;
    private final String arg$2;
    private final C2139ExploreSubtab arg$3;
    private final C2139ExploreSubtab arg$4;
    private final C2731SearchContext arg$5;
    private final boolean arg$6;
    private final TopLevelSearchParams arg$7;
    private final String arg$8;

    private ExploreJitneyLogger$$Lambda$12(ExploreJitneyLogger exploreJitneyLogger, String str, C2139ExploreSubtab exploreSubtab, C2139ExploreSubtab exploreSubtab2, C2731SearchContext searchContext, boolean z, TopLevelSearchParams topLevelSearchParams, String str2) {
        this.arg$1 = exploreJitneyLogger;
        this.arg$2 = str;
        this.arg$3 = exploreSubtab;
        this.arg$4 = exploreSubtab2;
        this.arg$5 = searchContext;
        this.arg$6 = z;
        this.arg$7 = topLevelSearchParams;
        this.arg$8 = str2;
    }

    public static Action1 lambdaFactory$(ExploreJitneyLogger exploreJitneyLogger, String str, C2139ExploreSubtab exploreSubtab, C2139ExploreSubtab exploreSubtab2, C2731SearchContext searchContext, boolean z, TopLevelSearchParams topLevelSearchParams, String str2) {
        return new ExploreJitneyLogger$$Lambda$12(exploreJitneyLogger, str, exploreSubtab, exploreSubtab2, searchContext, z, topLevelSearchParams, str2);
    }

    public void call(Object obj) {
        this.arg$1.publish(new Builder(this.arg$1.context(), this.arg$2, this.arg$3, this.arg$4, this.arg$5, Boolean.valueOf(this.arg$6)).location(this.arg$7.searchTerm()).dates(Arrays.asList(new String[]{this.arg$1.formatDate(this.arg$7.checkInDate()), this.arg$1.formatDate(this.arg$7.checkOutDate())})).guests(Long.valueOf((long) this.arg$7.guestDetails().totalGuestCount())).location_next(this.arg$8));
    }
}
