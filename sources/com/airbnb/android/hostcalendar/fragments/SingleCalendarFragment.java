package com.airbnb.android.hostcalendar.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.view.ViewPager.SimpleOnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.CalendarJitneyLogger;
import com.airbnb.android.core.analytics.PerformanceLoggerTimeline;
import com.airbnb.android.core.analytics.PerformanceLoggerTimeline.Event;
import com.airbnb.android.core.calendar.CalendarDays;
import com.airbnb.android.core.calendar.CalendarDays.OnChangeListener;
import com.airbnb.android.core.calendar.CalendarStore;
import com.airbnb.android.core.enums.ROLaunchSource;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.intents.HostReservationObjectIntents;
import com.airbnb.android.core.intents.ManageListingIntents;
import com.airbnb.android.core.intents.ThreadFragmentIntents;
import com.airbnb.android.core.interfaces.OnBackListener;
import com.airbnb.android.core.models.CalendarDay;
import com.airbnb.android.core.models.CalendarRule;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.NestedBusyDetail;
import com.airbnb.android.core.requests.CalendarRulesRequest;
import com.airbnb.android.core.responses.CalendarRulesResponse;
import com.airbnb.android.core.utils.SettingDeepLink;
import com.airbnb.android.core.views.OptionalSwipingViewPager;
import com.airbnb.android.core.views.SlidingTabLayout;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.android.hostcalendar.CalendarDateRange;
import com.airbnb.android.hostcalendar.HostCalendarGraph;
import com.airbnb.android.hostcalendar.activities.HostCalendarUpdateActivity;
import com.airbnb.android.hostcalendar.fragments.SingleCalendarFragmentPager.TabType;
import com.airbnb.android.sharedcalendar.listeners.SingleCalendarListener;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.bottom_sheet.BottomSheetBuilder;
import com.airbnb.p027n2.components.bottom_sheet.BottomSheetItemClickListener;
import com.airbnb.p027n2.components.bottom_sheet.BottomSheetMenuItem;
import com.airbnb.p027n2.components.onboarding_overlay.OnboardingOverlayViewController;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import icepick.State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import p032rx.Observer;

public class SingleCalendarFragment extends AirFragment implements OnBackListener {
    public static final String ARG_LISTING_ID = "listing_id";
    public static final String ARG_LISTING_NAME = "listing_name";
    protected static final String ARG_NAV_FROM_MULTICAL = "nav_from_multical";
    public static final String ARG_TARGET_END_DATE = "target_end_date";
    public static final String ARG_TARGET_START_DATE = "target_start_date";
    private static final int ONBOARDING_OVERLAY_SHOW_ON_SEEN_TIMES = 4;
    private static final int REQUEST_CODE_CALENDAR_SETTINGS = 106;
    private static final int RQUEST_CODE_EDIT_DATES = 107;
    private BottomSheetDialog bottomSheetDialog;
    private final BottomSheetItemClickListener bottomSheetItemClickListener = SingleCalendarFragment$$Lambda$3.lambdaFactory$(this);
    @State
    CalendarRule calendarRule;
    final RequestListener<CalendarRulesResponse> calendarRulesListener = new C0699RL().onResponse(SingleCalendarFragment$$Lambda$1.lambdaFactory$(this)).onError(SingleCalendarFragment$$Lambda$2.lambdaFactory$(this)).build();
    CalendarStore calendarStore;
    private final Set<SingleCalendarListenerFragment> childFragments = new HashSet();
    @BindView
    AirButton editButton;
    boolean inEditMode;
    private CalendarDateRange initialDateRange;
    @State
    boolean isNavFromMultical;
    CalendarJitneyLogger jitneyLogger;
    @State
    long listingId;
    private final SimpleOnPageChangeListener pageChangeListener = new SimpleOnPageChangeListener() {
        public void onPageSelected(int position) {
            CoreApplication.instance(SingleCalendarFragment.this.getContext()).component().navigationAnalytics().trackImpressionExplicitly(SingleCalendarFragment.this.pagerAdapter.getNavigationTrackingTag(position), Strap.make().mo11638kv("listing_id", SingleCalendarFragment.this.listingId));
        }
    };
    /* access modifiers changed from: private */
    public SingleCalendarFragmentPager pagerAdapter;
    @State
    CalendarDays selectedDays;
    private final SingleCalendarListener singleCalendarListener = new SingleCalendarListener() {
        public void goToReservation(String confirmationCode) {
            SingleCalendarFragment.this.startActivity(HostReservationObjectIntents.intentForConfirmationCode(SingleCalendarFragment.this.getActivity(), confirmationCode, ROLaunchSource.HostCalendar));
        }

        public void goToMessageThread(long threadId) {
            SingleCalendarFragment.this.startActivity(ThreadFragmentIntents.newIntent(SingleCalendarFragment.this.getActivity(), threadId, InboxType.Host));
        }

        public void onDayClick(CalendarDay calendarDay) {
            List<NestedBusyDetail> nestedBusyDetails = calendarDay.getNestedBusyDetails();
            if (!FeatureToggles.showNestedListings() || ListUtils.isEmpty((Collection<?>) nestedBusyDetails)) {
                toggleDayForEdit(calendarDay);
            } else {
                goToNestedListingView(calendarDay);
            }
        }

        private void toggleDayForEdit(CalendarDay calendarDay) {
            SingleCalendarFragment.this.selectedDays.toggle(calendarDay);
            SingleCalendarFragment.this.updateEditTitlebar(true);
        }

        private void goToNestedListingView(CalendarDay calendarDay) {
            SingleCalendarFragment.this.startActivity(HostCalendarUpdateActivity.intentForNestedListingView(SingleCalendarFragment.this.getContext(), SingleCalendarFragment.this.listingId, calendarDay));
        }

        public Set<AirDate> getSelectedDates() {
            return SingleCalendarFragment.this.selectedDays.getDates();
        }

        public void addSelectedDaysChangeListener(OnChangeListener listener) {
            SingleCalendarFragment.this.selectedDays.addChangeListener(listener);
        }

        public void removeSelectedDaysChangeListener(OnChangeListener listener) {
            SingleCalendarFragment.this.selectedDays.removeChangeListener(listener);
        }
    };
    @BindView
    SlidingTabLayout tabLayout;
    @BindView
    AirToolbar toolbar;
    @BindView
    OptionalSwipingViewPager viewPager;

