package com.airbnb.android.core.viewcomponents;

import com.airbnb.epoxy.EpoxyModel;
import java.util.List;

public class SimpleAirEpoxyAdapter extends AirEpoxyAdapter {
    public SimpleAirEpoxyAdapter(boolean autoDividerEnabled) {
        super(autoDividerEnabled);
        enableDiffing();
    }

    public void setModels(List<? extends EpoxyModel<?>> modelsToAdd) {
        this.models.clear();
        this.models.addAll(modelsToAdd);
        notifyModelsChanged();
    }
}
