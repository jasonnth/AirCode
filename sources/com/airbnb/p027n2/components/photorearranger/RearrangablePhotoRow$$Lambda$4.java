package com.airbnb.p027n2.components.photorearranger;

/* renamed from: com.airbnb.n2.components.photorearranger.RearrangablePhotoRow$$Lambda$4 */
final /* synthetic */ class RearrangablePhotoRow$$Lambda$4 implements AnimationListener {
    private final RearrangablePhotoRow arg$1;

    private RearrangablePhotoRow$$Lambda$4(RearrangablePhotoRow rearrangablePhotoRow) {
        this.arg$1 = rearrangablePhotoRow;
    }

    public static AnimationListener lambdaFactory$(RearrangablePhotoRow rearrangablePhotoRow) {
        return new RearrangablePhotoRow$$Lambda$4(rearrangablePhotoRow);
    }

    public void valueUpdated(Float f) {
        this.arg$1.setLabelAlpha(f.floatValue());
    }
}
