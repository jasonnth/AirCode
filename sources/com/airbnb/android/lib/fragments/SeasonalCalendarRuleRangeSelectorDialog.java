package com.airbnb.android.lib.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.ZenDialog.ZenBuilder;
import com.airbnb.android.core.utils.DateHelper;
import com.airbnb.android.lib.C0880R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SeasonalCalendarRuleRangeSelectorDialog extends ZenDialog {
    private static final String ARG_REQUEST_CODE_OK = "air_request_code_ok";
    public static final String ARG_RESULT_END_DATE = "end_date";
    public static final String ARG_RESULT_RANGE_TYPE = "range_type";
    public static final String ARG_RESULT_START_DATE = "start_date";
    /* access modifiers changed from: private */
    public DateRangeAdapter mAdapter;
    private List<SeasonalCalendarRuleDateRange> mDateRanges;
    private final OnItemClickListener mOnItemClickListener = new OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            int requestCode = SeasonalCalendarRuleRangeSelectorDialog.this.getArguments().getInt(SeasonalCalendarRuleRangeSelectorDialog.ARG_REQUEST_CODE_OK);
            SeasonalCalendarRuleDateRange selectedRange = SeasonalCalendarRuleRangeSelectorDialog.this.mAdapter.getItem(position);
            Intent intent = new Intent().putExtra(SeasonalCalendarRuleRangeSelectorDialog.ARG_RESULT_RANGE_TYPE, selectedRange.getType().ordinal());
            if (selectedRange.getType() == DateRangeType.PredefinedDateRange) {
                PredefinedDateRange dateRange = (PredefinedDateRange) selectedRange;
                intent.putExtra(SeasonalCalendarRuleRangeSelectorDialog.ARG_RESULT_START_DATE, dateRange.getStartDate());
                intent.putExtra(SeasonalCalendarRuleRangeSelectorDialog.ARG_RESULT_END_DATE, dateRange.getEndDate());
            }
            SeasonalCalendarRuleRangeSelectorDialog.this.sendActivityResult(requestCode, -1, intent);
            SeasonalCalendarRuleRangeSelectorDialog.this.dismiss();
        }
    };

    private class CustomDateRange implements SeasonalCalendarRuleDateRange {
        private CustomDateRange() {
        }

        public DateRangeType getType() {
            return DateRangeType.CustomRange;
        }

        public String toString(Context context) {
            return SeasonalCalendarRuleRangeSelectorDialog.this.getString(C0880R.string.seasonal_calendar_setting_specific_dates_option);
        }
    }

    private static class DateRangeAdapter extends ArrayAdapter<SeasonalCalendarRuleDateRange> {
        private final List<SeasonalCalendarRuleDateRange> mDateRanges;

        public DateRangeAdapter(Context context, List<SeasonalCalendarRuleDateRange> ranges) {
            super(context, 17367043);
            this.mDateRanges = ranges;
        }

        public int getCount() {
            return this.mDateRanges.size();
        }

        public SeasonalCalendarRuleDateRange getItem(int position) {
            return (SeasonalCalendarRuleDateRange) this.mDateRanges.get(position);
        }

        public View getView(int position, View v, ViewGroup parent) {
            if (v == null) {
                v = LayoutInflater.from(getContext()).inflate(17367043, parent, false);
            }
            ((TextView) ButterKnife.findById(v, 16908308)).setText(getItem(position).toString(getContext()));
            return v;
        }
    }

    public enum DateRangeType {
        PredefinedDateRange,
        FridaySaturday,
        CustomRange
    }

    private class FridaySaturdayDateRange implements SeasonalCalendarRuleDateRange {
        private FridaySaturdayDateRange() {
        }

        public DateRangeType getType() {
            return DateRangeType.FridaySaturday;
        }

        public String toString(Context context) {
            return SeasonalCalendarRuleRangeSelectorDialog.this.getString(C0880R.string.seasonal_calendar_setting_fridays_and_saturdays);
        }
    }

    private static class PredefinedDateRange implements SeasonalCalendarRuleDateRange {
        private final Date mEndDate;
        private final Date mStartDate;
        private SimpleDateFormat sdf;

        public PredefinedDateRange(Date startDate, Date endDate) {
            this.mStartDate = startDate;
            this.mEndDate = endDate;
        }

        public Date getStartDate() {
            return this.mStartDate;
        }

        public Date getEndDate() {
            return this.mEndDate;
        }

        public String toString(Context context) {
            if (this.sdf == null) {
                this.sdf = new SimpleDateFormat(context.getString(C0880R.string.month_name_short_format));
            }
            return context.getString(C0880R.string.calendar_setting_date_range, new Object[]{this.sdf.format(this.mStartDate), this.sdf.format(this.mEndDate)});
        }

        public DateRangeType getType() {
            return DateRangeType.PredefinedDateRange;
        }
    }

    private interface SeasonalCalendarRuleDateRange {
        DateRangeType getType();

        String toString(Context context);
    }

    private static class StartEndDate {
        public final Calendar endDate;
        public final Calendar startDate;

        public StartEndDate(Calendar startDate2, Calendar endDate2) {
            this.startDate = startDate2;
            this.endDate = endDate2;
        }
    }

    public static SeasonalCalendarRuleRangeSelectorDialog newInstance(int requestCodeOk) {
        Bundle args = new Bundle();
        args.putInt(ARG_REQUEST_CODE_OK, requestCodeOk);
        return (SeasonalCalendarRuleRangeSelectorDialog) new ZenBuilder(new SeasonalCalendarRuleRangeSelectorDialog()).withListView().withoutDividers().withCancelButton().withArguments(args).create();
    }

    /* access modifiers changed from: protected */
    public ListAdapter getListAdapter() {
        if (this.mAdapter == null) {
            this.mAdapter = new DateRangeAdapter(getActivity(), getDateRanges());
        }
        return this.mAdapter;
    }

    /* access modifiers changed from: protected */
    public OnItemClickListener getItemClickListener() {
        return this.mOnItemClickListener;
    }

    private List<SeasonalCalendarRuleDateRange> getDateRanges() {
        if (this.mDateRanges == null) {
            this.mDateRanges = new ArrayList();
            for (StartEndDate startEndDate : getPredefinedDateRanges()) {
                this.mDateRanges.add(new PredefinedDateRange(startEndDate.startDate.getTime(), startEndDate.endDate.getTime()));
            }
            this.mDateRanges.add(new FridaySaturdayDateRange());
            this.mDateRanges.add(new CustomDateRange());
        }
        return this.mDateRanges;
    }

    public static boolean doDatesMatchPredefinedDateRange(Date startDate, Date endDate) {
        for (StartEndDate startEndDate : getPredefinedDateRanges()) {
            if (DateHelper.isSameDay(startEndDate.startDate.getTime(), startDate) && DateHelper.isSameDay(startEndDate.endDate.getTime(), endDate)) {
                return true;
            }
        }
        return false;
    }

    private static List<StartEndDate> getPredefinedDateRanges() {
        List<StartEndDate> datePairs = new ArrayList<>();
        Calendar now = Calendar.getInstance();
        for (int i = 0; i < 4; i++) {
            now.set(5, 1);
            Calendar startDate = (Calendar) now.clone();
            Calendar endDate = (Calendar) now.clone();
            switch (now.get(2)) {
                case 0:
                case 1:
                    break;
                case 2:
                case 3:
                case 4:
                    startDate.set(2, 2);
                    setDateToEndOfMonth(endDate, 4);
                    continue;
                case 5:
                case 6:
                case 7:
                    startDate.set(2, 5);
                    setDateToEndOfMonth(endDate, 7);
                    continue;
                case 8:
                case 9:
                case 10:
                    startDate.set(2, 8);
                    setDateToEndOfMonth(endDate, 10);
                    continue;
                case 11:
                    if (i == 0) {
                        endDate.add(1, 1);
                        break;
                    }
                    break;
            }
            if (i == 0 && now.get(2) != 11) {
                startDate.add(1, -1);
            }
            startDate.set(2, 11);
            setDateToEndOfMonth(endDate, 1);
            datePairs.add(new StartEndDate(startDate, endDate));
            now.add(2, 3);
        }
        return datePairs;
    }

    private static void setDateToEndOfMonth(Calendar date, int month) {
        if (month == 11) {
            date.set(2, month);
            date.add(6, 31);
            return;
        }
        date.set(2, month + 1);
        date.add(6, -1);
    }
}
