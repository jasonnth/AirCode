package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.p000v4.content.ContextCompat;
import android.support.percent.PercentRelativeLayout;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.TextUtil;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.MarketingCard */
public class MarketingCard extends PercentRelativeLayout implements DividerView {
    @BindView
    View bottomSpace;
    @BindView
    AirTextView callToActionView;
    @BindView
    View clickOverlay;
    @BindView
    View divider;
    @BindView
    AirImageView imageView;
    @BindView
    AirTextView marketingText;
    private CharSequence subtitleText;
    private CharSequence titleText;

    public MarketingCard(Context context) {
        super(context);
        init(null);
    }

    public MarketingCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MarketingCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_marketing_card, this);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_MarketingCard);
        Drawable image = a.getDrawable(R.styleable.n2_MarketingCard_n2_image);
        String title = a.getString(R.styleable.n2_MarketingCard_n2_titleText);
        String subtitle = a.getString(R.styleable.n2_MarketingCard_n2_subtitleText);
        String callToAction = a.getString(R.styleable.n2_MarketingCard_n2_actionText);
        boolean showBottomSpace = a.getBoolean(R.styleable.n2_MarketingCard_n2_showBottomSpace, true);
        boolean showDivider = a.getBoolean(R.styleable.n2_MarketingCard_n2_showDivider, true);
        setImage(image);
        setTitle((CharSequence) title);
        setSubtitle((CharSequence) subtitle);
        setAction((CharSequence) callToAction);
        showBottomSpace(showBottomSpace);
        showDivider(showDivider);
        a.recycle();
    }

    public void setImage(int drawableRes) {
        setImage(ContextCompat.getDrawable(getContext(), drawableRes));
    }

    public void setImage(Drawable image) {
        this.imageView.setImageDrawable(image);
    }

    public void setImageUrl(String imageUrl) {
        this.imageView.setImageUrl(imageUrl);
    }

    public void setTitle(int textRes) {
        setTitle((CharSequence) getContext().getString(textRes));
    }

    public void setTitle(CharSequence text) {
        this.titleText = text;
        updateMarketingText();
    }

    public void setSubtitle(int textRes) {
        setSubtitle((CharSequence) getContext().getString(textRes));
    }

    public void setSubtitle(CharSequence text) {
        this.subtitleText = text;
        updateMarketingText();
    }

    private void updateMarketingText() {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        if (!TextUtils.isEmpty(this.titleText)) {
            builder.append(TextUtil.makeCircularBold(getContext(), this.titleText));
        }
        if (builder.length() > 0) {
            builder.append("  ");
        }
        if (!TextUtils.isEmpty(this.subtitleText)) {
            builder.append(this.subtitleText);
        }
        this.marketingText.setText(builder);
    }

    public void setAction(int textRes) {
        setAction((CharSequence) getContext().getString(textRes));
    }

    public void setAction(CharSequence text) {
        this.callToActionView.setText(text);
    }

    public void setActionOnClickListener(OnClickListener listener) {
        this.callToActionView.setOnClickListener(listener);
    }

    public void showBottomSpace(boolean showBottomSpace) {
        ViewLibUtils.setVisibleIf(this.bottomSpace, showBottomSpace);
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public void setOnClickListener(OnClickListener l) {
        this.clickOverlay.setOnClickListener(l);
    }

    public static void mock(MarketingCard card) {
        card.setImageUrl("https://a0.muscache.com/im/pictures/40527001/725cf38d_original.jpg?aki_policy=x_large");
        card.setTitle((CharSequence) "Primary title");
        card.setSubtitle((CharSequence) "Subtitle caption can have two lines of text with wrapping");
        card.setAction((CharSequence) "Call to action");
    }

    public static void mockWithLongTitle(MarketingCard card) {
        card.setImageUrl("https://a0.muscache.com/im/pictures/40527001/725cf38d_original.jpg?aki_policy=x_large");
        card.setTitle((CharSequence) "Primary title can have two lines of text with wrapping");
        card.setSubtitle((CharSequence) "Subtitle caption can have two lines of text with wrapping");
        card.setAction((CharSequence) "Call to action");
    }

    public static void mockWithLongCTA(MarketingCard card) {
        card.setImageUrl("https://a0.muscache.com/im/pictures/40527001/725cf38d_original.jpg?aki_policy=x_large");
        card.setTitle((CharSequence) "Primary title can have two lines of text with wrapping");
        card.setSubtitle((CharSequence) "Subtitle caption can have two lines of text with wrapping");
        card.setAction((CharSequence) "Call to action that is much longer than two lines and should ellipsize");
    }
}
