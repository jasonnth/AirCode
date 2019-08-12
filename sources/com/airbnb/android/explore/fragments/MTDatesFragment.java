package com.airbnb.android.explore.fragments;

import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.controllers.CalendarViewCallbacks;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.views.calendar.CalendarView;
import com.airbnb.android.core.views.calendar.CalendarView.DateRangeChangeListener;
import com.airbnb.android.core.views.calendar.CalendarView.Style;
import com.airbnb.android.core.views.calendar.DateRangeModel;
import com.airbnb.android.explore.C0857R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import icepick.State;

public class MTDatesFragment extends BaseExploreFragment implements CalendarViewCallbacks, DateRangeChangeListener {
    private static final String ARG_CHINA_STYLE = "arg_china_style";
    private static final String ARG_SOURCE_TAG = "arg_source_tag";
    @BindView
    View backgroundView;
    @BindView
    CalendarView calendarView;
    @State
    AirDate endDate;
    @State
    boolean isChinaStyle;
    @State
    AirDate startDate;
    @BindView
    AirToolbar toolbar;

    public static MTDatesFragment newInstance(Rect rect) {
        return (MTDatesFragment) ((FragmentBundleBuilder) FragmentBundler.make(new MTDatesFragment()).putParcelable("animate_rect", rect)).build();
    }

    public static MTDatesFragment newInstance(String sourceTag) {
        return (MTDatesFragment) ((FragmentBundleBuilder) FragmentBundler.make(new MTDatesFragment()).putString(ARG_SOURCE_TAG, sourceTag)).build();
    }

    public static MTDatesFragment newInstanceForChina() {
        return (MTDatesFragment) ((FragmentBundleBuilder) FragmentBundler.make(new MTDatesFragment()).putBoolean(ARG_CHINA_STYLE, true)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(C0857R.layout.fragment_mt_dates, container, false);
        bindViews(view);
        this.calendarView.setDateRangeChangeListener(this);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null) {
            this.isChinaStyle = getArguments().getBoolean(ARG_CHINA_STYLE, false);
            this.startDate = this.dataController.getTopLevelSearchParams().checkInDate();
            this.endDate = this.dataController.getTopLevelSearchParams().checkOutDate();
        }
        if (this.isChinaStyle) {
            this.toolbar.setTheme(3);
            this.backgroundView.setBackgroundResource(C0857R.color.n2_white);
            this.calendarView.setup(this, this.startDate, this.endDate, C0857R.string.start_date, C0857R.string.end_date, Style.WHITE);
            return;
        }
        this.calendarView.setup(this, this.startDate, this.endDate, C0857R.string.start_date, C0857R.string.end_date, Style.BABU);
    }

    public Strap getNavigationTrackingParams() {
        String sourceTag = getArguments() != null ? getArguments().getString(ARG_SOURCE_TAG) : null;
        if (!TextUtils.isEmpty(sourceTag)) {
            return super.getNavigationTrackingParams().mo11639kv(BaseAnalytics.FROM, sourceTag);
        }
        return super.getNavigationTrackingParams();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(C0857R.C0859id.reset).setVisible(true);
        if (this.isChinaStyle) {
            menu.findItem(C0857R.C0859id.reset).setTitle(C0857R.string.clear);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C0857R.C0859id.reset) {
            return super.onOptionsItemSelected(item);
        }
        this.calendarView.clearSelectedDates();
        return true;
    }

    public void onCalendarDatesApplied(AirDate start, AirDate end) {
        this.exploreNavigationController.closeModal();
        this.exploreJitneyLogger.selectDates(this.startDate, this.endDate, start, end);
        this.startDate = start;
        this.endDate = end;
        this.dataController.setCheckInCheckOutDates(start, end);
    }

    public void onStartDateClicked(AirDate start) {
    }

    public void onEndDateClicked(AirDate end) {
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.FindDatepicker;
    }

    public void onDateRangeChanged(DateRangeModel dateRangeModel) {
        this.startDate = dateRangeModel.getCheckInDate();
        this.endDate = dateRangeModel.getCheckOutDate();
    }
}
