package com.airbnb.android.listyourspacedls.adapters;

import com.airbnb.android.core.enums.PropertyType;
import com.airbnb.android.core.views.OptionsMenuFactory.Listener;

final /* synthetic */ class SpaceTypeAdapter$$Lambda$3 implements Listener {
    private final SpaceTypeAdapter arg$1;

    private SpaceTypeAdapter$$Lambda$3(SpaceTypeAdapter spaceTypeAdapter) {
        this.arg$1 = spaceTypeAdapter;
    }

    public static Listener lambdaFactory$(SpaceTypeAdapter spaceTypeAdapter) {
        return new SpaceTypeAdapter$$Lambda$3(spaceTypeAdapter);
    }

    public void itemSelected(Object obj) {
        SpaceTypeAdapter.lambda$propertyTypeClicked$2(this.arg$1, (PropertyType) obj);
    }
}
