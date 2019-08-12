package com.airbnb.p027n2.transitions;

/* renamed from: com.airbnb.n2.transitions.AutoSharedElementCallback$$Lambda$1 */
final /* synthetic */ class AutoSharedElementCallback$$Lambda$1 implements Runnable {
    private final AutoSharedElementCallback arg$1;

    private AutoSharedElementCallback$$Lambda$1(AutoSharedElementCallback autoSharedElementCallback) {
        this.arg$1 = autoSharedElementCallback;
    }

    public static Runnable lambdaFactory$(AutoSharedElementCallback autoSharedElementCallback) {
        return new AutoSharedElementCallback$$Lambda$1(autoSharedElementCallback);
    }

    public void run() {
        this.arg$1.startPostponedTransitionsIfReady();
    }
}
