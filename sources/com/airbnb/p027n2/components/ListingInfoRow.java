package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.components.ListingInfoRow */
public class ListingInfoRow extends BaseDividerComponent {
    @BindView
    AirImageView imageDrawable;
    @BindView
    AirTextView label;
    @BindView
    AirButton primaryButton;
    @BindView
    ProgressBar progressBar;
    @BindView
    AirTextView subtitleText;
    @BindView
    AirTextView titleText;

    public ListingInfoRow(Context context) {
        super(context);
    }

    public ListingInfoRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListingInfoRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_listing_info_row;
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        Paris.style(this).apply(attrs);
    }

    public void setTitle(CharSequence text) {
        this.titleText.setText(text);
    }

    public void setTitleMaxLine(int maxLine) {
        boolean z = true;
        AirTextView airTextView = this.titleText;
        if (maxLine != 1) {
            z = false;
        }
        airTextView.setSingleLine(z);
        this.titleText.setMaxLines(maxLine);
    }

    public void setSubtitleText(CharSequence text) {
        this.subtitleText.setText(text);
    }

    public void setButtonText(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.primaryButton, text);
    }

    public void setLabel(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.label, text);
    }

    public void setImage(String imageUrl) {
        this.imageDrawable.setImageUrl(imageUrl);
    }

    public void setImage(int drawableRes) {
        this.imageDrawable.setImageResource(drawableRes);
    }

    public void setProgressBarVisible(boolean visible) {
        this.progressBar.setVisibility(visible ? 0 : 8);
    }

    public void setProgressBarPercentage(int percentage) {
        this.progressBar.setProgress(percentage);
    }

    public void setButtonClickListener(OnClickListener listener) {
        this.primaryButton.setOnClickListener(listener);
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
    }

    public static void mock(ListingInfoRow view) {
        view.setTitle("Title");
        view.setSubtitleText("Subtitle");
        view.setButtonText("Button");
    }

    public static void mockHackBerry(ListingInfoRow view) {
        view.setTitle("Title");
        view.setSubtitleText("Subtitle");
        view.setButtonText("Button");
        Paris.style(view).applyHackberry();
    }

    public static void mockLabel(ListingInfoRow view) {
        view.setTitle("Title");
        view.setSubtitleText("Subtitle");
        view.setLabel("Label");
    }

    public static void mockDisabled(ListingInfoRow view) {
        view.setTitle("Title");
        view.setSubtitleText("Subtitle");
        Paris.style(view).applyDisabled();
    }
}
