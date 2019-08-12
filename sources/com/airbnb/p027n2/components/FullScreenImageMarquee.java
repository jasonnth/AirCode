package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.percent.PercentFrameLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.FullScreenImageMarquee */
public class FullScreenImageMarquee extends PercentFrameLayout {
    @BindView
    AirTextView descriptionTextView;
    @BindView
    AirTextView imageTextView;
    @BindView
    AirImageView imageView;
    @BindView
    AirTextView titleTextView;

    public FullScreenImageMarquee(Context context) {
        super(context);
        init(null);
    }

    public FullScreenImageMarquee(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public FullScreenImageMarquee(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_full_screen_image_marquee, this);
        ButterKnife.bind((View) this);
        setupAttrs(attrs);
        setBackgroundResource(R.color.n2_babu);
    }

    private void setupAttrs(AttributeSet attrs) {
        Context context = getContext();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.n2_FullScreenImageMarquee, 0, 0);
        Drawable image = ViewLibUtils.getDrawableCompat(context, a, R.styleable.n2_FullScreenImageMarquee_n2_image);
        String title = a.getString(R.styleable.n2_FullScreenImageMarquee_n2_titleText);
        String description = a.getString(R.styleable.n2_FullScreenImageMarquee_n2_descriptionText);
        String imageText = a.getString(R.styleable.n2_FullScreenImageMarquee_n2_imageText);
        setImage(image);
        setTitle((CharSequence) title);
        setDescription((CharSequence) description);
        setImageText((CharSequence) imageText);
    }

    public void setImage(Drawable image) {
        this.imageView.setImageDrawable(image);
    }

    public void setImage(int drawableRes) {
        this.imageView.setImageDrawableCompat(drawableRes);
    }

    public void setImageUrl(String imageUrl) {
        this.imageView.setImageUrl(imageUrl);
    }

    public void setImageText(CharSequence imageText) {
        ViewLibUtils.setVisibleIf(this.imageTextView, !TextUtils.isEmpty(imageText));
        this.imageTextView.setText(imageText);
    }

    public void setImageText(int imageText) {
        String imageTextText = getResources().getString(imageText);
        ViewLibUtils.setVisibleIf(this.imageTextView, !TextUtils.isEmpty(imageTextText));
        this.imageTextView.setText(imageTextText);
    }

    public void setTitle(CharSequence title) {
        ViewLibUtils.setVisibleIf(this.titleTextView, !TextUtils.isEmpty(title));
        this.titleTextView.setText(title);
    }

    public void setTitle(int titleRes) {
        String title = getResources().getString(titleRes);
        ViewLibUtils.setVisibleIf(this.titleTextView, !TextUtils.isEmpty(title));
        this.titleTextView.setText(title);
    }

    public void setDescription(CharSequence description) {
        ViewLibUtils.setVisibleIf(this.descriptionTextView, !TextUtils.isEmpty(description));
        this.descriptionTextView.setText(description);
    }

    public void setDescription(int descriptionRes) {
        String description = getResources().getString(descriptionRes);
        ViewLibUtils.setVisibleIf(this.descriptionTextView, !TextUtils.isEmpty(description));
        this.descriptionTextView.setText(description);
    }

    public static void mock(FullScreenImageMarquee view) {
        view.setTitle((CharSequence) "Title");
        view.setDescription((CharSequence) "Description");
        view.setImageText((CharSequence) "Image text");
    }
}
