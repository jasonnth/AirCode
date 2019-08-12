package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.utils.ViewLibUtils;
import java.util.List;

/* renamed from: com.airbnb.n2.components.RecommendationRow */
public class RecommendationRow extends LinearLayout {
    @BindView
    View bottomSpace;
    @BindView
    LinearLayout cardsContainer;
    @BindView
    View leftSpace;
    @BindViews
    List<RecommendationCard> recommendationCards;
    @BindView
    View rightSpace;

    /* renamed from: com.airbnb.n2.components.RecommendationRow$CardType */
    public enum CardType {
        Small("small", 1.0f, 3, 12),
        Medium("medium", 0.666f, 2, 11),
        Large("large", 0.666f, 1, 0),
        Unknown("", 1.0f, 1, 0);
        
        /* access modifiers changed from: private */
        public final float aspectRatio;
        /* access modifiers changed from: private */
        public final int dividerWidthDp;
        private final String key;
        /* access modifiers changed from: private */
        public final int numCards;

        private CardType(String key2, float aspectRatio2, int numCards2, int dividerWidthDp2) {
            this.key = key2;
            this.aspectRatio = aspectRatio2;
            this.numCards = numCards2;
            this.dividerWidthDp = dividerWidthDp2;
        }

        public static CardType from(String givenKey) {
            CardType[] values;
            for (CardType type : values()) {
                if (type.key.equals(givenKey)) {
                    return type;
                }
            }
            return Unknown;
        }
    }

    /* renamed from: com.airbnb.n2.components.RecommendationRow$Recommendation */
    public static class Recommendation {
        /* access modifiers changed from: private */
        public final CardType cardType;
        /* access modifiers changed from: private */
        public final OnClickListener clickListener;
        /* access modifiers changed from: private */
        public final String description;
        /* access modifiers changed from: private */
        public final int index;
        /* access modifiers changed from: private */
        public final String pictureUrl;
        /* access modifiers changed from: private */
        public final int scrimColor;
        /* access modifiers changed from: private */
        public final String subText;
        /* access modifiers changed from: private */
        public final String text;

        public Recommendation(String text2, String pictureUrl2, String subText2, String description2, int scrimColor2, int index2, OnClickListener clickListener2, CardType cardType2) {
            this.text = text2;
            this.pictureUrl = pictureUrl2;
            this.subText = subText2;
            this.scrimColor = scrimColor2;
            this.clickListener = clickListener2;
            this.index = index2;
            this.cardType = cardType2;
            this.description = description2;
        }
    }

    public RecommendationRow(Context context) {
        super(context);
        init();
    }

    public RecommendationRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RecommendationRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(1);
        inflate(getContext(), R.layout.n2_recommendation_3up, this);
        ButterKnife.bind((View) this);
    }

    public void setup(List<Recommendation> recommendationList) {
        int i = 8;
        if (recommendationList.size() > 3 || recommendationList.size() < 1) {
            throw new IllegalStateException("RecommendationRow does not support anything more than 3up or anything less than 2up");
        }
        int numItems = recommendationList.size();
        this.cardsContainer.setWeightSum((float) numItems);
        for (int i2 = 0; i2 < 3; i2++) {
            RecommendationCard card = (RecommendationCard) this.recommendationCards.get(i2);
            if (i2 > numItems - 1) {
                card.setVisibility(8);
            } else {
                Recommendation recommendation = (Recommendation) recommendationList.get(i2);
                int cardHeight = calculateCardImageHeight(recommendation);
                card.setVisibility(0);
                card.setupTitle(recommendation.text, recommendation.cardType);
                card.setDescriptionText(recommendation.description);
                card.setImageUrl(recommendation.pictureUrl);
                card.setupSubtext(recommendation.subText, recommendation.scrimColor);
                card.setOnClickListener(recommendation.clickListener);
                card.setCardImageHeight(cardHeight);
                setupDividerWidth(recommendation.cardType);
                card.setTag(Integer.valueOf(recommendation.index));
            }
        }
        View view = this.rightSpace;
        if (numItems >= 3) {
            i = 0;
        }
        view.setVisibility(i);
    }

    private void setupDividerWidth(CardType cardType) {
        int widthInPx = ViewLibUtils.dpToPx(getContext(), (float) cardType.dividerWidthDp);
        this.leftSpace.getLayoutParams().width = widthInPx;
        this.rightSpace.getLayoutParams().width = widthInPx;
    }

    private int calculateCardImageHeight(Recommendation recommendation) {
        return (int) (((float) (((ViewLibUtils.getScreenWidth(getContext()) - ((recommendation.cardType.numCards - 1) * ViewLibUtils.dpToPx(getContext(), (float) recommendation.cardType.dividerWidthDp))) - (getContext().getResources().getDimensionPixelOffset(R.dimen.n2_horizontal_padding_medium) * 2)) / recommendation.cardType.numCards)) * recommendation.cardType.aspectRatio);
    }

    public void showBottomSpace(boolean showBottomSpace) {
        ViewLibUtils.setVisibleIf(this.bottomSpace, showBottomSpace);
    }

    public static void mock(RecommendationRow view) {
    }
}
