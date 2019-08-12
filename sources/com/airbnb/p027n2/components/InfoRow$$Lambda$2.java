package com.airbnb.p027n2.components;

import android.view.View;
import android.widget.Toast;
import com.airbnb.p027n2.utils.AirTextBuilder.OnLinkClickListener;

/* renamed from: com.airbnb.n2.components.InfoRow$$Lambda$2 */
final /* synthetic */ class InfoRow$$Lambda$2 implements OnLinkClickListener {
    private static final InfoRow$$Lambda$2 instance = new InfoRow$$Lambda$2();

    private InfoRow$$Lambda$2() {
    }

    public static OnLinkClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(View view, CharSequence charSequence) {
        Toast.makeText(view.getContext(), "Link clicked", 1).show();
    }
}
