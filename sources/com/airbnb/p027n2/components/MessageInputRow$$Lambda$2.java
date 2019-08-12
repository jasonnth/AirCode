package com.airbnb.p027n2.components;

import android.view.View;

/* renamed from: com.airbnb.n2.components.MessageInputRow$$Lambda$2 */
final /* synthetic */ class MessageInputRow$$Lambda$2 implements Runnable {
    private final MessageInputRow arg$1;
    private final View arg$2;

    private MessageInputRow$$Lambda$2(MessageInputRow messageInputRow, View view) {
        this.arg$1 = messageInputRow;
        this.arg$2 = view;
    }

    public static Runnable lambdaFactory$(MessageInputRow messageInputRow, View view) {
        return new MessageInputRow$$Lambda$2(messageInputRow, view);
    }

    public void run() {
        MessageInputRow.lambda$null$0(this.arg$1, this.arg$2);
    }
}
