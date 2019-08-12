package com.airbnb.android.listing.utils;

import android.content.Context;
import android.os.Bundle;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.models.CheckInTimeOption;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingCheckInTimeOptions;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.viewcomponents.models.InlineInputRowEpoxyModel_;
import com.airbnb.android.listing.C7213R;
import com.airbnb.android.utils.NumberUtils;
import com.airbnb.android.utils.Strap;
import com.google.common.base.Objects;
import icepick.State;

public class CheckInOutSettingsHelper {
    @State
    CheckInTimeOption checkInEndTime;
    private InlineInputRowEpoxyModel_ checkInEndTimeInput;
    @State
    CheckInTimeOption checkInStartTime;
    private InlineInputRowEpoxyModel_ checkInStartTimeInput;
    @State
    ListingCheckInTimeOptions checkInTimeOptions;
    @State
    CheckInTimeOption checkOutTime;
    private InlineInputRowEpoxyModel_ checkOutTimeInput;
    private final Listener listener;

    public interface Listener {
        void modelsUpdated();
    }

    public CheckInOutSettingsHelper(Context context, Listing listing, Listener listener2, ListingCheckInTimeOptions checkInTimeOptions2, Bundle savedInstanceState) {
        this.listener = listener2;
        this.checkInTimeOptions = checkInTimeOptions2;
        if (savedInstanceState == null) {
            this.checkInStartTime = CheckInOutUtils.getTimeOptionForHour(listing.getCheckInTimeStart(), checkInTimeOptions2.getValidCheckInTimeStartOptions());
            this.checkInEndTime = CheckInOutUtils.getTimeOptionForHour(listing.getCheckInTimeEnd(), checkInTimeOptions2.getValidCheckInTimeEndOptions());
            this.checkOutTime = CheckInOutUtils.getTimeOptionForHour(CheckInOutUtils.getFormattedHourIfNotNull(listing.getCheckOutTime()), checkInTimeOptions2.getValidCheckOutTimeOptions());
        } else {
            onRestoreInstanceState(savedInstanceState);
        }
        this.checkInStartTimeInput = new InlineInputRowEpoxyModel_().titleRes(C7213R.string.manage_listing_check_in_out_arrive_after_input_label).hintRes(C7213R.string.manage_listing_check_in_out_time_empty_hint).input(this.checkInStartTime.getLocalizedHour()).autoHideTipOnInputChange(true).clickListener(CheckInOutSettingsHelper$$Lambda$1.lambdaFactory$(this, context, checkInTimeOptions2, listener2)).show();
        this.checkInEndTimeInput = new InlineInputRowEpoxyModel_().titleRes(C7213R.string.manage_listing_check_in_out_arrive_before_input_label).hintRes(C7213R.string.manage_listing_check_in_out_time_empty_hint).input(this.checkInEndTime.getLocalizedHour()).autoHideTipOnInputChange(true).enabled(!this.checkInStartTime.isFlexibleTime()).clickListener(CheckInOutSettingsHelper$$Lambda$2.lambdaFactory$(this, checkInTimeOptions2, context, listener2)).show();
        this.checkOutTimeInput = new InlineInputRowEpoxyModel_().titleRes(C7213R.string.manage_listing_check_in_out_leave_before_input_label).hintRes(C7213R.string.manage_listing_check_in_out_time_empty_hint).input(this.checkOutTime.getLocalizedHour()).autoHideTipOnInputChange(true).clickListener(CheckInOutSettingsHelper$$Lambda$3.lambdaFactory$(this, context, checkInTimeOptions2, listener2));
    }

    static /* synthetic */ void lambda$null$0(CheckInOutSettingsHelper checkInOutSettingsHelper, Listener listener2, CheckInTimeOption startTimeSelected) {
        checkInOutSettingsHelper.checkInStartTime = startTimeSelected;
        checkInOutSettingsHelper.checkInStartTimeInput.input(startTimeSelected.getLocalizedHour());
        listener2.modelsUpdated();
        checkInOutSettingsHelper.validateCheckInEndTimeForNewStartTime();
    }

