package com.airbnb.android.hostcalendar.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.content.ContextCompat;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindDimen;
import butterknife.BindView;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.airdate.AirMonth;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.calendar.CalendarDays;
import com.airbnb.android.core.enums.HelpCenterArticle;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.intents.HelpCenterIntents;
import com.airbnb.android.core.models.NightCount;
import com.airbnb.android.core.utils.SpannableUtils;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.android.hostcalendar.CalendarDateRange;
import com.airbnb.android.sharedcalendar.adapters.CalendarGridAdapter;
import com.airbnb.android.sharedcalendar.adapters.CalendarGridAdapter.Mode;
import com.airbnb.android.sharedcalendar.listeners.OnboardingOverlayListener;
import com.airbnb.android.sharedcalendar.listeners.SingleCalendarListener;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.n2.R;
import com.airbnb.p027n2.components.InlineTipRow;
import com.airbnb.p027n2.components.onboarding_overlay.OnboardingOverlayViewController;
import java.util.Set;

public class SingleCalendarMonthFragment extends SingleCalendarBaseFragment {
    private static final int DAYS_OF_HALF_WEEK = 3;
    private static final int DAYS_OF_WEEK = 7;
    private static final int ONBOARDING_OVERLAY_SHOW_ON_SEEN_TIMES = 2;
    private CalendarGridAdapter adapter;
    @BindView
    InlineTipRow inlineTipRow;
    private final OnboardingOverlayListener onboardingOverlayListener = SingleCalendarMonthFragment$$Lambda$1.lambdaFactory$(this);
    @BindView
    RecyclerView recyclerView;
    @BindDimen
    int tab_bar_height;

    public static SingleCalendarMonthFragment calendarForDates(long listingId, CalendarDateRange calendarDateRange) {
        return (SingleCalendarMonthFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new SingleCalendarMonthFragment()).putLong("listing_id", listingId)).putParcelable("date_range", calendarDateRange)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C6418R.layout.fragment_host_calendar_month, container, false);
        bindViews(view);
        resizeWidthIfLandscape(view);
        this.adapter = new CalendarGridAdapter(getContext(), Mode.SingleCanlendarMonth);
        this.adapter.setInfiniteScrollListener(this.infiniteScrollListener);
        this.adapter.setOnboardingOverlayListener(this.onboardingOverlayListener);
        bindSingleCalendarListener(this.singleCalendarListener);
        this.recyclerView.setAdapter(this.adapter);
        return view;
    }

    private void resizeWidthIfLandscape(final View view) {
        view.post(new Runnable() {
            public void run() {
                if (SingleCalendarMonthFragment.this.recyclerView != null) {
                    int w = view.getWidth();
                    int h = view.getHeight();
                    if (w > h) {
                        int padding = (int) (((double) ((w - h) - SingleCalendarMonthFragment.this.tab_bar_height)) / 2.0d);
                        SingleCalendarMonthFragment.this.recyclerView.setPadding(padding, 0, padding, 0);
                    }
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void bindSingleCalendarListener(SingleCalendarListener singleCalendarListener) {
        if (this.adapter != null) {
            this.adapter.setSingleCalendarListener(singleCalendarListener);
        }
    }

    /* access modifiers changed from: protected */
    public void updateAdapter(CalendarDays calendarDays, AirDate minDate, AirDate maxDate) {
        this.adapter.setCalendarData(calendarDays, minDate, maxDate);
    }

    /* access modifiers changed from: protected */
    public boolean scrollToTargetDate(AirDate targetDate) {
        final int position = this.adapter.getPositionOfMonth(new AirMonth(targetDate));
        if (position <= -1) {
            return false;
        }
        final LinearLayoutManager layoutManager = (LinearLayoutManager) this.recyclerView.getLayoutManager();
        layoutManager.scrollToPositionWithOffset(position, 0);
        if (position >= this.recyclerView.getChildCount()) {
            this.recyclerView.postDelayed(new Runnable() {
                public void run() {
                    layoutManager.scrollToPositionWithOffset(position, 0);
                }
            }, 100);
        }
        return true;
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.Ignore;
    }

    public void clearEditMode(Set<AirDate> dates) {
        this.adapter.clearEditMode(dates);
    }

    /* access modifiers changed from: protected */
    public void logScrollForward() {
    }

    /* access modifiers changed from: protected */
    public void showScrollLoaderIsLoading(boolean show, boolean isLoading) {
        this.adapter.showScrollLoaderIsLoading(show, isLoading);
    }

    /* access modifiers changed from: protected */
    public void showWarningIfBookedNightsExceedsLimit(NightCount count) {
        if (count.hasExceededAllowedNights()) {
            Intent helpIntent = HelpCenterIntents.intentForHelpCenterArticle(getContext(), HelpCenterArticle.CALENDAR_NIGHTS_ENFORCEMENT).toIntent();
            this.inlineTipRow.setText(getTipRowTextBuilder(count));
            this.inlineTipRow.setVisibility(0);
            this.recyclerView.setPadding(0, 0, 0, 0);
            this.inlineTipRow.setOnClickListener(SingleCalendarMonthFragment$$Lambda$2.lambdaFactory$(this, helpIntent));
            this.inlineTipRow.setCloseClickListener(SingleCalendarMonthFragment$$Lambda$3.lambdaFactory$(this));
        }
    }

    static /* synthetic */ void lambda$showWarningIfBookedNightsExceedsLimit$2(SingleCalendarMonthFragment singleCalendarMonthFragment, View v) {
        singleCalendarMonthFragment.inlineTipRow.setVisibility(8);
        singleCalendarMonthFragment.recyclerView.setPadding(0, singleCalendarMonthFragment.getResources().getDimensionPixelOffset(C6418R.dimen.tab_bar_height), 0, 0);
    }

    private SpannableStringBuilder getTipRowTextBuilder(NightCount count) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        String title = getContext().getString(C6418R.string.calendar_nights_enforcement_title, new Object[]{Integer.valueOf(AirDate.today().getYear())});
        String description = getContext().getString(C6418R.string.calendar_nights_enforcement_content, new Object[]{count.getCityName()});
        String linkText = getContext().getString(C6418R.string.learn_more_info_text);
        if (!TextUtils.isEmpty(title)) {
            builder.append(SpannableUtils.makeBoldedString((CharSequence) title, getContext())).append("\n");
        }
        if (!TextUtils.isEmpty(description)) {
            builder.append(description).append("\n");
        }
        if (!TextUtils.isEmpty(linkText)) {
            builder.append(SpannableUtils.makeColoredString(linkText, ContextCompat.getColor(getContext(), C0716R.color.n2_babu)));
        }
        return builder;
    }

    private int getCenterInRowShapePaddingDp() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return (int) ((0.0f - ((3.0f * (((float) displayMetrics.widthPixels) / displayMetrics.density)) / 7.0f)) + ((float) getResources().getDimensionPixelSize(R.dimen.n2_calendar_date_overlay_target_circle_padding)));
    }

    /* access modifiers changed from: private */
    public void setUpOnboardingOverlay(View view) {
        OnboardingOverlayViewController.show(getActivity(), view, getString(C6418R.string.onboarding_title_for_calendar_date), getString(C6418R.string.onboarding_dismiss_button), "calendar_grid_date", getCenterInRowShapePaddingDp(), 2);
    }
}
