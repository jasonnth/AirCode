package com.airbnb.p027n2.browser;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.p027n2.components.DLSComponent;

/* renamed from: com.airbnb.n2.browser.DLSComponentListFragment$ViewHolder$$Lambda$1 */
final /* synthetic */ class DLSComponentListFragment$ViewHolder$$Lambda$1 implements OnClickListener {
    private final ViewHolder arg$1;
    private final DLSComponent arg$2;

    private DLSComponentListFragment$ViewHolder$$Lambda$1(ViewHolder viewHolder, DLSComponent dLSComponent) {
        this.arg$1 = viewHolder;
        this.arg$2 = dLSComponent;
    }

    public static OnClickListener lambdaFactory$(ViewHolder viewHolder, DLSComponent dLSComponent) {
        return new DLSComponentListFragment$ViewHolder$$Lambda$1(viewHolder, dLSComponent);
    }

    public void onClick(View view) {
        DLSComponentListFragment.this.onComponentClick(this.arg$2);
    }
}
