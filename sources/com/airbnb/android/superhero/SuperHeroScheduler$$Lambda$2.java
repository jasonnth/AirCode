package com.airbnb.android.superhero;

import p032rx.functions.Action0;

final /* synthetic */ class SuperHeroScheduler$$Lambda$2 implements Action0 {
    private final SuperHeroScheduler arg$1;
    private final SuperHeroMessagesResponse arg$2;

    private SuperHeroScheduler$$Lambda$2(SuperHeroScheduler superHeroScheduler, SuperHeroMessagesResponse superHeroMessagesResponse) {
        this.arg$1 = superHeroScheduler;
        this.arg$2 = superHeroMessagesResponse;
    }

    public static Action0 lambdaFactory$(SuperHeroScheduler superHeroScheduler, SuperHeroMessagesResponse superHeroMessagesResponse) {
        return new SuperHeroScheduler$$Lambda$2(superHeroScheduler, superHeroMessagesResponse);
    }

    public void call() {
        this.arg$1._persistAndScheduleMessages(this.arg$2);
    }
}
