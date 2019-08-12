package com.airbnb.android.lib.businesstravel;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.booking.fragments.BookingBaseFragment;
import com.airbnb.android.core.businesstravel.BusinessTravelAccountManager;
import com.airbnb.android.core.models.ReservationDetails.TripType;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.businesstravel.BusinessTravelInterstitialAdapter.BusinessTravelListener;
import com.airbnb.p027n2.components.AirToolbar;

public class BusinessTravelInterstitialFragment extends BookingBaseFragment {
    public static final String TAG = BusinessTravelInterstitialFragment.class.getSimpleName();
    private final BusinessTravelListener btListener = BusinessTravelInterstitialFragment$$Lambda$1.lambdaFactory$(this);
    BusinessTravelAccountManager businessTravelAccountManager;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static BusinessTravelInterstitialFragment newInstance() {
        return new BusinessTravelInterstitialFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_business_travel_interstitial, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(new BusinessTravelInterstitialAdapter(this.btListener));
        this.recyclerView.setHasFixedSize(true);
        return view;
    }

    static /* synthetic */ void lambda$new$0(BusinessTravelInterstitialFragment businessTravelInterstitialFragment, boolean isBusinessTravel) {
        if (businessTravelInterstitialFragment.businessTravelAccountManager.isVerifiedBusinessTraveler()) {
            businessTravelInterstitialFragment.getBookingActivity().doneWithBusinessTravelIdentification(isBusinessTravel ? TripType.BusinessVerified : TripType.PersonalVerified);
        } else {
            businessTravelInterstitialFragment.getBookingActivity().doneWithBusinessTravelIdentification(isBusinessTravel ? TripType.BusinessUnverified : TripType.PersonalUnverified);
        }
    }
}
