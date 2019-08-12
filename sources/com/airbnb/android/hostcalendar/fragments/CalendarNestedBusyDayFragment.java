package com.airbnb.android.hostcalendar.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.CalendarJitneyLogger;
import com.airbnb.android.core.calendar.CalendarStore;
import com.airbnb.android.core.enums.ROLaunchSource;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.intents.HostCalendarIntents;
import com.airbnb.android.core.intents.HostCalendarIntents.ArgumentConstants;
import com.airbnb.android.core.intents.HostReservationObjectIntents;
import com.airbnb.android.core.models.CalendarDay;
import com.airbnb.android.core.models.NestedBusyDetail;
import com.airbnb.android.core.models.NestedListing;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.android.hostcalendar.HostCalendarGraph;
import com.airbnb.android.hostcalendar.activities.HostCalendarUpdateActivity;
import com.airbnb.android.hostcalendar.adapters.NestedListingViewAdapter;
import com.airbnb.android.hostcalendar.adapters.NestedListingViewAdapter.NestedListingViewListener;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import java.util.ArrayList;
import java.util.Collection;

public class CalendarNestedBusyDayFragment extends AirFragment implements ArgumentConstants {
    public static final int REQ_CODE_EDIT_NOTES = 100;
    private NestedListingViewListener actionListener = CalendarNestedBusyDayFragment$$Lambda$1.lambdaFactory$(this);
    private NestedListingViewAdapter adapter;
    private MenuItem addOrEditNoteLink;
    @BindView
    AirButton button;
    private CalendarDay calendarDay;
    CalendarStore calendarStore;
    private String hostNotes;
    CalendarJitneyLogger jitneyLogger;
    private long listingId;
    private NestedBusyDetail nestedBusyDetail;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static CalendarNestedBusyDayFragment newInstance(long listingId2, ArrayList<CalendarDay> calendarDays) {
        boolean z;
        boolean z2;
        boolean z3 = true;
        if (listingId2 != -1) {
            z = true;
        } else {
            z = false;
        }
        Check.argument(z);
        Check.notNull(calendarDays);
        if (calendarDays.size() == 1) {
            z2 = true;
        } else {
            z2 = false;
        }
        Check.argument(z2);
        if (ListUtils.isEmpty((Collection<?>) ((CalendarDay) calendarDays.get(0)).getNestedBusyDetails())) {
            z3 = false;
        }
        Check.argument(z3);
        return (CalendarNestedBusyDayFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new CalendarNestedBusyDayFragment()).putLong("listing_id", listingId2)).putParcelableArrayList(ArgumentConstants.ARG_CALENDAR_DAYS, calendarDays)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((HostCalendarGraph) CoreApplication.instance(getContext()).component()).inject(this);
        this.listingId = getArguments().getLong("listing_id");
        this.calendarDay = (CalendarDay) getArguments().getParcelableArrayList(ArgumentConstants.ARG_CALENDAR_DAYS).get(0);
        this.nestedBusyDetail = (NestedBusyDetail) this.calendarDay.getNestedBusyDetails().get(0);
        this.hostNotes = this.calendarDay.getNotes();
        this.adapter = new NestedListingViewAdapter(getContext(), this.nestedBusyDetail, this.calendarDay.getDate(), this.hostNotes, this.actionListener);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String string;
        View view = inflater.inflate(C6418R.layout.fragment_host_calendar_nested_busy_day, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        this.recyclerView.setAdapter(this.adapter);
        AirButton airButton = this.button;
        if (this.nestedBusyDetail.getReservationId() != null) {
            string = getContext().getString(C6418R.string.calendar_nested_listings_reservation_button);
        } else {
            string = getContext().getString(C6418R.string.calendar_nested_listings_see_calendar_button);
        }
        airButton.setText(string);
        this.button.setVisibility(0);
        return view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        this.toolbar.onCreateOptionsMenu(menu, inflater);
        this.addOrEditNoteLink = menu.findItem(C6418R.C6420id.add_edit_note_link);
        initAddOrEditLink();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C6418R.C6420id.add_edit_note_link) {
            return false;
        }
        this.jitneyLogger.editSheetNotesClickedForSingleDay(this.listingId, this.calendarDay, TextUtils.isEmpty(this.hostNotes));
        startActivityForResult(HostCalendarUpdateActivity.intentForUpdateNotesForSingleDay(getContext(), this.listingId, this.calendarDay, this.hostNotes), 100);
        return true;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 100:
                if (resultCode == -1) {
                    this.hostNotes = data.getExtras().getString("notes");
                    initAddOrEditLink();
                    this.adapter.notifyNotesChanged(this.hostNotes);
                    return;
                }
                return;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                return;
        }
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void buttonClicked() {
        if (this.nestedBusyDetail.getReservationId() != null) {
            goToReservation(this.nestedBusyDetail.getReservationId().longValue());
        } else {
            goToCalendar(this.nestedBusyDetail.getNestedListing());
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.CalendarNestedListing;
    }

    public Strap getNavigationTrackingParams() {
        return super.getNavigationTrackingParams().mo11638kv("user_id", this.mAccountManager.getCurrentUserId()).mo11638kv("listing_id", this.listingId).mo11639kv("date_selected", this.calendarDay.getDate().getIsoDateString());
    }

    public void goToReservation(long reservationId) {
        startActivity(HostReservationObjectIntents.intentForReservationId(getActivity(), reservationId, ROLaunchSource.NestedListing));
    }

    public void goToCalendar(NestedListing listing) {
        startActivity(HostCalendarIntents.intentForSingleListingCalendar(getActivity(), listing.getId(), listing.getName()));
    }

    private void initAddOrEditLink() {
        if (this.addOrEditNoteLink == null) {
            return;
        }
        if (TextUtils.isEmpty(this.hostNotes)) {
            this.addOrEditNoteLink.setTitle(getString(C6418R.string.calendar_update_add_note));
        } else {
            this.addOrEditNoteLink.setTitle(getString(C6418R.string.calendar_update_edit_note));
        }
    }
}
