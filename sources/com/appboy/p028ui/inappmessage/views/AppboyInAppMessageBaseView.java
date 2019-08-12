package com.appboy.p028ui.inappmessage.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.appboy.enums.inappmessage.TextAlign;
import com.appboy.models.IInAppMessage;
import com.appboy.p028ui.inappmessage.AppboyInAppMessageSimpleDraweeView;
import com.appboy.p028ui.inappmessage.IInAppMessageView;
import com.appboy.p028ui.support.FrescoLibraryUtils;
import com.appboy.p028ui.support.ViewUtils;
import com.appboy.support.StringUtils;
import com.appboy.ui.R;

/* renamed from: com.appboy.ui.inappmessage.views.AppboyInAppMessageBaseView */
public abstract class AppboyInAppMessageBaseView extends RelativeLayout implements IInAppMessageView {
    final boolean mCanUseFresco;

    public abstract Object getMessageBackgroundObject();

    public abstract TextView getMessageIconView();

    public abstract ImageView getMessageImageView();

    public abstract View getMessageSimpleDraweeView();

    public abstract TextView getMessageTextView();

    public AppboyInAppMessageBaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mCanUseFresco = FrescoLibraryUtils.canUseFresco(context);
        if (VERSION.SDK_INT > 11) {
            setLayerType(1, null);
        }
    }

    public void setMessageBackgroundColor(int color) {
        InAppMessageViewUtils.setViewBackgroundColor((View) getMessageBackgroundObject(), color);
    }

    public void setMessageTextColor(int color) {
        InAppMessageViewUtils.setTextViewColor(getMessageTextView(), color);
    }

    public void setMessageTextAlign(TextAlign textAlign) {
        InAppMessageViewUtils.setTextAlignment(getMessageTextView(), textAlign);
    }

    public void setMessage(String text) {
        getMessageTextView().setText(text);
    }

    public void setMessageImageView(Bitmap bitmap) {
        InAppMessageViewUtils.setImage(bitmap, getMessageImageView());
    }

    public void setMessageSimpleDrawee(IInAppMessage inAppMessage) {
        FrescoLibraryUtils.setDraweeControllerHelper((AppboyInAppMessageSimpleDraweeView) getMessageSimpleDraweeView(), getAppropriateImageUrl(inAppMessage), 0.0f, false);
    }

    public String getAppropriateImageUrl(IInAppMessage inAppMessage) {
        if (!StringUtils.isNullOrBlank(inAppMessage.getLocalImageUrl())) {
            return inAppMessage.getLocalImageUrl();
        }
        return inAppMessage.getRemoteImageUrl();
    }

    public void setMessageIcon(String icon, int iconColor, int iconBackgroundColor) {
        if (getMessageIconView() != null) {
            InAppMessageViewUtils.setIcon(getContext(), icon, iconColor, iconBackgroundColor, getMessageIconView());
        }
    }

    public void resetMessageMargins(boolean imageRetrievalSuccessful) {
        View viewContainingImage;
        RelativeLayout layoutContainingImage;
        if (this.mCanUseFresco) {
            viewContainingImage = getMessageSimpleDraweeView();
            layoutContainingImage = (RelativeLayout) findViewById(R.id.com_appboy_stubbed_inappmessage_drawee_view_parent);
        } else {
            viewContainingImage = getMessageImageView();
            layoutContainingImage = (RelativeLayout) findViewById(R.id.com_appboy_stubbed_inappmessage_image_view_parent);
        }
        if (viewContainingImage != null) {
            if (!imageRetrievalSuccessful) {
                ViewUtils.removeViewFromParent(viewContainingImage);
                if (layoutContainingImage != null) {
                    ViewUtils.removeViewFromParent(layoutContainingImage);
                }
            } else {
                ViewUtils.removeViewFromParent(getMessageIconView());
            }
        }
        if (getMessageIconView() != null && StringUtils.isNullOrBlank((String) getMessageIconView().getText())) {
            ViewUtils.removeViewFromParent(getMessageIconView());
        }
    }

    public View getMessageClickableView() {
        return this;
    }

    /* access modifiers changed from: 0000 */
    public View getProperViewFromInflatedStub(int stubLayoutId) {
        ((ViewStub) findViewById(stubLayoutId)).inflate();
        if (this.mCanUseFresco) {
            return findViewById(R.id.com_appboy_stubbed_inappmessage_drawee_view);
        }
        return findViewById(R.id.com_appboy_stubbed_inappmessage_image_view);
    }
}
