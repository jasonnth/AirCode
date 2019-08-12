package com.airbnb.android.lib.enums;

import com.airbnb.android.lib.C0880R;
import java.util.ArrayList;
import java.util.List;

public enum LegacyUnlistReason {
    NoLongerHaveAccess(C0880R.C0882id.unlist_reason_no_longer_have_access_to_space_cell, 2, "no_longer_have_access"),
    TooMuchWork(C0880R.C0882id.unlist_reason_hosting_too_much_work_cell, 5, "too_much_work"),
    NotEarnEnough(C0880R.C0882id.unlist_reason_not_earning_enough_cell, 6, "not_earn_enough"),
    LawQuestions(C0880R.C0882id.unlist_reason_questions_about_law_cell, 7, "law_questions"),
    TrustQuestions(C0880R.C0882id.unlist_reason_questions_about_trust_cell, 8, "trust_questions"),
    NegativeExperience(C0880R.C0882id.unlist_reason_negative_experience_cell, 9, "negative_experience"),
    OtherReason(C0880R.C0882id.unlist_reason_other_cell, 4, "other_reasons");
    
    public static final List<LegacyUnlistReason> LEGACY_UNLIST_REASONS = null;
    private final int reason;
    private final String reasonEvent;
    private final int viewId;

    static {
        LEGACY_UNLIST_REASONS = new ArrayList();
        LEGACY_UNLIST_REASONS.add(NoLongerHaveAccess);
        LEGACY_UNLIST_REASONS.add(TooMuchWork);
        LEGACY_UNLIST_REASONS.add(NotEarnEnough);
        LEGACY_UNLIST_REASONS.add(LawQuestions);
        LEGACY_UNLIST_REASONS.add(TrustQuestions);
        LEGACY_UNLIST_REASONS.add(NegativeExperience);
    }

    private LegacyUnlistReason(int viewId2, int reason2, String reasonEvent2) {
        this.viewId = viewId2;
        this.reason = reason2;
        this.reasonEvent = reasonEvent2;
    }

    public int getViewId() {
        return this.viewId;
    }

    public int getReason() {
        return this.reason;
    }

    public String getReasonEvent() {
        return this.reasonEvent;
    }

    public static LegacyUnlistReason getUnlistStateForResourceId(int viewId2) {
        for (LegacyUnlistReason reason2 : LEGACY_UNLIST_REASONS) {
            if (reason2.getViewId() == viewId2) {
                return reason2;
            }
        }
        throw new IllegalStateException("Invalid view id in LegacyUnlistReason:getUnlistStateForResourceId");
    }
}
