package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

/* renamed from: com.airbnb.n2.components.IconRow$$Lambda$4 */
final /* synthetic */ class IconRow$$Lambda$4 implements OnClickListener {
    private static final IconRow$$Lambda$4 instance = new IconRow$$Lambda$4();

    private IconRow$$Lambda$4() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Row clicked", 1).show();
    }
}
