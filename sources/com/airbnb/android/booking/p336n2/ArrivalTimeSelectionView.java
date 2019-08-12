package com.airbnb.android.booking.p336n2;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.airbnb.android.core.models.CheckinTimeSelectionOptions;
import com.airbnb.p027n2.collections.BaseSelectionView;
import com.airbnb.p027n2.collections.BaseSelectionView.SelectionViewItemPresenter;
import com.google.common.collect.FluentIterable;
import java.util.List;

/* renamed from: com.airbnb.android.booking.n2.ArrivalTimeSelectionView */
public class ArrivalTimeSelectionView extends BaseSelectionView<ArrivalTimeSelectionViewItem> {
    public ArrivalTimeSelectionView(Context context) {
        this(context, null);
    }

    public ArrivalTimeSelectionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArrivalTimeSelectionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public String getSelectedArrivalTime() {
        if (getSelectedItem() == null) {
            return null;
        }
        return ((ArrivalTimeSelectionViewItem) getSelectedItem()).getValue();
    }

    public void setSelectedArrivalTime(String timeOfTheDay) {
        if (!TextUtils.isEmpty(timeOfTheDay)) {
            setSelectedItem((SelectionViewItemPresenter) FluentIterable.from((Iterable<E>) getItems()).firstMatch(ArrivalTimeSelectionView$$Lambda$1.lambdaFactory$(timeOfTheDay)).orNull());
        }
    }

    public void setCheckInOptions(List<CheckinTimeSelectionOptions> checkinTimeSelectionOptions) {
        setItems(ArrivalTimeSelectionViewItem.populateOptions(checkinTimeSelectionOptions));
    }
}
