package com.airbnb.android.lib.fragments.inbox;

import java.util.concurrent.Callable;

final /* synthetic */ class InboxFragment$$Lambda$13 implements Callable {
    private final InboxFragment arg$1;

    private InboxFragment$$Lambda$13(InboxFragment inboxFragment) {
        this.arg$1 = inboxFragment;
    }

    public static Callable lambdaFactory$(InboxFragment inboxFragment) {
        return new InboxFragment$$Lambda$13(inboxFragment);
    }

    public Object call() {
        return this.arg$1.superHeroTableOpenHelper.messageToPreview();
    }
}
