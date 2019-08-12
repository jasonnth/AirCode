package com.airbnb.android.p011p3;

import com.airbnb.android.core.responses.ReviewKeywordsResponse;
import java.util.ArrayList;
import p032rx.functions.Action1;

/* renamed from: com.airbnb.android.p3.P3ReviewSearchFragment$$Lambda$1 */
final /* synthetic */ class P3ReviewSearchFragment$$Lambda$1 implements Action1 {
    private final P3ReviewSearchFragment arg$1;

    private P3ReviewSearchFragment$$Lambda$1(P3ReviewSearchFragment p3ReviewSearchFragment) {
        this.arg$1 = p3ReviewSearchFragment;
    }

    public static Action1 lambdaFactory$(P3ReviewSearchFragment p3ReviewSearchFragment) {
        return new P3ReviewSearchFragment$$Lambda$1(p3ReviewSearchFragment);
    }

    public void call(Object obj) {
        this.arg$1.keywords = new ArrayList<>(((ReviewKeywordsResponse) obj).keywords);
    }
}
