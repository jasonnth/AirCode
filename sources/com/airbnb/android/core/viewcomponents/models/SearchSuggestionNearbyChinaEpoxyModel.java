package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.views.SearchSuggestionNearbyView;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class SearchSuggestionNearbyChinaEpoxyModel extends AirEpoxyModel<SearchSuggestionNearbyView> {
    OnClickListener anywhereClickListener;
    OnClickListener cityClickListener;
    String cityName;
    OnClickListener nearbyClickListener;
    boolean showNearBy;

    public void bind(SearchSuggestionNearbyView view) {
        super.bind(view);
        view.setNearbyVisible(this.showNearBy);
        view.setCityName(this.cityName);
        view.setNearbyItemClickListener(this.nearbyClickListener);
        view.setCityItemClickListener(this.cityClickListener);
        view.setAnywhereItemClickListener(this.anywhereClickListener);
    }

    public void unbind(SearchSuggestionNearbyView view) {
        super.unbind(view);
        view.setNearbyItemClickListener(null);
        view.setCityItemClickListener(null);
    }
}
