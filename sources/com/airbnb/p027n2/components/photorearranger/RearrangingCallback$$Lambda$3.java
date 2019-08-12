package com.airbnb.p027n2.components.photorearranger;

/* renamed from: com.airbnb.n2.components.photorearranger.RearrangingCallback$$Lambda$3 */
final /* synthetic */ class RearrangingCallback$$Lambda$3 implements ViewHolderOperator {
    private static final RearrangingCallback$$Lambda$3 instance = new RearrangingCallback$$Lambda$3();

    private RearrangingCallback$$Lambda$3() {
    }

    public static ViewHolderOperator lambdaFactory$() {
        return instance;
    }

    /* renamed from: op */
    public void mo26162op(PhotoRearrangerViewHolder photoRearrangerViewHolder) {
        photoRearrangerViewHolder.onItemUnselected();
    }
}
