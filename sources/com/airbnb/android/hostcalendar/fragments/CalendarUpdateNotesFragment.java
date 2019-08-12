package com.airbnb.android.hostcalendar.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import butterknife.BindView;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.CalendarJitneyLogger;
import com.airbnb.android.core.calendar.CalendarStore;
import com.airbnb.android.core.calendar.CalendarUpdateListener;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.intents.HostCalendarIntents.ArgumentConstants;
import com.airbnb.android.core.models.CalendarDay;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.android.hostcalendar.HostCalendarGraph;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import java.util.ArrayList;
import java.util.Set;

public class CalendarUpdateNotesFragment extends AirFragment implements ArgumentConstants {
    protected CalendarStore calendarStore;
    private final CalendarUpdateListener calendarUpdateListener = new CalendarUpdateListener() {
        public void onCalendarUpdateSuccess(Set<Long> set, AirDate startDate, AirDate endDate) {
            CalendarUpdateNotesFragment.this.jitneyLogger.noteSaveButtonClicked(Long.valueOf(CalendarUpdateNotesFragment.this.listingId), CalendarUpdateNotesFragment.this.selectedDays, TextUtils.isEmpty(CalendarUpdateNotesFragment.this.getArguments().getString("notes", "")));
            if (CalendarUpdateNotesFragment.this.isResumed()) {
                CalendarUpdateNotesFragment.this.showLoader(false);
                Intent data = new Intent();
                data.putExtra("notes", CalendarUpdateNotesFragment.this.noteText.getText().toString());
                CalendarUpdateNotesFragment.this.getActivity().setResult(-1, data);
                CalendarUpdateNotesFragment.this.getActivity().finish();
            }
        }

        public void onCalendarError(NetworkException e) {
            if (CalendarUpdateNotesFragment.this.isResumed()) {
                CalendarUpdateNotesFragment.this.showLoader(false);
                CalendarUpdateNotesFragment.this.calendarUpdateNotesFrame.setBackgroundColor(ContextCompat.getColor(CalendarUpdateNotesFragment.this.getContext(), C6418R.color.n2_arches));
                NetworkUtil.tryShowRetryableErrorWithSnackbar(CalendarUpdateNotesFragment.this.getView(), e, CalendarUpdateNotesFragment$1$$Lambda$1.lambdaFactory$(this));
            }
        }
    };
    @BindView
    LinearLayout calendarUpdateNotesFrame;
    CalendarJitneyLogger jitneyLogger;
    /* access modifiers changed from: private */
    public long listingId;
    @BindView
    EditText noteText;
    ArrayList<CalendarDay> selectedDays;
    @BindView
    AirToolbar toolbar;

    public static CalendarUpdateNotesFragment newInstance(long listingId2, ArrayList<CalendarDay> daysToUpdate, String existingNotes) {
        return (CalendarUpdateNotesFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new CalendarUpdateNotesFragment()).putLong("listing_id", listingId2)).putParcelableArrayList(ArgumentConstants.ARG_CALENDAR_DAYS, daysToUpdate)).putString("notes", existingNotes)).build();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        ((HostCalendarGraph) CoreApplication.instance(getContext()).component()).inject(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C6418R.layout.fragment_host_calendar_update_notes, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        this.toolbar.setNavigationOnClickListener(CalendarUpdateNotesFragment$$Lambda$1.lambdaFactory$(this));
        this.listingId = getArguments().getLong("listing_id");
        this.selectedDays = getArguments().getParcelableArrayList(ArgumentConstants.ARG_CALENDAR_DAYS);
        this.noteText.setText(getArguments().getString("notes", ""));
        this.noteText.requestFocus();
        this.noteText.postDelayed(CalendarUpdateNotesFragment$$Lambda$4.lambdaFactory$(this), 200);
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$1(CalendarUpdateNotesFragment calendarUpdateNotesFragment) {
        if (calendarUpdateNotesFragment.getActivity() != null) {
            KeyboardUtils.showSoftKeyboard(calendarUpdateNotesFragment.getActivity(), calendarUpdateNotesFragment.noteText);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == C6418R.C6420id.save) {
            save();
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void save() {
        showLoader(true);
        this.calendarStore.updateCalendar(this.listingId, this.selectedDays, null, null, null, this.noteText.getText().toString(), this.calendarUpdateListener);
    }

    public void onPause() {
        super.onPause();
        KeyboardUtils.dismissSoftKeyboard(getActivity(), getView());
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.CalendarNoteView;
    }

    public Strap getNavigationTrackingParams() {
        return super.getNavigationTrackingParams().mo11638kv("listing_id", this.listingId);
    }
}
