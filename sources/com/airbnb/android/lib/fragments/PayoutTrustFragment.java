package com.airbnb.android.lib.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.data.AddressParts;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.datepicker.DatePickerDialog;
import com.airbnb.android.core.requests.PayoutCountriesRequest;
import com.airbnb.android.core.responses.PayoutCountriesResponse;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.LegacyAddPayoutActivity;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.LocaleUtil;
import icepick.State;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class PayoutTrustFragment extends AirFragment {
    public static final String ARG_BIRTHDAY = "dob_key";
    public static final String ARG_COUNTRY_CODE = "arg_country_code";
    private static final int REQUEST_CODE_COUNTRY_PICKER = 1972;
    @State
    protected AirDate birthday;
    @BindView
    View mBirthdayLabel;
    @BindView
    View mBirthdaySelector;
    @BindView
    TextView mBirthdaySelectorText;
    private CountryPickerDialogFragment mCountryPickerDialog;
    @BindView
    TextView mPayoutApt;
    @BindView
    TextView mPayoutCity;
    @State
    protected String mPayoutCountryCode;
    @BindView
    TextView mPayoutCountrySelector;
    @BindView
    TextView mPayoutState;
    @BindView
    TextView mPayoutStreet;
    @BindView
    TextView mPayoutZip;
    /* access modifiers changed from: private */
    public ArrayList<String> mSupportedCountries;
    /* access modifiers changed from: private */
    public ArrayList<String> mSupportedCountriesNames;

    public static PayoutTrustFragment forCountryCode(String countryCode) {
        return (PayoutTrustFragment) ((FragmentBundleBuilder) FragmentBundler.make(new PayoutTrustFragment()).putString("arg_country_code", countryCode)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_payout_trust, container, false);
        bindViews(view);
        updateBirthdayText();
        if (!Trebuchet.launch(TrebuchetKeys.NEW_PAYOUT_FLOW_V1_ENABLED, true)) {
            prefetchSupportedCountries();
        }
        if (this.mAccountManager.getCurrentUser().getBirthdate() != null) {
            this.mBirthdaySelector.setVisibility(8);
            this.mBirthdayLabel.setVisibility(8);
        }
        if (savedInstanceState == null) {
            this.mPayoutCountryCode = getArguments().getString("arg_country_code");
        }
        updateCountryText();
        return view;
    }

    private void updateCountryText() {
        this.mPayoutCountrySelector.setText(LocaleUtil.getDisplayCountryFromCountryCode(getActivity(), this.mPayoutCountryCode));
        this.mPayoutCountrySelector.setEnabled(false);
    }

    @OnClick
    public void onNextButtonClicked() {
        if (validateFields()) {
            LegacyAddPayoutActivity activity = (LegacyAddPayoutActivity) getActivity();
            activity.setNewTrustParameters(AddressParts.builder().street1(this.mPayoutStreet.getText().toString()).street2(this.mPayoutApt.getText().toString()).city(this.mPayoutCity.getText().toString()).state(this.mPayoutState.getText().toString()).zipCode(this.mPayoutZip.getText().toString()).countryCode(this.mPayoutCountryCode).build());
            activity.setCountryCode(this.mPayoutCountryCode);
            activity.showFragment(PayoutSelectFragment.newInstance(this.mPayoutCountryCode));
            KeyboardUtils.dismissSoftKeyboard(getActivity(), getView());
        }
    }

    @OnClick
    public void selectCountry() {
        if (Trebuchet.launch(TrebuchetKeys.NEW_PAYOUT_FLOW_V1_ENABLED, true)) {
            return;
        }
        if (this.mSupportedCountries != null) {
            showSelectCountryDialog();
        } else {
            loadSupportedCountriesForDialog();
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(ARG_BIRTHDAY, this.birthday);
        outState.putString("arg_country_code", this.mPayoutCountryCode);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case DatePickerDialog.DATE_PICKER_OK /*2002*/:
                this.birthday = (AirDate) data.getParcelableExtra("date");
                updateBirthdayText();
                return;
            default:
                return;
        }
    }

    private void updateBirthdayText() {
        if (this.birthday == null) {
            this.mBirthdaySelectorText.setText("");
            return;
        }
        this.mBirthdaySelectorText.setText(this.birthday.formatDate((DateFormat) new SimpleDateFormat(getString(C0880R.string.mdy_format_full))));
    }

    @OnClick
    public void selectBirthday() {
        DatePickerDialog.newInstance(this.birthday, true, this, 0).show(getFragmentManager(), (String) null);
    }

    /* access modifiers changed from: private */
    public void showSelectCountryDialog() {
        String currentCountry = Locale.getDefault().getCountry();
        if (this.mCountryPickerDialog == null || !this.mCountryPickerDialog.isResumed()) {
            this.mCountryPickerDialog = CountryPickerDialogFragment.newInstance(getString(C0880R.string.payout_choose_country), currentCountry, this.mSupportedCountries, this.mSupportedCountriesNames);
            this.mCountryPickerDialog.setTargetFragment(this, REQUEST_CODE_COUNTRY_PICKER);
            this.mCountryPickerDialog.show(getFragmentManager(), (String) null);
        }
    }

    private void prefetchSupportedCountries() {
        new PayoutCountriesRequest(new NonResubscribableRequestListener<PayoutCountriesResponse>() {
            public void onResponse(PayoutCountriesResponse response) {
                PayoutTrustFragment.this.mSupportedCountries = response.getSupportedCountries();
                PayoutTrustFragment.this.mSupportedCountriesNames = response.getSupportedCountriesNames();
            }

            public void onErrorResponse(AirRequestNetworkException error) {
            }
        }).execute(this.requestManager);
    }

    private void loadSupportedCountriesForDialog() {
        new PayoutCountriesRequest(new NonResubscribableRequestListener<PayoutCountriesResponse>() {
            public void onResponse(PayoutCountriesResponse response) {
                PayoutTrustFragment.this.mSupportedCountries = response.getSupportedCountries();
                PayoutTrustFragment.this.showSelectCountryDialog();
            }

            public void onErrorResponse(AirRequestNetworkException error) {
                NetworkUtil.toastGenericNetworkError(PayoutTrustFragment.this.getActivity());
            }
        }).execute(this.requestManager);
    }

    public boolean validateFields() {
        trimAndVerify(this.mPayoutState);
        if (!Trebuchet.launch(TrebuchetKeys.NEW_PAYOUT_FLOW_V1_ENABLED, true) && TextUtils.isEmpty(this.mPayoutCountryCode)) {
            Toast.makeText(getActivity(), C0880R.string.country_please, 0).show();
            selectCountry();
            return false;
        } else if (("US".equalsIgnoreCase(this.mPayoutCountryCode) || "CA".equalsIgnoreCase(this.mPayoutCountryCode)) && this.mPayoutState.getText().length() != 2) {
            Toast.makeText(getActivity(), C0880R.string.state_code_please, 0).show();
            this.mPayoutState.requestFocus();
            return false;
        } else {
            trimAndVerify(this.mPayoutApt);
            if (trimAndVerify(this.mPayoutStreet)) {
                Toast.makeText(getActivity(), C0880R.string.address_please, 1).show();
                this.mPayoutStreet.requestFocus();
                return false;
            } else if (trimAndVerify(this.mPayoutCity)) {
                Toast.makeText(getActivity(), C0880R.string.city_please, 1).show();
                this.mPayoutCity.requestFocus();
                return false;
            } else if (trimAndVerify(this.mPayoutZip) && !"IE".equalsIgnoreCase(this.mPayoutCountryCode)) {
                Toast.makeText(getActivity(), C0880R.string.zip_please, 1).show();
                this.mPayoutZip.requestFocus();
                return false;
            } else if ("US".equalsIgnoreCase(this.mPayoutCountryCode) && !MiscUtils.isUsZipCode(this.mPayoutZip.getText().toString())) {
                Toast.makeText(getActivity(), C0880R.string.valid_zip_please, 1).show();
                this.mPayoutZip.requestFocus();
                return false;
            } else if (this.birthday != null || this.mBirthdaySelector.getVisibility() != 0) {
                return true;
            } else {
                Toast.makeText(getActivity(), C0880R.string.date_of_birth_please, 1).show();
                this.mBirthdaySelector.requestFocus();
                return false;
            }
        }
    }

    private static boolean trimAndVerify(TextView textView) {
        String str = textView.getText().toString().trim();
        textView.setText(str);
        return TextUtils.isEmpty(str);
    }
}
