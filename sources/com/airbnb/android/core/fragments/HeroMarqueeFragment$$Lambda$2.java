package com.airbnb.android.core.fragments;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class HeroMarqueeFragment$$Lambda$2 implements OnClickListener {
    private final HeroMarqueeFragment arg$1;
    private final Bundle arg$2;

    private HeroMarqueeFragment$$Lambda$2(HeroMarqueeFragment heroMarqueeFragment, Bundle bundle) {
        this.arg$1 = heroMarqueeFragment;
        this.arg$2 = bundle;
    }

    public static OnClickListener lambdaFactory$(HeroMarqueeFragment heroMarqueeFragment, Bundle bundle) {
        return new HeroMarqueeFragment$$Lambda$2(heroMarqueeFragment, bundle);
    }

    public void onClick(View view) {
        HeroMarqueeFragment.lambda$onCreateView$1(this.arg$1, this.arg$2, view);
    }
}
