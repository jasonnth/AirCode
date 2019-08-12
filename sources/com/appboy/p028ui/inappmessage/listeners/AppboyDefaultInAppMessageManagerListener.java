package com.appboy.p028ui.inappmessage.listeners;

import com.appboy.models.IInAppMessage;
import com.appboy.models.MessageButton;
import com.appboy.p028ui.inappmessage.InAppMessageCloser;
import com.appboy.p028ui.inappmessage.InAppMessageOperation;

/* renamed from: com.appboy.ui.inappmessage.listeners.AppboyDefaultInAppMessageManagerListener */
public class AppboyDefaultInAppMessageManagerListener implements IInAppMessageManagerListener {
    @Deprecated
    public boolean onInAppMessageReceived(IInAppMessage inAppMessage) {
        return false;
    }

    public InAppMessageOperation beforeInAppMessageDisplayed(IInAppMessage inAppMessage) {
        return InAppMessageOperation.DISPLAY_NOW;
    }

    public boolean onInAppMessageClicked(IInAppMessage inAppMessage, InAppMessageCloser inAppMessageCloser) {
        return false;
    }

    public boolean onInAppMessageButtonClicked(MessageButton button, InAppMessageCloser inAppMessageCloser) {
        return false;
    }

    public void onInAppMessageDismissed(IInAppMessage inAppMessage) {
    }
}
