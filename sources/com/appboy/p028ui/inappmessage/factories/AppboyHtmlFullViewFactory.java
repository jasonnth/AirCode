package com.appboy.p028ui.inappmessage.factories;

import android.app.Activity;
import com.appboy.models.IInAppMessage;
import com.appboy.models.InAppMessageHtmlFull;
import com.appboy.p028ui.inappmessage.IInAppMessageViewFactory;
import com.appboy.p028ui.inappmessage.InAppMessageWebViewClient;
import com.appboy.p028ui.inappmessage.listeners.IInAppMessageWebViewClientListener;
import com.appboy.p028ui.inappmessage.views.AppboyInAppMessageHtmlFullView;
import com.appboy.ui.R;

/* renamed from: com.appboy.ui.inappmessage.factories.AppboyHtmlFullViewFactory */
public class AppboyHtmlFullViewFactory implements IInAppMessageViewFactory {
    private IInAppMessageWebViewClientListener mInAppMessageWebViewClientListener;

    public AppboyHtmlFullViewFactory(IInAppMessageWebViewClientListener inAppMessageWebViewClientListener) {
        this.mInAppMessageWebViewClientListener = inAppMessageWebViewClientListener;
    }

    public AppboyInAppMessageHtmlFullView createInAppMessageView(Activity activity, IInAppMessage inAppMessage) {
        AppboyInAppMessageHtmlFullView view = (AppboyInAppMessageHtmlFullView) activity.getLayoutInflater().inflate(R.layout.com_appboy_inappmessage_html_full, null);
        view.setWebViewContent(inAppMessage.getMessage(), ((InAppMessageHtmlFull) inAppMessage).getLocalAssetsDirectoryUrl());
        view.setInAppMessageWebViewClient(new InAppMessageWebViewClient(inAppMessage, this.mInAppMessageWebViewClientListener));
        return view;
    }
}
