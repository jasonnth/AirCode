package com.airbnb.p027n2.components;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirEditTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.MessageInputTwoRows */
public class MessageInputTwoRows extends MessageInputRow {
    @BindView
    AirImageView cameraIcon;
    @BindView
    AirImageView photosIcon;
    @BindView
    AirImageView savedMessageIcon;
    @BindView
    AirImageView textIcon;

    public MessageInputTwoRows(Context context) {
        super(context);
    }

    public MessageInputTwoRows(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MessageInputTwoRows(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.n2_message_input_two_rows;
    }

    public void bindViews() {
        this.input = (AirEditTextView) ViewLibUtils.findById(this, R.id.input_edit_text_two_rows);
        setupIcon(this.savedMessageIcon);
        setupIcon(this.cameraIcon);
        setupIcon(this.photosIcon);
        setupIcon(this.textIcon);
    }

    @OnClick
    public void showTextInput() {
        this.textIcon.setActivated(true);
        this.messageInputListener.onTextIconClicked(this.input);
    }

    @OnClick
    public void showSavedMessages() {
        this.textIcon.setActivated(false);
        this.messageInputListener.onSavedMessageIconClicked();
    }

    @OnClick
    public void showCamera() {
        this.textIcon.setActivated(false);
        this.messageInputListener.onCameraIconClicked();
    }

    @OnClick
    public void showPhotos() {
        this.textIcon.setActivated(false);
        this.messageInputListener.onPhotoIconClicked();
    }

    public ImageView getSavedMessageIcon() {
        return this.savedMessageIcon;
    }

    private void setupIcon(AirImageView iconView) {
        Drawable icon = DrawableCompat.wrap(iconView.getDrawable());
        DrawableCompat.setTintList(icon, ContextCompat.getColorStateList(getContext(), R.color.n2_message_input_two_rows_icon_tint_color));
        iconView.setImageDrawable(icon);
    }

    public static void mock(MessageInputTwoRows view) {
    }
}
