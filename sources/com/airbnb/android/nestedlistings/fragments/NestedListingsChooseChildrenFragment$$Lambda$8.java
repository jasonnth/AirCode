package com.airbnb.android.nestedlistings.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class NestedListingsChooseChildrenFragment$$Lambda$8 implements OnClickListener {
    private final NestedListingsChooseChildrenFragment arg$1;

    private NestedListingsChooseChildrenFragment$$Lambda$8(NestedListingsChooseChildrenFragment nestedListingsChooseChildrenFragment) {
        this.arg$1 = nestedListingsChooseChildrenFragment;
    }

    public static OnClickListener lambdaFactory$(NestedListingsChooseChildrenFragment nestedListingsChooseChildrenFragment) {
        return new NestedListingsChooseChildrenFragment$$Lambda$8(nestedListingsChooseChildrenFragment);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.arg$1.onUnsavedChangesDiscarded();
    }
}
