package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

/* renamed from: com.airbnb.n2.components.InfoActionRow$$Lambda$4 */
final /* synthetic */ class InfoActionRow$$Lambda$4 implements OnClickListener {
    private static final InfoActionRow$$Lambda$4 instance = new InfoActionRow$$Lambda$4();

    private InfoActionRow$$Lambda$4() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Row clicked", 1).show();
    }
}
