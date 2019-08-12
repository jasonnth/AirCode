package com.airbnb.android.core.views;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.views.SuperHeroDismissView.SuperHeroDismissInterface;

final /* synthetic */ class SuperHeroDismissView$$Lambda$1 implements OnClickListener {
    private final SuperHeroDismissInterface arg$1;

    private SuperHeroDismissView$$Lambda$1(SuperHeroDismissInterface superHeroDismissInterface) {
        this.arg$1 = superHeroDismissInterface;
    }

    public static OnClickListener lambdaFactory$(SuperHeroDismissInterface superHeroDismissInterface) {
        return new SuperHeroDismissView$$Lambda$1(superHeroDismissInterface);
    }

    public void onClick(View view) {
        this.arg$1.onFlingOrTap();
    }
}
