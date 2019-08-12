package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

/* renamed from: com.airbnb.n2.components.SectionHeader$$Lambda$1 */
final /* synthetic */ class SectionHeader$$Lambda$1 implements OnClickListener {
    private final SectionHeader arg$1;

    private SectionHeader$$Lambda$1(SectionHeader sectionHeader) {
        this.arg$1 = sectionHeader;
    }

    public static OnClickListener lambdaFactory$(SectionHeader sectionHeader) {
        return new SectionHeader$$Lambda$1(sectionHeader);
    }

    public void onClick(View view) {
        Toast.makeText(this.arg$1.getContext(), "Button clicked", 1).show();
    }
}
