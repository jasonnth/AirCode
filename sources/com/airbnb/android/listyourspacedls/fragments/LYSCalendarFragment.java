package com.airbnb.android.listyourspacedls.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.p000v4.util.LongSparseArray;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.calendar.CalendarDays;
import com.airbnb.android.core.calendar.CalendarStore;
import com.airbnb.android.core.calendar.CalendarStoreListener;
import com.airbnb.android.core.calendar.CalendarUpdateListener;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.CalendarDay;
import com.airbnb.android.core.models.NightCount;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.listing.LYSStep;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.android.listyourspacedls.ListYourSpaceDLSGraph;
import com.airbnb.android.sharedcalendar.adapters.CalendarGridAdapter;
import com.airbnb.android.sharedcalendar.adapters.CalendarGridAdapter.Mode;
import com.airbnb.android.sharedcalendar.listeners.OnGridDayClickListener;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import icepick.State;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class LYSCalendarFragment extends LYSBaseFragment {
    private static final long INVALID_LISTING_ID = -1;
    /* access modifiers changed from: private */
    public CalendarGridAdapter adapter;
    protected CalendarStore calendarStore;
    protected CalendarStoreListener calendarStoreListener = new CalendarStoreListener() {
        public void onResponse(LongSparseArray<CalendarDays> calendarDaysByListingId, LongSparseArray<NightCount> longSparseArray, AirDate startDate, AirDate endDate) {
            LYSCalendarFragment.this.controller.showLoadingOverlay(false);
            CalendarDays newCalendarDays = (CalendarDays) calendarDaysByListingId.get(LYSCalendarFragment.this.controller.getListing().getId());
            if (newCalendarDays != null && !newCalendarDays.isEmpty()) {
                LYSCalendarFragment.this.adapter.setCalendarData(newCalendarDays, newCalendarDays.getMinDate(), newCalendarDays.getMaxDate());
            }
        }

        public void onError(NetworkException e) {
            LYSCalendarFragment.this.controller.showLoadingOverlay(false);
            NetworkUtil.tryShowRetryableErrorWithSnackbar(LYSCalendarFragment.this.getView(), e, LYSCalendarFragment$2$$Lambda$1.lambdaFactory$(this));
        }
    };
    protected final CalendarUpdateListener calendarUpdateListener = new CalendarUpdateListener() {
        public void onCalendarUpdateSuccess(Set<Long> set, AirDate startDate, AirDate endDate) {
            if (LYSCalendarFragment.this.isResumed()) {
                LYSCalendarFragment.this.setLoadingFinished(true, null);
                LYSCalendarFragment.this.daysToSetAvailable.clear();
                LYSCalendarFragment.this.daysToSetUnavailable.clear();
                LYSCalendarFragment.this.navigateInFlow(LYSStep.CalendarStep);
            }
        }

        public void onCalendarError(NetworkException e) {
            if (LYSCalendarFragment.this.isResumed()) {
                LYSCalendarFragment.this.setLoadingFinished(false, null);
                NetworkUtil.tryShowErrorWithSnackbar(LYSCalendarFragment.this.getView(), e);
            }
        }
    };
    @State
    CalendarDays daysToSetAvailable;
    @State
    CalendarDays daysToSetUnavailable;
    @BindView
    AirButton nextButton;
    private final OnGridDayClickListener onGridDayClickListener = LYSCalendarFragment$$Lambda$1.lambdaFactory$(this);
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static LYSCalendarFragment newInstance() {
        return new LYSCalendarFragment();
    }

    static /* synthetic */ void lambda$new$0(LYSCalendarFragment lYSCalendarFragment, CalendarDay calendarDay) {
        calendarDay.toggleAvailability();
        if (calendarDay.isAvailable()) {
            if (lYSCalendarFragment.daysToSetUnavailable.has(calendarDay.getDate())) {
                lYSCalendarFragment.daysToSetUnavailable.remove(calendarDay);
            } else {
                lYSCalendarFragment.daysToSetAvailable.add(calendarDay);
            }
        } else if (lYSCalendarFragment.daysToSetAvailable.has(calendarDay.getDate())) {
            lYSCalendarFragment.daysToSetAvailable.remove(calendarDay);
        } else {
            lYSCalendarFragment.daysToSetUnavailable.add(calendarDay);
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            this.daysToSetAvailable = new CalendarDays(-1);
            this.daysToSetUnavailable = new CalendarDays(-1);
        }
        this.adapter = new CalendarGridAdapter(getContext(), Mode.ListYourSpace);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(C7251R.layout.lys_dls_toolbar_and_recycler_view, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        this.recyclerView.setAdapter(this.adapter);
        this.adapter.setOnGridDayClickListener(this.onGridDayClickListener);
        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        ((ListYourSpaceDLSGraph) CoreApplication.instance(getContext()).component()).inject(this);
    }

    public void onResume() {
        super.onResume();
        this.calendarStoreListener.setEnabled(true);
        getOrReloadCalendar();
    }

    public void onPause() {
        super.onPause();
        this.calendarStoreListener.setEnabled(false);
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void onClickNext() {
        this.userAction = UserAction.GoToNext;
        updateCalendar();
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return !this.daysToSetAvailable.isEmpty() || !this.daysToSetUnavailable.isEmpty();
    }

    /* access modifiers changed from: protected */
    public void onSaveActionPressed() {
        updateCalendar();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.LYSCalendar;
    }

    /* access modifiers changed from: private */
    public void getOrReloadCalendar() {
        this.controller.showLoadingOverlay(true);
        AirDate startDate = AirDate.today().getFirstDayOfMonth();
        this.calendarStore.getDaysForListingIds(Collections.singleton(Long.valueOf(this.controller.getListing().getId())), startDate, startDate.plusYears(1).getLastDayOfMonth(), this.controller.shouldReloadCalendar(), this.calendarStoreListener, true);
        this.controller.setShouldReloadCalendar(false);
    }

    private void updateCalendar() {
        if (canSaveChanges()) {
            setLoading(null);
            this.calendarStore.updateCalendarAvailability(this.controller.getListing().getId(), new ArrayList(this.daysToSetAvailable.getCalendarDays()), new ArrayList(this.daysToSetUnavailable.getCalendarDays()), this.calendarUpdateListener);
            return;
        }
        navigateInFlow(LYSStep.CalendarStep);
    }
}
