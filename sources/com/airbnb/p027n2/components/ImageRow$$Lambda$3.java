package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

/* renamed from: com.airbnb.n2.components.ImageRow$$Lambda$3 */
final /* synthetic */ class ImageRow$$Lambda$3 implements OnClickListener {
    private static final ImageRow$$Lambda$3 instance = new ImageRow$$Lambda$3();

    private ImageRow$$Lambda$3() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Row clicked", 1).show();
    }
}
