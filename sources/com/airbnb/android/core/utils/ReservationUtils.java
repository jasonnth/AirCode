package com.airbnb.android.core.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.provider.CalendarContract.Events;
import android.text.TextUtils;
import android.widget.Toast;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.calendar.CalendarDays;
import com.airbnb.android.core.models.CalendarDay;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.User;
import com.airbnb.android.utils.Strap;

public class ReservationUtils {
    public static final long INVALID_ID = -1;

    public static void launchIntentForExportToCalendar(Context context, Reservation reservation) {
        AirbnbEventLogger.track("android_eng", Strap.make().mo11639kv("export_itinerary_to_calendar", "send_intent"));
        User otherUser = reservation.isUserHost(CoreApplication.instance(context).component().accountManager().getCurrentUser()) ? reservation.getGuest() : reservation.getHost();
        String itineraryLink = context.getString(C0716R.string.airbnb_reservation_itinerary_url, new Object[]{reservation.getConfirmationCode()});
        String otherUserInfo = context.getString(C0716R.string.export_reservation_to_calendar_name, new Object[]{otherUser.getFirstName()});
        if (!TextUtils.isEmpty(otherUser.getEmailAddress())) {
            otherUserInfo = otherUserInfo + "\n" + otherUser.getEmailAddress();
        }
        if (!TextUtils.isEmpty(otherUser.getPhone())) {
            otherUserInfo = otherUserInfo + "\n" + otherUser.getPhone();
        }
        try {
            context.startActivity(new Intent("android.intent.action.INSERT").setData(Events.CONTENT_URI).putExtra("title", reservation.getListing().getName()).putExtra("description", itineraryLink + "\n\n" + otherUserInfo).putExtra("beginTime", reservation.getCheckinTime().getMillis()).putExtra("endTime", reservation.getCheckoutTime().getMillis()).putExtra("eventTimezone", reservation.getListing().getTimeZoneName()).putExtra("eventLocation", reservation.getListing().getAddress()));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, C0716R.string.export_to_calendar_not_found, 0).show();
        }
    }

    public static boolean isDateRangeUnblocked(AirDate startDate, AirDate endDate, CalendarDays days) {
        AirDate date = startDate;
        while (date.isSameDayOrBefore(endDate)) {
            CalendarDay day = days.get(date);
            if (day == null) {
                BugsnagWrapper.notify((Throwable) new IllegalStateException("Missing date in CalendarDays " + date.getIsoDateString() + " listing id " + days.getListingId()));
                return true;
            } else if (!day.isAvailable()) {
                return false;
            } else {
                date = date.plusDays(1);
            }
        }
        return true;
    }
}
