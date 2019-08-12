package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.n2.components.PosterCard$$Lambda$1 */
final /* synthetic */ class PosterCard$$Lambda$1 implements OnClickListener {
    private final PosterCard arg$1;
    private final OnClickListener arg$2;

    private PosterCard$$Lambda$1(PosterCard posterCard, OnClickListener onClickListener) {
        this.arg$1 = posterCard;
        this.arg$2 = onClickListener;
    }

    public static OnClickListener lambdaFactory$(PosterCard posterCard, OnClickListener onClickListener) {
        return new PosterCard$$Lambda$1(posterCard, onClickListener);
    }

    public void onClick(View view) {
        this.arg$2.onClick(this.arg$1);
    }
}
