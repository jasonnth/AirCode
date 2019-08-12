package com.airbnb.android.core.adapters;

import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.epoxy.EpoxyModel;
import java.util.Collection;
import java.util.List;

public class CarouselAdapter extends AirEpoxyAdapter {
    private static final int LOADING_ITEM_COUNT = 4;

    public CarouselAdapter() {
        enableDiffing();
    }

    public CarouselAdapter(EpoxyModel<?> loadingEpoxyModel) {
        this();
        for (int i = 0; i < 4; i++) {
            this.models.add(loadingEpoxyModel);
        }
    }

    public CarouselAdapter(List<EpoxyModel<?>> models) {
        this();
        setItems(models);
    }

    public void setItems(Collection<? extends EpoxyModel<?>> items) {
        Check.notNull(items, "Items must not be null");
        this.models.clear();
        this.models.addAll(items);
        notifyModelsChanged();
    }
}
