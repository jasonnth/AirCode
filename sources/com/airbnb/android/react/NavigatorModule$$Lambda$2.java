package com.airbnb.android.react;

final /* synthetic */ class NavigatorModule$$Lambda$2 implements ToolbarModifier {
    private final ReactInterface arg$1;
    private final String arg$2;

    private NavigatorModule$$Lambda$2(ReactInterface reactInterface, String str) {
        this.arg$1 = reactInterface;
        this.arg$2 = str;
    }

    public static ToolbarModifier lambdaFactory$(ReactInterface reactInterface, String str) {
        return new NavigatorModule$$Lambda$2(reactInterface, str);
    }

    public void call(Object obj) {
        this.arg$1.setLink(this.arg$2);
    }
}
