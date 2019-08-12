package com.airbnb.android.listing.utils;

import android.content.Context;
import android.os.Bundle;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.models.AdvanceNoticeSetting;
import com.airbnb.android.core.models.CalendarRule;
import com.airbnb.android.core.models.MaxDaysNoticeSetting;
import com.airbnb.android.core.models.TurnoverDaysSetting;
import com.airbnb.android.core.requests.CalendarRulesRequest;
import com.airbnb.android.core.requests.constants.CalendarRulesRequestConstants;
import com.airbnb.android.core.viewcomponents.models.InlineInputRowEpoxyModel_;
import icepick.State;
import org.json.JSONException;
import org.json.JSONObject;

public class AvailabilitySettingsHelper {
    private final InlineInputRowEpoxyModel_ advanceNoticeRow;
    private final Context context;
    private final InlineInputRowEpoxyModel_ cutoffTimeRow;
    private final InlineInputRowEpoxyModel_ futureReservationsRow;
    private final boolean instantBookEnabled;
    @State
    CalendarRule newSettings;
    private final InlineInputRowEpoxyModel_ preparationTimeRow;
    private final InlineInputRowEpoxyModel_ requestToBookRow;

    public interface Listener {
        void modelsUpdated();
    }

    public AvailabilitySettingsHelper(Context context2, CalendarRule calendarRule, boolean instantBookEnabled2, Listener listener, Bundle savedInstanceState) {
        this.context = context2;
        this.instantBookEnabled = instantBookEnabled2;
        if (savedInstanceState == null) {
            this.newSettings = new CalendarRule();
            AdvanceNoticeSetting advanceNotice = new AdvanceNoticeSetting();
            advanceNotice.setHours(calendarRule.getAdvanceNotice().getHours());
            advanceNotice.setAllowRequestToBook(calendarRule.getAdvanceNotice().getAllowRequestToBook());
            this.newSettings.setAdvanceNotice(advanceNotice);
            TurnoverDaysSetting turnoverDays = new TurnoverDaysSetting();
            turnoverDays.setDays(calendarRule.getTurnoverDays().getDays());
            this.newSettings.setTurnoverDays(turnoverDays);
            MaxDaysNoticeSetting maxDaysNotice = new MaxDaysNoticeSetting();
            maxDaysNotice.setDays(calendarRule.getMaxDaysNotice().getDays());
            this.newSettings.setMaxDaysNotice(maxDaysNotice);
        } else {
            onRestoreInstanceState(savedInstanceState);
        }
        this.advanceNoticeRow = new InlineInputRowEpoxyModel_().titleRes(AdvanceNoticeDisplay.getDaysTitleRes()).subTitleRes(AdvanceNoticeDisplay.getDaysInfoRes()).clickListener(AvailabilitySettingsHelper$$Lambda$1.lambdaFactory$(this, context2, listener));
        this.requestToBookRow = new InlineInputRowEpoxyModel_().titleRes(AdvanceNoticeDisplay.getRequestToBookTitleRes()).clickListener(AvailabilitySettingsHelper$$Lambda$2.lambdaFactory$(this, context2, listener));
        this.cutoffTimeRow = new InlineInputRowEpoxyModel_().titleRes(AdvanceNoticeDisplay.getCutoffTimeTitleRes()).subTitleRes(AdvanceNoticeDisplay.getCutoffTimeInfoRes()).clickListener(AvailabilitySettingsHelper$$Lambda$3.lambdaFactory$(this, context2, listener));
        this.preparationTimeRow = new InlineInputRowEpoxyModel_().titleRes(PreparationTimeDisplay.getTitleRes()).subTitleRes(PreparationTimeDisplay.getInfoRes()).clickListener(AvailabilitySettingsHelper$$Lambda$4.lambdaFactory$(this, context2, listener));
        this.futureReservationsRow = new InlineInputRowEpoxyModel_().titleRes(FutureReservationsDisplay.getTitleRes()).subTitleRes(FutureReservationsDisplay.getInfoRes()).clickListener(AvailabilitySettingsHelper$$Lambda$5.lambdaFactory$(this, context2, listener));
        updateRows();
    }

    static /* synthetic */ void lambda$null$1(AvailabilitySettingsHelper availabilitySettingsHelper, Listener listener, AdvanceNoticeSetting setting) {
        availabilitySettingsHelper.newSettings.getAdvanceNotice().setHours(setting.getHours());
        availabilitySettingsHelper.updateRows();
        listener.modelsUpdated();
    }

    static /* synthetic */ void lambda$null$4(AvailabilitySettingsHelper availabilitySettingsHelper, Listener listener, Boolean setting) {
        availabilitySettingsHelper.newSettings.getAdvanceNotice().setAllowRtbBoolean(setting.booleanValue());
        availabilitySettingsHelper.updateRows();
        listener.modelsUpdated();
    }

