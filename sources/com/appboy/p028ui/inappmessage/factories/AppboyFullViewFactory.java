package com.appboy.p028ui.inappmessage.factories;

import android.app.Activity;
import android.widget.RelativeLayout.LayoutParams;
import com.appboy.enums.inappmessage.ImageStyle;
import com.appboy.enums.inappmessage.Orientation;
import com.appboy.models.IInAppMessage;
import com.appboy.models.InAppMessageFull;
import com.appboy.p028ui.inappmessage.IInAppMessageViewFactory;
import com.appboy.p028ui.inappmessage.views.AppboyInAppMessageFullView;
import com.appboy.p028ui.support.FrescoLibraryUtils;
import com.appboy.p028ui.support.ViewUtils;
import com.appboy.ui.R;

/* renamed from: com.appboy.ui.inappmessage.factories.AppboyFullViewFactory */
public class AppboyFullViewFactory implements IInAppMessageViewFactory {
    public AppboyInAppMessageFullView createInAppMessageView(Activity activity, IInAppMessage inAppMessage) {
        InAppMessageFull inAppMessageFull = (InAppMessageFull) inAppMessage;
        boolean isGraphic = inAppMessageFull.getImageStyle().equals(ImageStyle.GRAPHIC);
        AppboyInAppMessageFullView view = getAppropriateFullView(activity, isGraphic);
        view.inflateStubViews(activity, inAppMessageFull);
        if (FrescoLibraryUtils.canUseFresco(activity.getApplicationContext())) {
            view.setMessageSimpleDrawee(inAppMessageFull);
        } else {
            view.setMessageImageView(inAppMessageFull.getBitmap());
        }
        view.getFrameView().setOnClickListener(null);
        view.setMessageBackgroundColor(inAppMessageFull.getBackgroundColor());
        view.setFrameColor(inAppMessageFull.getFrameColor());
        view.setMessageButtons(inAppMessageFull.getMessageButtons());
        view.setMessageCloseButtonColor(inAppMessageFull.getCloseButtonColor());
        if (!isGraphic) {
            view.setMessage(inAppMessageFull.getMessage());
            view.setMessageTextColor(inAppMessageFull.getMessageTextColor());
            view.setMessageHeaderText(inAppMessageFull.getHeader());
            view.setMessageHeaderTextColor(inAppMessageFull.getHeaderTextColor());
            view.setMessageHeaderTextAlignment(inAppMessageFull.getHeaderTextAlign());
            view.setMessageTextAlign(inAppMessageFull.getMessageTextAlign());
            view.resetMessageMargins(inAppMessageFull.getImageDownloadSuccessful());
        }
        resetLayoutParamsIfAppropriate(activity, inAppMessageFull, view);
        return view;
    }

    /* access modifiers changed from: 0000 */
    public boolean resetLayoutParamsIfAppropriate(Activity activity, IInAppMessage inAppMessage, AppboyInAppMessageFullView view) {
        LayoutParams layoutParams;
        if (!ViewUtils.isRunningOnTablet(activity) || inAppMessage.getOrientation() == null || inAppMessage.getOrientation() == Orientation.ANY) {
            return false;
        }
        int longEdge = view.getLongEdge();
        int shortEdge = view.getShortEdge();
        if (longEdge <= 0 || shortEdge <= 0) {
            return false;
        }
        if (inAppMessage.getOrientation() == Orientation.LANDSCAPE) {
            layoutParams = new LayoutParams(longEdge, shortEdge);
        } else {
            layoutParams = new LayoutParams(shortEdge, longEdge);
        }
        layoutParams.addRule(13, -1);
        view.getMessageBackgroundObject().setLayoutParams(layoutParams);
        return true;
    }

    /* access modifiers changed from: 0000 */
    public AppboyInAppMessageFullView getAppropriateFullView(Activity activity, boolean isGraphic) {
        if (isGraphic) {
            return (AppboyInAppMessageFullView) activity.getLayoutInflater().inflate(R.layout.com_appboy_inappmessage_full_graphic, null);
        }
        return (AppboyInAppMessageFullView) activity.getLayoutInflater().inflate(R.layout.com_appboy_inappmessage_full, null);
    }
}
