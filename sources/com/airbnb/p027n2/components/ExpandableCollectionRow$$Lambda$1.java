package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.n2.components.ExpandableCollectionRow$$Lambda$1 */
final /* synthetic */ class ExpandableCollectionRow$$Lambda$1 implements OnClickListener {
    private final ExpandableCollectionRow arg$1;

    private ExpandableCollectionRow$$Lambda$1(ExpandableCollectionRow expandableCollectionRow) {
        this.arg$1 = expandableCollectionRow;
    }

    public static OnClickListener lambdaFactory$(ExpandableCollectionRow expandableCollectionRow) {
        return new ExpandableCollectionRow$$Lambda$1(expandableCollectionRow);
    }

    public void onClick(View view) {
        ExpandableCollectionRow.lambda$init$0(this.arg$1, view);
    }
}
