package com.airbnb.android.lib.fragments.inbox;

import com.airbnb.android.superhero.SuperHeroMessage;
import p032rx.functions.Action1;

final /* synthetic */ class InboxFragment$$Lambda$14 implements Action1 {
    private final InboxFragment arg$1;

    private InboxFragment$$Lambda$14(InboxFragment inboxFragment) {
        this.arg$1 = inboxFragment;
    }

    public static Action1 lambdaFactory$(InboxFragment inboxFragment) {
        return new InboxFragment$$Lambda$14(inboxFragment);
    }

    public void call(Object obj) {
        this.arg$1.setSuperHeroPreview((SuperHeroMessage) obj);
    }
}
