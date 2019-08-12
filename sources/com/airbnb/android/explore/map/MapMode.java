package com.airbnb.android.explore.map;

import com.airbnb.android.core.map.ExploreMapMarkerable;
import com.airbnb.android.core.models.ExploreTab;
import com.airbnb.android.core.models.Mappable;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import java.util.List;

public interface MapMode<T extends ExploreMapMarkerable> {
    T createMarkerable(Mappable mappable);

    String getAssociatedTabId();

    AirEpoxyAdapter getCarouselAdapter();

    List<Mappable> getMappables();

    String getTypeString();

    void initDataAndAddToCarousel(ExploreTab exploreTab);

    void setSelectedPositionOnAdapter(int i);
}
