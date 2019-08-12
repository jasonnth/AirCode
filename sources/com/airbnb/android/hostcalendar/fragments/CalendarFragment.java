package com.airbnb.android.hostcalendar.fragments;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.controllers.BottomBarController;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.requests.ListingRequest;
import com.airbnb.android.core.responses.ListingResponse;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.android.hostcalendar.HostCalendarGraph;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.components.LoadingView;
import icepick.State;
import java.util.ArrayList;
import p032rx.Observer;

public class CalendarFragment extends AirFragment {
    public static final int CACHE_EXPIRATION_MINUTES = 1;
    BottomBarController bottomBarController;
    @BindView
    ViewGroup contentContainer;
    @State
    AirDateTime listingLoadedTime;
    final RequestListener<ListingResponse> listingLoaderListener = new C0699RL().onResponse(CalendarFragment$$Lambda$1.lambdaFactory$(this)).onError(CalendarFragment$$Lambda$2.lambdaFactory$(this)).onComplete(CalendarFragment$$Lambda$3.lambdaFactory$(this)).build();
    @State
    ArrayList<Listing> listings;
    @BindView
    LoadingView loadingView;

    static /* synthetic */ void lambda$new$0(CalendarFragment calendarFragment, ListingResponse response) {
        calendarFragment.listings = new ArrayList<>(response.getListings());
        calendarFragment.listingLoadedTime = AirDateTime.now();
        calendarFragment.showPage();
    }

    public static CalendarFragment newInstance() {
        return new CalendarFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((HostCalendarGraph) CoreApplication.instance(getContext()).component()).inject(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C6418R.layout.fragment_host_calendar, container, false);
        bindViews(view);
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (listingsExpired()) {
            retryLoading();
        }
    }

    public void onStart() {
        super.onStart();
        this.bottomBarController.setShowBottomBar(true, true);
    }

    /* access modifiers changed from: private */
    public void retryLoading() {
        showFragment(BlankCalendarFragment.blankCalendarFragment());
        showLoader(true);
        ListingRequest.forCalendar(0, 50).withListener((Observer) this.listingLoaderListener).execute(this.requestManager);
    }

    private void showPage() {
        showLoader(false);
        if (this.listings.isEmpty()) {
            showFragment(BlankCalendarFragment.noListingsFragment(getResources().getString(C6418R.string.host_calendar_listing_none)));
        } else if (this.listings.size() == 1) {
            Listing listing = (Listing) this.listings.get(0);
            showFragment(SingleCalendarFragment.newInstanceFromMultiCalendarAgenda(listing.getId(), listing.getName()));
        } else {
            showFragment(AgendaCalendarFragment.newInstance());
        }
    }

    private boolean listingsExpired() {
        return this.listings == null || this.listingLoadedTime.plusMinutes(1).isBeforeNow();
    }

    private void showFragment(Fragment fragment) {
        getChildFragmentManager().beginTransaction().replace(C6418R.C6420id.content_container, fragment, fragment.getTag()).commit();
    }

    public void showLoader(boolean visible) {
        ViewUtils.setVisibleIf((View) this.loadingView, visible);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.Ignore;
    }
}
