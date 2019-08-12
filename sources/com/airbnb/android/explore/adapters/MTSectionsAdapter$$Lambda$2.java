package com.airbnb.android.explore.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class MTSectionsAdapter$$Lambda$2 implements OnClickListener {
    private final MTSectionsAdapter arg$1;
    private final String arg$2;

    private MTSectionsAdapter$$Lambda$2(MTSectionsAdapter mTSectionsAdapter, String str) {
        this.arg$1 = mTSectionsAdapter;
        this.arg$2 = str;
    }

    public static OnClickListener lambdaFactory$(MTSectionsAdapter mTSectionsAdapter, String str) {
        return new MTSectionsAdapter$$Lambda$2(mTSectionsAdapter, str);
    }

    public void onClick(View view) {
        this.arg$1.dataController.fetchTab(this.arg$2);
    }
}
