package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.n2.components.NestedListingToggleRow$$Lambda$1 */
final /* synthetic */ class NestedListingToggleRow$$Lambda$1 implements OnClickListener {
    private final NestedListingToggleRow arg$1;

    private NestedListingToggleRow$$Lambda$1(NestedListingToggleRow nestedListingToggleRow) {
        this.arg$1 = nestedListingToggleRow;
    }

    public static OnClickListener lambdaFactory$(NestedListingToggleRow nestedListingToggleRow) {
        return new NestedListingToggleRow$$Lambda$1(nestedListingToggleRow);
    }

    public void onClick(View view) {
        this.arg$1.onClick();
    }
}
