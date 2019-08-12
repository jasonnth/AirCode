package com.airbnb.android.explore.adapters;

import com.airbnb.android.core.models.InlineSearchFeedFilterItem;
import com.airbnb.android.explore.presenters.ExploreFeedItemPresenter.OnInlineFilterItemClickListener;

final /* synthetic */ class MTSectionsAdapter$$Lambda$4 implements OnInlineFilterItemClickListener {
    private final MTSectionsAdapter arg$1;

    private MTSectionsAdapter$$Lambda$4(MTSectionsAdapter mTSectionsAdapter) {
        this.arg$1 = mTSectionsAdapter;
    }

    public static OnInlineFilterItemClickListener lambdaFactory$(MTSectionsAdapter mTSectionsAdapter) {
        return new MTSectionsAdapter$$Lambda$4(mTSectionsAdapter);
    }

    public void onInlineSearchFeedFilterItemClick(InlineSearchFeedFilterItem inlineSearchFeedFilterItem) {
        this.arg$1.onInlineFilterItemClicked(inlineSearchFeedFilterItem);
    }
}
