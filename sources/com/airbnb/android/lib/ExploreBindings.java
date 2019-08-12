package com.airbnb.android.lib;

import com.airbnb.android.core.apprater.GraphBindings;
import com.airbnb.android.explore.ExploreComponent.Builder;
import javax.inject.Provider;

public interface ExploreBindings extends GraphBindings {
    Provider<Builder> exploreComponentProvider();
}
