package com.airbnb.android.react;

import java.util.List;

final /* synthetic */ class NavigatorModule$$Lambda$5 implements ToolbarModifier {
    private final ReactInterface arg$1;
    private final List arg$2;

    private NavigatorModule$$Lambda$5(ReactInterface reactInterface, List list) {
        this.arg$1 = reactInterface;
        this.arg$2 = list;
    }

    public static ToolbarModifier lambdaFactory$(ReactInterface reactInterface, List list) {
        return new NavigatorModule$$Lambda$5(reactInterface, list);
    }

    public void call(Object obj) {
        this.arg$1.setMenuButtons(this.arg$2);
    }
}
