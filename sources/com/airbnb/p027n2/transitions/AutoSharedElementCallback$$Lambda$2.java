package com.airbnb.p027n2.transitions;

/* renamed from: com.airbnb.n2.transitions.AutoSharedElementCallback$$Lambda$2 */
final /* synthetic */ class AutoSharedElementCallback$$Lambda$2 implements Runnable {
    private final AutoSharedElementCallback arg$1;

    private AutoSharedElementCallback$$Lambda$2(AutoSharedElementCallback autoSharedElementCallback) {
        this.arg$1 = autoSharedElementCallback;
    }

    public static Runnable lambdaFactory$(AutoSharedElementCallback autoSharedElementCallback) {
        return new AutoSharedElementCallback$$Lambda$2(autoSharedElementCallback);
    }

    public void run() {
        AutoSharedElementCallback.lambda$new$0(this.arg$1);
    }
}
