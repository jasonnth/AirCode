package com.airbnb.android.managelisting.settings;

import com.airbnb.android.managelisting.C7368R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class UnlistReasonUtils {
    public static final int UNLIST_REASON_LAW_QUESTION = 3;
    public static final int UNLIST_REASON_NEGATIVE_EXPERIENCE = 5;
    public static final int UNLIST_REASON_NOT_EARN_ENOUGH = 2;
    public static final int UNLIST_REASON_NO_ACCESS = 0;
    public static final int UNLIST_REASON_OTHER = 6;
    public static final int UNLIST_REASON_TOO_MUCH_WORK = 1;
    public static final int UNLIST_REASON_TRUST_QUESTION = 4;

    @Retention(RetentionPolicy.SOURCE)
    public @interface UnlistReason {
    }

    public static Integer[] allReasons() {
        return new Integer[]{Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6)};
    }

    public static int getReasonStringRes(int reason) {
        switch (reason) {
            case 0:
                return C7368R.string.manage_listing_unlist_reason_no_access_to_space;
            case 1:
                return C7368R.string.manage_listing_unlist_reason_hosting_too_much_work;
            case 2:
                return C7368R.string.manage_listing_unlist_reason_not_earning_enough;
            case 3:
                return C7368R.string.manage_listing_unlist_reason_have_questions_about_law_and_taxes;
            case 4:
                return C7368R.string.f10101x6e9c04d4;
            case 5:
                return C7368R.string.f10100xf49575ef;
            case 6:
                return C7368R.string.manage_listing_unlist_reason_other;
            default:
                throw new RuntimeException("Unexpected unlist reason in getReasonStringRes: " + Integer.toString(reason));
        }
    }
}
