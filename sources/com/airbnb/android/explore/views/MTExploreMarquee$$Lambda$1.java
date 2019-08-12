package com.airbnb.android.explore.views;

import com.airbnb.android.explore.views.MTExploreMarquee.ExploreMarqueeChildListener;

final /* synthetic */ class MTExploreMarquee$$Lambda$1 implements ExploreMarqueeChildListener {
    private final MTExploreMarquee arg$1;

    private MTExploreMarquee$$Lambda$1(MTExploreMarquee mTExploreMarquee) {
        this.arg$1 = mTExploreMarquee;
    }

    public static ExploreMarqueeChildListener lambdaFactory$(MTExploreMarquee mTExploreMarquee) {
        return new MTExploreMarquee$$Lambda$1(mTExploreMarquee);
    }

    public void setBabuMode(boolean z) {
        this.arg$1.setBabuMode(z);
    }
}
