package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.android.core.models.RecommendationItem;
import com.airbnb.p027n2.components.RecommendationRow;
import com.airbnb.p027n2.components.RecommendationRow.CardType;
import com.airbnb.p027n2.components.RecommendationRow.Recommendation;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import java.util.ArrayList;
import java.util.List;

public abstract class RecommendationRowEpoxyModel extends AirEpoxyModel<RecommendationRow> {
    OnClickListener clickListener;
    DisplayOptions displayOptions;
    boolean loading;
    List<RecommendationItem> recommendationItems;
    boolean showBottomSpace = false;

    public void bind(RecommendationRow view) {
        super.bind(view);
        List<Recommendation> recommendations = new ArrayList<>(this.recommendationItems.size());
        for (int i = 0; i < this.recommendationItems.size(); i++) {
            RecommendationItem item = (RecommendationItem) this.recommendationItems.get(i);
            recommendations.add(new Recommendation(item.getTitle(), item.getPicture().getLargeUrl(), item.getSubText(), item.getDescription(), item.getPicture().getDominantSaturatedColor(), i, this.clickListener, CardType.from(item.getSize().getKey())));
        }
        view.showBottomSpace(this.showBottomSpace);
        view.setup(recommendations);
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        if (this.displayOptions != null) {
            return this.displayOptions.getSpanSize(totalSpanCount);
        }
        return super.getSpanSize(totalSpanCount, position, itemCount);
    }
}
