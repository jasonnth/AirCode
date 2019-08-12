package com.airbnb.android.lib.identity.psb;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.analytics.BookingAnalytics;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.fragments.datepicker.DatePickerDialog;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.Strap;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import icepick.State;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class SelectIdentificationExpiryDateFragment extends BaseCreateIdentificationFragment {
    @BindView
    View bookingNextButton;
    private SimpleDateFormat dateFormat;
    @BindView
    SheetInputText dateOfExpiryInput;
    @State
    AirDate expiryDate;
    @BindView
    JellyfishView jellyfishView;
    @BindView
    View nextButton;
    @BindView
    SheetMarquee sheetHeader;

    public static Fragment newInstance() {
        return new SelectIdentificationExpiryDateFragment();
    }

    @SuppressLint({"SimpleDateFormat"})
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_select_identification_expiry_date, container, false);
        bindViews(view);
        this.dateFormat = new SimpleDateFormat(getString(C0880R.string.mdy_short_with_full_year_and_space));
        if (savedInstanceState == null) {
            this.expiryDate = this.callback.getDateOfExpiry();
        }
        updateViewsWithDate();
        setStyle(view);
        return view;
    }

    private void setStyle(View view) {
        GuestProfilesStyle style = GuestProfilesStyle.getStyle(this.callback.isP4Redesign());
        view.setBackgroundColor(ContextCompat.getColor(getContext(), style.backgroundColorRes));
        style.marqueeStyle.setStyle(this.sheetHeader);
        style.inputStyle.setStyle(this.dateOfExpiryInput);
        ViewUtils.setVisibleIf((View) this.jellyfishView, style.hasJellyFish);
        ViewUtils.setVisibleIf(this.nextButton, style.nextButtonVisible);
        ViewUtils.setVisibleIf(this.bookingNextButton, style.bookingNextButtonVisible);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void showExpiryDatePicker() {
        DatePickerDialog.newInstance(this.expiryDate == null ? AirDate.today().plusYears(1) : this.expiryDate, false, this, C0880R.string.select_expiry_date_dialog_title, AirDate.today(), AirDate.today().plusYears(20)).show(getFragmentManager(), (String) null);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onNextClick() {
        this.callback.setDateOfExpiry(this.expiryDate);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onBookingNextClick() {
        onNextClick();
    }

    private void updateViewsWithDate() {
        boolean haveExpiryDate = this.expiryDate != null;
        if (haveExpiryDate) {
            this.dateOfExpiryInput.setText(this.expiryDate.formatDate((DateFormat) this.dateFormat));
        }
        this.nextButton.setEnabled(haveExpiryDate);
        this.bookingNextButton.setEnabled(haveExpiryDate);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1 && requestCode == 2002) {
            this.expiryDate = (AirDate) data.getParcelableExtra("date");
            updateViewsWithDate();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public Strap getNavigationTrackingParams() {
        return BookingAnalytics.getP4NavigationTrackingParams(this.callback.isP4Redesign());
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ChinaGuestPassportExpiryDate;
    }
}
