package com.airbnb.android.lib.fragments.completeprofile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics.VerifiedIdStrapper;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.CompleteProfileActivity;
import com.airbnb.android.lib.views.CircleBadgeView;
import com.airbnb.android.utils.Strap;

public class CompleteProfileEmailFragment extends CompleteProfileBaseFragment implements VerifiedIdStrapper {
    public static CompleteProfileEmailFragment newInstance() {
        return new CompleteProfileEmailFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_complete_profile_email, container, false);
        ((CircleBadgeView) view.findViewById(C0880R.C0882id.circle_badge_view)).initializeAsInactiveBadge();
        if (savedInstanceState == null) {
            getCompleteProfileActivity().showProgressBar();
            showChildFragment(CompleteProfileEmailChildFragment.newInstance());
            VerifiedIdAnalytics.trackEmailStartView(getVerifiedIdAnalyticsStrap());
            VerifiedIdAnalytics.trackHealth("email", "start");
        }
        return view;
    }

    public Strap getVerifiedIdAnalyticsStrap() {
        return Strap.make().mo11639kv("reservation_id", ((CompleteProfileActivity) getActivity()).getReservationId());
    }
}
