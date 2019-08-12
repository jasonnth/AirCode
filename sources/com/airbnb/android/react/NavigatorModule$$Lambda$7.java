package com.airbnb.android.react;

import com.airbnb.p027n2.components.AirToolbar;

final /* synthetic */ class NavigatorModule$$Lambda$7 implements ToolbarModifier {
    private final int arg$1;

    private NavigatorModule$$Lambda$7(int i) {
        this.arg$1 = i;
    }

    public static ToolbarModifier lambdaFactory$(int i) {
        return new NavigatorModule$$Lambda$7(i);
    }

    public void call(Object obj) {
        ((AirToolbar) obj).setTheme(this.arg$1);
    }
}
