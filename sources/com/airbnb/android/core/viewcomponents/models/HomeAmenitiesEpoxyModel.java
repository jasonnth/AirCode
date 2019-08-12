package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Amenity;
import com.airbnb.p027n2.components.HomeAmenities;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.airbnb.p027n2.utils.AccessibleDrawableResource;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import java.util.List;

public abstract class HomeAmenitiesEpoxyModel extends AirEpoxyModel<HomeAmenities> {
    List<Amenity> amenities;
    OnClickListener clickListener;
    CharSequence title;
    int titleRes;

    public void bind(HomeAmenities view) {
        super.bind(view);
        ImmutableList<AccessibleDrawableResource> amenityResources = FluentIterable.from((Iterable<E>) this.amenities).filter(HomeAmenitiesEpoxyModel$$Lambda$1.lambdaFactory$()).transform(HomeAmenitiesEpoxyModel$$Lambda$2.lambdaFactory$(view)).toList();
        if (!amenityResources.isEmpty()) {
            view.setItems(amenityResources);
        }
        view.setOnClickListener(this.clickListener);
        if (this.titleRes != 0) {
            view.setTitle(this.titleRes);
        } else {
            view.setTitle(this.title);
        }
    }

    public void unbind(HomeAmenities view) {
        view.setOnClickListener(null);
    }

    public int getDividerViewType() {
        return 0;
    }
}
