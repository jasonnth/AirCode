package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

/* renamed from: com.airbnb.n2.components.MicroRow$$Lambda$2 */
final /* synthetic */ class MicroRow$$Lambda$2 implements OnClickListener {
    private static final MicroRow$$Lambda$2 instance = new MicroRow$$Lambda$2();

    private MicroRow$$Lambda$2() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Row clicked", 0).show();
    }
}
