package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

/* renamed from: com.airbnb.n2.components.MicroSectionHeader$$Lambda$4 */
final /* synthetic */ class MicroSectionHeader$$Lambda$4 implements OnClickListener {
    private final MicroSectionHeader arg$1;

    private MicroSectionHeader$$Lambda$4(MicroSectionHeader microSectionHeader) {
        this.arg$1 = microSectionHeader;
    }

    public static OnClickListener lambdaFactory$(MicroSectionHeader microSectionHeader) {
        return new MicroSectionHeader$$Lambda$4(microSectionHeader);
    }

    public void onClick(View view) {
        Toast.makeText(this.arg$1.getContext(), "Button clicked", 1).show();
    }
}
