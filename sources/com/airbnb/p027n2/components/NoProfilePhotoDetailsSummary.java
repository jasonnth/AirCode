package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.NoProfilePhotoDetailsSummary */
public class NoProfilePhotoDetailsSummary extends FrameLayout implements DividerView {
    @BindView
    View divider;
    @BindView
    AirTextView extraText;
    @BindView
    AirImageView homeImage;
    @BindView
    AirTextView label;
    @BindView
    AirTextView subtitleText;
    @BindView
    AirImageView titleStatusIcon;
    @BindView
    AirTextView titleText;
    @BindView
    HaloImageView userImage;
    @BindView
    FrameLayout userImageContainer;
    @BindView
    AirImageView userStatusIcon;

    public NoProfilePhotoDetailsSummary(Context context) {
        super(context);
        init(null);
    }

    public NoProfilePhotoDetailsSummary(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_no_profile_photo_details_summary, this);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_NoProfilePhotoDetailsSummary);
        showDivider(a.getBoolean(R.styleable.n2_NoProfilePhotoDetailsSummary_n2_showDivider, true));
        if (a.getBoolean(R.styleable.n2_NoProfilePhotoDetailsSummary_n2_showHome, false)) {
            this.homeImage.setVisibility(0);
            this.userImageContainer.setVisibility(8);
        }
        a.recycle();
    }

    public void setLabelText(CharSequence text) {
        ViewLibUtils.setVisibleIf(this.label, !TextUtils.isEmpty(text));
        this.label.setText(text);
    }

    public void setTitleText(CharSequence text) {
        this.titleText.setText(text);
    }

    public void setSubtitleText(CharSequence text) {
        this.subtitleText.setText(text);
    }

    public void setExtraText(CharSequence text) {
        ViewLibUtils.setVisibleIf(this.extraText, !TextUtils.isEmpty(text));
        this.extraText.setText(text);
    }

    public void setUserImageUrl(String url) {
        if (url != null) {
            this.userImage.setImageUrl(url);
        } else {
            this.userImage.setImageDrawable(null);
        }
    }

    public void setHomeImageUrl(String url) {
        if (url != null) {
            this.homeImage.setImageUrl(url);
        } else {
            this.homeImage.setImageDrawable(null);
        }
    }

    public void setUserStatusIcon(Drawable icon) {
        this.userStatusIcon.setImageDrawable(icon);
    }

    public void setUserStatusIcon(int resId) {
        this.userStatusIcon.setImageResource(resId);
    }

    public void setTitleStatusIcon(int resId) {
        this.titleStatusIcon.setImageResource(resId);
    }

    public void setUserImageClickListener(OnClickListener listener) {
        this.userImage.setOnClickListener(listener);
    }

    public void setHomeImageClickListener(OnClickListener listener) {
        this.homeImage.setOnClickListener(listener);
    }

    public void setSubtitleClickListener(OnClickListener listener) {
        this.subtitleText.setOnClickListener(listener);
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public void setImageResource(int resId) {
        this.userImage.setImageResource(resId);
    }

    public void setHomeImageResource(int resId) {
        this.homeImage.setImageResource(resId);
    }

    public static void mock(NoProfilePhotoDetailsSummary summary) {
        summary.setTitleText("Title");
        summary.setSubtitleText("Subtitle");
        summary.setExtraText("Extra");
    }
}
