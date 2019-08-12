package com.appboy.p028ui.inappmessage.listeners;

import com.appboy.models.IInAppMessage;
import com.appboy.models.MessageButton;
import com.appboy.p028ui.inappmessage.InAppMessageCloser;
import com.appboy.p028ui.inappmessage.InAppMessageOperation;

/* renamed from: com.appboy.ui.inappmessage.listeners.IInAppMessageManagerListener */
public interface IInAppMessageManagerListener {
    InAppMessageOperation beforeInAppMessageDisplayed(IInAppMessage iInAppMessage);

    boolean onInAppMessageButtonClicked(MessageButton messageButton, InAppMessageCloser inAppMessageCloser);

    boolean onInAppMessageClicked(IInAppMessage iInAppMessage, InAppMessageCloser inAppMessageCloser);

    void onInAppMessageDismissed(IInAppMessage iInAppMessage);

    @Deprecated
    boolean onInAppMessageReceived(IInAppMessage iInAppMessage);
}
