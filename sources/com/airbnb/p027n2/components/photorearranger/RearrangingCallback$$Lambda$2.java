package com.airbnb.p027n2.components.photorearranger;

/* renamed from: com.airbnb.n2.components.photorearranger.RearrangingCallback$$Lambda$2 */
final /* synthetic */ class RearrangingCallback$$Lambda$2 implements ViewHolderOperator {
    private static final RearrangingCallback$$Lambda$2 instance = new RearrangingCallback$$Lambda$2();

    private RearrangingCallback$$Lambda$2() {
    }

    public static ViewHolderOperator lambdaFactory$() {
        return instance;
    }

    /* renamed from: op */
    public void mo26162op(PhotoRearrangerViewHolder photoRearrangerViewHolder) {
        photoRearrangerViewHolder.onItemSelected();
    }
}
