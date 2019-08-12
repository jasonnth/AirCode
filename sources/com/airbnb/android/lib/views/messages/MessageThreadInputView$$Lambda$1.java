package com.airbnb.android.lib.views.messages;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.p027n2.primitives.messaging.MessageInputListener;

final /* synthetic */ class MessageThreadInputView$$Lambda$1 implements OnClickListener {
    private final MessageInputListener arg$1;

    private MessageThreadInputView$$Lambda$1(MessageInputListener messageInputListener) {
        this.arg$1 = messageInputListener;
    }

    public static OnClickListener lambdaFactory$(MessageInputListener messageInputListener) {
        return new MessageThreadInputView$$Lambda$1(messageInputListener);
    }

    public void onClick(View view) {
        this.arg$1.onSendButtonClicked();
    }
}
