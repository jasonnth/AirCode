package com.airbnb.android.lib.fragments.inbox.utils;

import android.content.Context;
import android.support.p000v4.content.ContextCompat;
import android.util.Log;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.ListingSummary;
import com.airbnb.android.core.models.Post;
import com.airbnb.android.core.models.ReservationStatus;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.utils.MessagingUtil;
import com.airbnb.android.core.utils.ReservationStatusDisplay;
import com.airbnb.android.core.utils.SpannableUtils;
import com.airbnb.android.core.utils.ThreadUtils;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.C0880R;

public class HostThreadDisplayUtils {
    private static final String TAG = HostThreadDisplayUtils.class.getSimpleName();

    public static CharSequence calculateTitleText(Context context, Thread thread, User loggedInUser) {
        ReservationStatusDisplay statusDisplay = ReservationStatusDisplay.forHost(thread);
        String userNames = ThreadUtils.generateNamesString(context, thread, loggedInUser, InboxType.Host);
        return SpannableUtils.makeColoredString(context.getString(C0880R.string.bullet_with_space_parameterized, new Object[]{statusDisplay.getString(context), userNames}), statusDisplay.getColor(context));
    }

    public static String calculateReservationText(Context context, Thread thread) {
        if (thread.hasDates()) {
            return thread.getStartDate().getDateSpanStringWithDayOfWeek(context, thread.getNights());
        }
        Log.w(TAG, "Thread is missing dates: " + thread.getId());
        return null;
    }

    public static String calculateListingText(Context context, ListingSummary listing) {
        AirbnbAccountManager accountManager = AirbnbApplication.instance(context).component().accountManager();
        if (listing == null || accountManager.getCurrentUser().getTotalListingsCount() <= 1) {
            return null;
        }
        return listing.getName();
    }

    public static CharSequence calculatePreviewText(Context context, Thread thread) {
        return SpannableUtils.makeColoredString(calculatePreviewTextString(context, thread), ContextCompat.getColor(context, getPreviewTextColor(thread)));
    }

    private static int getPreviewTextColor(Thread thread) {
        switch (thread.getReservationStatus()) {
            case Pending:
                return C0880R.color.c_arches;
            case Inquiry:
                if (isInquiryWithin24Hours(thread)) {
                    return C0880R.color.c_arches;
                }
                break;
        }
        return C0880R.color.n2_text_color_unselected;
    }

    public static boolean isInquiryWithin24Hours(Thread thread) {
        boolean z = true;
        Post inquiry = thread.getActiveInquiry();
        if (inquiry == null) {
            return false;
        }
        if (inquiry.getCreatedAt().plusDays(1).getMillis() - AirDateTime.now().getMillis() <= 0) {
            z = false;
        }
        return z;
    }

    private static String calculatePreviewTextString(Context context, Thread thread) {
        ReservationStatus status = thread.getReservationStatus();
        if (thread.isRequiresResponse() && status == ReservationStatus.Inquiry && thread.getActiveInquiry() != null) {
            return MessagingUtil.getRespondWithinTime(context, thread.getActiveInquiry().getCreatedAt());
        }
        if (status == ReservationStatus.Pending && thread.getInquiryReservation() != null && thread.getInquiryReservation().getPendingExpiresAt() != null) {
            return thread.getInquiryReservation().getPendingExpiresAt().getExpiresAtString(context);
        }
        if (!thread.needsReview()) {
            return thread.getTextPreview(context, thread.getOtherUser().getName());
        }
        int numberOfDays = thread.daysLeftToReview();
        if (numberOfDays == 0) {
            return context.getString(C0880R.string.days_left_to_write_review_zero);
        }
        return context.getResources().getQuantityString(C0880R.plurals.days_left_to_write_review, numberOfDays, new Object[]{Integer.valueOf(numberOfDays)});
    }
}
