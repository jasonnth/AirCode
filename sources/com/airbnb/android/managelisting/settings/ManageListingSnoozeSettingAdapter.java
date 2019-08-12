package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.SnoozeMode;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.RangeDisplayEpoxyModel_;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.epoxy.EpoxyModel;
import com.google.common.base.Objects;
import icepick.State;

public class ManageListingSnoozeSettingAdapter extends AirEpoxyAdapter {
    private final RangeDisplayEpoxyModel_ dateRangeRow;
    @State
    AirDate endDate;
    private final Listener listener;
    @State
    AirDate startDate;

    public interface Listener {
        void onDateRangeSelected(AirDate airDate, AirDate airDate2);
    }

    public ManageListingSnoozeSettingAdapter(SnoozeMode snoozeMode, Listener listener2, Bundle savedInstanceState) {
        AirDate airDate;
        AirDate airDate2 = null;
        onRestoreInstanceState(savedInstanceState);
        this.listener = listener2;
        if (savedInstanceState == null) {
            if (snoozeMode != null) {
                airDate = snoozeMode.getStartDate();
            } else {
                airDate = null;
            }
            this.startDate = airDate;
            if (snoozeMode != null) {
                airDate2 = snoozeMode.getEndDate();
            }
            this.endDate = airDate2;
        }
        DocumentMarqueeEpoxyModel_ header = new DocumentMarqueeEpoxyModel_().titleRes(C7368R.string.manage_listing_snooze_status_title).captionRes(C7368R.string.manage_listing_snooze_status_subtitle);
        this.dateRangeRow = new RangeDisplayEpoxyModel_().startDate(this.startDate).startTitleHintRes(C7368R.string.manage_listing_snooze_status_date_start_title).startSubTitleHintRes(C7368R.string.manage_listing_snooze_status_date_subtitle).endTitleHintRes(C7368R.string.manage_listing_snooze_status_date_end_title).endSubTitleHintRes(C7368R.string.manage_listing_snooze_status_date_subtitle).endDate(this.endDate).formatWithYear(true).clickListener(ManageListingSnoozeSettingAdapter$$Lambda$1.lambdaFactory$(this));
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{header, this.dateRangeRow});
    }

    public void onSaveInstanceState(Bundle outState) {
        this.startDate = this.dateRangeRow.startDate();
        this.endDate = this.dateRangeRow.endDate();
        super.onSaveInstanceState(outState);
    }

    public boolean hasChanged(SnoozeMode snoozeMode) {
        AirDate originalStartDate;
        AirDate originalEndDate = null;
        if (snoozeMode != null) {
            originalStartDate = snoozeMode.getStartDate();
        } else {
            originalStartDate = null;
        }
        if (snoozeMode != null) {
            originalEndDate = snoozeMode.getEndDate();
        }
        return !Objects.equal(this.dateRangeRow.startDate(), originalStartDate) || !Objects.equal(this.dateRangeRow.endDate(), originalEndDate);
    }

    public void setDateRange(AirDate startDate2, AirDate endDate2) {
        this.dateRangeRow.startDate(startDate2);
        this.dateRangeRow.endDate(endDate2);
        notifyModelChanged(this.dateRangeRow);
    }

    public AirDate getStartDate() {
        return this.dateRangeRow.startDate();
    }

    public AirDate getEndDate() {
        return this.dateRangeRow.endDate();
    }

    public boolean hasSnoozeDates() {
        return (getStartDate() == null || getEndDate() == null) ? false : true;
    }

    public void setEnabled(boolean enabled) {
        this.dateRangeRow.enabled(enabled);
        notifyModelChanged(this.dateRangeRow);
    }

    /* access modifiers changed from: private */
    public void notifyListenerForDateSelected() {
        this.listener.onDateRangeSelected(this.dateRangeRow.startDate(), this.dateRangeRow.endDate());
    }
}
