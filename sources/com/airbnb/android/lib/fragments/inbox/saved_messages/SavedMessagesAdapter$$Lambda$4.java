package com.airbnb.android.lib.fragments.inbox.saved_messages;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.TemplateMessage;

final /* synthetic */ class SavedMessagesAdapter$$Lambda$4 implements OnClickListener {
    private final SavedMessagesAdapter arg$1;
    private final TemplateMessage arg$2;

    private SavedMessagesAdapter$$Lambda$4(SavedMessagesAdapter savedMessagesAdapter, TemplateMessage templateMessage) {
        this.arg$1 = savedMessagesAdapter;
        this.arg$2 = templateMessage;
    }

    public static OnClickListener lambdaFactory$(SavedMessagesAdapter savedMessagesAdapter, TemplateMessage templateMessage) {
        return new SavedMessagesAdapter$$Lambda$4(savedMessagesAdapter, templateMessage);
    }

    public void onClick(View view) {
        this.arg$1.listener.onMessageEdit(this.arg$2);
    }
}
