package com.airbnb.android.core.fragments.datepicker;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.ZenDialog.ZenBuilder;

public class DatePickerDialog extends ZenDialog {
    public static final String ARG_DATE = "date";
    private static final String ARG_FOR_BIRTH_DATE = "for_birth_date";
    private static final String ARG_MAX_DATE = "max_date";
    private static final String ARG_MIN_DATE = "min_date";
    public static final int DATE_PICKER_CANCEL = 2001;
    public static final int DATE_PICKER_OK = 2002;
    private static final int DEFAULT_AGE = 21;
    private static final int MIN_AGE = 18;
    @BindView
    DatePicker mDatePicker;

    public static DatePickerDialog newInstance(AirDate initialDate, boolean forBirthDate, Fragment targetFragment, int dialogTitleResource) {
        return newInstance(initialDate, forBirthDate, targetFragment, dialogTitleResource, null, null);
    }

    public static DatePickerDialog newInstance(AirDate initialDate, boolean forBirthDate, Fragment targetFragment, int dialogTitleResource, int requestCodeOk) {
        return newInstance(initialDate, forBirthDate, targetFragment, dialogTitleResource, null, null, requestCodeOk);
    }

    public static DatePickerDialog newInstance(AirDate initialDate, boolean forBirthDate, Fragment targetFragment, int dialogTitleResource, AirDate minDate, AirDate maxDate) {
        return newInstance(initialDate, forBirthDate, targetFragment, dialogTitleResource, minDate, maxDate, DATE_PICKER_OK);
    }

    public static DatePickerDialog newInstance(AirDate initialDate, boolean forBirthDate, Fragment targetFragment, int dialogTitleResource, AirDate minDate, AirDate maxDate, int requestCodeOk) {
        if (dialogTitleResource == 0) {
            dialogTitleResource = C0716R.string.select_date;
        }
        DatePickerDialog dialog = (DatePickerDialog) new ZenBuilder(new DatePickerDialog()).withTitle(dialogTitleResource).withCustomLayout().withDualButton(C0716R.string.cancel, (int) DATE_PICKER_CANCEL, C0716R.string.okay, requestCodeOk, targetFragment).create();
        Bundle args = dialog.getArguments();
        args.putParcelable("date", initialDate);
        args.putParcelable(ARG_MIN_DATE, minDate);
        args.putParcelable(ARG_MAX_DATE, maxDate);
        args.putBoolean(ARG_FOR_BIRTH_DATE, forBirthDate);
        return dialog;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        setCustomView(inflater.inflate(C0716R.layout.dialog_fragment_date_picker, container, false));
        ButterKnife.bind(this, view);
        AirDate initialDate = (AirDate) getArguments().getParcelable("date");
        if (initialDate == null) {
            initialDate = AirDate.today();
        }
        this.mDatePicker.init(initialDate.getYear(), initialDate.getMonthOfYear() - 1, initialDate.getDayOfMonth(), null);
        AirDate minDate = (AirDate) getArguments().getParcelable(ARG_MIN_DATE);
        if (minDate != null) {
            this.mDatePicker.setMinDate(minDate.getTimeInMillisAtStartOfDay());
        }
        AirDate maxDate = (AirDate) getArguments().getParcelable(ARG_MAX_DATE);
        if (maxDate != null) {
            this.mDatePicker.setMaxDate(maxDate.getTimeInMillisAtStartOfDay());
        }
        applyBirthDateYearRestriction();
        return view;
    }

    public AirDate getDatePickerDateAsAirDate() {
        return new AirDate(this.mDatePicker.getYear(), this.mDatePicker.getMonth() + 1, this.mDatePicker.getDayOfMonth());
    }

    private void applyBirthDateYearRestriction() {
        if (getArguments().getBoolean(ARG_FOR_BIRTH_DATE, false)) {
            this.mDatePicker.setMaxDate(getMinQualifiedBirthdate().getTimeInMillisAtStartOfDay());
        }
    }

    public static AirDate getMinQualifiedBirthdate() {
        return AirDate.today().plusYears(-18);
    }

    public static AirDate getDefaultBirthdate() {
        return AirDate.today().plusYears(-21);
    }

    /* access modifiers changed from: protected */
    public void clickRightButton(int requestCodeRight) {
        Intent data = new Intent();
        data.putExtra("date", getDatePickerDateAsAirDate());
        sendActivityResult(requestCodeRight, -1, data);
    }
}
