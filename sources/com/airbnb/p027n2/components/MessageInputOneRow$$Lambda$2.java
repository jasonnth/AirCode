package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.n2.components.MessageInputOneRow$$Lambda$2 */
final /* synthetic */ class MessageInputOneRow$$Lambda$2 implements OnClickListener {
    private final MessageInputOneRow arg$1;

    private MessageInputOneRow$$Lambda$2(MessageInputOneRow messageInputOneRow) {
        this.arg$1 = messageInputOneRow;
    }

    public static OnClickListener lambdaFactory$(MessageInputOneRow messageInputOneRow) {
        return new MessageInputOneRow$$Lambda$2(messageInputOneRow);
    }

    public void onClick(View view) {
        this.arg$1.messageInputListener.onSavedMessageIconClicked();
    }
}
