package com.airbnb.p027n2.components;

import android.view.View;
import android.widget.Toast;
import com.airbnb.p027n2.utils.AirTextBuilder.OnLinkClickListener;

/* renamed from: com.airbnb.n2.components.ImageRow$$Lambda$2 */
final /* synthetic */ class ImageRow$$Lambda$2 implements OnLinkClickListener {
    private static final ImageRow$$Lambda$2 instance = new ImageRow$$Lambda$2();

    private ImageRow$$Lambda$2() {
    }

    public static OnLinkClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(View view, CharSequence charSequence) {
        Toast.makeText(view.getContext(), "Link clicked", 1).show();
    }
}
