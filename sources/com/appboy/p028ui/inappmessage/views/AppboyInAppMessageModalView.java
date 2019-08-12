package com.appboy.p028ui.inappmessage.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.appboy.Constants;
import com.appboy.enums.inappmessage.ImageStyle;
import com.appboy.models.IInAppMessageImmersive;
import com.appboy.p028ui.inappmessage.AppboyInAppMessageImageView;
import com.appboy.p028ui.inappmessage.AppboyInAppMessageSimpleDraweeView;
import com.appboy.p028ui.inappmessage.IInAppMessageImageView;
import com.appboy.p028ui.inappmessage.config.AppboyInAppMessageParams;
import com.appboy.p028ui.support.FrescoLibraryUtils;
import com.appboy.p028ui.support.ViewUtils;
import com.appboy.support.AppboyLogger;
import com.appboy.ui.R;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.imagepipeline.image.ImageInfo;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.appboy.ui.inappmessage.views.AppboyInAppMessageModalView */
public class AppboyInAppMessageModalView extends AppboyInAppMessageImmersiveBaseView {
    /* access modifiers changed from: private */
    public static final String TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyInAppMessageModalView.class.getName()});
    private AppboyInAppMessageImageView mAppboyInAppMessageImageView;
    /* access modifiers changed from: private */
    public View mSimpleDraweeView;

    public AppboyInAppMessageModalView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void inflateStubViews(Activity activity, IInAppMessageImmersive inAppMessage) {
        if (this.mCanUseFresco) {
            this.mSimpleDraweeView = getProperViewFromInflatedStub(R.id.com_appboy_inappmessage_modal_drawee_stub);
            setInAppMessageImageViewAttributes(activity, inAppMessage, (AppboyInAppMessageSimpleDraweeView) this.mSimpleDraweeView);
            return;
        }
        this.mAppboyInAppMessageImageView = (AppboyInAppMessageImageView) getProperViewFromInflatedStub(R.id.com_appboy_inappmessage_modal_imageview_stub);
        setInAppMessageImageViewAttributes(activity, inAppMessage, this.mAppboyInAppMessageImageView);
        if (inAppMessage.getImageStyle().equals(ImageStyle.GRAPHIC) && inAppMessage.getBitmap() != null) {
            resizeGraphicFrameIfAppropriate(activity, inAppMessage, ((double) inAppMessage.getBitmap().getWidth()) / ((double) inAppMessage.getBitmap().getHeight()));
        }
    }

    public View getFrameView() {
        return findViewById(R.id.com_appboy_inappmessage_modal_frame);
    }

    public void resetMessageMargins(boolean imageRetrievalSuccessful) {
        super.resetMessageMargins(imageRetrievalSuccessful);
        RelativeLayout imageLayout = (RelativeLayout) findViewById(R.id.com_appboy_inappmessage_modal_image_layout);
        if ((imageRetrievalSuccessful || getMessageIconView() != null) && imageLayout != null) {
            LayoutParams layoutParams = new LayoutParams(-1, -2);
            layoutParams.setMargins(0, 0, 0, 0);
            imageLayout.setLayoutParams(layoutParams);
        }
        findViewById(R.id.com_appboy_inappmessage_modal_text_layout).setOnClickListener(new OnClickListener() {
            public void onClick(View scrollView) {
                AppboyLogger.m1733d(AppboyInAppMessageModalView.TAG, "Passing scrollView click event to message clickable view.");
                AppboyInAppMessageModalView.this.getMessageClickableView().performClick();
            }
        });
    }

    public void setMessageBackgroundColor(int color) {
        InAppMessageViewUtils.setViewBackgroundColorFilter(findViewById(R.id.com_appboy_inappmessage_modal), color, getContext().getResources().getColor(R.color.com_appboy_inappmessage_background_light));
    }

    public List<View> getMessageButtonViews() {
        List<View> buttonViews = new ArrayList<>();
        if (findViewById(R.id.com_appboy_inappmessage_modal_button_one) != null) {
            buttonViews.add(findViewById(R.id.com_appboy_inappmessage_modal_button_one));
        }
        if (findViewById(R.id.com_appboy_inappmessage_modal_button_two) != null) {
            buttonViews.add(findViewById(R.id.com_appboy_inappmessage_modal_button_two));
        }
        return buttonViews;
    }

    public View getMessageButtonsView() {
        return findViewById(R.id.com_appboy_inappmessage_modal_button_layout);
    }

    public TextView getMessageTextView() {
        return (TextView) findViewById(R.id.com_appboy_inappmessage_modal_message);
    }

    public TextView getMessageHeaderTextView() {
        return (TextView) findViewById(R.id.com_appboy_inappmessage_modal_header_text);
    }

    public View getMessageClickableView() {
        return findViewById(R.id.com_appboy_inappmessage_modal);
    }

    public View getMessageCloseButtonView() {
        return findViewById(R.id.com_appboy_inappmessage_modal_close_button);
    }

    public TextView getMessageIconView() {
        return (TextView) findViewById(R.id.com_appboy_inappmessage_modal_icon);
    }

    public Drawable getMessageBackgroundObject() {
        return getMessageClickableView().getBackground();
    }

    public ImageView getMessageImageView() {
        return this.mAppboyInAppMessageImageView;
    }

    public View getMessageSimpleDraweeView() {
        return this.mSimpleDraweeView;
    }

    public void setMessageSimpleDrawee(final IInAppMessageImmersive inAppMessage, final Activity activity) {
        if (inAppMessage.getImageStyle().equals(ImageStyle.GRAPHIC)) {
            FrescoLibraryUtils.setDraweeControllerHelper((AppboyInAppMessageSimpleDraweeView) getMessageSimpleDraweeView(), getAppropriateImageUrl(inAppMessage), 0.0f, false, new BaseControllerListener<ImageInfo>() {
                public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                    if (imageInfo != null) {
                        final double imageAspectRatio = ((double) imageInfo.getWidth()) / ((double) imageInfo.getHeight());
                        AppboyInAppMessageModalView.this.mSimpleDraweeView.post(new Runnable() {
                            public void run() {
                                AppboyInAppMessageModalView.this.resizeGraphicFrameIfAppropriate(activity, inAppMessage, imageAspectRatio);
                            }
                        });
                    }
                }
            });
            return;
        }
        setMessageSimpleDrawee(inAppMessage);
    }

    private void setInAppMessageImageViewAttributes(Activity activity, IInAppMessageImmersive inAppMessage, IInAppMessageImageView inAppMessageImageView) {
        float pixelRadius = (float) ViewUtils.convertDpToPixels(activity, AppboyInAppMessageParams.getModalizedImageRadiusDp());
        if (inAppMessage.getImageStyle().equals(ImageStyle.GRAPHIC)) {
            inAppMessageImageView.setCornersRadiusPx(pixelRadius);
        } else {
            inAppMessageImageView.setCornersRadiiPx(pixelRadius, pixelRadius, 0.0f, 0.0f);
        }
        inAppMessageImageView.setInAppMessageImageCropType(inAppMessage.getCropType());
    }

    /* access modifiers changed from: private */
    public void resizeGraphicFrameIfAppropriate(Activity activity, IInAppMessageImmersive inAppMessage, double imageAspectRatio) {
        if (inAppMessage.getImageStyle().equals(ImageStyle.GRAPHIC)) {
            double maxWidthDp = AppboyInAppMessageParams.getGraphicModalMaxWidthDp();
            double maxHeightDp = AppboyInAppMessageParams.getGraphicModalMaxHeightDp();
            LayoutParams params = (LayoutParams) findViewById(R.id.com_appboy_inappmessage_modal_graphic_bound).getLayoutParams();
            if (imageAspectRatio >= maxWidthDp / maxHeightDp) {
                params.width = (int) ViewUtils.convertDpToPixels(activity, maxWidthDp);
                params.height = (int) (ViewUtils.convertDpToPixels(activity, maxWidthDp) / imageAspectRatio);
            } else {
                params.width = (int) (ViewUtils.convertDpToPixels(activity, maxHeightDp) * imageAspectRatio);
                params.height = (int) ViewUtils.convertDpToPixels(activity, maxHeightDp);
            }
            findViewById(R.id.com_appboy_inappmessage_modal_graphic_bound).setLayoutParams(params);
        }
    }
}
