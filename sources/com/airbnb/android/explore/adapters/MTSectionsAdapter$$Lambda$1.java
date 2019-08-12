package com.airbnb.android.explore.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class MTSectionsAdapter$$Lambda$1 implements OnClickListener {
    private final MTSectionsAdapter arg$1;

    private MTSectionsAdapter$$Lambda$1(MTSectionsAdapter mTSectionsAdapter) {
        this.arg$1 = mTSectionsAdapter;
    }

    public static OnClickListener lambdaFactory$(MTSectionsAdapter mTSectionsAdapter) {
        return new MTSectionsAdapter$$Lambda$1(mTSectionsAdapter);
    }

    public void onClick(View view) {
        MTSectionsAdapter.lambda$showEmptyStateIfNeeded$0(this.arg$1, view);
    }
}
