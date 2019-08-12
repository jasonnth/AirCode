package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.ImpactDisplayCardContentContainer;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

@Deprecated
/* renamed from: com.airbnb.n2.components.ImpactDisplayCard */
public class ImpactDisplayCard extends LinearLayout implements DividerView {
    @BindView
    View bottomSpace;
    @BindView
    ViewGroup clickContainer;
    @BindView
    ImpactDisplayCardContentContainer contentContainer;
    @BindView
    View divider;
    @BindView
    AirImageView imageView;
    @BindView
    AirTextView labelTextView;
    @BindView
    AirTextView subtitleTextView;
    @BindView
    AirTextView titleTextView;

    public ImpactDisplayCard(Context context) {
        super(context);
        init(null);
    }

    public ImpactDisplayCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ImpactDisplayCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_impact_display_card, this);
        setOrientation(1);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_ImpactDisplayCard);
        int style = a.getInteger(R.styleable.n2_ImpactDisplayCard_n2_cardStyle, 1);
        String title = a.getString(R.styleable.n2_ImpactDisplayCard_n2_titleText);
        String subtitle = a.getString(R.styleable.n2_ImpactDisplayCard_n2_subtitleText);
        String label = a.getString(R.styleable.n2_ImpactDisplayCard_n2_labelText);
        Drawable image = ViewLibUtils.getDrawableCompat(getContext(), a, R.styleable.n2_ImpactDisplayCard_n2_image);
        boolean showDivider = a.getBoolean(R.styleable.n2_ImpactDisplayCard_n2_showDivider, true);
        boolean showBottomSpace = a.getBoolean(R.styleable.n2_ImpactDisplayCard_n2_showBottomSpace, true);
        a.recycle();
        setStyle(style);
        setTitleText((CharSequence) title);
        setSubtitleText((CharSequence) subtitle);
        setLabelText((CharSequence) label);
        setImage(image);
        showDivider(showDivider);
        showBottomSpace(showBottomSpace);
    }

    public void setStyle(int style) {
        this.contentContainer.setStyle(style);
    }

    public void setTitleText(int strRes) {
        setTitleText((CharSequence) getResources().getString(strRes));
    }

    public void setTitleText(CharSequence title) {
        this.titleTextView.setText(title);
    }

    public void setSubtitleText(int strRes) {
        setSubtitleText((CharSequence) getResources().getString(strRes));
    }

    public void setSubtitleText(CharSequence subtitle) {
        ViewLibUtils.setVisibleIf(this.subtitleTextView, !TextUtils.isEmpty(subtitle));
        this.subtitleTextView.setText(subtitle);
    }

    public void setLabelText(CharSequence label) {
        ViewLibUtils.setVisibleIf(this.labelTextView, !TextUtils.isEmpty(label));
        this.labelTextView.setText(label);
    }

    public void setLabelText(int strRes) {
        this.labelTextView.setVisibility(0);
        this.labelTextView.setText(strRes);
    }

    public void setBackgroundColor(int color) {
        this.imageView.setBackgroundColor(color);
    }

    public void setImageUrl(String url) {
        this.imageView.setImageUrl(url);
    }

    public void clearImage() {
        this.imageView.clear();
    }

    public void setScrimForText(boolean enabled) {
        this.imageView.setScrimForText(enabled);
    }

    public void setImage(Drawable image) {
        this.imageView.setImageDrawable(image);
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public void showBottomSpace(boolean showBottomSpace) {
        ViewLibUtils.setVisibleIf(this.bottomSpace, showBottomSpace);
    }

    public void setOnClickListener(OnClickListener l) {
        this.clickContainer.setOnClickListener(l);
    }

    public static void mock(ImpactDisplayCard card) {
        card.setTitleText((CharSequence) "Title");
        card.setSubtitleText((CharSequence) "Optional subtitle");
        card.setLabelText((CharSequence) "Optional label");
    }
}
