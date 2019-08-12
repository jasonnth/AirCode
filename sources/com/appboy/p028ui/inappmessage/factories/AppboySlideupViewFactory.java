package com.appboy.p028ui.inappmessage.factories;

import android.app.Activity;
import com.appboy.models.IInAppMessage;
import com.appboy.models.InAppMessageSlideup;
import com.appboy.p028ui.inappmessage.IInAppMessageViewFactory;
import com.appboy.p028ui.inappmessage.views.AppboyInAppMessageSlideupView;
import com.appboy.p028ui.support.FrescoLibraryUtils;
import com.appboy.ui.R;

/* renamed from: com.appboy.ui.inappmessage.factories.AppboySlideupViewFactory */
public class AppboySlideupViewFactory implements IInAppMessageViewFactory {
    public AppboyInAppMessageSlideupView createInAppMessageView(Activity activity, IInAppMessage inAppMessage) {
        InAppMessageSlideup inAppMessageSlideup = (InAppMessageSlideup) inAppMessage;
        AppboyInAppMessageSlideupView view = (AppboyInAppMessageSlideupView) activity.getLayoutInflater().inflate(R.layout.com_appboy_inappmessage_slideup, null);
        view.inflateStubViews(inAppMessage);
        if (FrescoLibraryUtils.canUseFresco(activity.getApplicationContext())) {
            view.setMessageSimpleDrawee(inAppMessage);
        } else {
            view.setMessageImageView(inAppMessageSlideup.getBitmap());
        }
        view.setMessageBackgroundColor(inAppMessageSlideup.getBackgroundColor());
        view.setMessage(inAppMessageSlideup.getMessage());
        view.setMessageTextColor(inAppMessageSlideup.getMessageTextColor());
        view.setMessageTextAlign(inAppMessageSlideup.getMessageTextAlign());
        view.setMessageIcon(inAppMessageSlideup.getIcon(), inAppMessageSlideup.getIconColor(), inAppMessageSlideup.getIconBackgroundColor());
        view.setMessageChevron(inAppMessageSlideup.getChevronColor(), inAppMessageSlideup.getClickAction());
        view.resetMessageMargins(inAppMessage.getImageDownloadSuccessful());
        return view;
    }
}
