package com.airbnb.p027n2.components.photorearranger;

/* renamed from: com.airbnb.n2.components.photorearranger.RearrangablePhotoRow$$Lambda$2 */
final /* synthetic */ class RearrangablePhotoRow$$Lambda$2 implements AnimationListener {
    private final RearrangablePhotoRow arg$1;

    private RearrangablePhotoRow$$Lambda$2(RearrangablePhotoRow rearrangablePhotoRow) {
        this.arg$1 = rearrangablePhotoRow;
    }

    public static AnimationListener lambdaFactory$(RearrangablePhotoRow rearrangablePhotoRow) {
        return new RearrangablePhotoRow$$Lambda$2(rearrangablePhotoRow);
    }

    public void valueUpdated(Float f) {
        this.arg$1.setLabelAlpha(f.floatValue());
    }
}
