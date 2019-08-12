package com.airbnb.android.registration;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.datepicker.DatePickerDialog;
import com.airbnb.android.registration.models.AccountRegistrationData;
import com.airbnb.android.registration.models.AccountRegistrationStep;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;
import icepick.State;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class EditBirthdayRegistrationFragment extends BaseRegistrationFragment {
    private static final String ARG_ALLOW_UNDER_AGE = "arg_allow_under_age";
    private static final String PLACE_HOLDER_TEXT = "      /      /      ";
    private static final int REQUEST_CODE_CONFIRM_AGE = 5002;
    private static final int REQUEST_CODE_REENTER_BIRTHDATE = 5001;
    private boolean allowUnderAgeInDatePicker;
    @BindView
    SheetInputText editBirthday;
    @BindView
    JellyfishView jellyfishView;
    @BindView
    AirButton nextButton;
    @State
    AirDate selectedBirthday;

    public static EditBirthdayRegistrationFragment newInstanceAllowingUnderAge() {
        return (EditBirthdayRegistrationFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new EditBirthdayRegistrationFragment()).putBoolean(ARG_ALLOW_UNDER_AGE, true)).putParcelable(BaseRegistrationFragment.ARG_ACCOUNT_REG_DATA, AccountRegistrationData.builder().build())).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.allowUnderAgeInDatePicker = getArguments().getBoolean(ARG_ALLOW_UNDER_AGE, false);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C1562R.layout.fragment_edit_birthday_registration, container, false);
        bindViews(view);
        if (savedInstanceState == null && !TextUtils.isEmpty(this.dataPassedIn.birthDateString())) {
            this.selectedBirthday = new AirDate(this.dataPassedIn.birthDateString());
        }
        if (this.selectedBirthday != null) {
            updateViewsWithDate();
        } else {
            this.editBirthday.setText(PLACE_HOLDER_TEXT);
        }
        return view;
    }

    public void onResume() {
        super.onResume();
        KeyboardUtils.dismissSoftKeyboard(getView());
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            switch (requestCode) {
                case DatePickerDialog.DATE_PICKER_OK /*2002*/:
                    this.selectedBirthday = (AirDate) data.getParcelableExtra("date");
                    updateViewsWithDate();
                    break;
                case REQUEST_CODE_REENTER_BIRTHDATE /*5001*/:
                    launchBirthdayPicker();
                    break;
                case REQUEST_CODE_CONFIRM_AGE /*5002*/:
                    proceedWithSelectedBirthday();
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.RegistrationBirthday;
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void launchBirthdayPicker() {
        DatePickerDialog.newInstance(this.selectedBirthday == null ? DatePickerDialog.getDefaultBirthdate() : this.selectedBirthday, !this.allowUnderAgeInDatePicker, this, C1562R.string.select_birth_date, null, AirDate.today()).show(getFragmentManager(), (String) null);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void next() {
        KeyboardUtils.dismissSoftKeyboard(getView());
        if (!this.allowUnderAgeInDatePicker || !this.selectedBirthday.isAfter(DatePickerDialog.getMinQualifiedBirthdate())) {
            proceedWithSelectedBirthday();
            return;
        }
        int userAge = Math.max(1, this.selectedBirthday.getYearsUntil(AirDate.today()));
        ZenDialog.builder().withBodyText(getResources().getQuantityString(C1562R.plurals.confirm_x_years_old, userAge, new Object[]{Integer.valueOf(userAge)})).withDualButton(C1562R.string.f1615no, REQUEST_CODE_REENTER_BIRTHDATE, C1562R.string.yes, REQUEST_CODE_CONFIRM_AGE).create().show(getFragmentManager(), (String) null);
    }

    private void updateViewsWithDate() {
        this.editBirthday.setText(this.selectedBirthday.formatDate((DateFormat) new SimpleDateFormat(getString(C1562R.string.mdy_short_with_full_year_and_space))));
        this.nextButton.setEnabled(true);
    }

    private void proceedWithSelectedBirthday() {
        this.registrationController.onStepFinished(AccountRegistrationStep.Birthday, AccountRegistrationData.builder().birthDateString(this.selectedBirthday.getIsoDateString()).build());
    }
}
