package com.airbnb.android.lib.tripassistant.amenities;

import android.content.Context;
import android.util.AttributeSet;
import com.airbnb.android.core.models.HelpThreadAmenity;
import com.airbnb.p027n2.collections.BaseSelectionView;
import com.google.common.collect.FluentIterable;
import java.util.List;

public class HTAmenitiesSelectionView extends BaseSelectionView<HelpThreadAmenitySelectionViewItem> {
    public HTAmenitiesSelectionView(Context context) {
        super(context);
    }

    public HTAmenitiesSelectionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HTAmenitiesSelectionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAmenities(List<HelpThreadAmenity> amenities) {
        setItems((List<T>) FluentIterable.from((Iterable<E>) amenities).transform(HTAmenitiesSelectionView$$Lambda$1.lambdaFactory$()).toList());
    }

    public List<HelpThreadAmenity> getSelectedAmenities() {
        return FluentIterable.from((Iterable<E>) getSelectedItems()).transform(HTAmenitiesSelectionView$$Lambda$2.lambdaFactory$()).toList();
    }
}
