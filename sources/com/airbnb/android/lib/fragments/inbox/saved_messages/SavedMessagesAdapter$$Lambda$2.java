package com.airbnb.android.lib.fragments.inbox.saved_messages;

import com.airbnb.android.core.models.TemplateMessage;
import com.google.common.base.Function;

final /* synthetic */ class SavedMessagesAdapter$$Lambda$2 implements Function {
    private final SavedMessagesAdapter arg$1;
    private final boolean arg$2;

    private SavedMessagesAdapter$$Lambda$2(SavedMessagesAdapter savedMessagesAdapter, boolean z) {
        this.arg$1 = savedMessagesAdapter;
        this.arg$2 = z;
    }

    public static Function lambdaFactory$(SavedMessagesAdapter savedMessagesAdapter, boolean z) {
        return new SavedMessagesAdapter$$Lambda$2(savedMessagesAdapter, z);
    }

    public Object apply(Object obj) {
        return this.arg$1.savedMessageToEpoxyModel((TemplateMessage) obj, this.arg$2);
    }
}
