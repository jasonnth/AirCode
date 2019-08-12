package com.airbnb.p027n2.components;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.PromotionMarquee */
public class PromotionMarquee extends LinearLayout {
    @BindView
    AirTextView bannerTextView;
    @BindView
    AirTextView capitionTextView;
    @BindView
    AirTextView titleTextView;

    public PromotionMarquee(Context context) {
        this(context, null);
    }

    public PromotionMarquee(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PromotionMarquee(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.n2_promotion_marquee, this);
        setOrientation(1);
        ButterKnife.bind((View) this);
    }

    public void setBannerText(int stringRes) {
        setBannerText((CharSequence) getResources().getString(stringRes));
    }

    public void setBannerText(CharSequence bannerText) {
        ViewLibUtils.setVisibleIf(this.bannerTextView, !TextUtils.isEmpty(bannerText));
        this.bannerTextView.setText(bannerText);
    }

    public void setTitleText(int stringRes) {
        this.titleTextView.setText(stringRes);
    }

    public void setTitleText(CharSequence buttonText) {
        this.titleTextView.setText(buttonText);
    }

    public void setCaptionText(int stringRes) {
        this.capitionTextView.setText(stringRes);
    }

    public void setCaptionText(CharSequence buttonText) {
        this.capitionTextView.setText(buttonText);
    }

    public static void mock(PromotionMarquee view) {
        view.setBannerText((CharSequence) "Spotlight");
        view.setTitleText((CharSequence) "Congrats, Brian!");
        view.setCaptionText((CharSequence) "You are invited to join an exclusive pilot that specialize high rated places like yours.");
    }
}
