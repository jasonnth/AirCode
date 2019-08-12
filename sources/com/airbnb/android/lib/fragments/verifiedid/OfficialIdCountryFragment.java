package com.airbnb.android.lib.fragments.verifiedid;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics.VerifiedIdStrapper;
import com.airbnb.android.core.models.NameCodeItem;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.OfficialIdActivity;
import com.airbnb.android.lib.fragments.CountryPickerDialogFragment;
import com.airbnb.android.utils.Strap;
import java.util.Locale;

public class OfficialIdCountryFragment extends Fragment implements VerifiedIdStrapper {
    private static final int REQUEST_CODE_COUNTRY_PICKER = 1974;
    private TextView mCountryTextView;
    private Button mNextButton;

    public static OfficialIdCountryFragment newInstance() {
        return new OfficialIdCountryFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        View view = inflater.inflate(C0880R.layout.fragment_official_id_country, container, false);
        OfficialIdActivity activity = (OfficialIdActivity) getActivity();
        activity.setIdFrontUriString(null);
        activity.setIdBackUriString(null);
        this.mCountryTextView = (TextView) view.findViewById(C0880R.C0882id.official_id_country);
        this.mCountryTextView.setText(new Locale(Locale.getDefault().getLanguage(), ((OfficialIdActivity) getActivity()).getOfficialIdCountryCode()).getDisplayCountry());
        this.mNextButton = (Button) view.findViewById(C0880R.C0882id.official_id_country_send_button);
        this.mNextButton.setOnClickListener(OfficialIdCountryFragment$$Lambda$1.lambdaFactory$(this));
        if (((OfficialIdActivity) getActivity()).getSupportedCountries() == null) {
            enableInteraction(false);
        } else {
            enableInteraction(true);
        }
        VerifiedIdAnalytics.trackOfflineCountryView(getVerifiedIdAnalyticsStrap());
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(OfficialIdCountryFragment officialIdCountryFragment, View view1) {
        VerifiedIdAnalytics.trackOfflineCountryNext(officialIdCountryFragment.getVerifiedIdAnalyticsStrap().mo11639kv("country_code", ((OfficialIdActivity) officialIdCountryFragment.getActivity()).getOfficialIdCountryCode()));
        ((OfficialIdActivity) officialIdCountryFragment.getActivity()).goToType();
    }

    private void selectCountry() {
        CountryPickerDialogFragment countryPickerDialog = CountryPickerDialogFragment.newInstance(getString(C0880R.string.country_picker_select_country), ((OfficialIdActivity) getActivity()).getOfficialIdCountryCode(), ((OfficialIdActivity) getActivity()).getSupportedCountries());
        countryPickerDialog.setTargetFragment(this, REQUEST_CODE_COUNTRY_PICKER);
        countryPickerDialog.show(getFragmentManager(), (String) null);
    }

    public Strap getVerifiedIdAnalyticsStrap() {
        return Strap.make().mo11639kv("reservation_id", ((OfficialIdActivity) getActivity()).getReservationId());
    }

    public void enableInteraction(boolean enable) {
        if (enable) {
            this.mNextButton.setEnabled(true);
            this.mCountryTextView.setOnClickListener(OfficialIdCountryFragment$$Lambda$2.lambdaFactory$(this));
            return;
        }
        this.mNextButton.setEnabled(false);
        this.mCountryTextView.setOnClickListener(null);
    }

    static /* synthetic */ void lambda$enableInteraction$1(OfficialIdCountryFragment officialIdCountryFragment, View v) {
        VerifiedIdAnalytics.trackOfflineCountryChange(officialIdCountryFragment.getVerifiedIdAnalyticsStrap());
        officialIdCountryFragment.selectCountry();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_COUNTRY_PICKER /*1974*/:
                NameCodeItem nameCodeItem = (NameCodeItem) data.getParcelableExtra("country");
                this.mCountryTextView.setText(nameCodeItem.getName());
                ((OfficialIdActivity) getActivity()).setOfficialIdCountryCode(nameCodeItem.getCode());
                return;
            default:
                return;
        }
    }
}
