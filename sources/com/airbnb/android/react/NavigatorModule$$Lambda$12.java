package com.airbnb.android.react;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class NavigatorModule$$Lambda$12 implements OnClickListener {
    private final OnMenuButtonClickListener arg$1;
    private final MenuButton arg$2;
    private final int arg$3;

    private NavigatorModule$$Lambda$12(OnMenuButtonClickListener onMenuButtonClickListener, MenuButton menuButton, int i) {
        this.arg$1 = onMenuButtonClickListener;
        this.arg$2 = menuButton;
        this.arg$3 = i;
    }

    public static OnClickListener lambdaFactory$(OnMenuButtonClickListener onMenuButtonClickListener, MenuButton menuButton, int i) {
        return new NavigatorModule$$Lambda$12(onMenuButtonClickListener, menuButton, i);
    }

    public void onClick(View view) {
        this.arg$1.onClick(this.arg$2, this.arg$3);
    }
}
