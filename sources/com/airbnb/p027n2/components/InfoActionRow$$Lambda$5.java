package com.airbnb.p027n2.components;

import android.view.View;
import android.widget.Toast;
import com.airbnb.p027n2.utils.AirTextBuilder.OnLinkClickListener;

/* renamed from: com.airbnb.n2.components.InfoActionRow$$Lambda$5 */
final /* synthetic */ class InfoActionRow$$Lambda$5 implements OnLinkClickListener {
    private static final InfoActionRow$$Lambda$5 instance = new InfoActionRow$$Lambda$5();

    private InfoActionRow$$Lambda$5() {
    }

    public static OnLinkClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(View view, CharSequence charSequence) {
        Toast.makeText(view.getContext(), "Link clicked", 1).show();
    }
}
