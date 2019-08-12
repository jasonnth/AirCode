package com.airbnb.android.p011p3;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.models.UrgencyMessage;

/* renamed from: com.airbnb.android.p3.P3EpoxyController$$Lambda$5 */
final /* synthetic */ class P3EpoxyController$$Lambda$5 implements OnClickListener {
    private final P3EpoxyController arg$1;
    private final UrgencyMessage arg$2;

    private P3EpoxyController$$Lambda$5(P3EpoxyController p3EpoxyController, UrgencyMessage urgencyMessage) {
        this.arg$1 = p3EpoxyController;
        this.arg$2 = urgencyMessage;
    }

    public static OnClickListener lambdaFactory$(P3EpoxyController p3EpoxyController, UrgencyMessage urgencyMessage) {
        return new P3EpoxyController$$Lambda$5(p3EpoxyController, urgencyMessage);
    }

    public void onClick(View view) {
        this.arg$1.controller.showZenDialog(ZenDialog.createSingleButtonDialog(this.arg$2.getHeadline(), this.arg$2.getContextualMessage(), C7532R.string.okay));
    }
}
