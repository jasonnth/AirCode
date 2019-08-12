package com.appboy.p028ui.inappmessage.factories;

import android.app.Activity;
import com.appboy.enums.inappmessage.ImageStyle;
import com.appboy.models.IInAppMessage;
import com.appboy.models.InAppMessageModal;
import com.appboy.p028ui.inappmessage.IInAppMessageViewFactory;
import com.appboy.p028ui.inappmessage.views.AppboyInAppMessageModalView;
import com.appboy.p028ui.support.FrescoLibraryUtils;
import com.appboy.ui.R;

/* renamed from: com.appboy.ui.inappmessage.factories.AppboyModalViewFactory */
public class AppboyModalViewFactory implements IInAppMessageViewFactory {
    public AppboyInAppMessageModalView createInAppMessageView(Activity activity, IInAppMessage inAppMessage) {
        InAppMessageModal inAppMessageModal = (InAppMessageModal) inAppMessage;
        boolean isGraphic = inAppMessageModal.getImageStyle().equals(ImageStyle.GRAPHIC);
        AppboyInAppMessageModalView view = getAppropriateModalView(activity, isGraphic);
        view.inflateStubViews(activity, inAppMessageModal);
        if (FrescoLibraryUtils.canUseFresco(activity.getApplicationContext())) {
            view.setMessageSimpleDrawee(inAppMessageModal, activity);
        } else {
            view.setMessageImageView(inAppMessageModal.getBitmap());
        }
        view.getFrameView().setOnClickListener(null);
        view.setMessageBackgroundColor(inAppMessage.getBackgroundColor());
        view.setFrameColor(inAppMessageModal.getFrameColor());
        view.setMessageButtons(inAppMessageModal.getMessageButtons());
        view.setMessageCloseButtonColor(inAppMessageModal.getCloseButtonColor());
        if (!isGraphic) {
            view.setMessage(inAppMessage.getMessage());
            view.setMessageTextColor(inAppMessage.getMessageTextColor());
            view.setMessageHeaderText(inAppMessageModal.getHeader());
            view.setMessageHeaderTextColor(inAppMessageModal.getHeaderTextColor());
            view.setMessageIcon(inAppMessage.getIcon(), inAppMessage.getIconColor(), inAppMessage.getIconBackgroundColor());
            view.setMessageHeaderTextAlignment(inAppMessageModal.getHeaderTextAlign());
            view.setMessageTextAlign(inAppMessageModal.getMessageTextAlign());
            view.resetMessageMargins(inAppMessage.getImageDownloadSuccessful());
        }
        return view;
    }

    /* access modifiers changed from: 0000 */
    public AppboyInAppMessageModalView getAppropriateModalView(Activity activity, boolean isGraphic) {
        if (isGraphic) {
            return (AppboyInAppMessageModalView) activity.getLayoutInflater().inflate(R.layout.com_appboy_inappmessage_modal_graphic, null);
        }
        return (AppboyInAppMessageModalView) activity.getLayoutInflater().inflate(R.layout.com_appboy_inappmessage_modal, null);
    }
}
