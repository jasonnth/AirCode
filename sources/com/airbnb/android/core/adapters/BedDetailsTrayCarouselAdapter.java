package com.airbnb.android.core.adapters;

import com.airbnb.android.core.models.ListingRoom;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.google.common.collect.FluentIterable;
import java.util.List;

public class BedDetailsTrayCarouselAdapter extends AirEpoxyAdapter {
    public BedDetailsTrayCarouselAdapter(List<ListingRoom> rooms) {
        super(false);
        this.models.addAll(FluentIterable.from((Iterable<E>) rooms).transform(BedDetailsTrayCarouselAdapter$$Lambda$1.lambdaFactory$()).toList());
    }
}
