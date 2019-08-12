package com.airbnb.android.p011p3;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.ReviewKeyword;

/* renamed from: com.airbnb.android.p3.P3ReviewSearchController$$Lambda$1 */
final /* synthetic */ class P3ReviewSearchController$$Lambda$1 implements OnClickListener {
    private final P3ReviewSearchController arg$1;
    private final ReviewKeyword arg$2;

    private P3ReviewSearchController$$Lambda$1(P3ReviewSearchController p3ReviewSearchController, ReviewKeyword reviewKeyword) {
        this.arg$1 = p3ReviewSearchController;
        this.arg$2 = reviewKeyword;
    }

    public static OnClickListener lambdaFactory$(P3ReviewSearchController p3ReviewSearchController, ReviewKeyword reviewKeyword) {
        return new P3ReviewSearchController$$Lambda$1(p3ReviewSearchController, reviewKeyword);
    }

    public void onClick(View view) {
        this.arg$1.listener.onClickKeyword(this.arg$2);
    }
}
