package com.airbnb.p027n2.components;

import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;

/* renamed from: com.airbnb.n2.components.RecommendationRow_ViewBinding */
public class RecommendationRow_ViewBinding implements Unbinder {
    private RecommendationRow target;

    public RecommendationRow_ViewBinding(RecommendationRow target2, View source) {
        this.target = target2;
        target2.rightSpace = Utils.findRequiredView(source, R.id.right_space, "field 'rightSpace'");
        target2.leftSpace = Utils.findRequiredView(source, R.id.left_space, "field 'leftSpace'");
        target2.cardsContainer = (LinearLayout) Utils.findRequiredViewAsType(source, R.id.cards_container, "field 'cardsContainer'", LinearLayout.class);
        target2.bottomSpace = Utils.findRequiredView(source, R.id.bottom_space, "field 'bottomSpace'");
        target2.recommendationCards = Utils.listOf((RecommendationCard) Utils.findRequiredViewAsType(source, R.id.left_card, "field 'recommendationCards'", RecommendationCard.class), (RecommendationCard) Utils.findRequiredViewAsType(source, R.id.right_card, "field 'recommendationCards'", RecommendationCard.class), (RecommendationCard) Utils.findRequiredViewAsType(source, R.id.center_card, "field 'recommendationCards'", RecommendationCard.class));
    }

    public void unbind() {
        RecommendationRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.rightSpace = null;
        target2.leftSpace = null;
        target2.cardsContainer = null;
        target2.bottomSpace = null;
        target2.recommendationCards = null;
    }
}