    static /* synthetic */ void lambda$null$7(AvailabilitySettingsHelper availabilitySettingsHelper, Listener listener, AdvanceNoticeSetting setting) {
        availabilitySettingsHelper.newSettings.getAdvanceNotice().setHours(setting.getHours());
        availabilitySettingsHelper.updateRows();
        listener.modelsUpdated();
    }

    static /* synthetic */ void lambda$null$10(AvailabilitySettingsHelper availabilitySettingsHelper, Listener listener, TurnoverDaysSetting setting) {
        availabilitySettingsHelper.newSettings.setTurnoverDays(setting);
        availabilitySettingsHelper.updateRows();
        listener.modelsUpdated();
    }

    static /* synthetic */ void lambda$null$13(AvailabilitySettingsHelper availabilitySettingsHelper, Listener listener, MaxDaysNoticeSetting setting) {
        availabilitySettingsHelper.newSettings.setMaxDaysNotice(setting);
        availabilitySettingsHelper.updateRows();
        listener.modelsUpdated();
    }

    public void setInputEnabled(boolean enabled) {
        this.advanceNoticeRow.enabled(enabled);
        this.requestToBookRow.enabled(enabled);
        this.cutoffTimeRow.enabled(enabled);
        this.preparationTimeRow.enabled(enabled);
        this.futureReservationsRow.enabled(enabled);
    }

    public InlineInputRowEpoxyModel_ getAdvanceNoticeRow() {
        return this.advanceNoticeRow;
    }

    public InlineInputRowEpoxyModel_ getRequestToBookRow() {
        return this.requestToBookRow;
    }

    public InlineInputRowEpoxyModel_ getCutoffTimeRow() {
        return this.cutoffTimeRow;
    }

    public InlineInputRowEpoxyModel_ getPreparationTimeRow() {
        return this.preparationTimeRow;
    }

    public InlineInputRowEpoxyModel_ getFutureReservationsRow() {
        return this.futureReservationsRow;
    }

    public void updateRows() {
        this.advanceNoticeRow.input(AdvanceNoticeDisplay.getDaysLongValue(this.context, this.newSettings.getAdvanceNotice()));
        if (this.newSettings.getAdvanceNotice().isSameDay()) {
            this.cutoffTimeRow.show().input(AdvanceNoticeDisplay.getHoursValue(this.context, this.newSettings.getAdvanceNotice()));
        } else {
            this.cutoffTimeRow.hide();
        }
        if (!this.instantBookEnabled || this.newSettings.getAdvanceNotice().isSameDay()) {
            this.requestToBookRow.hide();
        } else {
            this.requestToBookRow.show().subTitle(AdvanceNoticeDisplay.getRequestToBookInfo(this.context, this.newSettings.getAdvanceNotice())).input(AdvanceNoticeDisplay.getRequestToBookValue(this.context, this.newSettings.getAdvanceNotice()));
        }
        this.preparationTimeRow.input(PreparationTimeDisplay.getLongValue(this.context, this.newSettings.getTurnoverDays()));
        this.futureReservationsRow.input(FutureReservationsDisplay.getLongValue(this.context, this.newSettings.getMaxDaysNotice()));
    }

    public void onSaveInstanceState(Bundle outState) {
        IcepickWrapper.saveInstanceState(this, outState);
    }

    public void onRestoreInstanceState(Bundle inState) {
        IcepickWrapper.restoreInstanceState(this, inState);
    }

    public CalendarRule getNewSettings() {
        return this.newSettings;
    }

    public JSONObject getCalendarRulesSettings(JSONObject requestHash) {
        try {
            requestHash.put(CalendarRulesRequestConstants.BOOKING_LEAD_TIME, CalendarRulesRequest.getAdvanceNoticeHash(this.newSettings.getAdvanceNotice().getHours(), this.newSettings.getAdvanceNotice().getAllowRequestToBook()));
            requestHash.put("turnover_days", CalendarRulesRequest.getPreparationTimeHash(this.newSettings.getTurnoverDays().getDays()));
            requestHash.put(CalendarRulesRequestConstants.MAX_DAYS_NOTICE, CalendarRulesRequest.getFutureReservationsHash(this.newSettings.getMaxDaysNotice().getDays()));
        } catch (JSONException e) {
            BugsnagWrapper.throwOrNotify(new RuntimeException("Error constructing JSON for calendar_rules update", e));
        }
        return requestHash;
    }

    public boolean hasChanged(CalendarRule calendarRule) {
        return (this.newSettings.getAdvanceNotice().getHours() == calendarRule.getAdvanceNotice().getHours() && this.newSettings.getAdvanceNotice().getAllowRequestToBook() == calendarRule.getAdvanceNotice().getAllowRequestToBook() && this.newSettings.getAdvanceNotice().getHours() == calendarRule.getAdvanceNotice().getHours() && this.newSettings.getTurnoverDays().getDays() == calendarRule.getTurnoverDays().getDays() && this.newSettings.getMaxDaysNotice().getDays() == calendarRule.getMaxDaysNotice().getDays()) ? false : true;
    }
}
