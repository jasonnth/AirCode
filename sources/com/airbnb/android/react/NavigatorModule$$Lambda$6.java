package com.airbnb.android.react;

import com.airbnb.p027n2.components.AirToolbar;

final /* synthetic */ class NavigatorModule$$Lambda$6 implements ToolbarModifier {
    private final ReactInterface arg$1;
    private final int arg$2;
    private final int arg$3;

    private NavigatorModule$$Lambda$6(ReactInterface reactInterface, int i, int i2) {
        this.arg$1 = reactInterface;
        this.arg$2 = i;
        this.arg$3 = i2;
    }

    public static ToolbarModifier lambdaFactory$(ReactInterface reactInterface, int i, int i2) {
        return new NavigatorModule$$Lambda$6(reactInterface, i, i2);
    }

    public void call(Object obj) {
        NavigatorModule.lambda$setBackgroundColor$4(this.arg$1, this.arg$2, this.arg$3, (AirToolbar) obj);
    }
}
