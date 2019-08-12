package com.appboy.p028ui.inappmessage.listeners;

import android.os.Bundle;
import com.appboy.models.IInAppMessage;

/* renamed from: com.appboy.ui.inappmessage.listeners.IHtmlInAppMessageActionListener */
public interface IHtmlInAppMessageActionListener {
    void onCloseClicked(IInAppMessage iInAppMessage, String str, Bundle bundle);

    boolean onCustomEventFired(IInAppMessage iInAppMessage, String str, Bundle bundle);

    boolean onNewsfeedClicked(IInAppMessage iInAppMessage, String str, Bundle bundle);

    boolean onOtherUrlAction(IInAppMessage iInAppMessage, String str, Bundle bundle);
}
