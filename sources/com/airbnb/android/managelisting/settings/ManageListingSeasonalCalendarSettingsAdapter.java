package com.airbnb.android.managelisting.settings;

import android.content.Context;
import android.os.Bundle;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.SeasonalMinNightsCalendarSetting;
import com.airbnb.android.core.utils.SanitizeUtils;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.InlineFormattedIntegerInputRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.InlineInputRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.RangeDisplayEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SwitchRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToolbarSpacerEpoxyModel_;
import com.airbnb.android.core.views.OptionsMenuFactory;
import com.airbnb.android.listing.utils.SeasonalSettingsDisplay;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.components.SwitchStyle;
import com.airbnb.p027n2.utils.IntegerNumberFormatHelper;
import com.google.common.base.Objects;
import com.google.common.collect.FluentIterable;
import icepick.State;
import java.util.Arrays;

public class ManageListingSeasonalCalendarSettingsAdapter extends AirEpoxyAdapter {
    @State
    Integer checkInDay;
    private final InlineInputRowEpoxyModel_ checkInDayInputRow;
    private final SwitchRowEpoxyModel_ checkInDaySwitchRow;
    private final Context context;
    @State
    AirDate endDate;
    private final Listener listener;
    private final InlineFormattedIntegerInputRowEpoxyModel_ minNightsInputRow;
    @State
    Integer minimumNights;
    private final RangeDisplayEpoxyModel_ rangeDisplayInputRow;
    @State
    AirDate startDate;

    public interface Listener {
        void onDatesRowSelected(AirDate airDate, AirDate airDate2);

        void onValidInputChanged(boolean z);
    }

