package com.airbnb.android.core.fragments.datepicker;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.calendar.AvailabilityController;
import com.airbnb.android.core.controllers.CalendarViewCallbacks;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.AvailabilityConditionRange;
import com.airbnb.android.core.models.CalendarMonth;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.requests.GetAvailabilitiesRequest;
import com.airbnb.android.core.responses.CalendarAvailabilityResponse;
import com.airbnb.android.core.utils.DatesFragmentOptions;
import com.airbnb.android.core.utils.DatesFragmentOptions.Builder;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.ParcelStrap;
import com.airbnb.android.core.views.calendar.CalendarView;
import com.airbnb.android.core.views.calendar.CalendarView.AdditionalUnavailabilityInfoProvider;
import com.airbnb.android.core.views.calendar.CalendarView.DateRangeChangeListener;
import com.airbnb.android.core.views.calendar.CalendarView.Style;
import com.airbnb.android.core.views.calendar.DateRangeModel;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import icepick.State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import p032rx.Observer;

public class DatesFragment extends AirFragment implements AdditionalUnavailabilityInfoProvider, DateRangeChangeListener {
    private static final String ARG_OPTIONS = "options";
    @State
    boolean allowReset;
    @State
    ArrayList<CalendarMonth> availability;
    @BindView
    CalendarView calendarView;
    @BindView
    View container;
    private CalendarViewCallbacks controller;
    private DatesFragmentOptions datesFragmentOptions;
    @State
    AirDate endDate;
    @State
    boolean formatWithYear;
    @State
    String hostName;
    final RequestListener<CalendarAvailabilityResponse> listener = new C0699RL().onResponse(DatesFragment$$Lambda$1.lambdaFactory$(this)).onError(DatesFragment$$Lambda$2.lambdaFactory$(this)).build();
    @State
    int minimumNights;
    @State
    String minimumNightsString;
    private NavigationTag navigationTag;
    @State
    AirDate startDate;
    @State
    Style style;
    @BindView
    AirToolbar toolbar;

    public static DatesFragment forDates(AirDate start, AirDate end, NavigationTag source) {
        return getInstance(getDefaultOptionsBuilder(start, end, source));
    }

    public static DatesFragment forResy(AirDate date, NavigationTag source) {
        return getInstance(getDefaultOptionsBuilder(date, null, source).navigationTag(NavigationTag.PlaceResyDatepicker).preventEmptyDates(true).singleDaySelectionMode(true));
    }

    public static DatesFragment forBooking(Listing listing, AirDate start, AirDate end, Style style2, NavigationTag navigationTag2, NavigationTag source, ParcelStrap params) {
        return getInstance(DatesFragmentOptions.builder().listing(listing).startDate(start).endDate(end).style(style2).navigationTag(navigationTag2).sourceTag(source).navigationExtras(params).preventEmptyDates(true));
    }

    public static DatesFragment forListing(Listing listing, AirDate start, AirDate end, NavigationTag source) {
        return getInstance(getDefaultOptionsBuilder(start, end, source).listing(listing).preventEmptyDates(true));
    }

    public static DatesFragment forModal(AirDate start, AirDate end, int startDateTitle, int endDateTitle, int saveButtonText, NavigationTag source) {
        return getInstance(getDefaultOptionsBuilder(start, end, source).startDateTitleOverride(startDateTitle).endDateTitleOverride(endDateTitle).saveButtonTextOverride(saveButtonText).formatWithYear(true).preventEmptyDates(true));
    }

    private static Builder getDefaultOptionsBuilder(AirDate start, AirDate end, NavigationTag source) {
        return DatesFragmentOptions.builder().startDate(start).endDate(end).style(Style.WHITE_NEW).navigationTag(NavigationTag.FindDatepicker).sourceTag(source);
    }

