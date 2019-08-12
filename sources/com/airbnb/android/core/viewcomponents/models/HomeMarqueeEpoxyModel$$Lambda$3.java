package com.airbnb.android.core.viewcomponents.models;

import android.view.View;
import android.view.View.OnLongClickListener;
import com.airbnb.p027n2.components.HomeMarquee;

final /* synthetic */ class HomeMarqueeEpoxyModel$$Lambda$3 implements OnLongClickListener {
    private final HomeMarqueeEpoxyModel arg$1;
    private final HomeMarquee arg$2;

    private HomeMarqueeEpoxyModel$$Lambda$3(HomeMarqueeEpoxyModel homeMarqueeEpoxyModel, HomeMarquee homeMarquee) {
        this.arg$1 = homeMarqueeEpoxyModel;
        this.arg$2 = homeMarquee;
    }

    public static OnLongClickListener lambdaFactory$(HomeMarqueeEpoxyModel homeMarqueeEpoxyModel, HomeMarquee homeMarquee) {
        return new HomeMarqueeEpoxyModel$$Lambda$3(homeMarqueeEpoxyModel, homeMarquee);
    }

    public boolean onLongClick(View view) {
        return HomeMarqueeEpoxyModel.lambda$bind$1(this.arg$1, this.arg$2, view);
    }
}
