package com.appboy.p028ui.inappmessage.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import com.appboy.enums.inappmessage.TextAlign;
import com.appboy.models.MessageButton;
import com.appboy.p028ui.inappmessage.IInAppMessageImmersiveView;
import com.appboy.p028ui.support.ViewUtils;
import com.appboy.support.StringUtils;
import com.appboy.ui.R;
import java.util.List;

/* renamed from: com.appboy.ui.inappmessage.views.AppboyInAppMessageImmersiveBaseView */
public abstract class AppboyInAppMessageImmersiveBaseView extends AppboyInAppMessageBaseView implements IInAppMessageImmersiveView {
    public abstract View getFrameView();

    public abstract List<View> getMessageButtonViews();

    public abstract View getMessageButtonsView();

    public abstract TextView getMessageHeaderTextView();

    public abstract TextView getMessageTextView();

    public AppboyInAppMessageImmersiveBaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setMessageButtons(List<MessageButton> messageButtons) {
        InAppMessageViewUtils.setButtons(getMessageButtonViews(), getMessageButtonsView(), getContext().getResources().getColor(R.color.com_appboy_inappmessage_button_bg_light), messageButtons);
        InAppMessageViewUtils.resetButtonSizesIfNecessary(getMessageButtonViews(), messageButtons);
    }

    public void setMessageCloseButtonColor(int color) {
        InAppMessageViewUtils.setViewBackgroundColorFilter(getMessageCloseButtonView(), color, getContext().getResources().getColor(R.color.com_appboy_inappmessage_button_close_light));
    }

    public void setMessageHeaderTextColor(int color) {
        InAppMessageViewUtils.setTextViewColor(getMessageHeaderTextView(), color);
    }

    public void setMessageHeaderText(String text) {
        getMessageHeaderTextView().setText(text);
    }

    public void setMessageHeaderTextAlignment(TextAlign textAlign) {
        InAppMessageViewUtils.setTextAlignment(getMessageHeaderTextView(), textAlign);
    }

    public void setFrameColor(Integer color) {
        InAppMessageViewUtils.setFrameColor(getFrameView(), color);
    }

    public void resetMessageMargins(boolean imageRetrievalSuccessful) {
        super.resetMessageMargins(imageRetrievalSuccessful);
        if (StringUtils.isNullOrBlank(getMessageTextView().getText().toString())) {
            ViewUtils.removeViewFromParent(getMessageTextView());
        }
        if (StringUtils.isNullOrBlank(getMessageHeaderTextView().getText().toString())) {
            ViewUtils.removeViewFromParent(getMessageHeaderTextView());
        }
        InAppMessageViewUtils.resetMessageMarginsIfNecessary(getMessageTextView(), getMessageHeaderTextView());
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4) {
            return super.onKeyDown(keyCode, event);
        }
        InAppMessageViewUtils.closeInAppMessageOnKeycodeBack();
        return true;
    }
}
