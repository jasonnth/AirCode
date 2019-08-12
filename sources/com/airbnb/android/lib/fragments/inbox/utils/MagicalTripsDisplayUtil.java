package com.airbnb.android.lib.fragments.inbox.utils;

import android.content.Context;
import android.support.p000v4.content.ContextCompat;
import android.text.SpannableString;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.MagicalTripAttachment;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.core.models.TripStatus;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.SpannableUtils;
import com.airbnb.android.core.utils.UserUtils;
import com.airbnb.android.lib.C0880R;
import com.google.common.base.Predicates;
import com.google.common.collect.FluentIterable;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MagicalTripsDisplayUtil {
    public static String generateNamesString(Context context, Thread thread, User currentUser) {
        return UserUtils.generateNamesString(context, getOrderedUsers(thread, currentUser));
    }

    public static List<User> getOrderedUsers(Thread thread, User currentUser) {
        Map<Long, User> userLookup = FluentIterable.from((Iterable<E>) thread.getUsers()).uniqueIndex(MagicalTripsDisplayUtil$$Lambda$1.lambdaFactory$());
        MagicalTripAttachment attachment = (MagicalTripAttachment) Check.notNull(thread.getAttachment());
        FluentIterable append = FluentIterable.from((Iterable<E>) attachment.getHostIds()).append((Iterable<? extends E>) FluentIterable.from((Iterable<E>) attachment.getGuestsIds()).filter(MagicalTripsDisplayUtil$$Lambda$2.lambdaFactory$(currentUser)));
        userLookup.getClass();
        return append.transform(MagicalTripsDisplayUtil$$Lambda$3.lambdaFactory$(userLookup)).filter(Predicates.notNull()).toList();
    }

    static /* synthetic */ boolean lambda$getOrderedUsers$0(User currentUser, Long id) {
        return currentUser.getId() != id.longValue();
    }

    public static SpannableString generateEventText(Context context, MagicalTripAttachment trip) {
        String statusText = context.getString(getStatusString(trip.getStatus()));
        int color = ContextCompat.getColor(context, getStatusColor(trip.getStatus()));
        AirDateTime startTime = trip.getDetails().getStartsAt();
        AirDateTime endTime = trip.getDetails().getEndsAt();
        if (startTime == null || endTime == null) {
            return SpannableUtils.makeColoredString(statusText, color);
        }
        String dateString = generateDateString(context, startTime, endTime);
        return SpannableUtils.makeColoredSubstring(context.getString(C0880R.string.bullet_with_space_parameterized, new Object[]{SpannableUtils.COLORED_SUBSTRING_TOKEN, dateString}), statusText, color);
    }

    private static String generateDateString(Context context, AirDateTime startTime, AirDateTime endTime) {
        SimpleDateFormat format = new SimpleDateFormat(context.getString(C0880R.string.date_name_format), Locale.getDefault());
        String startString = startTime.formatDate(format);
        String endString = endTime.formatDate(format);
        return context.getString(C0880R.string.separator_with_values, new Object[]{startString, endString});
    }

    public static int getStatusString(TripStatus status) {
        switch (status) {
            case Pending:
                return C0880R.string.magical_trip_status_pending;
            case Active:
            case Accepted:
                return C0880R.string.magical_trip_status_confirmed;
            case Declined:
                return C0880R.string.magical_trip_status_declined;
            case Canceled:
                return C0880R.string.magical_trip_status_canceled;
            case Deleted:
                return C0880R.string.magical_trip_status_deleted;
            case Expired:
                return C0880R.string.magical_trip_status_expired;
            default:
                return C0880R.string.magical_trip_status_unknown;
        }
    }

    private static int getStatusColor(TripStatus status) {
        switch (status) {
            case Pending:
            case Declined:
            case Canceled:
            case Deleted:
            case Expired:
                return C0880R.color.n2_ebisu;
            case Active:
            case Accepted:
                return C0880R.color.n2_babu;
            default:
                return C0880R.color.n2_foggy;
        }
    }
}
