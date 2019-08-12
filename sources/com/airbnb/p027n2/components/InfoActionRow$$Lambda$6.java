package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

/* renamed from: com.airbnb.n2.components.InfoActionRow$$Lambda$6 */
final /* synthetic */ class InfoActionRow$$Lambda$6 implements OnClickListener {
    private static final InfoActionRow$$Lambda$6 instance = new InfoActionRow$$Lambda$6();

    private InfoActionRow$$Lambda$6() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Row clicked", 1).show();
    }
}
