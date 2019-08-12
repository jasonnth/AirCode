package com.airbnb.android.lib.fragments.verifiedid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics.VerifiedIdStrapper;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.OfficialIdActivity;
import com.airbnb.android.utils.Strap;
import java.util.Locale;

public class OfficialIdErrorFragment extends AirFragment implements VerifiedIdStrapper {
    public static OfficialIdErrorFragment newInstance() {
        return new OfficialIdErrorFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        View view = inflater.inflate(C0880R.layout.fragment_official_id_error, container, false);
        ((Button) view.findViewById(C0880R.C0882id.try_again_btn)).setOnClickListener(OfficialIdErrorFragment$$Lambda$1.lambdaFactory$(this));
        ((TextView) view.findViewById(C0880R.C0882id.unable_to_verify_details_tv)).setText(getString(C0880R.string.verified_id_offline_unable_to_verify_id_type, ((OfficialIdActivity) getActivity()).getOfficialIdTypeDisplayedString().toLowerCase(Locale.getDefault())));
        VerifiedIdAnalytics.trackOfflineErrorView(getVerifiedIdAnalyticsStrap());
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(OfficialIdErrorFragment officialIdErrorFragment, View v) {
        VerifiedIdAnalytics.trackOfflineErrorTryAgain(officialIdErrorFragment.getVerifiedIdAnalyticsStrap());
        ((OfficialIdActivity) officialIdErrorFragment.getActivity()).goToCountry();
    }

    public Strap getVerifiedIdAnalyticsStrap() {
        return Strap.make().mo11639kv("reservation_id", ((OfficialIdActivity) getActivity()).getReservationId());
    }
}
