package com.appboy.p028ui.inappmessage;

/* renamed from: com.appboy.ui.inappmessage.InAppMessageCloser */
public class InAppMessageCloser {
    private InAppMessageViewWrapper mInAppMessageViewWrapper;

    public InAppMessageCloser(InAppMessageViewWrapper inAppMessageViewWrapper) {
        this.mInAppMessageViewWrapper = inAppMessageViewWrapper;
    }

    public void close(boolean animate) {
        if (animate) {
            this.mInAppMessageViewWrapper.getInAppMessage().setAnimateOut(true);
        } else {
            this.mInAppMessageViewWrapper.getInAppMessage().setAnimateOut(false);
        }
        this.mInAppMessageViewWrapper.close();
    }
}
