package com.airbnb.android.checkin;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.checkin.CheckInIntroController.Listener;
import com.airbnb.android.checkin.data.CheckInComponent.Builder;
import com.airbnb.android.checkin.data.CheckInDataBindings;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.CheckInGuide;
import com.airbnb.android.core.utils.MapUtil;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.Strap;

public class CheckInIntroFragment extends CheckinBaseFragment {
    private static final String ARG_CHECK_IN_GUIDE = "check_in_guide";
    private CheckInIntroController adapterController;
    /* access modifiers changed from: private */
    public CheckInGuide guide;
    GuestCheckInJitneyLogger jitneyLogger;
    private final Listener listener = new Listener() {
        public void onAddressSelected(String address) {
            CheckInIntroFragment.this.startActivity(MapUtil.intentFor(CheckInIntroFragment.this.getContext(), 0.0d, 0.0d, address));
            CheckInIntroFragment.this.jitneyLogger.logCheckinGuideGuestOpenMapEvent(CheckInIntroFragment.this.guide.getListingId());
        }
    };
    @BindView
    RecyclerView recyclerView;

    public static CheckInIntroFragment create(CheckInGuide guide2) {
        return (CheckInIntroFragment) ((FragmentBundleBuilder) FragmentBundler.make(new CheckInIntroFragment()).putParcelable("check_in_guide", guide2)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Builder) ((CheckInDataBindings) CoreApplication.instance().componentProvider()).checkInComponentProvider().get()).build().inject(this);
        this.guide = (CheckInGuide) getArguments().getParcelable("check_in_guide");
        this.adapterController = new CheckInIntroController(getContext(), this.guide.getSteps().size(), this.guide.getVisibleStartingAt(), this.guide.getVisibilityStatus(), this.guide.getAddress(), this.guide.getLocalizedCheckInTimeWindow(), this.guide.getCheckinInformation(), this.listener);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C5618R.layout.fragment_check_in_guide_step, container, false);
        bindViews(view);
        this.recyclerView.setAdapter(this.adapterController.getAdapter());
        return view;
    }

    public NavigationTag getNavigationTrackingTag() {
        if (this.guide.getVisibilityStatus() == 1) {
            return NavigationTag.CheckinGuideGuestInstructionsInvisible;
        }
        if (this.guide.getVisibilityStatus() == 2) {
            return NavigationTag.CheckinGuideGuestInstructionsExpired;
        }
        return NavigationTag.Ignore;
    }

    public Strap getNavigationTrackingParams() {
        return super.getNavigationTrackingParams().mo11638kv("listing_id", this.guide.getListingId());
    }
}
