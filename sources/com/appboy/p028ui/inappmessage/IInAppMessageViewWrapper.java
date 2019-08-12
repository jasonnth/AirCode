package com.appboy.p028ui.inappmessage;

import android.app.Activity;
import android.view.View;
import com.appboy.models.IInAppMessage;

/* renamed from: com.appboy.ui.inappmessage.IInAppMessageViewWrapper */
public interface IInAppMessageViewWrapper {
    void close();

    IInAppMessage getInAppMessage();

    View getInAppMessageView();

    boolean getIsAnimatingClose();

    void open(Activity activity);
}
