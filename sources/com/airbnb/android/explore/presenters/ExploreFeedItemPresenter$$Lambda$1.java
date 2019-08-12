package com.airbnb.android.explore.presenters;

import com.airbnb.android.core.models.InlineSearchFeedItem;
import java.util.Comparator;

final /* synthetic */ class ExploreFeedItemPresenter$$Lambda$1 implements Comparator {
    private static final ExploreFeedItemPresenter$$Lambda$1 instance = new ExploreFeedItemPresenter$$Lambda$1();

    private ExploreFeedItemPresenter$$Lambda$1() {
    }

    public static Comparator lambdaFactory$() {
        return instance;
    }

    public int compare(Object obj, Object obj2) {
        return ExploreFeedItemPresenter.lambda$adjustPositionsForGrid$0((InlineSearchFeedItem) obj, (InlineSearchFeedItem) obj2);
    }
}
