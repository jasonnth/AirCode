package com.airbnb.android.lib.fragments.inbox.saved_messages;

import com.airbnb.android.core.responses.DeleteCannedMessageResponse;
import p032rx.functions.Action1;

final /* synthetic */ class SavedMessagesFragment$$Lambda$3 implements Action1 {
    private final SavedMessagesFragment arg$1;

    private SavedMessagesFragment$$Lambda$3(SavedMessagesFragment savedMessagesFragment) {
        this.arg$1 = savedMessagesFragment;
    }

    public static Action1 lambdaFactory$(SavedMessagesFragment savedMessagesFragment) {
        return new SavedMessagesFragment$$Lambda$3(savedMessagesFragment);
    }

    public void call(Object obj) {
        this.arg$1.deleteMessage((DeleteCannedMessageResponse) obj);
    }
}
