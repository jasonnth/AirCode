package com.airbnb.android.react;

import com.airbnb.p027n2.components.AirToolbar;

final /* synthetic */ class NavigatorModule$$Lambda$1 implements ToolbarModifier {
    private final String arg$1;

    private NavigatorModule$$Lambda$1(String str) {
        this.arg$1 = str;
    }

    public static ToolbarModifier lambdaFactory$(String str) {
        return new NavigatorModule$$Lambda$1(str);
    }

    public void call(Object obj) {
        ((AirToolbar) obj).setTitle((CharSequence) this.arg$1);
    }
}
