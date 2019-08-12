package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.MessageTranslationRow */
public class MessageTranslationRow extends LinearLayout {
    @BindView
    AirImageView airImageCaption;
    @BindView
    View loaderView;
    @BindView
    AirTextView textViewStatus;

    public MessageTranslationRow(Context context) {
        super(context);
        init();
    }

    public MessageTranslationRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MessageTranslationRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.n2_translation_row, this);
        setOrientation(1);
        ButterKnife.bind((View) this);
    }

    public void setStatusText(CharSequence s) {
        this.textViewStatus.setText(s);
    }

    public void setImageCaption(int resource) {
        this.airImageCaption.setImageDrawableCompat(resource);
    }

    public void showImageCaption(boolean shouldShow) {
        ViewLibUtils.setVisibleIf(this.airImageCaption, shouldShow);
    }

    public void setStatusTextVisibility(boolean shouldShow) {
        ViewLibUtils.setVisibleIf(this.textViewStatus, shouldShow);
    }

    public void setLoadingState(boolean loadingState) {
        ViewLibUtils.setVisibleIf(this.loaderView, loadingState);
    }

    public static void mock(MessageTranslationRow view) {
        view.setStatusText("Status");
    }
}
