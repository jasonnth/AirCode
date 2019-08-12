package com.airbnb.android.lib.fragments.unlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.fragments.datepicker.DatePickerDialog;
import com.airbnb.android.core.models.SnoozeMode;
import com.airbnb.android.lib.C0880R;
import icepick.State;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class SnoozeModeFragment extends BaseSnoozeListingFragment {
    private SimpleDateFormat dateDisplayFormat;
    @State
    AirDate endDate;
    @State
    AirDate maxDate;
    @State
    AirDate minDate;
    @BindView
    TextView snoozeEndDatePickerButton;
    @BindView
    TextView snoozeStartDatePickerButton;
    @State
    AirDate startDate;

    public static SnoozeModeFragment newInstance() {
        return new SnoozeModeFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            if (this.listing.isSnoozed()) {
                SnoozeMode snoozeMode = this.listing.getSnoozeMode();
                this.startDate = snoozeMode.getStartDate();
                this.endDate = snoozeMode.getEndDate();
            } else {
                this.startDate = AirDate.today();
                this.endDate = AirDate.today().plusWeeks(1);
            }
            this.minDate = AirDate.today();
            this.maxDate = AirDate.today().plusMonths(6).plusDays(1);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_snooze_mode, container, false);
        bindViews(view);
        initializeFormattedDateViews();
        return view;
    }

    private void initializeFormattedDateViews() {
        this.dateDisplayFormat = new SimpleDateFormat(getResources().getString(C0880R.string.mdy_format_full));
        setFormattedDateForTextView(C0880R.string.snooze_start, this.snoozeStartDatePickerButton, this.startDate);
        setFormattedDateForTextView(C0880R.string.snooze_end, this.snoozeEndDatePickerButton, this.endDate);
        this.snoozeStartDatePickerButton.setSelected(false);
        this.snoozeEndDatePickerButton.setSelected(false);
    }

    private void setFormattedDateForTextView(int resourceId, TextView textView, AirDate date) {
        textView.setText(String.format(getResources().getString(resourceId), new Object[]{date.formatDate((DateFormat) this.dateDisplayFormat)}));
    }

    /* access modifiers changed from: protected */
    public int getTitle() {
        return C0880R.string.set_snooze_dates_title;
    }

    @OnClick
    public void onClickSnoozeStartDateButton() {
        this.snoozeStartDatePickerButton.setSelected(true);
        this.snoozeEndDatePickerButton.setSelected(false);
        DatePickerDialog.newInstance(this.startDate, false, this, C0880R.string.select_snooze_start_date, this.minDate, this.maxDate).show(getFragmentManager(), (String) null);
    }

    @OnClick
    public void onClickSnoozeEndDateButton() {
        this.snoozeStartDatePickerButton.setSelected(false);
        this.snoozeEndDatePickerButton.setSelected(true);
        DatePickerDialog.newInstance(this.endDate, false, this, C0880R.string.select_snooze_end_date, this.minDate, this.maxDate).show(getFragmentManager(), (String) null);
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void snoozeListing() {
        super.snoozeListing(this.startDate, this.endDate);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1 && requestCode == 2002) {
            if (this.snoozeStartDatePickerButton.isSelected()) {
                this.startDate = (AirDate) data.getParcelableExtra("date");
                setFormattedDateForTextView(C0880R.string.snooze_start, this.snoozeStartDatePickerButton, this.startDate);
            } else {
                this.endDate = (AirDate) data.getParcelableExtra("date");
                setFormattedDateForTextView(C0880R.string.snooze_end, this.snoozeEndDatePickerButton, this.endDate);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
