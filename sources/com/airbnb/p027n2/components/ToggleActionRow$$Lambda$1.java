package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.n2.components.ToggleActionRow$$Lambda$1 */
final /* synthetic */ class ToggleActionRow$$Lambda$1 implements OnClickListener {
    private final ToggleActionRow arg$1;

    private ToggleActionRow$$Lambda$1(ToggleActionRow toggleActionRow) {
        this.arg$1 = toggleActionRow;
    }

    public static OnClickListener lambdaFactory$(ToggleActionRow toggleActionRow) {
        return new ToggleActionRow$$Lambda$1(toggleActionRow);
    }

    public void onClick(View view) {
        this.arg$1.onClick();
    }
}
