package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.n2.components.FindInlineFiltersToggleRow$$Lambda$1 */
final /* synthetic */ class FindInlineFiltersToggleRow$$Lambda$1 implements OnClickListener {
    private final FindInlineFiltersToggleRow arg$1;

    private FindInlineFiltersToggleRow$$Lambda$1(FindInlineFiltersToggleRow findInlineFiltersToggleRow) {
        this.arg$1 = findInlineFiltersToggleRow;
    }

    public static OnClickListener lambdaFactory$(FindInlineFiltersToggleRow findInlineFiltersToggleRow) {
        return new FindInlineFiltersToggleRow$$Lambda$1(findInlineFiltersToggleRow);
    }

    public void onClick(View view) {
        this.arg$1.toggle();
    }
}
