package com.airbnb.android.listyourspacedls.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class SpaceTypeAdapter$$Lambda$1 implements OnClickListener {
    private final SpaceTypeAdapter arg$1;

    private SpaceTypeAdapter$$Lambda$1(SpaceTypeAdapter spaceTypeAdapter) {
        this.arg$1 = spaceTypeAdapter;
    }

    public static OnClickListener lambdaFactory$(SpaceTypeAdapter spaceTypeAdapter) {
        return new SpaceTypeAdapter$$Lambda$1(spaceTypeAdapter);
    }

    public void onClick(View view) {
        this.arg$1.propertyTypeClicked();
    }
}
