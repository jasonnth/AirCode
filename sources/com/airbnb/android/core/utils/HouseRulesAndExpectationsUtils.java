package com.airbnb.android.core.utils;

import android.content.Context;
import android.text.TextUtils;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.ListingExpectation;
import com.airbnb.android.core.models.User;
import com.airbnb.android.utils.ListUtils;
import com.google.common.collect.FluentIterable;
import java.util.Collection;
import java.util.List;

public class HouseRulesAndExpectationsUtils {
    public static final int ADDED_DETAILS_MAX_LINE_LENGTH = 10;
    public static final int LONG_TERM_STAY_MIN_DAYS = 30;

    private HouseRulesAndExpectationsUtils() {
    }

    public static List<ListingExpectation> getCheckedExpectations(List<ListingExpectation> listingExpectations) {
        return FluentIterable.from((Iterable<E>) listingExpectations).filter(HouseRulesAndExpectationsUtils$$Lambda$1.lambdaFactory$()).toList();
    }

    public static boolean showExpectations(List<ListingExpectation> listingExpectations) {
        return Trebuchet.launch(TrebuchetKeys.ANDROID_SHOW_LISTING_EXPECTATIONS_GUEST) && !ListUtils.isEmpty((Collection<?>) listingExpectations);
    }

    public static int getExpectationsHeaderStringId(boolean isForBooking) {
        return isForBooking ? C0716R.string.p4_listing_expectations_section_header : C0716R.string.p3_listing_expectations_section_header;
    }

    public static boolean hasAnyAddedDetails(List<ListingExpectation> expectations) {
        return !FluentIterable.from((Iterable<E>) expectations).filter(HouseRulesAndExpectationsUtils$$Lambda$2.lambdaFactory$()).isEmpty();
    }

    static /* synthetic */ boolean lambda$hasAnyAddedDetails$0(ListingExpectation e) {
        return !TextUtils.isEmpty(e.getAddedDetails());
    }

    public static String getHouseRulesAndExpectationsTitle(Context context, List<ListingExpectation> expectations, boolean forBooking, User host) {
        boolean hasExpectations = showExpectations(expectations);
        if (TextUtils.isEmpty(host.getFirstName())) {
            if (forBooking) {
                return context.getString(C0716R.string.review_the_rules);
            }
            return context.getString(C0716R.string.p3_house_rules_and_expectations_title);
        } else if (hasExpectations) {
            if (!forBooking) {
                return context.getString(C0716R.string.p3_house_rules_and_expectations_title);
            }
            return context.getString(C0716R.string.p4_house_rules_and_expectations_title, new Object[]{host.getFirstName()});
        } else if (forBooking) {
            return context.getString(C0716R.string.p4_house_rules_and_expectations_title, new Object[]{host.getFirstName()});
        } else {
            return context.getString(C0716R.string.host_name_house_rules, new Object[]{host.getFirstName()});
        }
    }

    public static String getHouseRulesAndExpectationsDescription(Context context, List<ListingExpectation> expectations, boolean forBooking, User host) {
        boolean hasExpectations = showExpectations(expectations);
        if (TextUtils.isEmpty(host.getFirstName())) {
            if (forBooking) {
                return context.getString(C0716R.string.review_the_rules);
            }
            return context.getString(C0716R.string.p3_house_rules_and_expectations_title);
        } else if (hasExpectations) {
            if (forBooking) {
                return context.getString(C0716R.string.p4_house_rules_and_expectations_description, new Object[]{host.getFirstName()});
            }
            return context.getString(C0716R.string.p3_house_rules_and_expectations_description, new Object[]{host.getFirstName()});
        } else if (forBooking) {
            return null;
        } else {
            return context.getString(C0716R.string.host_name_house_rules_subtitle, new Object[]{host.getFirstName()});
        }
    }

    public static boolean shouldShowLongTermHouseRules(AirDate checkin, AirDate checkout) {
        if (checkin == null || checkout == null || checkin.getDaysUntil(checkout) <= 30) {
            return false;
        }
        return true;
    }
}
