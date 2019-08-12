package com.airbnb.android.itinerary.viewmodels;

import com.airbnb.android.itinerary.C5755R;
import com.airbnb.android.itinerary.data.models.FreeTimeItem;
import com.airbnb.android.itinerary.utils.ItineraryUtils;
import com.airbnb.android.itinerary.views.ItineraryItemView;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.p027n2.components.RecommendationRow.Recommendation;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import java.util.Collection;
import java.util.List;

public abstract class SuggestionsEpoxyModel extends AirEpoxyModel<ItineraryItemView> {
    FreeTimeItem freeTimeItem;
    List<List<Recommendation>> recommendations;

    public void bind(ItineraryItemView view) {
        super.bind(view);
        if (!ListUtils.isEmpty((Collection<?>) this.recommendations)) {
            view.setSubheader(view.getContext().getString(C5755R.string.itinerary_suggestions_title), true);
            view.setSuggestions((List) this.recommendations.get(0), this.recommendations.size() > 1 ? (List) this.recommendations.get(1) : null);
            view.setTimelineLineColorAndIndicator(ItineraryUtils.isDuringOrUpcoming(this.freeTimeItem, false), false);
            return;
        }
        view.setVisibility(8);
    }
}
