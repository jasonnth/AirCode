package com.airbnb.android.lib.fragments.inbox.saved_messages;

import android.view.View;
import android.view.View.OnLongClickListener;
import com.airbnb.android.core.models.TemplateMessage;

final /* synthetic */ class SavedMessagesAdapter$$Lambda$5 implements OnLongClickListener {
    private final SavedMessagesAdapter arg$1;
    private final TemplateMessage arg$2;

    private SavedMessagesAdapter$$Lambda$5(SavedMessagesAdapter savedMessagesAdapter, TemplateMessage templateMessage) {
        this.arg$1 = savedMessagesAdapter;
        this.arg$2 = templateMessage;
    }

    public static OnLongClickListener lambdaFactory$(SavedMessagesAdapter savedMessagesAdapter, TemplateMessage templateMessage) {
        return new SavedMessagesAdapter$$Lambda$5(savedMessagesAdapter, templateMessage);
    }

    public boolean onLongClick(View view) {
        return this.arg$1.listener.onLongClick(this.arg$2.getId());
    }
}
