package com.airbnb.android.p011p3;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

/* renamed from: com.airbnb.android.p3.P3ReviewSearchFragment$$Lambda$8 */
final /* synthetic */ class P3ReviewSearchFragment$$Lambda$8 implements OnEditorActionListener {
    private final P3ReviewSearchFragment arg$1;

    private P3ReviewSearchFragment$$Lambda$8(P3ReviewSearchFragment p3ReviewSearchFragment) {
        this.arg$1 = p3ReviewSearchFragment;
    }

    public static OnEditorActionListener lambdaFactory$(P3ReviewSearchFragment p3ReviewSearchFragment) {
        return new P3ReviewSearchFragment$$Lambda$8(p3ReviewSearchFragment);
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return P3ReviewSearchFragment.lambda$setupInputMarquee$7(this.arg$1, textView, i, keyEvent);
    }
}
