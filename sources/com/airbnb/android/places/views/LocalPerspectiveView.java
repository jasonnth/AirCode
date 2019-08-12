package com.airbnb.android.places.views;

import android.content.Context;
import android.support.p000v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.utils.SpannableUtils;
import com.airbnb.android.places.C7627R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.AirmojiEnum;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

public class LocalPerspectiveView extends LinearLayout implements DividerView {
    @BindView
    AirTextView descriptionView;
    @BindView
    View sectionDivider;
    @BindView
    AirTextView sectionTitleView;
    @BindView
    HaloImageView userImageView;
    @BindView
    View userInfoDetailedView;
    @BindView
    AirTextView userInfoGenericView;
    @BindView
    AirTextView userInfoView;

    public LocalPerspectiveView(Context context) {
        super(context);
        init();
    }

    public LocalPerspectiveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LocalPerspectiveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), C7627R.layout.view_local_perspective, this);
        setOrientation(1);
        ButterKnife.bind((View) this);
    }

    public void setSectionTitle(CharSequence sectionTitle) {
        ViewLibUtils.setGoneIf(this.sectionTitleView, TextUtils.isEmpty(sectionTitle));
        this.sectionTitleView.setText(sectionTitle);
    }

    public void setDescription(CharSequence description) {
        this.descriptionView.setText(new StringBuilder("\"").append(description).append("\""));
    }

    public void setUserInfo(CharSequence name, CharSequence tagline, String imageUrl) {
        boolean hasName;
        boolean hasTagline;
        boolean hasImage;
        boolean hasNameOrTagline;
        boolean z;
        boolean z2 = true;
        if (!TextUtils.isEmpty(name)) {
            hasName = true;
        } else {
            hasName = false;
        }
        if (!TextUtils.isEmpty(tagline)) {
            hasTagline = true;
        } else {
            hasTagline = false;
        }
        if (!TextUtils.isEmpty(imageUrl)) {
            hasImage = true;
        } else {
            hasImage = false;
        }
        if (hasName || hasTagline) {
            hasNameOrTagline = true;
        } else {
            hasNameOrTagline = false;
        }
        AirTextView airTextView = this.userInfoGenericView;
        if (!hasNameOrTagline || hasImage) {
            z = false;
        } else {
            z = true;
        }
        ViewLibUtils.setVisibleIf(airTextView, z);
        View view = this.userInfoDetailedView;
        if (!hasNameOrTagline || !hasImage) {
            z2 = false;
        }
        ViewLibUtils.setVisibleIf(view, z2);
        if (hasNameOrTagline) {
            if (hasImage) {
                this.userImageView.setImageUrl(imageUrl);
                SpannableStringBuilder sb = new SpannableStringBuilder();
                if (hasName) {
                    sb.append(SpannableUtils.makeColoredString(SpannableUtils.makeBookString(name, getContext()), ContextCompat.getColor(getContext(), C7627R.color.n2_babu)));
                    if (hasTagline) {
                        sb.append(getContext().getString(C7627R.string.comma_separator));
                    }
                }
                if (hasTagline) {
                    sb.append(tagline);
                }
                this.userInfoView.setText(sb);
                return;
            }
            StringBuilder sb2 = new StringBuilder(AirmojiEnum.EMDASH.character);
            if (hasName) {
                sb2.append(name);
                if (hasTagline) {
                    sb2.append(getContext().getString(C7627R.string.comma_separator));
                }
            }
            if (hasTagline) {
                sb2.append(tagline);
            }
            this.userInfoGenericView.setText(sb2);
        }
    }

    public void showDivider(boolean show) {
        ViewLibUtils.setVisibleIf(this.sectionDivider, show);
    }
}
