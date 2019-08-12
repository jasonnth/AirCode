package com.airbnb.android.react;

import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;

final /* synthetic */ class NavigatorModule$$Lambda$11 implements OnMenuItemClickListener {
    private final OnMenuButtonClickListener arg$1;
    private final MenuButton arg$2;
    private final int arg$3;

    private NavigatorModule$$Lambda$11(OnMenuButtonClickListener onMenuButtonClickListener, MenuButton menuButton, int i) {
        this.arg$1 = onMenuButtonClickListener;
        this.arg$2 = menuButton;
        this.arg$3 = i;
    }

    public static OnMenuItemClickListener lambdaFactory$(OnMenuButtonClickListener onMenuButtonClickListener, MenuButton menuButton, int i) {
        return new NavigatorModule$$Lambda$11(onMenuButtonClickListener, menuButton, i);
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        return this.arg$1.onClick(this.arg$2, this.arg$3);
    }
}
