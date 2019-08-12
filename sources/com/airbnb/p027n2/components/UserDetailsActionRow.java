package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.UserDetailsActionRow */
public class UserDetailsActionRow extends BaseDividerComponent {
    @BindView
    AirTextView extraText;
    @BindView
    AirImageView homeImage;
    @BindView
    AirTextView label;
    @BindView
    AirTextView subtitleText;
    @BindView
    AirTextView titleText;
    @BindView
    HaloImageView userImage;
    @BindView
    FrameLayout userImageContainer;
    @BindView
    AirImageView userStatusIcon;

    public UserDetailsActionRow(Context context) {
        super(context);
    }

    public UserDetailsActionRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UserDetailsActionRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_user_details_action_row;
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_UserDetailsActionRow);
        if (a.getBoolean(R.styleable.n2_UserDetailsActionRow_n2_showHomePhoto, false)) {
            this.homeImage.setVisibility(0);
            this.userImageContainer.setVisibility(8);
        }
        if (a.hasValue(R.styleable.n2_UserDetailsActionRow_n2_titleMaxLines)) {
            this.titleText.setMaxLines(a.getInt(R.styleable.n2_UserDetailsActionRow_n2_titleMaxLines, 0));
        }
        if (a.hasValue(R.styleable.n2_UserDetailsActionRow_n2_homeImageTopPadding)) {
            this.homeImage.setPadding(this.homeImage.getPaddingLeft(), a.getDimensionPixelSize(R.styleable.n2_UserDetailsActionRow_n2_homeImageTopPadding, 0), this.homeImage.getPaddingRight(), this.homeImage.getPaddingBottom());
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

    public void showPlaceholderImage() {
        if (this.userImage.getPlaceholderResId() != 0) {
            this.userImage.setImageResource(this.userImage.getPlaceholderResId());
        } else if (this.userImage.getPlaceholderDrawable() != null) {
            this.userImage.setImageDrawable(this.userImage.getPlaceholderDrawable());
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

    public void setUserImageClickListener(OnClickListener listener) {
        this.userImage.setOnClickListener(listener);
    }

    public void setHomeImageClickListener(OnClickListener listener) {
        this.homeImage.setOnClickListener(listener);
    }

    public void setSubtitleClickListener(OnClickListener listener) {
        this.subtitleText.setOnClickListener(listener);
    }

    public void setImageResource(int resId) {
        this.userImage.setImageResource(resId);
    }

    public void setHomeImageResource(int resId) {
        this.homeImage.setImageResource(resId);
    }

    public static void mock(UserDetailsActionRow row) {
        row.setTitleText("Title");
        row.setSubtitleText("Subtitle");
        row.setExtraText("Extra");
    }
}
