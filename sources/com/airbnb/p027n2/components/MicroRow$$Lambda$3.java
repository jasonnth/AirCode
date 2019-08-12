package com.airbnb.p027n2.components;

import android.view.View;
import android.widget.Toast;
import com.airbnb.p027n2.utils.AirTextBuilder.OnLinkClickListener;

/* renamed from: com.airbnb.n2.components.MicroRow$$Lambda$3 */
final /* synthetic */ class MicroRow$$Lambda$3 implements OnLinkClickListener {
    private static final MicroRow$$Lambda$3 instance = new MicroRow$$Lambda$3();

    private MicroRow$$Lambda$3() {
    }

    public static OnLinkClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(View view, CharSequence charSequence) {
        Toast.makeText(view.getContext(), "Link clicked", 1).show();
    }
}
