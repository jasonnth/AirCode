package com.appboy.p028ui.inappmessage.listeners;

import android.os.Bundle;
import com.appboy.models.IInAppMessage;

/* renamed from: com.appboy.ui.inappmessage.listeners.AppboyDefaultHtmlInAppMessageActionListener */
public class AppboyDefaultHtmlInAppMessageActionListener implements IHtmlInAppMessageActionListener {
    public void onCloseClicked(IInAppMessage inAppMessage, String url, Bundle queryBundle) {
    }

    public boolean onNewsfeedClicked(IInAppMessage inAppMessage, String url, Bundle queryBundle) {
        return false;
    }

    public boolean onCustomEventFired(IInAppMessage inAppMessage, String url, Bundle queryBundle) {
        return false;
    }

    public boolean onOtherUrlAction(IInAppMessage inAppMessage, String url, Bundle queryBundle) {
        return false;
    }
}
