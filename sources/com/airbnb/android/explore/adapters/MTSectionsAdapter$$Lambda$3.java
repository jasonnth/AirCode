package com.airbnb.android.explore.adapters;

import com.airbnb.android.core.models.ExploreSection;
import com.google.common.base.Predicate;

final /* synthetic */ class MTSectionsAdapter$$Lambda$3 implements Predicate {
    private final MTSectionsAdapter arg$1;

    private MTSectionsAdapter$$Lambda$3(MTSectionsAdapter mTSectionsAdapter) {
        this.arg$1 = mTSectionsAdapter;
    }

    public static Predicate lambdaFactory$(MTSectionsAdapter mTSectionsAdapter) {
        return new MTSectionsAdapter$$Lambda$3(mTSectionsAdapter);
    }

    public boolean apply(Object obj) {
        return MTSectionsAdapter.lambda$syncWithDataController$2(this.arg$1, (ExploreSection) obj);
    }
}
