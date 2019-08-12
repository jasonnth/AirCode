package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.p000v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.ReportableDetailsSummary */
public class ReportableDetailsSummary extends FrameLayout implements DividerView {
    @BindView
    View divider;
    @BindView
    AirTextView extraText;
    @BindView
    AirTextView label;
    @BindView
    AirTextView reportText;
    @BindView
    AirTextView subtitleText;
    @BindView
    AirTextView titleText;
    @BindView
    HaloImageView userImage;
    @BindView
    AirImageView userStatusIcon;

    public ReportableDetailsSummary(Context context) {
        super(context);
        init(null);
    }

    public ReportableDetailsSummary(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_reportable_details_summary, this);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_ReportableListingDetailsSummary);
        showDivider(a.getBoolean(R.styleable.n2_ReportableListingDetailsSummary_n2_showDivider, true));
        a.recycle();
    }

    public void setLabelText(CharSequence text) {
        ViewLibUtils.setVisibleIf(this.label, !TextUtils.isEmpty(text));
        this.label.setText(text);
    }

    public void setReportText(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.reportText, text);
    }

    public void setReported(boolean reported) {
        this.reportText.setTextColor(ContextCompat.getColor(getContext(), reported ? R.color.n2_text_color_main : R.color.n2_text_color_actionable));
    }

    public void setReportTextClickListener(OnClickListener listener) {
        this.reportText.setOnClickListener(listener);
        this.reportText.setClickable(listener != null);
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

    public void setUserStatusIcon(Drawable icon) {
        this.userStatusIcon.setImageDrawable(icon);
    }

    public void setUserStatusIcon(int resId) {
        this.userStatusIcon.setImageResource(resId);
    }

    public void setUserImageClickListener(OnClickListener listener) {
        this.userImage.setOnClickListener(listener);
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

    public static void mock(ReportableDetailsSummary view) {
        view.setTitleText("Title");
        view.setSubtitleText("Subtitle");
        view.setExtraText("Extra");
        view.setReportText("Reported");
    }
}
