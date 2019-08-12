package com.airbnb.p027n2.browser;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.n2.browser.DLSComponentCategoryListFragment$ViewHolder$$Lambda$1 */
final /* synthetic */ class DLSComponentCategoryListFragment$ViewHolder$$Lambda$1 implements OnClickListener {
    private final Item arg$1;

    private DLSComponentCategoryListFragment$ViewHolder$$Lambda$1(Item item) {
        this.arg$1 = item;
    }

    public static OnClickListener lambdaFactory$(Item item) {
        return new DLSComponentCategoryListFragment$ViewHolder$$Lambda$1(item);
    }

    public void onClick(View view) {
        this.arg$1.onClick();
    }
}
