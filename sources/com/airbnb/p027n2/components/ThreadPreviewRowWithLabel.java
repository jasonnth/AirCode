package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.imaging.Image;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.components.ThreadPreviewRowWithLabel */
public class ThreadPreviewRowWithLabel extends BaseDividerComponent {
    @BindView
    AirTextView fourthRowText;
    @BindView
    AirImageView imageView;
    @BindView
    AirTextView labelText;
    @BindView
    AirTextView subtitleText;
    @BindView
    AirTextView thirdRowText;
    @BindView
    AirTextView timeAgoText;
    @BindView
    AirTextView titleText;
    @BindView
    AirImageView unreadIndicator;

    public ThreadPreviewRowWithLabel(Context context) {
        super(context);
    }

    public ThreadPreviewRowWithLabel(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ThreadPreviewRowWithLabel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        Paris.style(this).apply(attrs);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_thread_preview_row_with_label;
    }

    public void setTitle(CharSequence text) {
        this.titleText.setText(text);
    }

    public void setTitle(int textRes) {
        setTitle((CharSequence) getResources().getString(textRes));
    }

    public void setSubtitle(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.subtitleText, text, true);
    }

    public void setSubtitle(int textRes) {
        setSubtitle((CharSequence) getResources().getString(textRes));
    }

    public void setThirdRowText(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.thirdRowText, text, true);
    }

    public void setThirdRowText(int textRes) {
        setThirdRowText((CharSequence) getResources().getString(textRes));
    }

    public void setFourthRowText(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.fourthRowText, text, true);
    }

    public void setFourthRowText(int textRes) {
        setFourthRowText((CharSequence) getResources().getString(textRes));
    }

    public void setTimeAgoText(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.timeAgoText, text, true);
    }

    public void setTimeAgoText(int textRes) {
        setTimeAgoText((CharSequence) getResources().getString(textRes));
    }

    public void setLabelText(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.labelText, text, true);
    }

    public void setLabelText(int textRes) {
        setLabelText((CharSequence) getResources().getString(textRes));
    }

    public void setImage(int drawableRes) {
        this.imageView.setImageResource(drawableRes);
    }

    public void setImage(Image image) {
        this.imageView.setImage(image);
    }

    public void showUnreadIndicator(boolean isVisible) {
        ViewLibUtils.setVisibleIf(this.unreadIndicator, isVisible);
    }

    public static void mock(ThreadPreviewRowWithLabel row) {
        row.setTitle((CharSequence) "title");
        row.setSubtitle((CharSequence) "subTitle");
        row.setThirdRowText((CharSequence) "thirdRowText");
        row.setFourthRowText((CharSequence) "fourthRowText");
        row.setImage(R.drawable.n2_empty_profile_halo_large_babu);
        row.showUnreadIndicator(true);
        row.setLabelText((CharSequence) "label");
        row.setTimeAgoText((CharSequence) "15:31 AM");
    }
}
