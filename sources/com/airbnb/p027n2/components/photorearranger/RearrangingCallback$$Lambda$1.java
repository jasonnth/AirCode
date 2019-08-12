package com.airbnb.p027n2.components.photorearranger;

/* renamed from: com.airbnb.n2.components.photorearranger.RearrangingCallback$$Lambda$1 */
final /* synthetic */ class RearrangingCallback$$Lambda$1 implements ViewHolderOperator {
    private static final RearrangingCallback$$Lambda$1 instance = new RearrangingCallback$$Lambda$1();

    private RearrangingCallback$$Lambda$1() {
    }

    public static ViewHolderOperator lambdaFactory$() {
        return instance;
    }

    /* renamed from: op */
    public void mo26162op(PhotoRearrangerViewHolder photoRearrangerViewHolder) {
        photoRearrangerViewHolder.updatePositionViews();
    }
}
