package com.airbnb.p027n2.components.photorearranger;

/* renamed from: com.airbnb.n2.components.photorearranger.RearrangableLabeledPhotoCell$$Lambda$1 */
final /* synthetic */ class RearrangableLabeledPhotoCell$$Lambda$1 implements AnimationListener {
    private final RearrangableLabeledPhotoCell arg$1;

    private RearrangableLabeledPhotoCell$$Lambda$1(RearrangableLabeledPhotoCell rearrangableLabeledPhotoCell) {
        this.arg$1 = rearrangableLabeledPhotoCell;
    }

    public static AnimationListener lambdaFactory$(RearrangableLabeledPhotoCell rearrangableLabeledPhotoCell) {
        return new RearrangableLabeledPhotoCell$$Lambda$1(rearrangableLabeledPhotoCell);
    }

    public void valueUpdated(Float f) {
        this.arg$1.updateAnimation(f.floatValue());
    }
}
