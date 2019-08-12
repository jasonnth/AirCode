package com.airbnb.android.booking.p336n2;

import android.content.Context;
import com.airbnb.android.core.models.CheckinTimeSelectionOptions;
import com.airbnb.android.core.utils.Check;
import com.airbnb.p027n2.collections.BaseSelectionView.SelectionViewItemPresenter;
import com.google.common.collect.FluentIterable;
import java.util.List;

/* renamed from: com.airbnb.android.booking.n2.ArrivalTimeSelectionViewItem */
public class ArrivalTimeSelectionViewItem implements SelectionViewItemPresenter {
    private final CheckinTimeSelectionOptions checkInTime;

    static /* synthetic */ ArrivalTimeSelectionViewItem access$lambda$0(CheckinTimeSelectionOptions checkinTimeSelectionOptions) {
        return new ArrivalTimeSelectionViewItem(checkinTimeSelectionOptions);
    }

    private ArrivalTimeSelectionViewItem(CheckinTimeSelectionOptions checkInTime2) {
        this.checkInTime = (CheckinTimeSelectionOptions) Check.notNull(checkInTime2);
    }

    public String getDisplayText(Context context) {
        return this.checkInTime.getLocalizedGuestCheckinWindow();
    }

    public boolean equals(Object o) {
        if (o != null && (o instanceof ArrivalTimeSelectionViewItem)) {
            return ((ArrivalTimeSelectionViewItem) o).checkInTime.getFormattedHour().equalsIgnoreCase(this.checkInTime.getFormattedHour());
        }
        return false;
    }

    public int hashCode() {
        return this.checkInTime.hashCode();
    }

    static List<ArrivalTimeSelectionViewItem> populateOptions(List<CheckinTimeSelectionOptions> checkInOptions) {
        return FluentIterable.from((Iterable<E>) checkInOptions).transform(ArrivalTimeSelectionViewItem$$Lambda$1.lambdaFactory$()).toList();
    }

    public boolean isInstantBookable() {
        return this.checkInTime.isIsCheckInTimeInstantBookable().booleanValue();
    }

    public String getValue() {
        return this.checkInTime.getFormattedHour();
    }
}
