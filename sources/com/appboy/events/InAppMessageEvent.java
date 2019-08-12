package com.appboy.events;

import com.appboy.models.IInAppMessage;

public final class InAppMessageEvent {

    /* renamed from: a */
    private final IInAppMessage f2784a;

    /* renamed from: b */
    private final String f2785b;

    public InAppMessageEvent(IInAppMessage inAppMessage, String userId) {
        this.f2785b = userId;
        if (inAppMessage == null) {
            throw new NullPointerException();
        }
        this.f2784a = inAppMessage;
    }

    public IInAppMessage getInAppMessage() {
        return this.f2784a;
    }
}
