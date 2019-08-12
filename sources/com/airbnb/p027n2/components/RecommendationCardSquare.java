package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import com.airbnb.n2.R;
import com.airbnb.p027n2.components.RecommendationRow.CardType;

/* renamed from: com.airbnb.n2.components.RecommendationCardSquare */
public class RecommendationCardSquare extends RecommendationCard {
    public RecommendationCardSquare(Context context) {
        super(context);
    }

    public RecommendationCardSquare(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecommendationCardSquare(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public int getLayout() {
        return R.layout.n2_recommendation_card_square;
    }

    public static void mock(RecommendationCardSquare card) {
        card.setupTitle("Title", CardType.Medium);
    }
}
