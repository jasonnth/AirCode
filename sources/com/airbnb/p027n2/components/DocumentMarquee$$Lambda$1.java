package com.airbnb.p027n2.components;

import android.view.View;
import android.widget.Toast;
import com.airbnb.p027n2.utils.AirTextBuilder.OnLinkClickListener;

/* renamed from: com.airbnb.n2.components.DocumentMarquee$$Lambda$1 */
final /* synthetic */ class DocumentMarquee$$Lambda$1 implements OnLinkClickListener {
    private static final DocumentMarquee$$Lambda$1 instance = new DocumentMarquee$$Lambda$1();

    private DocumentMarquee$$Lambda$1() {
    }

    public static OnLinkClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(View view, CharSequence charSequence) {
        Toast.makeText(view.getContext(), "Link clicked", 1).show();
    }
}
