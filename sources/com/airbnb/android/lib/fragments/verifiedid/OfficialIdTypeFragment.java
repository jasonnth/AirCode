package com.airbnb.android.lib.fragments.verifiedid;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics.VerifiedIdStrapper;
import com.airbnb.android.core.responses.OfficialIdSupportedCountriesResponse.Country.Identification;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.OfficialIdActivity;
import com.airbnb.android.utils.Strap;
import java.util.List;

public class OfficialIdTypeFragment extends Fragment implements VerifiedIdStrapper {
    public static final String TAG = OfficialIdTypeFragment.class.getSimpleName();

    public static OfficialIdTypeFragment newInstance() {
        return new OfficialIdTypeFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        View view = inflater.inflate(C0880R.layout.fragment_official_id_type, container, false);
        OfficialIdActivity activity = (OfficialIdActivity) getActivity();
        List<Identification> identificationsForCountry = (List) activity.getTypesForSupportedCountries().get(activity.getOfficialIdCountryCode());
        if (identificationsForCountry != null) {
            for (Identification identification : identificationsForCountry) {
                if ("PASSPORT".equals(identification.type)) {
                    Button passportButton = (Button) view.findViewById(C0880R.C0882id.official_id_passport_button);
                    passportButton.setVisibility(0);
                    passportButton.setOnClickListener(OfficialIdTypeFragment$$Lambda$1.lambdaFactory$(this));
                } else if (OfficialIdActivity.DRIVERS_LICENSE_TYPE.equals(identification.type)) {
                    Button driversLicenseButton = (Button) view.findViewById(C0880R.C0882id.official_id_drivers_license_button);
                    driversLicenseButton.setVisibility(0);
                    driversLicenseButton.setOnClickListener(OfficialIdTypeFragment$$Lambda$2.lambdaFactory$(this));
                } else if (OfficialIdActivity.ID_TYPE.equals(identification.type)) {
                    ((LinearLayout) view.findViewById(C0880R.C0882id.official_id_government_id_container)).setVisibility(0);
                    ((TextView) view.findViewById(C0880R.C0882id.official_id_government_id_text_choice)).setOnClickListener(OfficialIdTypeFragment$$Lambda$3.lambdaFactory$(this));
                } else {
                    Log.e(TAG, "Unknown Identification Type: " + identification.type);
                }
            }
        }
        VerifiedIdAnalytics.trackOfflineIdTypeView(Strap.make().mo11639kv("ids", identificationsForCountry != null ? identificationsForCountry.toString() : ""));
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(OfficialIdTypeFragment officialIdTypeFragment, View v) {
        VerifiedIdAnalytics.trackOfflineIdTypePassport(officialIdTypeFragment.getVerifiedIdAnalyticsStrap());
        ((OfficialIdActivity) officialIdTypeFragment.getActivity()).setOfficialIdType("PASSPORT");
        ((OfficialIdActivity) officialIdTypeFragment.getActivity()).goToPhotoSelectionFront();
    }

    static /* synthetic */ void lambda$onCreateView$1(OfficialIdTypeFragment officialIdTypeFragment, View v) {
        VerifiedIdAnalytics.trackOfflineIdTypeDriversLicense(officialIdTypeFragment.getVerifiedIdAnalyticsStrap());
        ((OfficialIdActivity) officialIdTypeFragment.getActivity()).setOfficialIdType(OfficialIdActivity.DRIVERS_LICENSE_TYPE);
        ((OfficialIdActivity) officialIdTypeFragment.getActivity()).goToPhotoSelectionFront();
    }

    static /* synthetic */ void lambda$onCreateView$2(OfficialIdTypeFragment officialIdTypeFragment, View v) {
        VerifiedIdAnalytics.trackOfflineIdTypeIdCard(officialIdTypeFragment.getVerifiedIdAnalyticsStrap());
        ((OfficialIdActivity) officialIdTypeFragment.getActivity()).setOfficialIdType(OfficialIdActivity.ID_TYPE);
        ((OfficialIdActivity) officialIdTypeFragment.getActivity()).goToPhotoSelectionFront();
    }

    public Strap getVerifiedIdAnalyticsStrap() {
        return Strap.make().mo11639kv("reservation_id", ((OfficialIdActivity) getActivity()).getReservationId());
    }
}
