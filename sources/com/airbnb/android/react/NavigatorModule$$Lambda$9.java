package com.airbnb.android.react;

final /* synthetic */ class NavigatorModule$$Lambda$9 implements Runnable {
    private final ReactInterface arg$1;
    private final ToolbarModifier arg$2;

    private NavigatorModule$$Lambda$9(ReactInterface reactInterface, ToolbarModifier toolbarModifier) {
        this.arg$1 = reactInterface;
        this.arg$2 = toolbarModifier;
    }

    public static Runnable lambdaFactory$(ReactInterface reactInterface, ToolbarModifier toolbarModifier) {
        return new NavigatorModule$$Lambda$9(reactInterface, toolbarModifier);
    }

    public void run() {
        NavigatorModule.lambda$withToolbar$6(this.arg$1, this.arg$2);
    }
}
