package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirEditTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ColorizedDrawable;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.MessageInputOneRow */
public class MessageInputOneRow extends MessageInputRow {
    @BindView
    View divider;
    @BindView
    AirImageView icon;
    private String iconType = "icon_type_no_icon";

    public MessageInputOneRow(Context context) {
        super(context);
    }

    public MessageInputOneRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MessageInputOneRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.n2_message_input_one_row;
    }

    public void bindViews() {
        this.input = (AirEditTextView) ViewLibUtils.findById(this, R.id.input_edit_text_one_row);
    }

    public void setupCamera() {
        setupIcon(R.drawable.n2_ic_camera, MessageInputOneRow$$Lambda$1.lambdaFactory$(this));
        this.iconType = "icon_type_camera";
    }

    public void setupSavedMessages() {
        setupIcon(R.drawable.n2_ic_saved_messages, MessageInputOneRow$$Lambda$2.lambdaFactory$(this));
        this.iconType = "icon_type_saved_message";
    }

    public ImageView getSavedMessageIcon() {
        if (this.iconType.equals("icon_type_saved_message")) {
            return this.icon;
        }
        return null;
    }

    private void setupIcon(int drawableId, OnClickListener listener) {
        this.icon.setVisibility(0);
        this.icon.setImageDrawable(ColorizedDrawable.forIdWithColor(getContext(), drawableId, R.color.n2_text_color_actionable));
        this.divider.setVisibility(0);
        this.icon.setOnClickListener(listener);
    }

    public void hideIcon() {
        this.icon.setVisibility(8);
        this.divider.setVisibility(8);
    }

    public static void mock(MessageInputOneRow row) {
    }
}
