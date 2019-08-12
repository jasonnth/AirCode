package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.n2.components.MessageInputOneRow$$Lambda$1 */
final /* synthetic */ class MessageInputOneRow$$Lambda$1 implements OnClickListener {
    private final MessageInputOneRow arg$1;

    private MessageInputOneRow$$Lambda$1(MessageInputOneRow messageInputOneRow) {
        this.arg$1 = messageInputOneRow;
    }

    public static OnClickListener lambdaFactory$(MessageInputOneRow messageInputOneRow) {
        return new MessageInputOneRow$$Lambda$1(messageInputOneRow);
    }

    public void onClick(View view) {
        this.arg$1.messageInputListener.onCombinationCameraIconClicked();
    }
}
