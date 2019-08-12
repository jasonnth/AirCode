package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

/* renamed from: com.airbnb.n2.components.InviteRow$$Lambda$2 */
final /* synthetic */ class InviteRow$$Lambda$2 implements OnClickListener {
    private static final InviteRow$$Lambda$2 instance = new InviteRow$$Lambda$2();

    private InviteRow$$Lambda$2() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Button clicked", 1).show();
    }
}
