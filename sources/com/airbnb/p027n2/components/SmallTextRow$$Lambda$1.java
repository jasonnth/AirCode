package com.airbnb.p027n2.components;

import android.view.View;
import android.widget.Toast;
import com.airbnb.p027n2.utils.AirTextBuilder.OnLinkClickListener;

/* renamed from: com.airbnb.n2.components.SmallTextRow$$Lambda$1 */
final /* synthetic */ class SmallTextRow$$Lambda$1 implements OnLinkClickListener {
    private static final SmallTextRow$$Lambda$1 instance = new SmallTextRow$$Lambda$1();

    private SmallTextRow$$Lambda$1() {
    }

    public static OnLinkClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(View view, CharSequence charSequence) {
        Toast.makeText(view.getContext(), "Link clicked", 1).show();
    }
}
