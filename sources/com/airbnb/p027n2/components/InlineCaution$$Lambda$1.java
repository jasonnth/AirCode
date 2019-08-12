package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

/* renamed from: com.airbnb.n2.components.InlineCaution$$Lambda$1 */
final /* synthetic */ class InlineCaution$$Lambda$1 implements OnClickListener {
    private final InlineCaution arg$1;

    private InlineCaution$$Lambda$1(InlineCaution inlineCaution) {
        this.arg$1 = inlineCaution;
    }

    public static OnClickListener lambdaFactory$(InlineCaution inlineCaution) {
        return new InlineCaution$$Lambda$1(inlineCaution);
    }

    public void onClick(View view) {
        Toast.makeText(this.arg$1.getContext(), "action", 0).show();
    }
}
