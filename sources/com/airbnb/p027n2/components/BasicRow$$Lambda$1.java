package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

/* renamed from: com.airbnb.n2.components.BasicRow$$Lambda$1 */
final /* synthetic */ class BasicRow$$Lambda$1 implements OnClickListener {
    private static final BasicRow$$Lambda$1 instance = new BasicRow$$Lambda$1();

    private BasicRow$$Lambda$1() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Row clicked", 1).show();
    }
}
