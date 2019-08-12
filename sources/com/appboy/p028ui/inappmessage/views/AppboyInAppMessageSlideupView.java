package com.appboy.p028ui.inappmessage.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.appboy.enums.inappmessage.ClickAction;
import com.appboy.models.IInAppMessage;
import com.appboy.p028ui.inappmessage.AppboyInAppMessageImageView;
import com.appboy.p028ui.inappmessage.AppboyInAppMessageSimpleDraweeView;
import com.appboy.ui.R;

/* renamed from: com.appboy.ui.inappmessage.views.AppboyInAppMessageSlideupView */
public class AppboyInAppMessageSlideupView extends AppboyInAppMessageBaseView {
    private AppboyInAppMessageImageView mAppboyInAppMessageImageView;
    private View mSimpleDraweeView;

    public AppboyInAppMessageSlideupView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void inflateStubViews(IInAppMessage inAppMessage) {
        if (this.mCanUseFresco) {
            this.mSimpleDraweeView = getProperViewFromInflatedStub(R.id.com_appboy_inappmessage_slideup_drawee_stub);
            ((AppboyInAppMessageSimpleDraweeView) this.mSimpleDraweeView).setInAppMessageImageCropType(inAppMessage.getCropType());
            return;
        }
        this.mAppboyInAppMessageImageView = (AppboyInAppMessageImageView) getProperViewFromInflatedStub(R.id.com_appboy_inappmessage_slideup_imageview_stub);
        this.mAppboyInAppMessageImageView.setInAppMessageImageCropType(inAppMessage.getCropType());
    }

    public void setMessageChevron(int color, ClickAction clickAction) {
        switch (clickAction) {
            case NONE:
                getMessageChevronView().setVisibility(8);
                return;
            default:
                InAppMessageViewUtils.setViewBackgroundColorFilter(getMessageChevronView(), color, getContext().getResources().getColor(R.color.com_appboy_inappmessage_chevron));
                return;
        }
    }

    public TextView getMessageTextView() {
        return (TextView) findViewById(R.id.com_appboy_inappmessage_slideup_message);
    }

    public View getMessageBackgroundObject() {
        return findViewById(R.id.com_appboy_inappmessage_slideup);
    }

    public ImageView getMessageImageView() {
        return this.mAppboyInAppMessageImageView;
    }

    public View getMessageSimpleDraweeView() {
        return this.mSimpleDraweeView;
    }

    public TextView getMessageIconView() {
        return (TextView) findViewById(R.id.com_appboy_inappmessage_slideup_icon);
    }

    public View getMessageChevronView() {
        return findViewById(R.id.com_appboy_inappmessage_slideup_chevron);
    }
}