    static /* synthetic */ boolean lambda$null$2(CheckInOutSettingsHelper checkInOutSettingsHelper, CheckInTimeOption endTimeOption) {
        return endTimeOption != null && CheckInOutUtils.timeRangeMeetsMinimum(checkInOutSettingsHelper.checkInStartTime, endTimeOption);
    }

    static /* synthetic */ void lambda$null$3(CheckInOutSettingsHelper checkInOutSettingsHelper, Listener listener2, CheckInTimeOption endTimeSelected) {
        checkInOutSettingsHelper.checkInEndTime = endTimeSelected;
        checkInOutSettingsHelper.checkInEndTimeInput.input(endTimeSelected.getLocalizedHour());
        listener2.modelsUpdated();
    }

    static /* synthetic */ void lambda$null$5(CheckInOutSettingsHelper checkInOutSettingsHelper, Listener listener2, CheckInTimeOption endTimeSelected) {
        checkInOutSettingsHelper.checkOutTime = endTimeSelected;
        checkInOutSettingsHelper.checkOutTimeInput.input(endTimeSelected.getLocalizedHour());
        listener2.modelsUpdated();
    }

    public InlineInputRowEpoxyModel_ getCheckInStartTimeInput() {
        return this.checkInStartTimeInput;
    }

    public InlineInputRowEpoxyModel_ getCheckInEndTimeInput() {
        return this.checkInEndTimeInput;
    }

    public InlineInputRowEpoxyModel_ getCheckOutTimeInput() {
        return this.checkOutTimeInput;
    }

    public void setEnabled(boolean enabled) {
        this.checkInStartTimeInput.enabled(enabled);
        this.checkInEndTimeInput.enabled(enabled);
        this.checkOutTimeInput.enabled(enabled);
    }

    private void validateCheckInEndTimeForNewStartTime() {
        if (!CheckInOutUtils.isValidTimeRange(this.checkInStartTime, this.checkInEndTime)) {
            this.checkInEndTime = CheckInOutUtils.getTimeOptionForHour(CheckInTimeOption.HOUR_FLEXIBLE, this.checkInTimeOptions.getValidCheckInTimeEndOptions());
            this.checkInEndTimeInput.input(this.checkInEndTime.getLocalizedHour());
        }
        this.checkInEndTimeInput.enabled(!this.checkInStartTime.isFlexibleTime());
        this.listener.modelsUpdated();
    }

    public Strap getCheckInSettings() {
        Strap strap = Strap.make();
        if (this.checkInStartTime.getFormattedHour() != null) {
            strap.mo11639kv(ListingRequestConstants.JSON_CHECK_IN_TIME_START, this.checkInStartTime.getFormattedHour());
        }
        if (this.checkInEndTime.getFormattedHour() != null) {
            strap.mo11639kv(ListingRequestConstants.JSON_CHECK_IN_TIME_END, this.checkInEndTime.getFormattedHour());
        }
        int checkOutTimeValue = NumberUtils.tryParseInt(this.checkOutTime.getFormattedHour(), -1);
        if (checkOutTimeValue != -1) {
            strap.mo11637kv(ListingRequestConstants.JSON_CHECK_OUT_TIME, checkOutTimeValue);
        }
        return strap;
    }

    public void onSaveInstanceState(Bundle outState) {
        IcepickWrapper.saveInstanceState(this, outState);
    }

    public void onRestoreInstanceState(Bundle inState) {
        IcepickWrapper.restoreInstanceState(this, inState);
    }

    public boolean hasChanged(Listing listing) {
        return !Objects.equal(this.checkInStartTime.getFormattedHour(), listing.getCheckInTimeStart()) || !Objects.equal(this.checkInEndTime.getFormattedHour(), listing.getCheckInTimeEnd()) || !Objects.equal(this.checkOutTime.getFormattedHour(), CheckInOutUtils.getFormattedHourIfNotNull(listing.getCheckOutTime()));
    }
}
