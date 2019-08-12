package com.airbnb.android.lib.views;

import android.support.p000v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class SuperhostView$$Lambda$1 implements OnClickListener {
    private final SuperhostView arg$1;
    private final FragmentManager arg$2;
    private final String arg$3;

    private SuperhostView$$Lambda$1(SuperhostView superhostView, FragmentManager fragmentManager, String str) {
        this.arg$1 = superhostView;
        this.arg$2 = fragmentManager;
        this.arg$3 = str;
    }

    public static OnClickListener lambdaFactory$(SuperhostView superhostView, FragmentManager fragmentManager, String str) {
        return new SuperhostView$$Lambda$1(superhostView, fragmentManager, str);
    }

    public void onClick(View view) {
        this.arg$1.showSuperhostTooltip(this.arg$2, this.arg$3);
    }
}
