package com.airbnb.p027n2.components.photorearranger;

/* renamed from: com.airbnb.n2.components.photorearranger.PhotoRearrangerController$$Lambda$1 */
final /* synthetic */ class PhotoRearrangerController$$Lambda$1 implements AnimationListener {
    private final PhotoRearrangerController arg$1;

    private PhotoRearrangerController$$Lambda$1(PhotoRearrangerController photoRearrangerController) {
        this.arg$1 = photoRearrangerController;
    }

    public static AnimationListener lambdaFactory$(PhotoRearrangerController photoRearrangerController) {
        return new PhotoRearrangerController$$Lambda$1(photoRearrangerController);
    }

    public void valueUpdated(Float f) {
        this.arg$1.updateView(f);
    }
}
