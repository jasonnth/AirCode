package com.airbnb.android.hostcalendar.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.intents.HostCalendarIntents.ArgumentConstants;
import com.airbnb.android.core.intents.HostCalendarIntents.CalendarUpdateAction;
import com.airbnb.android.core.models.CalendarDay;
import com.airbnb.android.core.models.CalendarRule;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.android.hostcalendar.adapters.MultiDayPriceTipsEpoxyController.OnPriceTipsDisclaimerClickedListener;
import com.airbnb.android.hostcalendar.fragments.AboutSmartPricingFragment;
import com.airbnb.android.hostcalendar.fragments.CalendarNestedBusyDayFragment;
import com.airbnb.android.hostcalendar.fragments.CalendarUpdateNotesFragment;
import com.airbnb.android.hostcalendar.fragments.CalendarWithPriceTipsUpdateFragment;
import com.airbnb.android.hostcalendar.fragments.MultiDayPriceTipsFragment;
import com.airbnb.android.hostcalendar.fragments.PriceTipsDisclaimerFragment;
import com.airbnb.android.hostcalendar.fragments.SingleCalendarFragmentPager.TabType;
import com.airbnb.android.utils.ListUtils;
import icepick.State;
import java.util.ArrayList;
import java.util.Collection;

public class HostCalendarUpdateActivity extends AirActivity implements ArgumentConstants, OnPriceTipsDisclaimerClickedListener {
    private static String ARG_UPDATE_ACTION = "update_action";
    @State
    long listingId;

    public static Intent intentForUpdateWithCalendarRule(Context context, long listingId2, ArrayList<CalendarDay> daysToUpdate, TabType tabType, CalendarRule calendarRule) {
        Check.state(listingId2 != -1);
        Check.notNull(daysToUpdate);
        Check.notEmpty(daysToUpdate);
        Check.notNull(calendarRule);
        return new Intent(context, HostCalendarUpdateActivity.class).putExtra("listing_id", listingId2).putParcelableArrayListExtra(ArgumentConstants.ARG_CALENDAR_DAYS, daysToUpdate).putExtra(ArgumentConstants.ARG_TAB_TYPE, tabType).putExtra(ArgumentConstants.ARG_CALENDAR_RULE, calendarRule).putExtra(ARG_UPDATE_ACTION, CalendarUpdateAction.UpdatePrice);
    }

    public static Intent intentForMultiDayPriceTips(Context context, long listingId2, ArrayList<CalendarDay> calendarDays, boolean appliedPriceTips) {
        Check.state(listingId2 != -1);
        Check.notEmpty(calendarDays);
        return new Intent(context, HostCalendarUpdateActivity.class).putExtra("listing_id", listingId2).putParcelableArrayListExtra(ArgumentConstants.ARG_CALENDAR_DAYS, calendarDays).putExtra(ARG_UPDATE_ACTION, CalendarUpdateAction.MultiDayPriceTips).putExtra(ArgumentConstants.ARG_APPLIED_PRICE_TIPS, appliedPriceTips);
    }

    public static Intent intentForUpdate(Context context, long listingId2, ArrayList<CalendarDay> daysToUpdate, TabType tabType) {
        Check.state(listingId2 != -1);
        Check.notNull(daysToUpdate);
        Check.notEmpty(daysToUpdate);
        return new Intent(context, HostCalendarUpdateActivity.class).putExtra("listing_id", listingId2).putParcelableArrayListExtra(ArgumentConstants.ARG_CALENDAR_DAYS, daysToUpdate).putExtra(ArgumentConstants.ARG_TAB_TYPE, tabType).putExtra(ARG_UPDATE_ACTION, CalendarUpdateAction.UpdatePrice);
    }

