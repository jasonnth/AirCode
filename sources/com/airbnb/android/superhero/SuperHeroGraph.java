package com.airbnb.android.superhero;

import com.airbnb.android.core.BaseGraph;

public interface SuperHeroGraph extends BaseGraph {
    void inject(SuperHeroAlarmReceiver superHeroAlarmReceiver);

    void inject(SuperHeroThreadFragment superHeroThreadFragment);
}
