package com.airbnb.p027n2.components.jellyfish;

/* renamed from: com.airbnb.n2.components.jellyfish.JellyfishView$$Lambda$1 */
final /* synthetic */ class JellyfishView$$Lambda$1 implements Runnable {
    private final JellyfishView arg$1;

    private JellyfishView$$Lambda$1(JellyfishView jellyfishView) {
        this.arg$1 = jellyfishView;
    }

    public static Runnable lambdaFactory$(JellyfishView jellyfishView) {
        return new JellyfishView$$Lambda$1(jellyfishView);
    }

    public void run() {
        JellyfishView.lambda$new$0(this.arg$1);
    }
}
