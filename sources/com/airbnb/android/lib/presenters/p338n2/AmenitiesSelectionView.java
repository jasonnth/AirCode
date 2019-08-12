package com.airbnb.android.lib.presenters.p338n2;

import android.content.Context;
import android.util.AttributeSet;
import com.airbnb.android.core.models.Amenity;
import com.airbnb.p027n2.collections.BaseSelectionView;
import com.google.common.collect.FluentIterable;
import java.util.List;

/* renamed from: com.airbnb.android.lib.presenters.n2.AmenitiesSelectionView */
public class AmenitiesSelectionView extends BaseSelectionView<AmenitySelectionViewItem> {
    public AmenitiesSelectionView(Context context) {
        super(context);
    }

    public AmenitiesSelectionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AmenitiesSelectionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initialize(List<Amenity> amenities) {
        setItems((List<T>) FluentIterable.from((Iterable<E>) amenities).transform(AmenitiesSelectionView$$Lambda$1.lambdaFactory$()).toList());
    }
}
