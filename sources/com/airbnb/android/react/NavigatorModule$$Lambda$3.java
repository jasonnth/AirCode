package com.airbnb.android.react;

import com.airbnb.p027n2.components.AirToolbar;

final /* synthetic */ class NavigatorModule$$Lambda$3 implements ToolbarModifier {
    private final int arg$1;

    private NavigatorModule$$Lambda$3(int i) {
        this.arg$1 = i;
    }

    public static ToolbarModifier lambdaFactory$(int i) {
        return new NavigatorModule$$Lambda$3(i);
    }

    public void call(Object obj) {
        ((AirToolbar) obj).setNavigationIcon(this.arg$1);
    }
}