    public static Intent intentForUpdateNotes(Context context, long listingId2, ArrayList<CalendarDay> daysToUpdate, String existingNotes) {
        Check.state(listingId2 != -1);
        Check.notNull(daysToUpdate);
        Check.notEmpty(daysToUpdate);
        return new Intent(context, HostCalendarUpdateActivity.class).putExtra("listing_id", listingId2).putParcelableArrayListExtra(ArgumentConstants.ARG_CALENDAR_DAYS, daysToUpdate).putExtra("notes", existingNotes).putExtra(ARG_UPDATE_ACTION, CalendarUpdateAction.Notes);
    }

    public static Intent intentForUpdateNotesForSingleDay(Context context, long listingId2, CalendarDay dayToUpdate, String existingNotes) {
        ArrayList<CalendarDay> calendarDays = new ArrayList<>();
        calendarDays.add(dayToUpdate);
        return intentForUpdateNotes(context, listingId2, calendarDays, existingNotes);
    }

    public static Intent intentForNestedListingView(Context context, long listingId2, CalendarDay calendarDay) {
        Check.state(listingId2 != -1);
        Check.notNull(calendarDay);
        ArrayList<CalendarDay> calendarDays = new ArrayList<>();
        calendarDays.add(calendarDay);
        return new Intent(context, HostCalendarUpdateActivity.class).putExtra("listing_id", listingId2).putExtra(ArgumentConstants.ARG_CALENDAR_DAYS, calendarDays).putExtra(ARG_UPDATE_ACTION, CalendarUpdateAction.NestedListing);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C6418R.layout.activity_host_calendar_blank);
        if (savedInstanceState == null) {
            showCalendarUpdateFragment();
        }
    }

    /* access modifiers changed from: protected */
    public boolean hasCustomEnterTransition() {
        return true;
    }

    private void showCalendarUpdateFragment() {
        Fragment fragment;
        Intent intent = getIntent();
        this.listingId = intent.getLongExtra("listing_id", -1);
        ArrayList<CalendarDay> daysToUpdate = intent.getParcelableArrayListExtra(ArgumentConstants.ARG_CALENDAR_DAYS);
        String existingNotes = intent.getStringExtra("notes");
        boolean appliedPriceTips = intent.getBooleanExtra(ArgumentConstants.ARG_APPLIED_PRICE_TIPS, false);
        TabType tabType = (TabType) intent.getSerializableExtra(ArgumentConstants.ARG_TAB_TYPE);
        CalendarRule calendarRule = (CalendarRule) intent.getParcelableExtra(ArgumentConstants.ARG_CALENDAR_RULE);
        switch ((CalendarUpdateAction) intent.getSerializableExtra(ARG_UPDATE_ACTION)) {
            case Notes:
                fragment = CalendarUpdateNotesFragment.newInstance(this.listingId, daysToUpdate, existingNotes);
                break;
            case NestedListing:
                if (FeatureToggles.showNestedListings() && daysToUpdate.size() == 1 && !ListUtils.isEmpty((Collection<?>) ((CalendarDay) daysToUpdate.get(0)).getNestedBusyDetails())) {
                    fragment = CalendarNestedBusyDayFragment.newInstance(this.listingId, daysToUpdate);
                    break;
                }
            case MultiDayPriceTips:
                fragment = MultiDayPriceTipsFragment.newInstance(daysToUpdate, appliedPriceTips, false);
                break;
            default:
                fragment = CalendarWithPriceTipsUpdateFragment.newInstance(this.listingId, daysToUpdate, tabType, calendarRule);
                break;
        }
        showFragment(fragment, C6418R.C6420id.content_container, FragmentTransitionType.SlideFromBottom, true);
    }

    public void showAboutSmartPricingFragment() {
        showModal(AboutSmartPricingFragment.newInstance(this.listingId), C6418R.C6420id.content_container, C6418R.C6420id.modal_container, true);
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }

    public void onDisclaimerClicked() {
        showModal(new PriceTipsDisclaimerFragment(), C6418R.C6420id.content_container, C6418R.C6420id.modal_container, true, null);
    }
}
