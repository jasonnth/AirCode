package com.airbnb.android.managelisting.settings;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.activities.ModalActivity;
import com.airbnb.android.core.controllers.CalendarViewCallbacks;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.fragments.datepicker.DatesFragment;

public class DatesModalActivity extends ModalActivity implements CalendarViewCallbacks {
    public static final int RESULT_CODE = 223;
    public static final String RESULT_END_DATE_ARG = "end_date_selected";
    public static final String RESULT_START_DATE_ARG = "start_date_selected";
    private AirDate selectedEndDate;
    private AirDate selectedStarDate;

    public static Intent intentForEditDates(Context context, AirDate startDate, AirDate endDate, int startDateTitle, int endDateTitle, int saveButtonText, NavigationTag source) {
        return ModalActivity.intentForFragment(DatesModalActivity.class, context, DatesFragment.forModal(startDate, endDate, startDateTitle, endDateTitle, saveButtonText, source));
    }

    public void onCalendarDatesApplied(AirDate start, AirDate end) {
        if (start != null && end != null) {
            this.selectedStarDate = start;
            this.selectedEndDate = end;
            finish();
        }
    }

    public void onStartDateClicked(AirDate start) {
    }

    public void onEndDateClicked(AirDate end) {
    }

    public void finish() {
        if (this.selectedStarDate == null || this.selectedEndDate == null) {
            setResult(0);
        } else {
            Intent data = new Intent();
            data.putExtra(RESULT_START_DATE_ARG, this.selectedStarDate);
            data.putExtra(RESULT_END_DATE_ARG, this.selectedEndDate);
            setResult(-1, data);
        }
        super.finish();
    }
}
