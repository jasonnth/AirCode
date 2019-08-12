package com.airbnb.p027n2.primitives.imaging;

/* renamed from: com.airbnb.n2.primitives.imaging.AirImageView$$Lambda$2 */
final /* synthetic */ class AirImageView$$Lambda$2 implements Runnable {
    private final AirImageView arg$1;

    private AirImageView$$Lambda$2(AirImageView airImageView) {
        this.arg$1 = airImageView;
    }

    public static Runnable lambdaFactory$(AirImageView airImageView) {
        return new AirImageView$$Lambda$2(airImageView);
    }

    public void run() {
        AirImageView.lambda$new$0(this.arg$1);
    }
}