    public interface SingleCalendarListenerFragment {
        void clearEditMode(Set<AirDate> set);

        void setListener(SingleCalendarListener singleCalendarListener);
    }

    public static SingleCalendarFragment calendarForDates(long listingId2, String listingName, AirDate targetStartDate, AirDate targetEndDate) {
        return (SingleCalendarFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new SingleCalendarFragment()).putLong("listing_id", listingId2)).putString("listing_name", listingName)).putBoolean(ARG_NAV_FROM_MULTICAL, false)).putParcelable("target_start_date", targetStartDate)).putParcelable("target_end_date", targetEndDate)).build();
    }

    public static SingleCalendarFragment newInstanceFromMultiCalendarAgenda(long listingId2, String listingName) {
        return (SingleCalendarFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new SingleCalendarFragment()).putLong("listing_id", listingId2)).putString("listing_name", listingName)).putBoolean(ARG_NAV_FROM_MULTICAL, true)).build();
    }

    public static SingleCalendarFragment newInstance(long listingId2, String listingName) {
        return (SingleCalendarFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new SingleCalendarFragment()).putLong("listing_id", listingId2)).putString("listing_name", listingName)).putBoolean(ARG_NAV_FROM_MULTICAL, false)).build();
    }

    static /* synthetic */ void lambda$new$0(SingleCalendarFragment singleCalendarFragment, CalendarRulesResponse response) {
        singleCalendarFragment.calendarRule = response.calendarRule;
        singleCalendarFragment.editButton.setState(AirButton.State.Normal);
        singleCalendarFragment.setupTabBar(singleCalendarFragment.initialDateRange);
        singleCalendarFragment.setUpOnboardingOverlay();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        ((HostCalendarGraph) CoreApplication.instance(getContext()).component()).inject(this);
    }

    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
        if (childFragment instanceof SingleCalendarListenerFragment) {
            ((SingleCalendarListenerFragment) childFragment).setListener(this.singleCalendarListener);
            this.childFragments.add((SingleCalendarListenerFragment) childFragment);
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PerformanceLoggerTimeline.start(Event.HOST_CALENDAR_SINGLE);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(C6418R.layout.fragment_host_calendar_single, container, false);
        bindViews(view);
        if (savedInstanceState == null) {
            this.isNavFromMultical = getArguments().getBoolean(ARG_NAV_FROM_MULTICAL);
            this.listingId = getArguments().getLong("listing_id");
            this.selectedDays = new CalendarDays(this.listingId);
        }
        this.initialDateRange = getInitialDateRange((AirDate) getArguments().getParcelable("target_start_date"), (AirDate) getArguments().getParcelable("target_end_date"));
        setHasOptionsMenu(true);
        setToolbar(this.toolbar);
        fetchCalendarRule();
        return view;
    }

    private void fetchCalendarRule() {
        this.editButton.setState(AirButton.State.Loading);
        CalendarRulesRequest.forListingId(this.listingId).withListener((Observer) this.calendarRulesListener).execute(this.requestManager);
    }

    public void onResume() {
        super.onResume();
        getAirActivity().setOnBackPressedListener(this);
        updateEditTitlebar(false);
    }

    public void onPause() {
        super.onPause();
        getAirActivity().setOnBackPressedListener(null);
    }

    private CalendarDateRange getInitialDateRange(AirDate targetStartDate, AirDate targetEndDate) {
        AirDate targetDate = AirDate.today();
        AirDate startDate = targetDate.plusMonths(-1).getFirstDayOfMonth();
        AirDate endDate = targetDate.getLastDayOfMonth();
        if (targetStartDate != null && targetStartDate.isAfter(startDate)) {
            targetDate = targetStartDate;
            if (targetEndDate != null && targetEndDate.isAfter(endDate)) {
                endDate = targetEndDate.getLastDayOfMonth();
            }
        }
        return CalendarDateRange.create(startDate, endDate.plusMonths(1), targetDate);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(C6418R.C6421menu.host_single_calendar, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C6418R.C6420id.settings) {
            return false;
        }
        this.bottomSheetDialog = new BottomSheetBuilder(getContext(), C6418R.C6421menu.host_single_calendar_bottom).setItemClickListener(this.bottomSheetItemClickListener).build();
        this.bottomSheetDialog.show();
        return true;
    }

    public boolean onBackPressed() {
        return clearEditMode(true);
    }

    /* access modifiers changed from: protected */
    public void updateToolbar(int numDaysSelected) {
        boolean editMode;
        boolean z = false;
        if (numDaysSelected > 0) {
            editMode = true;
        } else {
            editMode = false;
        }
        if (!editMode) {
            z = true;
        }
        setMenuVisibility(z);
        this.toolbar.setNavigationIcon(getNavigationIcon(editMode));
        this.toolbar.setTitle((CharSequence) getToolbarTitle(editMode, numDaysSelected));
        if (editMode) {
            this.toolbar.setTheme(2);
            this.toolbar.setBackgroundColor(ContextCompat.getColor(getContext(), C6418R.color.n2_babu));
            this.toolbar.setNavigationOnClickListener(SingleCalendarFragment$$Lambda$4.lambdaFactory$(this));
            this.toolbar.setNavigationContentDescription((CharSequence) getString(C6418R.string.close));
            return;
        }
        this.toolbar.setEllipsizeTitleInMiddle();
        this.toolbar.setTheme(1);
    }

    /* access modifiers changed from: protected */
    public int getNavigationIcon(boolean editMode) {
        if (editMode) {
            return 2;
        }
        if (this.isNavFromMultical) {
            return 0;
        }
        return 1;
    }

    private String getToolbarTitle(boolean editMode, int numDaysSelected) {
        if (editMode) {
            return getContext().getResources().getQuantityString(C6418R.plurals.dates_selected, numDaysSelected, new Object[]{Integer.valueOf(numDaysSelected)});
        } else if (this.isNavFromMultical) {
            return getResources().getString(C6418R.string.calendar);
        } else {
            return getArguments().getString("listing_name");
        }
    }

    private void setupTabBar(CalendarDateRange initialDateRange2) {
        this.inEditMode = false;
        this.pagerAdapter = new SingleCalendarFragmentPager(getActivity(), getChildFragmentManager(), this.listingId, initialDateRange2, this.calendarRule);
        this.viewPager.addOnPageChangeListener(this.pageChangeListener);
        this.viewPager.setAdapter(this.pagerAdapter);
        this.viewPager.setCurrentItem(0);
        this.tabLayout.setViewPager(this.viewPager);
    }

    private void setUpOnboardingOverlay() {
        OnboardingOverlayViewController.show(getActivity(), this.tabLayout.getChildViewAtIndex(this.pagerAdapter.getPagePosition(TabType.Details)).findViewById(C6418R.C6420id.tab_text), getString(C6418R.string.onboarding_title_for_calendar_detail_icon), getString(C6418R.string.onboarding_dismiss_button), "calendar_details_tab", 4);
    }

    /* access modifiers changed from: protected */
    public void updateEditTitlebar(boolean animated) {
        float translation = 0.0f;
        int numDaysSelected = this.selectedDays.size();
        updateToolbar(numDaysSelected);
        boolean isEditing = numDaysSelected > 0;
        if (this.inEditMode != isEditing) {
            this.inEditMode = isEditing;
            if (this.inEditMode) {
                translation = 0.0f - ((float) this.tabLayout.getHeight());
            }
            if (animated) {
                this.tabLayout.animate().translationY(translation);
            } else {
                this.tabLayout.setTranslationY(translation);
            }
            ViewUtils.setVisibleIf((View) this.editButton, this.inEditMode);
        }
    }

    private boolean clearEditMode(boolean animatedTitleBar) {
        if (this.selectedDays.size() <= 0) {
            return false;
        }
        Set<AirDate> selectedDates = new HashSet<>(this.selectedDays.getDates());
        this.selectedDays.clear();
        for (SingleCalendarListenerFragment fragment : this.childFragments) {
            fragment.clearEditMode(selectedDates);
        }
        updateEditTitlebar(animatedTitleBar);
        return true;
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void goToEditDates() {
        ArrayList<CalendarDay> days = new ArrayList<>(this.selectedDays.getCalendarDays());
        TabType tabType = this.pagerAdapter.getCurrentTabType();
        if (this.selectedDays.isEmpty()) {
            BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException("SingleCalendarFragment.goToEditDates() called without selected dates on tab: " + (tabType != null ? tabType.name() : "null")));
            updateEditTitlebar(false);
            return;
        }
        if (tabType != null) {
            switch (tabType) {
                case Month:
                    this.jitneyLogger.singleListingMonthDateClicked(Long.valueOf(this.listingId), days);
                    break;
                case Details:
                    this.jitneyLogger.singleListingAgendaDateClicked(Long.valueOf(this.listingId), days);
                    break;
            }
        }
        startActivityForResult(HostCalendarUpdateActivity.intentForUpdateWithCalendarRule(getContext(), this.listingId, days, tabType, this.calendarRule), 107);
    }

    public void resetCalendarCacheAndRedraw() {
        this.calendarStore.setCacheResetTime(AirDateTime.now());
        if (isResumed()) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }

    static /* synthetic */ void lambda$new$3(SingleCalendarFragment singleCalendarFragment, BottomSheetMenuItem item) {
        if (item.getId() == C6418R.C6420id.refresh) {
            singleCalendarFragment.resetCalendarCacheAndRedraw();
        } else if (item.getId() == C6418R.C6420id.calendar_settings_button) {
            singleCalendarFragment.startActivityForResult(ManageListingIntents.intentForExistingListingSettingWithFullscreenLoader(singleCalendarFragment.getContext(), singleCalendarFragment.listingId, SettingDeepLink.CalendarSettings, true), 106);
        } else {
            throw new IllegalArgumentException("Unknown menu option: " + item.getTitle());
        }
        singleCalendarFragment.bottomSheetDialog.dismiss();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 106:
                if (resultCode == -1) {
                    fetchCalendarRule();
                    resetCalendarCacheAndRedraw();
                    return;
                }
                return;
            case 107:
                if (resultCode == -1) {
                    int numOfDaysEdited = data.getIntExtra(CalendarWithPriceTipsUpdateFragment.NUM_OF_DAYS_EDITED, 0);
                    if (numOfDaysEdited > 0) {
                        new SnackbarWrapper().view(getView()).body(getResources().getQuantityString(C6418R.plurals.host_calendar_x_dates_updated, numOfDaysEdited, new Object[]{Integer.valueOf(numOfDaysEdited)})).duration(0).buildAndShow(2);
                    }
                }
                getView().postDelayed(SingleCalendarFragment$$Lambda$5.lambdaFactory$(this), 250);
                return;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                return;
        }
    }

    static /* synthetic */ void lambda$onActivityResult$4(SingleCalendarFragment singleCalendarFragment) {
        if (singleCalendarFragment.isResumed()) {
            singleCalendarFragment.clearEditMode(false);
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.Ignore;
    }
}
