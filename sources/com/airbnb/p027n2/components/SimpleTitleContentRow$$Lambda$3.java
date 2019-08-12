package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

/* renamed from: com.airbnb.n2.components.SimpleTitleContentRow$$Lambda$3 */
final /* synthetic */ class SimpleTitleContentRow$$Lambda$3 implements OnClickListener {
    private static final SimpleTitleContentRow$$Lambda$3 instance = new SimpleTitleContentRow$$Lambda$3();

    private SimpleTitleContentRow$$Lambda$3() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Row clicked", 1).show();
    }
}
