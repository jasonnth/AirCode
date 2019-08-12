package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.bumptech.glide.request.RequestListener;

/* renamed from: com.airbnb.n2.components.ImagePreviewRow */
public class ImagePreviewRow extends LinearLayout {
    @BindView
    AirImageView imagePreview;
    @BindView
    View sectionDivider;
    @BindView
    AirTextView subtitleText;
    @BindView
    AirTextView titleText;

    public ImagePreviewRow(Context context) {
        super(context);
        init(null);
    }

    public ImagePreviewRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ImagePreviewRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_image_preview_row, this);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
        setOrientation(1);
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.n2_ImagePreviewRow);
        String titleString = ta.getString(R.styleable.n2_ImagePreviewRow_n2_titleText);
        String subtitleString = ta.getString(R.styleable.n2_ImagePreviewRow_n2_subtitleText);
        boolean showDivider = ta.getBoolean(R.styleable.n2_ImagePreviewRow_n2_showDivider, true);
        setTitle((CharSequence) titleString);
        setSubtitleText((CharSequence) subtitleString);
        setBottomSectionDividerVisible(showDivider);
        ta.recycle();
    }

    public void setTitle(CharSequence text) {
        this.titleText.setText(text);
    }

    public void setTitle(int textRes) {
        setTitle((CharSequence) getResources().getString(textRes));
    }

    public void setSubtitleText(CharSequence text) {
        ViewLibUtils.setVisibleIf(this.subtitleText, !TextUtils.isEmpty(text));
        this.subtitleText.setText(text);
    }

    public void setSubtitleText(int textRes) {
        setSubtitleText((CharSequence) getResources().getString(textRes));
    }

    public void setBottomSectionDividerVisible(boolean show) {
        ViewLibUtils.setVisibleIf(this.sectionDivider, show);
    }

    public void setImageUrl(String url, RequestListener<String, Bitmap> requestListener) {
        if (url != null) {
            this.imagePreview.setVisibility(0);
            this.imagePreview.setImageUrl(url, requestListener);
            return;
        }
        this.imagePreview.setVisibility(8);
    }

    public static void mock(ImagePreviewRow view) {
        view.setTitle((CharSequence) "Title");
        view.setSubtitleText((CharSequence) "Subtitle");
    }
}