    private static DatesFragment getInstance(Builder optionsBuilder) {
        return (DatesFragment) ((FragmentBundleBuilder) FragmentBundler.make(new DatesFragment()).putParcelable(ARG_OPTIONS, optionsBuilder.build())).build();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CalendarViewCallbacks) {
            this.controller = (CalendarViewCallbacks) context;
        } else if (getParentFragment() instanceof CalendarViewCallbacks) {
            this.controller = (CalendarViewCallbacks) getParentFragment();
        } else {
            throw new IllegalStateException("Context must implement CalendarViewCallbacks to use this Calendar");
        }
    }

    public void onDetach() {
        this.controller = null;
        super.onDetach();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container2, Bundle savedInstanceState) {
        View view = inflater.inflate(C0716R.layout.fragment_dates, container2, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        this.datesFragmentOptions = (DatesFragmentOptions) getArguments().getParcelable(ARG_OPTIONS);
        this.navigationTag = this.datesFragmentOptions.navigationTag();
        Listing listing = this.datesFragmentOptions.listing();
        int startDateTitleOverride = this.datesFragmentOptions.startDateTitleOverride();
        int endDateTitleOverride = this.datesFragmentOptions.endDateTitleOverride();
        int saveButtonTextOverride = this.datesFragmentOptions.saveButtonTextOverride();
        if (savedInstanceState == null) {
            this.startDate = this.datesFragmentOptions.startDate();
            this.endDate = this.datesFragmentOptions.endDate();
            this.allowReset = !this.datesFragmentOptions.preventEmptyDates();
            this.formatWithYear = this.datesFragmentOptions.formatWithYear();
            this.style = this.datesFragmentOptions.style();
            AirDate today = AirDate.today();
            if (listing != null) {
                this.hostName = listing.getPrimaryHost() != null ? listing.getPrimaryHost().getName() : getString(C0716R.string.host);
                this.minimumNights = listing.getMinNights();
                this.calendarView.showProgress();
                new GetAvailabilitiesRequest(listing.getId(), today, 12).withListener((Observer) this.listener).execute(this.requestManager);
            }
        }
        this.calendarView.setDateRangeChangeListener(this);
        this.calendarView.setAdditionalUnavailabilityInformationProvider(this);
        if (this.style == Style.WHITE || this.style == Style.WHITE_NEW) {
            this.toolbar.setTheme(3);
        }
        this.calendarView.setup(this.controller, this.startDate, this.endDate, startDateTitleOverride, endDateTitleOverride, saveButtonTextOverride, this.allowReset, this.formatWithYear, this.style);
        if (this.datesFragmentOptions.singleDaySelectionMode()) {
            this.calendarView.forSingleDay();
        }
        if (!ListUtils.isEmpty((Collection<?>) this.availability)) {
            this.calendarView.setAvailabilityController(new AvailabilityController(getResources(), this.availability));
        }
        return view;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C0716R.C0718id.clear) {
            return super.onOptionsItemSelected(item);
        }
        this.calendarView.clearSelectedDates();
        return true;
    }

    static /* synthetic */ void lambda$new$0(DatesFragment datesFragment, CalendarAvailabilityResponse data) {
        datesFragment.calendarView.hidePogress();
        datesFragment.availability = new ArrayList<>(data.months);
        datesFragment.updateCalendarBottomBar();
        datesFragment.calendarView.setAvailabilityController(new AvailabilityController(datesFragment.getResources(), datesFragment.availability));
    }

    static /* synthetic */ void lambda$new$1(DatesFragment datesFragment, AirRequestNetworkException e) {
        NetworkUtil.tryShowErrorWithSnackbar(datesFragment.container, e);
        datesFragment.calendarView.hidePogress();
    }

    public NavigationTag getNavigationTrackingTag() {
        return this.navigationTag;
    }

    public Strap getNavigationTrackingParams() {
        NavigationTag sourceTag = this.datesFragmentOptions.sourceTag();
        ParcelStrap extras = this.datesFragmentOptions.navigationExtras();
        if (extras != null) {
            return extras.mo9946kv(BaseAnalytics.FROM, sourceTag.trackingName).internal();
        }
        return super.getNavigationTrackingParams().mo11639kv(BaseAnalytics.FROM, sourceTag.trackingName);
    }

    public void onDateRangeChanged(DateRangeModel dateRangeModel) {
        this.startDate = dateRangeModel.getCheckInDate();
        this.endDate = dateRangeModel.getCheckOutDate();
        if (this.availability != null) {
            updateCalendarBottomBar();
        }
    }

    private void updateCalendarBottomBar() {
        if (this.startDate == null) {
            this.calendarView.setBottomBarText(getMinimumNights());
        } else if (this.endDate == null) {
            this.calendarView.setBottomBarText(getMinimumNightsForStartDate(this.startDate));
        } else {
            int days = this.startDate.getDaysUntil(this.endDate);
            this.calendarView.setBottomBarText(getResources().getQuantityString(C0716R.plurals.calendar_nights_selected, days, new Object[]{Integer.valueOf(days)}));
        }
    }

    private String getMinimumNights() {
        if (this.minimumNightsString != null) {
            return this.minimumNightsString;
        }
        Iterator it = this.availability.iterator();
        while (it.hasNext()) {
            Iterator it2 = ((CalendarMonth) it.next()).getConditionRanges().iterator();
            while (true) {
                if (it2.hasNext()) {
                    if (((AvailabilityConditionRange) it2.next()).getConditions().getMinNights() != this.minimumNights) {
                        this.minimumNightsString = getString(C0716R.string.availability_calendar_host_minimum_night_varies);
                        return this.minimumNightsString;
                    }
                }
            }
        }
        this.minimumNightsString = stringForMinimumNights(this.minimumNights);
        return this.minimumNightsString;
    }

    private String getMinimumNightsForStartDate(AirDate startDate2) {
        Iterator it = this.availability.iterator();
        while (it.hasNext()) {
            Iterator it2 = ((CalendarMonth) it.next()).getConditionRanges().iterator();
            while (true) {
                if (it2.hasNext()) {
                    AvailabilityConditionRange range = (AvailabilityConditionRange) it2.next();
                    if (startDate2.isSameDayOrAfter(range.getStartDate()) && startDate2.isSameDayOrBefore(range.getEndDate())) {
                        return stringForMinimumNights(range.getConditions().getMinNights());
                    }
                }
            }
        }
        return stringForMinimumNights(this.minimumNights);
    }

    private String stringForMinimumNights(int minimumNights2) {
        if (minimumNights2 == 1) {
            return null;
        }
        return getResources().getQuantityString(C0716R.plurals.calendar_host_less_than_min_nights, minimumNights2, new Object[]{this.hostName, Integer.valueOf(minimumNights2)});
    }

    public String getListingHostName() {
        return this.hostName;
    }
}
