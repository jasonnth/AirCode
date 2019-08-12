package com.airbnb.android.lib.presenters;

import android.content.Context;
import android.content.res.Resources;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.airdate.AirMonth;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.utils.DateHelper;
import com.airbnb.android.core.utils.SpannableUtils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.primitives.fonts.Font;
import com.airbnb.p027n2.utils.TextUtil;
import com.google.common.collect.Collections2;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ReservationPresenter {
    private static SimpleDateFormat sMonthDaySdf;
    private static final AirDate today = AirDate.today();

    public static String getDates(Context context, Reservation reservation) {
        if (sMonthDaySdf == null) {
            sMonthDaySdf = new SimpleDateFormat(context.getString(C0880R.string.date_name_format));
        }
        return context.getString(C0880R.string.calendar_setting_date_range, new Object[]{reservation.getStartDate().formatDate((DateFormat) sMonthDaySdf), reservation.getEndDate().formatDate((DateFormat) sMonthDaySdf)});
    }

    private static String getGuests(Context context, Reservation reservation) {
        int guestCount = reservation.getGuestCount();
        return context.getResources().getQuantityString(C0880R.plurals.x_guests, guestCount, new Object[]{Integer.valueOf(guestCount)});
    }

    public static String getDatesAndGuestCount(Context context, Reservation reservation) {
        return context.getString(C0880R.string.bullet_with_space_parameterized, new Object[]{getDates(context, reservation), getGuests(context, reservation)});
    }

    public static String getGuestArriveDepartString(Context context, Reservation reservation, boolean arriving) {
        return context.getResources().getString(C0880R.string.bullet_with_space_parameterized, new Object[]{reservation.getGuest().getFirstName(), context.getResources().getString(arriving ? C0880R.string.arrives : C0880R.string.departs)});
    }

    public static CharSequence getReservationPrimaryText(Context context, Reservation reservation, boolean isArrival) {
        Resources resources = context.getResources();
        String guestName = reservation.getGuest().getFirstName();
        String arrival = resources.getString(C0880R.string.arrival);
        String departure = resources.getString(C0880R.string.departure);
        if (isArrival) {
            return SpannableUtils.makeColoredSubstring(resources.getString(C0880R.string.bullet_with_space_parameterized, new Object[]{SpannableUtils.COLORED_SUBSTRING_TOKEN, guestName}), arrival, resources.getColor(C0880R.color.c_babu));
        }
        return resources.getString(C0880R.string.bullet_with_space_parameterized, new Object[]{departure, guestName});
    }

    public static String getMonthReservationHeaderText(Context context, Reservation reservation, boolean arrival) {
        Resources resources = context.getResources();
        AirDate headerDate = arrival ? reservation.getCheckinDate() : reservation.getCheckoutDate();
        if (headerDate.equals(today)) {
            return resources.getString(C0880R.string.today);
        }
        if (today.getDaysUntil(headerDate) == 1) {
            return resources.getString(C0880R.string.tomorrow);
        }
        return headerDate.formatDate((DateFormat) new SimpleDateFormat(resources.getString(C0880R.string.md_with_abbr_day_name), Locale.getDefault()));
    }

    public static CharSequence getReservationInfoText(Context context, Reservation reservation) {
        Resources resources = context.getResources();
        String dateRangeString = DateHelper.getStringDateSpan(context, reservation.getCheckinDate(), reservation.getCheckoutDate());
        String guestCount = resources.getQuantityString(C0880R.plurals.x_guests, reservation.getGuestCount(), new Object[]{Integer.valueOf(reservation.getGuestCount())});
        return TextUtil.changeFontFamily(context, Font.CircularBook, resources.getString(C0880R.string.bullet_with_space_parameterized, new Object[]{dateRangeString, guestCount}), dateRangeString);
    }

    public static List<Reservation> filterBySelectedListings(List<Reservation> reservations, List<Long> selectedListingIds) {
        return selectedListingIds == null ? reservations : new ArrayList<>(Collections2.filter(reservations, ReservationPresenter$$Lambda$1.lambdaFactory$(selectedListingIds)));
    }

    static /* synthetic */ boolean lambda$filterBySelectedListings$0(List selectedListingIds, Reservation r) {
        return r != null && selectedListingIds.contains(Long.valueOf(r.getListing().getId()));
    }

    public static Map<AirDate, List<Reservation>> generateDateReservationMap(List<Reservation> reservations) {
        Map<AirDate, List<Reservation>> map = new HashMap<>();
        for (Reservation reservation : reservations) {
            addToMap(reservation, reservation.getCheckinDate(), map);
            addToMap(reservation, reservation.getCheckoutDate(), map);
        }
        return map;
    }

    public static Map<AirMonth, List<Reservation>> generateMonthReservationMap(List<Reservation> reservations) {
        Map<AirMonth, List<Reservation>> map = new HashMap<>();
        for (Reservation reservation : reservations) {
            AirMonth checkinMonth = new AirMonth(reservation.getCheckinDate().getYear(), reservation.getCheckinDate().getMonthOfYear());
            AirMonth checkoutMonth = new AirMonth(reservation.getCheckoutDate().getYear(), reservation.getCheckoutDate().getMonthOfYear());
            addToMap(reservation, checkinMonth, map);
            addToMap(reservation, checkoutMonth, map);
        }
        return map;
    }

    private static <T> void addToMap(final Reservation reservation, T date, Map<T, List<Reservation>> map) {
        if (map.containsKey(date)) {
            List<Reservation> reservationsForDate = (List) map.get(date);
            if (!reservationsForDate.contains(reservation)) {
                reservationsForDate.add(reservation);
                return;
            }
            return;
        }
        map.put(date, new ArrayList<Reservation>() {
            {
                add(reservation);
            }
        });
    }
}