    public ManageListingSeasonalCalendarSettingsAdapter(Context context2, SeasonalMinNightsCalendarSetting calendarSetting, Listener listener2, Bundle savedInstanceState) {
        super(true);
        onRestoreInstanceState(savedInstanceState);
        enableDiffing();
        this.context = context2;
        this.listener = listener2;
        if (savedInstanceState == null) {
            this.minimumNights = Integer.valueOf(calendarSetting.getMinNights());
            this.startDate = calendarSetting.getStartDate();
            this.endDate = calendarSetting.getEndDate();
            this.checkInDay = calendarSetting.getStartDayOfWeek();
        }
        ToolbarSpacerEpoxyModel_ toolBarSpaceRow = new ToolbarSpacerEpoxyModel_();
        this.rangeDisplayInputRow = new RangeDisplayEpoxyModel_().clickListener(ManageListingSeasonalCalendarSettingsAdapter$$Lambda$1.lambdaFactory$(this)).formatWithYear(true).startDate(this.startDate).endDate(this.endDate);
        this.minNightsInputRow = new InlineFormattedIntegerInputRowEpoxyModel_().titleRes(C7368R.string.manage_listing_trip_length_min_stay_title).hintRes(C7368R.string.manage_listing_trip_length_input_input_empty_hint).numberFormat(IntegerNumberFormatHelper.forInteger(4)).inputAmount((Integer) SanitizeUtils.defaultIfNull(this.minimumNights, Integer.valueOf(1))).amountChangedListener(ManageListingSeasonalCalendarSettingsAdapter$$Lambda$2.lambdaFactory$(this)).removeHintOnFocusMode(true);
        this.checkInDaySwitchRow = new SwitchRowEpoxyModel_().titleRes(C7368R.string.manage_listing_seasonal_settings_check_in_option_specific_day).style(SwitchStyle.Filled).checked(isCheckInDaySpecified(this.checkInDay)).checkedChangeListener(ManageListingSeasonalCalendarSettingsAdapter$$Lambda$3.lambdaFactory$(this));
        this.checkInDayInputRow = new InlineInputRowEpoxyModel_().titleRes(C7368R.string.manage_listing_seasonal_settings_check_in_input_title).inputRes(SeasonalSettingsDisplay.getDayOfWeekOptionsFromIndex(this.checkInDay)).clickListener(ManageListingSeasonalCalendarSettingsAdapter$$Lambda$4.lambdaFactory$(this)).show(isCheckInDaySpecified(this.checkInDay));
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{toolBarSpaceRow, this.rangeDisplayInputRow, this.minNightsInputRow, this.checkInDaySwitchRow, this.checkInDayInputRow});
    }

    public void onSaveInstanceState(Bundle outState) {
        this.minimumNights = this.minNightsInputRow.inputAmount();
        this.startDate = getSelectedStartDate();
        this.endDate = getSelectedEndDate();
        super.onSaveInstanceState(outState);
    }

    public boolean hasChanged(SeasonalMinNightsCalendarSetting setting) {
        return !Objects.equal(this.minNightsInputRow.inputAmount(), Integer.valueOf(setting.getMinNights())) || !Objects.equal(getSelectedStartDate(), setting.getStartDate()) || !Objects.equal(getSelectedEndDate(), setting.getEndDate()) || !Objects.equal(this.checkInDay, setting.getStartDayOfWeek());
    }

    public void setEnabled(boolean enabled) {
        this.minNightsInputRow.enabled(enabled);
        this.checkInDayInputRow.enabled(enabled);
        this.checkInDaySwitchRow.enabled(enabled);
        this.rangeDisplayInputRow.enabled(enabled);
        notifyModelsChanged();
    }

    public void setDateRange(AirDate startDate2, AirDate endDate2) {
        this.rangeDisplayInputRow.startDate(startDate2);
        this.rangeDisplayInputRow.endDate(endDate2);
        notifyModelChanged(this.rangeDisplayInputRow);
        if (this.checkInDayInputRow.isShown()) {
            resetCheckInDayIfOutOfBounds();
        }
    }

    public SeasonalMinNightsCalendarSetting getSeasonalSetting() {
        SeasonalMinNightsCalendarSetting setting = new SeasonalMinNightsCalendarSetting();
        setting.setStartDate(getSelectedStartDate());
        setting.setEndDate(getSelectedEndDate());
        setting.setMinNights(((Integer) SanitizeUtils.defaultIfNull(this.minNightsInputRow.inputAmount(), Integer.valueOf(1))).intValue());
        setting.setStartDayOfWeek((Integer) SanitizeUtils.defaultIfNull(this.checkInDay, Integer.valueOf(-1)));
        return setting;
    }

    private AirDate getSelectedStartDate() {
        return this.rangeDisplayInputRow.startDate();
    }

    private AirDate getSelectedEndDate() {
        return this.rangeDisplayInputRow.endDate();
    }

    private void resetCheckInDayIfOutOfBounds() {
        if (!isDayOfWeekInRange(this.checkInDay)) {
            setCheckIndayIndex(getSelectedStartDate().getDayOfWeek().getDayIndexFromSunday());
        }
    }

    /* access modifiers changed from: private */
    public void onSpecifiedDayCheckedChanged(boolean isChecked) {
        if (isChecked) {
            resetCheckInDayIfOutOfBounds();
        } else {
            this.checkInDay = Integer.valueOf(-1);
        }
        this.checkInDayInputRow.show(isChecked);
        notifyModelsChanged();
    }

    /* access modifiers changed from: private */
    public void showCheckInDayOptions() {
        OptionsMenuFactory.forItems(this.context, FluentIterable.from((Iterable<E>) Arrays.asList(ListUtils.range(0, 6))).filter(ManageListingSeasonalCalendarSettingsAdapter$$Lambda$5.lambdaFactory$(this)).toList()).setTitleResTransformer(ManageListingSeasonalCalendarSettingsAdapter$$Lambda$6.lambdaFactory$()).setListener(ManageListingSeasonalCalendarSettingsAdapter$$Lambda$7.lambdaFactory$(this)).buildAndShow();
    }

    /* access modifiers changed from: private */
    public void setCheckIndayIndex(int dayIndex) {
        this.checkInDay = Integer.valueOf(dayIndex);
        this.checkInDayInputRow.inputRes(SeasonalSettingsDisplay.getDayOfWeekOptionsFromIndex(this.checkInDay));
        notifyModelChanged(this.checkInDayInputRow);
    }

    /* access modifiers changed from: private */
    public void notifyListenerForValidInputChanged() {
        boolean z = true;
        int minNightsCount = SanitizeUtils.zeroIfNull(this.minNightsInputRow.inputAmount());
        Listener listener2 = this.listener;
        if (minNightsCount < 1) {
            z = false;
        }
        listener2.onValidInputChanged(z);
    }

    /* access modifiers changed from: private */
    public void notifyListenerForDateRangeClicked() {
        this.listener.onDatesRowSelected(getSelectedStartDate(), getSelectedEndDate());
    }

    private static boolean isCheckInDaySpecified(Integer checkInday) {
        return (checkInday == null || checkInday.intValue() == -1) ? false : true;
    }

    /* access modifiers changed from: private */
    public boolean isDayOfWeekInRange(Integer dayIndex) {
        boolean includesAllDays;
        boolean splitDates;
        boolean z = true;
        if (!isCheckInDaySpecified(dayIndex)) {
            return false;
        }
        if (getSelectedStartDate().getDaysUntil(getSelectedEndDate()) + 1 >= 7) {
            includesAllDays = true;
        } else {
            includesAllDays = false;
        }
        if (includesAllDays) {
            return true;
        }
        int startDayOfWeek = getSelectedStartDate().getDayOfWeek().getDayIndexFromSunday();
        int endDayOfWeek = getSelectedEndDate().getDayOfWeek().getDayIndexFromSunday();
        if (endDayOfWeek < startDayOfWeek) {
            splitDates = true;
        } else {
            splitDates = false;
        }
        if (!splitDates) {
            if (dayIndex.intValue() < startDayOfWeek || dayIndex.intValue() > endDayOfWeek) {
                z = false;
            }
            return z;
        } else if (dayIndex.intValue() >= startDayOfWeek || dayIndex.intValue() <= endDayOfWeek) {
            return true;
        } else {
            return false;
        }
    }
}
