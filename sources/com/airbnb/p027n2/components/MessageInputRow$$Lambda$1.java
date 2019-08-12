package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnFocusChangeListener;

/* renamed from: com.airbnb.n2.components.MessageInputRow$$Lambda$1 */
final /* synthetic */ class MessageInputRow$$Lambda$1 implements OnFocusChangeListener {
    private final MessageInputRow arg$1;

    private MessageInputRow$$Lambda$1(MessageInputRow messageInputRow) {
        this.arg$1 = messageInputRow;
    }

    public static OnFocusChangeListener lambdaFactory$(MessageInputRow messageInputRow) {
        return new MessageInputRow$$Lambda$1(messageInputRow);
    }

    public void onFocusChange(View view, boolean z) {
        this.arg$1.input.post(MessageInputRow$$Lambda$2.lambdaFactory$(this.arg$1, view));
    }
}
