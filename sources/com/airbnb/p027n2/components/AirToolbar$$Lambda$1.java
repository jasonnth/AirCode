package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

/* renamed from: com.airbnb.n2.components.AirToolbar$$Lambda$1 */
final /* synthetic */ class AirToolbar$$Lambda$1 implements OnClickListener {
    private static final AirToolbar$$Lambda$1 instance = new AirToolbar$$Lambda$1();

    private AirToolbar$$Lambda$1() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Toolbar clicked", 0).show();
    }
}
