package com.airbnb.p027n2.components;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.components.RecommendationRow.CardType;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.components.RecommendationCard */
public class RecommendationCard extends LinearLayout {
    @BindView
    AirTextView descriptionTextView;
    @BindView
    AirImageView imageView;
    @BindView
    AirTextView subText;
    @BindView
    AirTextView titleTextView;

    public RecommendationCard(Context context) {
        super(context);
        init();
    }

    public RecommendationCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RecommendationCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(1);
        inflate(getContext(), getLayout(), this);
        ButterKnife.bind((View) this);
    }

    public void setImageUrl(String url) {
        this.imageView.setImageUrl(url);
    }

    public void setupTitle(String title, CardType cardType) {
        ViewLibUtils.setVisibleIf(this.titleTextView, !TextUtils.isEmpty(title));
        this.titleTextView.setText(title);
        Paris.style(this.titleTextView).apply(cardType == CardType.Large ? R.style.n2_LargeText_PlusPlus : R.style.n2_SmallText_PlusPlus);
    }

    public void setupSubtext(String subtext, int color) {
        ViewLibUtils.setVisibleIf(this.subText, !TextUtils.isEmpty(subtext));
        this.subText.setText(subtext);
        this.subText.setTextColor(color);
    }

    public void setCardImageHeight(int heightInPx) {
        this.imageView.getLayoutParams().height = heightInPx;
    }

    /* access modifiers changed from: protected */
    public int getLayout() {
        return R.layout.n2_recommendation_card;
    }

    public void setDescriptionText(CharSequence descriptionText) {
        ViewLibUtils.setVisibleIf(this.descriptionTextView, !TextUtils.isEmpty(descriptionText));
        this.descriptionTextView.setText(descriptionText);
    }

    public static void mockMedium(RecommendationCard card) {
        card.setupTitle("No-frills dumplings at Dim Sum Club", CardType.Medium);
        card.setupSubtext("Food & Drink", -65281);
    }

    public static void mockMediumLongSubtext(RecommendationCard card) {
        card.setupTitle("No-frills dumplings at Dim Sum Club", CardType.Medium);
        card.setupSubtext("Food & Drink this is very very very very very very very very long to see if it will truncate properly", -65281);
    }

    public static void mockSmall(RecommendationCard card) {
        card.setupTitle("No-frills dumplings at Dim Sum Club", CardType.Small);
        card.setupSubtext("Food & Drink", -65281);
    }

    public static void mockLarge(RecommendationCard card) {
        card.setupTitle("No-frills dumplings at Dim Sum Club", CardType.Large);
        card.setupSubtext("Food & Drink", -65281);
    }
}
